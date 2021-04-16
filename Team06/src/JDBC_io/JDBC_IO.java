package JDBC_io;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.User;
import DTO.modifyQuiz;

public class JDBC_IO {
	
	final String DRIVER = "oracle.jdbc.OracleDriver";
	final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	final String USER = "quiz_user";
	final String PASSWORD = "quiz";
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public JDBC_IO() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public boolean selectID(String id) {
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			String sql = ""
					+ "select id "
					+ "from quizuser "
					+ "where id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String result = rs.getString("ID");
				
				if(result.equals(id)) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return false;
	}
	public boolean selectPW(String id, String pwd) {
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			String sql = ""
					+ "select pwd "
					+ "from quizuser "
					+ "where id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String rspw = rs.getString("PWD");
				if(rspw.equals(pwd)) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			queryClose();
		}
		return false;
	}
	
	public boolean insertUser(String id, String pwd) {
		
		int result = 0;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		
			String sql = ""
					+ "insert into quizuser "
					+ "values (?, ?, sysdate) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			noQueryClose();
		}
		return isDBProcDone(result);
	}
	public User selectUser(String ID) {
		User newUser = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			String sql = ""
					+ "SELECT * "
					+ "FROM QUIZUSER "
					+ "WHERE ID = ? ";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, ID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				newUser = new User(
										rs.getString("id"),
										rs.getString("SIGN_DATE")
							);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			queryClose();
		}
		return newUser;
	}
	
	
	// 퀴즈 등록 메소드
	// 처리 성공시 작업횟수 카운트(1 return), 실패시 카운트를 하지 않도록(0 return) 합니다.
	public int insertQuiz(String quiz, String answer, String quizTag, String id) {
		int result = 0;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			String sql = ""
					+ "insert into quiz "
					+ "values (quiz_seq.nextval, ?, ?, ?, ?) ";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, quiz);
			pstmt.setString(2, answer);
			pstmt.setString(3, quizTag);
			pstmt.setString(4, id);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			noQueryClose();
		}
		return result;
	}
	// quiz 테이블의 문제와 답을 가져오는 select문
	// 매개변수는 사용자 id이고, 리스트 타입으로 반환합니다.
	public List<modifyQuiz> selectQuiz(String id) {
		ArrayList<modifyQuiz> list = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			String sql = ""
					+ "select * "
					+ "from quiz "
					+ "where user_id = ? ";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			/*
			if(rs == null) {
				return null;
			}*/
			list = new ArrayList<modifyQuiz>();
			while (rs.next()) {
				modifyQuiz newQuiz = new modifyQuiz(
						rs.getString("quiz"),
						rs.getString("answer"),
						rs.getString("quiz_Tag"),
						id
						);
				list.add(newQuiz);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			queryClose();
		}
		return list;
	}
	public int deleteQuiz(String id, String quizTag) {
		int result = 0;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			String sql = "delete from quiz where user_id = ? and quiz_tag = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, quizTag);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			noQueryClose();
		}
		return result;
	}
	public int updateQuiz(String quiz, String answer, String id, String quizTag) {
		int result = 0;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			String sql = ""
					+ "update quiz "
					+ " set quiz = ?, answer = ? "
					+ " where user_id = ? and quiz_tag	= ? ";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, quiz);
			pstmt.setString(2, answer);
			pstmt.setString(3, id);
			pstmt.setString(4, quizTag);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			noQueryClose();
		}
		return result;
	}
	
	private void queryClose() {
		try {
			if(rs != null) rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		noQueryClose();
	}
	private void noQueryClose() {
		try {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private boolean isDBProcDone(int result) {
		return (result != 0) ? true : false;
	}
}
