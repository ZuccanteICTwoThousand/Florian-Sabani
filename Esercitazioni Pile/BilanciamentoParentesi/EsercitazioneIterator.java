package esercitazioneiterator;

/**
 * @author Florian
 */
public class EsercitazioneIterator {

    public static void main(String[] args) {
        Pila<String> pila = new Pila<>();

        String bilanciata = "{10-2*(2-1)*[1-(2/3)]}";
        String nonBilanciata = "(((";

        ControlloBilanciamento c = new ControlloBilanciamento(nonBilanciata);
        System.out.println(c.isBilanciata());
    }

}

class ControlloBilanciamento {

    String equazione;
    
    private final String[] aperta = {"(","[","{"};
    private final String[] chiusa = {")","]","}"};
    
    
    public ControlloBilanciamento(String equazione) {
        this.equazione = equazione;
    }
    
    boolean isBilanciata(){
        Pila<String> controllo =  new Pila<>();
        for (int i = 0; i < equazione.length(); i++) {
            char pos = equazione.charAt(i);
            
            if(isParentesi(aperta,pos)){
                controllo.push(pos+"");
            }
            
            if(isParentesi(chiusa,pos)){
                if(controllo.getLunghezza()<1) return false;
                if(!isStessoTipo(controllo.pop().charAt(0),pos)){
                    return false;
                }
            }
        }
        
        return controllo.getLunghezza()<1;
    }

    private boolean isParentesi(String[] tipoParentesi,char charAt) {
        for (int i = 0; i < tipoParentesi.length; i++) {
            if(charAt == tipoParentesi[i].charAt(0)){
                return true;
            }
        }
        return false;
    }

    private boolean isStessoTipo(char aperta,char chiusa){
        int posAperta = 0;
        for (int i = 0; i < this.aperta.length; i++) {
            if(aperta==this.aperta[i].charAt(0)){
                posAperta = i;
            }
        }
        return chiusa == this.chiusa[posAperta].charAt(0);
    }

}
