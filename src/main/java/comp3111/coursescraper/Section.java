package comp3111.coursescraper;


//task1//
public class Section {
	private static final int DEFAULT_MAX_SLOT2 =10;
	private String sectionCode;
	private int sectionType;
	private Slot [] slots;
	private int numSlots;
	public static final String type[] = {"L","T","La"};
	public Section() {
		slots = new Slot[DEFAULT_MAX_SLOT2];
		for (int i = 0; i < DEFAULT_MAX_SLOT2; i++) slots[i] = null;
		numSlots = 0;
	}
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
	
	public void addSlot(Slot s) {
		if (numSlots >= DEFAULT_MAX_SLOT2)
			return;
		slots[numSlots++] = s.clone();
	}
	public Slot getSlot(int i) {
		if (i >= 0 && i < numSlots)
			return slots[i];
		return null;
	}
	public String getSectionCode() {
		return sectionCode;
	}
	public void setSectionCode(String s) {
		this.sectionCode = s;
	}
	public int getSectionType() {
		return sectionType;
	}
	
	public void setsectionType(int sectionType) {
		this.sectionType = sectionType;
	}
	public int getNumSlots() {
		return numSlots;
	}
	
	public void setNumSlots(int numSlots) {
		this.numSlots = numSlots;
	}
}
//end