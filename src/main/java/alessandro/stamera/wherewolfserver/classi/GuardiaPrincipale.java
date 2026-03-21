package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;

public final class GuardiaPrincipale extends Guardia
{

    private GuardiaPrincipale()
    {
        super
        (
            "Guardia", BIANCA,
        "La prima notte riconosce le altre guardie, poi scopre dal moderatore il numero di criminali presenti in gioco."
        );
    }

    public static Ruolo getInstance() { return new GuardiaPrincipale(); }

}