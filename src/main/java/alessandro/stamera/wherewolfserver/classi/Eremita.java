package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;

public final class Eremita extends Villaggio
{

    private Eremita()
    {
        super("Eremita", BIANCA, "È protetto dalle creature dell'ombra", 1, false);
    }

    @Override public boolean isEremita() { return true; }

    public static Ruolo getInstance() { return new Eremita(); }

}
