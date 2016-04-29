package test;

import static org.junit.Assert.assertArrayEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.BeforeClass;
import org.junit.Test;

import _secondGiven.FileReader;
import _secondWork.PositionalIndex;

// This tests if the search for a word returns the correct document-numbers

public class Test_3a {

	// Location of test collection
	private static final String TEST_PATH_3 = "collections/testCollections/third";

	// Variable containing an instance of PositionalIndex
	private static PositionalIndex positionalIndex;

	@BeforeClass
	public static void setupBeforeClass() throws FileNotFoundException {
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
