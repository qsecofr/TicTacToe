import java.util.logging.Logger;

public class Spielfeld 
{
	private static final Logger LOGGER = Logger.getLogger(RegelnTicTacToe.class.getName());
	
	private int [][] spielfeld;
	private int zeilen = 0;
	private int spalten = 0;
	
	public Spielfeld(int zeilen, int spalten, int feldwert)
	{
		if (zeilen >= 1 && zeilen <= 8 && spalten >= 1 && spalten <= 8)
		{
			this.zeilen = zeilen;
			this.spalten = spalten;
			spielfeld = new int [zeilen][spalten];
			
			for ( int i=0; i<zeilen; i++ )
			{
				for ( int j=0; j<spalten; j++ )
				{
					spielfeld[i][j] = feldwert;
				}
			}
		}
		else
		{
			System.out.println("Anzahl Zeile bzw. Spalten müssen zwischen 1 und 8 liegen.");
		}
	}
	
	public int getFeldwert(int zeile, int spalte)
	{
    	LOGGER.info("Lese Feldwert: Zeile: " + zeile + "("+ (zeile-1) +") , Spalte: " + spalte + "(" + (spalte-1) + ")");
		
//		zeile--;
//		spalte--;
		return spielfeld[zeile--][spalte--];
	}
	
	 public void zeigeSpielfeld() 
	 {	
		System.out.println("-------------");
		for (int i = 0; i<this.zeilen; i++) 
		{
			System.out.print("| ");
	        for (int j=0; j<this.spalten; j++) 
	        {
	        	System.out.print(spielfeld[i][j] + " | ");
	        }
	        System.out.println();
		    System.out.println("-------------");
		}
	}

	public boolean freieFelder() 
	{
		boolean freieFelder = false;
        for (int i=0; i<zeilen; i++) 
         {
             for (int j=0; j<spalten; j++) 
             {
                 if (spielfeld[i][j] == 0) 
                 { freieFelder = true; }
             }
         }
         return freieFelder;
     }

	public void setFeldwert(int zeile, int spalte, int spieler) 
	{
		LOGGER.info("Schreibe Feldwert: Zeile: " + zeile + "("+ (zeile-1) +") , Spalte: " + spalte + "(" + (spalte-1) + ")");
		
		spielfeld[zeile-1][spalte-1] = spieler;
	}
 
}
