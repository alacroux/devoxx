/**
 * 
 */
package fr.netapsys.devoxx.core.engine.quiz;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;

import fr.netapsys.devoxx.core.api.response.Response;

/**
 * @author alexandre
 *
 */
public class QuizEngine extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		getContext().getShutdownStrategy().setTimeout(10);
		
		JacksonDataFormat responseDataFormat = new JacksonDataFormat(Response.class);
		
		from("activemq:quizz.checkResponse")
			.id("checkResponseRoute")
			.unmarshal(responseDataFormat)
			.processRef("responseProcessor")
			.to("direct:notify")
		.end();
		
		from("direct:notify")
			.marshal().json(JsonLibrary.Jackson)
			.convertBodyTo(String.class, "UTF-8")
			.to("activemq:web.notify")
		.end();
		
	}

}
