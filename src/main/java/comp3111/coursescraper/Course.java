package comp3111.coursescraper;



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
	
	//Task 1.1------------------------------sections and instructors container and number of section/instruction in course
	private Section [] sections;
	private int numSections;
	private String [] instructors;
	private int numInstructors;
	//task1.1 end----------------------------
	

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
	
	public void addSlot(Slot s) {
		if (numSlots >= DEFAULT_MAX_SLOT)
			return;
		slots[numSlots++] = s.clone();
	}
	public Slot getSlot(int i) {
		if (i >= 0 && i < numSlots)
			return slots[i];
		return null;
	}
	
	//task 1.3 ------------------------------add and get section/instruction function
	public void addSection(Section s) {
		if (numSections >= DEFAULT_MAX_Section)
			return;
		sections[numSections++] = s.sectionClone();
	}
	public Section getSection(int i) {
		if (i >= 0 && i < numSections)
			return sections[i];
		return null;
	}
	public void addInstructor(String i) {
		if(numInstructors>=DEFAULT_MAX_Instructor)
			return;
		instructors[numInstructors++] = i;
	}
	public String getInstructor(int i) {
		if (i >= 0 && i < numInstructors)
			return instructors[i];
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
	public void setCourseCode(String CourseCode) {
		
		this.CourseCode = CourseCode.substring(0,10);			// Cut the Course Title to get eg:(Comp2011) 
	}
	
	public String getCourseCode() {
		return CourseCode;
	}
	
	public void setCourseName(String CourseName) {
		if (CourseName.substring(12,13).equals(" ")) {
			this.CourseName = CourseName.substring(13);
		}
		
		else {
			this.CourseName = CourseName.substring(12);
		}
		
	}
	
	public String getCourseName() {
		return CourseName;
	}
	
	public String getfirstSectionCode() {
		return firstSectionCode;
	}
	
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
	 * @param numInstructor the numInstructor to set
	 */
	public void setNumInstructors(int numInstructors) {
		this.numInstructors = numInstructors;
	}
	//task 1.5 end---------------------------------
	
	// Task 3
	Course(String a, String b, String c, String d) {
		this.CourseCode = a;
		this.CourseName = c;
		this.sections[0].setSectionCode(b);
		this.instructors[0] = d;
	}
	
	

}
