
public class Course {
	private String courseID;
	private String courseName;
	private String instructorName;
	private int capacity;
	
	// Create an object from components
	Course(String id, String name, String instructor, int cap){
		courseID = id;
		courseName = name;
		instructorName = instructor;
		capacity  = cap;
	}

	public String getCourseName() {
		return courseName;
	}

	public String getInstructorName() {
		return instructorName;
	}

	public String getCourseID() {
		return courseID;
	}

	public int getCapacity() {
		return capacity;
	}
	
	

}
