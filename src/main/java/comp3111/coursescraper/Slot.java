package comp3111.coursescraper;

import java.util.Map;
import java.util.HashMap;
import java.time.LocalTime;
import java.util.Locale;
import java.time.format.DateTimeFormatter;

public class Slot {
	private static final int DEFAULT_MAX_Instructor =10;
	private int day;
	private LocalTime start;
	private LocalTime end;
	private String venue;
	//task 1.3.1----------------------------new data member for store instructors' name
	private String [] instructors;
	private int numInstructors;
	//task 1.3.1 end---------------------------
	public static final String DAYS[] = {"Mo", "Tu", "We", "Th", "Fr", "Sa"};
	public static final Map<String, Integer> DAYS_MAP = new HashMap<String, Integer>();
	static {
		for (int i = 0; i < DAYS.length; i++)
			DAYS_MAP.put(DAYS[i], i);
	}
	public Slot() {
		instructors = new String[DEFAULT_MAX_Instructor];
		for (int i = 0; i < DEFAULT_MAX_Instructor; i++) instructors[i] = null;
		numInstructors = 0;
	}
	@Override
	public Slot clone() {
		Slot s = new Slot();
		s.day = this.day;
		s.start = this.start;
		s.end = this.end;
		s.venue = this.venue;
		s.numInstructors = this.numInstructors;
		s.instructors = new String[this.numInstructors];
		for(int i = 0; i<this.numInstructors; i++) {
			s.instructors[i]=this.instructors[i];
		}
		return s;
	}  
	public String toString() {
		return DAYS[day] + start.toString() + "-" + end.toString() + ":" + venue;
	}
	public int getStartHour() {
		return start.getHour();
	}
	public int getStartMinute() {
		return start.getMinute();
	}
	public int getEndHour() {
		return end.getHour();
	}
	public int getEndMinute() {
		return end.getMinute();
	}
	/**
	 * @return the start
	 */
	public LocalTime getStart() {
		return start;
	}
	/**
	 * @param start the start to set
	 */
	public void setStart(String start) {
		this.start = LocalTime.parse(start, DateTimeFormatter.ofPattern("hh:mma", Locale.US));
	}
	/**
	 * @return the end
	 */
	public LocalTime getEnd() {
		return end;
	}
	/**
	 * @param end the end to set
	 */
	public void setEnd(String end) {
		this.end = LocalTime.parse(end, DateTimeFormatter.ofPattern("hh:mma", Locale.US));
	}
	/**
	 * @return the venue
	 */
	public String getVenue() {
		return venue;
	}
	/**
	 * @param venue the venue to set
	 */
	public void setVenue(String venue) {
		this.venue = venue;
	}

	/**
	 * @return the day
	 */
	public int getDay() {
		return day;
	}
	/**
	 * @param day the day to set
	 */
	public void setDay(int day) {
		this.day = day;
	}
	//task 1.3 ----------------------- add,set,get the number of instructors and instructors' name by index
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
	 * @param string name to add the name into instructor list
	 */
	public void addInstructor(String i) {
		if(numInstructors>=DEFAULT_MAX_Instructor)
			return;
		instructors[numInstructors++] = i;
	}
	/**
	 * @param Instructors index to get instructor's name
	 * @return name of instructor
	 */
	public String getInstructor(int i) {
		if (i >= 0 && i < numInstructors)
			return instructors[i];
		return null;
	}
	//task 1.3 end---------------------------
}
