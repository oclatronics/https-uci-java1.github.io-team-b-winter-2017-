import java.io.*;
import java.util.*;

public class CourseToReg {
	private HashMap<String, Course> courseList = new HashMap<String, Course>();

	public CourseToReg(String filename) {
		File courses = new File(filename);
		Scanner readCourseFile;
		try {
			readCourseFile = new Scanner(courses);
			readCourseFile.nextLine();
			while (readCourseFile.hasNext()) {
				String courseLine = readCourseFile.nextLine();
				String[] parts = courseLine.split(",");
				int cap = Integer.parseInt(parts[3]);
				courseList.put(parts[0], new Course(parts[0], parts[1], parts[2], cap));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Course findCourse(String courseNumber){
		return courseList.get(courseNumber);
	}

	public Collection<Course> getCourses() {
		return courseList.values();
	}
}
