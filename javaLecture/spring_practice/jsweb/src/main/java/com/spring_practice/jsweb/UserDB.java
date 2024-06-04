package com.spring_practice.jsweb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.sqlite.SQLiteConfig;

public class UserDB {

	String dbFileName = "";

	Connection connection;

	UserDB() {
		this.dbFileName = "d:\\gitMaster\\kopoLecture\\javaLecture\\spring_practice\\users.db";
//		this.dbFileName = "/Users/kimjisoo/Desktop/Jisoo/kopo/kopoLecture/javaLecture/spring_practice/users.db";
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
		String query = "CREATE TABLE users (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `name` TEXT, `userId` TEXT, `userPw` TEXT, `gender` TEXT, `address` TEXT, `created` TEXT, `role` TEXT)";
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
	
	public void insertData(User user) {
		String query = "INSERT INTO users (`name`, `userId`, `userPw`, `gender`, `address`, `created`, `role`) VALUES (?, ?, ?, ?, ?, ?, ?);";
	
		this.open();
		try {
			PreparedStatement statement = this.connection.prepareStatement(query);
			statement.setString(1, user.name);
			statement.setString(2, user.userId);
			statement.setString(3, user.userPw);
			statement.setString(4, user.gender);
			statement.setString(5, user.address);
			statement.setString(6, user.created);
			statement.setString(7, user.role);
			statement.execute();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
	}
	
	public ArrayList<User> selectData() {
		String query = "SELECT * FROM users;";
		ArrayList<User> list = new ArrayList<User>();
		this.open();
		try {
			PreparedStatement statement = this.connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				User user = new User();
				user.id = resultSet.getInt("id");
				user.name = resultSet.getString("name");
				user.userId = resultSet.getString("userId");
				user.userPw = resultSet.getString("userPw");
				user.gender = resultSet.getString("gender");
				user.address = resultSet.getString("address");
				user.created = resultSet.getString("created");
				list.add(user);
			}
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
		return list;
	}
	
	public void updateData(User user) {
		String query = "UPDATE users"
				+ " SET name=?, userId=?, userPw=?, gender=?, address=?, created=?" 
				+ " WHERE id=?";

		this.open();
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement(query);
			preparedStatement.setString(1, user.name);
			preparedStatement.setString(2, user.userId);
			preparedStatement.setString(3, user.userPw);
			preparedStatement.setString(4, user.gender);
			preparedStatement.setString(5, user.address);
			preparedStatement.setString(6, user.created);
			preparedStatement.setInt(7, user.id);
			preparedStatement.execute();
			preparedStatement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
	}
	
	public User detailData(User user) {
		String query = "SELECT * FROM users WHERE id=?";

		this.open();
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement(query);
			preparedStatement.setInt(1, user.id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				user.id = resultSet.getInt("id");
				user.name = resultSet.getString("name");
				user.userId = resultSet.getString("userId");
				user.userPw = resultSet.getString("userPw");
				user.gender = resultSet.getString("gender");
				user.address = resultSet.getString("address");
				user.created = resultSet.getString("created");
			}
			resultSet.close();
			preparedStatement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();

		return user;
	}
	
	public boolean deleteData(User user) {
		String query = "DELETE FROM history WHERE id=?";

		this.open();
		int result = 0;
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement(query);
			preparedStatement.setInt(1, user.id);
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
	
    public Map<String, String> signin(User user) {
        String query = "SELECT * FROM users WHERE userId=? and userPw=?";
        Map<String, String> result = new HashMap<>();

        this.open();
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUserId());
            preparedStatement.setString(2, user.getUserPw());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String storedPassword = resultSet.getString("userPw");
                String inputPassword = user.getUserPw();

                if (storedPassword.equals(inputPassword)) {
                    result.put("status", "success");
                    result.put("message", "로그인 성공");
                    result.put("role", resultSet.getString("role"));
                } else {
                    result.put("status", "fail");
                    result.put("message", "비밀번호가 일치하지 않습니다.");
                }
            } else {
                result.put("status", "fail");
                result.put("message", "해당하는 사용자가 없습니다.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            result.put("status", "error");
            result.put("message", "데이터베이스 오류");
        } finally {
            this.close();
        }

        return result;
    }
}

