package alessandro.stamera.wherewolfserver.classi;

public class Contadino extends Villaggio
{

    public Contadino(Aura aura)
    {
        super
        (
        "Contadino", aura,
    "Il Contadino ha una delle seguenti identità nascoste (a sua insaputa) : Semplice, Eroe, Discendente dei Lupi, Mostro.", 1,
        false
        );
    }

    @Override public boolean isContadino() { return true; }

}
