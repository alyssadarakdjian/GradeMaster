package application;

public class ClassData {
	
	private String CourseName;
	private int CourseNum;
	
	public ClassData(String CourseName, int CourseNum) {
		this.CourseName = CourseName;
		this.CourseNum = CourseNum;
	}
	
	public String getCourseName() {
		return CourseName;
	}
	
	public int getCourseNum() {
		return CourseNum;
	}
}