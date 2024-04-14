package application;

public class StudentData {
	
	public class ClassData {
		
		private String FirstName;
		private String LastName;
		private int Student_ID;
		
		public ClassData(String FirstName, String LastName, int Student_ID) {
			this.FirstName = FirstName;
			this.FirstName = LastName;
			this.Student_ID = Student_ID;
		}
		
		public String getFirstName() {
			return FirstName;
		}
		public String getLastName() {
			return LastName;
		}
		
		public int getStudent_ID() {
			return Student_ID;
		}
	}

	
	
}
