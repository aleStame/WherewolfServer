package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.fazioni.Criminale;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;

public final class Ladra extends Criminale
{

    private Ladra()
    {
        super
        (
    "Ladra", BIANCA,
"La prima notte riconosce gli altri criminali. Una volta per partita, dalla seconda notte può aprire gli occhi nel turno di un " +
          "mistico. La prima volta che viene attaccata, è protetta dalle creature dell'ombra.",
    "Il vostro ruolo è molto delicato. Essendo uno dei Criminali con aura bianca, siete più difficilmente individuabili e sarà per voi " +
            "più facile confondervi tra i vari abitanti del Villaggio. L'unico modo per essere scoperta, è quello di usare il vostro potere " +
            "nel turno di un Mistico ancora in gioco, quindi dovreste cercare di utilizzarlo solo se pensate che uno dei Mistici in gioco stia " +
            "bluffando. Anche la vostra protezione durante la notte è delicata: è vero che funziona una volta soltanto, ma le Creature " +
            "dell'Ombra questo non lo sanno e possono pensare di aver attaccato qualcuno che sia protetto continuamente e questo può sicuramente " +
            "giocare a vostro vantaggio."
        );
    }

    @Override public boolean isLadra() { return true; }

    public static Ruolo getInstance() { return new Ladra(); }

}