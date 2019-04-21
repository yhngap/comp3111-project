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
	
	//Task 1
	private Section [] sections;
	private int numSections;
	private String [] instructors;
	private int numInstructors;
	//end
	
	public Course() {
		slots = new Slot[DEFAULT_MAX_SLOT];
		for (int i = 0; i < DEFAULT_MAX_SLOT; i++) slots[i] = null;
		numSlots = 0;
		
		//Task 1//
		sections = new Section[DEFAULT_MAX_Section];
		for (int i = 0; i < DEFAULT_MAX_Section; i++) sections[i] = null;
		numSections = 0;
		instructors = new String[DEFAULT_MAX_Instructor];
		for (int i = 0; i < DEFAULT_MAX_Instructor; i++) instructors[i] = null;
		numInstructors = 0;
		//end
		
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
	
	//task1//
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
	//end
	
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
	
	//task1//
	public int getNumSection() {
		return numSections;
	}
	/**
	 * @return the numInstructors
	 */
	public int getNumIstructor() {
		return numInstructors;
	}
	//end
	
	/**
	 * @param numSlots the numSlots to set
	 */
	public void setNumSlots(int numSlots) {
		this.numSlots = numSlots;
	}
	/**
	 * @param numSections the numSections to set
	 */
	
	//task1//
	public void setNumSections(int numSections) {
		this.numSections = numSections;
	}
	
	/**
	 * @param numInstructor the numInstructor to set
	 */
	public void setNumInstructors(int numInstructors) {
		this.numInstructors = numInstructors;
	}
	//end
	
	

}
