package com.flo.scacchi.tabella;

import com.flo.scacchi.Pezzo;

/**
 *
 * @author florian
 */
public abstract class Movimenti {

    final Tabella tabella;
    final Pezzo pezzo;
    final int destinazioneCoordinateDelPezzo;
    /*
    Allora ogni movimento avra bisogno di sapere chi si sta muovendo , dove e la distribuzio
    della tabelle com'e messa , bisogna sapere le altre pedine come sono disposte
    */
    Movimenti(final Tabella tabella, final Pezzo pezzo, final int destinazioneCoordinateDelPezzo) {
        this.tabella = tabella;
        this.pezzo = pezzo;
        this.destinazioneCoordinateDelPezzo = destinazioneCoordinateDelPezzo;
    }

    // Il piu grande movimento fattibile 
    public static final class MovimentoGrande extends Movimenti {

        public MovimentoGrande(final Tabella tabella, final Pezzo pezzo, final int destinazioneCoordinateDelPezzo) {
            super(tabella, pezzo, destinazioneCoordinateDelPezzo);
        }

    }
    // Il movimento che andra a mangiare qualcosa
    public static final class MovimentoMangia extends Movimenti {
        //Il pezzo che verra mangiato
        final Pezzo pezzoMorto;
        
        public MovimentoMangia(final Tabella tabella, final Pezzo pezzo,
                final int destinazioneCoordinateDelPezzo,final Pezzo pezzoAttaccato) {
            super(tabella, pezzo, destinazioneCoordinateDelPezzo);
            this.pezzoMorto = pezzoAttaccato;   // Pezzo che morira
        }

    }

}
