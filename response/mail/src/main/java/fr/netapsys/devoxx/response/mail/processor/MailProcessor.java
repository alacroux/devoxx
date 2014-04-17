/**
 * 
 */
package fr.netapsys.devoxx.response.mail.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.netapsys.devoxx.core.api.response.Response;

/**
 * @author alexandre
 *
 */
public class MailProcessor implements Processor {
	
	private Logger logger = LoggerFactory.getLogger(MailProcessor.class);
	
	public void process(Exchange exchange) throws Exception {
		
		final String id = exchange.getExchangeId();
		
		logger.info("start email processing [ id : {} ]", id);
		
		final String emailBody = exchange.getIn().getBody(String.class);
		final String emailSubject = exchange.getIn().getHeader("Subject", String.class);
		final String emailFrom = exchange.getIn().getHeader("From", String.class);
		
		logger.info("email processed [ id : {} | body : {} | subject : {} | from : {} ]", id, emailBody, emailSubject, emailFrom);
		
		Response response = createResponse(emailSubject, emailBody, emailFrom);
		exchange.getIn().setBody(response);
		
		
	}

	/**
	 * Construit la réponse à partir de l'email : 
	 * si le sujet n'est pas vide, la réponse est le sujet
	 * sinon si le body n'est pas vide, la réponse est le body
	 * sinon la réponse est "no response found in mail"
	 * @param emailSubject Le sujet de l'email
	 * @param emailBody Le corps de l'email
	 * @param emailFrom La provenance de l'email 
	 * @return Une réponse
	 */
	private Response createResponse(final String emailSubject, final String emailBody, final String emailFrom) {
		String response = "no response found in mail";
		if(! StringUtils.isEmpty(emailSubject)) {
			response = emailSubject;
		}
		else if(! StringUtils.isEmpty(emailBody)) {
			response = emailBody;
		}
		return new Response(response, getCryptedFrom(emailFrom));
	}
	
	private String getCryptedFrom(String emailFrom) {
		if (emailFrom.contains("<")) {
			emailFrom = StringUtils.substringBetween(emailFrom, "<", ">");
		}
		if(emailFrom.contains("@")) {
			return StringUtils.substringBefore(emailFrom, "@");
		}
		return emailFrom;
	}
	
}
