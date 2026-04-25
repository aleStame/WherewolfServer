package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.fazioni.Villaggio;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;

public final class Medium extends Villaggio
{

    private Medium()
    {
        super
        (
    "Medium", BIANCA, "Dalla seconda notte, indica un giocatore eliminato e scopre se possedeva aura oscura.", 1, true
        );
    }

    @Override public boolean isMedium() { return true; }

    public static Ruolo getInstance() { return new Medium(); }

}
