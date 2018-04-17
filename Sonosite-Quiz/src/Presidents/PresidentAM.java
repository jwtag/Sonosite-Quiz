package Presidents;

public class PresidentAM extends PresidentBasic {

	public PresidentAM(String firstName, String lastName, String birthYear, String deathYear, String presidentAgeEnter,
			String presidentAgeExit) {
		super(firstName, lastName, birthYear, deathYear, presidentAgeEnter, presidentAgeExit);
	}
	
	public int compareTo(PresidentAM other){
		return 0;
	}
}
