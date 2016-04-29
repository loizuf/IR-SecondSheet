package test;

import static org.junit.Assert.assertArrayEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.BeforeClass;
import org.junit.Test;

import _secondGiven.FileReader;
import _secondWork.PositionalIndex;

// This tests if a search for a phrase with 3 terms returns the correct document-numbers

public class Test_4 {

	// Location of test collection
	private static final String TEST_PATH_3 = "collections/testCollections/third";

	// Variable containing an instance of PositionalIndex
	private static PositionalIndex positionalIndex;

	@BeforeClass
	public static void setupBeforeClass() throws FileNotFoundException {
		positionalIndex = new PositionalIndex(FileReader.readCollection(TEST_PATH_3));
	}

	@Test
	public void TestThreeTerms() {
		ArrayList<Integer> arrayList = positionalIndex.searchForPhrase(new String[]{"his", "soul", "was"});
		Collections.sort(arrayList);
		Integer[] array = arrayList.toArray(new Integer[arrayList.size()]);
		assertArrayEquals(new Integer[] { 1 }, array);
	}

	@Test
	public void TestThreeTermsLastOneMissing() {
		ArrayList<Integer> arrayList = positionalIndex.searchForPhrase(new String[]{"psychiatrist", "physicist", "marcus"});
		Collections.sort(arrayList);
		Integer[] array = arrayList.toArray(new Integer[arrayList.size()]);
		assertArrayEquals(new Integer[] { }, array);
	}
}
