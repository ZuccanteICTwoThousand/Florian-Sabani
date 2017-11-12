package impllementazionegrafo;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Florian
 */
 
class Graph<T>{
    private int nNodi;
    private ArrayList<T> listAdiacenza[];
    private T[] valoreNodi;
    
    Graph(T[] nNodi){
        this.nNodi = nNodi.length;
        listAdiacenza = new ArrayList[nNodi.length];
        valoreNodi = nNodi;
        
        for (int i=0; i<nNodi.length; i++){
            listAdiacenza[i] = new ArrayList();
        }
    }
 
    /**
     * Dati due indici crea un collegamento
     * @param daLui il nodo alla quale creare il collegamento
     * @param aLui il nodo a cui puntiamo con l arco
     */
    void addArco(int daLui, int aLui){
        listAdiacenza[daLui].add(valoreNodi[aLui]);
    }
 
    private void dfs(int v,boolean colorati[]){
        colorati[v] = true;
        System.out.print(valoreNodi[v]+" ");
        Iterator<T> i = listAdiacenza[v].iterator();
        while (i.hasNext()){
            int n = posizione(i.next());
            if (!colorati[n]){
                dfs(n,colorati);
            }
        }
    }

    void dfs(){
        boolean colorati[] = new boolean[nNodi];// in java sono settati in automatico tutti a false 
        //inizio il dfs uno ad uno da ogni nodo
        for (int i=0; i<nNodi; ++i){
            if (colorati[i] == false){
                dfs(i, colorati);
            }
        }
    }

    private int posizione(T next) {
        for (int i = 0; i < valoreNodi.length; i++) {
            if(next.equals(valoreNodi[i])){
                return i;
            }
        }
        return -1;
    }
    
    private class Nodo<T>{
        private int pos;
        private T info;
    }
    
}