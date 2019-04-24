package comp3111.coursescraper;


import java.awt.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
// Task 3 related
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.event.EventHandler;


import java.util.Random;
import java.util.Vector;
import java.util.Arrays;
import java.util.List;

public class Controller {
    @FXML
    private Tab tabMain;

    @FXML
    private TextField textfieldTerm;

    @FXML
    private TextField textfieldSubject;

    @FXML
    private Button buttonSearch;

    @FXML
    private TextField textfieldURL;

    @FXML
    private Tab tabStatistic;

    @FXML
    private Tab tabFilter;

    @FXML
    private Tab tabList;

    @FXML
    private Tab tabTimetable;

    @FXML
    private Tab tabAllSubject;

    @FXML
    private ProgressBar progressbar;

    @FXML
    private Tab tabSfq;

    @FXML
    private TableView<TableList> tableView;

    @FXML
    private TableColumn<TableList, String> tCourseCode;

    @FXML
    private TableColumn<TableList, String> tLectureSection;

    @FXML
    private TableColumn<TableList, String> tCourseName;

    @FXML
    private TableColumn<TableList, String> tInstructor;

    @FXML
    private TableColumn<TableList, String> tEnroll;
    @FXML
    private TextField textfieldSfqUrl;

    @FXML
    private Button buttonSfqEnrollCourse;

    @FXML
    private Button buttonInstructorSfq;

    @FXML
    private TextArea textAreaConsole;
    
    
    private Scraper scraper = new Scraper();
    private TableList [] ScrappedResult = new TableList [1000];
    private TableList [] enrollList = new TableList [1000];
    private int sectionCount = 0;	
    private int numEnroll = 0;
    private boolean active = false;
	@FXML 																			         // Associate data with Columns
	private void initialize() {
		
		
		tCourseCode.setCellValueFactory(new PropertyValueFactory<TableList,String>("CourseCode"));
		tLectureSection.setCellValueFactory(new PropertyValueFactory<TableList,String>("sections"));
		tCourseName.setCellValueFactory(new PropertyValueFactory<TableList,String>("CourseName"));
		tInstructor.setCellValueFactory(new PropertyValueFactory<TableList,String>("instructors"));
		tEnroll.setCellValueFactory(new PropertyValueFactory<TableList,String>("enroll"));
	}
	
	
////	task4 ---------------------when something is click on update time table 
//	@FXML
//	void userClickedOnTimeTable() {		
//    	//Add a random block on Saturday
//    	AnchorPane ap = (AnchorPane)tabTimetable.getContent();
//    	while(ap.getChildren().size()>29) {
//    		ap.getChildren().remove(ap.getChildren().size()-1);
//    	}
////    	Label randomLabel = new Label("COMP1022\nL1");
////    	Random r = new Random();
////    	double start = (r.nextInt(10) + 1) * 20 + 40;
//    	double opacit=0.55;
//    	double [][] color = {
//    			{1,0,0},
//    			{0.5,0.5,0.5},
//    			{0,1,0},
//    			{0.5,0,0.5},
//    			{0,0,1}
//    	};
////    	randomLabel.setBackground(new Background(new BackgroundFill(Color.color(color[0][0], color[0][1], color[0][2]), CornerRadii.EMPTY, Insets.EMPTY)));
////    	randomLabel.setLayoutX(602.0);
////    	randomLabel.setLayoutY(start);
////    	randomLabel.setMinWidth(100.0);
////    	randomLabel.setMaxWidth(100.0);
////    	randomLabel.setMinHeight(60);
////    	randomLabel.setMaxHeight(60);
////    	randomLabel.setOpacity(0.55);
////    	randomLabel.setId("Si");
////    	ap.getChildren().add(randomLabel);
////    	c.setBackground(new Background(new BackgroundFill(Color.color(color[0][0], color[0][1], color[0][2]), CornerRadii.EMPTY, Insets.EMPTY)));
//    	int day = 0;
//    	int startH = 0;
//    	int startM = 0;
//    	int endH = 0;
//    	int endM = 0;
//    	int index = 0;
//    	Label [] listLabel = new Label[numEnroll+1];
//    	for(int l = 0; l < numEnroll+1; l++) {
//    		listLabel[l]=null;
//    	}
//    	for(int s= 0; s < numEnroll+1; s++ ) {
//    		for(int k = 0; k < enrollList[s].getNumSlots(); k++) {
//    			day = enrollList[s].getSlot(k).getDay();
//    			startH = enrollList[s].getSlot(k).getStartHour();
//    			startM = enrollList[s].getSlot(k).getStartMinute();
//    			endH = enrollList[s].getSlot(k).getEndHour();
//    			endM = enrollList[s].getSlot(k).getEndMinute();
//    			listLabel[index] = new Label(enrollList[s].getCourseCode() + "/n" + enrollList[s].getSections());
//    			listLabel[index].setBackground(new Background(new BackgroundFill(Color.color(color[0][0], color[0][1], color[0][2]), CornerRadii.EMPTY, Insets.EMPTY)));
//        		listLabel[index].setLayoutX(day*100+2);				//	value = day*100+2
//        		listLabel[index].setLayoutY(40+20*(startH-9)+startM/3); 			// value = 40+ 20*(Hour-9)+minutes/3
//        		listLabel[index].setMinWidth(100.0);
//        		listLabel[index].setMaxWidth(100.0);
//        		listLabel[index].setMinHeight(20*(endH-startH)+(endM-startM)/3);				// value = 20*(endhour-endhour)+(endtmintues - startminutes)/3
//        		listLabel[index].setMaxHeight(20*(endH-startH)+(endM-startM)/3);				// value = 20*(endhour-endhour)+(endtmintues - startminutes)/3
//        		listLabel[index].setOpacity(0.55);
//        		ap.getChildren().add(listLabel[index]);	
//    			index += 1;
//    		}
//    		
//    	}
////    	Label [] listLabel = new Label[5];
////    	for(int l = 0; l < 5; l++) {
////    		listLabel[l]=null;
////    	}
////    	for(int l = 0; l < 5; l++) {
////    		listLabel[l] = new Label("value");    		//value = courseCode + sectionCode
////    		listLabel[l].setBackground(new Background(new BackgroundFill(Color.color(color[0][0], color[0][1], color[0][2]), CornerRadii.EMPTY, Insets.EMPTY)));
////    		listLabel[l].setLayoutX(value);				//	value = day*100+2
////    		listLabel[1].setLayoutY(value); 			// value = 40+ 20*(Hour-9)+minutes/3
////    		listLabel[1].setMinWidth(100.0);
////    		listLabel[1].setMaxWidth(100.0);
////    		listLabel[1].setMinHeight(value);				// value = 20*(endhour-endhour)+(endtmintues - startminutes)/3
////    		listLabel[1].setMaxHeight(value);				// value = 20*(endhour-endhour)+(endtmintues - startminutes)/3
////    		listLabel[1].setOpacity(0.55);
////    		ap.getChildren().add(listLabel[1]);			
////    	}
//		
//	}
//  task4 end -----------------------------------
	@FXML
	void userClickedOnTable() {		
		// Task 3 Update Enroll information 
		for (int it = 0 ; it < sectionCount; it++) {
				if (ScrappedResult[it].getEnroll().isSelected()) {
					int noContain = 0;
					for(int im = 0 ; im < numEnroll+1; im++) {
						if(enrollList[im].getSections().equals(ScrappedResult[it].getSections())) {
							noContain = 1;
						}
					}
					if(noContain == 0) {
						enrollList[numEnroll].setEnroll(ScrappedResult[it].getEnroll());
						enrollList[numEnroll].setCourseCode(ScrappedResult[it].getCourseCode());
						enrollList[numEnroll].setCourseName(ScrappedResult[it].getCourseName());
						enrollList[numEnroll].setSections(ScrappedResult[it].getSections());
						enrollList[numEnroll].setInstructors(ScrappedResult[it].getInstructors());
			    		for(int s = 0; s < ScrappedResult[it].getNumSlots(); s++) {
			    			enrollList[numEnroll].addSlot(ScrappedResult[it].getSlot(s));
			    		}
						numEnroll+=1;
					}
				}
				else {
					int contain = -1;
					for(int im = 0 ; im < numEnroll+1; im++) {
						if(enrollList[im].getSections().equals(ScrappedResult[it].getSections())) {
							contain = im;
						}
					}
					if(contain != -1) {
						for(int i = contain; i < numEnroll; i++) {
							enrollList[i]=enrollList[i+1];
						}
						enrollList[numEnroll] = new TableList();
						numEnroll -= 1;
					}
				}
		}
		if(numEnroll!=0) {
			active = true;
		}
		if(active) {
			textAreaConsole.clear();
			for(int im = 0 ; im < numEnroll+1; im++) {
				textAreaConsole.setText(textAreaConsole.getText()+"\n"+enrollList[im].getCourseCode()+" " + enrollList[im].getSections() + " has been enrolled in to your course list" +  "\n");
			}
		}
		
		
		
		
    	//Add a random block on Saturday
    	AnchorPane ap = (AnchorPane)tabTimetable.getContent();
    	while(ap.getChildren().size()>29) {
    		ap.getChildren().remove(ap.getChildren().size()-1);
    	}
//    	Label randomLabel = new Label("COMP1022\nL1");
//    	Random r = new Random();
//    	double start = (r.nextInt(10) + 1) * 20 + 40;
    	double opacit=0.55;

//    	randomLabel.setBackground(new Background(new BackgroundFill(Color.color(color[0][0], color[0][1], color[0][2]), CornerRadii.EMPTY, Insets.EMPTY)));
//    	randomLabel.setLayoutX(602.0);
//    	randomLabel.setLayoutY(start);
//    	randomLabel.setMinWidth(100.0);
//    	randomLabel.setMaxWidth(100.0);
//    	randomLabel.setMinHeight(60);
//    	randomLabel.setMaxHeight(60);
//    	randomLabel.setOpacity(0.55);
//    	randomLabel.setId("Si");
//    	ap.getChildren().add(randomLabel);
//    	c.setBackground(new Background(new BackgroundFill(Color.color(color[0][0], color[0][1], color[0][2]), CornerRadii.EMPTY, Insets.EMPTY)));
    	int day = 0;
    	int startH = 0;
    	int startM = 0;
    	int endH = 0;
    	int endM = 0;
    	int index = 0;
    	Label [] listLabel = new Label[1000];
    	for(int l = 0; l < 1000; l++) {
    		listLabel[l]=null;
    	}
    	double [][] color1 = {
    			{1,0,0},
    			{0,1,0},
    			{0,0,1},
    			{0.3,0.3,0.3},
    			{0.3,0,0.3},
    			{0.3,0.3,0},
    			{0,0.3,0.3},
    			{1,0,1},
    			{0,1,1},
    			{1,1,0},
    			{0.5,0.5,0},
    			{0.5,0,0.5},
    			{0,0.5,0.5},
    			{0.1,0.7,0.7},
    			{0.7,0.1,0.7},
    			{0.7,0.7,0.1},
    			{1,0,0.5},
    			{0,1,0.5},
    			{0,0.5,1},
    			{0.5,0,1}
    	};
    	System.out.println(numEnroll);
    	for(int s= 0; s < numEnroll; s++ ) {
    		System.out.println( enrollList[s].getNumSlots());
    		for(int k = 0; k < enrollList[s].getNumSlots(); k++) {
    			day = enrollList[s].getSlot(k).getDay();
    			startH = enrollList[s].getSlot(k).getStartHour();
    			startM = enrollList[s].getSlot(k).getStartMinute();
    			endH = enrollList[s].getSlot(k).getEndHour();
    			endM = enrollList[s].getSlot(k).getEndMinute();
    			
    			System.out.println(endM-startM);
    			System.out.println("H: " + startH + " -----day: " + day);
    			listLabel[index] = new Label(enrollList[s].getCourseCode() + "\n" + enrollList[s].getSections().substring(0,3));
    			listLabel[index].setStyle("-fx-font: 6 arial;");
    			if(s<20) {
    				listLabel[index].setBackground(new Background(new BackgroundFill(Color.color(color1[s][0],color1[s][1],color1[s][2]), CornerRadii.EMPTY, Insets.EMPTY)));
    			}
    			else {
    				listLabel[index].setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
    			}
        		listLabel[index].setLayoutX((day+1)*100+2);				//	value = day*100+2
        		listLabel[index].setLayoutY(40+20*(startH-9)+startM/3); 			// value = 40+ 20*(Hour-9)+minutes/3
        		listLabel[index].setMinWidth(100.0);
        		listLabel[index].setMaxWidth(100.0);
        		listLabel[index].setMinHeight(20*(endH-startH)+(endM-startM)/3);				// value = 20*(endhour-endhour)+(endtmintues - startminutes)/3
        		listLabel[index].setMaxHeight(20*(endH-startH)+(endM-startM)/3);				// value = 20*(endhour-endhour)+(endtmintues - startminutes)/3
        		listLabel[index].setOpacity(0.55);
        		listLabel[index].setTextFill(Color.WHITE);
        		ap.getChildren().add(listLabel[index]);	
    			index += 1;
    		}		
    	}
//    	Label [] listLabel = new Label[5];
//    	for(int l = 0; l < 5; l++) {
//    		listLabel[l]=null;
//    	}
//    	for(int l = 0; l < 5; l++) {
//    		listLabel[l] = new Label("value");    		//value = courseCode + sectionCode
//    		listLabel[l].setBackground(new Background(new BackgroundFill(Color.color(color[0][0], color[0][1], color[0][2]), CornerRadii.EMPTY, Insets.EMPTY)));
//    		listLabel[l].setLayoutX(value);				//	value = day*100+2
//    		listLabel[1].setLayoutY(value); 			// value = 40+ 20*(Hour-9)+minutes/3
//    		listLabel[1].setMinWidth(100.0);
//    		listLabel[1].setMaxWidth(100.0);
//    		listLabel[1].setMinHeight(value);				// value = 20*(endhour-endhour)+(endtmintues - startminutes)/3
//    		listLabel[1].setMaxHeight(value);				// value = 20*(endhour-endhour)+(endtmintues - startminutes)/3
//    		listLabel[1].setOpacity(0.55);
//    		ap.getChildren().add(listLabel[1]);			
//    	}
	}
// task 3 end -------------------------------------
	

    @FXML
    void allSubjectSearch() {
    	
    }

    // Task 6
    @FXML
    void findInstructorSfq() {
    	buttonSfqEnrollCourse.setDisable(false);    	                            // Enable the sfqEnrollCourse Button 
    	buttonInstructorSfq.setDisable(false);    	                                // Enable the buttonInstructorSfq Button 
    }

    @FXML
    void findSfqEnrollCourse() {

    }

    @FXML
    void search() {
    	//task 3 
    	active = false;
    	//Task 6 
    	buttonSfqEnrollCourse.setDisable(false);    	                            // Enable the sfqEnrollCourse Button 
    	buttonInstructorSfq.setDisable(false);    	                                // Enable the buttonInstructorSfq Button 													
    	//task 1-------------------------for console displace
    	
    	textAreaConsole.clear();
    	int courseCount = 0;
    	sectionCount = 0;	
    	String [] instructorsArray = new String[100];
    	for(int i = 0; i < 100; i++) {
    		instructorsArray[i]=null;
    	}
    	int arrayNum = 0;
    	//task 1.1-------------------------for checking the information of search is correct or not
    	List<Course> v = scraper.scrape(textfieldURL.getText(), textfieldTerm.getText(),textfieldSubject.getText());
    	if(v==null) {
    		textAreaConsole.setText("There is not class information. Make sure you enter correct information for search");
    		return;
    	}
    	//1.1 end--------------------------
    	//task 1-------------------------for getting information from scrapered course  
    	for (Course c : v) {
	    		if(c.getNumSection() !=0) {
	    		courseCount +=1;									//get total course number
	    		sectionCount += c.getNumSection();					//get total section number
	    		String newline = c.getTitle() + "\n";				//get each course title 
	    	//task 1.3.c-------------------------for getting name of Instructors who has teaching assignment this term but does not need to teach at Tu 3:10pm
	    		for(int i = 0; i < c.getNumSlots(); i++) {
	    			if(c.getSlot(i).getEndHour()<15 || c.getSlot(i).getStartHour()>15) {
	    				for(int j = 0; j < c.getSlot(i).getNumInstructors(); j++) {
	    					boolean contains = Arrays.stream(instructorsArray).anyMatch(c.getSlot(i).getInstructor(j)::equals);
	    					if(!contains) {
	    						instructorsArray[arrayNum] = c.getSlot(i).getInstructor(j);
	        					arrayNum += 1;
	    					}
	    				}
	    			}
	    			else if(c.getSlot(i).getEndHour()==15) {
	        			if(c.getSlot(i).getEndMinute() < 10 || c.getSlot(i).getStartMinute()>10) {
	        				for(int j = 0; j < c.getSlot(i).getNumInstructors(); j++) {
	        					boolean contains = Arrays.stream(instructorsArray).anyMatch(c.getSlot(i).getInstructor(j)::equals);
	        					if(!contains) {
	        						instructorsArray[arrayNum] = c.getSlot(i).getInstructor(j);
	            					arrayNum += 1;
	        					}
	        				}
	        			}
	    			}
	    		}
	    	//taks1.3.c  end-----------------------
	    	//task 1.2-------------------------for getting the section and slots information of courses
	    		for (int j = 0; j < c.getNumSection(); j++) {
	    			String s = c.getSection(j).getSectionCode();	
		    		for (int i = 0; i < c.getSection(j).getNumSlots(); i++) {
		    			Slot t = c.getSection(j).getSlot(i);
		    			newline += s + ":" + t + "\n";
		    		}
	    		}
	    		textAreaConsole.setText(textAreaConsole.getText() + "\n" + newline);
	    	}
    	}
    	//task1.2 end --------------------------
    	//task 1.3.c.a-------------------------for rearranging the instructor name
		String [] newArray = new String[arrayNum];
		for(int i =0; i<arrayNum;i++) {
			newArray[i]=instructorsArray[i];
		}
		Arrays.sort(newArray);
		String names = "\n";
		int spaceIndex = 0;
    	for(int i = 0;i<arrayNum;i++) {
    		names = names + newArray[i]+",\t  ";
    		spaceIndex +=1;
    		if(spaceIndex == 5) {
    			names = names +"\n";
    			spaceIndex-=5;
    		}
    	}
    	//task 1.3.c.a end -----------------------------
    	//task 1-------------------------for showing all information required by task 1 in the console
    	textAreaConsole.setText("Total Number of difference sections in this search: " + sectionCount +
    							"\nTotal Number of Course in this search: " + courseCount + 
    							"\nInstructors who has teaching assignment this term but does not need to teach at Tu 3:10pm: "
    							+ names +"\n"
    							+ textAreaConsole.getText());
    	
    	//task 1  end------------------------------------------------------------------------------------
    	

    	
    	int i = 0;
    	
    	final ObservableList<TableList> data = FXCollections.observableArrayList();

//    	task 3 ----------------------
    	for(int u = 0;  u < 1000; u++) {
    		ScrappedResult[u] = new TableList();		
    	}
    	
	    if (enrollList[0] == null) {
			for (int jc = 0 ; jc < 1000; jc++) {
				enrollList[jc] = new TableList();
			}    	
	    }    
    	
	    if(numEnroll != 0 ) {
	    	i = numEnroll;
	    }
	    for(int j = 0; j<i;j++) {
	    	ScrappedResult[j].setEnroll(enrollList[j].getEnroll());
     		ScrappedResult[j].setCourseCode(enrollList[j].getCourseCode());
    		ScrappedResult[j].setCourseName(enrollList[j].getCourseName());
    		ScrappedResult[j].setSections(enrollList[j].getSections());
    		ScrappedResult[j].setInstructors(enrollList[j].getInstructors());
    		for(int s = 0; s < enrollList[j].getNumSlots(); s++) {
    			ScrappedResult[j].addSlot(enrollList[j].getSlot(s));
    		}
	    }
    	for (Course c: v) {
    		for (int k = 0 ; k < c.getNumSection(); k++) {
    			String InstructorTotal = "";
    			boolean skip = false;
    			CheckBox cb = new CheckBox();
    			c.setCourseCode(c.getTitle());
    			c.setCourseName(c.getTitle());
    			c.setfirstSectionCode(c.getSection(k).getSectionCode());
    			for(int j =0; j<numEnroll;j++) {
    				if(c.getfirstSectionCode().equals(enrollList[j].getSections())) {
    					skip =true;
    					i-=1;
    				}
    			}
    			if(!skip) {
	    			ScrappedResult[i+k].setEnroll(cb);
	         		ScrappedResult[i+k].setCourseCode(c.getCourseCode());
	        		ScrappedResult[i+k].setCourseName(c.getCourseName());
	        		ScrappedResult[i+k].setSections(c.getfirstSectionCode());
	        		for (int suibian = 0; suibian < c.getNumIstructor(); suibian++) {
	        			InstructorTotal += c.getInstructor(suibian) + " ";
	        		}
	        		ScrappedResult[i+k].setInstructors(InstructorTotal);
	        		System.out.println(c.getSection(k).getNumSlots());
	        		for(int s = 0; s < c.getSection(k).getNumSlots(); s++) {
	        			ScrappedResult[i+k].addSlot(c.getSection(k).getSlot(s));
	        		}
	    		}
    		}
    		    		
    		int temp = c.getNumSection();
    		i = i + temp;
    	} 

    	for (int m = 0; m < sectionCount+numEnroll; m++) {    			
    		data.add(ScrappedResult[m]);
    	}

    	tableView.setItems(data);                                                            // Add data inside table
    	
    	// CheckBox Event

    	
    }

}
