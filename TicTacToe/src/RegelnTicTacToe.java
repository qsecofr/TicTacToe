
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;

public class RegelnTicTacToe 
{
	public static final int LEER  = 0;
	public static final int KREUZ = 1;
	public static final int KREIS = 2;
	
	public static final int[] SPIELER = {KREUZ,KREIS};
		   
	public static final int ZEILEN  = 3;
	public static final int SPALTEN = 3;

	private Spielfeld spielfeld = new Spielfeld(ZEILEN, SPALTEN, LEER);
	private int aktuellerSpieler = 0;
	
	private static final Logger LOGGER = Logger.getLogger(RegelnTicTacToe.class.getName());	
	private static final String LOGGER_GEWINNER = "abgeschlossen: noch kein Gewinner.";	
	private static final String LOGGER_KEIN_GEWINNER = "abgeschlossen: noch kein Gewinner.";
			
	

	public boolean pruefeGewinner() 
    {
    	LOGGER.info(".. pruefe Gewinner.. Spieler: " + aktuellerSpieler);
    	return (pruefeSpalten(aktuellerSpieler) || pruefeZeilen(aktuellerSpieler) || pruefeDiagonale(aktuellerSpieler));
    }

	private boolean pruefeSpalten(int spieler) 
	{
		LOGGER.info(".. pruefe Spalten.. ");
		for (int i=1; i<=ZEILEN; i++)
		{
			if ((
					(spielfeld.getFeldwert(i, 1) == spieler) 
					&& 	(spielfeld.getFeldwert(i, 2) == spieler) 
					&& 	(spielfeld.getFeldwert(i, 3) == spieler)
				) 
			== true)
			{
				LOGGER.info(LOGGER_GEWINNER);
				return true;
			}
		}
		LOGGER.info(LOGGER_KEIN_GEWINNER);
		return false;
	}

	private boolean pruefeZeilen(int spieler) 
	{
		LOGGER.info(".. pruefe Zeilen.. ");
		for (int i=1; i<=SPALTEN; i++)
		{
				if ((
							(spielfeld.getFeldwert(1, i) == spieler) 
						&& 	(spielfeld.getFeldwert(2, i) == spieler) 
						&& 	(spielfeld.getFeldwert(3, i) == spieler)
					) 
				== true)
				{
					LOGGER.info(LOGGER_GEWINNER);
					return true;
				}
		}
		LOGGER.info(LOGGER_KEIN_GEWINNER);
		return false;
	}

	private boolean pruefeDiagonale(int spieler) 
	{
		LOGGER.info(".. pruefe Diagolnalen.. ");
		if (
				(
					(spielfeld.getFeldwert(1, 1) == spieler) 
				&& 	(spielfeld.getFeldwert(2, 2) == spieler) 
				&& 	(spielfeld.getFeldwert(3, 3) == spieler)
				)
			||
				(
					(spielfeld.getFeldwert(1, 3) == spieler) 
				&& 	(spielfeld.getFeldwert(2, 2) == spieler) 
				&& 	(spielfeld.getFeldwert(3, 1) == spieler)
				)
			== true)
		{
			LOGGER.info(LOGGER_GEWINNER);
			return true;
		}
		else 
		{
			LOGGER.info(LOGGER_KEIN_GEWINNER);
			return false;
		}
	}
	
	public void zeigeSpielstand()
	{
		spielfeld.zeigeSpielfeld();
	}
	
	public boolean pruefeZug(int zeile, int spalte, int spieler)
	{
		LOGGER.info(".. pruefe Zug.. Spieler: " + aktuellerSpieler + " Zeile: " + zeile + " , Spalte: " + spalte);
		
		if (spielfeld.getFeldwert(zeile, spalte) == LEER)
		{
			spielfeld.setFeldwert(zeile, spalte, spieler);
			return true;
		} 
		else
		{ return false; }
	}
	
	public boolean naechsterZug()
	{
		return spielfeld.freieFelder();
	}

	public void naechsterSpieler() 
	{
		LOGGER.info("Spielerwechsel.. alter Spieler " + aktuellerSpieler);

		if (aktuellerSpieler == 0 || aktuellerSpieler == 2)
			{ aktuellerSpieler = 1; }
		else if (aktuellerSpieler == 1)
			{ aktuellerSpieler = 2; }
		System.out.println(" -> neuer Spieler " + aktuellerSpieler);
	}
	
	public int  getAktuellerSpieler()
	{
		return aktuellerSpieler;
	}

	public void macheZug() 
	{
		LOGGER.info("mache Zug.. für Spieler " + aktuellerSpieler);

		int[] koordinaten = {-1, -1};
				
		try
		{
			koordinaten = eingabeAufforderung();
		}
		catch(TicTacToeException e)
		{
			e.getCause();
		}
        
		//print Aufforderung zur Eingabe
		//read Lesen der Eingabe
		
		pruefeZug(koordinaten[0], koordinaten[1], aktuellerSpieler);
	}

	private int[] eingabeAufforderung() throws TicTacToeException
	{
		int[] koordinaten = {-1, -1};  //koordinaten[0] -> Zeile, koordinaten[1] -> Spalte
		
		boolean fehlerEingabeZeile = true;
		boolean fehlerEingabeSpalte = true;
		
		Scanner eingabe = new Scanner(System.in);
		
		LOGGER.info("Eingabe Aufforderung: " + fehlerEingabeZeile + fehlerEingabeSpalte + koordinaten[0] + koordinaten[1]);

		
        try 
        {
            while (fehlerEingabeZeile)
            {

            	System.out.print("Du bist an der Reihe. Bitte trage die Zeile (1-3) ein: ");
	            
            	fehlerEingabeZeile = eingabeDialog(eingabe, koordinaten, 0);
            	if (koordinaten[0] < 1 || koordinaten[0]> 3)
            	{
            		throw new TicTacToeException("Eingabe der Zeile kann von TicTacToe nicht verarbeitet werden.");
            	}
            }
            
            while (fehlerEingabeSpalte)
            {
	            System.out.print("Bitte gib die Spalte ein (1-3): ");
	            
	            fehlerEingabeZeile = eingabeDialog(eingabe, koordinaten, 1);
	            
            	if (koordinaten[1] < 1 || koordinaten[1] > 3)
            	{throw new TicTacToeException("Eingabe der Spalte kann von TicTacToe nicht verarbeitet werden.");}
            }
            
        } catch(Exception e) 
        {
            e.printStackTrace();
        } 
        finally 
        {
//        	eingabe.close();
        	fehlerEingabeZeile = true;
        	fehlerEingabeSpalte = true;

        }
        return koordinaten;
	}

	private boolean eingabeDialog(Scanner eingabe, int[] koordinaten, int idx) 
	{
		boolean fehlerEingabe = true;
		try 
        {
        	koordinaten[idx] = eingabe.nextInt();  
        	fehlerEingabe = false;
        }
        catch(InputMismatchException e)
        {
        	 System.out.println("Das Programm erwartet einen Zahl, du hast " + eingabe.toString() + " eingegeben.");
        	 fehlerEingabe = true;
        }
		return fehlerEingabe;
	}
}
