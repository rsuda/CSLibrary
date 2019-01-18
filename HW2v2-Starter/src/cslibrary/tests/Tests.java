// COURSE: CSCI1620
// TERM: FALL 2018
// 
// NAME: Robin Suda and Tyler Labreck
// RESOURCES: We used the javaDoc, lecture slides, and the book.
package cslibrary.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import cslibrary.cslibraryitems.DevBoard;
import cslibrary.cslibraryitems.Robot;
import cslibrary.cslibraryitems.Textbook;
import cslibrary.utils.CSLibraryItemCollection;

public class Tests 
{

	//
	//
	//TESTS FOR THE TEXTBOOK CLASS.
	//
	//
	
	@Test
	public void testSetAuthor() 
	{
		Textbook a = new Textbook("Java", "Reed", 20);
		assertEquals("Reed", a.getAuthor());
		
		Textbook b = new Textbook("Java", null, 20);
		assertEquals("None", b.getAuthor());
	}

	@Test
	public void testSetPages() 
	{
		Textbook a = new Textbook("Java", "Reed", 20);
		assertEquals(20, a.getNumPages());
		
		Textbook b = new Textbook("Java", "Reed", -5);
		assertEquals(0, b.getNumPages());
	}
	
	@Test
	public void testToStringTextbook() 
	{
		Textbook a = new Textbook("Java", "Reed", 20);
		Textbook b = new Textbook("Java", "Reed", -1);

		assertEquals("Java" + 
				"\nStatus: Available" + 
				"\nLoan: 2 weeks" + 
				"\nAge Recommendation: All ages" +  
				"\nAuthor: Reed" + 
				"\nPage count: 20", a.toString());
		assertEquals("Java" + 
				"\nStatus: Available" + 
				"\nLoan: 2 weeks" + 
				"\nAge Recommendation: No Recommendation" +  
				"\nAuthor: Reed" + 
				"\nPage count: Unknown", b.toString());
	}
	
	@Test
	public void testAlphabetizeTextbook() 
	{
		Textbook a = new Textbook("Java", "Reed", 20);
		assertEquals("Reed Java", a.alphabetizeBy());
		
		Textbook b = new Textbook(null, "Reed", -5);
		assertEquals("Reed Unnamed", b.alphabetizeBy());
	}
	
	@Test
	public void testTextBookAgeRecommendation() 
	{
		Textbook a = new Textbook("Java", "Reed", 450);
		assertEquals("High school", a.ageRecommendation());
		
		Textbook b = new Textbook(null, "Reed", 350);
		assertEquals("Middle school", b.ageRecommendation());
		
		Textbook c = new Textbook(null, "Reed", 75);
		assertEquals("Elementary", c.ageRecommendation());
		
		Textbook d = new Textbook(null, "Reed", 25);
		assertEquals("All ages", d.ageRecommendation());
		
		Textbook e = new Textbook(null, "Reed", 0);
		assertEquals("No Recommendation", e.ageRecommendation());
	}
	
	@Test
	public void testLoanTextBook() 
	{
		Textbook a = new Textbook("Java", "Reed", 20);
		assertEquals(2, a.loanPeriod());
	}
	
	//
	//
	//TESTS FOR THE ROBOT CLASS.
	//
	//
	
	@Test
	public void testGetReleaseInfo()
	{
		Robot a = new Robot("Wallee", "2018_3.5");
		assertEquals("2018_3.5", a.getReleaseInfo());
	}
	
	@Test
	public void testToStringRobot() 
	{
		Robot a = new Robot("Wallee", "2018_3.5");
		Robot b = new Robot(null, "2018_3.5");
		assertEquals("Wallee" + 
				"\nStatus: Available" + 
				"\nLoan: 0 weeks" + 
				"\nAge Recommendation: Elementary" +
				"\nRelease Info: 2018_3.5"
				, a.toString());
		assertEquals("Unknown" + 
				"\nStatus: Available" + 
				"\nLoan: 0 weeks" + 
				"\nAge Recommendation: Elementary" +
				"\nRelease Info: 2018_3.5"
				, b.toString());
	}
	
	@Test
	public void testAlphabetizeRobot() 
	{
		Robot a = new Robot("Wallee", "2018_3.5");
		assertEquals("Wallee 2018_3.5", a.alphabetizeBy());
		
		Robot b = new Robot(null, "2018_3.5");
		assertEquals("Unknown 2018_3.5", b.alphabetizeBy());
}
	
	@Test
	public void testAgeRecommendationRobot()
	{
		Robot a = new Robot("Wallee", "2018_3.5");
		assertEquals("Elementary", a.ageRecommendation());
	}
	
	@Test
	public void testLoanRobot() 
	{
		Robot a = new Robot("Wallee", "2018_3.5");
		assertEquals(0, a.loanPeriod());
	}
	
	//
	//
	//TESTS FOR THE DEVBOARD CLASS.
	//
	//
	
	@Test
	public void testSetDifficulty() 
	{
		DevBoard a = new DevBoard("Geralt", "Beginner");
		DevBoard b = new DevBoard("Geralt", null);
		assertEquals("Beginner", a.getDifficultyRating());
		assertEquals("Unknown", b.getDifficultyRating());
	}
	
	@Test
	public void testDevToStringy() 
	{
		DevBoard a = new DevBoard("Geralt", "Intermediate");
		assertEquals("Geralt" + 
				"\nStatus: Available" + 
				"\nLoan: 1 weeks" + 
				"\nAge Recommendation: Elementary" +   
				"\nDifficulty rating: Intermediate", a.toString());
	}
	
	@Test
	public void testAlphabetizeDev() 
	{
		DevBoard a = new DevBoard("Geralt", "Advanced");
		assertEquals("Geralt", a.alphabetizeBy());
		
		DevBoard b = new DevBoard(null, "Expert");
		assertEquals("Unnamed", b.alphabetizeBy());
	}
	
	@Test
	public void testLoanDev() 
	{
		DevBoard a = new DevBoard("Geralt", "Advanced");
		assertEquals(1, a.loanPeriod());
	}
	
	@Test
	public void testAgeRecommendationDev() 
	{
		DevBoard a = new DevBoard("Geralt", "Beginner");
		assertEquals("All ages", a.ageRecommendation());
		
		DevBoard b = new DevBoard("Geralt", "Intermediate");
		assertEquals("Elementary", b.ageRecommendation());
		
		DevBoard c = new DevBoard("Geralt", "Advanced");
		assertEquals("Middle school", c.ageRecommendation());
		
		DevBoard d = new DevBoard("Geralt", "Expert");
		assertEquals("High school", d.ageRecommendation());
		
		DevBoard e = new DevBoard("Geralt", "What?");
		assertEquals("No Recommendation", e.ageRecommendation());
	}
	
	//
	//
	//TESTS FOR THE CSLIBRARY CLASS.
	//
	//
	
	@Test
	public void testCheckOutItem() 
	{
		DevBoard a = new DevBoard("Geralt", "Beginner");
		assertEquals(true, a.checkOutItem());
		
		Robot b = new Robot("Wallee", "2018_3.5");
		assertEquals(false, b.checkOutItem());
		
		DevBoard c = new DevBoard("Geralt", "Advanced");
		c.checkOutItem();
		assertEquals(false, c.checkOutItem());
		
		DevBoard d = new DevBoard("Geralt", "Expert");
		assertEquals(false, d.isCheckedOut());
	}
	
	@Test
	public void testReturnItem() 
	{
		DevBoard a = new DevBoard("Geralt", "Beginner");
		a.checkOutItem();
		assertEquals(true, a.returnItem());
		
		DevBoard c = new DevBoard("Geralt", "Advanced");
		assertEquals(false, c.returnItem());
	}
	
	@Test
	public void testDevToStringyUnavailable() 
	{
		DevBoard a = new DevBoard("Geralt", "Intermediate");
		a.checkOutItem();
		assertEquals("Geralt" + 
				"\nStatus: Unavailable" + 
				"\nLoan: 1 weeks" + 
				"\nAge Recommendation: Elementary" +   
				"\nDifficulty rating: Intermediate", a.toString());
	}
	
	@Test
	public void testEqualsLibrary()
	{
		DevBoard a = new DevBoard("Geralt", "Intermediate");
		DevBoard b = new DevBoard("Geralt", "Advanced");
		assertEquals(true, a.equals(b));
		
		DevBoard c = new DevBoard("Chris", "Advanced");
		assertEquals(false, a.equals(c));
	}
	
	//
	//
	// TEST LIBRARYITEMSCOLLECTIONS
	//
	//
	DevBoard steve = new DevBoard("Steve", "Advanced");
	Robot wallee = new Robot("Wallee", "2018_3.5");
	Textbook history = new Textbook("History", "Reed", 450);
	Textbook wrongHistory = new Textbook("WrongHistory", "Reed", 450);
	
	@Test
	public void testGetNumberOfItems()
	{
		CSLibraryItemCollection test = new CSLibraryItemCollection();
		test.add(steve);
		test.add(steve);
		test.add(history);
		test.add(steve);
		test.add(wallee);
		test.add(history);
		test.add(history);
		test.add(history);
		test.add(wallee);
		assertEquals(3, test.getNumberOfItems(2));
		assertEquals(2, test.getNumberOfItems(3));
		assertEquals(4, test.getNumberOfItems(1));
		assertEquals(9, test.getNumberOfItems(0));
		assertEquals(-1, test.getNumberOfItems(9));
	}
	
	@Test
	public void testAddItem()
	{
		CSLibraryItemCollection test = new CSLibraryItemCollection();
		test.add(steve);
		test.add(steve);
		test.add(history);
		test.add(steve);
		test.add(wallee);
		test.add(history);
		test.add(history);
		test.add(history);
		test.add(wallee);
		test.add(wallee);
		assertEquals(false, test.add(steve));
	}
	
	@Test
	public void testFindItem()
	{
		CSLibraryItemCollection test = new CSLibraryItemCollection();
		test.add(steve);
		test.add(steve);
		test.add(history);
		test.add(steve);
		test.add(wallee);
		test.add(history);
		test.add(history);
		test.add(wallee);
		test.add(wallee);
		test.remove(2);
		assertEquals(5, test.find(history));
		assertEquals(-1, test.find(wrongHistory));
		assertEquals(-1, test.find(null));
	}
	
	@Test
	public void testRemoveIndexItem()
	{
		CSLibraryItemCollection test = new CSLibraryItemCollection();
		test.add(steve);
		test.add(steve);
		test.add(history);
		test.add(steve);
		test.add(wallee);
		test.add(history);
		test.add(history);
		test.add(history);
		test.add(wallee);
		test.add(wallee);
		assertEquals(true, test.remove(2));
		assertEquals(false, test.remove(11));
		assertEquals(false, test.remove(-1));
	}
	
	@Test
	public void testRemoveObjectItem()
	{
		CSLibraryItemCollection test = new CSLibraryItemCollection();
		test.add(steve);
		test.add(steve);
		test.add(history);
		test.add(steve);
		test.add(wallee);
		test.add(history);
		test.add(history);
		test.add(history);
		test.add(wallee);
		test.add(wallee);
		assertEquals(true, test.remove(history));
		assertEquals(false, test.remove(wrongHistory));
	}
	
	@Test
	public void testGetObjectItem()
	{
		CSLibraryItemCollection test = new CSLibraryItemCollection();
		test.add(steve);
		test.add(steve);
		test.add(history);
		test.add(steve);
		test.add(wallee);
		test.add(history);
		test.add(history);
		test.add(history);
		test.add(wallee);
		test.add(wallee);
		assertEquals(history, test.get(2));
		assertEquals(null, test.get(11));
		assertEquals(null, test.get(-1));
	}
	
	@Test
	public void testSort()
	{
		CSLibraryItemCollection test = new CSLibraryItemCollection();
		test.add(steve);
		test.add(steve);
		test.add(history);
		test.add(steve);
		test.add(wallee);
		test.add(history);
		test.add(null);
		test.add(history);
		test.add(wallee);
		test.add(wallee);
		test.sortCollection();
		assertEquals(history, test.get(2));
		assertEquals(steve, test.get(5));
		assertEquals(wallee, test.get(6));
		assertEquals(null, test.get(10));
	}
	
	@Test
	public void testGenerateRobots()
	{
		CSLibraryItemCollection test = new CSLibraryItemCollection();
		test.add(steve);
		test.add(steve);
		test.add(history);
		test.add(steve);
		test.add(wallee);
		test.add(history);
		test.add(null);
		test.add(wallee);
		test.add(history);
		test.add(wallee);
		CSLibraryItemCollection robots = test.generateSubCollection(3);
		CSLibraryItemCollection devboard = test.generateSubCollection(2);
		CSLibraryItemCollection textbook = test.generateSubCollection(1);
		CSLibraryItemCollection alltypes = test.generateSubCollection(0);
		assertEquals(wallee, robots.get(0));
		assertEquals(wallee, robots.get(1));
		assertEquals(wallee, robots.get(2));
		assertEquals(steve, devboard.get(0));
		assertEquals(steve, devboard.get(1));
		assertEquals(steve, devboard.get(2));
		assertEquals(history, textbook.get(0));
		assertEquals(history, textbook.get(1));
		assertEquals(history, textbook.get(2));
		assertEquals(null, test.generateSubCollection(5));
		assertEquals(null, test.generateSubCollection(-1));
		assertEquals(9, alltypes.getNumberOfItems(0));
	}

}
