package comp3111.coursescraper;
import java.time.LocalTime;
//task1.2 ----------------------new class section for task1
/**
 * 
 *section object is used for keeping the information of sections for different classes.
 *One section object contains section code, section type, number of slots and slot objects. 
 */
public class Section {
	private static final int DEFAULT_MAX_SLOT2 =10;
	private String sectionCode;
	private int sectionType;
	private Slot [] slots;
	private int numSlots;
	public static final String type[] = {"L","T","La"};
	/**
	 *construct maximum number of empty slots into array
	 */
	public Section() {
		slots = new Slot[DEFAULT_MAX_SLOT2];
		for (int i = 0; i < DEFAULT_MAX_SLOT2; i++) slots[i] = null;
		numSlots = 0;
	}
	/**
	 * @return s return the other the same section object
	 */
	public Section sectionClone() {
		Section s = new Section();
		for(int i=0;i<numSlots;i++) {
			s.slots[i]=this.slots[i].clone();
		}
		s.sectionCode = this.sectionCode;
		s.sectionType = this.sectionType;
		s.numSlots = this.numSlots;
		return s;
	}  
	/**
	 * @param s  add new slot s into section
	 */
	public void addSlot(Slot s) {
		if (numSlots >= DEFAULT_MAX_SLOT2)
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
	 *@return the sectionCode
	 */
	public String getSectionCode() {
		return sectionCode;
	}
	/**
	 * @param s  set the sectionCode
	 */
	public void setSectionCode(String s) {
		this.sectionCode = s;
	}
	/**
	 *@return the sectionType
	 */
	public int getSectionType() {
		return sectionType;
	}
	/**
	 * @param sectionType  the sectionType to set
	 */
	public void setsectionType(int sectionType) {
		this.sectionType = sectionType;
	}
	/**
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
	
	/* Helper functions for filter (Task 2) */
	
	public boolean containsAMSlot() {
		for (int i = 0; i < numSlots; i++) {
			if (slots[i].getStart().isBefore(LocalTime.NOON)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean containsPMSlot() {
		for (int i = 0; i < numSlots; i++) {
			if (slots[i].getEnd().equals(LocalTime.NOON) || slots[i].getEnd().isAfter(LocalTime.NOON)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean containsAMPMSlot() {
		for (int i = 0; i < numSlots; i++) {
			if (slots[i].getStart().isBefore(LocalTime.NOON) && (slots[i].getEnd().equals(LocalTime.NOON) || slots[i].getEnd().isAfter(LocalTime.NOON))) {
				return true;
			}
		}
		
		if (containsAMSlot() && containsPMSlot()) {
			return true;
		}
		else return false;
	}
	
	public boolean[] containsDaySlot() {
		boolean[] bContainDaySlot = new boolean[6];
		for (int i = 0; i < numSlots; i++) {
			for (int j = 0; j < 6; j++) {
				if (slots[i].getDay() == j) {
					bContainDaySlot[j] = true;
				}
			}
		}
		return bContainDaySlot;
	}
	
	// task 5
	public boolean isValidSection()
	{
		//By definition the section is invalid if a section isn't LX, LAX or TX
		if (sectionCode.substring(0, 1).equals("L") || sectionCode.substring(0, 1).equals("T") || sectionCode.substring(1, 2).equals("LA"))
			return true;
		return false;
	}
}
//task 1.2 end --------------------------------