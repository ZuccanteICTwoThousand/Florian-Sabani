package alberoricerca;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.util.Arrays;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author Florian
 */
public class Main extends JFrame {

    final int LARGHEZZA = 400, ALTEZZA = 600;
    final String nomeFile = "nomipropri.txt";

    Main() throws FileNotFoundException {

        Dizionario d = new Dizionario(nomeFile);
        Schermo s = new Schermo(d);
        this.setSize(LARGHEZZA, ALTEZZA);
        this.setDefaultCloseOperation(3);
        this.add(s, BorderLayout.CENTER);
        
        this.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e){
                gestisciInput(s.scritta,e);
                repaint();
            }

            private void gestisciInput(String scritta, KeyEvent tasto) {
                if((comandoIndentazione(tasto))){
                    if(tasto.getKeyCode() == KeyEvent.VK_BACK_SPACE && s.scritta.length()>0)
                        s.scritta = s.scritta.substring(0, s.scritta.length()-1);
                }else{
                    s.scritta+=(""+tasto.getKeyChar()).toLowerCase();
                }
            }

            private boolean comandoIndentazione(KeyEvent tasto) {
                return tasto.getKeyCode() == KeyEvent.VK_BACK_SPACE || 
                        tasto.getKeyCode() == KeyEvent.VK_CAPS_LOCK || 
                        tasto.getKeyCode() == KeyEvent.VK_SHIFT || 
                        tasto.getKeyCode() == KeyEvent.VK_ALT;
            }
        });
        
                
        this.setFocusable(true);
        requestFocusInWindow();
        this.setVisible(true);
    }

    public static void main(String[] a) throws FileNotFoundException {
        new Main();
    }

}

class Schermo extends JComponent {

    private Dizionario dizionario;
    protected String scritta;
        
    Schermo(Dizionario d) {
        this.dizionario = d;
        this.scritta = "";
        System.out.println("ou");
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        personalizzaGrafica(g2);

        int xCentro = super.getWidth() / 2;
        int yCentro = super.getHeight() / 2;

        scriviCentrato(scritta, new Rectangle(xCentro, yCentro, 0, 0), new Font("Raku.ttf", 30, 20), g, Color.BLACK);
        scriviCentrato("RICERCA NEL DIZIONIONARIO ITALIANO", new Rectangle(xCentro, (int) (yCentro-yCentro/1.5), 0, 0), new Font("Raku.ttf", 30, 19), g, Color.BLACK);
        scriviCentrato("by Sabani Florian 4IC C.Zuccante", new Rectangle(xCentro+xCentro/2, (yCentro-yCentro/2)+5, 0, 0), new Font("Raku.ttf", 10, 10), g, Color.BLACK);
        if(scritta.length()>0 ){
            String[] suggerimenti = dizionario.simili(scritta);
            System.out.println(Arrays.toString(suggerimenti));
            if(suggerimenti.length>0){
                System.out.println("Forse cercavi "+suggerimenti[0]);
                int spostamento = yCentro/(suggerimenti.length);
                for (int i = 0; i < suggerimenti.length && yCentro+spostamento<super.getHeight(); i++,yCentro+=spostamento) {
                    try{
                        scriviCentrato(suggerimenti[i], new Rectangle(xCentro, yCentro, 0, 0), new Font("Raku.ttf", 30, 20), g, Color.LIGHT_GRAY);
                    }catch(NullPointerException e){
                    
                    }
                }
            }
        }
    }

    private void personalizzaGrafica(Graphics2D g2) {
        g2.setPaint(Color.LIGHT_GRAY);
        g2.setStroke(new BasicStroke(2));
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.50f));
    }

    private void scriviCentrato(String testo, Rectangle rettangolo, Font font, Graphics g, Color colore) {
        // FontMetrics e una classe che dato il font lo converte in px lettera per lettera
        FontMetrics metrica = g.getFontMetrics(font);
        // Troviamo le coordinate x per il testo
        int x = rettangolo.x + (rettangolo.width - metrica.stringWidth(testo)) / 2;
        // Troviamo la coordinata y 
        //+getAscent che e il size del font
        int y = rettangolo.y + ((rettangolo.height - metrica.getHeight()) / 2) + metrica.getAscent();

        g.setFont(font);
        g.setColor(colore);
        g.drawString(testo, x, y);
        g.setColor(Color.black);
    }
}