/**
 * 
 */
package fr.netapsys.devoxx.core.api.question;

/**
 * @author alexandre
 *
 */
public interface QuestionManager {

	/**
	 * Prendre en compte une nouvelle question
	 * @param question La question
	 */
	void register(final Question question) ;
	
	/**
	 * Supprimer la question courante
	 */
	void unregister() ;

	/**
	 * Récupérer la question courante
	 * @return La question courante
	 */
	Question getQuestion() ;
	
}
