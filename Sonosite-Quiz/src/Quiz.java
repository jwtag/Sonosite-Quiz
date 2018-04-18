/**
 * Quiz answers.
 * @author John Taggart
 *
 */
import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import Presidents.*;
public class Quiz {
	
	public static Set<PresidentBasic> presidents; //Stores the presidents we are passed.
	
	/**
	 * Creates the .txt files and the histogram from "input.txt".
	 * @param args
	 * @throws NumberFormatException If the numbers for birth year and death year aren't integers.
	 * @throws Exception If the years passed are incorrect chronologically (ex: birthYear after deathYear or yearExit before yearEntry). 
	 */
	public static void main(String[] args) throws NumberFormatException, Exception{
		Scanner input = new Scanner("/Sonosite-Quiz/src/input.txt");
		
		//Fill presidents with the presidents from input.txt.
		presidents = new HashSet<PresidentBasic>();
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
	 * @param currentLine The line from which the PresidentBasic is created.
	 * @return a PresidentBasic formed from the String passed.
	 * @throws Exception If the years passed are incorrect chronologically (ex: birthYear after deathYear or yearExit before yearEntry).
	 */
	public static PresidentBasic createPresident(String currentLine) throws Exception{
		
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
		int birthYear = Integer.parseInt(currentLine.substring(0, 3)); //Store the birthYear.
		currentLine = currentLine.substring(5); //Trim off this field from currentLine.
		currentLine = currentLine.trim(); //Remove all leading and trailing whitespace
		
		//Take the line, get the deathYear from it, and trim the front to the start of the next field.
		if (currentLine.charAt(0) == ','){ //Trim off the ',' character if it is present.
			currentLine = currentLine.substring(1);
		}
		int deathYear = Integer.parseInt(currentLine.substring(0, 3)); //Store the deathYear.
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
		
		//Return a new PresidentBasic object using these fields.
		return new PresidentBasic(firstName, lastName, birthYear, deathYear, enterYear, exitYear);
	}
	
	/**
	 * Creates "youngest_president.txt".
	 * @throws FileNotFoundException if "youngest_president.txt" isn't found after it is created.
	 */
	public static void createYoungestPresident() throws FileNotFoundException{
		TreeSet<PresidentYoungest> presidentByYoungest = new TreeSet<PresidentYoungest>();
		
		//Add all of the PresidentBasics from "presidents" to presidentByYoungest, sorting them by youngest since presidentByYoungest is a TreeSet.
		for (PresidentBasic p : presidents){
			
			//We must cast each PresidentBasic to a "PresidentYoungest" in order to use the "PresidentYoungest" compareTo method for sorting them.
			presidentByYoungest.add((PresidentYoungest) p);
		}
		
		//Create the "youngest_president.txt" file.
		PrintStream output = new PrintStream(new FileOutputStream(new File("youngest_president.txt")));
		
		//Set the System to print to "output".
		System.setOut(output);
		
		//Output the list of sorted presidents (stored in the TreeSet) to "youngest_president.txt"
		for (PresidentYoungest p : presidentByYoungest){
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
		TreeSet<PresidentLongevity> presidentByLongevity = new TreeSet<PresidentLongevity>();
		
		//Add all of the PresidentBasics from "presidents" to PresidentLongevity, sorting them by longevity since presidentsByLongevity is a TreeSet.
		for (PresidentBasic p : presidents){
			
			//We must cast each PresidentBasic to a "PresidentLongevity" in order to use the "PresidentLongevity" compareTo method for sorting them.
			presidentByLongevity.add((PresidentLongevity) p);
		}
		
		//Create the "president_longevity.txt" file.
		PrintStream output = new PrintStream(new FileOutputStream(new File("president_longevity.txt")));
		
		//Set the System to print to "output".
		System.setOut(output);
		
		//Output the list of sorted presidents (stored in the TreeSet) to "president_longevity.txt"
		for (PresidentLongevity p : presidentByLongevity){
			System.out.println(p.toString());
		}
		
		//Reset the System's PrintStream to the console.
		System.setOut(System.out);
	}
	
	/**
	 * Creates the AM Histogram.
	 */
	public static void createAMHistogram(){
		
	}
	
}
