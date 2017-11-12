package pasticceria;

/**
 * Segnala che la lista Ã¨ vuota
 */
public class ListEmptyException extends Exception{

    /**
     * Default constructor
     */
    public ListEmptyException() {
        super();
    }

    /**
     * 
     */
    public void ListEmptyException() {
        // TODO implement here
    }

    /**
     * Ritorna la stringa: "La lista Ã¨ vuota non posso rimuovere"
     * @return
     */
    @Override
    public String getMessage() {
        // TODO implement here
        return "La lista Ã¨ vuota non posso rimuovere";
    }

}