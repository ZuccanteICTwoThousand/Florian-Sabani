package coda;

class Nodo<E> {//classe nodo generico della lista

    private E elemento;
    private Nodo prossimo;

    public Nodo(E element) {
        this(element, null);
    }

    public Nodo(E element, Nodo next) {
        this.elemento = element;
        this.prossimo = next;
    }

    public void setNext(Nodo next) {
        this.prossimo = next;
    }

    public E getElement() {
        return elemento;
    }

    public Nodo getNext() {
        return prossimo;
    }

    public String toString() {
        return (String) elemento.toString();
    }
}

class Lista<E> {

    private Nodo testa, coda;//nodi sentinella una per la testa e l'altro per la coda
    protected int lunghezza;

    public Lista() {//costruttore
        testa = coda=  null;
        lunghezza = 0;
    }

    public void aggiungiAllaCoda(E element) {// aggiungo in coda
        Nodo node = new Nodo(element);
        if (coda == null) {
            testa = coda = node;
        } else {
            coda.setNext(node);
            coda = node;
        }
        lunghezza++;
    }

    public E rimuoviDallAlto() {
        E element = null;
        if (lunghezza == 0) {
            System.out.println("errore coda vuota"); //stampo errore;
        } else {
            element = (E) testa.getElement();
            testa = testa.getNext();
            lunghezza--;
            return element;
        }
        return element;
    }

    public E getPrimo() {
        return (E) this.testa.getElement();
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        if (lunghezza != 0) {
            Nodo tmp = testa;
            while (tmp != null) {
                str.append(tmp.toString());
                tmp = tmp.getNext();
            }
        }
        return str.toString();
    }
}

public class Code<E> {

    private Lista<E> lista;

    public Code() {
        lista = new Lista<>();
    }

    public int getSize() {
        return lista.lunghezza;
    }

    public void enqueue(E element) {
        lista.aggiungiAllaCoda(element);
    }

    public E dequeue() {
        return lista.rimuoviDallAlto();
    }

    public E front() {
        return lista.getPrimo();
    }
    
    public String toString() {
        return lista.toString();
    }
}
