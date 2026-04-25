package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.fazioni.Villaggio;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.FALLITO;

public final class Eremita extends Villaggio
{

    private Eremita()
    {
        super("Eremita", BIANCA, "È protetto dalle creature dell'ombra", 1, false);
    }

    @Override public boolean isEremita() { return true; }

    @Override public EsitoAttacco attaccoNosferatu() { return FALLITO; }

    public static Ruolo getInstance() { return new Eremita(); }

}
