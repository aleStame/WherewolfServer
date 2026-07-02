package alessandro.stamera.wherewolfserver.classi.ruoli.contadini;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.TipoContadino;
import alessandro.stamera.wherewolfserver.classi.fazioni.Villaggio;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;

public class Contadino extends Villaggio
{

    public Contadino(TipoContadino tipoContadino)
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
