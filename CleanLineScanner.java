import java.util.Scanner;

public class CleanLineScanner {
	
	static public String cleanLine( Scanner s){ //static function to clean the line
		String text = s.nextLine();
		while(text.length()==0||text.charAt(0)=='/'){
			text = s.nextLine();
		}
		return text;
		
		
	}


	

}
