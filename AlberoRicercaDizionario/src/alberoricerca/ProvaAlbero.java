package alberoricerca;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Florian
 */
public class ProvaAlbero  {
    
    public static void main(String[] args) throws FileNotFoundException{
        
        //testNormale();
        
        //forTest();
        
        //stressTest(9999999);
    }

    static long tempoAttuale(){
        return System.currentTimeMillis();
    }

    private static void testNormale() throws FileNotFoundException {
        final String nomeFile = "tutteParole.txt";
        final String parolaDaCercare = "zio";
        final String unita = "  milli secondi";
        long tempoMillisec;
        
        System.out.println("PAROLA DA CERCARE : "+parolaDaCercare);
        
        tempoMillisec = tempoAttuale();
        Dizionario d = new Dizionario(nomeFile);
        System.out.println("Per costruire lalbero ci ha messo = "+(tempoAttuale()-tempoMillisec)+unita);
        
        tempoMillisec = tempoAttuale();
        System.out.println(d.trova(parolaDaCercare));
        System.out.println("Per trovare la parola con un trie ci ha messo = "+(tempoAttuale()-tempoMillisec)+unita);
        
        tempoMillisec = tempoAttuale();
        DizionarioLineare dl = new DizionarioLineare(nomeFile);
        System.out.println("Per costruire la lista concatenata ci ha messo = "+(tempoAttuale()-tempoMillisec)+unita);
                
        tempoMillisec = System.currentTimeMillis();
        System.out.println(dl.trovaLineare(parolaDaCercare));
        System.out.println("Per trovare la parola in modo lineare ci ha messo = "+(tempoAttuale()-tempoMillisec)+unita);     
    }

    private static void forTest() throws FileNotFoundException {
        final String nomeFile = "tutteParole.txt";
        final String[] parole = {"ciao","come","va","noi","siamo","le","parole","da","cercare"};
        final String unita = "  milli secondi";
        long tempoMillisec;
        

        
        tempoMillisec = tempoAttuale();
        Dizionario d = new Dizionario(nomeFile);
        System.out.println("Per costruire lalbero ci ha messo = "+(tempoAttuale()-tempoMillisec)+unita);
        
        tempoMillisec = tempoAttuale();
        for (int i = 0; i < parole.length; i++) {
            System.out.println(d.trova(parole[i]));
        }
        System.out.println("Per trovare le parole "+ Arrays.toString(parole) +" con un trie ci ha messo = "+(tempoAttuale()-tempoMillisec)+unita);

    }
    
    static String parolaRandom(int min,int max){
        Random r = new Random(System.currentTimeMillis());
        int lunghezza = min + r.nextInt(max-min);
        String alfabeto = "abcdefghilmnopqrstuvzxwyk";
        String parolaCasuale = "";
        for (int i = 0; i < lunghezza; i++) {
            parolaCasuale+=alfabeto.charAt(r.nextInt(alfabeto.length()));
        }
        
        return parolaCasuale;
    }

    private static void stressTest(int par) throws FileNotFoundException {
              final String nomeFile = "tutteParole.txt";
        final String[] parole = new String[par];
        
        final int lunghezzaMin = 3,lunghezzaMax = 15;
        
        for (int i = 0; i < par; i++) {
            parole[i]= parolaRandom(lunghezzaMin,lunghezzaMax);
        }
        
        final String unita = "  milli secondi";
        long tempoMillisec;
        

        
        tempoMillisec = tempoAttuale();
        Dizionario d = new Dizionario(nomeFile);
        System.out.println("Per costruire lalbero ci ha messo = "+(tempoAttuale()-tempoMillisec)+unita);
        
        tempoMillisec = tempoAttuale();
        for (int i = 0; i < parole.length; i++) {
            d.trova(parole[i]);
        }
        long tempo = (tempoAttuale()-tempoMillisec);
        System.out.println("Per trovare le "+par+" parole  con un trie ci ha messo = "+tempo+ unita);

    }
    
}
