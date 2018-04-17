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
public class PresidentBasic implements Comparable<PresidentBasic> {
		
		private String firstName; //Stores the first name of the president.
		private String lastName; //Stores the last name of the president.
		private int presidentAgeEnter; //Stores the age of the president when he entered office.
		private int presidentAgeExit; //Stores the age of the president when he left office.
		private int longevity; //Stores the number of years the president lived.
		private int totalAge; //Stores the age of the president when he died.
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
		
		public PresidentBasic(String firstName, String lastName, int birthYear, int deathYear, int yearEnter, int yearExit){
			this.firstName = firstName;
			this.lastName = lastName;
			this.presidentAgeEnter = yearEnter - birthYear;
			this.presidentAgeExit = yearExit - this.presidentAgeEnter;
			this.totalAge = deathYear - birthYear;
			
		}

		@Override
		public int compareTo(PresidentBasic o) {
			// TODO Auto-generated method stub
			return 0;
		}
	}
}
