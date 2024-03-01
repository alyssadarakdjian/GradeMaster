package application;

public class GradeData {
	private String Ass;
	private String Student;
	private int Grade;
	
	public GradeData(String Ass, String Student, int Grade) {
		this.Ass = Ass;
		this.Student = Student;
		this.Grade = Grade;
	}
	
	public String getAss() {
		return Ass;
	}
	public String getStudent() {
		return Student;
	}
	public int getGrade() {
		return Grade;
	}
}