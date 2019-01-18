// COURSE: CSCI1620
// TERM: FALL 2018
// 
// NAME: Robin Suda and Tyler Labreck
// RESOURCES: We used the javaDoc, lecture slides, and the book.
package cslibrary.cslibraryitems;

/**
 * An abstract definition of an item to be owned by a CS library. Contains all members common to all CS library items.
 * @author rsuda and tlabreck
 *
 */
public abstract class CSLibraryItem 
{

	/**
	 * Highest age group for recommendations. Has value of "High school".
	 */
	protected static final String AGE_GROUP_FOUR = "High school";

	/**
	 * Lowest age group for recommendations. Has value of "All ages".
	 */
	protected static final java.lang.String AGE_GROUP_ONE = "All ages";

	/**
	 * Third age group for recommendations. Has value of "Middle school".
	 */
	protected static final java.lang.String AGE_GROUP_THREE = "Middle school";

	/**
	 * Second age group for recommendations. Has value of "Elementary".
	 */
	protected static final java.lang.String AGE_GROUP_TWO = "Elementary";
	
	/**
	 * The name of the library item being used.
	 */
	private String name;
	
	/**
	 * If the item is available.
	 */
	private boolean available;

	/**
	 * New CS library items have their name set and are not checked out.
	 * 
	 * @param nameIn Name for the CS library item. Null names will be set to "Unnamed".
	 */
	public CSLibraryItem(java.lang.String nameIn) 
	{
		available = true;
		setName(nameIn);
	}

	/**
	 * Determines the recommended age group for the CS library item.
	 * @return The CS library item's recommended age group.
	 */
	public abstract String ageRecommendation();
	
	/**
	 * Determines what the CS library item should be alphabetized by.
	 * @return The String that determines order for the CS library item.
	 */
	public abstract String alphabetizeBy();
	
	/**
	 * Returns the number of weeks the CS library item can be checked out for.
	 * @return Number of weeks the CS library item can be on loan.
	 */
	public abstract int loanPeriod();
	
	/**
	 * Set the name for the library item.
	 * @param nameIn Name for the library Item. Null names will be set to "Unnamed".
	 */
	public void setName(String nameIn)
	{
		if (nameIn != null)
		{
			name = nameIn;
		}
		else
		{
			name = "Unnamed";
		}
	}
	
	/**
	 * Returns the name of the CS library item.
	 * @return Name of the CS library item.
	 */
	public java.lang.String getName()
	{
		return name;
	}
	
	/**
	 * Attempts to check out the CS library item based on current checked out state. 
	 * Items with a loan period of zero cannot be checked out.
	 * @return True if the item is successfully checked out, false otherwise.
	 */
	public boolean checkOutItem()
	{
		boolean checkOut = true;
		if (loanPeriod() == 0 || !available)
		{
			checkOut = false;
		}
		else
		{
			checkOut = true;
			available = false;
		}
		return checkOut;
	}
	
	/**
	 * Attempts to return the CS library item based on current checked out state.
	 * @return True if the item is successfully returned, false otherwise.
	 */
	public boolean returnItem()
	{
		if (isCheckedOut())
		{
			available = true;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Returns if the CS library item is currently checked out.
	 * @return True if the item is checked out, false if it is available.
	 */
	public boolean isCheckedOut()
	{
		return !available;
	}
	
	/**
	 * String representation of a CS library item. Returned String is of the form: 
	 * NAME
	 * 
	 * Status: AVAILABILITY
	 * 
	 * Loan: LOAN_PERIOD weeks
	 * 
	 * Age Recommendation: AGE_GROUP
	 * 
	 * Where NAME is the name of the CS library item, 
	 * AVAILABLITY is either "Unavailable" or "Available",LOAN_PERIOD 
	 * is the number of weeks the item can be checked out, and AGE_GROUP 
	 * is the recommended age group for the CS library item.
	 * 
	 * Overrides: toString in class java.lang.Object
	 * 
	 * @return String of the CS library item.
	 * */
	public String toString()
	{
		String x = "";
		if (available)
		{
			x = "Available";
		}
		else
		{
			x = "Unavailable";
		}
		return getName() + "\nStatus: " + x + "\nLoan: " + loanPeriod() + " weeks"
				+ "\nAge Recommendation: " + ageRecommendation();
	}
	
	/**
	 * Equality of CS library items is based solely on the names being the same.
	 * Overrides: equals in class java.lang.Object
	 * @param other - Reference to the Object to be tested for equality.
	 * @return True if the two CS library items are equal, false if they are not.
	 */
	public boolean equals(java.lang.Object other)
	{
		if (other instanceof CSLibraryItem)
		{
			return this.getName().equals(((CSLibraryItem) other).getName());
		}
		return false;
	}
	
	
	

}
