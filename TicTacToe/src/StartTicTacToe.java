
public class StartTicTacToe 
{	   
	   private static RegelnTicTacToe regeln = new RegelnTicTacToe(); 
	   
	   public static void main(String[] args) 
	   {
		   int runde = 0;
		   regeln.zeigeSpielstand();
		   
		   while ( regeln.naechsterZug() )
		   	{
			   runde++;
			   System.out.println("Runde " + runde);
			   regeln.naechsterSpieler();
			   
			   regeln.macheZug();
			   
			   System.out.println("Zeige Spielstand..");
			   regeln.zeigeSpielstand();
			   
			   if (regeln.pruefeGewinner())				
			   {
				   System.out.println("Spieler " + regeln.getAktuellerSpieler() + " hat gewonnen");
				   System.exit(0);
			   }

			   //
			}
//		   else 
//		   {
//			   print Unentschieden)
//		   }
		   
		   
//	       int Zufallszahl = (int) ((Math.random()*1000)+1);
		   
	   }
	   	   
	   
}
