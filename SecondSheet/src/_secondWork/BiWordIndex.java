package _secondWork;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import _secondGiven.Document;
import _secondGiven.InvertedIndex;

public class BiWordIndex extends InvertedIndex{

	// Diese Variable KANN verwendet werden um den BiWord-Index zu speichern.
	// Sie können auch eine eigene Variable anlegen.
	private HashMap<String, ArrayList<Integer>> index;
	
	// Bearbeiten sie Aufgabe 1 hier
	public BiWordIndex(ArrayList<Document> collection) {
		super(collection);
	}

	// Wir kennen diese Aufgabe vom ersten Arbeitsblatt
	// Sie können ihren code übernehmen
	public ArrayList<Integer> searchForSingleWord(String word) {
		return null;
	}
}
