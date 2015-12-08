package _firstGiven;

import java.util.ArrayList;
import java.util.HashMap;

/*
 * Indexable sollte von allen Klassen implementiert werden die indexiert werden sollen (bis jetzt nur Dokumente)
 */
public interface BooleanIndexable {

	/**
	 * Returns an ArrayList containing all words in the Document or query (without
	 * duplicates)
	 * 
	 * @return wordList - HashMap of all words (without duplicates)
	 */
	ArrayList<String> getUniqueWordList();

	/**
	 * Returns an ArrayList containing all words in the Document or query (with
	 * duplicates)
	 * 
	 * @return wordList - HashMap of all words (with duplicates)
	 */
	ArrayList<String> getWordList();
}
