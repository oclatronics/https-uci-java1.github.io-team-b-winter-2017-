//Student ID Class
public class StudentID {
	private String firstName = "";
	private String lastName = "";
	private int ID = 0;
	private boolean loggedIn = false;
			
	StudentID(){
		
	}
	
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public int getID() {
		return ID;
	}

	public boolean isLoggedIn() {
		if (ID !=0)
			loggedIn=true;
		else
			loggedIn=false;
		return loggedIn;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setID(int iD) {
		ID = iD;
		if (ID !=0)
			loggedIn=true;
		else
			loggedIn=false;
	}

}
