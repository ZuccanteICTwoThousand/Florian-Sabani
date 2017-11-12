package es2callegarin;

import java.awt.Color;
import zuclib.GraficaSemplice;
import zuclib.Tartaruga;

/**
 *
 * @author Florian
 */
class PoligonoPlex {

    private int nLati;
    private double lunghezzaLato;

    private double[] puntiX, puntiY;

    public PoligonoPlex(int nLati, double lunghezzaLato) {
        this.nLati = nLati;
        this.lunghezzaLato = lunghezzaLato;
        this.puntiX = new double[this.nLati];
        this.puntiY = new double[this.nLati];
    }

    public void disegna() {
        Tartaruga t = new Tartaruga();

        t.pennaSu();
        t.gotoXY(.5,.5 /*nn mi piace*/);
        t.pennaGiu();
        for (int i = 0; i < this.nLati; i++) {
            t.avanti(lunghezzaLato);
            t.sinistra(360 / this.nLati);
            this.puntiX[i] = t.getX();
            this.puntiY[i] = t.getY();
        }

        this.disegnaPlexus(t);
    }

    private void disegnaPlexus(Tartaruga t) {
        for (int i = 0; i < this.nLati; i++) {
            double xAttuale = t.getX(), yAttuale = t.getY();
            for (int j = 0; j < this.nLati; j++) {
                //t.setRitardoMillisec(200);
                t.gotoXY(this.puntiX[j], this.puntiY[j]);
                t.pennaSu();
                t.gotoXY(xAttuale, yAttuale);
                t.pennaGiu();
            }
            t.pennaSu();
            t.gotoXY(this.puntiX[i], this.puntiY[i]);
            t.pennaGiu();
        }
    }
    
    
}
