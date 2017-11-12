package listaconcatenata;

import java.util.Scanner;

/**
 *
 * @author florian.sabani
 */
public class Main2 {
	public static void main(String[] args) {
		Lista<String> lista = new Lista<>();
		Scanner input = new Scanner(System.in);
		String valori = input.nextLine();
		int inizio =0;
		for(int i = 0;i<valori.length();i++,inizio++){
			if(isSeparatore(valori.charAt(i))){
				lista.addTail(valori.substring(inizio, i));
				inizio=0;
			}
		}
		System.out.println(lista.toString());
	}

	private static boolean isSeparatore(char charAt) {
		String[] separatori = {" ", ",", ";", "-"};
		for (int i = 0; i < separatori.length; i++) {
			if(charAt==separatori[i].charAt(0)){
				return true;
			}
		}
		return false;
	}
}
