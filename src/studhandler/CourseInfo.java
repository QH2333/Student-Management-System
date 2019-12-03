package studhandler;

public class CourseInfo {
	public String courseNo = null;
	public String courseName = null;
	public int courseCredit = 0;
	public float courseScore = 0;
	public boolean isFilled = false;
	
	public CourseInfo(String courseNo, String courseName, int courseCredit) {
		this.courseNo = courseNo;
		this.courseName = courseName;
		this.courseCredit = courseCredit;
	}
	
	public void setScore(float s) {
		courseScore = s;
		isFilled = true;
	}
}
