package pasticceria;

/**
 * La classe descrive l'elemento appartenente ad una lista. Le informazioni da conservare sono incapsulate all'interno (campo data). Sono poi presenti le informazioni per la gestione della lista.
 */
public class ListItem {
    /**
     * Contiene le informazioni da conservare nella lista.
     */
    private Torta data;

    /**
     * Punta all'elemento successivo della lista. Se l'elemento è l'ultimo della lista assume il valore null.
     */
    private ListItem next;

    /**
     * Punta all'elemento precedente nella lista. Se l'elemento è il primo della lista assume il valore null.
     */
    private ListItem prev;



    /**
     * Inizializza l'elemento  della lista impostandone il valore (campo data) e impostando a null i campi net e prev. Di fatto inizializza un elemento isolato non appartenente a nessuna lista.
     * @param d Il valore da far contenere all'elemento.
     */
    public ListItem(Torta d) {
        data = d;
        next = null;
        prev = null;
    }

    /**
     * Ritorna l'elemento successivo nella lista.
     * @return L'elemento successivo o null se non ci sono successori.
     */
    public ListItem getNext() {
        return next;
    }

    /**
     * Imposta l'elelemtno successivo.
     * @param n L'elemento  successivo. Se il valore è null si rende l'elemento l'ultimo della lista.
     */
    public void setNext(ListItem n) {
        next = n;
    }

    /**
     * Ritorna l'elemento precedente nella lista.
     * @return L'elemento precedente, null se è il primo elemento della lista.
     */
    public ListItem getPrev() {
        return prev;
    }

    /**
     * Imposta il predecessore nella lista.
     * @param p
     */
    public void setPrev(ListItem p) {
        prev = p;
    }

    /**
     * Ritorna il valore incapsulato nella lista.
     * @return Il valore incapsulato nella lista.
     */
    public Torta getData() {
        return data;
    }

    /**
     * Ritorna la stringa che rappresenta il ticket.,
     * @return
     */
    @Override
    public String toString() {
        return data.toString();
    }

}