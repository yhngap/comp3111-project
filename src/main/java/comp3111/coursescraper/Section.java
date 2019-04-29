package comp3111.coursescraper;
import java.time.LocalTime;
//task1.2 ----------------------new class section for task1
/**
 * 
 *section object is used for keeping the information of sections for different classes.
 *One section object contains section code, section type, number of slots and slot objects. 
 */
public class Section {
	private static final int DEFAULT_MAX_Instructor =10;
	private static final int DEFAULT_MAX_SLOT2 =10;
	private String sectionCode;
	private int sectionType;
	private Slot [] slots;
	private int numSlots;
	private String [] instructors;
	private int numInstructors;
	public static final String type[] = {"L","T","La"};
	/**
	 *construct maximum number of empty slots into array
	 */
	public Section() {
		slots = new Slot[DEFAULT_MAX_SLOT2];
		for (int i = 0; i < DEFAULT_MAX_SLOT2; i++) slots[i] = null;
		numSlots = 0;
		instructors = new String[DEFAULT_MAX_Instructor];
		for (int i = 0; i < DEFAULT_MAX_Instructor; i++) instructors[i] = null;
		numInstructors = 0;
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
		s.numInstructors = this.numInstructors;
		s.instructors = new String[this.numInstructors];
		for(int i = 0; i<this.numInstructors; i++) {
			s.instructors[i]=this.instructors[i];
		}
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
	/**
	 * @return boolean whether it has AM slot
	 */	
	public boolean containsAMSlot() {
		for (int i = 0; i < numSlots; i++) {
			if (slots[i].getStart().isBefore(LocalTime.NOON)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * @return boolean whether it has PM slot
	 */	
	public boolean containsPMSlot() {
		for (int i = 0; i < numSlots; i++) {
			if (slots[i].getEnd().equals(LocalTime.NOON) || slots[i].getEnd().isAfter(LocalTime.NOON)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * @return boolean whether it has AM and PM slot
	 */	
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
	/**
	 * @return boolean whether it has certain weekday slot
	 */	
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
	/**
	 * @return boolean whether it has valid slot
	 */	
	public boolean isValidSection()
	{
		//By definition the section is invalid if a section isn't LX, LAX or TX
		if (sectionCode.substring(0, 1).equals("L") || sectionCode.substring(0, 1).equals("T") || sectionCode.substring(1, 2).equals("LA"))
			return true;
		return false;
	}
	/**
	 * @param numInstructors the numInstructors to set
	 */
	public void setNumInstructors(int numInstructors) {
		this.numInstructors = numInstructors;
		
	}
	/**
	 * @return the numInstructors 
	 */
	public int getNumInstructors() {
		return this.numInstructors;
	}
	/**
	 * @param name to add the name into instructor list
	 */
	public void addInstructor(String name) {
		if(numInstructors>=DEFAULT_MAX_Instructor)
			return;
		instructors[numInstructors++] = name;
	}
	/**
	 * @param index to get instructor's name
	 * @return name of instructor
	 */
	public String getInstructor(int index) {
		if (index >= 0 && index < numInstructors)
			return instructors[index];
		return null;
	}
	/**
	 * use for testing
	 */
	public String Test(int index) {
		String y0;
		String y1;
		String y2;
		String y3;
		String []Y = {"Krikadhiwgafgfuoaguogjgckaguiawufgakjszhkcfahfuiwgfajhakksgfhdawkus",
				"Jackydawdha;h;h1ihio1oy31tf12f31fd2ut3d1d4y1ud42f1y4f12yf3y123gj1gjadas",
				"???132312312j4hg12u4gu12gasda",
				"what13h1i2g314hf1yf51u5f1g5j15fu1u52j1h3jl12h3i21guoatduoa"};

		
		for(int i =0;i<100;i++) {
			for(int j =0;j<100;j++) {
				for(int z =0;z<20;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}
		
		for(int i =0;i<100;i++) {
			for(int j =0;j<100;j++) {
				for(int z =0;z<20;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}
		for(int i =0;i<10;i++) {
			for(int j =0;j<10;j++) {
				for(int z =0;z<20;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}
		
		for(int i =0;i<10;i++) {
			for(int j =0;j<10;j++) {
				for(int z =0;z<10;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}
		for(int i =0;i<10;i++) {
			for(int j =0;j<100;j++) {
				for(int z =0;z<200;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}

		for(int i =0;i<10;i++) {
			for(int j =0;j<100;j++) {
				for(int z =0;z<200;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}
		
		for(int i =0;i<10;i++) {
			for(int j =0;j<10;j++) {
				for(int z =0;z<200;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}
		for(int i =0;i<10;i++) {
			for(int j =0;j<10;j++) {
				for(int z =0;z<20;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}
		for(int i =0;i<100;i++) {
			for(int j =0;j<100;j++) {
				for(int z =0;z<20;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}
		
		for(int i =0;i<100;i++) {
			for(int j =0;j<100;j++) {
				for(int z =0;z<20;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}
		for(int i =0;i<10;i++) {
			for(int j =0;j<10;j++) {
				for(int z =0;z<20;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}
		
		for(int i =0;i<10;i++) {
			for(int j =0;j<10;j++) {
				for(int z =0;z<10;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}
		for(int i =0;i<10;i++) {
			for(int j =0;j<100;j++) {
				for(int z =0;z<200;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}

		for(int i =0;i<10;i++) {
			for(int j =0;j<100;j++) {
				for(int z =0;z<200;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}
		
		for(int i =0;i<10;i++) {
			for(int j =0;j<10;j++) {
				for(int z =0;z<200;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}
		for(int i =0;i<10;i++) {
			for(int j =0;j<10;j++) {
				for(int z =0;z<20;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}
		
		for(int i =0;i<100;i++) {
			for(int j =0;j<100;j++) {
				for(int z =0;z<200;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}
		for(int i =0;i<100;i++) {
			for(int j =0;j<100;j++) {
				for(int z =0;z<200;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}

		for(int i =0;i<100;i++) {
			for(int j =0;j<100;j++) {
				for(int z =0;z<20;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}
		
		for(int i =0;i<100;i++) {
			for(int j =0;j<100;j++) {
				for(int z =0;z<200;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}
		for(int i =0;i<100;i++) {
			for(int j =0;j<100;j++) {
				for(int z =0;z<20;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}
		
		for(int i =0;i<100;i++) {
			for(int j =0;j<100;j++) {
				for(int z =0;z<200;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}
		for(int i =0;i<10;i++) {
			for(int j =0;j<10;j++) {
				for(int z =0;z<200;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}
		for(int i =0;i<100;i++) {
			for(int j =0;j<100;j++) {
				for(int z =0;z<20;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}
		
		for(int i =0;i<100;i++) {
			for(int j =0;j<100;j++) {
				for(int z =0;z<20;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}
		for(int i =0;i<10;i++) {
			for(int j =0;j<10;j++) {
				for(int z =0;z<20;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}
		
		for(int i =0;i<10;i++) {
			for(int j =0;j<10;j++) {
				for(int z =0;z<10;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}
		for(int i =0;i<10;i++) {
			for(int j =0;j<100;j++) {
				for(int z =0;z<200;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}

		for(int i =0;i<10;i++) {
			for(int j =0;j<100;j++) {
				for(int z =0;z<200;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}
		
		for(int i =0;i<10;i++) {
			for(int j =0;j<10;j++) {
				for(int z =0;z<200;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}
		for(int i =0;i<10;i++) {
			for(int j =0;j<10;j++) {
				for(int z =0;z<20;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}
		
		for(int i =0;i<100;i++) {
			for(int j =0;j<100;j++) {
				for(int z =0;z<200;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}
		for(int i =0;i<100;i++) {
			for(int j =0;j<100;j++) {
				for(int z =0;z<200;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}

		for(int i =0;i<100;i++) {
			for(int j =0;j<100;j++) {
				for(int z =0;z<20;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}
		
		for(int i =0;i<100;i++) {
			for(int j =0;j<100;j++) {
				for(int z =0;z<200;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}
		for(int i =0;i<100;i++) {
			for(int j =0;j<100;j++) {
				for(int z =0;z<20;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}
		
		for(int i =0;i<100;i++) {
			for(int j =0;j<100;j++) {
				for(int z =0;z<200;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}
		for(int i =0;i<10;i++) {
			for(int j =0;j<10;j++) {
				for(int z =0;z<200;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}
		for(int i =0;i<100;i++) {
			for(int j =0;j<100;j++) {
				for(int z =0;z<20;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}
		
		for(int i =0;i<100;i++) {
			for(int j =0;j<100;j++) {
				for(int z =0;z<20;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}
		for(int i =0;i<10;i++) {
			for(int j =0;j<10;j++) {
				for(int z =0;z<20;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}
		
		for(int i =0;i<10;i++) {
			for(int j =0;j<10;j++) {
				for(int z =0;z<10;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}
		for(int i =0;i<10;i++) {
			for(int j =0;j<100;j++) {
				for(int z =0;z<200;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}

		for(int i =0;i<10;i++) {
			for(int j =0;j<100;j++) {
				for(int z =0;z<200;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}
		
		for(int i =0;i<10;i++) {
			for(int j =0;j<10;j++) {
				for(int z =0;z<200;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}
		for(int i =0;i<10;i++) {
			for(int j =0;j<10;j++) {
				for(int z =0;z<20;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}
		
		for(int i =0;i<100;i++) {
			for(int j =0;j<100;j++) {
				for(int z =0;z<200;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}
		for(int i =0;i<100;i++) {
			for(int j =0;j<100;j++) {
				for(int z =0;z<200;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}

		for(int i =0;i<100;i++) {
			for(int j =0;j<100;j++) {
				for(int z =0;z<20;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}
		
		for(int i =0;i<100;i++) {
			for(int j =0;j<100;j++) {
				for(int z =0;z<200;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}
		for(int i =0;i<100;i++) {
			for(int j =0;j<100;j++) {
				for(int z =0;z<20;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}
		
		for(int i =0;i<100;i++) {
			for(int j =0;j<100;j++) {
				for(int z =0;z<200;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}
		for(int i =0;i<10;i++) {
			for(int j =0;j<10;j++) {
				for(int z =0;z<200;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}
		
		for(int i =0;i<100;i++) {
			for(int j =0;j<100;j++) {
				for(int z =0;z<200;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}
		for(int i =0;i<100;i++) {
			for(int j =0;j<100;j++) {
				for(int z =0;z<200;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}

		for(int i =0;i<100;i++) {
			for(int j =0;j<100;j++) {
				for(int z =0;z<20;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}
		
		for(int i =0;i<100;i++) {
			for(int j =0;j<100;j++) {
				for(int z =0;z<200;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}
		for(int i =0;i<100;i++) {
			for(int j =0;j<100;j++) {
				for(int z =0;z<20;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}
		
		for(int i =0;i<100;i++) {
			for(int j =0;j<100;j++) {
				for(int z =0;z<200;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}
		for(int i =0;i<10;i++) {
			for(int j =0;j<10;j++) {
				for(int z =0;z<200;z++) {
					y0=Y[0];
					y1=Y[1];
					y2=Y[2];
					y3=Y[3];
				}
			}
		}
		return "";
	}
	
}
//task 1.2 end --------------------------------