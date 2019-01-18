// COURSE: CSCI1620
// TERM: FALL 2018
// 
// NAME: Robin Suda and Tyler Labreck
// RESOURCES: We used the javaDoc, lecture slides, and the book.
package cslibrary.cslibraryitems;

/**
 * Robot is a CS library item containing information needed for a CS library's records.
 * @author rsuda and tlabreck
 *
 */
public class Robot extends CSLibraryItem
{
	/**
	 * The name of the robot.
	 */
	private String modelName;
	
	/**
	 * The release information.
	 */
	private String releaseInfo;
	/**
	 * New robots will set their model name and release information.
	 * @param modelNameIn Model name of the robot. Null model names will be set to "Unknown".
	 * @param releaseInfoIn release date and software version of the robot in the form: "YYYY_VV.VV".
	 */
	public Robot(java.lang.String modelNameIn, java.lang.String releaseInfoIn)
	{
		super(modelNameIn);
		setReleaseInfo(releaseInfoIn);
	}
	
	/**
	 * Sets the release info of the robot in the form: "YYYY_VV.VV". Release info that do not match 
	 * this format may not alphabetize correctly.
	 * @param releaseInfoIn Release info of the robot.
	 */
	public void setReleaseInfo(java.lang.String releaseInfoIn)
	{
		releaseInfo = releaseInfoIn;
	}
	
	/**
	 * Returns the robot's release info.
	 * @return The robot's release info.
	 */
	public java.lang.String getReleaseInfo()
	{
		return releaseInfo;
	}
	
	/**
	 * String representation of a robot. Returned String is of the form: 
	 * NAME
	 * 
	 * Status: AVAILABILITY
	 * 
	 * Loan: LOAN_PERIOD weeks
	 * 
	 * Age Recommendation: AGE_GROUP
	 * 
	 * Release Info: RELEASE_INFO
	 * 
	 * Where NAME is the model name of the robot, AVAILABLITY is either "Unavailable" 
	 * or "Available",LOAN_PERIOD is the number of weeks the robot can be checked out, 
	 * AGE_GROUP is the recommended agegroup for the robot, and RELEASE_INFO is the 
	 * release information of the robot.
	 * @return String of the robot.
	 */
	public java.lang.String toString()
	{
		return super.toString() + "\nRelease Info: " + getReleaseInfo();
	}
	
	/**
	 * robots are alphabetized primarily by model name, then release info.
	 * @return String in the form: "NAME RELEASE_INFO".
	 */
	public java.lang.String alphabetizeBy()
	{
		return modelName + " " + getReleaseInfo();
	}
	
	/**
	 * All robots are recommended for AGE_GROUP_TWO.
	 * @return AGE_GROUP_TWO.
	 */
	public java.lang.String ageRecommendation()
	{
		return AGE_GROUP_TWO;
	}
	
	/**
	 * robots may not be checked out.
	 * @return Returns zero to indicate they cannot be out on loan.
	 */
	public int loanPeriod()
	{
		return 0;
	}
	
	/**
	 * Set the name for the Robot item.
	 * @param nameIn Takes in the name of the model for the Robot.
	 */
	public void setName(String nameIn)
	{
		if (nameIn != null)
		{
			modelName = nameIn;
		}
		else if (nameIn == null)
		{
			modelName = "Unknown";
		}
	}
	
	/**
	 * Get the name for the Robot item.
	 * @return modelName Returns the name of the Robot.
	 */
	public java.lang.String getName()
	{
		return modelName;
	}
}
