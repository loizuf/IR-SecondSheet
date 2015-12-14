package _secondWork;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import _secondGiven.BooleanDocument;
import _secondGiven.FileReader;

public class MainController {

	/*
	 * LoremIpsumCollectionDirectoryPath - Name des Verzeichnisses das die
	 * Dokumente enthaelt
	 */
	private static final String LOREM_IPSUM_COLLECTION_DIRECTORY_PATH = "collections/lorem";

	public static void main(String[] args) throws IOException {

		// collection - Liste aller Document-Objekte
		ArrayList<BooleanDocument> collection = FileReader.readCollection(LOREM_IPSUM_COLLECTION_DIRECTORY_PATH);

		// biWordIndex - Repraesentation des biWordIndex
		// positionalIndex - Repraesentation des positionalIndex
		BiWordIndex biWordIndex = new BiWordIndex(collection);
		//PositionalIndex positionalIndex = new PositionalIndex(collection);

		// breakdown the query into two terms
		String[] queryTerms = getQueryTerms();
		
		/*
		 *  This searches through the BiWordIndex for our Phrase
		 *  However we search for EXACTLY this combination of Words
		 *  Therefore we combine them into one string (just like in
		 *  the indexing progress) and search for that as if it was one word
		 */
		ArrayList<Integer> resultBiw = biWordIndex.searchForSingleWord(queryTerms[0]+" "+queryTerms[1]);
		/*
		 * This searches for the phrase in the positional index
		 */
		//ArrayList<Integer> resultPos = positionalIndex.searchForPhrase(queryTerms);
		
		System.out.println("Ergebniss des Bi-WordIndex f�r '" + queryTerms[0]+" "+queryTerms[1] + "'");
		postResults(resultBiw);

		//System.out.println("Ergebniss des Positional-Index");
		//postResults(resultPos);
	}

	// Bearbeiten sie Aufgabe 3 hier.
	public static String[] getQueryTerms() {
		// let's get a query from the user via the command line
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter a query:");
		String query = scanner.nextLine();
		scanner.close();
		
		// Lets clean up those queryterms
		String[] result = query.split("\\s+");
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
			result[i] = result[i].trim().toLowerCase();
		}
		
		return result;
	}

	/*
	 * Diese Methode gibt ausschlieslich die Ergebnisse an den User aus
	 */
	private static void postResults(ArrayList<Integer> result) {
		System.out.println("\n+++++++\n");
		System.out.println("The results are:");
		for (int currentResult : result) {
			System.out.println(currentResult);
		}
	}
}
