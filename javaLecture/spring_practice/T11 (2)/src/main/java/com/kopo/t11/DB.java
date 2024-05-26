package com.kopo.t11;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
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

    public boolean createTable(T t) {
        String queryString = "";
        
        Class<?> dataClass = t.getClass();
		Field[] dataClassFields = dataClass.getDeclaredFields();
        String indexFieldName = dataClassFields[0].getName();

        for (Field field : dataClassFields) {
            String fieldType = field.getType().toString();
			String fieldName = field.getName();

            if (!queryString.isEmpty()) {
                queryString = queryString + ",";
            }

            if (fieldName.equals(indexFieldName) && fieldType.matches("int")) {
                queryString = queryString + "`" + fieldName + "`" + " INTEGER PRIMARY KEY AUTOINCREMENT";
            } else if (fieldType.matches("int")) {
                queryString = queryString + "`" + fieldName + "`" + " INTEGER";
            } else if (fieldType.matches("(double|float)")) {
                queryString = queryString + "`" + fieldName + "`" + " REAL";
            } else if (fieldType.matches(".*String")) {
                queryString = queryString + "`" + fieldName + "`" + " TEXT";
            } 
        }
        queryString = "CREATE TABLE " + this.tableName + "(" + queryString + ");";
    
        boolean result = true;
        this.open();
        try {
            Statement statement = this.connection.createStatement();
			statement.execute(queryString);
			statement.close();
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        this.close();
        return result;
    }

    public boolean insertData(T t) {
        Class<?> dataClass = t.getClass();
        Field[] dataClassFields = dataClass.getDeclaredFields();
        String indexFieldName = dataClassFields[0].getName();

        String fields = "";
        String values = "";
        for (Field field : dataClassFields) {
            String fieldType = field.getType().toString();
			String fieldName = field.getName();
            if (fieldName.equals(indexFieldName) && fieldType.matches("int")) {
                continue;
            }

            if (!fields.isEmpty()) {
                fields = fields + ",";
            }
            fields = fields + "`" + fieldName + "`";
            if (!values.isEmpty()) {
                values = values + ",";
            }
            values = values + "?";
        }
        String queryString = "INSERT INTO " + this.tableName + " (" + fields + ")" + " VALUES (" + values + ")";

        boolean result = true;
        this.open();
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(queryString);
            int valueIndex = 1;
            for (Field field : dataClassFields) {
                String fieldType = field.getType().toString();
                String fieldName = field.getName();
                if (fieldName.equals(indexFieldName) && fieldType.matches("int")) {
                    continue;
                }
                String getterName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                if (fieldType.matches("int")) {
                    int getValue = 0;
                    try {
                        Method getter = dataClass.getDeclaredMethod(getterName);
                        getValue = (int) getter.invoke(t);
                    } catch (NoSuchMethodException e) {
                        getValue = field.getInt(t);
                    }
                    preparedStatement.setInt(valueIndex, getValue);
                } else if (fieldType.matches("float")) {
                    float getValue = 0;
                    try {
                        Method getter = dataClass.getDeclaredMethod(getterName);
                        getValue = (float) getter.invoke(t);
                    } catch (NoSuchMethodException e) {
                        getValue = field.getFloat(t);
                    }
                    preparedStatement.setFloat(valueIndex, getValue);
                } else if (fieldType.matches("double")) {
                    double getValue = 0;
                    try {
                        Method getter = dataClass.getDeclaredMethod(getterName);
                        getValue = (double) getter.invoke(t);
                    } catch (NoSuchMethodException e) {
                        getValue = field.getDouble(t);
                    }
                    preparedStatement.setDouble(valueIndex, getValue);
                } else if (fieldType.matches(".*String")) {
                    String getValue = "";
                    try {
                        Method getter = dataClass.getDeclaredMethod(getterName);
                        getValue = (String) getter.invoke(t);
                    } catch (NoSuchMethodException e) {
                        getValue = (String) field.get(t);
                    }
                    preparedStatement.setString(valueIndex, getValue);
                } else {
                    preparedStatement.setString(valueIndex, "");
                }
                valueIndex++;
            }
            preparedStatement.execute();
			preparedStatement.close();
        } catch (Exception e) {
        	result = false;
            e.printStackTrace();
        }
        this.close();
        return result;
    }
    
    public void updateData(T t) {
        Class<?> dataClass = t.getClass();
        Field[] dataClassFields = dataClass.getDeclaredFields();
        String indexFieldName = dataClassFields[0].getName();

        String setValue = "";
        int whereIdx = -1;
        for (Field field : dataClassFields) {
            String fieldType = field.getType().toString();
			String fieldName = field.getName();
            if (fieldName.equals(indexFieldName) && fieldType.matches("int")) {
                try {
                    whereIdx = field.getInt(t);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                continue;
            }

            if (!setValue.isEmpty()) {
                setValue = setValue + ",";
            }
            setValue = setValue + "`" + fieldName + "`" + "=?";
        }
        String queryString = "UPDATE " + this.tableName + " SET " + setValue + " WHERE " + indexFieldName + "=" + whereIdx;

        this.open();
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(queryString);
            int valueIndex = 1;
            for (Field field : dataClassFields) {
                String fieldType = field.getType().toString();
                String fieldName = field.getName();
                if (fieldName.equals(indexFieldName) && fieldType.matches("int")) {
                    continue;
                }
                String getterName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                if (fieldType.matches("int")) {
                    int getValue = 0;
                    try {
                        Method getter = dataClass.getDeclaredMethod(getterName);
                        getValue = (int) getter.invoke(t);
                    } catch (NoSuchMethodException e) {
                        getValue = field.getInt(t);
                    }
                    preparedStatement.setInt(valueIndex, getValue);
                } else if (fieldType.matches("float")) {
                    float getValue = 0;
                    try {
                        Method getter = dataClass.getDeclaredMethod(getterName);
                        getValue = (float) getter.invoke(t);
                    } catch (NoSuchMethodException e) {
                        getValue = field.getFloat(t);
                    }
                    preparedStatement.setFloat(valueIndex, getValue);
                } else if (fieldType.matches("double")) {
                    double getValue = 0;
                    try {
                        Method getter = dataClass.getDeclaredMethod(getterName);
                        getValue = (double) getter.invoke(t);
                    } catch (NoSuchMethodException e) {
                        getValue = field.getDouble(t);
                    }
                    preparedStatement.setDouble(valueIndex, getValue);
                } else if (fieldType.matches(".*String")) {
                    String getValue = "";
                    try {
                        Method getter = dataClass.getDeclaredMethod(getterName);
                        getValue = (String) getter.invoke(t);
                    } catch (NoSuchMethodException e) {
                        getValue = (String) field.get(t);
                    }
                    preparedStatement.setString(valueIndex, getValue);
                } else {
                    preparedStatement.setString(valueIndex, "");
                }
                valueIndex++;
            }
            preparedStatement.execute();
			preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.close();
    }

    public boolean deleteData(T t) {
        Class<?> dataClass = t.getClass();
        Field[] dataClassFields = dataClass.getDeclaredFields();
        String indexFieldName = dataClassFields[0].getName();

        int whereIdx = -1;
        for (Field field : dataClassFields) {
            String fieldType = field.getType().toString();
			String fieldName = field.getName();
            if (fieldName.equals(indexFieldName) && fieldType.matches("int")) {
                try {
                    whereIdx = field.getInt(t);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                continue;
            }
        }
        String queryString = "DELETE FROM " + this.tableName + " WHERE " + indexFieldName + "=" + whereIdx;

        this.open();
        int result = 0;
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(queryString);
            result = preparedStatement.executeUpdate();
            System.out.println(result);
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

    public ArrayList<T> selectData(T t) {
        Class<?> dataClass = t.getClass();
        Field[] dataClassFields = dataClass.getDeclaredFields();

        String queryString = "SELECT * FROM " + this.tableName;
        ArrayList<T> list = new ArrayList<T>();

        this.open();
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(queryString);
            ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				T fieldData = (T) dataClass.getDeclaredConstructor().newInstance();
				for (Field field : dataClassFields) {
					String fieldType = field.getType().toString();
					String fieldName = field.getName();
					if (fieldType.matches("int")) {
						field.setInt(fieldData, resultSet.getInt(fieldName));
                    } else if (fieldType.matches("float")) {
						field.setFloat(fieldData, resultSet.getFloat(fieldName));
                    } else if (fieldType.matches("double")) {
						field.setDouble(fieldData, resultSet.getDouble(fieldName));
					} else {
						field.set(fieldData, resultSet.getString(fieldName));
					}
				}
				list.add(fieldData);
			}
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.close();

        return list;
    }

    public T detailData(T t) {
        Class<?> dataClass = t.getClass();
        Field[] dataClassFields = dataClass.getDeclaredFields();
        String indexFieldName = dataClassFields[0].getName();

        int whereIdx = -1;
        for (Field field : dataClassFields) {
            String fieldType = field.getType().toString();
			String fieldName = field.getName();
            if (fieldName.equals(indexFieldName) && fieldType.matches("int")) {
                try {
                    whereIdx = field.getInt(t);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        String queryString = "SELECT * FROM " + this.tableName + " WHERE " + indexFieldName + "=" + whereIdx;
        T resultData = null;

        this.open();
        try {
            resultData = (T) dataClass.getDeclaredConstructor().newInstance();
            
            PreparedStatement preparedStatement = this.connection.prepareStatement(queryString);
            ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				for (Field field : dataClassFields) {
					String fieldType = field.getType().toString();
					String fieldName = field.getName();
					if (fieldType.matches("int")) {
						field.setInt(resultData, resultSet.getInt(fieldName));
                    } else if (fieldType.matches("float")) {
						field.setFloat(resultData, resultSet.getFloat(fieldName));
                    } else if (fieldType.matches("double")) {
						field.setDouble(resultData, resultSet.getDouble(fieldName));
					} else {
						field.set(resultData, resultSet.getString(fieldName));
					}
				}
			}
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.close();

        return resultData;
    }
}
