package codadoppiacircolare;

/**
 *
 * @author Florian
 */
public class CodaDoppiaCircolare<T> {

    private Nodo<T> testa, coda;
    int nElementi = 0;

    public CodaDoppiaCircolare() {
    }

    public CodaDoppiaCircolare(int l) {
        for (int i = 0; i < l; i++) {
            enQueueDown((T) "");
        }
    }

    public void enQueueDown(T valore) {
        Nodo daAggiungere = new Nodo(valore);
        if (nElementi == 0) {
            testa = coda = daAggiungere;
        } else {
            coda.setSucessivo(daAggiungere);
            daAggiungere.setPrecedente(coda);
            coda = daAggiungere;
        }
        nElementi++;
    }

    public void enQueueUp(T valore) {
        Nodo daAggiungere = new Nodo(valore);
        if (nElementi == 0) {
            testa = coda = daAggiungere;
        } else {
            testa.setPrecedente(daAggiungere);
            daAggiungere.setSucessivo(testa);
            testa = daAggiungere;
        }
        nElementi++;
    }

    public T deQueueUp() {
        T valore = testa.informazione;
        if (nElementi > 1) {
            Nodo precendente = testa.precedente;
            testa = testa.sucessivo;
            testa.setPrecedente(precendente);
        } else {
            testa = null;
        }
        nElementi--;
        return valore;
    }

    public T deQueueDown() {
        T valore = coda.informazione;
        if (nElementi > 1) {
            Nodo dopo = coda.sucessivo;
            coda = coda.precedente;
            coda.setSucessivo(dopo);
        } else {
            testa = null;
        }
        nElementi--;
        return valore;
    }

    public T top() {
        return testa.informazione;
    }

    public T down() {
        return coda.informazione;
    }

    public T get(int posizione) {

        while (posizione > nElementi) {
            posizione -= nElementi;
        }

        if (posizione == 0) {
            return top();
        } else if (posizione == nElementi) {
            return down();
        }

        Nodo viaggiante;

        if (posizione > nElementi / 2) {
            viaggiante = coda;
            for (int j = 0; j < posizione; j++) {
                viaggiante = viaggiante.precedente;
            }
        } else {
            viaggiante = testa;
            for (int j = 0; j < posizione; j++) {
                viaggiante = viaggiante.sucessivo;
            }
        }

        return (T) viaggiante.informazione;
    }

    public void set(int posizione, T valore) {
        while (posizione > nElementi) {
            posizione -= nElementi;
        }

        Nodo viaggiante;

        if (posizione > nElementi / 2) {
            viaggiante = coda;
            for (int j = 0; j < posizione; j++) {
                viaggiante = viaggiante.precedente;
            }
        } else {
            viaggiante = testa;
            for (int j = 0; j < posizione; j++) {
                viaggiante = viaggiante.sucessivo;
            }
        }

        viaggiante.setInformazione(valore);
    }

    public CodaDoppiaCircolare subsequence(int pos1, int pos2) {
        int lunghezza = Math.abs(pos2 - pos1);
        CodaDoppiaCircolare sequenza = new CodaDoppiaCircolare(lunghezza);
        for (int i = 0; i < lunghezza; i++) {
            sequenza.set(i, get(i));
        }
        return sequenza;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < nElementi - 1; i++) {
            s += " " + get(i);
        }
        return "Lunghezza : " + nElementi + " {" + s + " };";
    }

    class Nodo<T> {

        private T informazione;
        private Nodo sucessivo, precedente;

        public Nodo() {
        }

        public Nodo(T informazione) {
            this.informazione = informazione;
        }

        public void setInformazione(T informazione) {
            this.informazione = informazione;
        }

        public void setSucessivo(Nodo sucessivo) {
            this.sucessivo = sucessivo;
        }

        public void setPrecedente(Nodo precedente) {
            this.precedente = precedente;
        }
    }
}
