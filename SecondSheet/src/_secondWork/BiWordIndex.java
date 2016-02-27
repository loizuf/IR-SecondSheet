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
		//this calls the constructor for invertedindex class and indexs terms individually
		super(collection);
		index =super.index;
		// index bi-words
		
					
		// get the wordlist
		Iterator<Document> it = collection.iterator();
		int docId = -1;
		while (it.hasNext()) {
			Document doc = it.next();
			docId++;
			
			// get the terms in the doc as a stream
			ArrayList<String> terms = doc.getWordList();
			Iterator<String> termIt = terms.iterator();
			String lastTerm = "";
			while (termIt.hasNext()) {
				String term = termIt.next();
				//cleaning the term
				term = term.trim().toLowerCase();
				
				if (lastTerm.equals("")){//first term in doc
					lastTerm=term;
					continue;
				}
				else {
					indexTerm(lastTerm+" "+term,docId);
					lastTerm=term;
				}
			}//end of term loop
									
		} // end of document loop
		
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

	// For JUNIT-Tests. This Method must not be edited or deleted
	public HashMap<String, ArrayList<Integer>> getBiWordHashmap() {
		return index;
	}

}
