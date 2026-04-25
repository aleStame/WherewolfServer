package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.fazioni.Inquisizione;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;

public final class Templare extends Inquisizione
{

    private Templare() { super("Templare", BIANCA, null); }

    @Override public boolean isTemplare() { return true; }

    public static Ruolo getInstance() { return new Templare(); }

}