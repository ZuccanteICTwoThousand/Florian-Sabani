package letturascritturafile;

import java.io.FileNotFoundException;
import java.util.Date;

/**
 *
 * @author Florian
 */
class Utente {

    private final String utente;
    protected String passwd;
    protected Date ultimoAccesso;

    protected Gruppi gruppi= new Gruppi();   //gruppi a cui faccio parte;

    public Utente(String utente, String passwd) throws FileNotFoundException, NomeNonValidoException {
        if (utente.length() < 1) {
            throw new NomeNonValidoException();
        }
        this.utente = utente;
        this.passwd = passwd;
        ultimoAccesso();
        DataBase.addUser(this);
    }

    public Utente(String nome) throws FileNotFoundException, NomeNonValidoException {
        this(nome, "");
    }

    public void changePasswd(String oldPasswd, String newPassword) throws FileNotFoundException {
        if (DataBase.getUserPasswd(utente).equals(oldPasswd)) {
            this.passwd = newPassword;
            ultimoAccesso();
            DataBase.setUserPasswd(utente, newPassword);
        }
    }

    public String getUtente() {
        return utente;
    }

    public String getPasswd() {
        return passwd;
    }

    public void unSubscribe() throws FileNotFoundException {
        ultimoAccesso();
        DataBase.removeUser(this);
    }

    private void ultimoAccesso() {
        ultimoAccesso = new Date();
    }

    public void creaGruppo(String nome) throws NomeNonValidoException, FileNotFoundException {
        gruppi.listaGruppi.add(new Gruppo(nome));
        gruppi.poteri.add(new Potere("Fondatore"));
        aggiornaDataBase();
    }

    public Gruppo getGruppo(String nomeGruppo) throws NomeNonValidoException {
        for (int i = 0; i < gruppi.listaGruppi.size(); i++) {
            if (gruppi.listaGruppi.get(i).nomeGruppo.equals(nomeGruppo)) { 
                return gruppi.listaGruppi.get(i);
            }
        }
        throw new NomeNonValidoException();
    }

    public void infoGruppo(String nomeGruppo) throws NomeNonValidoException {
        for (int i = 0; i < gruppi.listaGruppi.size(); i++) {
            if (gruppi.listaGruppi.get(i).nomeGruppo.equals(nomeGruppo)) {
                gruppi.listaGruppi.get(i).toString();
            }
        }
        throw new NomeNonValidoException();
    }

    private void aggiornaDataBase() throws FileNotFoundException {
        DataBase.setUtente(this);
    }
    
}

class NomeNonValidoException extends Exception {

    public NomeNonValidoException() {
        System.err.println("Inserisci un NOME valido.");
    }
}
