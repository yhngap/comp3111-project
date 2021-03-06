package comp3111.coursescraper;


import org.junit.Test;

import comp3111.coursescraper.Course;

import static org.junit.Assert.*;

import java.util.List;

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

public class ItemTest {

	@Test
	public void testSetTitle() {
		Course i = new Course();
		i.setTitle("ABCDE");
		assertEquals(i.getTitle(), "ABCDE");
	}
	@Test
	public void testCourseCode() {
		Course i = new Course();
		i.setCourseCode("12345678901");
		assertEquals(i.getCourseCode(), "1234567890");
	}
	@Test
	public void testsetCourseName() {
		Course i = new Course();
		i.setCourseName("123456789012ABCDE");
		assertEquals(i.getCourseName(), "ABCDE");
	}
	@Test
	public void testsetDescription() {
		Course i = new Course();
		i.setDescription("ABCDE");
		assertEquals(i.getDescription(), "ABCDE");
	}
	@Test
	public void testaddInstructor() {
		Course i = new Course();
		i.addInstructor("ABCDE");
		assertEquals(i.getInstructor(0), "ABCDE");
	}
	@Test
	public void testsetfirstSectionCode() {
		Course i = new Course();
		i.setfirstSectionCode("ABCDE");
		assertEquals(i.getfirstSectionCode(), "ABCDE");
	}
	@Test
	public void testsetExclusion() {
		Course i = new Course();
		i.setExclusion("ABCDE");
		assertEquals(i.getExclusion(), "ABCDE");
	}
	@Test
	public void testgetNumIstructor() {
		Course i = new Course();
		i.addInstructor("ABC");
		assertEquals(i.getNumIstructor(), 1);
	}
	@Test
	public void testgetNumSlots() {
		Course i = new Course();
		Slot s = new Slot();
		i.addSlot(s);
		assertEquals(i.getNumSlots(), 1);
	}
	@Test
	public void testgetNumSection() {
		Course i = new Course();
		Section s = new Section();
		i.addSection(s);
		assertEquals(i.getNumSection(), 1);
	}
	@Test
	public void testsetNumSlots() {
		Course i = new Course();
		i.setNumSlots(2);
		assertEquals(i.getNumSlots(), 2);
	}
	@Test
	public void testsetNumSection() {
		Course i = new Course();
		i.setNumSections(2);
		assertEquals(i.getNumSection(), 2);
	}
	@Test
	public void testsetNumInstructors() {
		Course i = new Course();
		i.setNumInstructors(2);
		assertEquals(i.getNumIstructor(), 2);
	}
	@Test
	public void testsetCourseNAme() {
		Course i = new Course();
		i.setCourseName("012345678901 ABC");
		assertEquals(i.getCourseName(),"ABC" );
	}
	@Test
	public void testaddInstructor2() {
		Course i = new Course();
		i.setNumInstructors(50);
		i.addInstructor("ABC");
		assertEquals(i.getNumIstructor(),50 );
	}
	@Test
	public void testgetInstructor2() {
		Course i = new Course();
		i.addInstructor("ABC");
		assertEquals(i.getInstructor(2),null);
	}
	@Test
	public void testaddSection2() {
		Course i = new Course();
		Section s = new Section();
		i.addSection(s);
		assertEquals(i.getSection(2),null);
	}
	public void testaddSection3() {
		Course i = new Course();
		Section s = new Section();
		i.addSection(s);
		assertEquals(i.getSection(1),s);
	}
	@Test
	public void testgetSlot() {
		Course i = new Course();
		Slot s = new Slot();
		i.addSlot(s);
		assertEquals(i.getSlot(2),null);
	}
	@Test
	public void testaddMoreSlot() {
		Course i = new Course();
		i.setNumSlots(50);
		Slot s = new Slot();
		i.addSlot(s);
		assertEquals(i.getNumSlots(),50);
	}
	@Test
	public void testgetSlot2() {
		Course i = new Course();
		Slot s = new Slot();
		s.setVenue("A");
		i.addSlot(s);
		assertEquals(i.getSlot(0).getVenue(),"A");
	}
	@Test
	public void testaddSectionSlot() {
		Section i = new Section();
		Slot s = new Slot();
		i.addSlot(s);
		assertEquals(i.getNumSlots(),1);
	}
	@Test
	public void testgetSectionSlot() {
		Section i = new Section();
		Slot s = new Slot();
		i.addSlot(s);
		assertEquals(i.getSlot(2),null);
	}	
	@Test
	public void testaddSectionMoreSlot() {
		Section i = new Section();
		Slot s = new Slot();
		i.setNumSlots(10);
		i.addSlot(s);
		assertEquals(i.getNumSlots(),10);
	}
	@Test
	public void testCourseAddSection() {
		Course i = new Course();
		Section s = new Section();
		i.setNumSlots(10);
		i.addSection(s);
		assertEquals(i.getNumSlots(),10);
	}
	@Test
	public void testCourseSetSectionCode() {
		Course i = new Course();
		Section s = new Section();
		s.setSectionCode("A");
		i.addSection(s);
		assertEquals(i.getSection(0).getSectionCode(),"A");
	}
	@Test
	public void testSlotAddInstructor() {
		Slot s = new Slot();
		s.addInstructor("ABC");
		assertEquals(s.getInstructor(0),"ABC");
	}
	@Test
	public void testSlotAddInstructor2() {
		Slot s = new Slot();
		s.setNumInstructors(10);
		s.addInstructor("ABC");
		assertEquals(s.getInstructor(11),null);
	}
	@Test
	public void testScraperCreateSection() {
		Scraper scraper = new Scraper();
		List<Course> v = scraper.scrape("https://w5.ab.ust.hk/wcq/cgi-bin", "1830", "COMP");
		assertEquals(v.get(0).getNumSection(),2);
	}
	@Test
	public void Test() {
		Section s = new Section();
		assertEquals(s.Test(0),"");
	}

}
