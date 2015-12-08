package _firstWork;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import _firstGiven.BooleanDocument;

public class BiWordIndex {

	private HashMap<String, ArrayList<Integer>> biWordIndex;

	public BiWordIndex(ArrayList<BooleanDocument> collection) {
		biWordIndex = new HashMap<String, ArrayList<Integer>>();

		Iterator<BooleanDocument> it = collection.iterator();
		int docId = 0;

		// We use this to index the last-term + current-term combinations
		String lastWord;
		while (it.hasNext()) {
			BooleanDocument doc = it.next();
			docId++;

			// we will only take unique terms just now as this is a boolean
			// model
			ArrayList<String> terms = doc.getUniqueWordList();
			Iterator<String> termIt = terms.iterator();
			String term = null;
			while (termIt.hasNext()) {
				lastWord = term;
				term = termIt.next();

				// cleaning the term
				term = term.trim().toLowerCase();
				indexTerm(term, docId);

				// This is just to check for the first Word where we dont need
				// to index a Bi-Word-Term
				if (lastWord != null) {
					indexTerm(lastWord + " " + term, docId);
				}
			}

		}
	}

	public void indexTerm(String t, int doc) {

		ArrayList<Integer> docList = biWordIndex.get(t);

		if (docList == null) {
			docList = new ArrayList<Integer>();
			biWordIndex.put(t, docList);
		}

		docList.add(doc);

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
