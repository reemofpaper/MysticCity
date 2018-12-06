import java.util.Scanner;

//TODO: find system.out.println and convert them so that they use the IO object instead of system
//will call the display method and pass the string

public class TextInterface implements UserInterface {

	@Override
	public void display(String s) {
		System.out.println(s);
		
	}

	@Override
	public String getLine() {
		
		Scanner s = KeyboardScanner.getInstance();
		
		return s.nextLine();
	}

}
 
