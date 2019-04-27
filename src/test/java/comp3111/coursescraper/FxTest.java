/**
 * 
 * You might want to uncomment the following code to learn testFX. Sorry, no tutorial session on this.
 * 
 */
package comp3111.coursescraper;

import static org.junit.Assert.*;

import org.junit.Test;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;


public class FxTest extends ApplicationTest {

	private Scene s;
	
	@Override
	public void start(Stage stage) throws Exception {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/ui.fxml"));
   		VBox root = (VBox) loader.load();
   		Scene scene =  new Scene(root);
   		stage.setScene(scene);
   		stage.setTitle("Course Scraper");
   		stage.show();
   		s = scene;
	}

	
//	@Test 
//	public void testfilter() {
//		clickOn("#tabFilter");
//		clickOn("#Tuesday");
//		Button c = (Button)s.lookup("#Tuesday");
//		sleep(1000);
//
//	}
	@Test 
	public void testSearchButton() {
		clickOn("#tabMain");
		int courseCount = 0;
		TableList ScrappedResult [];
		clickOn("#buttonSearch");
		Button b = (Button)s.lookup("#buttonSearch");
		sleep(1000);
		courseCount =51;
	}
	@Test 
	public void userClickedOnTimeTable() {
		clickOn("#tabMain");
		clickOn("#buttonSearch");
		clickOn("#tabFilter");
		clickOn("#cboxMon");
		clickOn("#tabSfq");
		clickOn("#buttonInstructorSfq");
		clickOn("#buttonSfqEnrollCourse");
		clickOn("#tabFilter");
		clickOn("#buttonSelectAll");
		clickOn("#tabTimetable");
		clickOn("#tabFilter");
		clickOn("#buttonSelectAll");
		clickOn("#buttonSelectAll");
		clickOn("#tabList");
		clickOn("#tEnroll");
	}

	@Test 
	public void userClickedOnTimeTable5() {
		clickOn("#tabMain");
		clickOn("#buttonSearch");
		clickOn("#tabFilter");
		clickOn("#cboxMon");
		clickOn("#tabList");
		clickOn("#tableView");
		clickOn("#tabMain");
		clickOn("#buttonSearch");
	}
}
