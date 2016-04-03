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

public class TestPositionalSingleWord {

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
		ArrayList<Integer> arrayList = positionalIndex.searchForSingleWord("marcus");
		Collections.sort(arrayList);
		Integer[] array = arrayList.toArray(new Integer[arrayList.size()]);
		assertArrayEquals(new Integer[] { }, array);
	}
	
	@Test
	public void TestTermInOneDocument() {
		ArrayList<Integer> arrayList = positionalIndex.searchForSingleWord("hikaru");
		Collections.sort(arrayList);
		Integer[] array = arrayList.toArray(new Integer[arrayList.size()]);
		assertArrayEquals(new Integer[] { 5 }, array);
	}
	
	@Test
	public void TestTermInMultipleDocuments() {
		ArrayList<Integer> arrayList = positionalIndex.searchForSingleWord("spock");
		Collections.sort(arrayList);
		Integer[] array = arrayList.toArray(new Integer[arrayList.size()]);
		assertArrayEquals(new Integer[] { 1, 2, 4 }, array);
	}
	
	@Test
	public void TestFirstTerm() {
		ArrayList<Integer> arrayList = positionalIndex.searchForSingleWord("pavel");
		Collections.sort(arrayList);
		Integer[] array = arrayList.toArray(new Integer[arrayList.size()]);
		assertArrayEquals(new Integer[] { 6 }, array);
	}
	
	@Test
	public void TestLastTerm() {
		ArrayList<Integer> arrayList = positionalIndex.searchForSingleWord("much");
		Collections.sort(arrayList);
		Integer[] array = arrayList.toArray(new Integer[arrayList.size()]);
		assertArrayEquals(new Integer[] { 0 }, array);
	}

}
