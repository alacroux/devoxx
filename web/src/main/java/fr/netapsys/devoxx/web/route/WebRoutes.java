package fr.netapsys.devoxx.web.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.websocket.WebsocketConstants;
import org.apache.camel.model.dataformat.JsonLibrary;

public class WebRoutes extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		
		from("websocket://0.0.0.0:9292/quizz")
			.id("web.getQuestion")
			.beanRef("questionManager", "getQuestion")
			.marshal().json(JsonLibrary.Jackson)
			.to("websocket://0.0.0.0:9292/quizz")
		.end();

		from("activemq:web.notify")
			.id("web.in")
			.setHeader(WebsocketConstants.SEND_TO_ALL, constant(true))
			.to("websocket://0.0.0.0:9292/quizz")
		.end();
	}

}
