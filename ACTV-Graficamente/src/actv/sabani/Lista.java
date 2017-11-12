package actv.sabani;

import actv.sabani.Mappa.PuntoMappa;

/**
 *
 * @author ps09
 */

/**
 *
 * @author florian.sabani
 */
public class Lista<T>{

    private NodoConc testa, coda;	// Preferisco avere due nodi Testa e Coda
    protected int lunghezza = 0;	// Un intero che indica il numero di nodi pieni (non nulli)

    public Lista() {
        testa = null;
        coda = null;
    }

	/**
	 * Dato un array , riempio elemento per elemento la lista.
	 * @param array Struttura dalla quale trarre le informazioni.
	 */
    public Lista(T[] array) {
        for (int i = 0; i < array.length; i++) {
            this.addTail(array[i]);
        }
    }

	/**
	 * Aggiungo sulla testa.
	 * @param valore 
	 */
    public void addHead(T valore) {
        NodoConc daAggiungere = new NodoConc(valore,null);
        NodoConc testaVecchia = testa;
        if (lunghezza == 0) {
            coda = testa = daAggiungere;
        } else {
            testa = daAggiungere;
            testa.next = testaVecchia;
        }
        lunghezza++;
    }

	
	/**
	 * Aggiungo sulla coda
	 * @param valore 
	 */
    public void addTail(T valore) {// aggiungo in coda
        NodoConc daAggiungere = new NodoConc(valore,null);
        if (lunghezza == 0) {
            testa = coda = daAggiungere;
        } else {
            coda.next = daAggiungere;
            coda = daAggiungere;
        }
        lunghezza++;
    }

	/**
	 * Infiltro data una posizione
	 * @param i posizione
	 * @param valore valore da infiltrare
	 */
    public void add(int i, T valore) {
        if (i == 0) {
            this.addHead(valore);
        }
        if (i == lunghezza) {
            this.addTail(valore);
        }

        NodoConc<T> corrente = testa;
        for (int j = 0; j < lunghezza; j++) {
            if (j == i - 1) {
                NodoConc daAggiungere = new NodoConc(valore,null);
                NodoConc aCuiPunta = corrente.next;
                corrente.next = daAggiungere;
                daAggiungere.next = aCuiPunta;
                break;
            }
            corrente = corrente.next;
        }
        lunghezza++;
    }

	/**
	 * Restituisco linformazione
	 * @param i posizione
	 * @return L'informazione
	 */
    public T getInfo(int i) {
        NodoConc<T> corrente = testa;
        for (int j = 0; j < lunghezza; j++) {
            if (j == i) {
                return corrente.info;
            }
            corrente = corrente.next;
        }
        return null;
    }

	
	/**
	 * Il toString di questa classe Lista é stata pensata apposta per l'esercizio del compito!
	 * In quanto returna Stringhe diverse in base alla diversa situazione.
	 * @return Rappresenta l'info in una stringa
	 */
    @Override
    public String toString() {
        String valoriTeste = "";
        NodoConc viaggiante = testa;
		// Costruisco la mia string a
        for (int i = 0; i < lunghezza; i++) {
            valoriTeste += " [" + viaggiante.info+ "] ";
            viaggiante = viaggiante.next;
        }
		// Se la mia lista ha lunghezza 1 vuol dire che NON ci sono fermate equidistanti
		if(lunghezza==1){
			if(((PuntoMappa)testa.info).xPos==-1 && ((PuntoMappa)testa.info).xPos==-1){
				// Se sei sulla fermata vuol dire che la fermata ha lat e long -1 
				///PER CONVENZIONE. in quanto nel veneto non ci sono lat e long negative.
				return "Sei esattamente sulla fermata : "+valoriTeste;
			}
			return "Sei vicino alla fermata : "+valoriTeste;
		}else if(lunghezza>1){
			return "Sei equidistante dalle fermate"+ valoriTeste +", numero di fermate : " + lunghezza;
		}
		
		return valoriTeste;
    }
}

