package _secondGiven;

import java.util.ArrayList;
import java.util.LinkedHashSet;

/*
 * Objekte dieser Klasse zu erzeugen und zu speichern würde in einem echten IR-System keinen Sinn machen.
 * Diese Klasse existiert um alle Dateien als Objekte zu repräsentieren und den Zugang zu den Dateien zu erleichtern
 */
public class Document {

	/*
	 * wordList - Liste aller Wörter in der Reihenfolge in der sie im Text auftauchen (mit Vielfachen)
	 * name - Name des Dokuments
	 */
	private ArrayList<String> wordList;
	private String name;

	/**
	 * Constructor for a representation of a document within the code.
	 * 
	 * @param unalteredWords - String containing the complete text of the document
	 * @param name - name of the document
	 */
	public Document(String unalteredWords, String name) {
		
		this.name = name;
		wordList = Tokenizer.tokenize(unalteredWords);
	}

	/**
	 * Returns the name of the document
	 * @return name - String containing the name of the document
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns an ArrayList containing all words in the Document or query (without
	 * duplicates)
	 * 
	 * @return uniqueWordList - HashMap of all words (without duplicates)
	 */
	public ArrayList<String> getUniqueWordList() {
		ArrayList<String> uniqueWordList = new ArrayList<String>(new LinkedHashSet<>(wordList));
		return uniqueWordList;
	}

	/**
	 * Returns an ArrayList containing all words in the Document or query (with
	 * duplicates)
	 * 
	 * @return wordList - HashMap of all words (with duplicates)
	 */
	public ArrayList<String> getWordList() {
		return wordList;
	}
}