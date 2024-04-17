package application;

public class GraphGradeData {

	private int StudentID;
	private int AssID;
	private int PointsRecieved;
	
	private int userId = Controller.userId;
	private boolean isYours = false;
	
	private boolean isValid = true;
	
	
	public GraphGradeData(int StudentID, int AssID, int PointsRecieved) {
		this.StudentID = StudentID;
		this.AssID = AssID;
		this.PointsRecieved = PointsRecieved;
		
		if (StudentID == userId) {
			isYours = true;
		}
	}
	
	public int getStudentID() {
		return StudentID;
	}
	
	public int getAssID() {
		return AssID;
	}
	
	public int getPointsRecieved() {
		return PointsRecieved;
	}
	
	public boolean isItYours() {
		return isYours;
	}
	
	public boolean isItValid() {
		return isValid;
	}
	
	public void makeValid() {
		isValid = true;
	}
	
	public void makeInvalid() {
		isValid = false;
	}
	
}
