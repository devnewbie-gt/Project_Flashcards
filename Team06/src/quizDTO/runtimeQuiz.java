package quizDTO;

public class runtimeQuiz {
	private String quiz, answer;
	private String ID;

	public runtimeQuiz() {
		this.quiz = "DummyQuiz";
		this.answer = "DummyAnswer";
		this.ID= "NO ONE";
	}
	public runtimeQuiz(String quiz, String answer, String ID) {
		super();
		this.quiz = quiz;
		this.answer = answer;
		this.ID = ID;
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
	public String getID() {
		return ID;
	}
	public void setId(String ID) {
		this.ID = ID;
	}
}
