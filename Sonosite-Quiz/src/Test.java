import static org.junit.Assert.*;

/**
 * Contains tests for my code for the Sonosite Quiz.
 * @author John Taggart
 *
 */
public class Test {

	@org.junit.Test
	public void test() throws Exception {
		testQuiz();
		testPresident();
	}
	
	/**
	 * Tests the methods in testQuiz.
	 */
	public static void testQuiz(){
		
		/**
		 * Checks output with file with perfect input.
		 */
		
		//
		
		/**
		 * Checks output with file with sloppy input (input.txt).
		 */
		
		//
		
		/**
		 * Checks output with file from no input.
		 */
		
		//
		
		/**
		 * Checks output with file from input with one president.
		 */
		
		//
	}
	
	
	/**
	 * Tests the methods in my President data type.
	 */
	public static void testPresident() throws Exception{
		
		/**
		 * Tests all possible "bad year" exceptions.
		 */
		
		boolean exceptionThrown = false;
		
		//Birth > Death case.
		try {
			President p = new President("a", "b", 1002, 1001, "1/1/1000", "1/1/1001");
		} catch (Exception e){
			exceptionThrown = true;
		}
		assertTrue("Failed the birthYear <= deathYear test.", exceptionThrown);
		exceptionThrown = false;
		
		//Exit < Enter case.
		try {
			President p = new President("a", "b", 0, 1001, "1/1/1000", "1/1/0000");
		} catch (Exception e){
			exceptionThrown = true;
		}
		assertTrue("Failed the exit year >= entry year test.", exceptionThrown);
		exceptionThrown = false;
				
		//Death < Enter case.
		try {
			President p = new President("a", "b", 0, 1, "1/1/1000", "1/1/2000");
		} catch (Exception e){
			exceptionThrown = true;
		}
		assertTrue("Failed the deathYear >= entry year test.", exceptionThrown);
		exceptionThrown = false;
		
		//Enter < Birth case.
		try {
			President p = new President("a", "b", 1, 2, "1/1/0000", "1/1/0001");
		} catch (Exception e){
			exceptionThrown = true;
		}
		assertTrue("Failed the entry year >= birth case.", exceptionThrown);
		exceptionThrown = false;
				
		/**
		 * Tests "get-" functions and toString().
		 */
		
		//Create test President object.
		President p = new President("a", "b", 0, 10, "1/1/0001", "1/1/0009");
		
		//Test getFirstName()
		assertEquals("Failed the getFirstName() test.", "a", p.getFirstName());
		
		//Test getLastName()
		assertEquals("Failed the getLastName() test.", "b", p.getLastName());
		
		//Test getLongevity()
		assertEquals("Failed the getLongevity() test.", 10, p.getLongevity());
		
		//Test getPresidentAgeEnter()
		assertEquals("Failed the getPresidentAgeEnter() test.", 1, p.getPresidentAgeEnter());
		
		//Test getPresidentAgeExit()
		assertEquals("Failed the getPresidentAgeExit() test.", 9, p.getPresidentAgeExit());
				
		//Test getEnterYear()
		assertEquals("Failed the getEnterYear() test.", 1, p.getEnterYear());
		
		//Test getExitYear()
		assertEquals("Failed the getExitYear() test.", 9, p.getExitYear());
		
		//Test toString()
		assertEquals("Failed the toString() test.", "a,b,0,10,1/1/0001,1/1/0009", p.toString());

		/**
		 * Tests "hasAM".
		 */
		
		//Test "am" case.
		p = new President("am", "b", 0, 10, "1/1/0001", "1/1/0009");
		assertTrue("Failed the \"am\" test", p.hasAM());
		
		//Test "ma" case.
		p = new President("ma", "b", 0, 10, "1/1/0001", "1/1/0009");
		assertTrue("Failed the \"ma\" test", p.hasAM());
		
		//Test "AM" case.
		p = new President("AM", "b", 0, 10, "1/1/0001", "1/1/0009");
		assertTrue("Failed the \"AM\" test", p.hasAM());
		
		//Test "MA" case.
		p = new President("MA", "b", 0, 10, "1/1/0001", "1/1/0009");
		assertTrue("Failed the \"MA\" test", p.hasAM());
		
		//Test "MAAM" case.
		p = new President("MAAM", "b", 0, 10, "1/1/0001", "1/1/0009");
		assertTrue("Failed the \"MAAM\" test", p.hasAM());
		
		//Test "AMMA" case.
		p = new President("AMMA", "b", 0, 10, "1/1/0001", "1/1/0009");
		assertTrue("Failed the \"AMMA\" test", p.hasAM());
		
		//Test "am" in last name case.
		p = new President("a", "bam", 0, 10, "1/1/0001", "1/1/0009");
		assertTrue("Failed the \"am\" in last name test", p.hasAM());
		
		//Test "George Washington" case.
		p = new President("George", "Washington", 0, 10, "1/1/0001", "1/1/0009");
		assertTrue("Failed the \"George Washington\" test", !p.hasAM());
		
		//Test the "a" as first name "m" as last name case.
		p = new President("a", "m", 0, 10, "1/1/0001", "1/1/0009");
		assertTrue("Failed the \"a\" as first name \"m\" as last name case. test", !p.hasAM());
		
		//Test the "m" as first name "a" as last name case.
		p = new President("m", "a", 0, 10, "1/1/0001", "1/1/0009");
		assertTrue("Failed the \"m\" as first name \"a\" as last name case. test", !p.hasAM());
		
		
		/**
		 * Tests compareTo().
		 */
		President pGreater = new President("a", "b", 0, 10000, "1/1/0001", "1/1/1009"); //Greater in every way than pLesser.
		President pLesser = new President("a", "b", 5, 10, "1/1/0008", "1/1/0009"); //Lesser in every way than pGreater.
		
		//Test compareTo() using longevity.
		pGreater.setToCompareByLongevity();
		pLesser.setToCompareByLongevity();
		assertTrue("Failed longevity true test.", pGreater.compareTo(pLesser) == -1);
		assertTrue("Failed longevity false test.", pLesser.compareTo(pGreater) == 1);
		assertTrue("Failed longevity equal test.", pGreater.compareTo(pGreater) == 1);
		
		//Test compareTo() using CompareByYoungest.
		pGreater.setToCompareByYoungest();
		pLesser.setToCompareByYoungest();
		assertTrue("Failed CompareByYoungest true test.", pGreater.compareTo(pLesser) == 1);
		assertTrue("Failed CompareByYoungest false test.", pLesser.compareTo(pGreater) == -1);
		assertTrue("Failed CompareByYoungest equal test.", pGreater.compareTo(pGreater) == 1);
	}

}
