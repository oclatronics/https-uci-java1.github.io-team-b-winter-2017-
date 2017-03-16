import java.util.*;

public class MainMenu {

	public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner (System.in);

        final String LOGIN   = "[1] Login";
		final String LOGOUT   = "[1] Logout";	
		final String REGISTER_LOCKED   = "[2] Register-Locked";
		final String REGISTER   = "[2] Register";
		final String PROFILE_LOCKED   = "[3] Profile-Locked";
		final String PROFILE   = "[3] Profile";
		
        CourseToReg course = new CourseToReg("Courses1.csv");
	    Registrar registrar = new Registrar("Registrar.csv");
        StudentID student=new StudentID();
               
        while(true){  //Main Menu
            System.out.println("Please Make a selection:"); 
            if (student.isLoggedIn()){
            	System.out.println(LOGOUT); 
            	System.out.println(REGISTER); 
            	System.out.println(PROFILE); 
            }
            else{
            	System.out.println(LOGIN); 
            	System.out.println(REGISTER_LOCKED); 
            	System.out.println(PROFILE_LOCKED);            	
            }          	
            System.out.println("[4] Exit"); 
            System.out.println("Selection: "); 
            int selection=scanner.nextInt();   
            
            switch (selection){
       		case 1:System.out.println("******** Log in/out ********");
       			if (!student.isLoggedIn()){
	       			Login(student);
	           		if (student.isLoggedIn()){
	           			System.out.println("");
	           			System.out.println("Logged in as " + student.getFirstName() +
	           				" " + student.getLastName() + ", ID: " + student.getID() + "\n");
                                    WriteToStudent.addStudent(student);
	           		}
	           		else
	           			System.out.println("Not logged in");
       			}
           		break;
                
           case 2:
        	   	System.out.println("******** Register Screen ********");
                if (student.isLoggedIn()){
                	try{
                  	   for(Course cs: course.getCourses())
                 	   {
                  		   System.out.println(cs.getCourseID() + ": " + cs.getCourseName());
                 	   }
                		System.out.println("\nSelect your course: ");
                		String selectedCourse = scanner.next();
                		Course c = course.findCourse(selectedCourse);
                		
                		if (c == null)
                		{
                			System.out.println("Unknown course, here is the list of courses");
                     	   for(Course cs: course.getCourses())
                    	   {
                     		   System.out.println(cs.getCourseID() + ": " + cs.getCourseName());
                    	   }
               			
                		}
                		else
                		{
                			registrar.register(student, c);
                			System.out.println("Successfully registered");
                		}
                		//System.out.println(course.getCourseList());
                	}
                	catch (Exception ex){
                		System.out.println("Failure in registration system \n"); 
                	}
                }
               	break;
                     
           case 3:
        	   System.out.println("******** View Profile Screen ********");
               if (student.isLoggedIn()){
            	   List<String> classes = registrar.getStudentCourses(student);
            	   System.out.println("You are registered for the following courses:");
            	   
            	   for(String cl: classes)
            	   {
            		   Course c = course.findCourse(cl);
            		   System.out.println(c.getCourseID() + ": " + c.getCourseName());
            	   }
               }
           	   break;

           case 4:
        	    System.out.println("Exit Successful");
        	    scanner.close();
                System.exit(0);
                        
           default:
        	   System.out.println("Please enter a valid selection.");         
            }          
        }
	}
	
	public static void Login(StudentID student){
		java.io.File file = new java.io.File("studentID.csv");
		//may need to update Eclipse run configuration-> Arguments -> working directory		
		if (file.exists()){
			Scanner log = new Scanner (System.in);
			int studentID=0;
			
			System.out.print("Please enter First Name: "); 
			String first = log.nextLine().trim();
			
			System.out.print("Please enter Last Name: "); 
			String last = log.nextLine().trim();

			while(true){
				try{ //get student ID
					
	    			System.out.print("Please enter Student ID (5 digits, 10000 to 99999): "); 
		    		studentID = log.nextInt();
		    		
		    		if ((studentID > 9999) && (studentID <= 99999)){
		    			try{
			    			java.io.FileReader fr = new java.io.FileReader(file);
			    			java.io.BufferedReader br = new java.io.BufferedReader(fr);
			    			String line;
				    		String[] studentLogIn;
				    		line = br.readLine(); //read header line
			    			line = br.readLine(); //read second line			    		
				    		while(line != null){
				    			studentLogIn = line.split(",");	
				    			if(first.trim().toLowerCase().equals(studentLogIn[0].trim().toLowerCase()) &&
				    				last.trim().toLowerCase().equals(studentLogIn[1].trim().toLowerCase()) &&
				    				(studentID == Integer.parseInt(studentLogIn[2]))){
					    			student.setID(studentID);
					    			student.setFirstName(first);
					    			student.setLastName(last);
				    			}
				    			line = br.readLine();
				    		}
			    			
			    			fr.close();
			    			br.close();
			    			break;	
		    			}
		    			catch(java.io.IOException e){
		    				System.out.println("File read error");	
		    			}
		    		}
		    		else if (studentID == 0){
		    			System.out.println("Log in aborted \n");
		    			break;
		    		}
		    		else{
		    			System.out.println("\n" + "Student ID must be six digits");
		    			System.out.println("Enter 0 to abort log in \n");
		    			studentID=0;  			
		    		}
				}
				catch (InputMismatchException e){
					System.out.println( "\nStudentID must be numeric. Log in aborted. \n");
					break;
				}
			}
		}
		else{
			System.out.println("Cannot find " + file.getAbsolutePath());
		}		
	}

}

