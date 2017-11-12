package bst;

import static java.util.Arrays.sort;

/**
 *
 * @author Florian
 * @param <T> il tipo t dev essere confrontabile con un altro oggetto dello stesso tipo
 */
public class BST<T extends Comparable<T>> {

    private Nodo<T> radice;

    public BST() {
        this.radice = null;
    }

    public BST(T o) {
        this.radice = new Nodo<>(o);
    }
    
    public BST(T[] array){
        sort(array);
        radice = new Nodo(array,0,array.length);
    }

    public void add(T o) {
        if(radice == null)radice = new Nodo(o);
        else radice.inserisci(o);
        
    }
    
    public void remove(T o){
        // da implementare...
    }

    public boolean esiste(T valore){
        return esiste(radice,valore);
    }
    
    private boolean esiste(Nodo<T> node, T o) {
        if (node == null) {
            return false;
        }
        if (node.informazione.compareTo(o) == 0) {
            return true;
        } else if (node.informazione.compareTo(o) < 0) {
            esiste(node.destra, o);
        } else {
            esiste(node.sinistra, o);
        }
        return false;
    }

    private void preVisita(Nodo<T> node) {
        if (node != null) {
            node.elabora();
            BST.this.preVisita(node.sinistra);
            BST.this.preVisita(node.destra);
        }
    }

    public void crescente(Nodo<T> node) {
        if (node != null) {
            crescente(node.sinistra);
            node.elabora();
            crescente(node.destra);
        }
    }

    public void decrescente(Nodo<T> node) {
        if (node != null) {
            decrescente(node.sinistra);
            decrescente(node.destra);
            node.elabora();
        }
    }

    public void preVisita() {
        if (radice != null) {
            BST.this.preVisita(radice);
        }
    }

    public void simmetricaCrescente() {
        if (radice != null) {
            crescente(radice);
        }
    }

    public void simmetricaDecrescente() {
        if (radice != null) {
            decrescente(radice);
        }
    }

    class Nodo<T extends Comparable<T>> {

        T informazione;
        Nodo<T> sinistra;
        Nodo<T> destra;

        public Nodo(T o) {
            this.informazione = o;
            this.sinistra = null;
            this.destra = null;
        }

        public Nodo() {
            this.informazione = null;
            this.sinistra = null;
            this.destra = null;
        }

        private Nodo(T[] array, int inizio, int fine) {
            int pivot = (inizio+fine)/2;
            this.informazione = array[pivot];
            
            if(inizio<pivot){
                sinistra = new Nodo(array,inizio,pivot);
            }
            if(pivot+1<fine){
                destra = new Nodo(array,pivot+1,fine);
            }
        }

        public void inserisci(T o) {
            if (informazione.compareTo(o) < 0) {
                if (destra == null) {
                    destra = new Nodo(o);
                } else {
                    destra.inserisci(o);
                }
            } else if(informazione.compareTo(o) > 0) {
                if (sinistra == null) {
                    sinistra = new Nodo<>(o);
                } else {
                    sinistra.inserisci(o);
                }
            }
        }

        public void elabora() {
            System.out.print(this.informazione + " ");
        }
    }

}
