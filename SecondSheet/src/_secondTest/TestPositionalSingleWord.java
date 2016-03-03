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
import _secondGiven.Document;
import _secondWork.PositionalIndex;

@RunWith(Parameterized.class)
public class TestPositionalSingleWord {

	// Location of two (nonsensical) collections
	// first collection is simple, second contains upper and lowercase as well
	// as unnecessary whitespace (which isn't important for the first sheet as
	// the actual collection isnt lower or uppercase)
	private static final String TEST_PATH_2 = "collections/testCollections/second";

	// Variable containing an instance of PositionalIndex
	private PositionalIndex positionalIndex;

	// parameterized variables
	private String inputString;
	private ArrayList<Integer> expectedResult;

	public TestPositionalSingleWord(String inputString, ArrayList<Integer> expectedResult, String message)
			throws FileNotFoundException {
		positionalIndex = new PositionalIndex(FileReader.readCollection(TEST_PATH_2));

		this.inputString = inputString;
		this.expectedResult = expectedResult;
	}

	@Test
	public void testSearchForSingleWord() {
		// compare expected result with the result of the students
		assertEquals(expectedResult, positionalIndex.searchForSingleWord(inputString));
	}
	
	// This method sets up the data for the tests
	// the third variable is used to display a description to the students
	@Parameters (name = "{2}")
	public static List<Object[]> data() {
		return Arrays
				.asList(new Object[][] { 
					{ "purple", new ArrayList<Integer>(Arrays.asList(new Integer[] { 0 })), "Single Document" },
					{ "corn", new ArrayList<Integer>(Arrays.asList(new Integer[] { 0, 1, 2, 4 })), "Multiple Documents" },
					{ "moon", new ArrayList<Integer>(Arrays.asList(new Integer[] { 0, 1, 2, 4 })), "Term at the end of a Document" },
					{ "soylent", new ArrayList<Integer>(Arrays.asList(new Integer[] { 2 })), "Term at the start of a Document" },
					{ "cook", new ArrayList<Integer>(Arrays.asList(new Integer[] { })), "No Document" } 
				});
	}

}
