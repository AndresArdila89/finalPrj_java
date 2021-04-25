package App;

public class FileInvalidException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public FileInvalidException(String msg, String filename) {
		super(	
				"Error: Empty Field detected on file " + filename + "\n" +
				"=======================================================\n"+
				"Field " + msg + "\n" + 
				"Processing Stop at this point\n" +
				"Other Fields might be empty\n" +
				
				"--------------------------------------------------------\n");
			
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return super.getMessage();
	}
}
