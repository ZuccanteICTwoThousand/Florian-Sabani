package simulazionedeadlock;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Florian
 */
public class SimulazioneDeadLock {
    public static void main(String[] args) {
        Risorsa r1 = new Risorsa("risorsa 1");
        Risorsa r2 = new Risorsa("risorsa 2");
        
        Lettore t1 = new Lettore(r1,r2,false);
        Lettore t2 = new Lettore(r1,r2,true);
        
        new Thread(t1).start();new Thread(t2).start();
        
    }
    
    static class Risorsa{
        Semaphore sem = new Semaphore(1);
        String r1;

        public Risorsa(String r1) {
            this.r1 = r1;
        }
    }
    
    static class Lettore implements Runnable{
        Risorsa r1;
        Risorsa r2;
        boolean deadlock;
        public Lettore(Risorsa r1,Risorsa r2,boolean deadlock) {
            this.r1 = r1;
            this.r2 = r2;
            this.deadlock = deadlock;
        }

        @Override
        public void run() {
            if(!deadlock){
                try {
                    r1.sem.acquire();
                    Thread.sleep(100);
                    r2.sem.acquire();
                    System.out.println("Acquisite "+r1.r1+" e "+r2.r1);
                    r1.sem.release();
                    r2.sem.release();
                } catch (InterruptedException ex) {
                }
            }else{
                try {
                    r2.sem.acquire();
                    r1.sem.acquire();
                    System.out.println("Acquisite "+r2.r1+" e "+r1.r1);
                    r2.sem.release();
                    r1.sem.release();
                } catch (InterruptedException ex) {
                }
            }
        }
    }
    
}
