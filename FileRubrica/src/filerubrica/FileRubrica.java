package filerubrica;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author florian.sabani
 */
public class FileRubrica {
	public static void main(String[] args) throws FileNotFoundException {
		File fInput = new File("input.txt.txt");
		Scanner scan = new Scanner(fInput);
		
		Rubrica r = new Rubrica(scan);
		
		PrintWriter p = new PrintWriter("output.txt.txt");
		
		Iterator it = r.getListaContatti().iterator();
		
		while (it.hasNext()) {
			p.println(it.next().toString());
		}
		
		scan.close();
		p.close();
		
	}
	
}
