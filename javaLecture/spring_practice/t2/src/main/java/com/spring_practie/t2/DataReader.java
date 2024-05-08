package com.spring_practie.t2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import org.sqlite.SQLiteConfig;

public class DataReader {
	private Connection connection;
	private String dbFileName;
	private String dbTableName;
	static { // 이걸 명시해놓으면 sqlite-jdbc를 사용할 수 있게 됨! 오라클도 이런 식으로 하는 방법 있음
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public DataReader(String databaseFileName, String dbTableName) {
		this.dbFileName = databaseFileName;
		this.dbTableName = dbTableName;
	}
	
	public boolean open() {
		try {
			SQLiteConfig config = new SQLiteConfig();
			this.connection = DriverManager.getConnection("jdbc:sqlite:/" + this.dbFileName, config.toProperties()); // SQLite 오픈하는 법 (다른 DB들도 이런 게 있음)
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
	
	public int createTable() throws Exception {
		if (this.connection == null) {
			throw new Exception("DB is not open");
		}
		// CREATE TABLE students(idx INT PRIMARY KEY, name TEXT, score REAL);
		String query = "CREATE TABLE " + this.dbTableName + " (idx INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, mid_score REAL, final_score REAL);";
		Statement statement = this.connection.createStatement();
		int result = statement.executeUpdate(query);
		statement.close();
		return result;
	}
	
	public int insertData() throws SQLException {
		Random random = new Random();
		// for문으로 한 번에 넣어도 됨!
		String query = "";
		for (int i = 1; i <= 10; i++) {			
			Integer midScore = random.nextInt(100) + 1;
			Integer finalScore = random.nextInt(100) + 1;
			// Values('학생1' <= 이런 식으로 숫자까지 나온 다음 '이걸 닫아줘야 함!!
			query += "INSERT INTO " + this.dbTableName + " (name, mid_score, final_score) Values('학생" + String.valueOf(i) + "', " + midScore + ", " + finalScore + ");";
		}
		Statement statement = this.connection.createStatement();
		int result = statement.executeUpdate(query);
		statement.close();
		return result;
	}
	
	public boolean selectData() throws SQLException {
		boolean result = false;
		String query = "SELECT * FROM " + this.dbTableName + " WHERE ?;";
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
	
	public int deleteTable() throws SQLException { // 테이블 삭제 및 시퀀스인덱스(AutoIncrement) 초기화
		String query = "DELETE FROM " + dbTableName + ";\n" + "UPDATE SQLITE_SEQUENCE SET seq = 0 WHERE name = " + dbTableName + ";";
		Statement statement = this.connection.createStatement();
		int result = statement.executeUpdate(query);
		statement.close();
		return result;
	}
	
}
