package cslibrary.application;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cslibrary.cslibraryitems.Textbook;
import cslibrary.cslibraryitems.CSLibraryItem;
import cslibrary.cslibraryitems.DevBoard;
import cslibrary.cslibraryitems.Robot;
import cslibrary.utils.CSLibraryItemCollection;

/**
 * A small application used to manage items in a CS lending library.
 * @author Patrick Cavanaugh updated by Doug Bertelsen
 *
 */
public class CSLibraryApplication extends JFrame
{
	/**
	 * Width of the application.
	 */
	private static final int APP_WIDTH = 610;
	/**
	 * Height of the application.
	 */
	private static final int APP_HEIGHT = 150;
	/**
	 * Constant for requesting all items regardless of availability.
	 */
	private static final int ALL_AVAILABILITY = 0;
	/**
	 * Constant for requesting only available items.
	 */
	private static final int AVAILABLE_ITEMS = 1;
	/**
	 * Constant for requesting only checked out items.
	 */
	private static final int CHECKED_OUT_ITEMS = 2;
	/**
	 * Panel for application instructions.
	 */
	private JPanel instructionsPanel;
	/**
	 * Text field to hold application instructions.
	 */
	private JTextField instructions;
	
	/**
	 * Panel for user selection options.
	 */
	private JPanel selectionPanel;
	/**
	 * Label for the item type box.
	 */
	private JLabel listTypeLabel;
	/**
	 * Options for item type.
	 */
	private final String[] listOptions = {"All", "Textbooks", "DevBoards", "Robots"};
	/**
	 * Drop down box for selecting item type.
	 */
	private JComboBox<String> listType;
	/**
	 * Label for the availability box.
	 */
	private JLabel availabilityLabel;
	/**
	 * Options for availability.
	 */
	private final String[] availabilityOptions = {"All", "Available", "Checked Out"};
	/**
	 * Drop down box for selecting item availability.
	 */
	private JComboBox<String> availability;
	/**
	 * Label for the items box.
	 */
	private JLabel itemsLabel;
	/**
	 * Drop down box for selecting item to act upon.
	 */
	private JComboBox<String> items;
	
	/**
	 * Panel for user actions.
	 */
	private JPanel actionsPanel;
	/**
	 * Button to retrieve information about selected item.
	 */
	private JButton info;
	/**
	 * Button to check out selected item.
	 */
	private JButton checkOutItem;
	/**
	 * Button to return selected item.
	 */
	private JButton returnItem;
	
	/**
	 * Collection of all items owned by the library.
	 */
	private CSLibraryItemCollection allItems;
	/**
	 * Collection of all the available items.
	 */
	private CSLibraryItemCollection availableItems;
	/**
	 * Collection of all the checked out items.
	 */
	private CSLibraryItemCollection checkedOutItems;
	/**
	 * Collection of the currently requested items based upon user choices.
	 */
	private CSLibraryItemCollection requestedItems;
	
	/**
	 * Creates application components and adds to Frame, instantiates CSLibraryCollections, and populates
	 * collections from file.
	 */
	private CSLibraryApplication()
	{
		super("CS Lending Library Check Out");
		
		//Create separate collections for available and checked out items
		allItems = new CSLibraryItemCollection();
		//availableItems = new LibraryItemCollection();
		checkedOutItems = new CSLibraryItemCollection();
		
		//Populate all Library items from file
		populateItems();
		
		setLayout(new FlowLayout());
		
		//Set up instructions Panel
		instructionsPanel = new JPanel();
		instructions = new JTextField("Select items to list and availabilty to update listed items");
		instructions.setEditable(false);
		instructionsPanel.add(instructions);
		add(instructionsPanel);
		
		//Set up user selection Panel
		selectionPanel = new JPanel();
		listTypeLabel = new JLabel("List: ");
		listType = new JComboBox<String>(listOptions);
		availabilityLabel = new JLabel("Availability: ");
		availability = new JComboBox<String>(availabilityOptions);
		itemsLabel = new JLabel("Item: ");
		availableItems.sortCollection();
		items = new JComboBox<String>(generateItemOptions(availableItems, ALL_AVAILABILITY));
		items.setPrototypeDisplayValue("XXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		UserSelectionListener usl = new UserSelectionListener();
		listType.addItemListener(usl);
		availability.addItemListener(usl);
		selectionPanel.add(listTypeLabel);
		selectionPanel.add(listType);
		selectionPanel.add(availabilityLabel);
		selectionPanel.add(availability);
		selectionPanel.add(itemsLabel);
		selectionPanel.add(items);
		add(selectionPanel);
		
		//Set up user actions Panel
		actionsPanel = new JPanel();
		info = new JButton("Item Info");
		checkOutItem = new JButton("Check Out");
		returnItem = new JButton("Return");
		UserActionsListener ual = new UserActionsListener();
		info.addActionListener(ual);
		checkOutItem.addActionListener(ual);
		returnItem.addActionListener(ual);
		actionsPanel.add(info);
		actionsPanel.add(checkOutItem);
		actionsPanel.add(returnItem);
		add(actionsPanel);	
	}
	
	/**
	 * Application entry point. Instantiates and sets up Frame.
	 * @param args Not used.
	 */
	public static void main(String[] args)
	{
		//Create application
		CSLibraryApplication app = new CSLibraryApplication();
		//Terminate the application when window is closed
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Set dimensions
		app.setSize(APP_WIDTH, APP_HEIGHT);
		//Default size is all you get
		app.setResizable(false);
		//Center on screen
		app.setLocationRelativeTo(null);
		//Make sure it can be seen
		app.setVisible(true);
	}

	/**
	 * Populate all items collection from file, to begin all items are available, creates a subcollection
	 * of all items for available items.
	 */
	private void populateItems()
	{
		//Scanner for reading
		Scanner readIn = null;
		//Number of items by type
		int numTextbooks;
		int numDevBoards;
		int numRobots;
		//For reading in item info
		String title;
		String authorRatingDate;
		int pages;
		//Temp reference for creating new items
		CSLibraryItem newItem;
		
		try
		{
			//Open items file for reading
			readIn = new Scanner(new File("CSLibraryStock.txt"));
		}
		catch (FileNotFoundException fnfe)
		{
			//Uh-oh
			JOptionPane.showMessageDialog(null, "File Not Found, Terminating");
			System.exit(1);
		}
		
		//Read in header including number of items by type contained in file
		readIn.next();
		numTextbooks = readIn.nextInt();
		readIn.next();
		numDevBoards = readIn.nextInt();
		readIn.next();
		numRobots = readIn.nextInt();
		readIn.nextLine();  // read the last newline before the first book
		
		//Read in the textbooks
		for (int i = 0; i < numTextbooks; i++)
		{
			title = readIn.nextLine();
			authorRatingDate = readIn.nextLine();
			pages = Integer.parseInt(readIn.nextLine());
			
			newItem = new Textbook(title, authorRatingDate, pages);
			allItems.add(newItem);
		}
		
		//Read in the dev boards
		for (int i = 0; i < numDevBoards; i++)
		{
			title = readIn.nextLine();
			authorRatingDate = readIn.nextLine();
			
			newItem = new DevBoard(title, authorRatingDate);
			allItems.add(newItem);
		}
		
		//Read in the robots
		for (int i = 0; i < numRobots; i++)
		{
			title = readIn.nextLine();
			authorRatingDate = readIn.nextLine();
			
			newItem = new Robot(title, authorRatingDate);
			allItems.add(newItem);
		}
		
		//All items initially available
		availableItems = allItems.generateSubCollection(CSLibraryItemCollection.ALL_TYPES);
		//Clean up after ourselves
		readIn.close();

	}
	
	/**
	 * Create an array of Strings to be displayed in the items drop down.
	 * @param collection Collection of items of selected type
	 * @param checkedOut Determines if need all, available, or checked out items in list
	 * @return Array of item choices
	 */
	private String[] generateItemOptions(CSLibraryItemCollection collection, int checkedOut)
	{
		//Create array to the most items possible as determined by the size of passed collection.
		//Plus one for "Select Item" default choice.
		String[] itemOptions = new String[collection.getNumberOfItems(CSLibraryItemCollection.ALL_TYPES) + 1];
		//First option is always "Select Item"
		itemOptions[0] = "Select Item";
		//Position in array to place item
		int itemOptionsPos = 1;
		//Fresh item collection to add requested items to
		requestedItems = new CSLibraryItemCollection();
		//Check all items in the collection
		for (int i = 0; i < itemOptions.length - 1; i++)
		{
			//Determine if current item should be added based upon checked out requested
			//and item availability
			if (checkedOut == ALL_AVAILABILITY
					|| (checkedOut == AVAILABLE_ITEMS && !collection.get(i).isCheckedOut())
					|| (checkedOut == CHECKED_OUT_ITEMS && collection.get(i).isCheckedOut()))
			{
				//Add short name to options list, +1 to take "Select Item" choice into account
				itemOptions[itemOptionsPos++] = collection.get(i).alphabetizeBy();
				//Add item to collection of requested items
				requestedItems.add(collection.get(i));
			}
		}
		//Array may have unused elements, trim it down
		return trimArray(itemOptions);
	}
	
	/**
	 * Trims unused elements off the end of a String array.
	 * @param arrayIn Array to be trimmed
	 * @return Trimmed array
	 */
	private static String[] trimArray(String[] arrayIn)
	{
		//Reference of array to be returned
		String[] trimmed;
		//Let's see how many values are actually in there
		int valueCount = 0;
		for (int i = 0; i < arrayIn.length; i++)
		{
			if (arrayIn[i] != null)
			{
				valueCount++;
			}
			else
			{
				break;
			}
		}
		
		//Create a copy of the input array up to last used element
		trimmed = Arrays.copyOf(arrayIn, valueCount);
		
		return trimmed;

	}
	
	/**
	 * Listener for JComboBoxes. Used to dynamically update the requested items box.
	 * This nifty feature is what makes everything is the application complex. You're welcome.
	 * @author Patrick Cavanaugh
	 */
	private class UserSelectionListener implements ItemListener
	{
		/**
		 * Called when type or availability selections have changed. Updates the list of
		 * selectable items in items box.
		 */
		@Override
		public void itemStateChanged(ItemEvent event)
		{
			/* Doesn't look like much, but does a lot; the hard work is passed off to other very
			 * clever methods. Decomposition people! This was originally 100+ lines of mess and wasted
			 * time until I remembered I wrote the used methods just for this. I'm not bitter, really.
			 * Is anyone still reading these comments? There are jewels of wisdom within.*/
			
			//A new selection was made
			if (event.getStateChange() == ItemEvent.SELECTED)
			{
				//Get a subcollection of the selected type
				CSLibraryItemCollection allItemsOfSelectedType =
						allItems.generateSubCollection(listType.getSelectedIndex());
				
				//Sort the items
				allItemsOfSelectedType.sortCollection();
				
				//Update the item selection filtered by requested availability
				updateItems(generateItemOptions(allItemsOfSelectedType,
						availability.getSelectedIndex()));
			}	
		} //End itemStateChanged
		
		/**
		 * Updates the options of the items box.
		 * @param itemChoices The new options
		 */
		private void updateItems(String[] itemChoices)
		{
			//Clear current choices in items combo box
			items.removeAllItems();
			
			//Repopulate with new choices
			for (int i = 0; i < itemChoices.length; i++)
			{
				items.addItem(itemChoices[i]);
			}
		}
	} //End userSelectionListener
	
	/**
	 * Listener for the action buttons.
	 * @author Patrick Cavanaugh
	 */
	private class UserActionsListener implements ActionListener
	{
		/**
		 * Called when one of the buttons is pressed. Determines which button and what to do.
		 */
		@Override
		public void actionPerformed(ActionEvent event)
		{
			//Get what the currently selected item is, -1 to take "Select Item" choice into account
			CSLibraryItem selectedItem = requestedItems.get(items.getSelectedIndex() - 1);
			
			//If no item is selected, user can't do anything, remind them politely
			if (selectedItem == null)
			{
				JOptionPane.showMessageDialog(null, "Please Select Item", "Item Info",
						JOptionPane.PLAIN_MESSAGE);
			}
			//The info button was pressed, give them all information about the item
			else if (event.getSource() == info)
			{
				JOptionPane.showMessageDialog(null, selectedItem, "Item Info",
						JOptionPane.PLAIN_MESSAGE);
				
			}
			//Item was requested to be checked out
			else if (event.getSource() == checkOutItem)
			{
				//Try to check out the item
				boolean success = selectedItem.checkOutItem();
				//If it was checked out successfully...
				if (success)
				{
					//Update available and checked out item collections accordingly
					availableItems.remove(selectedItem);
					checkedOutItems.add(selectedItem);
					//Force a new item selected event to update the list. This is pure cheese.
					//Work smart, not hard. If you're still reading this, at exactly 21 minutes
					//into your next class meeting start singing your favorite Taylor Swift song
					//aloud so that we know.
					int currentSelectedIndex = listType.getSelectedIndex();
					listType.setSelectedIndex(-1);
					listType.setSelectedIndex(currentSelectedIndex);
					//Tell them the item was successfully checked out
					JOptionPane.showMessageDialog(null, String.format("%s Checked Out",
							selectedItem.alphabetizeBy()),
							"Item Info", JOptionPane.PLAIN_MESSAGE);
				}
				//If it was not checked out successfully...
				else
				{
					//Let them know
					JOptionPane.showMessageDialog(null, String.format("%s Not Available",
							selectedItem.alphabetizeBy()),
							"Item Info", JOptionPane.PLAIN_MESSAGE);
				}
			}
			//Item requested to be returned
			else if (event.getSource() == returnItem)
			{
				//Attempt to return the item
				boolean success = selectedItem.returnItem();
				//If return was successful...
				if (success)
				{
					//Update available and checked out item collections accordingly
					checkedOutItems.remove(selectedItem);
					availableItems.add(selectedItem);
					//Return of the cheese, force the selection event
					int currentSelectedIndex = listType.getSelectedIndex();
					listType.setSelectedIndex(-1);
					listType.setSelectedIndex(currentSelectedIndex);
					//Report the success
					JOptionPane.showMessageDialog(null, String.format("%s Returned",
							selectedItem.alphabetizeBy()),
							"Item Info", JOptionPane.PLAIN_MESSAGE);
				}
				//If return is not successful...
				else
				{
					//Report the failure
					JOptionPane.showMessageDialog(null, String.format("%s Was Not Checked Out",
							selectedItem.alphabetizeBy()),
							"Item Info", JOptionPane.PLAIN_MESSAGE);
				}
			}
			
		}
		
	}
} //End CheckOutApplication...phew
