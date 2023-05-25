package demo001.simgame;

import java.util.Iterator;
import java.util.Scanner;

public class BusinessSimulation001 {

    public static void main(String[] args) {
   	 // TODO Auto-generated method stub
   	 System.out.println("Wirtschaftssimulation Programmierung II");
   	 
   	 Niederlassung niederlassungEinbeck = new Niederlassung("Einbeck", Warenart.BIER, 5, 2);
   	 Niederlassung niederlassungWien = new Niederlassung("Wien", Warenart.WEIN, 5, 2);
   	 
   	 Lager lagerAugsburg = new Lager("Augsburg", 5, 1);
   	 
   	 Scanner myScanner = new Scanner(System.in);
   	 
   	 int gesamterloes = 0;
   	 int spielen = 1;
   	 int taler = 0;
   	 int rundenzahl = 1;
   	 
   	 while (spielen==1) {
   		 
   		System.out.println("==================================== Spielrunde " + rundenzahl);
   		 
   		System.out.println("Aktueller Bestand im Lager:");
   	    for (Iterator<Warenart> warenartIterator = lagerAugsburg.getEingelagerteWaren().iterator(); warenartIterator.hasNext();) {
   		 Warenart warenart = (Warenart) warenartIterator.next();
   		 System.out.println(Util.convertWarenartToString(warenart)+": "+lagerAugsburg.getBestand(warenart));  		 
   		}
   		
   	    System.out.println("Guthaben: " + taler); 
   		 
   		System.out.println("\nSoll produziert werden? [j/n]");
		String userInput = myScanner.nextLine();
		
		if (userInput.equals("j")) {
			
			niederlassungEinbeck.anfordern();
		   	niederlassungWien.anfordern();
		   	 
		   	niederlassungEinbeck.produzieren();
		   	niederlassungWien.produzieren();
		}
		
		int produzierteMengeEinbeck = niederlassungEinbeck.abholen();
	   	int produzierteMengeWien = niederlassungWien.abholen();
	   	 
	   	Warenart produzierteWarenart = niederlassungEinbeck.getWarenart();
	   	Warenart produzierteWarenartWien = niederlassungWien.getWarenart();
	   	
	   	System.out.println("Produktion:");
	   	System.out.println("Ort: "+niederlassungEinbeck.getOrt()+"\tWare: "
   			 +niederlassungEinbeck.getWarenartString()+"\tMenge: "+produzierteMengeEinbeck);
	   	
   	    System.out.println("Ort: " + niederlassungWien.getOrt() + "\tWare: "
   			 + niederlassungWien.getWarenartString() + "\tMenge: " + produzierteMengeWien);
   	    
   	    lagerAugsburg.einlagern(produzierteWarenart, produzierteMengeEinbeck);
   	    lagerAugsburg.einlagern(produzierteWarenartWien, produzierteMengeWien);
   	    
   	    int preis = 10;
   	    
   	    System.out.println("\nWie viel Bier soll verkauft werden? [0.." + lagerAugsburg.getBestand(Warenart.BIER) + "]");
		int mengeZuVerkaufendenBieres = Integer.parseInt(myScanner.nextLine());

		System.out.println("Wie viel Wein soll verkauft werden? [0.." + lagerAugsburg.getBestand(Warenart.WEIN) + "]");
		int mengeZuVerkaufendenWeines = Integer.parseInt(myScanner.nextLine());
   	 
		int erloesBier = lagerAugsburg.verkaufen(produzierteWarenart, mengeZuVerkaufendenBieres, preis);
		int erloesWein = lagerAugsburg.verkaufen(produzierteWarenartWien, mengeZuVerkaufendenWeines, preis);
   	    
		int LohnEinbeck = niederlassungEinbeck.getGesamtlohn();
		int LohnWien = niederlassungWien.getGesamtlohn();
		int LohnAugsburg = lagerAugsburg.getGesamtlohn();
		
   	    gesamterloes = erloesBier + erloesWein - (LohnEinbeck + LohnWien + LohnAugsburg);
   	    taler = taler + gesamterloes;
   	    rundenzahl = rundenzahl + 1;
   	    
   	    System.out.println("Erloes durch Verkauf von Bier: " + erloesBier);
   	    System.out.println("Erloes durch Verkauf von Wein: " + erloesWein);
   	    System.out.println("Kosten durch Loehne: " + (LohnWien + LohnEinbeck + LohnAugsburg));
   	    System.out.println("Gesamterloes: " + gesamterloes);
		
   	    System.out.println("\naktuelle Mitarbeiter in Einbeck: " + niederlassungEinbeck.getAnzahlArbeiter());
		System.out.println("aktuelle Mitarbeiter in Wien: " + niederlassungWien.getAnzahlArbeiter());
		
		System.out.println("Soll die Mitarbeiteranzahl angepasst werden? [j/n]");
   	    
		String anpassen = myScanner.nextLine();
		
		if (anpassen.equals("j")) {		
			
			System.out.println("Einstellen oder Entlassen?");
			String userInput3 = myScanner.nextLine();
			
			if (userInput3.equals("Einstellen")) {
				
				System.out.println("neue Mitarbeiter in Einbeck: ");
				int neueMitarbeiterEinbeck = Integer.parseInt(myScanner.nextLine());
				
				System.out.println("neue Mitarbeiter in Wien: ");
				int neueMitarbeiterWien = Integer.parseInt(myScanner.nextLine());
				
				niederlassungEinbeck.ArbeiterEinstellen(neueMitarbeiterEinbeck);
				niederlassungWien.ArbeiterEinstellen(neueMitarbeiterWien);
			}
			
			else {
				
				System.out.println("zu entlassende Mitarbeiter in Einbeck: [0..." + niederlassungEinbeck.getAnzahlArbeiter() + "]");
				int freigesetzteMitarbeiterEinbeck = Integer.parseInt(myScanner.nextLine());
				System.out.println("zu entlassende Mitarbeiter in Wien: [0..." + niederlassungWien.getAnzahlArbeiter() + "]");
				int freigesetzteMitarbeiterWien = Integer.parseInt(myScanner.nextLine());	
				
				niederlassungEinbeck.arbeiterEntlassen(freigesetzteMitarbeiterEinbeck);
				niederlassungWien.arbeiterEntlassen(freigesetzteMitarbeiterWien);
			}
	}
		
   	    
   	 String weiterspielen;
		do {
			System.out.println("\nNaechste Runde? [j/n]");
			weiterspielen = myScanner.nextLine();;
			
			if( weiterspielen.equals ("j")) {
				spielen=1;
			}
			
			if( weiterspielen.equals ("n")) {
				spielen=0;
				System.out.println("====================================");
				System.out.println("Auswertung nach Ende der Runden:");
				
				System.out.println("\nAktueller Bestand im Lager:");
		   	    for (Iterator<Warenart> warenartIterator = lagerAugsburg.getEingelagerteWaren().iterator(); warenartIterator.hasNext();) {
		   		 Warenart warenart = (Warenart) warenartIterator.next();
		   		 System.out.println(Util.convertWarenartToString(warenart)+": "+lagerAugsburg.getBestand(warenart));  		 
		   		}
		   	    
		   	    System.out.println("Guthaben: " + taler);
			
			}
			
		}while(!(weiterspielen.equals("j")) && !(weiterspielen.equals ("n")));
   	    
		}
				
	}

}
