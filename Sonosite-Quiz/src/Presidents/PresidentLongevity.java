package Presidents;

/**
 * PresidentLongevity classes can be cast to in order to use 
 * their compareTo methods to compare the ages of presidents when they died.
 * @author John
 *
 */
public class PresidentLongevity extends PresidentBasic implements Comparable<PresidentLongevity> {

	public PresidentLongevity(String firstName, String lastName, int birthYear, int deathYear, int yearEnter,
			int yearExit) throws Exception {
		super(firstName, lastName, birthYear, deathYear, yearEnter, yearExit);
	}

	@Override
	public int compareTo(PresidentLongevity o) {
		return this.getLongevity() - o.getLongevity();
	}

}
