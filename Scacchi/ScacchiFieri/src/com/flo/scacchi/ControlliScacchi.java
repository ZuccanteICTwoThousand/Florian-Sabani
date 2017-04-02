package com.flo.scacchi;
/**
 *
 * @author florian
 */
public class ControlliScacchi {

    static boolean[] PRIMA_COLONNA = numeroColonna(0);
    static boolean[] SECONDA_COLONNA = numeroColonna(1);
    static boolean[] SETTIMA_COLONNA = numeroColonna(6);
    static boolean[] OTTAVA_COLONNA= numeroColonna(7);

    public final static int numeroDiCaselle = 64;
    public final static int numeroDiCasellePerRiga = 8;
    
    ControlliScacchi(){
        throw new RuntimeException("Problema Controllo scacchi");
    }
    
    static boolean coordinataPostiValida(int coordinata) {
        return coordinata >= 0 && coordinata <= /*Numero di caselle negli scacchi*/ numeroDiCaselle;
    }

    private static boolean[] numeroColonna(int numeroDellaColonna) {
        final boolean a[] = new boolean[numeroDiCaselle];
        do{
            a[numeroDellaColonna]=true;
            //passiamo alla riga dopo
            numeroDellaColonna+= numeroDiCasellePerRiga;
        }while(numeroDellaColonna<numeroDiCaselle);
        
        return a;
    }
}
