package comp3111.coursescraper;
import javafx.scene.control.CheckBox;

public class TableList {
	private static final int DEFAULT_MAX_SLOT =10;
	private String CourseCode;
	private String CourseName;
	private String instructors;
	private String sections;
	private CheckBox enroll;
	private boolean enrolled;
	private Slot [] slots;
	private int numSlots;
	private boolean courseSFQ;
	private double courseSFQScore;
	
	TableList (String CC, String CN, String Ins, String Sec) {
		this.CourseCode = CC;
		this.CourseName = CN;
		this.instructors = Ins;
		this.sections = Sec;
		this.enroll = new CheckBox();
		this.enrolled = false;
		this.courseSFQ = false;
	}
	TableList () {
		this.CourseCode = null;
		this.CourseName = null;
		this.instructors = null;
		this.sections = " ";
		slots = new Slot[DEFAULT_MAX_SLOT];
		for (int i = 0; i < DEFAULT_MAX_SLOT; i++) slots[i] = null;
		numSlots = 0;
	}
	/**
	 * @param s  add new slot s into section
	 */
	public void addSlot(Slot s) {
		if (numSlots >= DEFAULT_MAX_SLOT)
			return;
		slots[numSlots++] = s.clone();
	}
	/**
	 * @param i  get corresponding slot by index i
	 * @return slot or null
	 */
	public Slot getSlot(int i) {
		if (i >= 0 && i < numSlots)
			return slots[i];
		return null;
	}	/**
	 *@return the numSlots
	 */
	public int getNumSlots() {
		return numSlots;
	}
	/**
	 *@param numSlots  the numSlots to set
	 */
	public void setNumSlots(int numSlots) {
		this.numSlots = numSlots;
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
// Check SFQ List
	public void setcourseSFQ(boolean a) {
		this.courseSFQ = a;
	}
	
	public boolean getcourseSFQ() {
		return this.courseSFQ;
	}
// Get SFQ Score
	public void setcourseSFQScore (double S) {
		this.courseSFQScore = S;
	}
	
	public double getcourseSFQScore() {
		return courseSFQScore;
	}
		
	
}
