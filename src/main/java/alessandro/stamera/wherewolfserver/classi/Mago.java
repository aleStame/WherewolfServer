package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;

public final class Mago extends Villaggio
{

    public Mago()
    {
        super("Mago", BIANCA, "Ogni notte indica un giocatore e scopre se è mistico.", 1, true);
    }

    @Override public boolean isMago() { return true; }

    public static Ruolo getInstance() { return new Mago(); }

}
