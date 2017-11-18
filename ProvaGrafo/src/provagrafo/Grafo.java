package provagrafo;

/**
 *
 * @author florian.sabani
 */
import java.util.ArrayList;
import java.util.Iterator;

class Grafo<T>{

    int nNodi;
    private ArrayList<T> listeAdiacenza[];
    private ArrayList<Integer> pesiAdiacenza[];
    private T[] valoreNodi;
    
    private boolean DIREZIONATO = false;
    
    
    Grafo(T[] nodi,boolean direzionato){
        this(nodi);
        this.DIREZIONATO = direzionato;
    }
    
    Grafo(T[] nNodi) {
        this.nNodi = nNodi.length;
        listeAdiacenza = new ArrayList[nNodi.length];
        pesiAdiacenza = new ArrayList[nNodi.length];

        valoreNodi = nNodi;

        for (int i = 0; i < nNodi.length; i++) {
            listeAdiacenza[i] = new ArrayList();
            pesiAdiacenza[i] = new ArrayList<>();
        }
    }

    Grafo(){
    }
    
    /**
     * Dati due indici crea un collegamento
     *
     * @param daLui il nodo alla quale creare il collegamento
     * @param aLui il nodo a cui puntiamo con l arco
     */
    void addArco(int daLui, int aLui) {
        addArco(daLui, aLui, 0);
    }

    void addArco(T daLui, T aLui) {
        addArco(indexOf(daLui), indexOf(aLui), 0);
    }

    void addArco(T daLui, T aLui, int peso) {
        addArco(indexOf(daLui), indexOf(aLui), peso);
    }

    void addArco(int daLui, int aLui, int peso) {
        listeAdiacenza[daLui].add(valoreNodi[aLui]);
        pesiAdiacenza[daLui].add(peso);
        /*    
        //Anche collegamento inverso :D
        if(!DIREZIONATO){
            listeAdiacenza[aLui].add(valoreNodi[daLui]);
            pesiAdiacenza[aLui].add(peso);
        }
*/
    }

    private void dfs(int i, boolean colorati[]) {
        colorati[i] = true;
        System.out.print(valoreNodi[i] + " ");
        Iterator<T> it = listeAdiacenza[i].iterator();
        while (it.hasNext()) {
            int n = indexOf(it.next());
            if (!colorati[n]) {
                dfs(n, colorati);
            }
        }
    }

    void dfs() {
        boolean colorati[] = new boolean[nNodi];// in java sono settati in automatico tutti a false 
        //inizio il dfs uno ad uno da ogni nodo
        for (int i = 0; i < nNodi; ++i) {
            if (colorati[i] == false) {
                dfs(i, colorati);
            }
        }
    }

    private void bfs(int num, boolean colorati[]) {

        colorati[num] = true;

        ArrayList<Integer> coda = new ArrayList<>();

        for (int i = 0; i < listeAdiacenza[num].size(); i++) {
            T valore = listeAdiacenza[num].get(i);
            int pN = indexOf(valore);
            if (!colorati[pN]) {
                System.out.print(" "+valore);
                coda.add(pN);
            }
        }

        Iterator<Integer> it = coda.iterator();
        while (it.hasNext()) {
            int n = it.next();
            if (!colorati[n]) {
                bfs(n, colorati);
            }
        }
    }

    void bfs() {
        // in java sono settati in automatico tutti a false 
        boolean colorati[] = new boolean[nNodi];
        //inizio il dfs uno ad uno da ogni nodo
        System.out.print(valoreNodi[0]);
        bfs(0, colorati);
    }

    int indexOf(T next) {
        for (int i = 0; i < valoreNodi.length; i++) {
            if (next.equals(valoreNodi[i])) {
                return i;
            }
        }
        return -1;
    }
    
    T[] getArchi(int pos){
        return (T[]) listeAdiacenza[pos].toArray();
    }
    
    T getInfo(int pos){
        return valoreNodi[pos];
    }
    
    int getNArchi(int pos){
        return listeAdiacenza[pos].size();
    }
    
    @Override
    protected Grafo clone(){
        Grafo g = new Grafo(valoreNodi,DIREZIONATO);
        g.listeAdiacenza = listeAdiacenza;
        g.pesiAdiacenza = pesiAdiacenza;
        return g;
    }
    
    int minCosto(T inizio,T fine){
        Grafo<Integer> controllo = new Grafo();
        controllo.valoreNodi = new Integer[nNodi];
        
        for (int i = 0; i < nNodi; i++) {
            controllo.valoreNodi[i] = Integer.MAX_VALUE;
        }
        
        return minSpesa(controllo,0,fine);
    }

    private int minSpesa(Grafo controllo, int corrente, T fine) {
        if(corrente == indexOf(fine)){
            return (int) controllo.valoreNodi[corrente];
        }
        
        T[] collegamenti = getArchi(corrente);
        
        for (int i = 0; i < 10; i++) {
            
        }
        return 1;
    }
}
