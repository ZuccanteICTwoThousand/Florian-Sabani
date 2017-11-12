package pasticceria;

/**
 * Eccezione che indica che un elemento ricercato in una lista/coda  non viene trovato.
 */
public class ItemNotFound extends Exception{

    /**
     * Default constructor
     */
    public ItemNotFound() {
        super();
    }

    /**
     * @return
     */
    @Override
    public String getMessage() {
        // TODO implement here
        return "Non Ã¨ stato possibile trovare l'elemento";
    }

}