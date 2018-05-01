import java.util.Scanner;
import java.util.ArrayList;

public class Hangman {
	public static void main(String[] args) {
		HangmanSpel hs = new HangmanSpel();
		hs.spelen();	
	}
}

class HangmanSpel {
	Scanner input = new Scanner(System.in);
	
	void spelen() {
		System.out.println("Welkom bij Guillotineman (Hangman)");
		Woord woord = new Woord(vraagWoord());
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("Raad het woord dat hieronder moet verschijnen.");
		woord.printMijnWoord();
		
		for (int i = 8; i>0; i--) {
			System.out.println("Het mes valt over " + i + " poging(en)");
			String raad = vraagLetter();
			if (raad.length() == 1) {
				woord.raden(raad.charAt(0));
			} else {
				woord.gok(raad);
			}
			
			if (woord.check()) {
				System.out.println("Goedzo, je hebt het geraden! Je bent veilig");
				System.out.print("Het woord was inderdaad: ");
				woord.printMijnWoord();
				break;
			} else {
			woord.printMijnWoord();
			woord.printLettersGeraden();
			}
		}
		if (!woord.check()) {
			System.out.println("Helaas, je hebt het niet geraden, dus je bent onthoofd. Het woord was: " + woord.woordKlein);
		}
	}
	


	public String vraagWoord() {
		System.out.println("Welk woord wil je dat er geraden wordt?");
		String name = input.nextLine();
		return name;
	}

	public String vraagLetter() {
		System.out.println("Raad een letter of het woord.");
		String letter = input.next();
		return letter;
	}
	
}

class Woord extends HangmanSpel{
	String woordKlein;
	char[] mijnWoord;
	ArrayList<Character> lettersGeraden = new ArrayList<Character>();
	
	Woord (String woord) {
		woordKlein = woord.toLowerCase();
		mijnWoord = new char[woord.length()];
		for (int i = 0; i < woord.length(); i++) {
			mijnWoord[i] = '_';
		}
	}
	
	public void raden(char letter) {
		if (lettersGeraden.contains(letter)) {
			System.out.println("Je hebt de letter " + letter + " al geprobeerd, probeer een andere letter.");
			String raad = vraagLetter();
			if (raad.length() == 1) {
				raden(raad.charAt(0));
			} else {
				gok(raad);
			}
		}
		for (int i=0; i<mijnWoord.length; i++) {
			if (woordKlein.charAt(i) == letter) {
				mijnWoord[i] = woordKlein.charAt(i);
			} 
		}
		lettersGeraden.add(letter);
	}
	
	public void printMijnWoord() {
		System.out.println();
		for (int i=0; i<mijnWoord.length; i++) {
			System.out.print(mijnWoord[i] + " ");
		}
		System.out.println();
	}
	
	public void printLettersGeraden() {
		System.out.println("\n");
		System.out.println("Je hebt al geraden: ");
		for (char letter : lettersGeraden) {
			System.out.print(letter + " ");
		}
		System.out.println("\n");
	}
	
	public void gok(String gok) {
		if (gok.equals(woordKlein)) {
			for (int i=0; i<mijnWoord.length; i++) {
				mijnWoord[i] = gok.charAt(i);
			}
		} else {
			System.out.println(gok + " is niet goed.");
			return;
		}
	}
	
	public boolean check() {
		if (woordKlein.equals(new String(mijnWoord))) {
			return true;
		} else {
			return false;
		}
	}
}
