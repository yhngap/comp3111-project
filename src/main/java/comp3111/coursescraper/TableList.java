package comp3111.coursescraper;

public class TableList {
	private String CourseCode;
	private String CourseName;
	private String instructors;
	private String sections;
	
	TableList (String CC, String CN, String Ins, String Sec) {
		this.CourseCode = CC;
		this.CourseName = CN;
		this.instructors = Ins;
		this.sections = Sec;
	}
	
	TableList () {
		this.CourseCode = null;
		this.CourseName = null;
		this.instructors = null;
		this.sections = null;
	}
	
	public String getCourseCode() {
		return CourseCode;
	}
	
	public void setCourseCode(String S) {
		this.CourseCode = S;
	}
	
	public String getCourseName() {
		return CourseName;
	}
	
	public void setCourseName(String S) {
		this.CourseName = S;
	}
	
	public String getInstructors() {
		return instructors;
	}
	
	public void setInstructors(String S) {
		this.instructors = S;
	}
	
	public String getSections() {
		return sections;
	}
	
	public void setSections(String S) {
		this.sections = S;
	}
}
