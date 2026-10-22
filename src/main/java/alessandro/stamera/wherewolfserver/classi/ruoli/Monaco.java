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
     1, false,
    "Sapere quali ruoli non sono in gioco può essere molto più importante di quanto non sembri a prima vista. La vostra conoscenza vi " +
            "permetterà in certe partite di poter tralasciare ragionamenti su potenziali minacce e in altre di smascherare degli impostori. Se " +
            "i ruoli che sapete non essere presenti sono Creature dell'Ombra, come il Vampiro, potrebbe risultare vincente scoprirsi da subito " +
            "per dare l'informazione al villaggio. In caso contrario, la tattica migliore è sicuramente quella di restare in attesa che " +
            "qualcuno faccia un passo falso e dica di essere un ruolo che voi sapete non essere in gioco."
        );
    }

    @Override public boolean isMonaco() { return true; }

    public static Ruolo getInstance() { return new Monaco(); }

}