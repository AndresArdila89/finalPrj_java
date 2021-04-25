package App;

import java.util.StringTokenizer;

public class Article {
	private String id;
	private String author;
	private String journal;
	private String title;
	private String year;
	private String volume;
	private String number;
	private String pages;
	private String keywords;
	private String doi;
	private String ISSN;
	private String month;
	private int articleCount;
	
	public Article(int counter) {
		this.articleCount = counter;
	}
	

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String strAuthors) {
		
		this.author = strAuthors;
	}
	public String getJournal() {
		return journal;
	}
	public void setJournal(String journal) {
		this.journal = journal;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getPages() {
		return pages;
	}
	public void setPages(String pages) {
		this.pages = pages;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String strKeywords) {
		
		this.keywords = strKeywords;
	}
	public String getDoi() {
		return doi;
	}
	public void setDoi(String doi) {
		this.doi = doi;
	}
	public String getISSN() {
		return ISSN;
	}
	public void setISSN(String iSSN) {
		ISSN = iSSN;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	
	public void makeIEEE(StringBuilder lineIEEE) {
		StringTokenizer st = new StringTokenizer(author);
		
		while(st.hasMoreTokens()) {
			String nt = st.nextToken().toString();
			if(nt.equals("and")) {
				lineIEEE.append(", ");
				
			}else 
			{
				lineIEEE.append(" " + nt);
			}					
		}
		
		lineIEEE.append(".\""+title+"\", "+ journal+", vol. "+volume+", no. "+number+
				",p. " + pages + ", " + month + " " + year + ".\n" );
	}
	
	public void makeACM(StringBuilder lineACM) {
		StringTokenizer st = new StringTokenizer(author);
		lineACM.append("["+ articleCount +"]\t");
		while(st.hasMoreTokens()) {
			String nt = st.nextToken().toString();
			if(nt.equals("and")) {
				lineACM.append("et al. ");
				break;
				
			}else 
			{
				lineACM.append(nt + " ");
			}					
		}
		
		lineACM.append(year + ". "+title+". "+ journal+". "+volume+", "+number+" (" + year +"), " +
				pages + ". DOI:https://doi.org/" + doi +".\n\n" );
	}
	
	public void makeNJ(StringBuilder lineNJ) {
		StringTokenizer st = new StringTokenizer(author);
		
		while(st.hasMoreTokens()) {
			String nt = st.nextToken().toString();
			if(nt.equals("and")) {
				lineNJ.append("& ");
				
			}else 
			{
				lineNJ.append(" " + nt);
			}					
		}
		
		lineNJ.append(". "+title+". "+ journal+". "+volume+", "+pages +
				"(" + year +")" +".\n" );
	}
	
	
	
	
}
