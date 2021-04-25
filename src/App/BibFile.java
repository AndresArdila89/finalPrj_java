package App;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class BibFile {
	public LinkedList<Article> BibFileList = new LinkedList<Article>();
	public String fileName;
	
	StringBuilder fileIEEE = new StringBuilder();
	StringBuilder fileACM = new StringBuilder();
	StringBuilder fileNJ = new StringBuilder();
	
	private int counter=0;
	
	

	public boolean processFilesForValidation(File file) throws FileInvalidException, FileNotFoundException {
		this.fileName =  file.getName();
		Scanner fileReader = null;
		
		try {
			//fileReader = new Scanner(new FileInputStream("data/Latex1.bib"));
			fileReader = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(file.getName() + " Does Not Exist");
			return false;
		}
		
		
		String line = "";

		while (fileReader.hasNext()) {
			// find article
			line = readLine(fileReader).trim();

			if (line.equals("@ARTICLE{")) {
				// Start of the article
				// instantiate the object BibFile
				this.counter++;
				Article art = new Article(this.counter);
				
				// Read a new line
				line = readLine(fileReader);
				// Add the id and remove the comma
				art.setId(line.substring(0, line.length() - 2));
				
				String result = "";
				while (result != "end") {

					// Read a new line
					line = readLine(fileReader);

					// Split the line between variable and value
					result = splitLine(line, art);
					if (result.equals("end")) {
						art.makeIEEE(fileIEEE);
						art.makeACM(fileACM);
						art.makeNJ(fileNJ);
						this.BibFileList.add(art);
					}
					else if(!result.equals("success")) {
						fileReader.close();
						throw new FileInvalidException(result,fileName);
			
					}
				}


			}
		}
		
		fileReader.close();
		
		// remove all letters from the file name leaving only the number
		String fileNumber= fileName.replaceAll("[^0-9]", "");
		// instatiated and assign name and path for the file 
		PrintWriter IEEE = new PrintWriter("data/IEEE" + (fileNumber) + ".json");
		//here we created the json file
		IEEE.write (fileIEEE.toString().indent(-1));
		// close the json file 
		IEEE.close();
		
		PrintWriter ACM = new PrintWriter("data/ACM" + (fileNumber) + ".json");
		ACM.write (fileACM.toString());
		ACM.close();
		
		PrintWriter NJ = new PrintWriter("data/NJ" + (fileNumber) + ".json"); 
		NJ.write (fileNJ.toString().indent(-1));
		NJ.close();
		return true;
		
		
	}
	
//================== METHODS
	
	// Read line, skips empty lines
	public static String readLine(Scanner readline) {
		String line = readline.nextLine().trim();
		while (line == "") {
			line = readline.nextLine();
		}
		return line;
	}
	
	// Splits the content on the line separating variable from value
	// it does not matter the order of the lines it will assign the correct 
	// value to the correct property of the object
	public static String splitLine(String line, Article article) {

		if (line.equals("}")) {
			return "end";
		}

		int indexEqual = line.indexOf('=');
		int indexOpenBr = line.indexOf('{') + 1;
		int indexCloseBr = line.indexOf("},");
		
		String pr = line.substring(0, indexEqual);
		String vl = line.substring(indexOpenBr, indexCloseBr);
		
		switch (pr) {
		case "author":
			if (vl.equals("")) {
				return "Author is empty";
			}
			
			article.setAuthor(vl);
			break;
		case "journal":
			if (vl.equals("")) {
				return "Journal is empty";
			}
			article.setJournal(vl);
			break;
		case "title":
			if (vl.equals("")) {
				return "Title is empty";
			}
			article.setTitle(vl);
			break;
		case "year":
			if (vl.equals("")) {
				return "Year is empty";
			}
			article.setYear(vl);
			break;
		case "volume":
			if (vl.equals("")) {
				return "Volume is empty";
			}
			article.setVolume(vl);
			break;
		case "number":
			if (vl.equals("")) {
				return "Number is empty";
			}
			article.setNumber(vl);
			break;
		case "pages":
			if (vl.equals("")) {
				return "Pages is empty";
			}
			article.setPages(vl);
			break;
		case "keywords":
			if (vl.equals("")) {
				return "Keywords is empty";
			}
			article.setKeywords(vl);
			break;
		case "doi":
			if (vl.equals("")) {
				return "Doi is empty";
			}
			article.setDoi(vl);
			break;
		case "ISSN":
			if (vl.equals("")) {
				return "ISSN is empty";
			}
			article.setISSN(vl);
			break;
		case "month":
			if (vl.equals("")) {
				return "Month is empty";
			}
			article.setMonth(vl);
			break;
		}
		return "success";
	}
}
