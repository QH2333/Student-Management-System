package studhandler;

public class StudentInfo {
	public String studentNo = null;
	public String studentName = null;
	public int gender = 0;
	public String studentPwd = null;
	
	public StudentInfo(String studentNo) {
		this.studentNo = studentNo;
	}
	
	public StudentInfo(String studentNo, String studentName, int gender, String studentPwd) {
		this.studentNo = studentNo;
		this.studentName = studentName;
		this.gender = gender;
		this.studentPwd = studentPwd;
	}
}
