package giochipile;

/**
 *
 * @author Florian
 */
public class Pila<T> {

    private Nodo<T> testa;
    private int lunghezza;

    public Pila() {
        this.testa = new Nodo<>(null);
    }

    public void push(T x) {
        Nodo nuovaTesta = new Nodo(x);
        if(testa==null){
            testa = nuovaTesta;
        }else{
            nuovaTesta.setSotto(testa);
            testa = nuovaTesta;
        }
        lunghezza++;
    }
    
    public Nodo pop() {
        Nodo testAttuale = null;
        if(lunghezza>=0){
            testAttuale = testa;
            testa = testa.getSotto();
            lunghezza--;
        }else{
            System.out.println("La pila é vuota");
        }
        return testAttuale;
    }

    public T getFront() {
        return this.testa.getValore();
    }

    public void visita(){
        testa.visita(this.lunghezza);
    }
    
    public int getLunghezza() {
        return lunghezza;
    }
    
}

class Nodo<T> {

    private T valore;
    private Nodo<T> sotto;

    public Nodo(T valore) {
        this.valore = valore;
    }

    public T getValore() {
        return valore;
    }

    public Nodo<T> getSotto() {
        return sotto;
    }

    public void setValore(T valore) {
        this.valore = valore;
    }

    public void visita(int contatore){
        System.out.println(valore);
        if(contatore>0)
            sotto.visita(contatore-1);
    }
    
    public void setSotto(Nodo<T> sopra) {
        this.sotto = sopra;
    }
    
}
