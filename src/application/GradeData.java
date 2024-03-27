package application;

public class GradeData {
	private int PointsRecieved;
	private String FeedBack;
	
	public GradeData(int PointsRecieved, String FeedBack) {
		this.PointsRecieved = PointsRecieved;
		this.FeedBack = FeedBack;
		
	}
	
	public int getPointsRecieved() {
		return PointsRecieved;
	
	}
	
	public String getFeedBack() {
		return FeedBack;
	
	}
}