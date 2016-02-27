package _secondWork;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import _secondGiven.Document;
import _secondGiven.InvertedIndex;

public class BiWordIndex extends InvertedIndex{

	private HashMap<String, ArrayList<Integer>> index;
	
	// Bearbeiten sie Aufgabe 1 hier
	public BiWordIndex(ArrayList<Document> collection) {
		super(collection);
	}

	// We already know this method from the last assignement
	public ArrayList<Integer> searchForSingleWord(String word) {
		ArrayList<Integer> result = index.get(word);
		if (result != null) {
			return result;
		} else {
			return new ArrayList<Integer>();
		}
	}

}
