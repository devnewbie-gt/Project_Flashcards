package DTO;

public class runtimeQuiz {
	private String quiz, answer;
		
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
	public runtimeQuiz() {
		this.quiz = "DummyQuiz";
		this.answer = "DummyAnswer";
	}
	public runtimeQuiz(String quiz, String answer, String id) {
		super();
		this.quiz = quiz;
		this.answer = answer;
	}
	@Override
	public String toString() {
		return "runtimeQuiz [quiz=" + quiz + "\nanswer=" + answer + "]";
	}
	
	
}
