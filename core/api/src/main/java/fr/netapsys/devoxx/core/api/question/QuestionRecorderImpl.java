package fr.netapsys.devoxx.core.api.question;


public class QuestionRecorderImpl {

	private Question question = null;
	
	private QuestionManager questionManager = null;
	
	public void register() {
		getQuestionManager().register(getQuestion());
	}
	
	public void unregister() {
		if (getQuestion().getId().equals(getQuestionManager().getQuestion().getId())) {
			getQuestionManager().unregister();
		}
	}

	/**
	 * @return the question
	 */
	protected Question getQuestion() {
		return question;
	}


	/**
	 * @param question the question to set
	 */
	public void setQuestion(Question question) {
		this.question = question;
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
