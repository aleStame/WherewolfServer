package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;

public final class Oste extends Villaggio
{

    private Oste()
    {
        super
        (
    "Oste", BIANCA,
"Ogni mattino se la Veggente quella notte ha scoperto un'aura oscura, il moderatore lo comunica pubblicamente", 1, true
        );
    }

    public static Ruolo getInstance() { return new Oste(); }

}