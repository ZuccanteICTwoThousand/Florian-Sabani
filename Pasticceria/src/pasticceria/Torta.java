

package pasticceria;
import java.math.*;
/**
 * Descrive da un punto di vista software una torta e il tenpo necesario a produrla.
 */
public class Torta {
    /**
     * Il tempo di produzione della torta. Quando il valore Ã¨ 0 la torta Ã¨ pronta.
     */
    private int time;

    /**
     * Il nome della torta.
     */
    private String nome;


    /**
     * Inizializza la torta
     * @param nom Il nome della torta.
     * @param t il tempo di produzione della torta.
     */
    public Torta(String nom, int t) {
        nome = nom;
        time = t;
    }

    /**
     * Ritorna il nome della torta
     * @return il nome della torta
     */
    public String getNome() {
        return nome;
    }

    /**
     * Ritorna il tempo di produzione della torta.
     * @return Il tempo di produzione.
     */
    public int getTime() {
        return time;
    }

    /**
     * Toglie dal tempo di produzione della torta un valore casuale tra 0 e 10.
     */
    public void update() {
       int rand = (int) (Math.random()*10);
       time-=rand;
    }

    /**
     * Ritorna una stringa che mostra i dati dell'oggetto Torta.
     * @return Una stringa nel formato "Cognome  Nome - Codice"
     */
    public String toString() {
        return nome + " - " + time;
    }

}