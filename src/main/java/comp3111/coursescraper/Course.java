package comp3111.coursescraper;

/**
 *Course object is used for keeping the information of course which is scraped from website.
 * One course object contains the title, description, exclusion. course code, course name, 
 * the number of slots, the number of instructors, the number of sections, 
 * the instructor's name, section objects and slot objects.
 */
public class Course {
	private static final int DEFAULT_MAX_SLOT = 50;
	
	//Task 1
	private static final int DEFAULT_MAX_Section =50;
	private static final int DEFAULT_MAX_Instructor =50;
	//end
	
	private String title ; 
	private String description ;
	private String exclusion;
	private Slot [] slots;
	private int numSlots;
	// Task 3
	private String CourseCode;
	private String CourseName;
	private String firstSectionCode;
	private String attribute;
	
	//Task 1.1------------------------------sections and instructors container and number of section/instruction in course
	private Section [] sections;
	private int numSections;
	private String [] instructors;
	private int numInstructors;
	//task1.1 end----------------------------
	/**
	 *construct maximum number of empty sections, instructors and slots into corresponding array
	 */
	public Course() {
		slots = new Slot[DEFAULT_MAX_SLOT];
		for (int i = 0; i < DEFAULT_MAX_SLOT; i++) slots[i] = null;
		numSlots = 0;
		
		//Task 1.2----------------------------section and instructor container initialization
		sections = new Section[DEFAULT_MAX_Section];
		for (int i = 0; i < DEFAULT_MAX_Section; i++) sections[i] = null;
		numSections = 0;
		instructors = new String[DEFAULT_MAX_Instructor];
		for (int i = 0; i < DEFAULT_MAX_Instructor; i++) instructors[i] = null;
		numInstructors = 0;
		//task 1.2 end-------------------------- 
		
	}
	/**
	 * @param s  add new slot s into course
	 */
	public void addSlot(Slot s) {
		if (numSlots >= DEFAULT_MAX_SLOT)
			return;
		slots[numSlots++] = s.clone();
	}
	/**
	 * @param index to get the slot
	 * @return the Slot
	 */
	public Slot getSlot(int index) {
		if (index >= 0 && index < numSlots)
			return slots[index];
		return null;
	}
	
	//task 1.3 ------------------------------add and get section/instruction function
	/**
	 * @param s add new section s into course
	 */
	public void addSection(Section s) {
		if (numSections >= DEFAULT_MAX_Section)
			return;
		sections[numSections++] = s.sectionClone();
	}
	/**
	 * @param index to get the section
	 * @return the Slot
	 */
	public Section getSection(int index) {
		if (index >= 0 && index < numSections)
			return sections[index];
		return null;
	}
	/**
	 * @param name add new instructor's name into course
	 */
	public void addInstructor(String name) {
		if(numInstructors>=DEFAULT_MAX_Instructor)
			return;
		instructors[numInstructors++] = name;
	}
	/**
	 * @param index to get the instructor's name
	 * @return the Slot
	 */
	public String getInstructor(int index) {
		if (index >= 0 && index < numInstructors)
			return instructors[index];
		return null;
	}
	//task 1.3 end -------------------------------
	
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	// Task 3
	/**
	 * @param CourseCode the CourseCode to set
	 */
	public void setCourseCode(String CourseCode) {
		
		this.CourseCode = CourseCode.substring(0,10);			// Cut the Course Title to get eg:(Comp2011) 
	}
	/**
	 * @return the getCourseCode
	 */
	public String getCourseCode() {
		return CourseCode;
	}
	
	/**
	 * @param CourseName the CourseName to set
	 */
	public void setCourseName(String CourseName) {
		if (CourseName.substring(12,13).equals(" ")) {
			this.CourseName = CourseName.substring(13);
		}
		
		else {
			this.CourseName = CourseName.substring(12);
		}
		
	}
	
	/**
	 * @return the CourseName
	 */
	public String getCourseName() {
		return CourseName;
	}
	/**
	 * @return the getfirstSectionCode
	 */
	public String getfirstSectionCode() {
		return firstSectionCode;
	}
	
	/**
	 * @param sectionCode the sectionCode to set
	 */
	public void setfirstSectionCode (String sectionCode) {
		this.firstSectionCode = sectionCode;
	}
	

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	
	/**
	 * @return the exclusion
	 */
	public String getExclusion() {
		return exclusion;
	}

	/**
	 * @param exclusion the exclusion to set
	 */
	public void setExclusion(String exclusion) {
		this.exclusion = exclusion;
	}

	/**
	 * @return the numSlots
	 */
	public int getNumSlots() {
		return numSlots;
	}
	/**
	 * @return the numSections
	 */
	
	//task 1.4 --------------------------- get number of section and instructors
	public int getNumSection() {
		return numSections;
	}
	/**
	 * @return the numInstructors
	 */
	public int getNumIstructor() {
		return numInstructors;
	}
	//task 1.4 end ------------------------------
	
	/**
	 * @param numSlots the numSlots to set
	 */
	public void setNumSlots(int numSlots) {
		this.numSlots = numSlots;
	}
	/**
	 * @param numSections the numSections to set
	 */
	
	//task 1.5------------------------------------ set number of sections and instructors
	public void setNumSections(int numSections) {
		this.numSections = numSections;
	}
	
	/**
	 * @param numInstructors the numInstructors to set
	 */
	public void setNumInstructors(int numInstructors) {
		this.numInstructors = numInstructors;
	}
	//task 1.5 end---------------------------------
	

	/* Helper functions for filter (Task 2) */
	
	/**
	 * Returns the course attribute.
	 * @return the attribute
	 */
	public String getAttribute() {
		return attribute;
	}
	
	/**
	 * Sets the course attribute.
	 * @param attribute the attribute to set
	 */
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	
	
	public boolean isCC4Y() {
		if (attribute.contains("Common Core") && attribute.contains("4Y")) {
			return true;
		}
		return false;
	}
	
	public boolean isNoEx() {
		if (exclusion.contains("null")) {
			return true;
		}
		return false;
	}
	
	public boolean containsAMPMSection() {
		for (int i = 0; i < numSections; i++) {
			if (sections[i].containsAMPMSlot())
				return true;
		}
		return false;
	}
	
	public boolean containsAMSection() {
		for (int i = 0; i < numSections; i++) {
			if (sections[i].containsAMSlot())
				return true;
		}
		return false;
	}
	
	public boolean containsPMSection() { 
		for (int i = 0; i < numSections; i++) {
			if (sections[i].containsPMSlot())
				return true;
		}
		return false;
	}
	
	public boolean[] containsDaySection() {
		boolean[] bContainsDaySection = new boolean[6];
		for (int i = 0; i < numSections; i++) {
			for (int k = 0; k < 6; k++) {
				bContainsDaySection[k] |= sections[i].containsDaySlot()[k];
			}
		}
		return bContainsDaySection;
	}
	
	public boolean containsLabOrTut() {
		for (int i = 0; i < numSections; i++) {
			if (sections[i].getSectionCode().contains("LA") || sections[i].getSectionCode().contains("T"))
				return true;
		}
		return false;
	}
	
	// task 5
	public boolean isValidCourse() { return (getNumValidSections() > 0? true: false); }
	
	public int getNumValidSections()
	{
		int validCount = numSections;	//Using decrement strategy
		for (int i = 0; i < numSections; i++)
		{
			if (!sections[i].isValidSection())
				validCount--;
		}
		return validCount;
	}
}
