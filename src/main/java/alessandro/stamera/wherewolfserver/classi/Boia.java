package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.NERA;

public final class Boia extends Inquisizione
{

    public Boia()
    {
        super
        (
    "Boia", NERA,
"La prima notte viene individuato dall'Inquisitore. Se non è accusato, può segnalare un giocatore durante il ballottaggio: se è un " +
          "mistico o una creatura dell'ombra, i voti di tutti gli altri accusati vengono azzerati alla fine della votazione"
        );
    }

    @Override public boolean isBoia() { return true; }

}
