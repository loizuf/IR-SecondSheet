package _secondTest;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import _secondGiven.FileReader;
import _secondGiven.Document;
import _secondWork.BiWordIndex;
import _secondWork.PositionalIndex;

public class TestSearchProximity {

	// Location of test collection
	private static final String TEST_PATH_3 = "collections/testCollections/third";

	// Variable containing an instance of PositionalIndex
	private PositionalIndex positionalIndex;

	// parameterized variables
	private String[] inputTerms;
	private ArrayList<Integer> expectedResult;

	public TestSearchProximity(String[] inputTerms, ArrayList<Integer> expectedResult, String message) throws FileNotFoundException {
		

		this.inputTerms = inputTerms;
		this.expectedResult = expectedResult;
	}

	@BeforeClass
	public void setupBeforeClass() throws FileNotFoundException {
		positionalIndex = new PositionalIndex(FileReader.readCollection(TEST_PATH_3));
	}

	@Test
	public void TestAbsentTerms() {
		ArrayList<Integer> arrayList = positionalIndex.searchForPhrase(new String[]{"", ""}, 0);
		Integer[] array = arrayList.toArray(new Integer[arrayList.size()]);
		assertArrayEquals(new Integer[] { }, array);
	}

	@Test
	public void testPerformANDMerge() {
		// compare expected result with the result of the students
		assertEquals(expectedResult, positionalIndex.searchForPhrase(inputTerms));
	}
	
	// This method sets up the data for the tests
	// the third variable is used to display a description to the students
	@Parameters (name = "{3}")
	public static List<Object[]> data() {
		return Arrays
				.asList(new Object[][] { 
					{ new String[]{"is", "null"}, new ArrayList<Integer>(Arrays.asList(new Integer[] {})), 1, "2. Term nicht vorhanden" },
					{ new String[]{"naught", "is"}, new ArrayList<Integer>(Arrays.asList(new Integer[] {})), 1, "1. Term nicht vorhanden" },
					{ new String[]{"naught", "null"}, new ArrayList<Integer>(Arrays.asList(new Integer[] {})), 1, "Beide Terme nicht vorhanden" },
					{ new String[]{"there", "is"}, new ArrayList<Integer>().add(0), 1, "Match am Anfang" },
					{ new String[]{"the", "sea"}, new ArrayList<Integer>().add(4), 1, "Match am Ende" },
					{ new String[]{"green", "eating"}, new ArrayList<Integer>(Arrays.asList(new Integer[] { 1, 5 })), 3, "Mehrere Treffer" },
					{ new String[]{"eating", "green"}, new ArrayList<Integer>(Arrays.asList(new Integer[] { 1, 5 })), 3, "Umgedrehte Reihenfolge" },
					{ new String[]{"eating", "green"}, new ArrayList<Integer>(Arrays.asList(new Integer[] { 1, 5 })), 4, "k größer" },
					{ new String[]{"eating", "green"}, new ArrayList<Integer>(Arrays.asList(new Integer[] { 5 })), 2, "k kleiner" },
					{ new String[]{"eating", "green"}, new ArrayList<Integer>(Arrays.asList(new Integer[] {})), 1, "k zu klein" },
					{ new String[]{"space", "station"}, new ArrayList<Integer>(Arrays.asList(new Integer[] {})), 0, "k=0" },
					{ new String[]{"moon", "moon"}, new ArrayList<Integer>(), 1, "Gleiches Wort" }
				});
	}

}
