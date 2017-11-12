package preparazionecodepile;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Florian
 */
public class Labirinto {
    char[][] griglia;

    public Labirinto(Scanner s) {
        Scanner c = s;
        int lunghezza = getAltezza(c);
        Scanner f = s;
        griglia = new char[lunghezza][getLarghezza(f)];
        
        for(int righe = 0;righe < griglia[0].length;righe ++){
            String linea = s.nextLine();
            for (int colonne = 0; colonne < griglia.length; colonne++) {
                griglia[righe][colonne] = linea.charAt(colonne);
            }
        }
    }
    
    private int getAltezza(Scanner s) {
        int contatore = 0;
        while(s.hasNextLine()){
            contatore++;
            s.nextLine();
        }
        return contatore;
    }
    
    private int getLarghezza(Scanner s) {
        return s.nextLine().length();
    }

    void visitaGriglia() {
        System.out.println(Arrays.deepToString(griglia));
    }
    
}
