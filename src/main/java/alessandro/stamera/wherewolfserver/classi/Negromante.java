package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.NEGROMANTE;

public final class Negromante extends Ruolo
{

    private Negromante()
    {
        super
        (
    "Negromante", NEGROMANTE, BIANCA,
"La prima notte individua la Megera, riconosce il Becchino e indica due giocatori che diventano maledetti fino a che il Negromante è " +
          "in gioco. Se all'inizio del giorno sono stati maledetti due o più giocatori maledetti, il Moderatore lo comunica pubblicamente. Il " +
          "mattino successivo, se il Negromante è ancora in gioco, tutti i giocatori di fazione diversa da Negromante vengono eliminati.",
     3, true
        );
    }

    public static Ruolo getInstance() { return new Negromante(); }

}