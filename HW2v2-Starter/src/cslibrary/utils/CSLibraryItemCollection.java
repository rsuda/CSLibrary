// COURSE: CSCI1620
// TERM: FALL 2018
// 
// NAME: Robin Suda and Tyler Labreck
// RESOURCES: We used the javaDoc, lecture slides, the book, and Devin Kellog from the CSLearning Center.
package cslibrary.utils;

import cslibrary.cslibraryitems.CSLibraryItem;
import cslibrary.cslibraryitems.DevBoard;
import cslibrary.cslibraryitems.Robot;
import cslibrary.cslibraryitems.Textbook;

/**
 * A collection of CSLibraryItems with a maximum capacity of ten. 
 * CSLibraryItems are stored in an array;all current items in the 
 * collection are stored contiguously, that is no gaps between them.
 * @author rsuda and tlabreck
 *
 */
public class CSLibraryItemCollection 
{	
	/**
	 * Constant value representing all types of items to be used 
	 * withgetNumberOfItems and generateSubCollection.
	 */
	public static final int ALL_TYPES = 0;
	
	/**
	 * Constant value representing Textbook items to be used withgetNumberOfItems 
	 * and generateSubCollection.
	 */
	public static final int TEXTBOOK = 1;
	
	/**
	 * Constant value representing DevBoard items to be used withgetNumberOfItems 
	 * and generateSubCollection.
	 */
	public static final int DEV_BOARD = 2;
	
	/**
	 * Constant value representing Robot items to be used withgetNumberOfItems and generateSubCollection.
	 */
	public static final int ROBOT = 3;
	
	/**
	 * Maximum items in array.
	 */
	private static final int MAX_ITEMS = 10;
	
	/**
	 * Array of all library items stored.
	 */
	private CSLibraryItem[] libraryItems;
	
	/**
	 * Counts number of items currently in array.
	 */
	private int countItems;
	
	/**
	 * Creates a new CSLibraryItemCollection.
	 */
	public CSLibraryItemCollection()
	{
		countItems = 0;
		libraryItems = new CSLibraryItem[MAX_ITEMS];
	}

	/**
	 * Returns the current number of CSLibraryItems of the requested type stored in the collection.If an 
	 * invalid value for type is given -1 will be returned to indicate invalid type.
	 * @param type The type of CSLibraryItem to be counted as determined by the static value passed.
	 * @return Current number of items of requested type in the collection or -1 if invalid type given.
	 */
	public int getNumberOfItems(int type)
	{
		int count = 0;
		if (type == ALL_TYPES)
		{
			count = helperAllTypes();
		}
		else if (type == TEXTBOOK)
		{
			count = helperTextbook();
		}
		else if (type == DEV_BOARD)
		{
			count = helperDevBoard();
		}
		else if (type == ROBOT)
		{
			count = helperRobot();
		}
		else
		{
			count = -1;
		}
		return count;
	}
	
	/**
	 * Helper method for getNumberOfItems for robot.
	 * @return count of the items.
	 */
	private int helperRobot()
	{
		int count = 0;
		for (int i = 0; i < libraryItems.length; i++)
		{
			if (libraryItems[i] instanceof Robot)
			{
				count++;
			}
		}
		return count;
	}
	
	/**
	 * Helper method for getNumberOfItems for devBoard.
	 * @return count of the items.
	 */
	private int helperDevBoard()
	{
		int count = 0;
		for (int i = 0; i < libraryItems.length; i++)
		{
			if (libraryItems[i] instanceof DevBoard)
			{
				count++;
			}
		}
		return count;
	}
	
	/**
	 * Helper method for getNumberOfItems for textbook.
	 * @return count of the items.
	 */
	private int helperTextbook()
	{
		int count = 0;
		for (int i = 0; i < libraryItems.length; i++)
		{
			if (libraryItems[i] instanceof Textbook)
			{
				count++;
			}
		}
		return count;
	}
	
	/**
	 * Helper method for getNumberOfItems for all types.
	 * @return count of the items.
	 */
	private int helperAllTypes()
	{
		int count = 0;
		for (int i = 0; i < libraryItems.length; i++)
		{
			if (libraryItems[i] instanceof CSLibraryItem)
			{
				count++;
			}
		}
		return count;
	}
	
	/**
	 * Adds a CSLibraryItem to the collection. Attempting to add a CSLibraryItem to a collection that is at
	 * capacity will result in the item not being added. Sorted order is not enforced from adding.
	 * @param toAdd The CSLibraryItem to be added.
	 * @return True if the item was added successfully, false if it was not added.
	 */
	public boolean add(CSLibraryItem toAdd)
	{
		if (!atMaxLimit() && toAdd != null)
		{
			libraryItems[countItems] = toAdd;
			countItems++;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Finds the location of a CSLibraryItem in the collection.
	 * @param toFind The CSLibraryItem being searched for.
	 * @return The position of toFind in the collection or -1 if the item is not present.
	 */
	public int find(CSLibraryItem toFind)
	{
		if (toFind != null)
		{
			for (int i = 0; i < libraryItems.length - 1; i++)
			{
				if (libraryItems[i] != null && libraryItems[i].equals(toFind))
				{
					return i;
				}
			}
		}
		return -1;
	}
	
	/**
	 * Removes the CSLibraryItem at the specified index. Sorted order is not enforced from removing.Receiving an 
	 * invalid index does not change the state of the collection.
	 * @param index Index of the item to be removed.
	 * @return True if the item at specified index is successfully removed, false if no item is removed.
	 */
	public boolean remove(int index)
	{
		if (index >= 0 && index < MAX_ITEMS && libraryItems[index] != null)
		{
			libraryItems[index] = null;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Removes the given CSLibraryItem if present. Sorted order is not enforced from removing.If the item to 
	 * remove is not in the collection the state of the collection is unchanged.
	 * @param toRemove The item to be removed.
	 * @return True if the item is successfully removed, false if no item is removed.
	 */
	public boolean remove(CSLibraryItem toRemove)
	{
		boolean value = false;
		if (toRemove == null)
		{
			value = false;
		}
		else
		{
			for (int i = 0; i < libraryItems.length; i++)
			{
				if (libraryItems[i] != null && libraryItems[i].equals(toRemove))
				{
					libraryItems[i] = null;
					value = true;
				}
			}
		}
		return value;
	}
	
	/**
	 * Returns the CSLibraryItem at the specified index.
	 * @param index Location of the item to be retrieved.
	 * @return The LibraryItem at the given index, returns null if the index is invalid.
	 */
	public CSLibraryItem get(int index)
	{
		if (index >= 0 && index < libraryItems.length)
		{
			return libraryItems[index];
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * Generates a new CSLibraryItemCollection containing CSLibraryItems of the requested type.
	 * @param collectionType The type of CSLibraryItem to be collected as determined by the static value passed.
	 * @return The new CSLibraryItemCollection of the requested type. If an invalid value for collection typeis 
	 * given the method will return null.
	 */
	public CSLibraryItemCollection generateSubCollection(int collectionType)
	{
		CSLibraryItemCollection value = new CSLibraryItemCollection();
		if (collectionType >= 0 && collectionType <= ROBOT)
		{
			if (collectionType == ALL_TYPES)
			{
				value = helperGenerateAllTypes();
			}
			else if (collectionType == ROBOT)
			{
				value = helperGenerateRobot();
			}
			else if (collectionType == DEV_BOARD)
			{
				value = helperGenerateDevBoard();
			}
			else
			{
				value = helperGenerateTextBook();
			}
		}
		else
		{
			return null;
		}
		return value;
	}
	
	/**
	 * Helper method for generating a collection of items.
	 * @return collection of new Textbooks.
	 */
	private CSLibraryItemCollection helperGenerateTextBook()
	{
		CSLibraryItemCollection collection = new CSLibraryItemCollection();
		for (int i = 0; i < libraryItems.length; i++)
		{
			if (libraryItems[i] instanceof Textbook && libraryItems[i] != null)
			{
				collection.add(libraryItems[i]);
			}
		}
		return collection;	
	}
	
	/**
	 * Helper method for generating a collection of items.
	 * @return collection of new DevBoards.
	 */
	private CSLibraryItemCollection helperGenerateDevBoard()
	{
		CSLibraryItemCollection collection = new CSLibraryItemCollection();
		for (int i = 0; i < libraryItems.length; i++)
		{
			if (libraryItems[i] instanceof DevBoard && libraryItems[i] != null)
			{
				collection.add(libraryItems[i]);
			}
		}
		return collection;	
	}
	
	/**
	 * Helper method for generating a collection of items.
	 * @return collection of new Robots.
	 */
	private CSLibraryItemCollection helperGenerateRobot()
	{
		CSLibraryItemCollection collection = new CSLibraryItemCollection();
		for (int i = 0; i < libraryItems.length; i++)
		{
			if (libraryItems[i] instanceof Robot && libraryItems[i] != null)
			{
				collection.add(libraryItems[i]);
			}
		}
		return collection;	
	}
	
	/**
	 * Helper method for generating a collection of items.
	 * @return collection of new AllTypes.
	 */
	private CSLibraryItemCollection helperGenerateAllTypes()
	{
		CSLibraryItemCollection collection = new CSLibraryItemCollection();
		for (int i = 0; i < libraryItems.length; i++)
		{
			if (libraryItems[i] != null && libraryItems[i] instanceof CSLibraryItem)
			{
				collection.add(libraryItems[i]);
			}
		}
		return collection;	
	}
	
	/**
	 * Sorts all of the items in the collection based upon each type's alphabetize rule.
	 * In order to determine the relationship of Strings, use String's compareTo method.
	 * 
	 * String's compare to is called upon by a String and is passed another String as an argument.The method 
	 * returns an int with the following possible results:
	 * 
	 * A value less than 0: The calling String is less than the passed String.
	 * 
	 * The value 0: The calling String and the passed String are equal.
	 * 
	 * A value greater than 0: The calling String is greater than the passed String.
	 */
	public void sortCollection()
	{
		CSLibraryItem temp;
		for (int pass = 0; pass < libraryItems.length - 1; pass++)
		{
			for (int comp = 0; comp < libraryItems.length - 1; comp++)
			{
				if (libraryItems[comp] == null)
				{
					temp = libraryItems[comp];
					libraryItems[comp] = libraryItems[comp + 1];
					libraryItems[comp + 1] = temp;
				}
				if (libraryItems[comp + 1] != null && libraryItems[comp].alphabetizeBy().compareTo(
						libraryItems[comp + 1].alphabetizeBy()) > 0)
				{
					temp = libraryItems[comp];
					libraryItems[comp] = libraryItems[comp + 1];
					libraryItems[comp + 1] = temp;
				}
			}
		}
	}
	
	/**
	 * Used to quickly determine if the array is at maximum capacity.
	 * @return True if at maximum limit, false if it is not.
	 */
	private boolean atMaxLimit()
	{
		return countItems == MAX_ITEMS;
	}
}
