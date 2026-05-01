package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.fazioni.Villaggio;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Potere;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;

public final class Guaritore extends Villaggio
{

    private final Potere potere;

    private Guaritore()
    {
        super
        (
    "Guaritore", BIANCA,
"Ogni notte individua i giocatori uccisi e una volta per partita può indicarne uno per farlo tornare in vita.", 1, true
        );
        potere = new Potere();
    }

    @Override public boolean isGuaritore() { return true; }

    @Override public boolean isPotereUtilizzato() { return potere.isPotereUtilizzato(); }

    @Override public void utilizzaPotere() { potere.utilizzaPotere(); }

    public static Ruolo getInstance() { return new Guaritore(); }

}
