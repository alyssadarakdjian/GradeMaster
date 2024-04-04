package application;

public class StudentGradeData {
	private String Ass;
	private int PointsPoss;
	private int PointsRec;
	private String FeedBack;
	
	public StudentGradeData(String Ass, int PointsPoss, int PointsRec, String FeedBack) {
		this.Ass = Ass;
		this.PointsPoss = PointsPoss;
		this.PointsRec = PointsRec;
		this.FeedBack = FeedBack;
		
	}
	public String getAss() {
		return Ass;
	}
		public int getPointsPoss() {
		return PointsPoss;
	}
		
		public int getPointsRec() {
			return PointsRec;
	}

	public String getFeedback() {
		return FeedBack;
	}
	
}