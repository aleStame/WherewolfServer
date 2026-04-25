package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.fazioni.Villaggio;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;

public final class Oste extends Villaggio
{

    private Oste()
    {
        super
        (
    "Oste", BIANCA,
"Ogni mattino se la Veggente quella notte ha scoperto un'aura oscura, il moderatore lo comunica pubblicamente", 1, false
        );
    }

    @Override public boolean isOste() { return true; }

    public static Ruolo getInstance() { return new Oste(); }

}