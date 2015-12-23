package _secondWork;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import _secondGiven.BooleanDocument;
import _secondGiven.PositionalIndexEntry;

public class PositionalIndex {

	
	private HashMap<String, ArrayList<PositionalIndexEntry>> index;
	
	// Bearbeiten sie Aufgabe 3 hier
	public PositionalIndex(ArrayList<BooleanDocument> collection) {
		
		index = new HashMap<String, ArrayList<PositionalIndexEntry>>();

		Iterator<BooleanDocument> it = collection.iterator();
		int docId = -1;
		while (it.hasNext()) {
			BooleanDocument doc = it.next();
			docId++;
			
			// we will only take unique terms just now as this is a boolean
			ArrayList<String> terms = doc.getUniqueWordList();
			Iterator<String> termIt = terms.iterator();
			while (termIt.hasNext()) {
				String term = termIt.next();
				
				//cleaning the term
				term = term.trim().toLowerCase();
				//indexTerm(term, docId);
			}
			
			
			

		}
	}

	// Second method required for Aufgabe 3; index term

		public void indexTerm(String t, int doc, int pos) {

			/*ArrayList<Integer> docList = index.get(t);

			if (docList == null) {
				docList = new ArrayList<Integer>();
				index.put(t, docList);
			}

			docList.add(doc);
*/
		}
	
	// Bearbeiten sie Aufgabe 4.a hier
	// returns list with docId's containing word
	public ArrayList<Integer> searchForSingleWord(String word) {
		return null;
	}

	// Bearbeiten sie Aufgabe 4.a hier
	// returns list with positions on which word occurs in the document with the ID docNumber
	public ArrayList<Integer> searchForSingleWordInDocument(String word, int docNumber) {
		return null;
	}

	// Bearbeiten sie Aufgabe 4.b hier
	// returns list of docId's containing word-combination
	public ArrayList<Integer> searchForPhrase(String[] phrase) {
		return null;
	}
	
	// Bearbeiten sie Aufgabe 5 hier
	// returns list of docId's containing words in proximity of k of each other
	public ArrayList<Integer> searchForPhrase(String[] phrase, int k) {
		return null;
	}
}
