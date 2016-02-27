package _secondGiven;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class InvertedIndex {

	protected HashMap<String, ArrayList<Integer>> index;

	// Bearbeiten sie Aufgabe 1 hier.
	public InvertedIndex(ArrayList<Document> collection) {

		index = new HashMap<String, ArrayList<Integer>>();

		Iterator<Document> it = collection.iterator();
		int docId = -1;
		while (it.hasNext()) {
			Document doc = it.next();
			docId++;
			
			// we will only take unique terms just now as this is a boolean
			ArrayList<String> terms = doc.getUniqueWordList();
			Iterator<String> termIt = terms.iterator();
			while (termIt.hasNext()) {
				String term = termIt.next();
				
				//cleaning the term
				term = term.trim().toLowerCase();
				indexTerm(term, docId);
			}
			
			
			

		}
	}

	// Second method required for Aufgabe 1; index term

	public void indexTerm(String t, int doc) {

		ArrayList<Integer> docList = index.get(t);

		if (docList == null) {
			docList = new ArrayList<Integer>();
			index.put(t, docList);
		}

		docList.add(doc);

	}

	// Bearbeiten sie Aufgabe 2 hier.
	public ArrayList<Integer> searchForSingleWord(String word) {
		ArrayList<Integer> result = index.get(word);
		if (result != null) {
			return result;
		} else {
			return new ArrayList<Integer>();
		}
	}

	// Bearbeiten sie Aufgabe 4 hier
	public ArrayList<Integer> performANDMerge(String firstWord, String secondWord) {
		// empty arraylist to place results
		ArrayList<Integer> results = new ArrayList<Integer>();

		// get postingslists for first and second word and check if they are
		// emtpy
		ArrayList<Integer> first = searchForSingleWord(firstWord);
		ArrayList<Integer> second = searchForSingleWord(secondWord);

		if (first.isEmpty() || second.isEmpty()) {
			return results;
		}

		
		
		// get an iterator for firstWord
		Iterator<Integer> postingsX = first.iterator();
		// get an iterator for secondWord
		Iterator<Integer> postingsY = second.iterator();

		int x = postingsX.next();
		int y = postingsY.next();

		while (true) {
			if (x == y) {
				results.add(x);
				if (postingsX.hasNext()) {
					x = postingsX.next();
				} else
					break;
				if (postingsY.hasNext()) {
					y = postingsY.next();
				} else
					break;
			} else if (x < y) {
				if (postingsX.hasNext()) {
					x = postingsX.next();
				} else
					break;
			} else {
				if (postingsY.hasNext()) {
					y = postingsY.next();
				} else
					break;
			}
		}

		return results;
	}

	// For JUNIT-Tests. This Method must not be edited or deleted
	public HashMap<String, ArrayList<Integer>> getInvertedIndexHashmap() {
		return index;
	}

	// Fuer Aufgabe 5 erweitern sie diese Klasse um weitere Methoden
}
