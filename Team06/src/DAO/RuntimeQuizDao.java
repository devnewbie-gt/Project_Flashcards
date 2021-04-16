package DAO;

import java.util.LinkedList;
import java.util.List;

import DTO.runtimeQuiz;

public class RuntimeQuizDao implements SuperDao {
	public List<runtimeQuiz> runtimeQuizList;
	private int index = 0;
	
	public RuntimeQuizDao() {
		runtimeQuizList = new LinkedList<runtimeQuiz>();
	}
	public void setQuizList(List<runtimeQuiz> list) {
		if(runtimeQuizList.size() != 0) {
			runtimeQuizList.clear();
		}
		
		
		for (int i = 0; i < list.size(); i++) {
			runtimeQuiz tmp = new runtimeQuiz();
			tmp.setQuiz(list.get(i).getQuiz());
			tmp.setAnswer(list.get(i).getAnswer());
			runtimeQuizList.add(tmp);
		}
	}
	public String getQuiz() {
		return DisplayQuiz();
	}
	public String getAnswer() {
		return DisplayAnswer();
	}
	public runtimeQuiz getRuntimeQuiz() {
		if(isEmpty()) insertRuntimeQuiz(new runtimeQuiz());
		return runtimeQuizList.get(index);
	}
	public void insertRuntimeQuiz(runtimeQuiz runtimeQuiz) {
		runtimeQuizList.add(runtimeQuiz);
	}
	public String DisplayQuiz() {
		if(isEmpty()) return "Solved All Quiz";
		return runtimeQuizList.get(index).getQuiz();
	}
	public String DisplayAnswer() {
		if(isEmpty()) return "Solved All Quiz";
		return runtimeQuizList.get(index).getAnswer();
	}
	public String Correct() {
		if(isEmpty()) {
			return "Solved All Quiz";
		}
		runtimeQuizList.remove(index);
		return null;
	}
	public String inCorrect() {
		if(isEmpty()) {
			return "Solved All Quiz";
		}
		runtimeQuiz tmp = new runtimeQuiz();
		tmp.setQuiz(runtimeQuizList.get(index).getQuiz());
		tmp.setAnswer(runtimeQuizList.get(index).getAnswer());
		runtimeQuizList.add(tmp);
		runtimeQuizList.remove(index);
		return null;
	}
	public boolean isEmpty() {
		if(runtimeQuizList.size() == 0) {
			return true;
		}
		return false;
	}
	@Override
	public SuperDao getDao() {
		return this;
	}
}
