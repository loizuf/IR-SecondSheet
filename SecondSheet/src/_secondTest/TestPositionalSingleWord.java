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
import _secondGiven.PositionalIndexEntry;
import _secondWork.PositionalIndex;

@RunWith(Parameterized.class)
public class TestPositionalSingleWord {

	// Location of two (nonsensical) collections
	// first collection is simple, second contains upper and lowercase as well
	// as unnecessary whitespace (which isn't important for the first sheet as
	// the actual collection isnt lower or uppercase)
	private static final String TEST_PATH_2 = "collections/testCollections/second";

	// Variable containing an instance of InvertedIndex
	private PositionalIndex positionalIndex;

	// parameterized variables
	private String inputString;
	private PositionalIndexEntry expectedResult;

	public TestPositionalSingleWord(String inputString, PositionalIndexEntry expectedResult, String message)
			throws FileNotFoundException {
		// these Variables are used to test the two methods
		positionalIndex = new PositionalIndex(FileReader.readCollection(TEST_PATH_2));

		this.inputString = inputString;
		this.expectedResult = expectedResult;
	}

	@Test
	public void testSearchForSingleWord() {
		assertEquals(expectedResult.getDocMap(), positionalIndex.searchForSingleWord(inputString));
	}
	
	// This method sets up the data for the tests
	@Parameters (name = "{2}")
	public static List<Object[]> data() {
		return Arrays
				.asList(new Object[][] { 
					{ "purple", new ArrayList<Integer>(Arrays.asList(new Integer[] { 1 })), "Single Document" },
					{ "corn", new ArrayList<Integer>(Arrays.asList(new Integer[] { 1, 2, 3, 5 })), "Multiple Documents" },
					{ "moon", new ArrayList<Integer>(Arrays.asList(new Integer[] { 1, 2, 3, 5 })), "Term at the end of a Document" },
					{ "soylent", new ArrayList<Integer>(Arrays.asList(new Integer[] { 2 })), "Term at the start of a Document" },
					{ "cook", new ArrayList<Integer>(Arrays.asList(new Integer[] { })), "No Document" } 
				});
	}
	
	// A method to simply write the method above (data) in a readable form.
	// This method just creates a treemap from a twodimensional array
	/*
	private static TreeMap<Integer, ArrayList<Integer>> getTreemap(int[][] array) {
		TreeMap<Integer, ArrayList<Integer>> result = new TreeMap<Integer, ArrayList<Integer>>();
		for (int i = 0; i < array.length; i++) {
			ArrayList<Integer> currentValue = new ArrayList<>();
			for (int j = 0; j < array[i].length; j++) {
				currentValue.add(array[i][j]);
			}
			
			// This is important as the indexing process doesnt keep empty arraylists for every document
			if(!currentValue.isEmpty())
			result.put(i, currentValue);
		}
		return result;
	}*/

}
