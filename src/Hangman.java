import java.util.Scanner;
import java.util.Arrays;

public class Hangman {
	Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		Hangman a = new Hangman();
		Woord woord = new Woord(a.vraagWoord());
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("Raad het woord dat hieronder moet verschijnen.");
		woord.printGeraden();
		
		for (int i = 8; i>0; i--) {
			System.out.println("Je hebt nog " + i + " poging(en)");
			String raad = a.vraagLetter();
			if (raad.length() == 1) {
				woord.raden(raad.charAt(0));
			} else {
				woord.gok(raad);
			}
			
			if (Arrays.equals(woord.letters, woord.lettersGeraden)) {
				System.out.println("Goedzo, je hebt het geraden!");
				System.out.print("Het woord was inderdaad: ");
				woord.printGeraden();
				break;
			}
			woord.printGeraden();
		}
		
		
	}
	
	public String vraagWoord() {
		System.out.println("Welk woord wil je dat er geraden wordt?");
		String name = input.nextLine();
		return name;
	}
	
	public String vraagLetter() {
		System.out.println("Raad een letter.");
		String letter = input.next();
		return letter;
	}
}

class Woord {
	char[] letters;
	char[] lettersGeraden;
	
	Woord (String woord) {
		String woordKlein = woord.toLowerCase();
		letters = new char[woord.length()];
		lettersGeraden = new char[woord.length()];
		for (int i = 0; i < woord.length(); i++) {
			letters[i] = woordKlein.charAt(i);
			lettersGeraden[i] = '_';
		}
	}
	
	public void raden(char letter) {
		for (int i=0; i<lettersGeraden.length; i++) {
			if (letters[i]==letter) {
				lettersGeraden[i] = letters[i];
			}
		}
	}
	
	public void printGeraden() {
		System.out.println();
		for (int i=0; i<lettersGeraden.length; i++) {
			System.out.print(lettersGeraden[i] + " ");
		}
		System.out.println("\n");
	}
	
	public void gok(String gok) {
		if (gok.length() == letters.length) {
			for (int i = 0; i<gok.length(); i++) {
				if (gok.charAt(i) != letters[i]) {
					System.out.println(gok + " is niet goed.");
					return;
				}
			}
		} else {
			System.out.println(gok + " is niet goed.");
			return;
		}
		for (int i=0; i<lettersGeraden.length; i++) {
			lettersGeraden[i] = gok.charAt(i);
		}
	}
}
