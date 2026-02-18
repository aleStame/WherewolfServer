package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;

public final class Bardo extends Villaggio
{

    public Bardo()
    {
        super
        (
    "Bardo", BIANCA,
"Ogni mattina, se la Veggente quella notte ha scoperto un'aura bianca, il Moderatore la comunica pubblicamente.", 1, false
        );
    }

    @Override public boolean isBardo() { return true; }

}
