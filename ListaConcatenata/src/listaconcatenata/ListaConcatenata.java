package listaconcatenata;

import java.util.Scanner;

/**
 *
 * @author florian.sabani
 */
public class ListaConcatenata {
	public static void main(String[] args) {
		String[] a = {"aa","bb","cc","dd"};
		Lista<String> lista = new Lista<>();
		Scanner input = new Scanner(System.in);
		String valori = input.nextLine();
		while(valori.length()>0){
			lista.addTail(valori);
			valori = input.nextLine();
		}
		
		lista.addHead("xx");	//Aggiungo testa
		lista.addTail("ee");	//Aggiungo coda
		lista.rimuoviHead();	// rimuovo testa
		lista.rimuoviCoda();	// rimuovo coda
		lista.setInfo(1, "bb1");	//imposto pos 1
		lista.setInfo(0, "aa1");	//imposto pos 0
		lista.add(2, "pos2");	//aggiungo pos 2
		lista.remove(2);	// rimuovo pos 2

		System.out.println(lista.toString());
	}
	
}
