package fr.netapsys.devoxx.response.mail.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

import fr.netapsys.devoxx.response.mail.processor.MailProcessor;

public class MailRoutes extends RouteBuilder {

	private MailProcessor mailProcessor;
	
	@Override
	public void configure() throws Exception {

		from("pop3://{{mail.box.host}}:{{mail.box.port}}?"
				+ "username={{mail.box.username}}&"
				+ "password={{mail.box.password}}&"
				+ "delete={{mail.box.delete}}&"
				+ "debugMode={{mail.box.debugMode}}&"
				+ "consumer.delay={{mail.box.consumerDelay}}")
			.id("mailRoute")
			.process(mailProcessor)
			.marshal().json(JsonLibrary.Jackson)
			.convertBodyTo(String.class, "UTF-8")
			.to("activemq:quizz.checkResponse")
		.end();

	}

	/**
	 * @return the mailProcessor
	 */
	protected MailProcessor getMailProcessor() {
		return mailProcessor;
	}

	/**
	 * @param mailProcessor the mailProcessor to set
	 */
	public void setMailProcessor(MailProcessor mailProcessor) {
		this.mailProcessor = mailProcessor;
	}

}
