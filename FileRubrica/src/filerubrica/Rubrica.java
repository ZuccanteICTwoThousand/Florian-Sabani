package filerubrica;

import java.util.Scanner;

/**
 *
 * @author florian.sabani
 */
public class Rubrica {
	@Override
	public String toString() {
		return "Nome,Cognome,Numero Di Telefono" + listaContatti.toString();
	}

	private Lista<Contatto> listaContatti;
	private AlberoBinario a;

	public Rubrica(Scanner input) {
		listaContatti = new Lista<>();
		while (input.hasNext()) {
			listaContatti.addTail(new Contatto(input.next(), input.next(), input.next()));//nome cognome numero
		}
	}

	public Rubrica(Scanner input, boolean si) {
		listaContatti = new Lista<>();
		while (input.hasNext()) {
			listaContatti.addTail(new Contatto(input.next(), input.next(), input.next()));//nome cognome numero
		}
		Contatto[] array = new Contatto[listaContatti.lunghezza];
		for (int i = 0; i < array.length; i++) {
			Contatto inTesta = listaContatti.rimuoviHead();
			array[i] =  new Contatto(inTesta.getNome(),inTesta.getCognome(),inTesta.numeroTelefono);
		}
		a = new AlberoBinario(array);
	}

	public Lista<Contatto> getListaContatti() {
		return listaContatti;
	}

	public class Contatto implements Comparable {
		private String nome, cognome, numeroTelefono;
		//private Color color;

		public String getNome() {
			return nome;
		}

		public String getCognome() {
			return cognome;
		}

		public Contatto(String nome, String cognome, String numero) {
			this.nome = nome;
			this.cognome = cognome;
			this.numeroTelefono = numero;
		}

		@Override
		public String toString() {
			return nome + "," + cognome + "," + numeroTelefono;
		}

		@Override
		public int compareTo(Object o) {
			Contatto contatto = (Contatto) o;
			int risultato = this.getCognome().compareTo(contatto.getCognome());
			if (risultato == 0) {   // I due cognomi sono uguali...
				risultato = this.getNome().compareTo(contatto.getNome()); // Se continua ad essere 0 hanno sia il nome che il cognome uguale
			}
			return risultato;
		}
	}

}
