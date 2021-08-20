package courses;

import io.*;

public class Student implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;
	
	public String name, idNo;
	public int[] marks;
	public int courseCode, regNo;
	
	public Student(int regNo, int courseCode, int subNo)
	{
		Screen.nextLine();
		this.regNo = regNo;
		this.courseCode = courseCode;
		name = Keyboard.readString("Student name :");
		idNo = Keyboard.readString("NIC no of student");
		marks = new int[subNo];
		Screen.nextLine();
		Screen.show("Student Registration No :",regNo);
		Screen.nextLine();
	}
	
	public void showResults(String courseName, String[] subjects)
	{
		Screen.nextLine();
		Screen.show("Student name :", name);
		Screen.show("Registration No ", regNo);
		Screen.show("Course Followed :", courseName);
		Screen.nextLine();
		for(int x=0; x<marks.length; x++) Screen.show(subjects[x]+" :", marks[x]);
		Screen.nextLine();
	}
}
