/**
 * 
 */
package fr.netapsys.devoxx.core.engine.question;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.netapsys.devoxx.core.api.question.Question;
import fr.netapsys.devoxx.core.api.question.QuestionManager;

/**
 * @author alexandre
 *
 * Manager ne gérant qu'une seule question à la fois.
 */
public class QuestionManagerImpl implements QuestionManager {

	private Question question = null;
	
	private CamelContext camelContext = null;
	
	private ProducerTemplate template = null;
	
	private final Logger log = LoggerFactory.getLogger(QuestionManagerImpl.class);
	
	public void init() {
		template = getCamelContext().createProducerTemplate();
	}
	
	@Override
	public void register(Question question) {
		log.info("Enregistrement de la question {} : {}", question.getId(), question.getQuestion());
		this.question = question;
		notifyUi();
	}

	@Override
	public void unregister() {
		log.info("Suppression de la question {}", question.getId());
		this.question = null;
		notifyUi();
	}
	
	private void notifyUi() {
		getTemplate().sendBody("direct:notify", question);
	}

	@Override
	public Question getQuestion() {
		return this.question;
	}

	/**
	 * @return the camelContext
	 */
	protected CamelContext getCamelContext() {
		return camelContext;
	}

	/**
	 * @param camelContext the camelContext to set
	 */
	public void setCamelContext(CamelContext camelContext) {
		this.camelContext = camelContext;
	}

	/**
	 * @return the template
	 */
	protected ProducerTemplate getTemplate() {
		return template;
	}
	
}
