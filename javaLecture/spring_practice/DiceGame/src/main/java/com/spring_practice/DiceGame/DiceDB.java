package com.spring_practice.DiceGame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.sqlite.SQLiteConfig;


public class DiceDB {

	String dbFileName = "";

	Connection connection;

	DiceDB() {
//		this.dbFileName = "d:\\gitMaster\\kopoLecture\\javaLecture\\spring_practice\\dice.db";
		this.dbFileName = "/Users/kimjisoo/Desktop/Jisoo/kopo/kopoLecture/javaLecture/spring_practice/dice.db";
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
	
	// 혹시 예약어랑 겹치는 필드명이 있으면 ``로 감싸주면 사용 가능
	public void createTable() {
		String query = "CREATE TABLE history (`idx` INTEGER PRIMARY KEY AUTOINCREMENT, `user` INTEGER, `computer` INTEGER, `result` TEXT, `created` TEXT)";
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
	
	public void insertData(Dice dice) {
		String query = "INSERT INTO history (`user`, `computer`, `result`, `created`) VALUES (?, ?, ?, ?);";
		this.open();
		try {
			PreparedStatement statement = this.connection.prepareStatement(query);
			statement.setInt(1, dice.user);
			statement.setInt(2, dice.computer);
			statement.setString(3, dice.result);
			statement.setString(4, dice.created);
			statement.execute();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
	}
	
	public ArrayList<Dice> selectData() {
		String query = "SELECT * FROM history;";
		ArrayList<Dice> list = new ArrayList<Dice>();
		this.open();
		try {
			PreparedStatement statement = this.connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Dice dice = new Dice();
				dice.idx = resultSet.getInt("idx");
				dice.user = resultSet.getInt("user");
				dice.computer = resultSet.getInt("computer");
				dice.result = resultSet.getString("result");
				dice.created = resultSet.getString("created");
				list.add(dice);
			}
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
		return list;
	}
	
	public void updateData(Dice dice) {
		String query = "UPDATE history"
				+ " SET user=?, computer=?, result=?, created=?" 
				+ " WHERE idx=?";

		this.open();
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement(query);
			preparedStatement.setInt(1, dice.user);
			preparedStatement.setInt(2, dice.computer);
			preparedStatement.setString(3, dice.result);
			preparedStatement.setString(4, dice.created);
			preparedStatement.setInt(5, dice.idx);
			preparedStatement.execute();
			preparedStatement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
	}
	
	public Dice detailData(Dice dice) {
		String query = "SELECT * FROM history WHERE idx=?";

		this.open();
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement(query);
			preparedStatement.setInt(1, dice.idx);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				dice.idx = resultSet.getInt("idx");
				dice.user = resultSet.getInt("user");
				dice.computer = resultSet.getInt("computer");
				dice.result = resultSet.getString("result");
				dice.created = resultSet.getString("created");
			}
			resultSet.close();
			preparedStatement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();

		return dice;
	}
	
	public boolean deleteData(Dice dice) {
		String query = "DELETE FROM history WHERE idx=?";

		this.open();
		int result = 0;
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement(query);
			preparedStatement.setInt(1, dice.idx);
			result = preparedStatement.executeUpdate();
			System.out.println("delete result: " + result);
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
	
}
