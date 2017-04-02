package com.flo.scacchi.tabella;

import com.flo.scacchi.ControlliScacchi;
import com.flo.scacchi.Pezzo;
import com.google.common.collect.ImmutableMap;
import java.util.HashMap;
import java.util.Map;
import guiDopo.Main;


// Voglio farla immutabile quindi in alcun modo potro andare a modificare lo stato delle mie classi
public abstract class Posto { 
    /*
    // Essendo di tipo abstact non possiamo creare un nuovo posto non puo essere istanziata
    // Ma possiamo istanziare delle subclasses;
    */
    protected final int coordinata; // protected cosicche anche le sottoclassi possano accederci e final cosicche nn venga modificata
    static Main m = new Main();

    private static final Map<Integer,PostoVuoto> POSTO_VUOTO = possibiliPostiVuoti();
    
    private Posto(int coordinata) {
        this.coordinata = coordinata;
    }

    public abstract boolean isPostoOccupato();

    public abstract Pezzo getPezzo();    //i metodi abstact verranno dichiarati dopo in una sottoclasse

    private static  Map<Integer, PostoVuoto> possibiliPostiVuoti() {
        final Map<Integer,PostoVuoto> mappa = new HashMap<>();  //Le Mappe sono come le matrici solo che le righe non si contano vuota
        final int numeroCaselle = ControlliScacchi.numeroDiCaselle;   // numero di caselle
        // Riempio la mappa con tutte caselle inizialmente vuote
        for (int i = 0; i < numeroCaselle; i++) {
            mappa.put(i,new PostoVuoto(i));
        }
        /* Metodo di guava che e una libreria che forza cio che returniamo in immutabile 
        (in ogni caso si potrebbe returnare mappa solo che sarebbe rischioso per eventuali bugs)
        */
        /* Se non si vuole importare guava ( che e fatto da google e bello )
        si puo usare la versione brutta del jdk
        Collections.unmodifiableMap(mappa);
        io uso guava ez
        */
        return ImmutableMap.copyOf(mappa);
    }
    
    public static Posto creaPosto(final int posto,final Pezzo pezzo){
        //.
        return pezzo != null ? new PostoOccupato(posto,pezzo): /*new PostoVuoto(posto);*/ POSTO_VUOTO.get(posto);
    }

    public static final class PostoVuoto extends Posto {
        //costruttore 

        PostoVuoto(final int coordinata) { 
            super(coordinata); // il costruttore va a richiamare la classe super quindi quella sopra in cui e
        }

        @Override
        public boolean isPostoOccupato() {
            return false;
        }

        @Override
        public Pezzo getPezzo() {
            return null;
        }
    }

    public static final class PostoOccupato extends Posto {

        private final Pezzo pezzo;  //immutabile

        PostoOccupato(final int coordinata, Pezzo pezzo) {
            super(coordinata);
            this.pezzo = pezzo;
        }

        @Override
        public boolean isPostoOccupato() {
            return true;
        }
        @Override
        public Pezzo getPezzo() {
            return this.pezzo;
        }

    }

}
