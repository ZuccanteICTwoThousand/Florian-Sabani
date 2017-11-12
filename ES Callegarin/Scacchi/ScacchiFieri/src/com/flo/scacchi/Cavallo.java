package com.flo.scacchi;

import com.flo.scacchi.alleanza.Squadra;
import com.flo.scacchi.tabella.Movimenti;
import com.flo.scacchi.tabella.Movimenti.*; // I varii tipi di movimenti
import com.flo.scacchi.tabella.Tabella;
import com.flo.scacchi.tabella.Posto;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Collection;
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
    public Collection<Movimenti> movimentiFattibili(final Tabella tabella) {
        // Movimenti a L
        //int movimentoCoordinata;
        final List<Movimenti> movimenti = new ArrayList<>();

        // Consideriamo tutte i movimenti (l'array) analizzandoli tutti in un for ( anche for each potrebbe andare)
        for (int i = 0; i < possibiliMovimenti.length; i++) {
            final int movimentoCoordinata = this.posizionePezzo + possibiliMovimenti[i];    // redichiarandolo ogni volta posso metterlo final :D
            // ( e faro che sei dentro il bordo xD)
            if (ControlliScacchi.coordinataPostiValida(movimentoCoordinata)/* Se stai dentro i bordi */) {
                if (isPrimaColonna(this.posizionePezzo, this.possibiliMovimenti[i])
                        || isPrimaColonna(this.posizionePezzo, this.possibiliMovimenti[i])
                        || isSecondaColonna(this.posizionePezzo, this.possibiliMovimenti[i])
                        || isPenultimaColonna(this.posizionePezzo, this.possibiliMovimenti[i])
                        || isUltimaColonna(this.posizionePezzo, this.possibiliMovimenti[i])) {
                    /*Fine controllo bordi*/
                    continue;
                }

                final Posto casella = tabella.getPosto(movimentoCoordinata);
                // Se dove vogliamo andare non e occupato
                if (!casella.isPostoOccupato()) {
                    movimenti.add(new MovimentoGrande(tabella ,this, movimentoCoordinata)); // Lo occupiamo noi a cazzo dritto
                } else // Se invece é occupato cerchiamo di capire se ce un nemico o un alleato 
                {
                    Pezzo destinazionePezzo = casella.getPezzo();
                    Squadra pezzoSquadra = destinazionePezzo.getSquadra();  // prendiamo linfo del pezzo che occupa la casella chiedendoci la squadra se nera o bianca
                    // Se la squadra del nostro pezzo é diversa da quella del pezzo che occupa la casella in cui vorremmo andare
                    // allora pettini
                    if (this.squadraPezzo != pezzoSquadra) {
                        // Siamo su un personaggio della squadra avversaria.
                        movimenti.add(new MovimentoMangia(tabella,this,movimentoCoordinata,destinazionePezzo)); // movimento per ora lasciamolo cosi
                    }
                    // ancora roba da fare qui.
                }
            }
        }
        // guava ancora una volta ci salva il culo
        return ImmutableList.copyOf(movimenti);
    }

    /*
    Adesso mettero una serie di metodi che controlleranno varii casi di posizioni :
    Ossia per esempio il cavallo potrebbe essere nella prima colonna , quindi in alto:
     */
    private static boolean isPrimaColonna(final int posizioneAttuale, final int posizioneRichiesta) {

        return ControlliScacchi.PRIMA_COLONNA[posizioneAttuale]
                && ((posizioneRichiesta == -17) || (posizioneRichiesta == -10) || (posizioneRichiesta == 6) || (posizioneRichiesta == 15));

    }

    private static boolean isSecondaColonna(final int posizioneAttuale, final int posizioneRichiesta) {

        return ControlliScacchi.SECONDA_COLONNA[posizioneAttuale]
                && ((posizioneRichiesta == 10) || (posizioneRichiesta == 6));

    }

    private static boolean isPenultimaColonna(final int posizioneAttuale, final int posizioneRichiesta) {
        return ControlliScacchi.SETTIMA_COLONNA[posizioneAttuale]
                && ((posizioneRichiesta == -6) || (posizioneRichiesta == 10));
    }

    private static boolean isUltimaColonna(final int posizioneAttuale, final int posizioneRichiesta) {
        return ControlliScacchi.OTTAVA_COLONNA[posizioneAttuale]
                && ((posizioneRichiesta == -15) || (posizioneRichiesta == -6) || (posizioneRichiesta == 10) || (posizioneRichiesta == 17));
    }
}
