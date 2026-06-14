package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.CreatureOmbra;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.VAMPIRO;

public final class Vampiro extends CreatureOmbra
{

    private Vampiro()
    {
        super
        (
    "Vampiro", VAMPIRO, BIANCA,
"La prima notte riconosce il Ghoul. Dalla seconda notte, può indicare un giocatore che viene avvisato. Se è il Cacciatore di vampiri o " +
          "un lupo, il Vampiro viene ucciso. Se è un mistico, non accade nulla. Altrimenti, quel giocatore lo riconosce e diventa una progenie " +
          "vampirica con aura oscura e fazione Vampiro. Inoltre, la prima notte individua la Megera.",
     2, true
        );
    }

    public boolean isVampiro() { return true; }

    public static Ruolo getInstance() { return new Vampiro(); }

}