package com.ust.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCstudent {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost://3306/Hari", "root", "pass@word1");
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("Select * from Student");

		while (rs.next()) {
			System.out.println(rs.getInt("rollno") + " " + rs.getString("name") + " " + rs.getString("email") + " "
					+ rs.getString("gender") + " " + rs.getDate("dob") + " " + rs.getLong("mobile"));

			rs.close();
			statement.close();
			connection.close();
		}
	}

}
