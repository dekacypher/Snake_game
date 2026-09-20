import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;

 
public class Modell{
    int posLengde, posBredde = 6; 
    private Oblig7GUI gui; 
   // private String [][] brett = new String[12][12]; 
    //private Kontroll c; 
    ArrayList<Koordinater> slange = new ArrayList<>();
    public JLabel[][] ruter; 

    Modell(Oblig7GUI gui, Kontroll c){
        this.gui = gui; 
        ruter = gui.hentRuter(); 
      plasserSkattStart(); 
    }

   public void plasserSkattStart(){
        for(int i = 0; i < 10; i++){
            plasserSkatt(); 
        }
    }

   private void plasserSkatt(){
        Random skattRandom = new Random(); 
        int rad = skattRandom.nextInt(12); 
        int kolonne = skattRandom.nextInt(12); 
        ruter[rad][kolonne] = new JLabel("$");
        gui.kallSkatt(rad, kolonne);
    }

    public void flytt(Rettning rettning){
        gui.drepSlange(posLengde, posBredde);

        if(Rettning.NORD.equals(rettning)) posLengde--;
        if(Rettning.SOR.equals(rettning)) posLengde++;
        if(Rettning.OST.equals(rettning) ) posBredde++;
        if(Rettning.VEST.equals(rettning)) posBredde--;
      
        gui.tegnSlange(posLengde, posBredde); //tegnes ikke opp
      
      }

   
    public void leggTilRute(Koordinater k){
        //if sjekk som sjekker om k er en gyldig pos i rutenettet
        //....if sjekk som sjekker om det er en skatt i den pos. 
        //hvis ^ stemmer, opprettes en grønn rute og settes inn foran. 
        
        JLabel rute = new JLabel(" "); 
        rute.setBackground(Color.GREEN);
        slange.add(0, k); 
        

    }

    


    
}