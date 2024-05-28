package com.spring_practice.t5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.sqlite.SQLiteConfig;

public class DB<T> {
	String dbFileName = "";
	String tableName = "";

	Connection connection;

	DB(String dbFileName, String tableName) {
		this.dbFileName = dbFileName;
		this.tableName = tableName;
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

	// ---------- 예전 방식으로 해 보기! ---------- //

	public void createTable() {
		String query = "CREATE TABLE " + this.tableName
				+ " (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, middleScore INT, finalScore INT, totalScore INT, avgScore REAL, created TEXT);";

		this.open();
		try {
			Statement statement = this.connection.createStatement();
			statement.execute(query);
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
	}

	public void insertData(Student student) throws SQLException {
		// Statement 말고 PreparedStatement를 써서 완성된 쿼리를 안 써 줘도 됨! ?로 비워두고 set 해 주기!
		String query = "INSERT INTO " + this.tableName
				+ "(name, middleScore, finalScore, totalScore, avgScore, created) VALUES (?, ?, ?, ?, ?, ?)";

		this.open();
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement(query);
			preparedStatement.setString(1, student.name);
			preparedStatement.setInt(2, student.middleScore);
			preparedStatement.setInt(3, student.finalScore);
			preparedStatement.setInt(4, student.middleScore + student.finalScore);
			preparedStatement.setDouble(4, (student.middleScore + student.finalScore) / 2.0);
			preparedStatement.setString(6, student.created);
			preparedStatement.execute();
			preparedStatement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
	}

	public void updateData(Student student) throws SQLException {
		// SQL 쿼리를 포함한 예외 메시지가 브라우저로 출력되면 SQL 인젝션이 발생할 수 있음! => PreparedStatement 사용
		String query = "UPDATE " + this.tableName
				+ " SET name=?, middleScore=?, finalScore=?, totalScore=?, avgScore=?, created=?" + " WHERE id=?";

		this.open();
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement(query);
			preparedStatement.setString(1, student.name);
			preparedStatement.setInt(2, student.middleScore);
			preparedStatement.setInt(3, student.finalScore);
			preparedStatement.setInt(4, student.middleScore + student.finalScore);
			preparedStatement.setDouble(4, (student.middleScore + student.finalScore) / 2.0);
			preparedStatement.setString(6, student.created);
			preparedStatement.setInt(7, student.id);
			preparedStatement.execute();
			preparedStatement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
	}

	public boolean deleteData(Student student) {
		String query = "DELETE FROM " + this.tableName + " WHERE id=?";

		this.open();
		int result = 0;
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement(query);
			preparedStatement.setInt(1, student.id);
			result = preparedStatement.executeUpdate();
//			System.out.println(result);
			preparedStatement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();

		if (result > 0) {
			return true;
		}
		return false;
	}

	public ArrayList<Student> selectData() {
		ArrayList<Student> studentList = new ArrayList<>();
		String query = "SELECT * FROM " + this.tableName;

		this.open();
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Student s = new Student();
				s.id = resultSet.getInt("id");
				s.name = resultSet.getString("name");
				s.middleScore = resultSet.getInt("middleScore");
				s.finalScore = resultSet.getInt("finalScore");
				s.totalScore = resultSet.getInt("totalScore");
				s.avgScore = resultSet.getDouble("avgScore");
				s.created = resultSet.getString("created");
				studentList.add(s);
			}
			resultSet.close();
			preparedStatement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();

		return studentList;
	}

	public Student detailData(Student student) {
		String query = "SELECT * FROM " + this.tableName + " WHERE id=?";

		this.open();
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement(query);
			preparedStatement.setInt(1, student.id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				student.setId(resultSet.getInt("id"));
				student.setName(resultSet.getString("name"));
				student.setMiddleScore(resultSet.getInt("middleScore"));
				student.setFinalScore(resultSet.getInt("finalScore"));
				student.setTotalScore(resultSet.getInt("totalScore"));
				student.setAvgScore(resultSet.getDouble("avgScore"));
				student.setCreated(resultSet.getString("created"));
			}
			resultSet.close();
			preparedStatement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();

		return student;
	}

}
