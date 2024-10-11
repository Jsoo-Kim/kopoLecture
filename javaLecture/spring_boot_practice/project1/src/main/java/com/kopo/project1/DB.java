package com.kopo.project1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.*;

import org.sqlite.SQLiteConfig;

public class DB {
	String dbFileName = ""; // 데이터베이스 파일 이름

	Connection connection; // 연결 객체

	// 기본 생성자
	DB() {
		this.dbFileName = "c:/kopo/project1.db"; // 생성자 내부로 이동
	}

	// 파라미터를 받는 생성자
	DB(String dbFileName) {
		this.dbFileName = dbFileName;
	}

	private void open() {
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			this.connection = DriverManager.getConnection("jdbc:sqlite:/" + this.dbFileName, config.toProperties());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void close() {
		if (this.connection != null) {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		this.connection = null;
	}

	public void createTable() {

		String query = "CREATE TABLE users (`idx` INTEGER PRIMARY KEY AUTOINCREMENT, `name` TEXT, `id` TEXT, `password` TEXT, `sex` TEXT, `address` TEXT, `phone` TEXT, `user_type` TEXT, `updated` TEXT, `created` TEXT)";

		this.open();
		try {
			Statement statement = this.connection.createStatement();
			statement.execute(query);
			statement.close();
		} catch (Exception e) {
		}
		this.close(); // 데이터베이스 연결을 닫기
	}
	
	public void insertData(User user) {
		String query = "INSERT INTO users (`name`, `id`, `password`, `sex`, `address`, `phone`, `user_type`, `updated`, `created`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		// 데이터베이스 연결 열기
		this.open();
		try {
			java.util.Date nowDate = new java.util.Date(); // 현재 시간
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String updated = simpleDateFormat.format(nowDate);
			String created = updated;
			PreparedStatement statement = this.connection.prepareStatement(query);
			// PreparedStatement에 파라미터 설정
			statement.setString(1, user.name);
			statement.setString(2, user.id);
			statement.setString(3, user.password);
			statement.setString(4, user.sex);
			statement.setString(5, user.address);
			statement.setString(6, user.phone);
			statement.setString(7, "guest");
			statement.setString(8, updated);
			statement.setString(9, created);
			// SQL 쿼리 실행
			statement.execute();
			// PreparedStatement 닫기
			statement.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
	}

	public boolean loginData(User user) {
		String query = "SELECT * FROM users WHERE id=? AND password=?";
		this.open();

		boolean result = false;
		try {
			PreparedStatement statement = this.connection.prepareStatement(query);
			statement.setString(1, user.id);
			statement.setString(2, user.password);
			
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				result = true;
			}
			
			resultSet.close();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
		return result;
	}
}
