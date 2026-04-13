package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.POSSEDUTO;
import static alessandro.stamera.wherewolfserver.classi.Tratto.CREATURA_OMBRA;

public final class Posseduto extends Ruolo
{

    private Posseduto()
    {
        super
        (
    "Posseduto", POSSEDUTO, NERA,
"La prima notte individua la Megera e riconosce il Peccatore. Anche se è stato ucciso, non è considerato eliminato dal gioco finché " +
           "un altro giocatore non diventa il Posseduto. Se è stato ucciso, indica un giocatore che lo riconosce: il ruolo di quel giocatore " +
           "diventa il Posseduto. Se viene ucciso al rogo perde tutti i poteri.",
     3, false
        );
        aggiungiTratti(CREATURA_OMBRA);
    }

    @Override public boolean isPosseduto() { return true; }

    public static Ruolo getInstance() { return new Posseduto(); }

}