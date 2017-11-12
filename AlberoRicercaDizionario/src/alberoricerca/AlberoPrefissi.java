package alberoricerca;

import alberoricerca.Dizionario.Parola;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Florian
 */
class AlberoPrefissi<T> {
    ArrayList<Nodo> radici = new ArrayList<>();

    public void aggiungi(Parola valore){
        
        char letteraIniziale = valore.parola.charAt(0);
        Nodo daAggiungere = new Nodo(letteraIniziale);
        int pos = -1;
        for (int i = 0; i < radici.size() && pos < 0; i++) {
            if(letteraIniziale == radici.get(i).lettera){
                pos = i;    // se ce lo trovo e quitto
            }
        }
        if(pos<0){  // se non cera
            radici.add(new Nodo(letteraIniziale));
            pos = radici.size()-1;
        }
        
        Nodo dallaQualePartire = radici.get(pos);
        adda(dallaQualePartire,valore,1);
    }

    private void adda(Nodo dallaQualePartire, Parola valore, int pos) {
        int lung = dallaQualePartire.sottoRadici.size();
        
        for (int i = 0; i < lung; i++) {
            char confronto = valore.parola.charAt(pos);
            if(confronto == dallaQualePartire.sottoRadici.get(i).lettera){
                    adda(dallaQualePartire.sottoRadici.get(i),valore,pos+1);
                return;
            }
        }
        // VUOL DIRE CHE NON E ANCORA STATE INSERITA UNA PAROLA COME QUESTA O ALMENO SIMILE...
        // quindi la inseriamo tutta quanta noi da dove siamo arrivato obv ( pos )
        
        Nodo corrente = dallaQualePartire;
        /*
        for (int i = pos; i < valore.parola.length(); i++) {
            //corrente.sottoRadici.add(new Nodo())
        }*/
        /*
        System.out.println("Addo per la prima volta : "+valore.parola.substring(pos,valore.parola.length()));
        System.out.println("Perche "+valore.parola.substring(0,pos)+" c'era gia!");
*/
        
        // Addo prima tutte le lettere senza descrizione
        for (int i = pos; i < valore.parola.length()-1; i++) {
            corrente.sottoRadici.add(new Nodo(valore.parola.charAt(i),null));
            corrente = corrente.sottoRadici.get(corrente.sottoRadici.size()-1);
        }
        
        // lultima con la descrizione op
        corrente.sottoRadici.add(new Nodo(valore.parola.charAt(valore.parola.length()-1),valore.descrizione));
    }

    boolean esiste(String ciao) {
        int pos = -1;
        char letteraIniziale = ciao.charAt(0);
        for (int i = 0; i < radici.size() && pos < 0; i++) {
            if(letteraIniziale == radici.get(i).lettera){
                pos = i;    // se ce lo trovo e quitto
            }
        }
        
        if(pos<0)return false;
        
        //System.out.println("La lettera "+ciao.charAt(0)+" ce!");
        
        Nodo corrente = radici.get(pos);
        
        for (int i = 1; i < ciao.length(); i++) {
            letteraIniziale = ciao.charAt(i);
            boolean ceNeUno = false;
            for (int j = 0; j < corrente.sottoRadici.size(); j++) {
                if(letteraIniziale == corrente.sottoRadici.get(j).lettera){
                    corrente = corrente.sottoRadici.get(j);
                    ceNeUno = true;
                    break;
                }
            }
            if(!ceNeUno){
                return false;
            }
             //System.out.println("La lettera "+ciao.charAt(i)+" ce!");
        }
        return true;
    }

    String[] simili(String ciao,int maxPar) {
        //ArrayList<String> simili = new ArrayList<>();
        
        int pos = -1;
        char letteraIniziale = ciao.charAt(0);
        for (int i = 0; i < radici.size() && pos < 0; i++) {
            if(letteraIniziale == radici.get(i).lettera){
                pos = i;    // se ce lo trovo e quitto
            }
        }
        
        if(pos<0)return new String[]{"--------------------"};
        
        //System.out.println("La lettera "+ciao.charAt(0)+" ce!");
        
        Nodo corrente = radici.get(pos);
        String corretta = "";
        for (int i = 1; i < ciao.length(); i++) {
            letteraIniziale = ciao.charAt(i);
            boolean ceNeUno = false;
            for (int j = 0; j < corrente.sottoRadici.size(); j++) {
                if(letteraIniziale == corrente.sottoRadici.get(j).lettera){
                    corrente = corrente.sottoRadici.get(j);
                    ceNeUno = true;
                    corretta+=letteraIniziale;
                    break;
                }
            }
            if(!ceNeUno){
                break;
            }
             //System.out.println("La lettera "+ciao.charAt(i)+" ce!");
        }
        
        int nSottoNodi = nSottoNodi(corrente,0,maxPar);
        String[] simili = new String[nSottoNodi];
        costruisciSuggerimenti(corrente,ciao.charAt(0)+corretta,simili,maxPar);
        
        return simili;
    }
    /*
    private String[] daListTOArray(ArrayList<String> parole){
        String[] array = new String[parole.size()];
        for (int i = 0; i < parole.size(); i++) {
            array[i] = parole.get(i);
        }
        return array;
    }
    */
    @Override
    public String toString() {
        String ris = "";
        System.out.println("lunghezza = "+radici.size());
        for (int i = 0; i < radici.size(); i++) {
            System.out.println(radici.get(i).lettera);
        }
        return ris;
    }

    private int nSottoNodi(Nodo corrente,int p,int max) {
        if(corrente.sottoRadici.size()>0 && max>0){
            for (int i = 0; i < corrente.sottoRadici.size(); i++,p++) {
                nSottoNodi(corrente.sottoRadici.get(i),p+1,max-1);
            }
        }
        return p;
    }

    private void costruisciSuggerimenti(Nodo corrente, String corretta, String[] simili, int max) {
        for (int i = 0; i < Math.min(max,corrente.sottoRadici.size()); i++) {
            simili[i] = suggerimento(corrente.sottoRadici.get(i),corretta);
        }
    }

    private String suggerimento(Nodo get,String corretta) {
        String parolaRandom = "";
        Random r = new Random();
        Nodo viaggiante = get;
        parolaRandom+= corretta;
        while(viaggiante.sottoRadici.size()>0){
            parolaRandom+=viaggiante.lettera;
            viaggiante = viaggiante.sottoRadici.get(0);
        }
        parolaRandom += viaggiante.lettera +" ; ";
        return parolaRandom;
    }
    
    
    
    private class Nodo {
        ArrayList<Nodo> sottoRadici;
        String valore;
        char lettera;
        
        public Nodo(char lettera,String valore) {
            sottoRadici = new ArrayList<>();
            this.valore = valore;
            this.lettera = lettera;
        }
        
        public Nodo(char lettera){
            this(lettera,null);
        }
        
        public void add(char lettera){
            sottoRadici.add(new Nodo(lettera));
        }
        
    }
}
