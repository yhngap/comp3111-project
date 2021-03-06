package comp3111.coursescraper;

import java.net.URLEncoder;
import java.util.List;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.DomText;
import java.util.Vector;


/**
 * WebScraper provide a sample code that scrape web content. After it is constructed, you can call the method scrape with a keyword, 
 * the client will go to the default url and parse the page by looking at the HTML DOM.  
 * <br>
 * In this particular sample code, it access to HKUST class schedule and quota page (COMP). 
 * <br>
 * https://w5.ab.ust.hk/wcq/cgi-bin/1830/subject/COMP
 *  <br>
 * where 1830 means the third spring term of the academic year 2018-19 and COMP is the course code begins with COMP.
 * <br>
 * Assume you are working on Chrome, paste the url into your browser and press F12 to load the source code of the HTML. You might be freak
 * out if you have never seen a HTML source code before. Keep calm and move on. Press Ctrl-Shift-C (or CMD-Shift-C if you got a mac) and move your
 * mouse cursor around, different part of the HTML code and the corresponding the HTML objects will be highlighted. Explore your HTML page from
 * body &rarr; div id="classes" &rarr; div class="course" &rarr;. You might see something like this:
 * <br>
 * <pre>
 * {@code
 * <div class="course">
 * <div class="courseanchor" style="position: relative; float: left; visibility: hidden; top: -164px;"><a name="COMP1001">&nbsp;</a></div>
 * <div class="courseinfo">
 * <div class="popup attrword"><span class="crseattrword">[3Y10]</span><div class="popupdetail">CC for 3Y 2010 &amp; 2011 cohorts</div></div><div class="popup attrword"><span class="crseattrword">[3Y12]</span><div class="popupdetail">CC for 3Y 2012 cohort</div></div><div class="popup attrword"><span class="crseattrword">[4Y]</span><div class="popupdetail">CC for 4Y 2012 and after</div></div><div class="popup attrword"><span class="crseattrword">[DELI]</span><div class="popupdetail">Mode of Delivery</div></div>	
 *    <div class="courseattr popup">
 * 	    <span style="font-size: 12px; color: #688; font-weight: bold;">COURSE INFO</span>
 * 	    <div class="popupdetail">
 * 	    <table width="400">
 *         <tbody>
 *             <tr><th>ATTRIBUTES</th><td>Common Core (S&amp;T) for 2010 &amp; 2011 3Y programs<br>Common Core (S&amp;T) for 2012 3Y programs<br>Common Core (S&amp;T) for 4Y programs<br>[BLD] Blended learning</td></tr><tr><th>EXCLUSION</th><td>ISOM 2010, any COMP courses of 2000-level or above</td></tr><tr><th>DESCRIPTION</th><td>This course is an introduction to computers and computing tools. It introduces the organization and basic working mechanism of a computer system, including the development of the trend of modern computer system. It covers the fundamentals of computer hardware design and software application development. The course emphasizes the application of the state-of-the-art software tools to solve problems and present solutions via a range of skills related to multimedia and internet computing tools such as internet, e-mail, WWW, webpage design, computer animation, spread sheet charts/figures, presentations with graphics and animations, etc. The course also covers business, accessibility, and relevant security issues in the use of computers and Internet.</td>
 *             </tr>	
 *          </tbody>
 *      </table>
 * 	    </div>
 *    </div>
 * </div>
 *  <h2>COMP 1001 - Exploring Multimedia and Internet Computing (3 units)</h2>
 *  <table class="sections" width="1012">
 *   <tbody>
 *    <tr>
 *        <th width="85">Section</th><th width="190" style="text-align: left">Date &amp; Time</th><th width="160" style="text-align: left">Room</th><th width="190" style="text-align: left">Instructor</th><th width="45">Quota</th><th width="45">Enrol</th><th width="45">Avail</th><th width="45">Wait</th><th width="81">Remarks</th>
 *    </tr>
 *    <tr class="newsect secteven">
 *        <td align="center">L1 (1765)</td>
 *        <td>We 02:00PM - 03:50PM</td><td>Rm 5620, Lift 31-32 (70)</td><td><a href="/wcq/cgi-bin/1830/instructor/LEUNG, Wai Ting">LEUNG, Wai Ting</a></td><td align="center">67</td><td align="center">0</td><td align="center">67</td><td align="center">0</td><td align="center">&nbsp;</td></tr><tr class="newsect sectodd">
 *        <td align="center">LA1 (1766)</td>
 *        <td>Tu 09:00AM - 10:50AM</td><td>Rm 4210, Lift 19 (67)</td><td><a href="/wcq/cgi-bin/1830/instructor/LEUNG, Wai Ting">LEUNG, Wai Ting</a></td><td align="center">67</td><td align="center">0</td><td align="center">67</td><td align="center">0</td><td align="center">&nbsp;</td>
 *    </tr>
 *   </tbody>
 *  </table>
 * </div>
 *}
 *</pre>
 * <br>
 * The code 
 * <pre>
 * {@code
 * List<?> items = (List<?>) page.getByXPath("//div[@class='course']");
 * }
 * </pre>
 * extracts all result-row and stores the corresponding HTML elements to a list called items. Later in the loop it extracts the anchor tag 
 * &lsaquo; a &rsaquo; to retrieve the display text (by .asText()) and the link (by .getHrefAttribute()).   
 * 
 *
 */
public class Scraper {
	private WebClient client;

	/**
	 * Default Constructor 
	 */
	public Scraper() {
		client = new WebClient();
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);
	}
	/**
	 * @param e	HtmlElement for scraping information into slot
	 * @param c course for adding slot
	 * @param secondRow	boolean for checking add second slot
	 * @param name the instructor's name for slot
	 * @param nameIndex number of instructors
	 */
	private void addSlot(HtmlElement e, Course c, boolean secondRow, String [] name, int nameIndex) {
		String times[] =  e.getChildNodes().get(secondRow ? 0 : 3).asText().split(" ");
		String venue = e.getChildNodes().get(secondRow ? 1 : 4).asText();
		if (times[0].equals("TBA"))
			return;
		if(venue.equals("TBA"))
			return;
		for (int j = 0; j < times[0].length(); j+=2) {
			String code = times[0].substring(j , j + 2);
			if (Slot.DAYS_MAP.get(code) == null)
				break;
			Slot s = new Slot();
			s.setDay(Slot.DAYS_MAP.get(code));
			s.setStart(times[1]);
			s.setEnd(times[3]);
			s.setVenue(venue);

			for(int i = 0; i < nameIndex; i++) {
				s.addInstructor(name[i]);
			}
			c.addSlot(s);
		}
	}
	
	//task 1.2.1-------------------------for adding the slot into corresponding section
	/**
	 * @param e	HtmlElement for scraping information into slot
	 * @param sec section for adding slot
	 * @param secondRow	boolean for checking add second slot
	 */
	private void addSectionSlot(HtmlElement e, Section sec, boolean secondRow) {
			String times[] =  e.getChildNodes().get(secondRow ? 0 : 3).asText().split(" ");
			String venue = e.getChildNodes().get(secondRow ? 1 : 4).asText();
			if (times[0].equals("TBA"))
				return;
			if(venue.equals("TBA"))
				return;
			if(sec == null) {
				return;
			}
			for (int j = 0; j < times[0].length(); j+=2) {
				String code = times[0].substring(j , j + 2);
				if (Slot.DAYS_MAP.get(code) == null)
					break;
				Slot s = new Slot();
				s.setDay(Slot.DAYS_MAP.get(code));
				s.setStart(times[1]);
				s.setEnd(times[3]);
				s.setVenue(venue);
				sec.addSlot(s);
			}	
	}
	//task 1.2.1 end-------------------------
	
	//task 1.2.2-------------------------for adding section to related course 
	/**
	 * @param c course for adding section
	 * @param section to set the sectioncode
	 */
	private	Section createSection( Course c,String section, String [] name, int nameIndex) {
		int type;
		if(section.substring(0,2).equals("LA")) {
			type=2;
		}
		else if(section.substring(0,1).equals("T")) {
			type=1;
		}
		else if(section.substring(0,1).equals("L")) {
			type=0;
		}
		else 
			return null;

		Section s = new Section();
		s.setSectionCode(section);
		s.setsectionType(type);
		for(int i = 0; i < nameIndex; i++) {
			s.addInstructor(name[i]);
		}
		return s;
	}
	//task 1.2.2 end--------------------------------
	/**
	 * @param baseurl link of website.
	 * @param term course for adding slot
	 * @param sub boolean for checking add second slot
	 * @return A list of course scrape for website
	 */
	public List<Course> scrape(String baseurl, String term, String sub) {

		try {
			
			HtmlPage page = client.getPage(baseurl + "/" + term + "/subject/" + sub);

			List<?> items = (List<?>) page.getByXPath("//div[@class='course']");
			if(items.isEmpty()) {
				return null;
			}
			Vector<Course> result = new Vector<Course>();

			for (int i = 0; i < items.size(); i++) {
				Course c = new Course();
				HtmlElement htmlItem = (HtmlElement) items.get(i);
				
				HtmlElement title = (HtmlElement) htmlItem.getFirstByXPath(".//h2");
			
				c.setTitle(title.asText());
				// Task 3 : 
				c.setCourseCode(title.asText());
				c.setCourseName(title.asText());
				
				
				List<?> popupdetailslist = (List<?>) htmlItem.getByXPath(".//div[@class='popupdetail']/table/tbody/tr");
				HtmlElement exclusion = null;
				HtmlElement attribute = null;
				for ( HtmlElement e : (List<HtmlElement>)popupdetailslist) {
					HtmlElement t = (HtmlElement) e.getFirstByXPath(".//th");
					HtmlElement d = (HtmlElement) e.getFirstByXPath(".//td");
					if (t.asText().equals("EXCLUSION")) {
						exclusion = d;
					}
					if (t.asText().equals("ATTRIBUTES")) {
						attribute = d;
					}
				}
				c.setExclusion((exclusion == null ? "null" : exclusion.asText()));
				c.setAttribute((attribute == null ? "null" : attribute.asText()));
				
				//task 1.3.c.1-------------------------for scraping instructors's name from web into related course
				List<?> instructorslist = (List<?>) htmlItem.getByXPath(".//tr[contains(@class,'newsect')]");
				for ( HtmlElement e : (List<HtmlElement>)instructorslist) {
					List<?> namelist = (List<?>) htmlItem.getByXPath(".//a[contains(@href,'/wcq/cgi-bin/1830/instructor/')]");
					for( int z = 0; z < namelist.size(); z++) {
						HtmlElement name = (HtmlElement) namelist.get(z);
						int repeat = 0;
						if(name!=null) {
							if(c.getNumIstructor()==0) {
								c.addInstructor(name.asText());
							}
							else {
								for(int j = 0; j < c.getNumIstructor(); j++ ) {
									if(c.getInstructor(j).equals(name.asText())) {
										repeat = 1; 
									}
								}
								if(repeat == 0)
									c.addInstructor(name.asText());
							}
						}
					}
				}				
				//task 1.3.c.1 end----------------------------
				
				List<?> sections = (List<?>) htmlItem.getByXPath(".//tr[contains(@class,'newsect')]");
				for ( HtmlElement e: (List<HtmlElement>)sections) {
					
					//task 1-------------------------for obtaining the information of course from web
					String [] names = new String[20];
					int nameIndex = 0;
					List<?> namelist = (List<?>) e.getByXPath(".//a[contains(@href,'/wcq/cgi-bin/1830/instructor/')]");
					for( int z = 0; z < namelist.size(); z++) {
						HtmlElement name = (HtmlElement) namelist.get(z);
						names[nameIndex]=name.asText();
						nameIndex += 1;	
					}
					HtmlElement s = (HtmlElement) e.getFirstByXPath(".//td[contains(@align,'center')]");
					Section section = createSection(c,s.asText(),names,nameIndex);
					addSectionSlot(e,section,false);
					addSlot(e, c, false,names,nameIndex);
					e = (HtmlElement)e.getNextSibling();
					if (e != null && !e.getAttribute("class").contains("newsect")) {
						addSlot(e, c, true,names,nameIndex);
						addSectionSlot(e,section,true);
					}
					if(section != null)
						c.addSection(section);
				}
					//task 1 end----------------------
				result.add(c);
			}
			client.close();
			return result;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	public List<Course> scrapeSFQ(String baseurl){
		try {
			HtmlPage page = client.getPage(baseurl);
			List<?> items = (List<?>) page.getByXPath("//table");
			Vector<TableList>result = new Vector<TableList>();
			if(items.isEmpty()) {
				return null;
			}
			for(int i = 0; i < items.size(); i++) {
				HtmlElement item = (HtmlElement) items.get(i);
				List<?> namelist = item.getByXPath(".//td[contains(@colspan,'3')]");
			}
		} catch (Exception e) {
		System.out.println(e);
		}
	return null;

	}
	/**
	 *@param baseurl url link
	 *@ param term 1810 or 1830
	 * @return scrapp data
	 */	
	// task 5
	public List<String> getSubjects(String baseurl, String term){
		try {
			HtmlPage page = client.getPage(baseurl + term + "/subject/ACCT"); // (baseurl + term + "/") should be used here but the site is down right now
			List<?> items = (List<?>) page.getByXPath("//div[@class='depts']/a");
			Vector<String> result =  new Vector<String>();
			for(int i=0; i<items.size();++i) {
				HtmlElement htmlItem = (HtmlElement) items.get(i);
				String subj = htmlItem.asText();
				result.add(subj);
			}
			return result;		
		}catch(Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	// Task 6 CourseSFQ
	/**
	 * A function to scrape the data from the baseurl link. The function will go to the baseurl link to scrape
	 * all the average score of each enrolled courses
	 * @param baseurl
	 * @param enrollList
	 * @return null
	 */
	public List<Course> scrapeSFQ(String baseurl, TableList [] enrollList){
		try {
			HtmlPage page = client.getPage(baseurl);
			List<?> items = (List<?>) page.getByXPath("//table");          // Total 13 tables
			Vector<TableList>result = new Vector<TableList>();
		
			if(items.isEmpty()) {
				return null;
			}
	
			for(int i = 0; i < items.size(); i++) {
				HtmlElement item = (HtmlElement) items.get(i);
				List<?> namelist = item.getByXPath(".//td[contains(@colspan,'3')]");				

				for (int j = 0; j < namelist.size(); j++) {
					HtmlElement list = (HtmlElement) namelist.get(j);
					String scrapeText = list.asText();
					int scrapeSize = scrapeText.length();
			
					for (int k = 0 ; k < 100 ; k++) {		

						if (list.asText().substring(10,11) != " ") {
							
							if (list.asText().substring(1).equals(enrollList[k].getCourseCode() + " ")) {
								int sectionOfSFQCourse = 1;
								double L2_Score = 0;
								double L3_Score = 0;
								double L4_Score = 0;
								double L1_Score = Double.parseDouble(list.getParentNode().getNextElementSibling().getChildNodes().get(7).asText().substring(0,4));
								
								if (list.getParentNode().getNextElementSibling().getNextElementSibling().getNextElementSibling().getChildNodes().get(3).asText().substring(1).equals("L2 ")) {
									L2_Score = Double.parseDouble(list.getParentNode().getNextElementSibling().getNextElementSibling().getNextElementSibling().getChildNodes().get(7).asText().substring(0,4));
									sectionOfSFQCourse++;
								}
								
								if (list.getParentNode().getNextElementSibling().getNextElementSibling().getNextElementSibling().getNextElementSibling().getNextElementSibling().getChildNodes().get(3).asText().substring(1).equals("L3 ")) {							
									L3_Score = Double.parseDouble(list.getParentNode().getNextElementSibling().getNextElementSibling().getNextElementSibling().getNextElementSibling().getNextElementSibling().getChildNodes().get(7).asText().substring(0,4));
									sectionOfSFQCourse++;
								}
								
								if (list.getParentNode().getNextElementSibling().getNextElementSibling().getNextElementSibling().getNextElementSibling().getNextElementSibling().getNextElementSibling().getNextElementSibling().getChildNodes().get(3).asText().substring(1).equals("L4 ")) {
									L4_Score = Double.parseDouble(list.getParentNode().getNextElementSibling().getNextElementSibling().getNextElementSibling().getNextElementSibling().getNextElementSibling().getNextElementSibling().getNextElementSibling().getChildNodes().get(7).asText().substring(0,4));
									sectionOfSFQCourse++;
								}
								
								double scoreOfSFQ = (L1_Score + L2_Score + L3_Score + L4_Score ) / sectionOfSFQCourse;
									
								enrollList[k].setcourseSFQScore(scoreOfSFQ);	
								enrollList[k].setcourseSFQ(true);
								
							}
						}
						
						if (list.asText().substring(1).equals(enrollList[k].getCourseCode())) {
							
							int sectionOfSFQCourse = 1;
							double L2_Score = 0;
							double L3_Score = 0;
							double L4_Score = 0;
							double L1_Score = Double.parseDouble(list.getParentNode().getNextElementSibling().getChildNodes().get(7).asText().substring(0,4));
							
							if (list.getParentNode().getNextElementSibling().getNextElementSibling().getNextElementSibling().getChildNodes().get(3).asText().substring(1).equals("L2 ")) {
								L2_Score = Double.parseDouble(list.getParentNode().getNextElementSibling().getNextElementSibling().getNextElementSibling().getChildNodes().get(7).asText().substring(0,4));
								sectionOfSFQCourse++;
							}
							
							if (list.getParentNode().getNextElementSibling().getNextElementSibling().getNextElementSibling().getNextElementSibling().getNextElementSibling().getChildNodes().get(3).asText().substring(1).equals("L3 ")) {							
								L3_Score = Double.parseDouble(list.getParentNode().getNextElementSibling().getNextElementSibling().getNextElementSibling().getNextElementSibling().getNextElementSibling().getChildNodes().get(7).asText().substring(0,4));
								sectionOfSFQCourse++;
							}
							
							if (list.getParentNode().getNextElementSibling().getNextElementSibling().getNextElementSibling().getNextElementSibling().getNextElementSibling().getNextElementSibling().getNextElementSibling().getChildNodes().get(3).asText().substring(1).equals("L4 ")) {
								L4_Score = Double.parseDouble(list.getParentNode().getNextElementSibling().getNextElementSibling().getNextElementSibling().getNextElementSibling().getNextElementSibling().getNextElementSibling().getNextElementSibling().getChildNodes().get(7).asText().substring(0,4));
								sectionOfSFQCourse++;
							}
							
							double scoreOfSFQ = (L1_Score + L2_Score + L3_Score + L4_Score ) / sectionOfSFQCourse;
						
							enrollList[k].setcourseSFQScore(scoreOfSFQ);	
							enrollList[k].setcourseSFQ(true);
							
						}

					}
				}
				

			}
		} catch (Exception e) {
		System.out.println(e);		
		}
		
		
	
		return null;
	}
	
	// Task 6 Instructor SFQ
	/**
	 * A function to scraped all the instructor's score from the baseUrl. It will automatically calculate
	 * the average score of every instructors
	 * @param baseurl
	 * @param insSFQ
	 * @return null
	 */
	public List<Course> scrapeInstructorSFQ(String baseurl, InstructorSFQ [] insSFQ){
		try {
			HtmlPage page = client.getPage(baseurl);
			List<?> items = (List<?>) page.getByXPath("//table");          // Total 13 tables
			Vector<TableList>result = new Vector<TableList>();
			int pos = 1;
		
			if(items.isEmpty()) {
				return null;
			}
	
			// section.getIns
			
			for(int i = 0; i < items.size(); i++) {
				HtmlElement item = (HtmlElement) items.get(i);
				List<?> namelist = item.getByXPath(".//td[contains(@colspan,'3')]");	

				for (int j = 0; j < (namelist.size() - 1); j++) {
					HtmlElement list = (HtmlElement) namelist.get(j);
					
					String name = list.getParentNode().getNextElementSibling().getNextElementSibling().getChildNodes().get(5).asText();
					String score = list.getParentNode().getNextElementSibling().getNextElementSibling().getChildNodes().get(9).asText().substring(0,4);
					String check_L2 = "";
					String check_L3 = "";
					String check_L4 = "";
					if (list.getParentNode().getNextElementSibling().getNextElementSibling().getNextElementSibling().getChildNodes() != null) {
						check_L2 = list.getParentNode().getNextElementSibling().getNextElementSibling().getNextElementSibling().getChildNodes().get(3).asText().substring(1);
						if (check_L2.equals("L2 ") ) {
							check_L3 = list.getParentNode().getNextElementSibling().getNextElementSibling().getNextElementSibling().getNextElementSibling().getNextElementSibling().getChildNodes().get(3).asText().substring(1);
							if (check_L3.equals("L3 ")) {
								check_L4 = list.getParentNode().getNextElementSibling().getNextElementSibling().getNextElementSibling().getNextElementSibling().getNextElementSibling().getNextElementSibling().getNextElementSibling().getChildNodes().get(3).asText().substring(1);
							}
						}
						
					}
					
					for (int k = 0; k < pos; k++) {	
						if (insSFQ[k].getname().equals(name)) {			
							
							if (score.equals("-(-)")) {	}	
							
							else {
								insSFQ[k].addscore(Double.parseDouble(score));
								insSFQ[k].addtimes(1);
								insSFQ[k].setexist(true);
								k = pos;
							}		
						}
						
						else if (k == (pos-1) & insSFQ[k].getname() != name) {					
							if (score.equals("-(-)")) {
								insSFQ[k].setexist(true);
								insSFQ[k].setname(name);							
								k = pos;
							}						
							else {
								insSFQ[k].setexist(true);
								insSFQ[k].setname(name);	
								insSFQ[k].addscore(Double.parseDouble(score));
								insSFQ[k].addtimes(1);
								k = pos;
							}
						}
					}
					
					if (pos > 300) { }
					else {
						pos++;
					}					
							
// L2					
					if (check_L2.equals("L2 ")) {
						String L2_name = list.getParentNode().getNextElementSibling().getNextElementSibling().getNextElementSibling().getNextElementSibling().getChildNodes().get(5).asText();
						String L2_score = list.getParentNode().getNextElementSibling().getNextElementSibling().getNextElementSibling().getNextElementSibling().getChildNodes().get(9).asText().substring(0,4);
						for (int k = 0; k < pos; k++) {
							if (insSFQ[k].getname().equals(L2_name)) {											
								if (score.equals("-(-)")) {	}	
								else {
									insSFQ[k].addscore(Double.parseDouble(L2_score));
									insSFQ[k].addtimes(1);
									insSFQ[k].setexist(true);
									k = pos;
								}		
							}
							
							else if (k == (pos-1) & insSFQ[k].getname() != L2_name) {
								if (score.equals("-(-)")) {
									insSFQ[k].setexist(true);
									insSFQ[k].setname(L2_name);							
									k = pos;
								}
								else {
									insSFQ[k].setexist(true);
									insSFQ[k].setname(L2_name);							
									insSFQ[k].addscore(Double.parseDouble(L2_score));
									insSFQ[k].addtimes(1);
									k = pos;
								}
						
							}
						}
						if (pos > 300) { }
						else {
							pos++;
						}
					}
 //L3				
					if (check_L3.equals("L3 ")) {
						String L3_name = list.getParentNode().getNextElementSibling().getNextElementSibling().getNextElementSibling().getNextElementSibling().getNextElementSibling().getNextElementSibling().getChildNodes().get(5).asText();
						String L3_score = list.getParentNode().getNextElementSibling().getNextElementSibling().getNextElementSibling().getNextElementSibling().getNextElementSibling().getNextElementSibling().getChildNodes().get(9).asText().substring(0,4);
						for (int k = 0; k < pos; k++) {
							if (insSFQ[k].getname().equals(L3_name)) {											
								if (score.equals("-(-)")) {	}									
								else {
									insSFQ[k].addscore(Double.parseDouble(L3_score));
									insSFQ[k].addtimes(1);
									insSFQ[k].setexist(true);
									k = pos;
								}		
							}							
							else if (k == (pos-1) & insSFQ[k].getname() != L3_name) {								
								if (score.equals("-(-)")) {
									insSFQ[k].setexist(true);
									insSFQ[k].setname(L3_name);							
									k = pos;
								}								
								else {
									insSFQ[k].setexist(true);
									insSFQ[k].setname(L3_name);							
									insSFQ[k].addscore(Double.parseDouble(L3_score));
									insSFQ[k].addtimes(1);
									k = pos;
								}						
							}
						}
						if (pos > 300) { }
						else {
							pos++;
						}
					}
					
// L4
					if (check_L4.equals("L4 ")) {
						String L4_name = list.getParentNode().getNextElementSibling().getNextElementSibling().getNextElementSibling().getNextElementSibling().getNextElementSibling().getNextElementSibling().getNextElementSibling().getNextElementSibling().getChildNodes().get(5).asText();
						String L4_score = list.getParentNode().getNextElementSibling().getNextElementSibling().getNextElementSibling().getNextElementSibling().getNextElementSibling().getNextElementSibling().getNextElementSibling().getNextElementSibling().getChildNodes().get(9).asText().substring(0,4);
						for (int k = 0; k < pos; k++) {			
							if (insSFQ[k].getname().equals(L4_name)) {										
								if (score.equals("-(-)")) {	}								
								else {
									insSFQ[k].addscore(Double.parseDouble(L4_score));
									insSFQ[k].addtimes(1);
									insSFQ[k].setexist(true);
									k = pos;
								}		
							}						
							else if (k == (pos-1) & insSFQ[k].getname() != L4_name) {							
								if (score.equals("-(-)")) {
									insSFQ[k].setexist(true);
									insSFQ[k].setname(L4_name);							
									k = pos;
								}
								else {
									insSFQ[k].setexist(true);
									insSFQ[k].setname(L4_name);							
									insSFQ[k].addscore(Double.parseDouble(L4_score));
									insSFQ[k].addtimes(1);
									k = pos;
								}
					
							}
						}
						if (pos > 300) { }
						else {
							pos++;
						}
					}
				}
			}
		} catch (Exception e) {
		System.out.println(e);		
		}
	
	
		return null;
	}
}
