package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.fazioni.PiccoloPopolo;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;

public final class Leprecauno extends PiccoloPopolo
{

    private Leprecauno()
    {
        super
        (
    "Leprecauno", BIANCA, "La prima notte riconosce le altre creature del Piccolo Popolo. Inoltre, è protetto da tutti i mistici"
        );
    }

    @Override public boolean isLeprecauno() { return true; }

    public static Ruolo getInstance() { return new Leprecauno(); }

}
