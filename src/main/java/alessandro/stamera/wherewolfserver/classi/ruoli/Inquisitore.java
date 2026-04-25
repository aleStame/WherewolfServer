package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.fazioni.Inquisizione;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;

public final class Inquisitore extends Inquisizione
{

    private Inquisitore()
    {
        super
        (
    "Inquisitore", BIANCA,
"La prima notte individua il Boia e il Templare e scopre quanti mistici sono in gioco. Se viene indicato da un mistico, lo riconosce. " +
          "Può segnalare un giocatore durante le accuse: se è un mistico, sarà accusato a prescindere dai voti ricevuti."
        );
    }

    @Override public boolean isInquisitore() { return true; }

    public static Ruolo getInstance() { return new Inquisitore(); }

}