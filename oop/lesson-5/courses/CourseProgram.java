package courses;

import io.*;

public class CourseProgram 
{
	public static int courseNo=0, studNo=0;
	public static Course[] courses = new Course[100];
	
	public static void save()
	{
		WriteFile.open("Courses.dat");
		WriteFile.writeInt(courseNo);
		WriteFile.writeInt(studNo);
		for(int x=1; x<=courseNo; x++) WriteFile.writeString(courses[x].courseName);
		WriteFile.close();
		for(int x=1; x<=courseNo; x++) courses[x].save();
	}
	
	public static void load()
	{
		ReadFile.open("Courses.dat");
		courseNo = ReadFile.readInt();
		studNo = ReadFile.readInt();
		String[] courseNames = new String[courseNo+1];
		for(int x=1; x<=courseNo; x++) courseNames[x]=ReadFile.readString();
		ReadFile.close();
		for(int x=1; x<=courseNo; x++)
		{
			ReadFile.open(courseNames[x]+".dat");
			courses[x] = (Course)ReadFile.readObject();
			ReadFile.close();
		}
	}
	
	public static void newCourse()
	{
		courseNo++;
		courses[courseNo] = new Course(courseNo);
		save();
	}
	
	public static int selectCourse()
	{
		Screen.nextLine();
		for(int x=1; x<=courseNo; x++) 
			Screen.show(x+" .", courses[x].courseName);
		return Keyboard.readInt("Enter choice",1,courseNo);
	}
	
	public static void addStudent()
	{
		studNo++;
		courses[selectCourse()].addStudent(studNo);
	}
	
	public static void enterSubjectMarks()
	{
		Screen.nextLine();
		courses[selectCourse()].enterMarks();
		Screen.nextLine();
	}
	
	public static void modifyMarks()
	{
		Screen.nextLine();
		courses[selectCourse()].changeMarks();
		Screen.nextLine();
	}
	
	public static void courseResults()
	{
		Screen.nextLine();
		courses[selectCourse()].showCourseResults();
		Screen.nextLine();
	}
	
	public static void studentResults()
	{
		Screen.nextLine();
		courses[selectCourse()].studentResult();
		Screen.nextLine();
	}
	
	public static void main(String[] args)
	{
		load();
		loop : while(true)
		{
			int x = Menu.select("New Course","New Student","Enter subject marks","Modify a student\'s marks",
					"Display course results","Display results of a student");
			switch(x)
			{
				case 0: break loop;
				case 1: newCourse(); break;
				case 2: addStudent(); break;
				case 3: enterSubjectMarks(); break;
				case 4: modifyMarks(); break;
				case 5: courseResults(); break;
				case 6: studentResults(); break;
			}
		}
		save();
	}
}
