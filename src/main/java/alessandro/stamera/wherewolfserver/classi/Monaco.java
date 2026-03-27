package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;

public final class Monaco extends Villaggio
{

    public Monaco()
    {
        super
        (
    "Monaco", BIANCA,
"La prima notte scopre almeno due ruoli che non sono presenti nel gioco, fra quelli che sono tra i ruoli possibili per quella partita.",
     1, true
        );
    }

    public static Ruolo getInstance() { return new Monaco(); }

}