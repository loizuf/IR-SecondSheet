package _firstWork;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

import _firstGiven.BooleanDocument;
import _firstGiven.PositionalIndexEntry;

public class PositionalIndex {

	HashMap<String, PositionalIndexEntry> positionalIndex;

	public PositionalIndex(ArrayList<BooleanDocument> collection) {

		positionalIndex = new HashMap<>();

		Iterator<BooleanDocument> it = collection.iterator();

		// This counts up for every Document
		int docId = -1;

		while (it.hasNext()) {
			BooleanDocument doc = it.next();
			docId++;

			// we will now take all terms as we are interested
			// in all the positions within a document
			ArrayList<String> terms = doc.getWordList();
			Iterator<String> termIt = terms.iterator();
			String term = null;

			// This counts up for every word in this Document
			int countInDocument = -1;
			while (termIt.hasNext()) {
				term = termIt.next();
				countInDocument++;

				// cleaning the term
				term = term.trim().toLowerCase();
				indexTerm(term, docId, countInDocument);

			}
		}
	}

	public void indexTerm(String t, int docNumber, int positionInDocument) {

		PositionalIndexEntry docEntry = positionalIndex.get(t);

		// If this Term didn't occur at any time in any Document
		if (docEntry == null) {
			docEntry = new PositionalIndexEntry(t);
			positionalIndex.put(t, docEntry);
		}

		// If this Term didn't occur in this Document
		if (docEntry.getDocMap().get(docNumber) == null) {
			docEntry.incrementCounter();
			docEntry.addNewDocument(docNumber);
		}

		docEntry.addNewEntryForDocument(docNumber, positionInDocument);
	}

	// returns list with docId's containing word
	public ArrayList<Integer> searchForSingleWord(String word) {
		ArrayList<Integer> result =  new ArrayList<Integer>(positionalIndex.get(word).getDocMap().keySet());
		return result;
	}

	// returns list with positions on which word occurs in the document with the ID docNumber
	public ArrayList<Integer> searchForSingleWordInDocument(String word, int docNumber) {
		ArrayList<Integer> result = positionalIndex.get(word).getDocMap().get(docNumber);

		if (result != null) {
			return result;
		} else {
			return new ArrayList<Integer>();
		}
	}
	
	// According to the slides this should be done recursively
	// From the feel it should be possible to do this recursively
	// but im not capable of wrapping my head around that
	public ArrayList<Integer> searchForPhrase(String[] phrase) {
		ArrayList<Integer> result = positionalIntersect(phrase[0], phrase[1]);

		for (int i = 2; i < phrase.length; i++) {
			result.retainAll(positionalIntersect(phrase[i-1], phrase[i]));
		}
		return result;
	}
	
	// This is just an overloaded method to account for proximity search
	// Not quite sure how much sense this one makes for more than 2 terms
	public ArrayList<Integer> searchForPhrase(String[] phrase, int k) {
		ArrayList<Integer> result = positionalIntersect(phrase[0], phrase[1], k);

		for (int i = 2; i < phrase.length; i++) {
			result.retainAll(positionalIntersect(phrase[i-1], phrase[i], k));
		}
		return result;
	}

	// This Method searches for two terms adjacent two each other
	private ArrayList<Integer> positionalIntersect(String stringOne, String stringTwo) {
		ArrayList<Integer> result = new ArrayList<>();
		
		// Get treemaps (containing positioning information for EVERY document) for both terms
		TreeMap<Integer, ArrayList<Integer>> firstMap = positionalIndex
				.get(stringOne)
				.getDocMap();
		TreeMap<Integer, ArrayList<Integer>> secondMap = positionalIndex.get(stringTwo).getDocMap();
		
		Iterator<Integer> firstIterator = firstMap.keySet().iterator();
		Iterator<Integer> secondIterator = secondMap.keySet().iterator();
		int firstDocId = firstIterator.next();
		int secondDocId = secondIterator.next();
		outerLoop:
		while(true){
			// Search for documents which contain both terms 
			if(firstDocId == secondDocId) {
				
				// get positions of the terms for the document in which both terms occur
				// note: in this case first == second so it doesnt matter which one we use
				ArrayList<Integer> firstPositions = firstMap.get(firstDocId);
				ArrayList<Integer> secondPositions = secondMap.get(firstDocId);
				
				firstPositions.sort(null);
				secondPositions.sort(null);
				
				Iterator<Integer> firstPosIt = firstPositions.iterator();
				Iterator<Integer> secondPosIt = secondPositions.iterator();
				int firstPos = firstPosIt.next();
				int secondPos = secondPosIt.next();
				
				// search for positions of the first term where it is one smaller than the position of the second term
				while (true) {
					if(firstPos == secondPos-1){
						result.add(firstDocId);
						
						// when we find one solution we can advance to the next document
						if(firstIterator.hasNext()) firstDocId = firstIterator.next(); else break outerLoop;
						if(secondIterator.hasNext()) secondDocId = secondIterator.next(); else break outerLoop;

						break;
					} else {
						if(firstPos<secondPos) {
							if(firstPosIt.hasNext()) firstPos = firstPosIt.next(); else break;
						} else {
							if(secondPosIt.hasNext()) secondPos = secondPosIt.next(); else break;
						}
					}
				}
			} else {
				if(firstDocId < secondDocId) {
					if(firstIterator.hasNext()) firstDocId = firstIterator.next(); else break;
				} else {
					if(secondIterator.hasNext()) secondDocId = secondIterator.next(); else break;
				}
			}
		}
		return result;
	}

	// This is just an overloaded method to account for proximity search
	private ArrayList<Integer> positionalIntersect(String stringOne, String stringTwo, int k) {
		ArrayList<Integer> result = new ArrayList<>();
		
		TreeMap<Integer, ArrayList<Integer>> firstMap = positionalIndex.get(stringOne).getDocMap();
		TreeMap<Integer, ArrayList<Integer>> secondMap = positionalIndex.get(stringTwo).getDocMap();
		
		Iterator<Integer> firstIterator = firstMap.keySet().iterator();
		Iterator<Integer> secondIterator = secondMap.keySet().iterator();
		int firstDocId = firstIterator.next();
		int secondDocId = secondIterator.next();
		
		while(true){
			if(firstDocId == secondDocId) {
				
				// get positions of the terms for the document in which both terms occur
				// note: in this case first == second so it doesnt matter which one we use
				ArrayList<Integer> firstPositions = firstMap.get(firstDocId);
				ArrayList<Integer> secondPositions = secondMap.get(firstDocId);
				
				firstPositions.sort(null);
				secondPositions.sort(null);
				Iterator<Integer> firstPosIt = firstPositions.iterator();
				Iterator<Integer> secondPosIt = secondPositions.iterator();
				
				int firstPos = firstPosIt.next();
				int secondPos = secondPosIt.next();
				
				while (true) {
					if(Math.abs(firstPos - secondPos)<k){
						result.add(firstDocId);
						break;
					} else {
						if(firstPos<secondPos) {
							if(firstPosIt.hasNext()) firstPos = firstPosIt.next(); else break;
						} else {
							if(secondPosIt.hasNext()) secondPos = secondPosIt.next(); else break;
						}
					}
				}
			} else {
				if(firstDocId < secondDocId) {
					if(firstIterator.hasNext()) firstDocId = firstIterator.next(); else break;
				} else {
					if(secondIterator.hasNext()) secondDocId = secondIterator.next(); else break;
				}
			}
		}
		return result;
	}

	// We already know this method from the last assignement
	public TreeMap<Integer, ArrayList<Integer>> getPositionalWordTreeMap(String word) {
		TreeMap<Integer, ArrayList<Integer>> result = positionalIndex.get(word).getDocMap();

		if (result != null) {
			return result;
		} else {
			return new TreeMap<>();
		}
	}
}
