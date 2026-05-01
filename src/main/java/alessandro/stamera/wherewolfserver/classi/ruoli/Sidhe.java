package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.fazioni.PiccoloPopolo;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;

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