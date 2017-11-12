package studenti4icgrafi;
/**
 *
 * @author Florian
 * @param <T> il tipo del quale ogni nodo dev essere
 */
public class Grafo<T> {

    private Nodo<T>[] nodi;

    public Grafo() {

    }

    public Grafo(Nodo<T>[] nodi) {
        this.nodi = nodi;
    }

    public T getInfo(int pos){
        return nodi[pos].getValore();
    }
    
    public Nodo getNodo(int pos){
        return nodi[pos];
    }
    
    public Arco getCollegamenti(int pos){
        return nodi[pos].getCollegamenti();
    }
    
    public Nodo[] getNodi(){
        return nodi;
    }
    
    public Arco getMigliorCammino(Nodo uno,Nodo due){
        if(uno.getCollegamenti().getNElementi()==0){
            return null;
        }
        return null;
    }
}

class Nodo<T> {
    T valore;
    Arco collegamenti;

    public Nodo(T valore, Arco collegamenti) {
        this.valore = valore;
        this.collegamenti = collegamenti;
    }

    public T getValore() {
        return valore;
    }

    public Arco getCollegamenti() {
        return collegamenti;
    }
    
}

class Arco {

    private Nodo[] lista;
    private int[] distanze;
    
    public Arco(Nodo[] nodiCollegati,int[] distanze) {  //distanzaTraIlMioNodoEOgniunoDeiNodi
        this.lista = nodiCollegati;
        this.distanze = distanze;
    }
    
    public int getDistanza(int nodoPos){
        return distanze[nodoPos];
    }
    
    public void aggiungiCollegamento(Nodo n){
        
    }
    
    public Nodo getNodo(int pos){
        return lista[pos];
    }
    
    public int getNElementi(){
        return lista.length;
    }
}
