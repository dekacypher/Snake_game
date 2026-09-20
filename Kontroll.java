import javax.swing.JLabel;

public class Kontroll{
    private Oblig7GUI gui; 
    private Modell modell; 
    private Thread traad; 
    private Rettning ret = Rettning.SOR; 
    public JLabel[][] ruter;



  public Kontroll(){
    gui = new Oblig7GUI(this); 
    modell = new Modell(gui,this); 
    ruter = gui.hentRuter(); 
    traad = new Thread(new Teller()); 
    traad.start();
   
  }

  public boolean gyldig(int x, int y){
    return x <= ruter.length && y <= ruter.length && x >= 0 && y >= 0;
  }


class Teller implements Runnable{
  @Override
  public void run(){
    while(true){
      try {
        Thread.sleep(650);
      } catch (Exception e) {
          return; 
      }
      modell.flytt(ret);
    }
  }
}

public void flytt(){
  modell.flytt(ret);
}



public void settRettning(Rettning rettning){
  System.out.println(rettning);
  this.ret = rettning; 

}

public static void main(String[] args) {
  Kontroll kontroll = new Kontroll(); 
  
}




}

  



  
