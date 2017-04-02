package com.flo.scacchi;
/**
 *
 * @author florian
 */
public class ControlliScacchi {

    static boolean[] PRIMA_COLONNA = null;
    static boolean[] SECONDA_COLONNA = null;
    static boolean[] SETTIMA_COLONNA = null;
    static boolean[] OTTAVA_COLONNA= null;

    ControlliScacchi(){
        throw new RuntimeException("Problema Controllo scacchi");
    }
    
    static boolean coordinataPostiValida(int coordinata) {
        return coordinata >= 0 && coordinata <= /*Numero di caselle negli scacchi*/ 64;
    }
}
