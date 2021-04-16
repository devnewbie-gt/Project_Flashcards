package UI;

public class ErrorHandler {
	
	public void DBError() {
		System.out.println("DB Error");
	}
//	회원등록, ID 중복검사
	public void UserAlreadyExist() {
		System.out.println("User Already Exist");
	}
	
//	로그인, ID존재 여부 확인
	public void UserNoExist() {
		System.out.println("User doesn't Exist"); 
	}
	
//	 로그인 ID, PW 매칭
	public void UserNoMatch() {
		System.out.println("Invalid Password");
	}
	
//	퀴즈입력, 중복검사
	public void AlreadyExistQuiz() {
		System.out.println("Quiz Already Exist");
	}
}
