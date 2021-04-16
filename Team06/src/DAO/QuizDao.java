package DAO;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import DTO.modifyQuiz;
import DTO.runtimeQuiz;
import JDBC_io.JDBC_IO;
import UI.ErrorHandler;

public class QuizDao implements SuperDao {
	
	public List<modifyQuiz> modQuizList;
	
	private JDBC_IO DB;
	private RuntimeQuizDao rq;
	
	private ErrorHandler errHandler;
	private String userID;
	
	public QuizDao(String id) {
	      rq = new RuntimeQuizDao();
	      modQuizList = new ArrayList<modifyQuiz>();
	      DB = new JDBC_IO();
	      userID = id;
	      getMemberQuiz();
   }
	public QuizDao(String id, RuntimeQuizDao rq) {
		modQuizList = new ArrayList<modifyQuiz>();
		DB = new JDBC_IO();
		this.rq = rq;		
		userID = id;
		getMemberQuiz();
	}
	public void getMemberQuiz() {
		modQuizList = DB.selectQuiz(userID);
		
		if(modQuizList == null || modQuizList.size() < 1) {
			return;
		}
		setRuntimeQuiz();
	}
	public String commitQuiz() {
		int delete = 0;
		int update = 0;
		int insert = 0;
		
		for (int i = 0; i < modQuizList.size(); i++) {
			if(modQuizList.get(i).getModState() == 1) {
				update += DB.updateQuiz(modQuizList.get(i).getQuiz(), 
						modQuizList.get(i).getAnswer(), 
						modQuizList.get(i).getUSER_ID(), 
						modQuizList.get(i).getQuizTag());
			}
			else if(modQuizList.get(i).getModState() == 2) {
				delete += DB.deleteQuiz(modQuizList.get(i).getUSER_ID(), modQuizList.get(i).getQuizTag());
			}
			else if(modQuizList.get(i).getModState() == 3) {
				insert += DB.insertQuiz(modQuizList.get(i).getQuiz(),
						modQuizList.get(i).getAnswer(),
						modQuizList.get(i).getQuizTag(),
						modQuizList.get(i).getUSER_ID());
			}
		}
		if(delete == 0 && update == 00 && insert == 0) {
			return null;
		}
		modQuizList.clear();
		getMemberQuiz();
		return "수정 및 삭제 작업이 완료되었습니다..!!\n수정 작업 : " + update + "건\n삭제 작업 : " + delete + "건\n삽입 작업 : " + insert + "건";
		/*
		System.out.println("수정 및 삭제 작업이 완료되었습니다..!!");
		System.out.println("수정 작업 : " + update + "건");
		System.out.println("삭제 작업 : " + delete + "건");
		System.out.println("삽입 작업 : " + insert + "건");*/
	}
	public void entryQuiz(String id, String quiz, String answer, String quizTag) {
		modifyQuiz tmp = new modifyQuiz();
		tmp.setUSER_ID(id);
		tmp.setQuiz(quiz);
		tmp.setQuizTag(quizTag);
		tmp.setAnswer(answer);
		tmp.setModState(3);
		modQuizList.add(tmp);
	}
	public void deleteQuiz(String id, String quizTag) {
		int index = -1;
		
		for (modifyQuiz modQuiz : modQuizList) {
			if(quizTag.equals(modQuiz.getQuizTag())) {
				index = modQuizList.indexOf(modQuiz);
			}
		}
		modifyQuiz tmp = modQuizList.get(index);
		tmp.setModState(2);
		modQuizList.set(index, tmp);
	}
	public void updateQuiz(String id, String quiz, String answer, String quizTag) {
		int index = -1;
		
		for (modifyQuiz modifyQuiz2 : modQuizList) {
			index = modQuizList.indexOf(modifyQuiz2);
		}
		modifyQuiz tmp = new modifyQuiz();
		tmp.setQuiz(quiz);
		tmp.setAnswer(answer);
		tmp.setQuizTag(quizTag);
		tmp.setUSER_ID(id);
		tmp.setModState(1);
		modQuizList.set(index, tmp);
	}
	public void setRuntimeQuiz() {
		List<runtimeQuiz> runtimeQuiz = new LinkedList<runtimeQuiz>();
		
		for (int i = 0; i < modQuizList.size(); i++) {
			runtimeQuiz tmp = new runtimeQuiz();
			tmp.setQuiz(modQuizList.get(i).getQuiz());
			tmp.setAnswer(modQuizList.get(i).getAnswer());
			runtimeQuiz.add(tmp);
		}
		rq.setQuizList(runtimeQuiz);
	}
	public boolean isQuizExist(String quizTag) {
		if(modQuizList == null || modQuizList.size() < 1) {
			return false;
		}
		for (int i = 0; i < modQuizList.size(); i++) {
			if(quizTag.equals(modQuizList.get(i).getQuizTag())) {
				return true;
			}
		}
		return false;
	}
	public String getUserID() {
		return userID;
	}
	public void displayList() {
		System.out.println(modQuizList.toString());
	}
	@Override
	public SuperDao getDao() {
		return this;
	}
}
