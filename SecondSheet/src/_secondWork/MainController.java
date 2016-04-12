package _secondWork;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import _secondGiven.Document;
import _secondGiven.FileReader;

public class MainController {

	// directory der Collection
	private static final String COLLECTION_3_DIRECTORY_PATH = "collections/testCollections/third";

	public static void main(String[] args) throws IOException {

		// collection - Liste aller Document-Objekte
		ArrayList<Document> collection = FileReader.readCollection(COLLECTION_3_DIRECTORY_PATH);

		// biWordIndex - Repraesentation des biWordIndex
		// positionalIndex - Repraesentation des positionalIndex
		BiWordIndex biWordIndex = new BiWordIndex(collection);
		PositionalIndex positionalIndex = new PositionalIndex(collection);

		// Query hohlen
		String[] queryTerms = getQueryTerms();
		
		// Diese Methode durchsucht den Biword-Index indem beide Worte durch ein Leerzeichen
		// getrennt als ein Term gesucht werden
		ArrayList<Integer> resultBiw = biWordIndex.searchForSingleWord(queryTerms[0]+" "+queryTerms[1]);
		// Diese Methode durchsucht den Positional-Index
		ArrayList<Integer> resultPos = positionalIndex.searchForPhrase(queryTerms);
		
		System.out.println("Ergebniss des Bi-WordIndex");
		postResults(resultBiw);

		System.out.println("Ergebniss des Positional-Index");
		postResults(resultPos);
	}

	// Diese Methode liest Terme vom User ein
	public static String[] getQueryTerms() {
		
		// Einlesen über die Kommandozeile
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter a query:");
		String query = scanner.nextLine();
		scanner.close();
		
		// Bereinigen der Terme (Trennen in einzelne Terme, Entfernen von überschüssigem Whitespace, Groß-, und Kleinschreibung)
		String[] result = query.split("\\s+");
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
			result[i] = result[i].trim().toLowerCase();
		}
		
		return result;
	}

	// Diese Methode gibt ausschlieslich die Ergebnisse an den User aus
	private static void postResults(ArrayList<Integer> result) {
		System.out.println("\n+++++++\n");
		System.out.println("The results are:");
		for (int currentResult : result) {
			System.out.println(currentResult);
		}
	}
}
