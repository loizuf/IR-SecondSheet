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
import _secondGiven.Document;
import _secondWork.PositionalIndex;

@RunWith(Parameterized.class)
public class TestSearchPhrases {

	// Location of test collection
	private static final String TEST_PATH_2 = "collections/testCollections/second";

	// Variable containing an instance of PositionalIndex
	private PositionalIndex positionalIndex;

	// parameterized variables
	private String[] inputTerms;
	private ArrayList<Integer> expectedResult;

	public TestSearchPhrases(String[] inputTerms, ArrayList<Integer> expectedResult, String message) throws FileNotFoundException {
		positionalIndex = new PositionalIndex(FileReader.readCollection(TEST_PATH_2));

		this.inputTerms = inputTerms;
		this.expectedResult = expectedResult;
	}

	@Test
	public void testPerformANDMerge() {
		// compare expected result with the result of the students
		assertEquals(expectedResult, positionalIndex.searchForPhrase(inputTerms));
	}
	
	// This method sets up the data for the tests
	// the third variable is used to display a description to the students
	@Parameters (name = "{2}")
	public static List<Object[]> data() {
		return Arrays
				.asList(new Object[][] { 
					{ new String[]{"is", "null"}, new ArrayList<Integer>(), "2. Term nicht vorhanden" },
					{ new String[]{"naught", "is"}, new ArrayList<Integer>(), "1. Term nicht vorhanden" },
					{ new String[]{"naught", "null"}, new ArrayList<Integer>(), "Beide Terme nicht vorhanden" },
					{ new String[]{"there", "is"}, new ArrayList<Integer>().add(0), "Match am Anfang" },
					{ new String[]{"the", "sea"}, new ArrayList<Integer>().add(4), "Match am Ende" },
					{ new String[]{"eating", "collars"}, new ArrayList<Integer>().add(5), "Match in der Mitte" },
					{ new String[]{"space", "station"}, new ArrayList<Integer>(Arrays.asList(new Integer[] { 2, 6, 7 })), "Mehrere Matches" },
					{ new String[]{"moon", "moon"}, new ArrayList<Integer>(), "Gleiches Wort" },
					{ new String[]{"there", "is", "no"}, new ArrayList<Integer>().add(0), "3 Wörter, Anfang" },
					{ new String[]{"is", "people", "eating"}, new ArrayList<Integer>().add(1), "3 Wörter, Mitte" },
					{ new String[]{"station", "of", "corn"}, new ArrayList<Integer>().add(2), "3 Wörter, Ende" },
					{ new String[]{"are", "eating", "tree"}, new ArrayList<Integer>().add(3), "2 von 3" },
					{ new String[]{"is", "green", "no"}, new ArrayList<Integer>(), "falsche Reihenfolge" },
					{ new String[]{"are", "people", "collars"}, new ArrayList<Integer>(), "nicht nebeneinander" },
					{ new String[]{"is", "no", "green", "space"}, new ArrayList<Integer>().add(6), "4 Wörter" }
				});
	}

}
