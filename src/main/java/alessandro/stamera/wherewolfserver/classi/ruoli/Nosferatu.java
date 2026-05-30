package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.CreatureOmbra;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.NOSFERATU;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Tratto.CREATURA_OMBRA;

public final class Nosferatu extends CreatureOmbra
{

    private Nosferatu()
    {
        super
        (
    "Nosferatu", NOSFERATU, NERA,
"La prima notte riconosce il Ghoul e individua la Megera. Dalla seconda notte, individua i giocatori uccisi quella notte, può " +
          "indicarne uno e farlo tornare in vita. Se è un lupo mannaro o il Cacciatore di vampiri, il Nosferatu viene ucciso. Se è un mistico, " +
          "non accade nulla. Altrimenti, lo riconosce e diventa una progenie vampirica con aura oscura e fazione Nosferatu",
     3, false
        );
        aggiungiTratti(CREATURA_OMBRA);
    }

    @Override public boolean isNosferatu() { return true; }

    public static Ruolo getInstance() { return new Nosferatu(); }

}