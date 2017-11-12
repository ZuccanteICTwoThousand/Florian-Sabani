package preparazionecodepile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author Florian
 */
public class ModificaFile {
    private ArrayList<String> lista;
    private final File fileTesto;
    
    public ModificaFile(String file) throws FileNotFoundException {
        this.fileTesto = new File(file);
        Scanner registra = new Scanner(fileTesto);
        lista = new ArrayList<>();
        
        while (registra.hasNext()) {
            lista.add(registra.nextLine());
        }
    }
    
    public void modificaPos(int posizione,String cambiaIn) throws FileNotFoundException{
        lista.set(posizione, cambiaIn);
        aggiornaFile();
    }
    
    public void modificaPos(String parola,String cambiaIn) throws FileNotFoundException{
        lista.set(lista.indexOf(parola), cambiaIn);
        aggiornaFile();
    }
    
    private void aggiornaFile() throws FileNotFoundException{
        PrintWriter p = new PrintWriter(fileTesto);
        for (int i = 0; i < lista.size(); i++) {
            p.println(lista.get(i));
        }
        p.close();
    }
    
}
