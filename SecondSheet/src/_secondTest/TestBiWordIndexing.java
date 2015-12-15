// This tests the searchmethod of the inverted index. The index constructor has to work for this

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
import _secondWork.BiWordIndex;
import _secondWork.PositionalIndex;

@RunWith(Parameterized.class)
public class TestBiWordIndexing {

	// Location of two (nonsensical) collections
	// first collection is simple, second contains upper and lowercase as well
	// as unnecessary whitespace (which isn't important for the first sheet as
	// the actual collection isnt lower or uppercase)
	private static final String TEST_PATH_2 = "collections/testCollections/second";

	// Variable containing an instance of BiwordIndex
	private BiWordIndex biWordIndex;

	// parameterized variables
	private String inputString;
	private ArrayList<Integer> expectedResult;

	public TestBiWordIndexing(String inputString, ArrayList<Integer> expectedResult, String message)
			throws FileNotFoundException {
		biWordIndex = new BiWordIndex(FileReader.readCollection(TEST_PATH_2));

		this.inputString = inputString;
		this.expectedResult = expectedResult;
	}

	@Test
	public void testSearchForSingleWord() {
		// compare expected result with the result of the students
		assertEquals(expectedResult, biWordIndex.searchForSingleWord(inputString));
	}

	// This method sets up the data for the tests
	// the third variable is used to display a description to the students
	@Parameters (name = "{2}")
	public static List<Object[]> data() {
		return Arrays
				.asList(new Object[][] { 
					{ "president obama", new ArrayList<Integer>(Arrays.asList(new Integer[] {  })), "no document" },
					{ "soylent green", new ArrayList<Integer>(Arrays.asList(new Integer[] { 1 })), "single document" },
					{ "there is", new ArrayList<Integer>(Arrays.asList(new Integer[] { 0, 5 })), "at the start of document" },
					{ "no moon", new ArrayList<Integer>(Arrays.asList(new Integer[] { 2, 6 })), "at the end of document" },
					{ "space station", new ArrayList<Integer>(Arrays.asList(new Integer[] { 2, 6, 7 })), "more than 2 documents" }
				});
	}

}
