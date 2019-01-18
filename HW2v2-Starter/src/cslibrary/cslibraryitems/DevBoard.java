// COURSE: CSCI1620
// TERM: FALL 2018
// 
// NAME: Robin Suda and Tyler Labreck
// RESOURCES: We used the javaDoc, lecture slides, and the book.
package cslibrary.cslibraryitems;

/**
 * Dev board is a CS library item containing information needed for a CS library's records.
 * @author rsuda and tlabreck
 *
 */
public class DevBoard extends CSLibraryItem
{
	/**
	 * Rating for the DevBoard.
	 */
	private String difficultyRating;
	
	/**
	 * New dev board's will set their name and difficulty rating.
	 * @param nameIn Name of the dev board. Null names will be set to "Unnamed".
	 * @param ratingIn Difficulty rating of the dev board. Valid ratings are "Beginner", 
	 * "Intermediate", "Advanced", and "Expert".Any other provided difficulty rating 
	 * will be set to "Unknown".
	 */
	public DevBoard(java.lang.String nameIn, java.lang.String ratingIn)
	{
		super(nameIn);
		setDifficultyRating(ratingIn);
	}
	
	/**
	 * Set the difficulty rating of the dev board.
	 * @param difficultyRatingIn Difficulty rating of the dev board. Valid ratings are 
	 * "Beginner", "Intermediate", "Advanced", and "Expert".Any other provided 
	 * difficulty rating will be set to "Unknown".
	 */
	public void setDifficultyRating(java.lang.String difficultyRatingIn)
	{
		if (difficultyRatingIn == null)
		{
			difficultyRating = "Unknown";
		}
		else if (difficultyRatingIn.equals("Beginner") 
				|| difficultyRatingIn.equals("Intermediate") 
				|| difficultyRatingIn.equals("Advanced") || difficultyRatingIn.equals("Expert"))
		{
			difficultyRating = difficultyRatingIn;
		}
		else
		{
			difficultyRating = "Unknown";
		}
	}
	
	/**
	 * Returns the dev board's difficulty rating.
	 * @return The dev board's difficulty rating
	 */
	public java.lang.String getDifficultyRating()
	{
		return difficultyRating;
	}
	
	/**
	 * String representation of a dev board. Returned String is of the form: 
	 * NAME
	 * 
	 * Status: AVAILABILITY
	 * 
	 * Loan: LOAN_PERIOD weeks
	 * 
	 * Age Recommendation: AGE_GROUP
	 * 
	 * Difficulty rating: DIFFICULTY_RATING
	 * 
	 * Where NAME is the name of the dev board, AVAILABLITY is either "Unavailable" or 
	 * "Available",LOAN_PERIOD is the number of weeks the item can be checked out, 
	 * AGE_GROUP is the recommended agegroup for the dev board, and DIFFICULTY_RATING 
	 * is the difficulty rating of the dev board.
	 * @return String of the dev board.
	 */
	public java.lang.String toString()
	{
		return super.toString() + "\n" + "Difficulty rating: " + difficultyRating;
	}
	
	/**
	 * dev boards are alphabetized by name.
	 * @return The name of the dev board.
	 */
	public java.lang.String alphabetizeBy()
	{
		return super.getName();
	}
	
	/**
	 * Returns the recommended age group for the dev board. Dev board age group recommendations are as follows:
	 * "Beginner": AGE_GROUP_ONE
	 * 
	 * "Intermediate": AGE_GROUP_TWO
	 * 
	 * "Advanced": AGE_GROUP_THREE
	 * 
	 * "Expert": AGE_GROUP_FOUR
	 * 
	 * Based upon the difficulty rating, returns the appropriate age recommendation. If the difficulty rating 
	 * is unknown,returns "No Recommendation".
	 * @return The dev board's recommended age group.
	 */
	public java.lang.String ageRecommendation()
	{
		String recommendation = "";
		if (difficultyRating.equals("Beginner"))
		{
			recommendation = AGE_GROUP_ONE;
		}
		else if (difficultyRating.equals("Intermediate"))
		{
			recommendation = AGE_GROUP_TWO;
		}
		else if (difficultyRating.equals("Advanced"))
		{
			recommendation = AGE_GROUP_THREE;
		}
		else if (difficultyRating.equals("Expert"))
		{
			recommendation = AGE_GROUP_FOUR;
		}
		else
		{
			recommendation = "No Recommendation";
		}
		return recommendation;
	}
	
	/**
	 * dev boards may be checked out for one week.
	 * @return Number of weeks the dev board can be on loan.
	 */
	public int loanPeriod()
	{
		return 1;
	}
}