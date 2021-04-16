package DTO;

public class modifyQuiz {
	
	private String quiz, answer, quizTag;
	private  String USER_ID;
	int modState;	// 0 : 변동없음, 1 : 수정, 2 : 삭제, 3 : 삽입
	
	public modifyQuiz() {
		quiz = "";
		answer = "";
		quizTag = "";
		USER_ID = "";
		modState = 0;
	}
	public modifyQuiz(String quiz, String answer, String quizTag, String USER_ID) {
		super();
		this.quiz = quiz;
		this.answer = answer;
		this.quizTag = quizTag;
		this.USER_ID = USER_ID;
		this.modState = 0;
	}

	public String getQuiz() {
		return quiz;
	}

	public void setQuiz(String quiz) {
		this.quiz = quiz;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getQuizTag() {
		return quizTag;
	}

	public void setQuizTag(String quizTag) {
		this.quizTag = quizTag;
	}

	public int getModState() {
		return modState;
	}

	public void setModState(int modState) {
		this.modState = modState;
	}

	public String getUSER_ID() {
		return USER_ID;
	}

	public void setUSER_ID(String uSER_ID) {
		USER_ID = uSER_ID;
	}

	@Override
	public String toString() {
		return "modifyQuiz [quiz=" + quiz + ", answer=" + answer + ", quizTag=" + quizTag + ", USER_ID=" + USER_ID
				+ ", modState=" + modState + "]";
	}
	
	
	
}
