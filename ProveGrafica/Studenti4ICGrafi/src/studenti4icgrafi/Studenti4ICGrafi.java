package studenti4icgrafi;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 *
 * @author Florian
 */
public class Studenti4ICGrafi {

    public static void main(String[] args) {
        RappresentaGrafo c = new RappresentaGrafo(5 + new Random().nextInt(3));

        JFrame j = new JFrame();
        j.add(c);
        j.setBounds(100, 100, 700, 500);  // dove spawna e la larghezza altezza
        j.setSize(700, 500);
        j.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        j.setResizable(true);
        j.setTitle("Rappresentatore Grafi 4IC #Sabani-Florian");
        j.setVisible(true);
    }
}

class RappresentaGrafo extends JPanel {

    private int nNodi;
    private Nodo<String>[] nodi;
    private Grafo grafo;
    private int diametro;
    private Point[] punti;
    private Rectangle zonaInfo;

    public RappresentaGrafo(int nNodi) {

        this.nNodi = nNodi;
        diametro = (20 / nNodi) * 15;

        nodi = this.assegnaNodiRandom(nNodi);
        this.grafo = new Grafo<>(nodi);
        Arco c = grafo.getMigliorCammino(grafo.getNodo(0), grafo.getNodo(grafo.getNodi().length - 1));

        int infoPxNodo = 20;
        int nMaxCol = grafo.getNodo(0).getCollegamenti().getNElementi();

        for (int i = 1; i < nNodi; i++) {
            if (grafo.getNodo(i).getCollegamenti().getNElementi() > nMaxCol) {
                nMaxCol = grafo.getNodo(i).getCollegamenti().getNElementi();
            }
        }
        int larghezza = 100 + nMaxCol * infoPxNodo, altezza = infoPxNodo * nNodi;
        zonaInfo = new Rectangle(0, 0, larghezza, altezza);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.disegna(g);
    }

    public Nodo[] assegnaNodiRandom(int lunghezza) {
        Random r = new Random();
        Nodo[] nodi = new Nodo[lunghezza];
        char valoreChar = 'A';
        for (int i = 0; i < lunghezza; i++) {
            nodi[i] = new Nodo(valoreChar + "", getNodi(nodi, r.nextInt(lunghezza)));
            valoreChar++;
        }
        return nodi;
    }

    private Arco getNodi(Nodo[] nodi, int numCasuale) {
        Random r = new Random();
        Nodo[] collegamentoNodi = new Nodo[numCasuale];
        int[] distanzeOgniNodo = new int[numCasuale];
        for (int i = 0; i < numCasuale; i++) {
            int posCasuale = r.nextInt(nodi.length);
            collegamentoNodi[i] = nodi[posCasuale];
            distanzeOgniNodo[i] = 4 + r.nextInt(20);
        }
        Arco collegamenti = new Arco(collegamentoNodi, distanzeOgniNodo);
        return collegamenti;
    }

    private void disegna(Graphics g) {
        g.setColor(Color.lightGray);
        g.fillRoundRect(zonaInfo.x, zonaInfo.y, zonaInfo.width, zonaInfo.height, 20, 20);
        g.setColor(Color.black);
        this.punti = puntiPallini();
        for (int i = 0; i < grafo.getNodi().length; i++) {
            disegnaNodo(grafo, i, punti, g);
        }

        int xScritta = 50, yScritta = 10;
        int infoPxNodo = 20;

        
        Color coloreScritte = Color.DARK_GRAY;
        
        for (int posMia = 0; posMia < grafo.getNodi().length; posMia++, yScritta += infoPxNodo) {
            scriviCentrato((String) grafo.getNodo(posMia).getValore(), new Rectangle(punti[posMia].x, punti[posMia].y, 0, 0), new Font("Raku.ttf", 30, 20), g,Color.red);
            scriviCentrato("Val n^" + posMia + " : " + grafo.getNodo(posMia).getValore().toString(), new Rectangle(xScritta, yScritta, 0, 0), new Font("Raku.ttf", 30, 13), g,coloreScritte);

            if (grafo.getNodo(posMia).getCollegamenti().getNElementi() > 0) {
                xScritta *= 2;
                scriviCentrato("Col: ", new Rectangle(xScritta, yScritta, 0, 0), new Font("Raku.ttf", 30, 13), g,coloreScritte);
            }

            for (int i = 0; i < grafo.getNodi().length; i++) {
                if (isCollegato(grafo.getNodo(posMia).getCollegamenti(), grafo.getNodo(i))) {
                    xScritta += 20;
                    scriviCentrato(grafo.getNodo(i).getValore() + " ", new Rectangle(xScritta, yScritta, 0, 0), new Font("Raku.ttf", 30, 13), g,coloreScritte);
                }
            }

            xScritta = 50;
        }
    }

    private void disegnaNodo(Grafo grafo, int posMia, Point[] punti, Graphics g) {
        g.fillOval(punti[posMia].x - this.diametro / 2, punti[posMia].y - this.diametro / 2, diametro, diametro);
        int nodiConnessi = 0;
        for (int i = 0; i < grafo.getNodi().length; i++) {
            if (isCollegato(grafo.getNodo(posMia).getCollegamenti(), grafo.getNodo(i))) {
                disegnaLinea(punti[posMia].x, punti[posMia].y, punti[i].x, punti[i].y, g, grafo.getNodo(posMia).getCollegamenti().getDistanza(nodiConnessi));
                nodiConnessi++;
            }
        }
    }

    private void disegnaLinea(int x, int y, int x0, int y0, Graphics g, int nodoValore) {
        int mediaX = x0 - x;
        int mediaY = y0 - y;
        double angolo = Math.atan2(mediaY, mediaX); // Angolazione dati i due punti...
        double distanzaTraIPunti = new Point(x, y).distance(new Point(x0, y0));
        linea(x, y, angolo, distanzaTraIPunti - (diametro / 2), g, nodoValore);// Toglo alla distanza il raggio
    }

    public void linea(int xInizio, int yInizio, double angolo, double lunghezza, Graphics g, int nodoValore) {
        int xFine = (int) (xInizio + (Math.cos(angolo) * lunghezza));   //X
        int yFine = (int) (yInizio + (Math.sin(angolo) * lunghezza));   //Y

        int metaX = (int) (xInizio + (Math.cos(angolo) * lunghezza / 2));
        int metaY = (int) (yInizio + (Math.sin(angolo) * lunghezza / 2));

        scriviCentrato(nodoValore+"", new Rectangle(metaX,metaY, 0, 0), new Font("Raku.ttf", 30, 20), g,Color.BLACK);

        g.drawLine(xInizio, yInizio, xFine, yFine); // la disegno dc
        disegnaFreccia(g, xInizio, yInizio, xFine, yFine, 10, 9);
    }

    private void disegnaFreccia(Graphics g, int x1, int y1, int x2, int y2, int larghezza, int altezza) {
        int catetoX = x2 - x1, catetoY = y2 - y1;
        double ipotenusa = Math.sqrt(catetoX * catetoX + catetoY * catetoY);
        double lunghX = ipotenusa - larghezza, larghX = lunghX, lunghY = altezza, larghY = -altezza, xPos;
        double sin = catetoY / ipotenusa, cos = catetoX / ipotenusa;

        xPos = lunghX * cos - lunghY * sin + x1;
        lunghY = lunghX * sin + lunghY * cos + y1;
        lunghX = xPos;

        xPos = larghX * cos - larghY * sin + x1;
        larghY = larghX * sin + larghY * cos + y1;
        larghX = xPos;

        int[] xpoints = {x2, (int) lunghX, (int) larghX};
        int[] ypoints = {y2, (int) lunghY, (int) larghY};

        g.setColor(Color.red);
        g.fillPolygon(xpoints, ypoints, 3);
        g.setColor(Color.black);
    }

    private Point[] puntiPallini() {
        Random r = new Random();
        Point[] punti = new Point[nodi.length];
        int percentuale = 10; // percentu
        int bordoX = (super.getWidth() / percentuale);
        int bordoY = (super.getHeight() / percentuale);

        int distanzaMinima = 70;    // settare meglio

        int larghezza = super.getWidth();
        int altezza = super.getHeight();

        for (int i = 0; i < punti.length; i++) {
            int posX = bordoX + r.nextInt(larghezza - (bordoX * 2));
            int posY = bordoY + r.nextInt(altezza - (bordoY * 2));

            while (isVicino(new Point(posX, posY), punti, distanzaMinima, i)) { // da rifare :D
                posX = bordoX + r.nextInt(larghezza - (bordoX * 2));
                posY = bordoY + r.nextInt(altezza - (bordoY * 2));
            }

            punti[i] = new Point(posX, posY);
        }
        return punti;
    }

    private boolean isVicino(Point point, Point[] punti, int distanzaMinima, int posMax) {

        if ((point.x > 0 && point.x < zonaInfo.width + distanzaMinima) && (point.y > 0 && point.y < zonaInfo.height + distanzaMinima)) {
            return true;
        }

        for (int i = 0; i < posMax; i++) {
            if (point.distance(punti[i]) < distanzaMinima) {
                return true;
            }
        }
        return false;
    }

    private boolean isCollegato(Arco collegamenti, Nodo nodo) {
        for (int i = 0; i < collegamenti.getNElementi(); i++) {
            if (nodo == collegamenti.getNodo(i)) {
                return true;
            }
        }
        return false;
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

}
