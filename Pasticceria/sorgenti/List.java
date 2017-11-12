package pasticceria;

/**
 * La classe incapsula una lista e fornisce i metodi necessari all'accesso e alla gestione dei suoi elementi. La classe è generica, perciò permette sia la gestione di una coda FiFO e LIFO, la cui implementazione è lasciata alle sottoclassi.
 */
public class List {
    /**
     * Il primo elemento della lista. null se la lista è vuota.
     */
    protected ListItem head;

    /**
     * L'ultimo elemento della lista, null se è vuota.
     */
    protected ListItem tail;


    /**
     * Inizializza la lista come vuota.
     */
    protected void List() {
        head = null;
        tail = null;
    }

    /**
     * Aggiunge un elemento in coda alla lista.
     * @param el Il valore da incapsulare nell'elemento.
     */
    protected void addTail(Torta el) {
        if (tail == null)
        {
            head = tail = new ListItem (el);
        }
        else
        {
            tail.setNext(new ListItem(el));
            tail.getNext().setPrev(tail);
            tail = tail.getNext();
        }
    }

    /**
     * Aggiunge un elemento in testa alla lista.
     * @param el Il valore da incapsulare nell'elemento.
     */
    protected void addHead(Torta el) {
        if (head == null)
        {
            head = tail = new ListItem (el);
        }
        else
        {
            head.setPrev(new ListItem(el));
            head.getPrev().setNext(head);
            head = head.getPrev();
        }
    }

    /**
     * Rimuove un elemento in coda alla lista e ne ritorna il lavore contenuto.
     * @return Il valore contenuto nella coda.
     */
    protected Torta removeTail() throws ListEmptyException{
        if (head == null) throw new ListEmptyException();
        
        ListItem res = tail;
        tail = tail.getPrev();
        if (tail != null) tail.setNext(null);
        else head = null;            
        res.setPrev(null);
                
        return res.getData();
    }

    /**
     * Rimuove il primo elemento della lista.
     * @return Il valore contenuto nel primo elemento.
     */
    protected Torta removeHead() throws ListEmptyException{
        if (head == null) throw new ListEmptyException();
        
        ListItem res = head;
        head = head.getNext();
        if (head != null) head.setPrev(null);
        else tail = null;
        res.setNext(null);
                
        return res.getData();
    }

    /**
     * Rimuove tutti gli elementi della lista.
     * @return Il numero di elementi rimossi; 0 se la lista è vuota.
     */
    protected int removeAll() {
        int res = 0;
        
        try
        {
            while(true)
            {
                removeHead();
                res++;
            }
        }
        catch(ListEmptyException lee)
        {            
        }
        
        return res;
    }

    /**
     * Conta gli elementi della lista e ne ritorna il valore.
     * @return Il numero degli elementi della lista.
     */
    public int count() {
        int res = 0;
        
        ListItem tmp = head;
        while(tmp != null)
        {
            res++;
            tmp = tmp.getNext();
        }
        
        return res;
    }

    /**
     * Ritorna se la lista è vuota o no.
     * @return true se la lista è vuota. false altrimenti.
     */
    public boolean empty() {
        return (head == null);
    }

    /**
     * Ritorna la rappresentazione degli elementi della lista.
     * @return L'elenco dei valori contenuti negli elementi della lista dal primo all'ultmo nella forna el1, el2, ... eln
     */
    public String toString() {
        String res = "";
        
        ListItem tmp = head;
        while (tmp != null)
        {
            res += tmp.getData() + ",\n";
            tmp = tmp.getNext();
        }
        
        if (!"".equals(res)) return res.substring(0, res.length() - 2);
        else return "";
    }

    /**
     * Ritorna una rappresentazione degli elementi della lista.
     * @return L'elenco dei valori contenuti negli elementi della lista all'ultmo al primo nella forna eln, ...,  el2,  el1
     */
    public String toReverseString() {
        String res = "";
        
        ListItem tmp = head;
        while (tmp != tail)
        {
            tmp = tmp.getNext();
        }
        
        while (tmp != null)
        {
            res += tmp.getData() + ",\n";
            tmp = tmp.getPrev();
        }
        
        if (!"".equals(res)) return res.substring(0, res.length() - 2);
        else return "";
    }

}