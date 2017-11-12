package letturascritturafile;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 *
 * @author Florian
 */
public class Gruppo {

    public ArrayList<Utente> utenti = new ArrayList<>();
    public String nomeGruppo = "";

    public Object contenutoGruppo = null;
    
    public Gruppo(String nome) {
        this.nomeGruppo = nome;
    }

    public Gruppo(String nome, ArrayList<Utente> utenti) throws NomeNonValidoException {
        this.utenti = utenti;
        this.nomeGruppo = nome;
        for (int i = 0; i < this.utenti.size(); i++) {
            this.utenti.get(i).gruppi.listaGruppi.add(this);
            this.utenti.get(i).gruppi.poteri.add(new Potere("Membro"));
        }
    }

    public Gruppo(String nome, Utente[] utenti) throws NomeNonValidoException {
        this.utenti = new ArrayList<>();
        this.nomeGruppo = nome;
        for (int i = 0; i < utenti.length; i++) {
            this.utenti.add(utenti[i]);
            this.utenti.get(i).gruppi.listaGruppi.add(this);
            this.utenti.get(i).gruppi.poteri.add(new Potere("Membro")); 
        }
    }

    /**
     * @param u Utente che verra aggiunto
     */
    public void aggiungiUtente(Utente u) throws NomeNonValidoException, FileNotFoundException {
        u.gruppi.listaGruppi.add(this);
        u.gruppi.poteri.add(new Potere("Membro"));
        this.utenti.add(u);
        DataBase.setUtente(u);
    }

    public void espelli(Utente chi) throws FileNotFoundException {
        if(chi!=utenti.get(0)){     // Se non e il fondatore ad essere eliminato...
            for (int i = 0; i < utenti.size(); i++) {
                if (utenti.get(i) == chi) {
                    utenti.get(i).gruppi.listaGruppi.remove(i);
                    utenti.get(i).gruppi.poteri.remove(i);
                    utenti.remove(i);
                }
            }
            DataBase.setUtente(chi);
        }
    }

    public void promuovi(Utente chi) throws FileNotFoundException {
        for (int i = 0; i < utenti.size(); i++) {
            if (utenti.get(i)==chi) {
                String potereMassimo = utenti.get(i).gruppi.poteri.get(i).poteriPossibili[utenti.get(i).gruppi.poteri.get(i).poteriPossibili.length-1];
                if (!utenti.get(i).gruppi.poteri.get(i).potereCheHoIo.equals(potereMassimo)) {    // Se non e fondatore
                    utenti.get(i).gruppi.poteri.get(i).incrementa();
                }
            }
        }
        DataBase.setUtente(chi);
    }

    public void declassa(Utente chi) throws FileNotFoundException {
        if(chi!=utenti.get(0)){     // Se non e il fondatore ad essere eliminato...
            for (int i = 0; i < utenti.size(); i++) {
                if (utenti.get(i) == chi) {
                    String potereMinimo = utenti.get(i).gruppi.poteri.get(i).poteriPossibili[0];
                    if (!utenti.get(i).gruppi.poteri.get(i).potereCheHoIo.equals(potereMinimo)) {    // Se non e Membro
                        utenti.get(i).gruppi.poteri.get(i).decrementa();
                    }
                }
            }
            DataBase.setUtente(chi);
        }
    }

    public Object pull(){
        return contenutoGruppo;
    }
    
    public void push(Object c){
        this.contenutoGruppo = c;
    }
    
    @Override
    public String toString() {
        return "Gruppo "+nomeGruppo + " : "+utenti +'}';
    }
    
}

class Gruppi {

    ArrayList<Gruppo> listaGruppi = new ArrayList<>();
    ArrayList<Potere> poteri  = new ArrayList<>();

}

class Potere {

    public final String[] poteriPossibili = {"Membro", "Amministratore", "Fondatore"};
    public String potereCheHoIo;
    private int posizione;

    public Potere(String potere) throws NomeNonValidoException {
        for (int i = 0; i < poteriPossibili.length; i++) {
            if (potere.equals(poteriPossibili[i])) {
                potereCheHoIo = potere;
                posizione = i;
                return;
            }
        }
        throw new NomeNonValidoException();
    }

    public String getPotereCheHoIo() {
        return potereCheHoIo;
    }

    public void incrementa() {
        potereCheHoIo = poteriPossibili[++posizione];
    }

    public void decrementa() {
        potereCheHoIo = poteriPossibili[--posizione];
    }
}
