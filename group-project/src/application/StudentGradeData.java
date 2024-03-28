package application;

public class StudentGradeData {
	private String Ass;
	private String Weight;
	private char Grade;
	private int Percent;
	private String Feedback;
	
	public StudentGradeData(String Ass, String Weight, char Grade, int Percent, String Feedback) {
		this.Ass = Ass;
		this.Weight = Weight;
		this.Grade = Grade;
		this.Percent = Percent;
		this.Feedback = Feedback;
		
	}
	public String getAss() {
		return Ass;
	}
	public String getWeight() {
		return Weight;
	}
	public char getGrade() {
		return Grade;
	}
	public int getPercent() {
		return Percent;
	}
	public String getFeedback() {
		return Feedback;
	}
	
}
