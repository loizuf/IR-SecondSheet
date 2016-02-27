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
import _secondWork.PositionalIndex;

@RunWith(Parameterized.class)
public class TestPositions {

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
	private int inputDocNumber;

	public TestPositions(String inputString, ArrayList<Integer> expectedResult, int inputDocNumber, String message)
			throws FileNotFoundException {
		positionalIndex = new PositionalIndex(FileReader.readCollection(TEST_PATH_2));

		this.inputString = inputString;
		this.expectedResult = expectedResult;
		this.inputDocNumber = inputDocNumber;
	}

	@Test
	public void testSearchForSingleWord() {
		// compare expected result with the result of the students
		assertEquals(expectedResult, positionalIndex.searchForSingleWordInDocument(inputString, inputDocNumber));
	}

	// This method sets up the data for the tests
	// the third variable is used to display a description to the students
	@Parameters (name = "{3}")
	public static List<Object[]> data() {
		return Arrays
				.asList(new Object[][] { 
					{ "obama", new ArrayList<Integer>(), 2, "Word not in collection" },
					{ "there", new ArrayList<Integer>(), 2, "Word in other documents, but not here" },
					{ "there", new ArrayList<Integer>().add(0), 0, "first Word" },
					{ "corn", new ArrayList<Integer>().add(10), 3, "last word" },
					{ "purple", new ArrayList<Integer>().add(5), 0, "word only in first document" },
					{ "four", new ArrayList<Integer>().add(1), 7, "word only in last document" },
					{ "is", new ArrayList<Integer>().add(2), 1, "word in multiple documents" },
					{ "is", new ArrayList<Integer>().addAll(Arrays.asList(new Integer[] {1, 5})), 2, "word in multiple times int this document" }
				});
	}

}
