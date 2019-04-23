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

import java.util.Random;
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
    
    // List: List element (task 3)
//    @FXML private TableView<TableList> tableView = new TableView<TableList>();
//    @FXML private TableColumn<TableList, String> tCourseCode = new TableColumn<TableList, String> ();
//    @FXML private TableColumn<TableList, String> tLectureSection = new TableColumn<TableList, String> ();
//    @FXML private TableColumn<TableList, String> tCourseName = new TableColumn<TableList, String> ();
//    @FXML private TableColumn<TableList, String> tInstructor = new TableColumn<TableList, String> ();
//    @FXML private TableColumn<TableList, CheckBox> tEnroll = new TableColumn<TableList, CheckBox> ();
    
	@FXML 																			         // Associate data with Columns
	private void initialize() {
		
		
		tCourseCode.setCellValueFactory(new PropertyValueFactory<TableList,String>("CourseCode"));
		tLectureSection.setCellValueFactory(new PropertyValueFactory<TableList,String>("sections"));
		tCourseName.setCellValueFactory(new PropertyValueFactory<TableList,String>("CourseName"));
		tInstructor.setCellValueFactory(new PropertyValueFactory<TableList,String>("instructors"));
		tEnroll.setCellValueFactory(new PropertyValueFactory<TableList,String>("enroll"));
	}
//	task4 ---------------------when something is click on update time table 
	@FXML
	void userClickedOnTable() {
		
    	//Add a random block on Saturday
    	AnchorPane ap = (AnchorPane)tabTimetable.getContent();
    	while(ap.getChildren().size()>29) {
    		ap.getChildren().remove(ap.getChildren().size()-1);
    	}
    	Label randomLabel = new Label("COMP1022\nL1");
    	Random r = new Random();
    	double start = (r.nextInt(10) + 1) * 20 + 40;
    	double opacit=0.55;
    	double [][] color = {
    			{1,0,0},
    			{0.5,0.5,0.5},
    			{0,1,0},
    			{0.5,0,0.5},
    			{0,0,1}
    	};
    	randomLabel.setBackground(new Background(new BackgroundFill(Color.color(color[0][0], color[0][1], color[0][2]), CornerRadii.EMPTY, Insets.EMPTY)));
    	randomLabel.setLayoutX(602.0);
    	randomLabel.setLayoutY(start);
    	randomLabel.setMinWidth(100.0);
    	randomLabel.setMaxWidth(100.0);
    	randomLabel.setMinHeight(60);
    	randomLabel.setMaxHeight(60);
    	randomLabel.setOpacity(0.55);
    	randomLabel.setId("Si");
    	ap.getChildren().add(randomLabel);

//    	c.setBackground(new Background(new BackgroundFill(Color.color(color[0][0], color[0][1], color[0][2]), CornerRadii.EMPTY, Insets.EMPTY)));
//
//    	Label [] listLabel = new Label[5];
//    	for(int l = 0; l < 5; l++) {
//    		listLabel[l]=null;
//    	}
//    	for(int l = 0; l < 5; l++) {
//    		listLabel[l] = new Label("value");    		//value = courseCode + sectionCode
//    		listLabel[l].setBackground(new Background(new BackgroundFill(Color.color(color[0][0], color[0][1], color[0][2]), CornerRadii.EMPTY, Insets.EMPTY)));
////    		listLabel[l].setLayoutX(value);				//	value = day*100+2
////    		listLabel[1].setLayoutY(value); 			// value = 40+ 20*(Hour-9)+minutes/3
//    		listLabel[1].setMinWidth(100.0);
//    		listLabel[1].setMaxWidth(100.0);
////    		listLabel[1].setMinHeight(value);				// value = 20*(endhour-endhour)+(endtmintues - startminutes)/3
////    		listLabel[1].setMaxHeight(value);				// value = 20*(endhour-endhour)+(endtmintues - startminutes)/3
//    		listLabel[1].setOpacity(0.55);
////    		ap.getChildren().add(listLabel[1]);			
//    	}
//		
	}
//   task4 end -----------------------------------
	
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
    	//Task 6 
    	buttonSfqEnrollCourse.setDisable(false);    	                            // Enable the sfqEnrollCourse Button 
    	buttonInstructorSfq.setDisable(false);    	                                // Enable the buttonInstructorSfq Button 
    	
    	//task 1-------------------------for console displace
    	textAreaConsole.clear();
    	int courseCount = 0;
    	int sectionCount = 0;
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
    	
    	// Task 3 

    	
    	// ScrappedResult for table to use;
    	
    	int i = 0;
    	
    	final ObservableList<TableList> data = FXCollections.observableArrayList();
    	TableList [] ScrappedResult = new TableList[sectionCount];
    	
    	for(int u = 0;  u < sectionCount; u++) {
    		ScrappedResult[u] = new TableList();
    	}
    	
    	System.out.println("sectionCount = " + sectionCount);
    	System.out.println("Message for testing ");
    	for (Course c: v) {
    		for (int k = 0 ; k < c.getNumSection(); k++) {
    			String InstructorTotal = "";
    			CheckBox cb = new CheckBox();
    			c.setCourseCode(c.getTitle());
    			c.setCourseName(c.getTitle());
    			c.setfirstSectionCode(c.getSection(k).getSectionCode());
    			ScrappedResult[i+k].setEnroll(cb);
         		ScrappedResult[i+k].setCourseCode(c.getCourseCode());
        		ScrappedResult[i+k].setCourseName(c.getCourseName());
        		ScrappedResult[i+k].setSections(c.getfirstSectionCode());
        		for (int suibian = 0; suibian < c.getNumIstructor(); suibian++) {
        			InstructorTotal += c.getInstructor(suibian) + " ";
        		}
        		ScrappedResult[i+k].setInstructors(InstructorTotal);
    		}
    		    		
    		int temp = c.getNumSection();
    		i = i + temp;
    	} 
    	
    	if (sectionCount != 0) {
    		for (int m = 0; m < sectionCount; m++) {
    			data.add(ScrappedResult[m]);
    		}
    	}
     	
    	tableView.setItems(data);                                                            // Add data inside table

    }

}
