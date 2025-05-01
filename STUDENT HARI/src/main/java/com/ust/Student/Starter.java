package com.ust.Student;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.ust.Student.bean.Student;
import com.ust.Student.dao.StudentDAO;

public class Starter {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int option = 0;

		while (option >= 0) {
			System.out.println("welcome to student portal");
			System.out.println("1.View students ");
			System.out.println("2.Add students ");
			System.out.println("3.Update students ");
			System.out.println("4.Delete students ");
			System.out.println("5.Exit");
			System.out.println("choose option from   1  -  5: ");
			option = input.nextInt();

			try {
				switch (option) {
				case 1:
					System.out.println("Display all Students");
					List<Student> student = StudentDAO.readAll();
					System.out.println("Rollno \t Name \t Email \t Mobile \t gender \t Dob");
					student.forEach(std -> System.out.println(std.getRollno() + "\t" + std.getName() + "\t"
							+ std.getEmail() + "\t" + std.getMobile() + "\t" + std.getGender() + "\t" + std.getDob()));
					break;
				case 2:
					System.out.println("Add a new student");
					System.out.print("Enter Rollno of student:");
					int rollno = input.nextInt();
					System.out.print("Enter Student's Name :");
					String name = input.next();
					System.out.print("Enter Student's Email:");
					String email = input.next();
					System.out.print("Enter Student's mobile:");
					Long mobile = input.nextLong();
					System.out.print("Enter Student's gender:");
					String gender = input.next();
					System.out.print("Enter Student's Date of birth:");
					String dob = input.next();
					StudentDAO.save(new Student(rollno, name, email, mobile, gender, dob));
					System.out.println("New Record Added to Table!!!");
					break;
				case 3:
					System.out.print("Enter Roll No to update: ");
					int roll1 = input.nextInt();
					input.nextLine();
					Student std1 = StudentDAO.readById(roll1);
					if (std1 == null) {
						System.out.println("Student not found.");
						break;
					}

					System.out.print("Enter name to update (" + std1.getName() + ") or press 0 to skip: ");
					String newName = input.nextLine();
					if (newName.equals("0"))
						std1.setName(newName);

					System.out.print("Enter email to update (" + std1.getEmail() + ") or press 0 to skip: ");
					String newEmail = input.nextLine();
					if (newEmail.equals("0"))
						std1.setEmail(newEmail);

					System.out.print("Enter the gender to update (" + std1.getGender() + ") or press 0 to skip: ");
					String newGender = input.nextLine();
					if (newGender.equals("0"))
						std1.setGender(newGender);

					System.out.print("Enter the mobile number to update (" + std1.getMobile() + ") or 0 to skip: ");
					long newMobile = input.nextLong();
					if (newMobile != 0)
						std1.setMobile(newMobile);
					input.nextLine();

					System.out.print("Enter the DOB to update(" + std1.getDob() + ") or press 0 to skip: ");
					String dob1 = input.nextLine();
					if (!dob1.equals("0"))
						std1.setDob(String.valueOf(dob1));

					int updateStatus = StudentDAO.update(roll1, std1);
					if (updateStatus!=0)
						System.out.println("Student updated successfully!");
					else
						System.out.println("Failed to Update");
					break;

				case 4:
					System.out.println("Delete a student");
					System.out.print("Enter rollno of+ to delete:");
					int rollno2 = input.nextInt();
					int deleteStatus = 0;
					deleteStatus = StudentDAO.delete(rollno2);
					if (deleteStatus != 0)
						System.out.println("1 Record Deleted Successfully");
					else
						System.out.println("Error while deleting ::Check the ID");
					break;
				case 5:
					System.out.println("Thanks for using Student Portal");
					StudentDAO.closeResource();
					System.exit(0);
					break;

				default:-
					System.out.println("Please Enter a number between 1 to 5 only");
					break;
				}

			} catch (SQLException | ClassNotFoundException e) {
				
				e.printStackTrace();

			}
		}

		input.close();
	}
}
