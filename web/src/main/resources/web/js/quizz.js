var quizzModule = angular.module('quizz', []);

quizzModule.controller('quizzController',
    ['$scope', '$rootScope', 'quizzService', function($scope, $rootScope, quizzService) {

	$rootScope.question = {id: "???", question: "La question n'a pas encore été divulguée"};
    
    $rootScope.responses = [];
    
    $scope.getLevel = function(response) {
    	if (response.matchQuestion == true) {
    		return 'success';
    	} else {
    		return 'danger';
    	}
  	};
    
}]);
