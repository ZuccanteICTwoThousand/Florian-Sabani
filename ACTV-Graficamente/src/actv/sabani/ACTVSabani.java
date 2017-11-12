package actv.sabani;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.scene.shape.Line;

import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author ps09
 */
public class ACTVSabani extends JFrame {

    private int LARGHEZZA = 1000, ALTEZZA = 1000;

    ACTVSabani(Mappa mappa) {
        this.setSize(LARGHEZZA, ALTEZZA);
        this.setDefaultCloseOperation(3);
        this.add(new Schermo(mappa), BorderLayout.CENTER);
        this.setVisible(true);
    }

    public static void main(String[] args) throws FileNotFoundException {
        //Creo la mappa e aggiungo le coordinate lette da stops.csv
        Mappa mappa = new Mappa("stops.csv");
        // E difficile trovarsi equidistante tra piu fermate , o trovarsi esattamente in una fermata
        //In quanto i gps non hanno precisione Assoluta , ma parziale.

        new ACTVSabani(mappa);
    }

    private class Schermo extends JComponent {

        private ArrayList<Point> fermate = new ArrayList<>();
        private ArrayList<Line> collegamenti = new ArrayList<>();

        private Mappa veneto;
        private int raggio = 10;

        double valoreMinimoX;
        double valoreMassimoX;

        double valoreMinimoy;
        double valoreMassimoy;
        int centramento  =500;
        public Schermo(Mappa m) {

            veneto = m;
            double sfasamento = .2;
            valoreMinimoX = minValueX(veneto.lista)-sfasamento;
            valoreMassimoX = maxValueX(veneto.lista)+sfasamento;
            valoreMinimoy = minValueY(veneto.lista)-sfasamento;
            valoreMassimoy = maxValueY(veneto.lista)+sfasamento;
            
            for (int i = 0; i < veneto.lista.lunghezza; i++) {
                fermate.add(new Point(scaleX(veneto.lista.getInfo(i).xPos)-centramento, scaleY(veneto.lista.getInfo(i).yPos)));
            }

            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    Point punto = new Point(e.getXOnScreen(), e.getYOnScreen());
                    fermate.add(punto);
                    calcolaDistanze(punto);
                    repaint();
                }
            });
        }

        private void calcolaDistanze(Point p) {
            double latMouseX = p.getX();
            double longMouseY = p.getY();
            // indico con un toString particolare dov'è la fermata/e piu vicina/e. 
            //(In quanto si puo essere in una posizione equidistante tra piu fermate)

            double xTrasformato = userX(latMouseX);
            double yTrasformato = userY(longMouseY);

            System.out.println("x : " + xTrasformato);
            System.out.println("y : " + yTrasformato);

            Line lineaCollegamentoFermata = new Line(latMouseX, longMouseY,
                    scaleX(veneto.search(xTrasformato, yTrasformato).getInfo(0).xPos), scaleY(veneto.search(xTrasformato, yTrasformato).getInfo(0).yPos));
            collegamenti.add(lineaCollegamentoFermata);
            System.out.println(scaleX(veneto.search(xTrasformato, yTrasformato).getInfo(0).xPos));
        }

        private void personalizzaGrafica(Graphics2D g2) {
            g2.setPaint(Color.LIGHT_GRAY);
            g2.setStroke(new BasicStroke(2));
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.50f));
        }

        @Override
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            personalizzaGrafica(g2);

            Color[] colors = {Color.YELLOW, Color.MAGENTA, Color.CYAN, Color.RED, Color.BLUE, Color.PINK};
            int posizioneColore = 0;

            for (Point s : fermate) {
                g2.setPaint(Color.BLACK);
                g2.drawOval(s.x, s.y, raggio, raggio);
                g2.setPaint(colors[(posizioneColore++) % colors.length]);
                g2.fillOval(s.x, s.y, raggio, raggio);
            }

            for (Line s : collegamenti) {
                g2.setPaint(colors[(posizioneColore++) % colors.length]);
                g2.drawLine((int) s.getStartX()+centramento, (int) s.getStartY(), (int) s.getEndX()-centramento, (int) s.getEndY());
            }
        }

        private int scaleX(double x) {
            return (int) ((LARGHEZZA * (x - valoreMinimoX) / (valoreMassimoX - valoreMinimoX)) + LARGHEZZA / 2);
        }

        private int scaleY(double y) {
            return (int) ((ALTEZZA * (y - valoreMassimoy) / (valoreMassimoy - valoreMinimoy)) + ALTEZZA);
        }

        private double minValueX(Lista<Mappa.PuntoMappa> lista) {
            double min = lista.getInfo(0).xPos;
            for (int i = 0; i < lista.lunghezza; i++) {
                if (min > lista.getInfo(i).xPos) {
                    min = lista.getInfo(i).xPos;
                }
            }
            return min;
        }

        private double minValueY(Lista<Mappa.PuntoMappa> lista) {
            double min = lista.getInfo(0).yPos;
            for (int i = 0; i < lista.lunghezza; i++) {
                if (min > lista.getInfo(i).yPos) {
                    min = lista.getInfo(i).yPos;
                }
            }
            return min;
        }

        private double maxValueY(Lista<Mappa.PuntoMappa> lista) {
            double min = lista.getInfo(0).yPos;
            for (int i = 0; i < lista.lunghezza; i++) {
                if (min < lista.getInfo(i).yPos) {
                    min = lista.getInfo(i).yPos;
                }
            }
            return min;
        }

        private double maxValueX(Lista<Mappa.PuntoMappa> lista) {
            double min = lista.getInfo(0).xPos;
            for (int i = 0; i < lista.lunghezza; i++) {
                if (min < lista.getInfo(i).xPos) {
                    min = lista.getInfo(i).xPos;
                }
            }
            return min;
        }

        double userX(double x) {
            return this.valoreMassimoX + x * (this.valoreMassimoX - this.valoreMinimoX) / (double) LARGHEZZA;
        }

        double userY(double y) {
            return this.valoreMassimoy - y * (this.valoreMassimoy - this.valoreMinimoy) / (double) ALTEZZA;
        }
    }
}
