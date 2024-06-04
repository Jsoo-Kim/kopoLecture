package com.kopo.Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.taglibs.standard.extra.spath.Step;
import org.sqlite.SQLiteConfig;

public class DB {
	String dbFileName = ""; // 데이터베이스 파일 이름

	Connection connection; // 연결 객체

	// 기본 생성자
	DB() {
		this.dbFileName = "c:/kopo/customer.db"; // 생성자 내부로 이동
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

		String query = "CREATE TABLE customer (`idx` INTEGER PRIMARY KEY AUTOINCREMENT, `name` TEXT, `id` TEXT, `pw` TEXT, `gender` INTEGER, `address` TEXT, `created` TEXT)";

		this.open();
		try {
			Statement statement = this.connection.createStatement();
			statement.execute(query);
			statement.close();
		} catch (Exception e) {
		}
		this.close(); // 데이터베이스 연결을 닫기
	}

	public void insertData(Customer customer) {
		String query = "INSERT INTO customer (`name`, `id`, `pw`, `gender`, `address`, `created`) VALUES (?, ?, ?, ?, ?, ?)";

		// 데이터베이스 연결 열기
		this.open();
		try {
			PreparedStatement statement = this.connection.prepareStatement(query);
			// PreparedStatement에 파라미터 설정
			statement.setString(1, customer.name);
			statement.setString(2, customer.id);
			statement.setString(3, customer.pw);
			statement.setInt(4, customer.gender);
			statement.setString(5, customer.address);
			statement.setString(6, customer.created);
			// SQL 쿼리 실행
			statement.execute();
			// PreparedStatement 닫기
			statement.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
	}

	public List<Customer> getAllCustomers() {
		String query = "SELECT * FROM customer";
		ArrayList<Customer> list = new ArrayList<Customer>();
		this.open();

		try {
			PreparedStatement statement = this.connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {

				Customer customer = new Customer();
				customer.idx = resultSet.getInt("idx");
				customer.name = resultSet.getString("name");
				customer.id = resultSet.getString("id");
				customer.pw = resultSet.getString("pw");
				customer.gender = resultSet.getInt("gender");
				customer.address = resultSet.getString("address");
				customer.created = resultSet.getString("created");
				// ArrayList에 추가
				list.add(customer);
			}
			resultSet.close();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
		return list;
	}

	public boolean signinAction(Customer customer) {
		String query = "SELECT * FROM customer WHERE id=? AND pw=?";
		this.open();

		boolean result = false;
		try {
			PreparedStatement statement = this.connection.prepareStatement(query);
			statement.setString(1, customer.id);
			statement.setString(2, customer.pw);
			
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
