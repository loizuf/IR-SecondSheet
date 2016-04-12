package test;

import static org.junit.Assert.assertArrayEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.BeforeClass;
import org.junit.Test;

import _secondGiven.FileReader;
import _secondWork.BiWordIndex;

//This class tests the Indexing of the BiWord-Index

public class Test_1b {

	// Location of test collection
	private static final String TEST_PATH_3 = "collections/testCollections/third";

	// Variable containing an instance of BiwordIndex
	private BiWordIndex biWordIndex;
	
	@BeforeClass
	public void setupBeforeClass() throws FileNotFoundException {
		biWordIndex = new BiWordIndex(FileReader.readCollection(TEST_PATH_3));
	}

	@Test
	public void TestAbsentTerms() {
		ArrayList<Integer> arrayList = biWordIndex.searchForSingleWord("doctor marcus");
		Integer[] array = arrayList.toArray(new Integer[arrayList.size()]);
		assertArrayEquals(new Integer[] { }, array);
	}

	@Test
	public void TestTermsInOneDocument() {
		ArrayList<Integer> arrayList = biWordIndex.searchForSingleWord("half vulcan");
		Integer[] array = arrayList.toArray(new Integer[arrayList.size()]);
		assertArrayEquals(new Integer[] { 1 }, array);
	}

	@Test
	public void TestFirstTerms() {
		ArrayList<Integer> arrayList = biWordIndex.searchForSingleWord("lieutenant hikaru");
		Integer[] array = arrayList.toArray(new Integer[arrayList.size()]);
		assertArrayEquals(new Integer[] { 5 }, array);
	}

	@Test
	public void TestLastTerms() {
		ArrayList<Integer> arrayList = biWordIndex.searchForSingleWord("starship reliant");
		Integer[] array = arrayList.toArray(new Integer[arrayList.size()]);
		assertArrayEquals(new Integer[] { 6 }, array);
	}

	@Test
	public void TestTermsInMultipleDocuments() {
		ArrayList<Integer> arrayList = biWordIndex.searchForSingleWord("commander spock");
		Collections.sort(arrayList);
		Integer[] array = arrayList.toArray(new Integer[arrayList.size()]);
		assertArrayEquals(new Integer[] { 1, 2 }, array);
	}
}
