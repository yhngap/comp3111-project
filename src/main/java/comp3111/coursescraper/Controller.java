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
    private TextField textfieldSfqUrl;

    @FXML
    private Button buttonSfqEnrollCourse;

    @FXML
    private Button buttonInstructorSfq;

    @FXML
    private TextArea textAreaConsole;

    
    private Scraper scraper = new Scraper();
    
    @FXML
    void allSubjectSearch() {
    	
    }

    @FXML
    void findInstructorSfq() {
    	buttonInstructorSfq.setDisable(true);
    }

    @FXML
    void findSfqEnrollCourse() {

    }

    @FXML
    void search() {
    	//task 1
    	textAreaConsole.clear();
    	int courseCount = 0;
    	int sectionCount = 0;
    	String [] instructorsArray = new String[100];
    	for(int i = 0; i < 100; i++) {
    		instructorsArray[i]=null;
    	}
    	int arrayNum = 0;
    	List<Course> v = scraper.scrape(textfieldURL.getText(), textfieldTerm.getText(),textfieldSubject.getText());
    	if(v==null) {
    		textAreaConsole.setText("There is not class information. Make sure you enter correct information for search");
    		return;
    	}
    	for (Course c : v) {
    		courseCount +=1;									//get total course
    		sectionCount += c.getNumSection();					//get total section
    		String newline = c.getTitle() + "\n";
    		

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
    		
    		for (int j = 0; j < c.getNumSection(); j++) {
    			String s = c.getSection(j).getSectionCode();
    		for (int i = 0; i < c.getSection(j).getNumSlots(); i++) {
    			Slot t = c.getSection(j).getSlot(i);
    			newline += s + ":" + t + "\n";
    		}
    		}
    		textAreaConsole.setText(textAreaConsole.getText() + "\n" + newline);
    	}
		String [] newArray = new String[arrayNum];
		for(int i =0; i<arrayNum;i++) {
			newArray[i]=instructorsArray[i];
		}
		Arrays.sort(newArray);
		String names = newArray[0] + ",\n";
		int spaceIndex = 0;
//    	Arrays.sort(instructorsArray);
//    	System.out.println(Arrays.toString(instructorsArray));
    	for(int i = 1;i<arrayNum;i++) {
    		names = names + newArray[i]+",\t  ";
    		spaceIndex +=1;
    		if(spaceIndex == 5) {
    			names = names +"\n";
    			spaceIndex-=5;
    		}
    	}
    	textAreaConsole.setText("Total Number of difference sections in this search: " + sectionCount +
    							"\nTotal Number of Course in this search: " + courseCount + 
    							"\nInstructors who has teaching assignment this term but does not need to teach at Tu 3:10pm: "
    							+ names +"\n"
    							+ textAreaConsole.getText());
    	//end

    	
    	
    	//Add a random block on Saturday
    	AnchorPane ap = (AnchorPane)tabTimetable.getContent();
    	Label randomLabel = new Label("COMP1022\nL1");
    	Random r = new Random();
    	double start = (r.nextInt(10) + 1) * 20 + 40;
    	double opacit=0.55;
    	randomLabel.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
    	randomLabel.setLayoutX(600.0);
    	randomLabel.setLayoutY(start);
    	randomLabel.setMinWidth(100.0);
    	randomLabel.setMaxWidth(100.0);
    	randomLabel.setMinHeight(60);
    	randomLabel.setMaxHeight(60);
    
    	ap.getChildren().addAll(randomLabel);
    	
    	//test
    	AnchorPane ap1 = (AnchorPane)tabTimetable.getContent();
    	Label randomLabel1 = new Label("COMP1022\nL1");
    	Random r1 = new Random();
    	double start1 = (r.nextInt(10) + 1) * 20 + 40;

    	randomLabel1.setBackground(new Background(new BackgroundFill(Color.color(1,0, 0, 0.5), CornerRadii.EMPTY, Insets.EMPTY)));
    	randomLabel1.setLayoutX(600.0);
    	randomLabel1.setLayoutY(start+10);
    	randomLabel1.setMinWidth(100.0);
    	randomLabel1.setMaxWidth(100.0);
    	randomLabel1.setMinHeight(60);
    	randomLabel1.setMaxHeight(60);
    
    	ap.getChildren().addAll(randomLabel1);
    	//end
    	
    }

}
