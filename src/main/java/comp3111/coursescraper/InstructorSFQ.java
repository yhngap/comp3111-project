package comp3111.coursescraper;

public class InstructorSFQ {
	private double score;
	private String name;
	private int times;
	private boolean exist;


public InstructorSFQ () {
	this.score = 0;
	this.name = "";
	this.times = 0;
	this.exist = false;
}

public void setscore(double s) {
	this.score = s;
}

public double getscore() {
	return score;
}

public void setname(String s) {
	this.name = s;
}

public String getname() {
	return name;
}

public void settimes(int s) {
	this.score = s;
}

public double gettimes() {
	return times;
}

public void setexist(boolean s) {
	this.exist = s;
}

public boolean getexist() {
	return exist;
}

public void addscore(double score) {
	this.score += score;
}

public void addtimes(int i) {
	this.times += i;
}


}