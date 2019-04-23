package comp3111.coursescraper;
import javafx.scene.control.CheckBox;
public class TableList {
	private String CourseCode;
	private String CourseName;
	private String instructors;
	private String sections;
	private CheckBox enroll;
	private boolean enrolled;
	
	
	TableList (String CC, String CN, String Ins, String Sec) {
		this.CourseCode = CC;
		this.CourseName = CN;
		this.instructors = Ins;
		this.sections = Sec;
		this.enroll = new CheckBox();
		this.enrolled = false;
	}
	
	TableList () {
		this.CourseCode = null;
		this.CourseName = null;
		this.instructors = null;
		this.sections = null;
	}
	
	public CheckBox getEnroll() {
		return enroll;
	}
	
	public void setEnroll(CheckBox S) {
		this.enroll = S;
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
// Enroll List	
	public void setEnrolled(boolean a) {
		this.enrolled = a;
	}
	
	public boolean getEnrolled() {
		return this.enrolled;
	}
	
	
}
