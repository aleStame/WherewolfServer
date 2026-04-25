package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.fazioni.Villaggio;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;

public final class Mago extends Villaggio
{

    private Mago()
    {
        super("Mago", BIANCA, "Ogni notte indica un giocatore e scopre se è mistico.", 1, true);
    }

    @Override public boolean isMago() { return true; }

    public static Ruolo getInstance() { return new Mago(); }

}
