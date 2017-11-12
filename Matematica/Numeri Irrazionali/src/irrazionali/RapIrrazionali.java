package irrazionali;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
/**
 *
 * @author Florian
 */
public class RapIrrazionali extends JPanel{

    private int LARGHEZZA_SCHERMO;
    private int ALTEZZA_SCHERMO;
    JFrame schermo;
    Graphics grafica;
    private final int UNO = 50;
    private final int PERPENDICOLARE = 45;
    
    private int radice = 0;
    
    public RapIrrazionali(int larghezza,int altezza,int radice) {
        this.LARGHEZZA_SCHERMO = larghezza;
        this.ALTEZZA_SCHERMO = altezza;
        this.setRadice(radice);
        schermo = new JFrame();
        schermo.add(this);
        schermo.setSize(larghezza, altezza);
        schermo.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        schermo.setResizable(false);
        schermo.setVisible(true);
    }
    
    @Override
    public void paintComponent(Graphics g){
        grafica =  g;
        disegna(radice);
    }
    
    public void disegna(int radice) {
        grafica.drawLine(LARGHEZZA_SCHERMO/2, ALTEZZA_SCHERMO/2, LARGHEZZA_SCHERMO, ALTEZZA_SCHERMO/2);
        disegnaRicorsivo(0,radice,LARGHEZZA_SCHERMO/2,ALTEZZA_SCHERMO/2,-45);// contatore x y angolo
    }

    private void disegnaRicorsivo(int contatore,int radice, int x, int y, double angolo) {
        double perpendicolare = angolo+PERPENDICOLARE;
        Point punto = linea(x,y,perpendicolare,Math.sqrt(contatore)*UNO);
        
        int arrivoX = (int) ((LARGHEZZA_SCHERMO/2)+Math.sqrt(contatore)*UNO)/2;
        int arrivoY = (int) ((ALTEZZA_SCHERMO/2)+Math.sqrt(contatore)*UNO)/2;
        //grafica.drawArc(punto.x,punto.y, (punto.x+arrivoX)/2,(punto.y+arrivoY)/2,0, (int) getAngolo(perpendicolare));
        grafica.drawLine(LARGHEZZA_SCHERMO/2,ALTEZZA_SCHERMO/2, punto.x,punto.y);
        
        if(radice>0){
            disegnaRicorsivo(contatore+1,radice-1,punto.x,punto.y,perpendicolare);
        }else{
            System.out.println("Finito :D");
        }
    }

    private void scriviCentrato(String testo, Rectangle rettangolo, Font font, Graphics g,Color colore) {
        // FontMetrics e una classe che dato il font lo converte in px lettera per lettera
        FontMetrics metrica = g.getFontMetrics(font);
        // Troviamo le coordinate x per il testo
        int x = rettangolo.x + (rettangolo.width - metrica.stringWidth(testo)) / 2;
        // Troviamo la coordinata y 
        //+getAscent perche in JavaFX si parde da in alto a destra
        int y = rettangolo.y + ((rettangolo.height - metrica.getHeight()) / 2) + metrica.getAscent();

        g.setFont(font);
        g.setColor(colore);
        g.drawString(testo, x, y);
        g.setColor(Color.black);
    }
    
    public Point linea(int xInizio, int yInizio, double angolo, double lunghezza) {
        int xFine = (int) (xInizio + (Math.cos(-angolo) * lunghezza));   //X
        int yFine = (int) (yInizio + (Math.sin(-angolo) * lunghezza));   //Y
        
        int xMeta = (int) (xInizio + (Math.cos(-angolo) * lunghezza/2));   //X
        int yMeta = (int) (yInizio + (Math.sin(-angolo) * lunghezza/2));   //Y
        
        scriviCentrato("√"+Math.round(lunghezza/UNO),new Rectangle(xMeta,yMeta),new Font("Arial",10,20),grafica,Color.red);
        grafica.drawLine(xInizio, yInizio, xFine, yFine); // la disegno dc
        return new Point(xFine,yFine);
    }
    
    private double getAngolo(double angolo){
        if(angolo>360){
            return getAngolo(angolo-360);
        }
        return angolo;
    }
    
    private void setRadice(int radice) {
        this.radice = radice;
    }
    
}
