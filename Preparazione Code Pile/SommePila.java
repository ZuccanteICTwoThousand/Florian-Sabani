package preparazionecodepile;
/**
 *
 * @author Florian
 */
public class SommePila {
    
    public int somma(int n1 ,int n2){
        Pila<Integer> p = affida(n1);
        Pila<Integer> p2 = affida(n2);
        
        Pila<Integer> risultato = new Pila();
        
        int lunghezza = Math.max(p.getLunghezza(), p2.getLunghezza());
        int riporto=0;
        for (int i = 0; i < lunghezza; i++) {
            int n = p.getLunghezza()>0 ? p.pop() : 0;
            int n0 = p2.getLunghezza()>0 ? p2.pop() : 0;
            int somma = n+n0;
            int temp = somma;
            if(somma>=10){
                somma/=10;
            }
            risultato.push(somma+riporto);
            riporto = 0;
            if(temp>=10){
                riporto++;
            }
        }
        String valore = "";
        for (int i = 0; i < risultato.getLunghezza();) {
            valore+=risultato.pop();
        }
        return Integer.parseInt(valore);
    }

    private Pila<Integer> affida(int n) {
        Pila<Integer> pila = new Pila<>();
        String numero = n+"";
        for (int i = 0; i < numero.length(); i++) {
            pila.push(Integer.parseInt(numero.charAt(i)+""));
        }
        return pila;
    }
    
}
