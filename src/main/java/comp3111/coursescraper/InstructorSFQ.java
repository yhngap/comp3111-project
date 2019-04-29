package comp3111.coursescraper;

/**
 * InstructorSFQ object is used for keeping the SFQ information of all instructors' SFQ score which is scraped from website.
 * One InstructorSFQ object contains the score, name, exist time, and the status of exist 
 * @author zzhan
 */
public class InstructorSFQ {
	private double score;
	private String name;
	private int times;
	private boolean exist;

/**
 * Default constructor of InstructorSFQ, use to initial the elements of InstructorSFQ
 */
public InstructorSFQ () {
	this.score = 0;
	this.name = "";
	this.times = 0;
	this.exist = false;
}

/**
 * set the score to s
 * @param s
 */
public void setscore(double s) {
	this.score = s;
}

/**
 * get the score
 * @return score
 */
public double getscore() {
	return score;
}
/**
 * set the name to s
 * @param s
 */
public void setname(String s) {
	this.name = s;
}
/**
 * get the name
 * @return name
 */
public String getname() {
	return name;
}
/**
 * set times to s
 * @param s
 */
public void settimes(int s) {
	this.score = s;
}
/**
 * get the times
 * @return times
 */
public double gettimes() {
	return times;
}
/**
 * set the status of exist to s
 * @param s
 */
public void setexist(boolean s) {
	this.exist = s;
}
/**
 * get the status of exist
 * @return exist
 */
public boolean getexist() {
	return exist;
}
/**
 * add the score 
 * @param score
 */
public void addscore(double score) {
	this.score += score;
}
/**
 * add times
 * @param i
 */
public void addtimes(int i) {
	this.times += i;
}


}