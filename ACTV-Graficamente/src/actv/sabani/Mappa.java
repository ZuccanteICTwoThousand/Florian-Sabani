package actv.sabani;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author ps09
 */
public class Mappa {
	Lista<PuntoMappa> lista = new Lista<>();

	/**
	 * Costruttore che chiama il metodo load
	 * @param file Nome del file dalla quale bisogna leggere le informazioni
	 */
	Mappa(String file) throws FileNotFoundException {
		this.load(file);
	}

	/**
	 * Metodo che riempie la lista di Fermate leggendo le informazioni da un file chiamato 
	 * fileName , questo metodo salta le informazioni non importanti al finalismo del compito.s
	 * @param fileName nome del file dalla quale leggere le informazioni.
	 * @throws java.io.FileNotFoundException se il file non fosse trovato
	 */
	public void load(String fileName) throws FileNotFoundException {
		File f = new File(fileName);
		Scanner lettura = new Scanner(f);
		lettura.useDelimiter(",");
		lettura.nextLine(); // salto la prima linea in quanto é di elenco
		while (lettura.hasNextLine()) {
			int stop_id = lettura.nextInt();
			skip(lettura);	// skippo 
			String nome = lettura.next();
			skip(lettura);	// skippo 
			String coordinataX = lettura.next();
			String coordinataY = lettura.next();
			double x,y;
			try{
				x = Double.parseDouble(coordinataX);
				y = Double.parseDouble(coordinataY);
				lista.addTail(new PuntoMappa(stop_id, nome, x, y));	// Se la riga é scritta bene
			}catch(NumberFormatException e){	//altrimenti non la aggiungo alla lista
				//System.out.println("Errore nel file Testo ;(");
			}
			
			lettura.nextLine();	//passiamo alla linea sucessiva 
			//quindi nuovo Punto
		}
	}

	/**
	 * Salta una lettura dal file.
	 * @param s Lettore che deve saltare una lettura.
	 */
	private void skip(Scanner s) {
		s.next();	// skippo 
	}

	/**
	 * Trova , dato un punto nella mappa, la fermata più vicina.
	 * Restituisce tutte le fermata la quale é equalmente vicino.
	 * Returna un punto con coordinate -1 e -1 se SEI sulla fermata.
	 * @param lat	Latitudine della posizione
	 * @param longi	Longitudine della posizione
	 * @return	Fermata/e piu vicine
	 */
	public Lista<PuntoMappa> search(double lat,double longi){
		// La distanza minima inizialmente non si sa(potrebbe essere la distanza della prima fermata.)
		double distanzaMinima = Double.MAX_VALUE;
		PuntoMappa piuVicino = null;
		int nFermateEquidistanti = 0;
		Lista<PuntoMappa> listaPuntiEquidistanti = new Lista<>();
		for (int i = 0; i < lista.lunghezza;i++) {
			// SE TROVI UNA FERMATA PIU VICINA 
			if(distanzaMinima>lista.getInfo(i).distanza(lat, longi)){
				piuVicino = lista.getInfo(i);	// il punto piu vicino é questo.
				distanzaMinima = lista.getInfo(i).distanza(lat, longi);
				nFermateEquidistanti = 0;	// lo risetto a zero se trovo una fermata nuova piu vicina delle altre
			}
			// SE SEI EQUIDISTANTE DA PIU FERMATA
			if(distanzaMinima==lista.getInfo(i).distanza(lat, longi)){
				// Trovato un altro punto equidistante
				listaPuntiEquidistanti.addHead(lista.getInfo(i));	//Aggiungo in testa alla lista.
				nFermateEquidistanti++;	// questo numero lo incremento se trovo fermate dalla distanza uguale
			}
			// SE TI TROVI SULLA FERMATA
			if((lista.getInfo(i).distanza(lat, longi))==0){
				// Se sulla fermata :D
				Lista<PuntoMappa> seiSullaFermata = new Lista<>();
				seiSullaFermata.addTail(lista.getInfo(i));
				// per convenzione indico che le posizioni della fermata sono -1
				// in quanto nella nostra zona sono tutte positive
				// se la fermata ha latitudine e longitudine -1 vuol dire
				//che lutonto si trova sulla fermata.
				seiSullaFermata.getInfo(0).xPos = -1;
				seiSullaFermata.getInfo(0).yPos = -1;
				return seiSullaFermata;
			}
		}
		
		Lista<PuntoMappa> puntiEquidistanti = new Lista<>();
                
		for (int i = 0; i < nFermateEquidistanti; i++) {	// Leggo soltanto le fermate dalla distanza minima ed equidistante,
			//i vale zero perche ho fatto addHead sopra non addTail.
			puntiEquidistanti.addTail(listaPuntiEquidistanti.getInfo(i));
		}
                
                
		return puntiEquidistanti;
	}
	
	class PuntoMappa {
		//stop_id,stop_code,stop_name,stop_desc,stop_lat,stop_lon,zone_id,stop_url,
		//location_type,parent_station,stop_timezone,wheelchair_boarding
		String location = "Luogo non specificato.";	//Indica il nome della fermata.
		int id = -1;//Id della fermata che se non fosse indicato rimane -1
		double xPos, yPos;	//posizione x e y della nostra fermata

		
		/**
		 * Costruttore che permette di riempire i campi con valori leciti.
		 * @param id	Identificatore fermata
		 * @param location	Nome fermata
		 * @param xPos	Latitudine fermata
		 * @param yPos	Longitudine fermata
		 */
		public PuntoMappa(int id, String location, double xPos, double yPos) {
			this.id = id;
			this.location = location;
			this.xPos = xPos;
			this.yPos = yPos;
		}
		
		/**
		 *	Metodo che permette di calcolare la distanza tra la mia fermata e un punto dato.
		 * @param x1 Latidudine del punto
		 * @param y2 Longitudine del punto
		 * @return Distanza da percorre per raggiungere la fermata (this)
		 */
		private double distanza(double x1,double y2){
			//Ipotenusa dato il punto "Posizione" quindi le coordinate viene calcolata
			// la distanza dalla nostra fermata.
			return Math.sqrt((xPos-x1)*(xPos-x1)+(yPos-y2)*(yPos-y2));
		}

		@Override
		public String toString() {
			return "Nome fermata : " + location + ", ID =" + id + ", Latitudine =" + xPos + ", Longitudine=" + yPos;
		}
		
		
	}
}
