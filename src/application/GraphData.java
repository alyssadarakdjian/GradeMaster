package application;

public class GraphData {

	private String Ass;
	private int AssID;
	private int PointsPoss;
	
	private boolean isValid = true;
	
	
	public GraphData(String Ass, int AssID, int PointsPoss) {
		this.Ass = Ass;
		this.AssID = AssID;
		this.PointsPoss = PointsPoss;
	}
	
	public String getAss() {
		return Ass;
	}
	
	public int getAssID() {
		return AssID;
	}
	
	public int getPointsPoss() {
		return PointsPoss;
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
