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
	 * 
	 * @param args
	 * @throws NumberFormatException If the numbers for birth year and death year aren't integers.
	 * @throws Exception If the years passed are incorrect chronologically (ex: birthYear after deathYear or yearExit before yearEntry). 
	 */
	public static void main(String[] args) throws NumberFormatException, Exception{
		Scanner input = new Scanner("/Sonosite-Quiz/src/input.txt");
		
		//Fill presidents with the presidents from input.txt.
		presidents = new HashSet<PresidentBasic>();
		while (input.hasNextLine()){
			String[] currentLine = input.nextLine().split(",");
			presidents.add(new PresidentBasic(currentLine[0], currentLine[1], Integer.parseInt(currentLine[2]), Integer.parseInt(currentLine[3]), currentLine[4], currentLine[5]));
		}
		input.close();
		
		//Create the various .txt files and the histogram.
		createYoungestPresident();
		createLongevity();
		createAMHistogram();
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
