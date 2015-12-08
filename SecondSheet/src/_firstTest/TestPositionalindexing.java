// This tests the searchmethod of the inverted index. The index constructor has to work for this

package _firstTest;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import _firstGiven.FileReader;
import _firstGiven.PositionalIndexEntry;
import _firstWork.PositionalIndex;

@RunWith(Parameterized.class)
public class TestPositionalindexing {

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

	public TestPositionalindexing(String inputString, PositionalIndexEntry expectedResult)
			throws FileNotFoundException {
		// these Variables are used to test the two methods
		positionalIndex = new PositionalIndex(FileReader.readCollection(TEST_PATH_2));

		this.inputString = inputString;
		this.expectedResult = expectedResult;
	}

	@Test
	public void testSearchForSingleWord() {
		//assertEquals(expectedResult.getDocMap(), positionalIndex.getPositionalWordTreeMap(inputString));
	}

	// This method sets up the data for the tests
	@Parameters (name = "is \"{0}\" indexed correctly?")
	public static List<Object[]> data() {
		return Arrays
				.asList(new Object[][] { 
					{ "there", new PositionalIndexEntry(1, getTreemap(new int [][]{{0},{},{},{},{}}), "there") },
					{ "is", new PositionalIndexEntry(4, getTreemap(new int [][]{{1},{2},{1,5},{},{}}), "is") },
					{ "no", new PositionalIndexEntry(2, getTreemap(new int [][]{{2},{},{2},{},{}}), "no") },
					{ "green", new PositionalIndexEntry(2, getTreemap(new int [][]{{3},{1},{},{},{}}), "green") },
					{ "yellow", new PositionalIndexEntry(2, getTreemap(new int [][]{{4},{},{},{4},{}}), "yellow") },
					{ "purple", new PositionalIndexEntry(1, getTreemap(new int [][]{{5},{},{},{},{}}), "purple") },
					{ "corn", new PositionalIndexEntry(4, getTreemap(new int [][]{{6},{5},{10},{},{6}}), "corn") },
					{ "on", new PositionalIndexEntry(3, getTreemap(new int [][]{{7},{6},{},{},{7}}), "on") },
					{ "the", new PositionalIndexEntry(3, getTreemap(new int [][]{{8},{7},{},{},{8,11}}), "the") },
					{ "moon", new PositionalIndexEntry(4, getTreemap(new int [][]{{9},{8},{3},{},{2}}), "moon") },
					{ "soylent", new PositionalIndexEntry(1, getTreemap(new int [][]{{},{0},{},{},{}}), "soylent") },
					{ "people", new PositionalIndexEntry(2, getTreemap(new int [][]{{},{3},{},{0},{}}), "people") },
					{ "eating", new PositionalIndexEntry(2, getTreemap(new int [][]{{},{4},{},{2},{}}), "eating") },
					{ "this", new PositionalIndexEntry(3, getTreemap(new int [][]{{},{},{0,4},{3},{}}), "this") },
					{ "a", new PositionalIndexEntry(1, getTreemap(new int [][]{{},{},{6},{},{}}), "a") },
					{ "space", new PositionalIndexEntry(1, getTreemap(new int [][]{{},{},{7},{},{}}), "space") },
					{ "station", new PositionalIndexEntry(3, getTreemap(new int [][]{{},{},{8},{5},{3}}), "station") },
					{ "of", new PositionalIndexEntry(2, getTreemap(new int [][]{{},{},{9},{},{10}}), "of") },
					{ "are", new PositionalIndexEntry(1, getTreemap(new int [][]{{},{},{},{1},{}}), "are") },
					{ "when", new PositionalIndexEntry(1, getTreemap(new int [][]{{},{},{},{6},{}}), "when") },
					{ "dog", new PositionalIndexEntry(2, getTreemap(new int [][]{{},{},{},{7},{0}}), "dog") },
					{ "rain", new PositionalIndexEntry(1, getTreemap(new int [][]{{},{},{},{8},{}}), "rain") },
					{ "from", new PositionalIndexEntry(1, getTreemap(new int [][]{{},{},{},{},{1}}), "from") },
					{ "will", new PositionalIndexEntry(1, getTreemap(new int [][]{{},{},{},{},{4}}), "will") },
					{ "grow", new PositionalIndexEntry(1, getTreemap(new int [][]{{},{},{},{},{5}}), "grow") },
					{ "bottom", new PositionalIndexEntry(1, getTreemap(new int [][]{{},{},{},{},{9}}), "bottom") },
					{ "sea", new PositionalIndexEntry(1, getTreemap(new int [][]{{},{},{},{},{12}}), "sea") }
				});
	}
	
	// A method to simply write the method above (data) in a readable form.
	// This method just creates a treemap from a twodimensional array
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
	}

}
