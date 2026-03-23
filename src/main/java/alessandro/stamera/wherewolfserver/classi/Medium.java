package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;

public final class Medium extends Villaggio
{

    public Medium()
    {
        super
        (
    "Medium", BIANCA, "Dalla seconda notte, indica un giocatore eliminato e scopre se possedeva aura oscura.", 1, true
        );
    }

    @Override public boolean isMedium() { return true; }

    public static Ruolo getInstance() { return new Medium(); }

}
