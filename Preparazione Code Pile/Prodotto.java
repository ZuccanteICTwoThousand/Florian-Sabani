package preparazionecodepile;
/**
 *
 * @author Florian
 */
public class Prodotto {
    private String nomeProdotto;
    private int prezzoProdotto,quantitativo;

    @Override
    public String toString() {
        return "Prodotto{" + "nomeProdotto=" + nomeProdotto + ", prezzoProdotto=" + prezzoProdotto + ", quantitativo=" + quantitativo + '}';
    }

    public Prodotto(String nomeProdotto, int prezzoProdotto, int quantitativo) {
        this.nomeProdotto = nomeProdotto;
        this.prezzoProdotto = prezzoProdotto;
        this.quantitativo = quantitativo;
    }

    public String getNomeProdotto() {
        return nomeProdotto;
    }

    public int getPrezzoProdotto() {
        return prezzoProdotto;
    }

    public int getQuantitativo() {
        return quantitativo;
    }
}
