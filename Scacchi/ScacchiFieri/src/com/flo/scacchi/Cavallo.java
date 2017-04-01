package com.flo.scacchi;

import com.flo.scacchi.alleanza.Squadra;
import com.flo.scacchi.tabella.Movimenti;
import com.flo.scacchi.tabella.Tabella;
import com.flo.scacchi.tabella.Posto;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author florian
 */
public class Cavallo extends Pezzo {

    /* 
    Dati trovati su wikipedia.en e cmq si possono calcolare con un pezzo di carta e spostandosi nella Map 
    creata prima
     */
    private int[] possibiliMovimenti = {-17, -15, -10, -6, 6, 10, 15, 17};

    public Cavallo(final int posizione, final Squadra squadraPezzo) {
        super(posizione, squadraPezzo);
    }

    @Override
    public List<Movimenti> movimentiFattibili(Tabella tabella) {
        // Movimenti a L
        int movimentoCoordinata;
        final List<Movimenti> movimenti = new ArrayList<>();

        // Consideriamo tutte i movimenti (l'array) analizzandoli tutti in un for ( anche for each potrebbe andare)
        for (int i = 0; i < possibiliMovimenti.length; i++) {
            movimentoCoordinata = this.posizionePezzo + possibiliMovimenti[i];
            // ( e faro che sei dentro il bordo xD)
            if (true/* Se stai dentro i bordi */) {
                final Posto casella = tabella.getPosto(movimentoCoordinata);
                // Se dove vogliamo andare non e occupato
                if (!casella.isPostoOccupato()) {
                    movimenti.add(new Movimenti()); // Lo occupiamo noi a cazzo dritto
                } else // Se invece é occupato cerchiamo di capire se ce un nemico o un alleato 
                {
                    Pezzo DestinazionePezzo = casella.getPezzo();
                    Squadra pezzoSquadra = DestinazionePezzo.getSquadra();  // prendiamo linfo del pezzo che occupa la casella chiedendoci la squadra se nera o bianca
                    // Se la squadra del nostro pezzo é diversa da quella del pezzo che occupa la casella in cui vorremmo andare
                    // allora pettini
                    if (this.squadraPezzo != pezzoSquadra) {
                        // Siamo su un personaggio della squadra avversaria.
                        movimenti.add(new Movimenti()); // movimento per ora lasciamolo cosi
                    }
                    // ancora roba da fare qui.
                }
            }
        }
        // guava ancora una volta ci salva il culo
        return ImmutableList.copyOf(movimenti);
    }

}
