package coda;
/**
 *
 * @author Andrea Dattero
 */
public class Atleta {
    private int punteggio,eta;
    private String nome,cognome;

    public Atleta(int punteggio, int eta, String nome, String cognome) {
        this.punteggio = punteggio;
        this.eta = eta;
        this.nome = nome;
        this.cognome = cognome;
    }

    public int getPunteggio() {
        return punteggio;
    }

    public int getEta() {
        return eta;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }
    
}
