/**
 * My code for the quiz.
 * @author John Taggart
 *
 */
import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Quiz {
	
	public static Set<President> presidents; //Stores the presidents we are passed.
	
	/**
	 * Creates the .txt files and the histogram from "input.txt".
	 * @param args File besides "input.txt" to be read (if applicable).
	 * @throws NumberFormatException If the numbers for birth year and death year aren't integers.
	 * @throws Exception If the years passed are incorrect chronologically (ex: birthYear after deathYear or yearExit before yearEntry). 
	 */
	public static void main(String[] args) throws NumberFormatException, Exception{
		String inputFile = "src/input.txt"; //The input file to be sourced from.
		
		if (args.length == 1){ //If the file to be read is passed in, inputFile is set to its name here.
			inputFile = args[0];
		}
		
		Scanner input = new Scanner(new File(inputFile));
		
		//Fill presidents with the presidents from input.txt.
		presidents = new HashSet<President>();
		while (input.hasNextLine()){
			//Create a president object and add it to the presidents.
			presidents.add(createPresident(input.nextLine()));
		}
		input.close();
		
		//Create the various .txt files and the histogram.
		createYoungestPresident();
		createLongevity();
		createAMHistogram();
	}
	
	/**
	 * Creates a president from the line passed.  The line should be formatted as follows:
	 * <first_name>,<last_name>,<birth_year>,<death_year>,<president_from_date>,<president_to_date>
	 * 
	 * However, if the line isn't QUITE formatted that way (but the order is correct), there is a chance
	 * that the program can read that data.
	 * 
	 * @param currentLine The line from which the President is created.
	 * @return a President formed from the String passed.
	 * @throws Exception If the years passed are incorrect chronologically (ex: birthYear after deathYear or yearExit before yearEntry).
	 */
	public static President createPresident(String currentLine) throws Exception{
		
		//Take the line, get the firstName from it, and trim the front to the start of the next field.
		String firstName = currentLine.substring(0, currentLine.indexOf(',')); //Store the firstName.
		currentLine = currentLine.substring(currentLine.indexOf(',') + 1); //Trim off this field from currentLine.
		currentLine = currentLine.trim(); //Remove all leading and trailing whitespace
		
		//Take the line, get the lastName from it, and trim the front to the start of the next field.
		String lastName = currentLine.substring(0, currentLine.indexOf(',')); //Store the lastName.
		currentLine = currentLine.substring(currentLine.indexOf(',') + 1); //Trim off this field from currentLine.
		currentLine = currentLine.trim(); //Remove all leading and trailing whitespace
		
		//Take the line, get the birthYear from it, and trim the front to the start of the next field.
		if (currentLine.charAt(0) == ','){ //Trim off the ',' character if it is present.
			currentLine = currentLine.substring(1);
		}
		int birthYear = Integer.parseInt(currentLine.substring(0, 4).trim()); //Store the birthYear.
		currentLine = currentLine.substring(5); //Trim off this field from currentLine.
		currentLine = currentLine.trim(); //Remove all leading and trailing whitespace
		
		//Take the line, get the deathYear from it, and trim the front to the start of the next field.
		if (currentLine.charAt(0) == ','){ //Trim off the ',' character if it is present.
			currentLine = currentLine.substring(1);
		}
		int deathYear = Integer.parseInt(currentLine.substring(0, 4).trim()); //Store the deathYear.
		currentLine = currentLine.substring(5); //Trim off this field from currentLine.
		currentLine = currentLine.trim(); //Remove all leading and trailing whitespace
		
		//Take the line, get the enterYear (year the president entered office) from it, and trim the front to the start of the next field.
		if (currentLine.charAt(0) == ','){ //Trim off the ',' character if it is present.
			currentLine = currentLine.substring(1);
		}
		String enterYear = currentLine.substring(0, currentLine.indexOf(',')); //Store the year the president entered office.
		currentLine = currentLine.substring(currentLine.indexOf(',') + 1); //Trim off this field from currentLine.
		currentLine = currentLine.trim(); //Remove all leading and trailing whitespace
		
		//Stores the year the president left office.
		String exitYear = currentLine;
		
		//Return a new President object using these fields.
		return new President(firstName, lastName, birthYear, deathYear, enterYear, exitYear);
	}
	
	/**
	 * Creates "youngest_president.txt".
	 * @throws FileNotFoundException if "youngest_president.txt" isn't found after it is created.
	 */
	public static void createYoungestPresident() throws FileNotFoundException{
		TreeSet<President> presidentByYoungest = new TreeSet<President>();
		
		//Add all of the Presidents from "presidents" to presidentByYoungest, sorting them by youngest since presidentByYoungest is a TreeSet.
		for (President p : presidents){
			
			//We must set each President's compareTo() method to compare by youngest before adding them to the TreeSet.
			p.setToCompareByYoungest();
			
			//By adding p to the TreeSet, the TreeSet uses the compareTo() to sort them automatically for us.
			presidentByYoungest.add(p);
		}
		
		//Create the "youngest_president.txt" file.
		PrintStream output = new PrintStream(new FileOutputStream(new File("youngest_president.txt")));
		
		//Set the System to print to "output".
		System.setOut(output);
		
		//Output the list of sorted presidents (stored in the TreeSet) to "youngest_president.txt"
		for (President p : presidentByYoungest){
			System.out.println(p.toString());
		}
		
		//Reset the System's PrintStream to the console.
		System.setOut(System.out);
	}
	
	/**
	 * Creates "president_longevity.txt".
	 * @throws FileNotFoundException if "president_longevity.txt" isn't found after it is created.
	 */
	public static void createLongevity() throws FileNotFoundException{
		TreeSet<President> presidentByLongevity = new TreeSet<President>();
		
		//Add all of the Presidents from "presidents" to PresidentLongevity, sorting them by longevity since presidentsByLongevity is a TreeSet.
		for (President p : presidents){
			
			//We must set each President's compareTo() method to compare by longevity before adding them to the TreeSet.
			p.setToCompareByLongevity();
			
			//By adding p to the TreeSet, the TreeSet uses the compareTo() to sort them automatically for us.
			presidentByLongevity.add(p);
		}
		
		//Create the "president_longevity.txt" file.
		PrintStream output = new PrintStream(new FileOutputStream(new File("president_longevity.txt")));
		
		//Set the System to print to "output".
		System.setOut(output);
		
		//Output the list of sorted presidents (stored in the TreeSet) to "president_longevity.txt"
		for (President p : presidentByLongevity){
			System.out.println(p.toString());
		}
		
		//Reset the System's PrintStream to the console.
		System.setOut(System.out);
	}
	
	/**
	 * Creates the AM/MA Histogram. and outputs it to "histogram.txt".
	 * @throws Exception If hasAM() malfunctioned in its operations.  Consult the method declaration in President.java for more details.
	 */
	public static void createAMHistogram() throws Exception{
		boolean firstPresidentRead = true; //If the current President being read is the first president read into the program.
		
		int earliestYear = 0; //The earliest year on the histogram.
		
		int latestYear = 0; //The latest year on the histogram.
		
		/**
		 * A Map of when each AM/MA President took office, with the keys being the years they took office, and the values being the president.
		 */
		TreeMap<Integer, President> AMPresidents = new TreeMap<Integer, President>();
		
		//Goes through presidents and adds each AM/MA President to AMPresidents
		for (President p : presidents){
			
			//Update the start and end years for the histogram as necessary.
			if (p.getEnterYear() < earliestYear){
				earliestYear = p.getEnterYear();
			}
			if (p.getExitYear() > latestYear){
				latestYear = p.getExitYear();
			}
			if (firstPresidentRead){ //If this is the first president read into the program, register it's entry and exit years as the earliestYear and latestYear.
				earliestYear = p.getEnterYear();
				latestYear = p.getExitYear();
				firstPresidentRead = false;
			}
			if (p.hasAM()){ //If p has 'AM'/'MA', add it to AMPresidents.
				AMPresidents.put(p.getEnterYear(), p);
			}
		}
		
		String[] histogram = new String[latestYear - earliestYear + 1]; //Stores the histogram.
		
		//Label each line of the histogram with the year it represents.
		for (int i = 0; i < histogram.length; i++){
			histogram[i] = (earliestYear + i) + "\t";
		}
		
		/**
		 * Since AMPresidents is stored in a TreeMap, the Presidents are sorted by the year they entered office.
		 * Thus, we can easily create a histogram with labels of when they entered office.  Below we loop through
		 * AMPresidents, and we add a star to the Strings corresponding with every year after each president who had
		 * an "AM"/"MA" in their name entered office.  The years where Presidents with an "AM"/"MA" in their name 
		 * entered office are labeled with the year they entered office and their name.
		 */
		for (Integer year : AMPresidents.keySet()){
			for (int i = year - earliestYear; i < histogram.length; i++){ //Add a star to all years following the starting year.
				histogram[i] += '*';
			}
			//Add the presidents name as a label.
			histogram[year - earliestYear] += " - President " + AMPresidents.get(year).getFirstName() + " " + AMPresidents.get(year).getLastName();
		}
		
		//Create the "histogram.txt" file.
		PrintStream output = new PrintStream(new FileOutputStream(new File("histogram.txt")));
		
		//Set the System to print to "output".
		System.setOut(output);
		
		System.out.println("Starting year: " + earliestYear + "\n");
		
		//Output the histogram of "AM"/"MA" Presidents (stored in the String[]) to "histogram.txt"
		for (int i = 0; i < histogram.length; i++){
			System.out.println(histogram[i]);
		}
		
		System.out.print("\nLast year: " + latestYear);
		
		//Reset the System's PrintStream to the console.
		System.setOut(System.out);
	}
	
}
