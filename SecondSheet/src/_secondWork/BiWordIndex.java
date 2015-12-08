package _secondWork;

import java.util.ArrayList;
import java.util.HashMap;

import _secondGiven.BooleanDocument;

public class BiWordIndex {

	private HashMap<String, ArrayList<Integer>> biWordIndex;

	// Bearbeiten sie Aufgabe 1 hier
	public BiWordIndex(ArrayList<BooleanDocument> collection) {
		biWordIndex = new HashMap<String, ArrayList<Integer>>();
	}

	// We already know this method from the last assignement
	public ArrayList<Integer> searchForSingleWord(String word) {
		ArrayList<Integer> result = biWordIndex.get(word);
		if (result != null) {
			return result;
		} else {
			return new ArrayList<Integer>();
		}
	}

	// For JUNIT-Tests. This Method must not be edited or deleted
	public HashMap<String, ArrayList<Integer>> getBiWordHashmap() {
		return biWordIndex;
	}

}
