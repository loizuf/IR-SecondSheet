package _firstGiven;

import java.util.ArrayList;
import java.util.Arrays;

public class Tokenizer {
	
	/*
	 * nonAlphaNumericalRegex - regular expression um alle nicht-alphanumerischen Zeichen zu finden
	 * whiteSpaceRegex - regular expression um alle Whitespace-character zu finden
	 */
	private static final String nonAlphaNumericalRegex = "[^a-zA-Z0-9 -]";
	private static final String whiteSpaceRegex = "\\s+";
	
	/**
	 * Returns a list of terms which were seperated by whitespace in the document.
	 * @param text - complete text from the document
	 * @return result - list of terms which were seperated by whitespace
	 */
	public static ArrayList<String> tokenize(String text) {
		// Diese Methode splittet text an jedem Whitespace und returned eine Liste mit den tokens 
		ArrayList<String> result = new ArrayList<>(Arrays.asList(text.split(whiteSpaceRegex)));
		for (int i=0; i<result.size(); i++) {
			// gets an entry from results, deletes unwanted characters,
			// deletes whitespace, converts to lower case
			result.set(i, result.get(i).replaceAll(nonAlphaNumericalRegex, "").trim().toLowerCase());
		}
		return result;
	}
}
