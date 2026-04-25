package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.fazioni.Villaggio;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;

public final class Monaco extends Villaggio
{

    public Monaco()
    {
        super
        (
    "Monaco", BIANCA,
"La prima notte scopre almeno due ruoli che non sono presenti nel gioco, fra quelli che sono tra i ruoli possibili per quella partita.",
     1, false
        );
    }

    @Override public boolean isMonaco() { return true; }

    public static Ruolo getInstance() { return new Monaco(); }

}