// This tests the searchmethod of the inverted index. The index constructor has to work for this

package _secondTest;

import static org.junit.Assert.assertArrayEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.BeforeClass;
import org.junit.Test;

import _secondGiven.FileReader;
import _secondWork.PositionalIndex;

public class TestPositions {

	// Location of two (nonsensical) collections
	// first collection is simple, second contains upper and lowercase as well
	// as unnecessary whitespace (which isn't important for the first sheet as
	// the actual collection isnt lower or uppercase)
	private static final String TEST_PATH_3 = "collections/testCollections/third";

	// Variable containing an instance of PositionalIndex
	private PositionalIndex positionalIndex;

	@BeforeClass
	public void setupBeforeClass() throws FileNotFoundException {
		positionalIndex = new PositionalIndex(FileReader.readCollection(TEST_PATH_3));
	}

	@Test
	public void TestAbsentTerm() {
		ArrayList<Integer> arrayList = positionalIndex.searchForSingleWordInDocument("marcus", 0);
		Collections.sort(arrayList);
		Integer[] array = arrayList.toArray(new Integer[arrayList.size()]);
		assertArrayEquals(new Integer[] { }, array);
	}

	@Test
	public void TestTermInOtherDocument() {
		ArrayList<Integer> arrayList = positionalIndex.searchForSingleWordInDocument("hikaru", 0);
		Collections.sort(arrayList);
		Integer[] array = arrayList.toArray(new Integer[arrayList.size()]);
		assertArrayEquals(new Integer[] { }, array);
	}

	@Test
	public void TestTermInFirstDocument() {
		ArrayList<Integer> arrayList = positionalIndex.searchForSingleWordInDocument("kirk", 0);
		Collections.sort(arrayList);
		Integer[] array = arrayList.toArray(new Integer[arrayList.size()]);
		assertArrayEquals(new Integer[] { 4 }, array);
	}

	@Test
	public void TestTermInLastDocument() {
		ArrayList<Integer> arrayList = positionalIndex.searchForSingleWordInDocument("chekov", 6);
		Collections.sort(arrayList);
		Integer[] array = arrayList.toArray(new Integer[arrayList.size()]);
		assertArrayEquals(new Integer[] { 2 }, array);
	}

	@Test
	public void TestFirstTerm() {
		ArrayList<Integer> arrayList = positionalIndex.searchForSingleWordInDocument("chief", 3);
		Collections.sort(arrayList);
		Integer[] array = arrayList.toArray(new Integer[arrayList.size()]);
		assertArrayEquals(new Integer[] { 0 }, array);
	}

	@Test
	public void TestLastTerm() {
		ArrayList<Integer> arrayList = positionalIndex.searchForSingleWordInDocument("human", 1);
		Collections.sort(arrayList);
		Integer[] array = arrayList.toArray(new Integer[arrayList.size()]);
		assertArrayEquals(new Integer[] { 36 }, array);
	}

	@Test
	public void TestTermInMultipleDocuments() {
		ArrayList<Integer> arrayList = positionalIndex.searchForSingleWordInDocument("spock", 4);
		Collections.sort(arrayList);
		Integer[] array = arrayList.toArray(new Integer[arrayList.size()]);
		assertArrayEquals(new Integer[] { 26 }, array);
	}

	@Test
	public void TestTermMultipleTimesInOneDocument() {
		ArrayList<Integer> arrayList = positionalIndex.searchForSingleWordInDocument("the", 5);
		Collections.sort(arrayList);
		Integer[] array = arrayList.toArray(new Integer[arrayList.size()]);
		assertArrayEquals(new Integer[] { 4, 7, 13, 17 }, array);
	}
}
