package application;

public class GradeData {
	private String FirstName;
	private String LastName;
	private int PointsRecieved;
	private String FeedBack;
	
	public GradeData(String FirstName, String LastName,int PointsRecieved, String FeedBack) {
		this.FirstName = FirstName;
		this.LastName = LastName;
		this.PointsRecieved = PointsRecieved;
		this.FeedBack = FeedBack;
		
	}
	
	public String getFirstName() {
		return FirstName;
	}
	
	public String getLastName() {
		return LastName;
	}
	
	public int getPointsRecieved() {
		return PointsRecieved;
	
	}
	
	public String getFeedBack() {
		return FeedBack;
	
	}
}
