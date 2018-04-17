package Presidents;

/**
 * PresidentYoungest classes can be cast to in order to use 
 * their compareTo methods to compare the ages of presidents when they entered office.
 * @author John
 *
 */
public class PresidentYoungest extends PresidentBasic implements Comparable<PresidentYoungest> {

	public PresidentYoungest(String firstName, String lastName, int birthYear, int deathYear, int yearEnter,
			int yearExit) throws Exception {
		super(firstName, lastName, birthYear, deathYear, yearEnter, yearExit);
	}

	@Override
	public int compareTo(PresidentYoungest o) {
		return this.getPresidentAgeEnter() - o.getPresidentAgeEnter();
	}

}
