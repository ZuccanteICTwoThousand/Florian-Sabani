package giocobottoni;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author Florian
 */
public class Bottoni implements ActionListener {

    final int TEMPO_MAX = 10000; //ms

    private Frame f;
    private Panel p;

    final int _lato = 400;

    Button[][] matrice;

    final Color base = Color.white;
    final Color prendi = Color.green;

    private int ILL_riga, ILL_colonna;

    private int punti = 0;
    private Integer ritardo;

    final Random r;
    final Tempo t;

    int[] baitsR, baitsC;

    Bottoni() {
        this(10, 10);
        this.ritardo = new Integer(2000);
    }

    Bottoni(int nBlocchi) {
        this((int) Math.sqrt(nBlocchi), (int) Math.sqrt(nBlocchi));
    }

    Bottoni(int righe, int colonne) {
        this.ritardo = TEMPO_MAX;
        r = new Random();
        t = new Tempo(ritardo, this);
        baitsR = baitsC = new int[0];

        f = new Frame("Gioco Delle Talpe");
        f.setBounds(1600 / 2, 900 / 2, _lato, _lato);
        f.setBackground(Color.gray);
        f.setResizable(false);

        chiusura();

        matrice = new Button[righe][colonne];    // false di default

        p = new Panel(new GridLayout(righe, colonne));

        bottoni(righe, colonne);

        f.add(p);

        f.setVisible(true);

        // Fai partire un thread che velocizzi il gioco
        aggiorna();
        new Thread(t).start();
    }

    private Button bottone() {
        Button b = new Button();
        b.setBackground(base);
        b.addActionListener(this);

        return b;
    }

    private void bottoni(int righe, int colonne) {
        for (int i = 0; i < righe; i++) {
            for (int j = 0; j < colonne; j++) {
                Button add = bottone();
                p.add(add);
                matrice[i][j] = add;
            }
        }
    }

    private void chiusura() {
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e == null) {    // TEMPO FINITO 
            this.chiudi();
        } else if (e.getSource() == matrice[ILL_riga][ILL_colonna]) {   // CLICCATO
            punti++;
            // t.puntatore = this.ritardo;// resetta timer
            this.aggiorna();
        }
    }

    private void aggiorna() {

        togliBaits();
        colora(base);

        System.out.println("PUNTEGGIO : " + punti);

        int rigaCaso = r.nextInt(matrice.length);
        int colonnaCaso = r.nextInt(matrice[ILL_riga].length);

        while (rigaCaso == ILL_riga && colonnaCaso == ILL_colonna) {
            rigaCaso = r.nextInt(matrice.length);
            colonnaCaso = r.nextInt(matrice[ILL_riga].length);
        }

        this.ILL_riga = rigaCaso;
        this.ILL_colonna = colonnaCaso;

        // timer , dopo un po cambia casella anche se non lhai toccata
        aggiungiBaits();
        colora(prendi);
    }

    private void colora(Color t) {
        matrice[ILL_riga][ILL_colonna].setBackground(t);
    }

    private void chiudi() {

        int diametro = _lato;

        Label finito = new Label("TEMPO FINITO p = " + punti);
        Panel pannelloFine = new Panel(null);
        finito.setBounds(((_lato / 2) - ((diametro / 2))) + diametro / 6, ((_lato / 2) - ((diametro / 2))) - diametro / 4, diametro, diametro);
        finito.setFont(new Font("Arial", 30, 30));
        pannelloFine.add(finito);
        pannelloFine.setBackground(Color.red);

        f.remove(p);
        f.add(pannelloFine);

        f.setVisible(false);
        f.setVisible(true); //aggiorna

        System.out.println("Finito");
    }

    private void togliBaits() {
        for (int i = 0; i < baitsR.length; i++) {
            matrice[baitsR[i]][baitsC[i]].setBackground(base);
        }
    }

    private void aggiungiBaits() {
        int nBaits = r.nextInt(6);

        baitsR = new int[nBaits];
        baitsC = new int[nBaits];
        
        int distanzaMax = matrice.length / 2;
        
        for (int i = 0; i < nBaits; i++) {
            int rVic = ILL_riga+r.nextInt(distanzaMax) - r.nextInt(distanzaMax);
            int cVic = ILL_colonna+r.nextInt(distanzaMax) - r.nextInt(distanzaMax);

            while ((rVic < 0 || cVic < 0 || rVic > matrice.length-2 || cVic > matrice[0].length-2)
                    || (rVic == ILL_riga && cVic == ILL_colonna)) {
                rVic = ILL_riga+r.nextInt(distanzaMax) - r.nextInt(distanzaMax);
                cVic = ILL_colonna+r.nextInt(distanzaMax) - r.nextInt(distanzaMax);
            }
            
            baitsR[i] = rVic;
            baitsC[i] = cVic;
        }
        
        for (int i = 0; i < baitsR.length; i++) {
            matrice[baitsR[i]][baitsC[i]].setBackground(new Color(0,r.nextInt(255),0));
        }
    }
}
