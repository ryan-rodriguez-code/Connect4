//This class will have to be edited to the old one in my other compute
//Adapt the old waitUntil method with new one located here
package game;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
//class used to handle JOptionPane elements more easier. has more methods than needed for this program.
public class JOP {
//used to show text to the user. displays the String parameter.
	public static void msg(String msg) {
		UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(Font.MONOSPACED, Font.BOLD, 14))); 
		JOptionPane.showMessageDialog(null, msg);
	}
//returns the user input to the question given in the String parameter.
	public static String in(String msg){
		UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(Font.MONOSPACED, Font.BOLD, 14))); 
		String s = JOptionPane.showInputDialog(msg);
		if(s == null) {
			System.exit(0);
		}
		return s;
	}
//returns false if the input is in the String[] options true otherwise.
//if the input is null then the program ends.
//used to run infinite loop until user enters correct information into input.
	public static boolean waitUntil(String input, String[] options) {
		if(input == null) {
			System.exit(0);
		}
		
		for(int i = 0; i < options.length; i++) {
			if(input.equals(options[i])) {
				return false;
			}
		}
		return true;
	}
//checks if String s is an int.
	public static boolean isInt(String s) {
		for(int i = 0; i < s.length(); i++) {
			if((int)s.charAt(i) < 48 || (int)s.charAt(i) > 57  || (int)s.charAt(i) == (int)'-') {
				return false;
			}
		}
		return true;
	}
//checks if String s is a boolean.
	public static boolean isBollean(String s) {
		if(s.equals("true") || s.equals("false")) {
			return true;
		}
		return false;
	}
//checks to see if String s is a double.
	public static boolean isDouble(String s) {
		int numDots = 0;
		for(int i = 0; i < s.length(); i++) {
			if(!(s.charAt(i) == '0' || s.charAt(i) == '1' || s.charAt(i) == '2' || s.charAt(i) == '3' || s.charAt(i) == '4' || s.charAt(i) == '5' || s.charAt(i) == '6' || s.charAt(i) == '7' || s.charAt(i) == '8' || s.charAt(i) == '9' || s.charAt(i) == '.' || s.charAt(i) == '-')) {
				return false;
			}
			else if(s.charAt(i) == '.') {
				numDots++;
			}
		}
		
		if(numDots == 1 && s.charAt(s.length() - 1) != '.' && s.charAt(0) != '.') {
			return true;
		}
		return false;
	}
}
