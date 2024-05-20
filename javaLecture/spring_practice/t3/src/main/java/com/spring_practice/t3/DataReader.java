package com.spring_practice.t3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class DataReader {
	private Connection connection;
	private String dbUrl;
	private String dbUser;
	private String dbPassword;
	private String dbTableName;

	static { // 이걸 명시해놓으면 jdbc 사용 가능
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // Class.forName을 통해서 동적으로 로딩 가능
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DataReader(String dbUrl, String dbUser, String dbPassword, String dbTableName) {
		this.dbUrl = dbUrl;
		this.dbUser = dbUser;
		this.dbPassword = dbPassword;
		this.dbTableName = dbTableName;
	}

	public boolean open() {
		try {
			this.connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean close() {
		if (this.connection == null) {
			return true;
		}
		try {
			this.connection.close(); // 커넥션 종료
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

//	public int createTable() throws SQLException {
//		if (this.connection == null) {
//			throw new SQLException("DB is not open");
//		}
//		String query = "CREATE TABLE " + this.dbTableName
//				+ " (name VARCHAR2(100), score NUMBER)";
//		Statement statement = this.connection.createStatement();
//		int result = statement.executeUpdate(query);
//		statement.close();
//		return result;
//	}

	public int createTable() throws SQLException {
		// 데이터베이스 연결이 열려 있는지 확인. 연결이 닫혀 있으면 SQLException을 던짐
		if (this.connection == null) {
			throw new SQLException("DB is not open");
		}
		// 시퀀스를 생성하는 SQL 쿼리 문자열을 정의
		String query = "CREATE SEQUENCE sequence " + "START WITH 1 " + "INCREMENT BY 1 " + "NOMAXVALUE";

		// 시퀀스를 생성하기 위해 Statement 객체를 생성하고, 쿼리를 실행
		Statement sequenceStatement = this.connection.createStatement();
		sequenceStatement.executeUpdate(query);
		sequenceStatement.close();

		// 테이블을 생성하는 SQL 쿼리 문자열을 정의
		query = "CREATE TABLE " + this.dbTableName 
				+ " (id NUMBER DEFAULT sequence.NEXTVAL PRIMARY KEY, "
				+ " name VARCHAR2(100), " 
				+ " score NUMBER)";

		// 테이블을 생성하기 위해 Statement 객체를 생성하고, 쿼리를 실행
		Statement tableStatement = this.connection.createStatement();
		// 테이블 생성 결과를 정수형 변수로 받음
		int result = tableStatement.executeUpdate(query);
		// 테이블 생성이 끝나면 Statement 객체를 닫음
		tableStatement.close();

		return result;
	}

	public int insertData(String name, int score) throws SQLException {
//		Random random = new Random();
//		String query = "INSERT INTO " + this.dbTableName + " (id, name, mid_score, final_score) VALUES (?, ?, ?, ?)";
		String query = "INSERT INTO " + this.dbTableName + " (name, score) VALUES (?, ?)";
		PreparedStatement preparedStatement = this.connection.prepareStatement(query);
//		for (int i = 1; i <= 10; i++) {
//			Integer midScore = random.nextInt(100) + 1;
//			Integer finalScore = random.nextInt(100) + 1;
//			preparedStatement.setInt(1, i);
//			preparedStatement.setString(2, "학생" + String.valueOf(i));
//			preparedStatement.setInt(3, midScore);
//			preparedStatement.setInt(4, finalScore);
//			preparedStatement.addBatch();
//		}
		preparedStatement.setString(1, name);
		preparedStatement.setInt(2, score);
//		int[] result = preparedStatement.executeBatch();
		int result = preparedStatement.executeUpdate();
		preparedStatement.close();
//		return result.length;
		return result;
	}

	public boolean selectData() throws SQLException {
		boolean result = false;
		String query = "SELECT * FROM " + this.dbTableName + " WHERE id = ?";
		PreparedStatement preparedStatement = this.connection.prepareStatement(query);
		preparedStatement.setInt(1, 1);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			System.out.println(resultSet.getString("name"));
			result = true;
		}
		resultSet.close();
		preparedStatement.close();
		return result;
	}

	public int deleteTable() throws SQLException {
		String query = "DELETE FROM " + dbTableName;
		Statement statement = this.connection.createStatement();
		int result = statement.executeUpdate(query);
		statement.close();
		return result;
	}

	public int dropTable() throws SQLException {
		String query = "DROP TABLE " + dbTableName;
		Statement statement = this.connection.createStatement();
		int result = statement.executeUpdate(query);
		statement.close();
		return result;
	}

}
