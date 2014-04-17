package fr.netapsys.devoxx.core.api.response;

import java.io.Serializable;

public class Response implements Serializable {

	/**
	 * Id de sérialisation.
	 */
	private static final long serialVersionUID = 8367669944067379167L;

	private String id = "";
	
	private String response = "";
	
	private String from = "";
	
	private boolean matchQuestion = false;

	public Response() {
		
	}
	
	/**
	 * @param response
	 * @param from
	 */
	public Response(String response, String from) {
		super();
		this.response = response;
		this.from = from;
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
	 * @return the response
	 */
	public String getResponse() {
		return response;
	}

	/**
	 * @param response the response to set
	 */
	public void setResponse(String response) {
		this.response = response;
	}

	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * @return the match
	 */
	public boolean isMatchQuestion() {
		return matchQuestion;
	}

	/**
	 * @param match the match to set
	 */
	public void setMatchQuestion(boolean matchQuestion) {
		this.matchQuestion = matchQuestion;
	}
	
}
