package letturascritturafile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Florian
 */
class Rubrica {
    private File file;
    public ArrayList<Contatto> lista = new ArrayList<>();
    public Rubrica(String nome) throws FileNotFoundException {
        file =  new File(nome);
        Scanner s = new Scanner(file);
        s.useDelimiter(",");
        int i = 0;
        while(s.hasNextLine()){
            Campi c = new Campi();
            while(s.hasNext()) {
                c.valori.add(s.next());
            }
            lista.add(new Contatto(c));
        }
    }
    
    class Contatto{
        /*
        Name,Given Name,Additional Name,Family Name,Yomi Name,Given Name Yomi,Additional Name Yomi,Family Name Yomi,Name Prefix,Name Suffix
        ,Initials,Nickname,Short Name,Maiden Name,Birthday,Gender,Location,Billing Information,Directory Server,Mileage,Occupation,Hobby
        ,Sensitivity,Priority,Subject,Notes,Group Membership,E-mail 1 - Type,E-mail 1 - Value,Phone 1 - Type,Phone 1 - Value,Website 1 - Type
        ,Website 1 - Value
        */
        //33
        
        Campi campiDelContatto;

        @Override
        public String toString() {
            return campiDelContatto.toString();
        }

        public Contatto(Campi campiDelContatto) {
            this.campiDelContatto = campiDelContatto;
        }
        
        
        
    }
    
    class Campi{
        
        protected ArrayList<String> valori = new ArrayList<>();

        @Override
        public String toString() {
            String r= "";
            for (int i = 0; i < valori.size(); i++) {
                r+=valori.get(i);
            }
            return r;
        }
             
        protected void aggiungi(String s){
            valori.add(s);
        }      
        
    }
}
