package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Fazione.VILLAGGIO;

public class Contadino extends Ruolo
{

    public Contadino(Aura aura)
    {
        super
        (
        "Contadino", VILLAGGIO, aura,
    "Il Contadino ha una delle seguenti identità nascoste (a sua insaputa) : Semplice, Eroe, Discendente dei Lupi, Mostro.", 1,
        false
        );
    }

    @Override public boolean isContadino() { return true; }

}
