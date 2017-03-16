import java.io.*;
import java.util.*;

// Contains student registrations
public class Registrar {
	private Hashtable<Integer, List<String>> registrar = new Hashtable<Integer, List<String>>();
	PrintWriter outFile;
	
	public Registrar(String filename)
	{
		Scanner readRegistrar;

		try {
			File f = new File(filename);
			if (f.exists())
			{
				readRegistrar = new Scanner(new File(filename));
				while (readRegistrar.hasNext()) {
					String registration = readRegistrar.nextLine();
					String[] parts = registration.split(",");
					Integer student = Integer.parseInt(parts[0]);
					List<String> classes = registrar.containsKey(student) ? registrar.get(student) : new ArrayList<String>();
					// Only add the course if it's not in the list yet
					if (!classes.contains(parts[1]))
					{
						classes.add(parts[1]);
						registrar.put(student, classes);
					}
				}
			}
				
			outFile = new PrintWriter(new FileWriter(filename, true));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	// Register a student for a class
	public void register(StudentID student, Course course)
	{
		Integer id = student.getID();
		// Get list of classes for the student if already registered, or make a new list
		List<String> classes = registrar.containsKey(id) ? registrar.get(id) : new ArrayList<String>();
		
		String courseID = course.getCourseID();
		
		// Only add the course if it's not in the list yet
		if (!classes.contains(courseID))
		{
			classes.add(courseID);
			registrar.put(id,  classes);
			outFile.println(id + "," + courseID);
			outFile.flush();
		}
	}
	
	public List<String> getStudentCourses(StudentID student)
	{
		return registrar.get(student.getID());
	}
}
