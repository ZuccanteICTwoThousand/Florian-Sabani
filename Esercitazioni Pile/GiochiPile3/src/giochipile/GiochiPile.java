/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giochipile;

/**
 *
 * @author Florian
 */
public class GiochiPile {

    public static void main(String[] args) {
        /*
        String esempio = "([({})[]])";
        Analizza c = new Analizza(esempio);
        System.out.println(c.isBilanciata());
         */

        Espressione e = new Espressione("4*(5+9)-3");

        e.elabora();
    }

    static class Espressione {

        private String espressione;
        private final int ZERO_ASCI = 47;
        private final char[] OPERATORI = {'+', '-', '*', '/'};
        private final char[] PARENTESI = {'(', ')', '[', ']', '{', '}'};

        Pila<Integer> numeri;
        Pila<String> operatori;
        Pila<String> parentesi;
        Pila<Integer> risultati;
        
        public Espressione(String espressione) {
            this.espressione = espressione;
        }

        public String generaPostfissa() {
            if (espressione.length() == 0) {
                return "Inserire un espressione prima";
            }
            numeri = new Pila<>();
            operatori = new Pila<>();
            parentesi = new Pila<>();

            for (int i = 0; i < espressione.length(); i++) {
                char pos = espressione.charAt(i);
                if (pos > ZERO_ASCI && pos < ZERO_ASCI + 11) {
                    numeri.push(Integer.parseInt(pos+""));
                } else {
                    if (isOperatore(pos)) {
                        operatori.push(pos + "");
                    } else {
                        if (isParentesi(pos)) {
                            parentesi.push(pos + "");
                        } else {
                            System.out.println("Inserire un espressione valida.");
                        }
                    }
                }
            }

            String postFissa = generaStringa(numeri, operatori);
            return postFissa;
        }

        public void elabora() {
            char primoCar = espressione.charAt(0);
            switch (primoCar) {
                case '+':
                case '-':
                case '*':
                case '/':
                    Integer operDx = (Integer) numeri.pop().getValore();
                    Integer operSx = (Integer) numeri.pop().getValore();
                    Integer risultato = calcola(operSx, operDx, primoCar);
                    risultati.push(risultato);
                    break;
                default:
                    Integer numero = new Integer(espressione);
                    risultati.push(numero);
                    break;
            }
        }

        public Integer calcola(Integer x, Integer y, char oper) {
            Integer ris = null;
            switch (oper) {
                case '+':
                    ris = new Integer(x.intValue() + y.intValue());
                    break;
                case '-':
                    ris = new Integer(x.intValue() - y.intValue());
                    break;
                case '*':
                    ris = new Integer(x.intValue() * y.intValue());
                    break;
                case '/':
                    ris = new Integer(x.intValue() / y.intValue());
                    break;
            }
            return ris;
        }

        private boolean isOperatore(char pos) {
            for (int i = 0; i < OPERATORI.length; i++) {
                if (pos == OPERATORI[i]) {
                    return true;
                }
            }
            return false;
        }

        private boolean isParentesi(char pos) {
            for (int i = 0; i < PARENTESI.length; i++) {
                if (pos == PARENTESI[i]) {
                    return true;
                }
            }
            return false;
        }

        private String generaStringa(Pila<Integer> numeri, Pila<String> operatori) {
            String postFissa = "";
            for (int i = 0; i < numeri.getLunghezza();) {
                postFissa += numeri.pop().getValore();
            }
            for (int i = 0; i < operatori.getLunghezza();) {
                postFissa += operatori.pop().getValore();
            }
            return postFissa;
        }
    }

    static class Analizza {

        private String caso;
        private final String[] aperte = new String[]{"(", "[", "{"};
        private final String[] chiuse = new String[]{")", "]", "}"};

        public Analizza(String caso) {
            this.caso = caso;
        }

        public boolean isBilanciata() {
            Pila<String> pila = new Pila<>();
            for (int i = 0; i < caso.length(); i++) {
                String carattere = caso.charAt(i) + "";
                if (isParentesi(carattere)) {
                    if (isAperta(carattere)) {
                        pila.push(carattere);
                    } else {
                        //isChiusa...
                        if (pila.getLunghezza() == 0) {
                            return false;
                        }
                        if (!isStessaPos(pila.getFront(), carattere)) {
                            return false;
                        } else {
                            System.out.println("Prima parentesi chiusa = " + pila.pop().getValore());
                        }
                    }
                }
            }
            return pila.getLunghezza() == 0;
        }

        private boolean isParentesi(String carattere) {
            for (int i = 0; i < this.aperte.length; i++) {
                if (carattere.equals(aperte[i])) {
                    return true;
                }
            }
            for (int i = 0; i < this.chiuse.length; i++) {
                if (carattere.equals(chiuse[i])) {
                    return true;
                }
            }
            return false;
        }

        private boolean isAperta(String carattere) {
            for (int i = 0; i < this.aperte.length; i++) {
                if (carattere.equals(aperte[i])) {
                    return true;
                }
            }
            return false;
        }

        private boolean isStessaPos(String front, String carattere) {
            int posizioneAperta = -1, posizioneChiusa = -1;
            for (int i = 0; i < aperte.length; i++) {
                if (front.equals(aperte[i])) {
                    posizioneAperta = i;
                }
            }
            for (int i = 0; i < chiuse.length; i++) {
                if (carattere.equals(chiuse[i])) {
                    posizioneChiusa = i;
                }
            }
            return posizioneAperta == posizioneChiusa;
        }
    }
}
