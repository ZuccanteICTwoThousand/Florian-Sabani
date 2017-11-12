package preparazionecodepile;

import java.io.FileNotFoundException;

/**
 * @author Florian
 */
public class PreparazioneCodePile {
    public static void main(String[] args) throws FileNotFoundException{
        SommePila s = new SommePila();
        System.out.println(s.somma(999,100));
    }

}
