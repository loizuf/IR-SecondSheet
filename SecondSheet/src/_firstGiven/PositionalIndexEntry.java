package _firstGiven;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class PositionalIndexEntry {
	
	String term;
	int number;
	TreeMap<Integer, ArrayList<Integer>> docMap;

	public PositionalIndexEntry() {
		this.number = 0;
		this.docMap = new TreeMap<>();
		this.term = "-";
	}
	
	public PositionalIndexEntry(String term) {
		this.number = 0;
		this.docMap = new TreeMap<>();
		this.term = term;
	}
	
	public PositionalIndexEntry(int number, TreeMap<Integer, ArrayList<Integer>> docMap, String term){
		this.number = number;
		this.docMap = docMap;
		this.term = term;
	}

	public String getTerm() {
		return term;
	}
	
	public void incrementCounter() {
		number++;
	}

	public int getNumber() {
		return number;
	}
	
	public void addNewDocument(int docNumber){
		docMap.put(docNumber, new ArrayList<>());
	}
	
	public void addNewEntryForDocument(int docNumber, int position) {
		docMap.get(docNumber).add(position);
		docMap.get(docNumber).sort(null);
	}

	public TreeMap<Integer, ArrayList<Integer>> getDocMap() {
		return docMap;
	}
	
	public ArrayList<Integer> getOccurrencesInOneDocument(int docNumber) {
		return docMap.get(docNumber);
	}
	
	public ArrayList<Integer> getDocList() {
		return (ArrayList<Integer>) docMap.keySet();
	}

}
