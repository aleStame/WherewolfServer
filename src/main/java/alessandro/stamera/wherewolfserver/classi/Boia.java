package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.INQUISIZIONE;

public final class Boia extends Ruolo
{

    public Boia()
    {
        super
        (
    "Boia", INQUISIZIONE, BIANCA,
"La prima notte viene individuato dall'Inquisitore. Se non è accusato, può segnalare un giocatore durante il ballottaggio: se è un " +
          "mistico o una creatura dell'ombra, i voti di tutti gli altri accusati vengono azzerati alla fine della votazione",
    3, false);
    }

    @Override public boolean isBoia() { return true; }

}
