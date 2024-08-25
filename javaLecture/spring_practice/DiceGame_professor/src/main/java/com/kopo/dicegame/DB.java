package com.kopo.dicegame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.sqlite.SQLiteConfig;

public class DB {
    String dbFileName = "";

    Connection connection;
    
    DB() {
        this.dbFileName = "c:/kopo/dice.db";
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
		String query = "CREATE TABLE history (`idx` INTEGER PRIMARY KEY AUTOINCREMENT, `user` INTEGER, `com` INTEGER, `result` TEXT, `created` TEXT)";
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
		String query = "INSERT INTO history (`user`, `com`, `result`, `created`) VALUES (?, ?, ?, ?)";
		this.open();
		try {
			PreparedStatement statement = this.connection.prepareStatement(query);
			statement.setInt(1, dice.user);
			statement.setInt(2, dice.com);
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
		String query = "SELECT * FROM history";
		ArrayList<Dice> list = new ArrayList<Dice>();
		this.open();
		try {
			PreparedStatement statement = this.connection.prepareStatement(query);
			ResultSet resultSet =  statement.executeQuery();
			while (resultSet.next()) {
				Dice dice = new Dice();
				dice.idx = resultSet.getInt("idx");
				dice.user = resultSet.getInt("user");
				dice.com = resultSet.getInt("com");
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
}
