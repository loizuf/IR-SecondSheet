package test;

import static org.junit.Assert.assertArrayEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.BeforeClass;
import org.junit.Test;

import _secondGiven.FileReader;
import _secondWork.PositionalIndex;

// This tests if a search for a phrase with 2 terms returns the correct document-numbers

public class Test_3c {

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
		ArrayList<Integer> arrayList = positionalIndex.searchForPhrase(new String[]{"marcus", "doctor"});
		Collections.sort(arrayList);
		Integer[] array = arrayList.toArray(new Integer[arrayList.size()]);
		assertArrayEquals(new Integer[] { }, array);
	}

	@Test
	public void TestAbsentSecondTerm() {
		ArrayList<Integer> arrayList = positionalIndex.searchForPhrase(new String[]{"doctor", "Qu"});
		Collections.sort(arrayList);
		Integer[] array = arrayList.toArray(new Integer[arrayList.size()]);
		assertArrayEquals(new Integer[] { }, array);
	}

	@Test
	public void TestMatchAtTheStart() {
		ArrayList<Integer> arrayList = positionalIndex.searchForPhrase(new String[]{"doctor", "leonard"});
		Collections.sort(arrayList);
		Integer[] array = arrayList.toArray(new Integer[arrayList.size()]);
		assertArrayEquals(new Integer[] { 2 }, array);
	}

	@Test
	public void TestMatchAtTheEnd() {
		ArrayList<Integer> arrayList = positionalIndex.searchForPhrase(new String[]{"most", "human"});
		Collections.sort(arrayList);
		Integer[] array = arrayList.toArray(new Integer[arrayList.size()]);
		assertArrayEquals(new Integer[] { 1 }, array);
	}

	@Test
	public void TestMultipleMatches() {
		ArrayList<Integer> arrayList = positionalIndex.searchForPhrase(new String[]{"commander", "spock"});
		Collections.sort(arrayList);
		Integer[] array = arrayList.toArray(new Integer[arrayList.size()]);
		assertArrayEquals(new Integer[] { 1, 2 }, array);
	}
	
	@Test
	public void TestReverseOrderedTerms() {
		ArrayList<Integer> arrayList = positionalIndex.searchForPhrase(new String[]{"officer", "communications"});
		Collections.sort(arrayList);
		Integer[] array = arrayList.toArray(new Integer[arrayList.size()]);
		assertArrayEquals(new Integer[] { }, array);
	}

	@Test
	public void TestTermsNotNextToEachOther() {
		ArrayList<Integer> arrayList = positionalIndex.searchForPhrase(new String[]{"doctor", "enterprise"});
		Collections.sort(arrayList);
		Integer[] array = arrayList.toArray(new Integer[arrayList.size()]);
		assertArrayEquals(new Integer[] { }, array);
	}
}
