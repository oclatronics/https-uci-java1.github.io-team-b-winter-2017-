import java.io.*;


public class WriteToStudent {

	public static void addStudent(StudentID student) throws Exception{
	
        PrintWriter outFile;
        
        try { 
            outFile = new PrintWriter(new FileWriter("addstudent.csv", true)); 
            outFile.println(student.getID() + "," + student.getFirstName() + "," + student.getLastName());
            outFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }  catch (IOException e) {
            e.printStackTrace();
        }     
    }

}


