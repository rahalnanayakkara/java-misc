package courses;

import io.*;

public class Course implements java.io.Serializable
{	
	private static final long serialVersionUID = 2L;
	
	public String courseName;
	public String[] subjects;
	public int subNo, studNo, courseCode;;
	public Student[] s = new Student[100];
	
	public Course(int courseCode)
	{
		Screen.nextLine();
		this.courseCode=courseCode;
		courseName = Keyboard.readString("Course name");
		subNo = Keyboard.readInt("No. of subjects");
		subjects = new String[subNo];
		for(int x=0; x<subNo; x++) subjects[x] = Keyboard.readString("Enter subject "+(x+1)+" :");
		studNo = 0;
		Screen.nextLine();
	}
	
	public void addStudent(int regNo)
	{
		studNo++;
		s[studNo] = new Student(regNo, courseCode, subNo);
		save();
	}
	
	public void save()
	{
		WriteFile.open(courseName+".dat");
		WriteFile.writeObject(this);
		WriteFile.close();
	}
	
	public void listSubjects()
	{
		Screen.nextLine();
		for(int x=0; x<subNo; x++) Screen.show((x+1)+". ", subjects[x]);
		Screen.nextLine();
	}
	
	public void enterMarks()
	{
		Screen.nextLine();
		listSubjects();
		int x = Keyboard.readInt("Select subject",1,subNo)-1;
		for(int y=1; y<=studNo; y++) s[y].marks[x] = Keyboard.readInt("Marks of "+s[y].name);
		save();
		Screen.nextLine();
	}
	
	public int getStudent(int regNo)
	{
		int courseRegNo=0;
		for(int y=1; y<=studNo; y++) 
			if (regNo==s[y].regNo) courseRegNo=y;
		return courseRegNo;
	}
	
	public void changeMarks()
	{
		Screen.nextLine();
		int x = getStudent(Keyboard.readInt("Enter Student Registration Number"));
		if (x!=0)
		{
			for(int y=0; y<subNo; y++)
			s[x].marks[y] = Keyboard.readInt("Enter marks for "+subjects[y]);
			save();
		}
		else Screen.show("No such student is registered in this course", "");
	}
	
	public void showCourseResults()
	{
		Screen.nextLine();
		Screen.display("Reg No\t\t\t",0);
		Screen.display("Name\t\t\t",0);
		Screen.display(subjects);
		for(int y=1; y<=studNo; y++)
		{
			Screen.display(s[y].regNo+"\t\t\t",0);
			Screen.display(s[y].name+"\t\t\t",0);
			Screen.display(s[y].marks);
		}
		Screen.nextLine();
	}
	
	public void studentResult()
	{
		Screen.nextLine();
		int x = getStudent(Keyboard.readInt("Enter Student Registration Number"));
		if (x==0) Screen.show("No such student is registered in this course", "");
		else s[x].showResults(courseName, subjects);
		Screen.nextLine();
	}
}
