import java.awt.*;
import java.awt.event.*;
//import java.util.Random;

import javax.swing.*;
import javax.swing.border.Border;

/////////***** KOMMENTARER ******///////
///Programmere slange
//linje 146 i Oblig7GUI tegner opp slangehodet, men linje 42 i Modell tegner ikke opp slangehode
//Traader fungerer noen ganger????????????  


public class Oblig7GUI{
    private static JFrame vindu; 
    public int dimensjon = 12;  
    Modell modell; 
    public static JLabel[][] ruter = new JLabel[12][12];
    Kontroll c; 


    public Oblig7GUI(Kontroll c){ 
        this.c = c; 
        try{
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e){
            System.exit(1);
        }

        vindu = new JFrame("SlangeSpillet"); 
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        vindu.setLocationRelativeTo(null);

        JPanel velkommen = velkomstPanel(); 
        vindu.add(velkommen);
        vindu.pack(); 
        vindu.setVisible(true); 
       
       

    }

    public  JPanel velkomstPanel(){
        JPanel velkommenPanel = new JPanel(); 
        JLabel hilsen = new JLabel("Velkommen til Slangespillet!"); 
        velkommenPanel.add(hilsen); 
        JButton start = new JButton("Start spill!"); 
        JButton slutt = new JButton("Avslutt!"); 

        class StoppAction implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e){
                System.exit(1);
            }
        }
        slutt.addActionListener(new StoppAction());
        velkommenPanel.add(slutt); 

        class StartAction implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e){
                startSpill(velkommenPanel); 
            }

        }
        start.addActionListener(new StartAction()); 
        velkommenPanel.add(start); 

        return velkommenPanel; 

    }

    public  void startSpill(JPanel fjern){
        vindu.remove(fjern);

        JPanel total = new JPanel(); 
        total.setSize(600,600);
        total.setLayout(new BorderLayout());
        
        //KONTROLLER????? Sjekk om riktig. 
        JPanel kontroller = new JPanel(); 
        kontroller.setSize(100, 100);
        kontroller.setLayout(new BorderLayout());
    
        JButton opp = new JButton("Opp"); 
        JButton ned = new JButton("Ned"); 
        JButton hoyre = new JButton("Hoyre"); 
        JButton venstre = new JButton("Venstre"); 

       class InnerClass implements ActionListener {
        @Override
        public void actionPerformed (ActionEvent e){
            c.settRettning(Rettning.NORD); 
        }
    }
        class InnerClass2 implements ActionListener{
            @Override
            public void actionPerformed (ActionEvent e){
                c.settRettning(Rettning.SOR); 
            }
              
        } 
         class InnerClass3 implements ActionListener{
            public void actionPerformed (ActionEvent e){
                c.settRettning(Rettning.OST); 
            }
               
         }
           class InnerClass4 implements ActionListener{
            public void actionPerformed (ActionEvent e){
                c.settRettning(Rettning.VEST); 
            }
        }
           
       hoyre.addActionListener(new InnerClass3());
       venstre.addActionListener(new InnerClass4());
       opp.addActionListener(new InnerClass());
       ned.addActionListener(new InnerClass2()); 

        kontroller.add(opp, BorderLayout.NORTH); 
        kontroller.add(ned, BorderLayout.SOUTH); 
        kontroller.add(hoyre, BorderLayout.EAST); 
        kontroller.add(venstre, BorderLayout.WEST); 

        total.add(kontroller, BorderLayout.NORTH); 

        ///Avslutt Knapp
        JButton slutt = new JButton("Avslutt!"); 

        class StoppBehandler implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e){
                System.exit(1);
            }
        }
        slutt.addActionListener(new StoppBehandler());
        JPanel sluttKnapp = new JPanel(); ///???
        sluttKnapp.setLayout(new BorderLayout());
        sluttKnapp.add(slutt, BorderLayout.NORTH); 
        total.add(sluttKnapp, BorderLayout.EAST); 
        
        //RUTENETTET??? 
        JPanel brett = tegnBrett();
        //tegnSlange(6, 6);///____?????? tegnes opp? 
        total.add(brett); 
        modell = new Modell(this, c); 


        //Lengde____????
        int teller = 0; 
        JLabel lengde = new JLabel("Lengde: " + teller); 
        total.add(lengde, BorderLayout.WEST); 

        
        ///Setter Sammen
        vindu.add(total); 
        vindu.validate();

    }

    private  JPanel tegnBrett(){
       JPanel brett = new JPanel();
       brett.setLayout(new GridLayout(dimensjon,dimensjon)); 

       for(int rx = 0; rx < dimensjon; rx++ ){
           for(int kx = 0; kx < dimensjon; kx++){
               JLabel rute = new JLabel(" "); 
               Border kant = BorderFactory.createLineBorder(Color.BLACK, 1); 
               rute.setBorder(kant);
               ruter[rx][kx] = rute; 
               rute.setOpaque(true);
    
               brett.add(rute); 

            }
    
        }
        return brett;
    }


   /*public static void tegnSlange(int posLengde, int posBredde ){
        JLabel rute = ruter[posLengde][posBredde];
         rute.setBackground(Color.GREEN);
         rute.setOpaque(true);
}*/

/*public void gydligPos(){ ///random start pos for slangehode
    Random pos = new Random(); 
    int rad = pos.nextInt(Oblig7GUI.dimensjon); 
    int kolonne = pos.nextInt(Oblig7GUI.dimensjon); 
    tegnSlange(rad, kolonne);    
}*/

public void kallSkatt(int rad, int kolonne){ ///kaller medtode i konstruktør? 
    ruter[rad][kolonne].setForeground(Color.RED);
    ruter[rad][kolonne].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
    ruter[rad][kolonne].setOpaque(true);
    ruter[rad][kolonne].setText("$");
}

public void drepSlange(int rad, int kolonne){
    ruter[rad][kolonne].setBackground(Color.WHITE); ////?????
    ruter[rad][kolonne].setOpaque(true);
  }


public JLabel[][] hentRuter(){
    return ruter; 
}

public void tegnSlange(int rad, int kolonne ){
    //JLabel rute = ruter[posLengde][posBredde];
    ruter[rad][kolonne].setBackground(Color.GREEN);
    ruter[rad][kolonne].setOpaque(true);
  }

  

}