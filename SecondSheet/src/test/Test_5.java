package test;

import static org.junit.Assert.assertArrayEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.BeforeClass;
import org.junit.Test;

import _secondGiven.FileReader;
import _secondWork.PositionalIndex;

// This tests if a search for a phrase with 2 terms in a set distance to each other returns the correct document-numbers

public class Test_5 {

	// Location of test collection
	private static final String TEST_PATH_3 = "collections/testCollections/third";

	// Variable containing an instance of PositionalIndex
	private PositionalIndex positionalIndex;

	@BeforeClass
	public void setupBeforeClass() throws FileNotFoundException {
		positionalIndex = new PositionalIndex(FileReader.readCollection(TEST_PATH_3));
	}

	@Test
	public void TestAbsentFirstTerm() {
		ArrayList<Integer> arrayList = positionalIndex.searchForPhrase(new String[]{"marcus", "doctor"}, 1);
		Collections.sort(arrayList);
		Integer[] array = arrayList.toArray(new Integer[arrayList.size()]);
		assertArrayEquals(new Integer[] { }, array);
	}

	@Test
	public void TestAbsentSecondTerm() {
		ArrayList<Integer> arrayList = positionalIndex.searchForPhrase(new String[]{"doctor", "Qu"}, 1);
		Collections.sort(arrayList);
		Integer[] array = arrayList.toArray(new Integer[arrayList.size()]);
		assertArrayEquals(new Integer[] { }, array);
	}

	@Test
	public void TestDistanceofTwo() {
		ArrayList<Integer> arrayList = positionalIndex.searchForPhrase(new String[]{"doctor", "horatio"}, 2);
		Collections.sort(arrayList);
		Integer[] array = arrayList.toArray(new Integer[arrayList.size()]);
		assertArrayEquals(new Integer[] { 2 }, array);
	}

	@Test
	public void TestDistanceOfThree() {
		ArrayList<Integer> arrayList = positionalIndex.searchForPhrase(new String[]{"souls", "encountered"}, 3);
		Collections.sort(arrayList);
		Integer[] array = arrayList.toArray(new Integer[arrayList.size()]);
		assertArrayEquals(new Integer[] { 1 }, array);
	}

	@Test
	public void TestMultipleMatchesAllCorrect() {
		ArrayList<Integer> arrayList = positionalIndex.searchForPhrase(new String[]{"commander", "spock"});
		Collections.sort(arrayList);
		Integer[] array = arrayList.toArray(new Integer[arrayList.size()]);
		assertArrayEquals(new Integer[] { 1, 2 }, array);
	}

	@Test
	public void TestMultipleMatchesSomeCorrect() {
		ArrayList<Integer> arrayList = positionalIndex.searchForPhrase(new String[]{"logic", "spock"}, 3);
		Collections.sort(arrayList);
		Integer[] array = arrayList.toArray(new Integer[arrayList.size()]);
		assertArrayEquals(new Integer[] { 2 }, array);
	}
	
	@Test
	public void TestReverseOrderedTerms() {
		ArrayList<Integer> arrayList = positionalIndex.searchForPhrase(new String[]{"uhura", "penda"}, 1);
		Collections.sort(arrayList);
		Integer[] array = arrayList.toArray(new Integer[arrayList.size()]);
		assertArrayEquals(new Integer[] { 4 }, array);
	}

	@Test
	public void TestReverseOrderedTermsDistanceOfThree() {
		ArrayList<Integer> arrayList = positionalIndex.searchForPhrase(new String[]{"navigator", "chekov"}, 3);
		Collections.sort(arrayList);
		Integer[] array = arrayList.toArray(new Integer[arrayList.size()]);
		assertArrayEquals(new Integer[] { 6 }, array);
	}
}
