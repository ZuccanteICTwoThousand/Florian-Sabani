package giocobottoni;

import java.awt.event.ActionListener;

/**
 *
 * @author Florian
 */
public class Tempo implements Runnable {

    ActionListener a;
    Integer puntatore;
    int delayMax;

    Tempo(Integer ritardo, Bottoni aThis) {
        a = aThis;
        puntatore = delayMax = ritardo;
    }

    @Override
    public void run() {
        aspetta(puntatore);
        a.actionPerformed(null);
    }

    private void aspetta(Integer puntatore) {
        try {
            Thread.sleep(puntatore);
        } catch (InterruptedException ex) {
        }
    }

}
