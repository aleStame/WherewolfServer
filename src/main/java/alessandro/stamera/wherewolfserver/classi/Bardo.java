package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.VILLAGGIO;

public final class Bardo extends Ruolo
{

    public Bardo()
    {
        super
        (
    "Bardo", VILLAGGIO, BIANCA,
"Ogni mattina, se la Veggente quella notte ha scoperto un'aura bianca, il Moderatore la comunica pubblicamente.", 1, false
        );
    }

    @Override public boolean isBardo() { return true; }

}
