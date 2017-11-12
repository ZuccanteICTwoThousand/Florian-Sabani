package letturascritturafile;

import java.io.FileNotFoundException;
import java.util.Iterator;

/**
 *
 * @author Florian
 */
public class LetturaScritturaFile {
    public static void main(String[] args) throws FileNotFoundException, NomeNonValidoException {
        
        Utente gino = new Utente("gino","ciao123");
        Utente alessio = new Utente("alessio");
        Utente marta = new Utente("marta","yolo13");
        gino.unSubscribe();
        marta.changePasswd("yolo13", "ehSi");
        //Utente stupido = new Utente("","ciao");
        Utente giorgio = new Utente("giorgio","meme");
        
        giorgio.creaGruppo("Gruppo Di Giorgio");
        giorgio.getGruppo("Gruppo Di Giorgio").aggiungiUtente(gino);
        giorgio.getGruppo("Gruppo Di Giorgio").aggiungiUtente(alessio);
        giorgio.getGruppo("Gruppo Di Giorgio").aggiungiUtente(new Utente("Stevanato","hs123"));
        giorgio.getGruppo("Gruppo Di Giorgio").aggiungiUtente(new Utente("Marco","noob"));
        giorgio.getGruppo("Gruppo Di Giorgio").aggiungiUtente(new Utente("Xilo","GymSharkFucker"));
        giorgio.getGruppo("Gruppo Di Giorgio").aggiungiUtente(new Utente("Igor","checazzoeigor"));
 
        
        alessio.creaGruppo("Gruppo di Alessio");
        alessio.getGruppo("Gruppo di Alessio").aggiungiUtente(gino);
        alessio.getGruppo("Gruppo di Alessio").aggiungiUtente(giorgio);
        
        alessio.creaGruppo("Gruppo Secondo");
        alessio.getGruppo("Gruppo Secondo").aggiungiUtente(marta);
        
        alessio.getGruppo("Gruppo Secondo").promuovi(marta);
        alessio.getGruppo("Gruppo Secondo").promuovi(marta);
        alessio.getGruppo("Gruppo di Alessio").declassa(giorgio);
        
        marta.getGruppo("Gruppo Secondo").push("commit 1");
        
        giorgio.getGruppo("Gruppo Di Giorgio").espelli(gino);
        
        
    }
    
}
