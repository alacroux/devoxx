angular.module('quizz').factory('quizzService', ['$q', '$rootScope',
  function($q, $rootScope) {
  
    var service = {};
    var callbacks = {};
    var callbackIndex = 0;
    var webSocket = null;
    var disconnections = 0;
    var retryWaitMax = 120;
    var pendingRequests = [];
  
    service.connect = function() {
      webSocket = new WebSocket("ws://devoxx01.pr.netapsys.fr:9292/quizz");
      
      webSocket.onopen = function() {
        console.log("Connexion au serveur effectuée !");
        connectionStatus = true;
        for (var requestIndex in pendingRequests) {
          console.log('Sending old requests', pendingRequests[requestIndex]);
          webSocket.send(JSON.stringify(pendingRequests[requestIndex]));
        }
        pendingRequests = [];
		sendRequest({});
      };

      webSocket.onerror = function() {
        console.log("La connexion au serveur a provoqué une erreur ! Veuillez rafraichir la page.");
      };

      webSocket.onmessage = function(message) {
        listener(JSON.parse(message.data));
      };

      webSocket.onclose = function() {
        console.log("La connexion au serveur interrompue.");
        connectionStatus = false;
        disconnections++;
        var retryWait = Math.pow(2, disconnections);
        if(retryWait > retryWaitMax) {
          disconnections--;
          retryWait = retryWaitMax;
        }
        console.log('Retrying in ' + retryWait + ' seconds...');
        setTimeout(service.connect, retryWait * 1000);
      };
    };

    function sendRequest(request) {
      var defer = $q.defer();
      if(!connectionStatus) {
        console.log('Saving request until connected');
        pendingRequests.push(request);
      }
      else {
        console.log('Sending request', request);
        webSocket.send(JSON.stringify(request));
      }
      return defer.promise;
    }
  
    function listener(data) {
      console.log('Message reçu : ', data);
      if (data != null) {
      	if (data.question != undefined) {
      		$rootScope.question = data;
      		$rootScope.responses = [];
      	} else if (data.from != undefined) {
      		$rootScope.responses.push(data);
      	}
      	$rootScope.$digest();
      } else {
        $rootScope.question = data;
	    $rootScope.responses = [];
      }
    }

    service.send = function(operation, data) {
      delete data.$$hashKey;
      return sendRequest(data);
    };
  
    service.connect();
  
    return service;
  }
]);
