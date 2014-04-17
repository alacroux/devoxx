/**
 * 
 */
package fr.netapsys.devoxx.core.engine.quiz.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import fr.netapsys.devoxx.core.api.question.Question;
import fr.netapsys.devoxx.core.api.question.QuestionManager;
import fr.netapsys.devoxx.core.api.response.Response;

/**
 * @author alexandre
 *
 */
public class ResponseProcessor implements Processor {
	
	private QuestionManager questionManager = null;
	
	@Override
	public void process(Exchange exchange) throws Exception {

		Response response = exchange.getIn().getBody(Response.class);
		Question question = getQuestionManager().getQuestion();
		
		if (question == null) {
			throw new RuntimeException("La question n'a pas encore été posée !");
		}
		
		if(match(question, response)) {
			response.setMatchQuestion(true);
		}
		
	}
	
	/**
	 * @param question La question courante
	 * @param response La réponse soumise
	 * @return true si c'est la bonne réponse
	 */
	private boolean match(final Question question, final Response response) {
		return question.getResponse().equalsIgnoreCase(response.getResponse());
	}

	/**
	 * @return the questionManager
	 */
	protected QuestionManager getQuestionManager() {
		return questionManager;
	}

	/**
	 * @param questionManager the questionManager to set
	 */
	public void setQuestionManager(QuestionManager questionManager) {
		this.questionManager = questionManager;
	}

}
