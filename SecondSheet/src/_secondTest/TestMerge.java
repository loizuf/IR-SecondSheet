package _secondTest;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import _secondGiven.BooleanDocument;
import _secondGiven.FileReader;
import _secondGiven.InvertedIndex;

@RunWith(Parameterized.class)
public class TestMerge {

	// Location of test collection
	private static final String TEST_PATH_2 = "collections/testCollections/second";

	// Variable containing an instance of InvertedIndex
	private InvertedIndex invertedIndex;

	// parameterized variables
	private String[] inputTerms;
	private ArrayList<Integer> expectedResult;

	public TestMerge(String[] inputTerms, ArrayList<Integer> expectedResult, String message) throws FileNotFoundException {
		// these Variables are used to test the two methods
		invertedIndex = new InvertedIndex(FileReader.readCollection(TEST_PATH_2));

		this.inputTerms = inputTerms;
		this.expectedResult = expectedResult;
	}

	@Test
	public void testPerformANDMerge() {
		assertEquals(expectedResult, invertedIndex.performANDMerge(inputTerms[0], inputTerms[1]));
	}
	
	// This method sets up the data for the tests
	@Parameters (name = "{2}")
	public static List<Object[]> data() {
		return Arrays
				.asList(new Object[][] { 
					{ new String[]{"is", "null"}, new ArrayList<Integer>(Arrays.asList(new Integer[] {})), "2. Term nicht vorhanden" },
					{ new String[]{"naught", "is"}, new ArrayList<Integer>(Arrays.asList(new Integer[] {})), "1. Term nicht vorhanden" },
					{ new String[]{"naught", "null"}, new ArrayList<Integer>(Arrays.asList(new Integer[] {})), "Beide Terme nicht vorhanden" },
					{ new String[]{"no", "green"}, new ArrayList<Integer>(Arrays.asList(new Integer[] { 1 })), "Match am Anfang" },
					{ new String[]{"eating", "this"}, new ArrayList<Integer>(Arrays.asList(new Integer[] { 4 })), "Match am Ende" },
					{ new String[]{"moon", "eating"}, new ArrayList<Integer>(Arrays.asList(new Integer[] { 2 })), "Match in der Mitte" },
					{ new String[]{"corn", "station"}, new ArrayList<Integer>(Arrays.asList(new Integer[] { 3, 5 })), "Mehrere Matches" },
					{ new String[]{"moon", "moon"}, new ArrayList<Integer>(Arrays.asList(new Integer[] { 1, 2, 3, 5 })), "Gleiches Wort" }
				});
	}

}
