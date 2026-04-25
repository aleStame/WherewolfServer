package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.fazioni.Villaggio;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;

public final class Bardo extends Villaggio
{

    private Bardo()
    {
        super
        (
    "Bardo", BIANCA,
"Ogni mattina, se la Veggente quella notte ha scoperto un'aura bianca, il Moderatore la comunica pubblicamente.", 1, false
        );
    }

    @Override public boolean isBardo() { return true; }

    public static Ruolo getInstance() { return new Bardo(); }

}
