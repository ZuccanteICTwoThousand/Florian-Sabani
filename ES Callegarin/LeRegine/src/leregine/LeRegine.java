package leregine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Florian
 */
public class LeRegine {

    public static void main(String[] args) throws FileNotFoundException {

        File f = new File("C:\\Users\\Florian\\Documents\\NetBeansProjects\\LeRegine\\src\\leregine\\INPUT.txt");
        
        Scanner s = new Scanner(f);
        
        int Nx = Integer.parseInt(s.next());    // COLONNE X RIGHE 
        int K1 = Integer.parseInt(s.next());    // REGINE BIANCHE
        int K2 = Integer.parseInt(s.next());    // REGINE NERE
        
        int[][] coordinateBianche = new int[K1][2];
        
        // Riempiamo le coordinate delle regine bianche
        for (int j = 0; j < K1; j++) {
            for (int i = 0; i < coordinateBianche[j].length; i++) {
                coordinateBianche[j][i] = Integer.parseInt(s.next());
            }
        }
        
        int[][] coordinateNere = new int[K2][2];
        
        // Riempiamo le coordinate delle regine nere
        for (int j = 0; j < K2; j++) {
            for (int i = 0; i < coordinateNere[j].length; i++) {
                coordinateNere[j][i] = Integer.parseInt(s.next());
            }
        }
        
        Scacchiera scacchiera = new Scacchiera(Nx,coordinateBianche,coordinateNere);
        scacchiera.stampa();
    }

}
