package alberoricerca;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 *
 * @author Florian
 */
class Dizionario {

    AlberoPrefissi<Parola> ap;

    Dizionario(String file) throws FileNotFoundException {
        ap = new AlberoPrefissi();

        FileReader f = new FileReader(file);
        Scanner lettura = new Scanner(f);
       // lettura.useDelimiter(",");

        while (lettura.hasNextLine()) {
            String parola = lettura.nextLine();
            ap.aggiungi(new Parola(parola, ""));
        }

        //ap.aggiungi(new Parola(parola, descrizione));
    }

    boolean trova(String ciao) {
        return ap.esiste(ciao);
    }
    
    String[] simili(String ciao){
        return ap.simili(ciao,100);
    }

    @Override
    public String toString() {
        return "Albero : " + ap.toString();
    }

    class Parola {

        final String descrizione;
        final String parola;

        public Parola(String parola, String descrizione) {
            this.descrizione = descrizione;
            this.parola = parola;
        }
    }
}
/*


    String[] simili(String ciao,int maxPar) {
        int pos = -1;
        char letteraIniziale = ciao.charAt(0);
        for (int i = 0; i < radici.size() && pos < 0; i++) {
            if(letteraIniziale == radici.get(i).lettera){
                pos = i;    // se ce lo trovo e quitto
            }
        }
        
        if(pos<0)return null;
        
        //System.out.println("La lettera "+ciao.charAt(0)+" ce!");
        
        Nodo corrente = radici.get(pos);
        ArrayList<String> simili = new ArrayList<>();
        
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
            if(ceNeUno){
                if(corrente.valore!=null){
                    simili.add(corrente.valore);
                }
            }else if(maxPar<=0){
                return daListTOArray(simili);
            }
             //System.out.println("La lettera "+ciao.charAt(i)+" ce!");
        }
        return daListTOArray(simili);
    }
    
    private String[] daListTOArray(ArrayList<String> parole){
        String[] array = new String[parole.size()];
        for (int i = 0; i < parole.size(); i++) {
            array[i] = parole.get(i);
        }
        return array;
    }
    
    @Override
    public String toString() {
        String ris = "";
        System.out.println("lunghezza = "+radici.size());
        for (int i = 0; i < radici.size(); i++) {
            System.out.println(radici.get(i).lettera);
        }
        return ris;
    }
    

*/