package Presidents;
/**
 * Base class for president objects. Stores the basic data we use to sort presidents in Quiz.java.  Also
 * calculates the values for the various fields we store in President-type objects.
 * The various subclasses that extend it (PresidentLongevity, PresidentYoungest, PresidentAM) only modify the
 * compareTo method for their purposes.  Thus, to get an understanding of all of the President-type objects,
 * read through this object's code first!
 * 
 * @author John
 *
 */
public class PresidentBasic {
		
		//These fields are private to guarantee the client cannot modify them in unintended ways.
	
		private String firstName; //Stores the first name of the president.
		private String lastName; //Stores the last name of the president.
		private int birthYear; //The president's birth year (as an integer).
		private int deathYear; //The president's death year (as an integer).
		private String dateEnter; //Stores the date of when the president entered office (as a String formatted as MM/DD/YY).
		private String dateExit; //Stores the date of when the president left office (as a String formatted as MM/DD/YY).
		private int presidentAgeEnter; //Stores the age of the president when he entered office.
		private int presidentAgeExit; //Stores the age of the president when he left office.
		private int longevity; //Stores the number of years the president lived.
		private int hasAM; /* Stores if the president's name has the letters 'a' and 'm' side-by-side.
							* Since checking if there are any sequential pairs of the letters 'a' and 'm'
							* in a name is time-consuming, we won't do it unless we have to.  In order to do
							* this, we will store this as an integer with its value as follows:
							* 
							* -1 = Doesn't have an 'AM' or 'MA' pair.
							* 0 = We haven't checked yet if it has an 'AM' or 'MA' pair.  The user should NEVER
							* 	  Have this value returned to them.
							* 1 = Has an 'AM' or 'MA' pair.
							*/
		
		/**
		 * Constructor for PresidentBasic objects.  Use this constructor for general flexibility.
		 * @param firstName The president's first name.
		 * @param lastName The president's last name.
		 * @param birthYear The president's birth year (as an integer).
		 * @param deathYear The president's death year (as an integer).
		 * @param dateEnter The date the president entered office (as a String formatted as MM/DD/YY).
		 * @param dateExit The date the president left office (as a String as MM/DD/YY).
		 * @throws Exception if the years passed are incorrect chronologically (ex: birthYear after deathYear or yearExit before yearEntry).
		 */
		public PresidentBasic(String firstName, String lastName, int birthYear, int deathYear, String dateEnter, String dateExit) throws Exception{
			
			//Obtain the Integer values for the years the president entered and exited office.
			int yearEnter = Integer.parseInt((dateEnter.split("/")[2]));
			int yearExit = Integer.parseInt((dateExit.split("/")[2]));
			
			//Check that the years are valid (no odd ordering).
			if (birthYear >= deathYear){
				throw new Exception("The birth year of the president must be less than the death year.");
			}  else if (yearExit < yearEnter){
				throw new Exception("The year the president left office must be greater than or equal to when he entered office.");
			}  else if (deathYear < yearExit){
				throw new Exception("The death year of the president must be greater than or equal to the year he left office.");
			} else if (birthYear > yearEnter){
				throw new Exception("The president must be born before he entered office.");
			}
			this.firstName = firstName;
			this.lastName = lastName;
			this.birthYear = birthYear;
			this.deathYear = deathYear;
			this.dateEnter = dateEnter;
			this.dateExit = dateExit;
			this.presidentAgeEnter = yearEnter - birthYear;
			this.presidentAgeExit = yearExit - this.presidentAgeEnter;
			this.longevity = deathYear - birthYear;
			this.hasAM = 0;
		}
		
		/**
		 * Returns the first name of this president as a String.
		 * @return The first name of this president as a String.
		 */
		public String getFirstName(){
			return this.firstName;
		}
		
		/**
		 * Returns the last name of this president as a String.
		 * @return The last name of this president as a String.
		 */
		public String getLastName(){
			return this.lastName;
		}
		
		/**
		 * Returns the age when the president died (longevity).
		 * @return the age when the president died (longevity) as an int.
		 */
		public int getLongevity(){
			return this.longevity;
		}
		
		/**
		 * Returns the age when the president entered office.
		 * @return the age when the president entered office as an int.
		 */
		public int getPresidentAgeEnter(){
			return this.presidentAgeEnter;
		}
		
		/**
		 * Returns the age when the president left office.
		 * @return the age when the president left office as an int.
		 */
		public int getPresidentAgeExit(){
			return this.presidentAgeExit;
		}
		
		/**
		 * Returns if the president's name has the letters 'a' and 'm' side-by-side.
		 * @return true if the president's name has the letters 'a' and 'm' side-by-side, false otherwise.
		 * @throws Exception If hasAM is zero after the String has been processed.
		 */
		public boolean hasAM() throws Exception{
			
			/**
			 * If hasAM == 0, calculate its value so that it is 1 or -1.  By using this integer-based approach,
			 * we only have to loop through the String one time to obtain the value we need for retrieving the data
			 * desired.
			 */
			
			if (this.hasAM == 0){
				
				//Create a string storing the first and last names combined with only one case (and a space between them).
				String fullName = this.firstName.toLowerCase() + ' ' + this.lastName.toLowerCase();
				
				for (int i = 0; i < fullName.length() - 1; i++){
					
					//If the fullName has an 'am'/'ma' pair, set hasAM to 1 (true) and break the loop.
					if ((fullName.charAt(i) == 'a' && fullName.charAt(i + 1) == 'm') || (fullName.charAt(i) == 'm' && fullName.charAt(i + 1) == 'a')){
						this.hasAM = 1;
						break;
					}
				}
				
				//If the two letters were not found to be side-by-side in the name, then hasAM should be set to -1.
				if (this.hasAM == 0) this.hasAM = -1;
				
			}
			
			if (this.hasAM == 0) throw new Exception("hasAM should not equal zero after the string has been read!");
			//If hasAM == 1, the president has a pair of the letters 'a' and 'm'.  Otherwise, he doesn't.
			return this.hasAM == 1;
		}
		
		/**
		 * Returns details about this president in the format they were originally stored in in "input.txt".  In other words, in the following format:
		 * 
		 * <first_name>,<last_name>,<birth_year>,<death_year>,<president_from_date>,<president_to_date>
		 */
		public String toString(){
			return this.firstName + "," + this.lastName + "," + this.birthYear + "," + this.deathYear + "," + this.dateEnter + "," + this.dateExit;
		}
}
