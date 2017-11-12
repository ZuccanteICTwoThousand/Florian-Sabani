package leregine;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import zuclib.GraficaSemplice;

/**
 *
 * @author Florian
 */
public class Scacchiera {

    private int nCaselle;
    private int[][] coordinateBianche, coordinateNere;
    final double GROSSEZZA_LINEA = .013;

    final String PNG_REGINA_BIANCA = "C:\\Users\\Florian\\Documents\\NetBeansProjects\\LeRegine\\src\\leregine\\biancaRegina.png";
    final String PNG_REGINA_NERA = "C:\\Users\\Florian\\Documents\\NetBeansProjects\\LeRegine\\src\\leregine\\neraRegina.png";

    final String ILLUMINA_BLU = "C:\\Users\\Florian\\Documents\\NetBeansProjects\\LeRegine\\src\\leregine\\ILLUMINA_BLU.png";
    final String ILLUMINA_ROSSO = "C:\\Users\\Florian\\Documents\\NetBeansProjects\\LeRegine\\src\\leregine\\ILLUMINA_ROSSO.png";

    private ArrayList<Double> attacchiX, attacchiY;

    final double LARGHEZZA_PX = .035;
    final double ALTEZZA_PX = .05;

    public Scacchiera(int nCaselle, int[][] coordinateBianche, int[][] coordinateNere) {
        this.nCaselle = nCaselle;
        this.coordinateBianche = coordinateBianche;
        this.coordinateNere = coordinateNere;

        this.attacchiX = new ArrayList<>();
        this.attacchiY = new ArrayList<>();
    }

    public void stampa() {
        System.out.println(Arrays.deepToString(this.coordinateBianche));

        GraficaSemplice.setFinestra(500, 500, "Scacchi-REGINE Sabani4IC");
        GraficaSemplice.pulisci(Color.lightGray);
        GraficaSemplice.setFont(new Font("C:\\Users\\Florian\\Documents\\NetBeansProjects\\LeRegine\\src\\leregine\\fontFigo.ttf", Font.TYPE1_FONT, 15));

        double bordo = .1;

        double latoQuadrante = (1 - (bordo * 2)) / this.nCaselle;

        GraficaSemplice.quadratoPieno(0 + bordo + (latoQuadrante / 2) * this.nCaselle, 0 + bordo + (latoQuadrante / 2) * this.nCaselle, latoQuadrante * this.nCaselle, Color.orange);

        double y = 0 + bordo + latoQuadrante / 2;
        double x = 0 + bordo + latoQuadrante / 2;

        for (int i = 0; i < this.nCaselle + 1; i++, y += latoQuadrante) {
            for (int j = 0; j < this.nCaselle; j++, x += latoQuadrante) {
                disegnaQuadrante(x, y, latoQuadrante, i, j);
            }
            x = 0 + bordo + latoQuadrante / 2;
        }
        this.segnalaInRosso();
    }

    private void disegnaQuadrante(double x, double y, double latoQuadrante, int riga, int colonna) {

        if (riga < this.nCaselle) {
            GraficaSemplice.quadrato(x, y, latoQuadrante);
        }

        // Se ci troviamo su una regina nera
        for (int i = 0; i < this.coordinateNere.length; i++) {
            if (riga == this.coordinateNere[i][0] && colonna == this.coordinateNere[i][1]) {
                disegnaRegina(x - latoQuadrante, y - latoQuadrante, this.PNG_REGINA_NERA, latoQuadrante, riga, colonna);
            }
        }

        // Se ci troviamo su una regina bianca
        for (int i = 0; i < this.coordinateBianche.length; i++) {
            if (riga == this.coordinateBianche[i][0] && colonna == this.coordinateBianche[i][1]) {
                disegnaRegina(x - latoQuadrante, y - latoQuadrante, this.PNG_REGINA_BIANCA, latoQuadrante, riga, colonna);
            }
        }

    }

    private void disegnaRegina(double x1, double y1, String coloreRegina, double latoQuadrante, int riga, int colonna) {
        if (coloreRegina.contains("bianca")) {
            GraficaSemplice.quadratoPieno(x1, y1, latoQuadrante, Color.green);
        } else if (coloreRegina.contains("nera")) {
            GraficaSemplice.quadratoPieno(x1, y1, latoQuadrante, Color.lightGray);
        }

        GraficaSemplice.quadrato(x1, y1, latoQuadrante);

        GraficaSemplice.disegnaImmagineRidimensionata(x1, y1, coloreRegina, this.LARGHEZZA_PX, this.ALTEZZA_PX, this.ALTEZZA_PX);

        Color coloreDelTesto = Color.DARK_GRAY;

        // Sono a sinistra del centro
        if (colonna <= this.nCaselle / 2) {
            if (riga <= this.nCaselle / 2) {
                GraficaSemplice.testo(x1 - latoQuadrante / 4, y1 - latoQuadrante / 4, "(" + riga + ";" + colonna + ")", y1, coloreDelTesto);
            } else {
                GraficaSemplice.testo(x1 - latoQuadrante / 4, y1 + latoQuadrante / 4, "(" + riga + ";" + colonna + ")", y1, coloreDelTesto);
            }

        } //Sono a destra del centro
        else {
            if (riga <= this.nCaselle / 2) {
                GraficaSemplice.testo(x1 + latoQuadrante / 4, y1 - latoQuadrante / 4, "(" + riga + ";" + colonna + ")", y1, coloreDelTesto);
            } else {
                GraficaSemplice.testo(x1 + latoQuadrante / 4, y1 + latoQuadrante / 4, "(" + riga + ";" + colonna + ")", y1, coloreDelTesto);
            }
        }

        // Verso sinistra
        // <---
        double x_blu = x1 - latoQuadrante, y_blu = y1;

        int rigaDiControllo = riga, colonnaDiControllo = colonna;
        
        for (int i = this.nCaselle; i > this.nCaselle - colonna + 1; i--, x_blu -= latoQuadrante) {
            colonnaDiControllo--;
            if (controlloAttacco(rigaDiControllo, colonnaDiControllo, coloreRegina, x_blu, y_blu)) {
                break;
            }
            GraficaSemplice.disegnaImmagineRidimensionata(x_blu, y_blu, this.ILLUMINA_BLU, this.LARGHEZZA_PX, this.ALTEZZA_PX, this.ALTEZZA_PX);
        }

        x_blu = x1 + latoQuadrante;
        rigaDiControllo = riga;
        colonnaDiControllo = colonna;

        //-->
        // Verso destra
        for (int i = colonna; i < this.nCaselle; i++, x_blu += latoQuadrante) {
            colonnaDiControllo++;
            if (controlloAttacco(rigaDiControllo, colonnaDiControllo, coloreRegina, x_blu, y_blu)) {
                break;
            }
            GraficaSemplice.disegnaImmagineRidimensionata(x_blu, y_blu, this.ILLUMINA_BLU, this.LARGHEZZA_PX, this.ALTEZZA_PX, this.ALTEZZA_PX);
        }

        x_blu = x1 + latoQuadrante;
        y_blu = y1 + latoQuadrante;
        rigaDiControllo = riga;
        colonnaDiControllo = colonna;

        // Verso su
        for (int i = this.nCaselle; i > riga; i--, y_blu += latoQuadrante) {
            rigaDiControllo++;
            if (controlloAttacco(rigaDiControllo, colonnaDiControllo - 1, coloreRegina, x_blu, y_blu)) {
                break;
            }
            GraficaSemplice.disegnaImmagineRidimensionata(x_blu - latoQuadrante, y_blu, this.ILLUMINA_BLU, this.LARGHEZZA_PX, this.ALTEZZA_PX, this.ALTEZZA_PX);
        }

        x_blu = x1 + latoQuadrante;
        y_blu = y1;
        rigaDiControllo = riga;
        colonnaDiControllo = colonna;

        //Verso giu
        for (int i = riga; i > 0 + 1; i--, y_blu -= latoQuadrante) {
            rigaDiControllo--;
            if (controlloAttacco(rigaDiControllo, colonnaDiControllo, coloreRegina, x_blu - latoQuadrante, y_blu - latoQuadrante)) {
                break;
            }
            GraficaSemplice.disegnaImmagineRidimensionata(x_blu - latoQuadrante, y_blu - latoQuadrante, this.ILLUMINA_BLU, this.LARGHEZZA_PX, this.ALTEZZA_PX, this.ALTEZZA_PX);
        }
       

        x_blu = x1 + latoQuadrante*2;
        y_blu = y1 + latoQuadrante;
        rigaDiControllo = riga;
        colonnaDiControllo = colonna;

        // Verso in alto a destra
        for (int i = this.nCaselle,j=this.nCaselle; (i > riga) && (j>colonna) ; i--,j--, y_blu += latoQuadrante,x_blu+=latoQuadrante) {
            rigaDiControllo++;
            colonnaDiControllo++;
            if (controlloAttacco(rigaDiControllo , colonnaDiControllo, coloreRegina, x_blu -latoQuadrante , y_blu)) {
                break;
            }
            GraficaSemplice.disegnaImmagineRidimensionata(x_blu - latoQuadrante, y_blu, this.ILLUMINA_BLU, this.LARGHEZZA_PX, this.ALTEZZA_PX, this.ALTEZZA_PX);
        }
         
        x_blu = x1;
        y_blu = y1 + latoQuadrante;
        rigaDiControllo = riga;
        colonnaDiControllo = colonna;
/*
        // Verso in alto a sinistra
        for (int i = riga,j= colonna; i>=0 && j < this.nCaselle ;i--,j++, y_blu += latoQuadrante, x_blu -= latoQuadrante) {
            rigaDiControllo--;
            colonnaDiControllo++;
            
            if (controlloAttacco(rigaDiControllo, colonnaDiControllo-1, coloreRegina, x_blu - latoQuadrante, y_blu)) {
                break;
            }
            
            GraficaSemplice.disegnaImmagineRidimensionata(x_blu - latoQuadrante, y_blu, this.ILLUMINA_BLU, this.LARGHEZZA_PX, this.ALTEZZA_PX, this.ALTEZZA_PX);
        }
*/

    }

    private boolean controlloAttacco(int rigaDiControllo, int colonnaDiControllo, String coloreRegina, double x_blu, double y_blu) {

        //Controllo Nere
        for (int i = 0; i < this.coordinateNere.length; i++) {
            if (rigaDiControllo == this.coordinateNere[i][0] && colonnaDiControllo == this.coordinateNere[i][1]) {
                // Se sono una regina bianca andiamo contro una nera quindi addo la pos x e y in quelle da fare rosse
                if (coloreRegina.contains("bianca")) {
                    this.attacchiX.add(x_blu);
                    this.attacchiY.add(y_blu);
                }
                //altrimenti ce un altra regina bianca...
                return true;
            }
        }

        //Controllo Bianche
        for (int i = 0; i < this.coordinateBianche.length; i++) {
            if (rigaDiControllo == this.coordinateBianche[i][0] && colonnaDiControllo == this.coordinateBianche[i][1]) {
                // Se sono una regina bianca andiamo contro una nera quindi addo la pos x e y in quelle da fare rosse
                if (coloreRegina.contains("nera")) {
                    this.attacchiX.add(x_blu);
                    this.attacchiY.add(y_blu);
                }
                //altrimenti ce un altra regina bianca...
                return true;
            }
        }
        return false;
    }

    private void segnalaInRosso() {
        for (int i = 0; i < this.attacchiX.size(); i++) {
            GraficaSemplice.disegnaImmagineRidimensionata(this.attacchiX.get(i), this.attacchiY.get(i), this.ILLUMINA_ROSSO, this.LARGHEZZA_PX, this.LARGHEZZA_PX * 2, this.LARGHEZZA_PX * 2);
        }
    }

}
