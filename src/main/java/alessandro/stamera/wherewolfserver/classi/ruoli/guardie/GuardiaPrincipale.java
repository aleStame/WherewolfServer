package alessandro.stamera.wherewolfserver.classi.ruoli.guardie;

import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;

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