package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;

public class Contadino extends Villaggio
{

    public Contadino()
    {
        super
        (
        "Contadino", BIANCA,
    "Il Contadino ha una delle seguenti identità nascoste (a sua insaputa) : Semplice, Eroe, Discendente dei Lupi, Mostro.", 1,
        false
        );
    }

    @Override public boolean isContadino() { return true; }

}
