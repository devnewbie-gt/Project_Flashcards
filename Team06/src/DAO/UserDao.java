package DAO;

import java.util.ArrayList;

import DTO.User;
import JDBC_io.JDBC_IO;
import UI.ErrorHandler;

public class UserDao implements SuperDao {
	ArrayList<User> userList = new ArrayList<>();
	JDBC_IO DB;
	ErrorHandler errorHandler;
	public UserDao() {
		DB = new JDBC_IO();
		errorHandler = new ErrorHandler();
	}
	
	public void entryUser(String ID, String PW) {
		if(isUserExist(ID)) {
			errorHandler.UserAlreadyExist();
			return;
		}
		if(!DB.insertUser(ID, PW)) {
			errorHandler.DBError();
			return;
		}
		System.out.println("User Entry Success");			
	}
	public boolean login(String ID, String PW) {
		User newUser = null;
		if(!isUserExist(ID)) {
			errorHandler.UserNoExist();
			return false;
		}
		if(!identifyUser(ID, PW)) {
			errorHandler.UserNoMatch();
			return false;
		}
		newUser = DB.selectUser(ID).setLocalUser();
		userList.add(newUser);
		System.out.println("User Login Success");
		return true;
	}
	public void paintUserList() {
		for(User tempUser : userList) {
			System.out.println(tempUser);
		}
	}
	public boolean isUserExist(String ID) {
		return DB.selectID(ID);
	}
	private boolean identifyUser(String ID, String PW) {
		return DB.selectPW(ID, PW);
	}
	@Override
	public SuperDao getDao() {
		return this;
	}
}
