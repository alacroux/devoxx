package fr.netapsys.devoxx.core.api.question;

import java.io.Serializable;

public class Question implements Serializable {

	/**
	 * Id de sérialisation.
	 */
	private static final long serialVersionUID = 1648954308466534087L;

	private String id = "";
	
	private String question = "";
	
	private String response = "";
	
	public Question() {
		
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}

	/**
	 * @param question the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}

	/**
	 * @return the response
	 */
	public String getResponse() {
		return response;
	}

	/**
	 * @param reponse the response to set
	 */
	public void setResponse(String response) {
		this.response = response;
	}
	
}
