// COURSE: CSCI1620
// TERM: FALL 2018
// 
// NAME: Robin Suda and Tyler Labreck
// RESOURCES: We used the javaDoc, lecture slides, and the book.
package cslibrary.cslibraryitems;

/**
 * Textbook is a CS library item containing information needed for a CS library's records.
 * @author rsuda and tlabreck
 *
 */
public class Textbook extends CSLibraryItem 
{
	/**
	 * Max pages a textbook can have.
	 */
	private static final int MAX_PAGES = 400;
	
	/**
	 * Mid pages a textbook can have.
	 */
	private static final int MID_PAGES = 100;
	
	/**
	 * Min pages a textbook can have.
	 */
	private static final int MIN_PAGES = 50;
	
	/**
	 * Variable to store the author.
	 */
	private String author;
	
	/**
	 * Variable to store the number of pages a textbook has.
	 */
	private int numPages;
	
	/**
	 * New textbooks will set their name, author, and number of pages.
	 * @param nameIn Name for the textbook. Null names will be set to "Unnamed".
	 * @param authorIn Author of the textbook. Null authors will be set to "None".
	 * @param numPagesIn Pages in the textbook. Textbooks created with less than one page will 
	 * have the numberof pages set to 0, denoting the number of pages is unknown.
	 */
	public Textbook(java.lang.String nameIn, java.lang.String authorIn, int numPagesIn)
	{
		super(nameIn);
		setAuthor(authorIn);
		setNumPages(numPagesIn);
	}
	
	/**
	 * Set the author for the textbook.
	 * @param authorIn Author for the textbook. Null authors will be set to "None".
	 */
	public void setAuthor(java.lang.String authorIn)
	{
		if (authorIn != null)
		{
			author = authorIn;
		}
		else
		{
			author = "None";
		}
	}
	
	/**
	 * Returns the author of the textbook.
	 * @return Author of the textbook.
	 */
	public java.lang.String getAuthor()
	{
		return author;
	}
	
	/**
	 * Set the number of pages for the textbook.
	 * @param numPagesIn Pages in the textbook. Textbooks created with less than 
	 * one page will have the number of pages set to zero, denoting the number of 
	 * pages is unknown.
	 */
	public void setNumPages(int numPagesIn)
	{
		if (numPagesIn < 1)
		{
			numPages = 0;
		}
		else
		{
			numPages = numPagesIn;
		}
	}
	
	/**
	 * Returns the number of pages in the textbook.
	 * @return Number of pages in the textbook. A zero value denotes the number 
	 * of pages is unknown.
	 */
	public int getNumPages()
	{
		return numPages;
	}
	
	/**
	 * String representation of the textbook. Returned String is of the form:
	 * NAME
	 * 
	 * Status: AVAILABILITY
	 * 
	 * Loan: LOAN_PERIOD weeks
	 * 
	 * Age Recommendation: AGE_GROUP
	 * 
	 * Author: AUTHOR
	 * 
	 * Page count: PAGES
	 * 
	 * Where NAME is the name of the textbook, AVAILABLITY is either "Unavailable" or 
	 * "Available",LOAN_PERIOD is the number of weeks the textbook can be checked out, 
	 * AGE_GROUP is the recommended agegroup for the textbook, AUTHOR is the author of 
	 * the textbook, and PAGES is the number of pages if known,otherwise "Unknown".
	 * 
	 * @return String of the book.
	 */
	public java.lang.String toString()
	{
		String alt = "";
		if (numPages == 0)
		{
			alt = "Unknown";
			return super.toString() + "\nAuthor: " + author + "\nPage count: " + alt;
		}
		else 
		{
			return super.toString() + "\nAuthor: " + author + "\nPage count: " + numPages;

		}
	}
	
	/**
	 * Textbooks are alphabetized primarily by author, then name.
	 * @return String in the form: "AUTHOR NAME".
	 */
	public java.lang.String alphabetizeBy()
	{
		return author + " " + super.getName();
	}
	
	/**
	 * Returns the recommended age group for the textbook. Textbook age group recommendations 
	 * are as follows:
	 * 50 pages or less: AGE_GROUP_ONE
	 * 
	 * 100 pages or less: AGE_GROUP_TWO
	 * 
	 * 400 pages or less: AGE_GROUP_THREE
	 * 
	 * Unlimited pages: AGE_GROUP_FOUR
	 * 
	 * Based upon the number of pages, returns the lowest age recommendation. If the number 
	 * of pages is unknown, returns "No Recommendation".
	 * @return The textbook's recommended age group.
	 */
	public java.lang.String ageRecommendation()
	{
		String ageGroup = "";
		//MAX_PAGES = 400
		if (numPages > MAX_PAGES)
		{
			ageGroup = AGE_GROUP_FOUR;
		}
		//MID_PAGES = 100
		else if (numPages > MID_PAGES)
		{
			ageGroup = AGE_GROUP_THREE;
		}
		//MIN_PAGES = 50
		else if (numPages > MIN_PAGES)
		{
			ageGroup = AGE_GROUP_TWO;
		}
		//MIN_PAGES = 50
		else if (numPages <= MIN_PAGES && numPages > 0)
		{
			ageGroup = AGE_GROUP_ONE;
		}
		//numPages = 0
		else
		{
			ageGroup = "No Recommendation";
		}
		return ageGroup;
	}
	
	/**
	 * Textbooks may be checked out for two weeks.
	 * @return Number of weeks the textbook can be on loan.
	 */
	public int loanPeriod()
	{
		return 2;
	}
}
