package _firstWork;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

import _firstGiven.BooleanDocument;
import _firstGiven.PositionalIndexEntry;

public class PositionalIndex {

	// Bearbeiten sie Aufgabe 3 hier
	public PositionalIndex(ArrayList<BooleanDocument> collection) {
		
	}

	// returns list with docId's containing word
	public ArrayList<Integer> searchForSingleWord(String word) {
		return null;
	}

	// returns list with positions on which word occurs in the document with the ID docNumber
	public ArrayList<Integer> searchForSingleWordInDocument(String word, int docNumber) {
		return null;
	}
	
	// returns list of docId's containing word-combination
	public ArrayList<Integer> searchForPhrase(String[] phrase) {
		return null;
	}
	
	// returns list of docId's containing words in proximity of k of each other
	public ArrayList<Integer> searchForPhrase(String[] phrase, int k) {
		return null;
	}
}
