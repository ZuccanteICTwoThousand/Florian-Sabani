package com.flo.scacchi;

import com.flo.scacchi.alleanza.Squadra;
import com.flo.scacchi.tabella.Movimenti;
import com.flo.scacchi.tabella.Tabella;
import java.util.List;

/**
 *
 * @author florian
 */
public abstract class Pezzo {
    
    
    protected final int posizionePezzo;
    protected final Squadra squadraPezzo;   // La squadra ci dira se il nostro pezzo sara dei bianchi o dei neri.
    
    Pezzo(final int posizione,final Squadra squadraPezzo){
        this.posizionePezzo = posizione;
        this.squadraPezzo = squadraPezzo;
    }
    
    /* Ogni pezzo diverso retituira una serie di movimenti fattibili diversi
    Rappresentabili da un'arrayList.
    */
    public abstract List<Movimenti> movimentiFattibili (final Tabella tabella);
    
    public Squadra getSquadra(){
        return this.squadraPezzo;
    }
}