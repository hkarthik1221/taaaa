package com.ust.Student.dao;

import java.sql.CallableStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ust.Student.bean.Student;

public class StudentDAO {

	private static Connection conn = null;
	private static Statement statement = null;
	private static PreparedStatement pstmt = null;
	private static CallableStatement cstmt = null;
	private static ResultSet rs = null;

	private static final String URL = "jdbc:mysql://localhost:3306/hari";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "pass@word1";

	public static void createConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}


	public static int save(Student student) throws SQLException, ClassNotFoundException {
		createConnection();
		pstmt = conn.prepareStatement("insert into student values(?,?,?,?,?,?)");
		pstmt.setInt(1, student.getRollno());
		pstmt.setString(2, student.getName());
		pstmt.setString(3, student.getEmail());
		pstmt.setLong(4, student.getMobile());
		pstmt.setString(5, student.getGender());
		pstmt.setString(6, student.getDob());
		int insertStatus = 0;
		insertStatus = pstmt.executeUpdate();
		return insertStatus;

	}

	public static int update(int id, Student student) throws SQLException, ClassNotFoundException {
		createConnection();
		pstmt = conn.prepareStatement("update student set name=?,email=?,mobile=?,gender=?,dob=?,where rollno=?");
		pstmt.setInt(3, student.getRollno());
		pstmt.setString(1, student.getName());
		pstmt.setString(2, student.getEmail());
		int insertStatus = 0;
		insertStatus = pstmt.executeUpdate();
		return insertStatus;
	}
	

	public static int delete(int rollno) throws SQLException, ClassNotFoundException {
		createConnection();
		pstmt = conn.prepareStatement("delete from student where rollno =?");
		pstmt.setInt(1, rollno);
		int deleteStatus = 0;
		deleteStatus = pstmt.executeUpdate();
		return deleteStatus;

	}


	public static List<Student> readAll() throws SQLException, ClassNotFoundException {
		createConnection();
		statement = conn.createStatement();
		rs = statement.executeQuery("select * from student");
		List<Student> student = new ArrayList<Student>();
		while (rs.next()) {
			student.add(new Student(rs.getInt("rollno"), rs.getString("name"), rs.getString("email"),
					rs.getLong("mobile"), rs.getString("gender"), rs.getString("dob")));
		}
		return student;
	}

	
	public static Student readById(int id) throws ClassNotFoundException, SQLException {
		createConnection();
		pstmt = conn.prepareStatement("select * from student where rollno=?");
		pstmt.setInt(1, id);
		rs = pstmt.executeQuery();
		Student student = new Student();
		if (rs.next()) {
			student.setRollno(rs.getInt("rollno"));
			student.setName(rs.getString("name"));
			student.setEmail(rs.getString("email"));
			student.setMobile(rs.getLong("mobile"));
			student.setGender(rs.getString("gender"));
			student.setDob(rs.getString("dob"));
		}
		return student;
	}

	public static void closeResource() throws SQLException {
		if (rs != null)
			rs.close();
		if (cstmt != null)
			cstmt.close();
		if (pstmt != null)
			pstmt.close();
		if (statement != null)
			statement.close();
		if (conn != null)
			conn.close();

	}

}
