package alessandro.stamera.wherewolfserver.classi.ruoli.guardie;

import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;

public class AltraGuardia extends Guardia
{

    private AltraGuardia()
    {
        super
        (
    "Altra guardia", BIANCA,
"La prima notte riconosce le altre guardie, poi scopre dal moderatore il numero di criminali presenti in gioco."
        );
    }

    public static Ruolo getInstance() { return new AltraGuardia(); }

}