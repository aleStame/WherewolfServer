package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;

public final class Sidhe extends PiccoloPopolo
{

    private Sidhe()
    {
        super
        (
    "Sidhe", BIANCA,
"La prima notte riconosce le altre creature del Piccolo Popolo (Goblin, Leprecauno). È protetta da tutti i Mistici."
        );
    }

    @Override public boolean isSidhe() { return true; }

    public static Ruolo getInstance() { return new Sidhe(); }

}