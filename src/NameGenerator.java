import java.io.*; //input output
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

/**
 * Babyname-generator
 * <p> User can pick babynames she/he likes and export them to a text-file. </p>
 * @author Jussi Ketom�ki
 * @version 0.6
 */

public class NameGenerator {
	
	private static final Scanner lukija = new Scanner(System.in);
	private static Scanner menuChoice;
	
	/**
	 * <p>The main method greets the user and asks what genders names does she/he want to look at.
	 * The method also asks for the users lastname.
	 * This is to make it easier to visualise what the full name would look like. </p>
	 * @param args the main args.
	 * @throws FileNotFoundException if a file does not exist.
	 */
	public static void main(String[] args) throws FileNotFoundException  {
		System.out.println("----------------------------NIMIGENERAATTORI---------------------------------");
		System.out.println(" ");
		System.out.println("Tervetuloa etsim��n lapsellesi nime�!");
		System.out.println(" ");
		System.out.println("Lopetettuasi valitsemasi nimet l�ytyv�t suosikit-nimisest� tekstitiedostosta.");
		System.out.println("Mik� on sukunimesi? T�m� lis�t��n arvotun nimen per��n: ");
		String last = new String(lukija.next()); //User inputs her or his lastname as to make the name picking easier.
		System.out.println(" ");
		
		System.out.println("Haluatko etsi� tyt�n vai pojan nime�: ");
		System.out.println("1. Tyt�n ");
		System.out.println("2. Pojan ");
		int gender = 0; 
	    while (gender !=1 || gender != 2 ){ //User must input either 1 or 2 or the program will not continue.
			gender = lukija.nextInt(); //
			if (gender == 1){
				System.out.println(" ");
				System.out.println("Selv�, arvomme tyt�n nimi�! T�ss� ensimm�inen: ");
				randomiseGirlName(last);
			} else if (gender == 2){
				System.out.println(" ");
				System.out.println("Selv�, arvomme pojan nimi�! T�ss� ensimm�inen: ");
				randomiseBoyName(last);
			} else {
				System.out.println("V��r� valinta. N�pp�ile  1 + enter =  Tyt�n nimi tai 2 + enter = Pojan nimi. ");
			}
	    }//while
	
	}//main
	
/**
 * <p> The randomiseGirlName method scans a file which includes all Finnish female names.
 * The text file that is imported is copy pasted from "https://fi.wikipedia.org/wiki/Luettelo_suomalaisen_nimip%C3%A4iv%C3%A4kalenterin_naisten_nimist%C3%A4" Luettelo suomalaisen nimip�iv�kalenterin naisten nimist�</a>
 * Afterwords it is made so that the different names can be seen as seperate values in an array.
 * Then we must determine the amount of names to help the randomizing process.
 * Then the name is randomized.
 * The user has the option to randomize another name, add to favourites or quit and return to main menu. </p>
 * @param l	the users lastname as given in the main method.
 * @throws FileNotFoundException if a file does not exist.
 */
	public static void randomiseGirlName(String l) throws FileNotFoundException {
		
		final Scanner lukija = new Scanner(new File("tyttonimet.txt"));
		ArrayList<String> tyttoNimet = new ArrayList<String>();
		while (lukija.hasNext()){
		    tyttoNimet.add(lukija.next());
		}
		lukija.close(); 
		
		int amount = tyttoNimet.size(); //Check the amount of names
		Random rand = new Random(); 
		int r = rand.nextInt(amount); //Determine rand that is the same size that the amount of names on the list.
		System.out.println(tyttoNimet.get(r) + " " + l); // Print the first randomized name.
		
		System.out.println(" ");
		System.out.println("1. Arvo uusi");
		System.out.println("2. Lis�� suosikkeihin");
		System.out.println("3. Lopeta ja palaa p��valikkoon");
		
		menuChoice = new Scanner(System.in);
		PrintWriter kirjoittaja = new PrintWriter("suosikit.txt"); //Declares a new text file.
		kirjoittaja.println("SUOSIKIT:"); //Makes a title for the text file.
		
		int menu = 0;
		while (menu != 3){
			menu = menuChoice.nextInt();
			switch (menu){
			case 1:
				r = rand.nextInt(amount); 
				System.out.println(tyttoNimet.get(r) + " " + l); //A new name is randomized.
				break;
			case 2:
			    kirjoittaja.println(tyttoNimet.get(r)); //Favourites are exported to a file.
				System.out.println("Lis�tty suosikkeihin, arvo seuraava n�pp�ilem�ll� 1 + enter. Lopetus: 3 + enter.");
				break;
			case 3:
				System.out.println("Lopetit, palataan p��valikkoon."); //Ends the menu.
				System.out.println(" ");
				break;
			}//switch	
		}//while
		
		kirjoittaja.close(); //Closes and saves the favourites text file.	
		main(null); //Returns to main method / main menu.
		
	} //randomiseGirlName
	
/**
 * <p> The randomiseBoyName method scans a file which includes all Finnish male names.
 * The text file that is imported is copy pasted from <a href="https://fi.wikipedia.org/wiki/Luettelo_suomalaisen_nimip%C3%A4iv%C3%A4kalenterin_miesten_nimist%C3%A4">Luettelo suomalaisen nimip�iv�kalenterin miesten nimist�</a>
 * Afterwords it is made so that the different names can be seen as seperate values in an array.
 * Then we must determine the amount of names to help the randomizing process.
 * Then the name is randomized.
 * The user has the option to randomize another name, add to favourites or quit and return to main menu. </p>
 * @param l	the users lastname as given in the main method.
 * @throws FileNotFoundException if a file does not exist.
 */
	public static void randomiseBoyName(String l) throws FileNotFoundException {
		final Scanner lukija = new Scanner(new File("poikanimet.txt"));
		ArrayList<String> poikaNimet = new ArrayList<String>();
		while (lukija.hasNext()){
		    poikaNimet.add(lukija.next());
		}
		lukija.close(); 
		
		int amount = poikaNimet.size(); //Check the amount of names
		Random rand = new Random(); 
		int r = rand.nextInt(amount); //Determine rand that is the same size that the amount of names on the list.
		System.out.println(poikaNimet.get(r) + " " + l); // Print the first randomized name.
		
		System.out.println(" ");
		System.out.println("1. Arvo uusi");
		System.out.println("2. Lis�� suosikkeihin");
		System.out.println("3. Lopeta ja palaa p��valikkoon");
		
		menuChoice = new Scanner(System.in);
		PrintWriter kirjoittaja = new PrintWriter("suosikit.txt"); //Declares a new text file.
		kirjoittaja.println("SUOSIKIT:"); //Makes a title for the text file.
		
		int menu = 0;
		while (menu != 3){
			menu = menuChoice.nextInt();
			switch (menu){
			case 1:
				r = rand.nextInt(amount); 
				System.out.println(poikaNimet.get(r) + " " + l); //A new name is randomized.
				break;
			case 2:
			    kirjoittaja.println(poikaNimet.get(r)); //Favourites are exported to a file.
				System.out.println("Lis�tty suosikkeihin, arvo seuraava n�pp�ilem�ll� 1 + enter. Lopetus: 3 + enter.");
				break;
			case 3:
				System.out.println("Lopetit, palataan p��valikkoon."); //Ends the menu.
				System.out.println(" ");
				break;
			}//switch	
		}//while
		
		kirjoittaja.close(); //Closes and saves the favourites text file.
		main(null); //Returns to main method / main menu.
		
	} //randomiseBoyName
	
	
} //class



