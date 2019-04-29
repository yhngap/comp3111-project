package comp3111.coursescraper;
import javafx.scene.control.CheckBox;

/**
 * TableList object is used for keeping the enrollment of course which is scraped from filter .
 * One TableList object contains Course's Code, Course's Name, name of instructors, which sections, enroll status, 
 * enrolled status, array of slots, number of Slots, course's SFQ and course's SFQ Score 
 * @author zzhan
 */
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
	
	/**
	 * Construct the setting value of new class of TableList type
	 * @param CC
	 * @param CN
	 * @param Ins
	 * @param Sec
	 */	
	TableList (String CC, String CN, String Ins, String Sec) {
		this.CourseCode = CC;
		this.CourseName = CN;
		this.instructors = Ins;
		this.sections = Sec;
		this.enroll = new CheckBox();
		this.enrolled = false;
		this.courseSFQ = false;
	}
	
	/**
	 * Default constructor of TableList type
	 */
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
	}	
	
	/**
	 * Get the numSlots
	 *@return the numSlots
	 */
	public int getNumSlots() {
		return numSlots;
	}
	/**
	 * the numSlots to set
	 *@param numSlots  
	 */
	public void setNumSlots(int numSlots) {
		this.numSlots = numSlots;
	}
	/**
	 * ChckBox type, to get the enroll status of Checkbox
	 * @return enroll
	 */
	public CheckBox getEnroll() {
		return enroll;
	}
	/**
	 * set the enroll element by inputting S
	 * @param S
	 */
	
	public void setEnroll(CheckBox S) {
		this.enroll = S;
	}
	/**
	 * Get the CourseCode 
	 * @return CourseCode
	 */
	public String getCourseCode() {
		return CourseCode;
	}
	/**
	 * Set the CourseCode by inputting S
	 * @param S
	 */
	public void setCourseCode(String S) {
		this.CourseCode = S;
	}
	/**
	 * Get the name of Course
	 * @return CourseName
	 */
	public String getCourseName() {
		return CourseName;
	}
	/**
	 * set the course name by S
	 * @param S
	 */
	public void setCourseName(String S) {
		this.CourseName = S;
	}
	/**
	 * Get the instructors
	 * @return instructors
	 */
	public String getInstructors() {
		return instructors;
	}
	/**
	 * set the name of Instructors to S
	 * @param S
	 */
	public void setInstructors(String S) {
		this.instructors = S;
	}
	/**
	 * Get the sections
	 * @return sections
	 */
	public String getSections() {
		return sections;
	}
	/**
	 * set the sections to S
	 * @param S
	 */
	public void setSections(String S) {
		this.sections = S;
	}
	/**
	 * set Enrolled to boolean type a
	 * @param a
	 */
	public void setEnrolled(boolean a) {
		this.enrolled = a;
	}
	/**
	 * get the enrolled 
	 * @return enrolled
	 */
	public boolean getEnrolled() {
		return this.enrolled;
	}
	/**
	 * Set the courseSFQ status to a
	 * @param a
	 */
	public void setcourseSFQ(boolean a) {
		this.courseSFQ = a;
	}
	/**
	 * get the status of courseSFQ
	 * @return courseSFQ
	 */
	public boolean getcourseSFQ() {
		return this.courseSFQ;
	}
	/**
	 * Set the courseSFQScore
	 * @param S
	 */
	public void setcourseSFQScore (double S) {
		this.courseSFQScore = S;
	}
	/**
	 * Get the score of courseSFQ
	 * @return courseSFQScore
	 */
	public double getcourseSFQScore() {
		return courseSFQScore;
	}
		
	
}
