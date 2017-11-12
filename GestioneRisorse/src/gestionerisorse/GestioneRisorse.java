package gestionerisorse;
/**
 *
 * @author Florian
 */
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class GestioneRisorse {
    
    static Object risorsa = new Object();   // non la uso ma se dovessi volera usare staaak
    
    static ArrayList<String> articoliPubblicati = new ArrayList<String>();

    static Semaphore nArticoli = new Semaphore(0);
    static Semaphore lucchetto = new Semaphore(1);  // inizialmente e aperto

    static class Produttore extends Thread {
        
        @Override
        public void run() {
            int contatore = 1;
            try {
                while (true) {
                    String threadName = Thread.currentThread().getName() + contatore++;
                    
                    lucchetto.acquire();
                    articoliPubblicati.add(threadName);
                    System.out.println("Il Produttore ha prodotto : " + threadName);
                    lucchetto.release();
                    
                    // aggiungo nei permessi un articolo 1
                    nArticoli.release();
                    Thread.sleep(500);
                }
            } catch (InterruptedException x) {
            }
        }
    }
    
    // Leggera un articolo ogni volta che possibiile
    static class Consumatore extends Thread {
        String nomeConsumatore;
        
        public Consumatore(String name) {
            this.nomeConsumatore = name;
        }
        
        @Override
        public void run() {
            try {
                while (true) {
                    // fino a quando non ci sono articoli aspetta , rischiando il starvation
                    nArticoli.acquire();
                    
                    // codice da eseguire in modo atomico
                    lucchetto.acquire();
                    String articoloLetto = "";
                    for (String value : articoliPubblicati) {
                        articoloLetto = value + ",";
                    }
                    System.out.println(nomeConsumatore + " ha usato : " + articoloLetto);
                    lucchetto.release();    // fine sezione critichissimissima
                }
            } catch (InterruptedException e) {
            }
        }
    }
    
    public static void main(String[] args) {
        new Produttore().start();   // abbiamo un produttore
        // e piu consumatori...
        new Consumatore("Florian").start();
        new Consumatore("Luca").start();
        new Consumatore("Davide").start();
        new Consumatore("Andrea").start();
        new Consumatore("Dein").start();
    }
}