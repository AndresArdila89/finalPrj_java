package App;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class BibCreator {

	public static void main(String[] args) throws IOException {
		// Linked list to store all the paths of the files
		LinkedList<File> filePaths = new LinkedList<File>();
		// Fills the LinkedList with the paths
		loadFiles(filePaths);

		// This List stores all the BibFiles objects
		// each BibFile object contains articles
		List<BibFile> allFiles = new ArrayList<BibFile>();
		
		int valid = 0;
		int invalid = 0;

		System.out.println("Welcome to BibCreator!\n");

		for (File file : filePaths) {
			BibFile BibFileFile = new BibFile();
			try {
				BibFileFile.processFilesForValidation(file);
				allFiles.add(BibFileFile);
				valid++;

			} catch (FileInvalidException e) {
				// TODO Auto-generated catch block
				invalid++;
				System.out.println(e.getMessage());
			}
		}

		System.out.println("A total of " + invalid + " files were invalid, and could not be processed.\n" + "All "
				+ valid + " valid files have been created.");

		int file_error = 0;

		Scanner userInput = new Scanner(System.in);
		while (file_error < 2) {

			System.out.println("Please enter the name of one of the files that you need to review: ");
			String file_name = userInput.nextLine();
			
			BufferedReader reader = null;
			String line = null;

			try {

				reader = new BufferedReader(new FileReader("data/" + file_name));
				line = reader.readLine();
				while (line != null) {
					System.out.println(line);
					line = reader.readLine();
				}
				reader.close();
			} catch (FileNotFoundException e) {
				file_error++;
				// TODO Auto-generated catch block
				System.out.println("Could not open input file. File does not exist. Probably it could not be Created.");
			}
		}
		userInput.close();
		System.out.println("program terminated..........");
		System.exit(0);

	}
	
	// creates the name and path for the .bib files
	public static void loadFiles(LinkedList<File> files) {

		for (int i = 1; i <= 10; i++) {
			File file = new File("data/Latex" + i + ".bib");
			files.add(file);
		}
	}
}
