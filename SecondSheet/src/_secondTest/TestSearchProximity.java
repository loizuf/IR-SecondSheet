package _secondTest;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import _secondGiven.FileReader;
import _secondWork.PositionalIndex;

@RunWith(Parameterized.class)
public class TestSearchProximity {

	// Location of test collection
	private static final String TEST_PATH_2 = "collections/testCollections/second";

	// Variable containing an instance of InvertedIndex
	private PositionalIndex positionalIndex;

	// parameterized variables
	private String[] inputTerms;
	private ArrayList<Integer> expectedResult;

	public TestSearchProximity(String[] inputTerms, ArrayList<Integer> expectedResult, String message) throws FileNotFoundException {
		// these Variables are used to test the two methods
		positionalIndex = new PositionalIndex(FileReader.readCollection(TEST_PATH_2));

		this.inputTerms = inputTerms;
		this.expectedResult = expectedResult;
	}

	@Test
	public void testPerformANDMerge() {
		assertEquals(expectedResult, positionalIndex.searchForPhrase(inputTerms));
	}
	
	// This method sets up the data for the tests
	@Parameters (name = "{2}")
	public static List<Object[]> data() {
		return Arrays
				.asList(new Object[][] { 
					{ new String[]{"is", "null"}, new ArrayList<Integer>(Arrays.asList(new Integer[] {})), "2. Term nicht vorhanden" },
					{ new String[]{"naught", "is"}, new ArrayList<Integer>(Arrays.asList(new Integer[] {})), "1. Term nicht vorhanden" },
					{ new String[]{"naught", "null"}, new ArrayList<Integer>(Arrays.asList(new Integer[] {})), "Beide Terme nicht vorhanden" },
					{ new String[]{"there", "is"}, new ArrayList<Integer>().add(0), "Match am Anfang" },
					{ new String[]{"the", "sea"}, new ArrayList<Integer>().add(4), "Match am Ende" },
					{ new String[]{"eating", "collars"}, new ArrayList<Integer>(Arrays.asList(new Integer[] { 5 })), "Match in der Mitte" },
					{ new String[]{"space", "station"}, new ArrayList<Integer>(Arrays.asList(new Integer[] { 2, 6, 7 })), "Mehrere Matches" },
					{ new String[]{"moon", "moon"}, new ArrayList<Integer>(), "Gleiches Wort" },
					{ new String[]{"camels", "four"}, new ArrayList<Integer>(), "falsche Reihenfolge" }
				});
	}

}
