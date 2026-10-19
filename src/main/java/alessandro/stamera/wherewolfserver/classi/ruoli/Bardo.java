package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.fazioni.Villaggio;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;

public final class Bardo extends Villaggio
{

    private Bardo()
    {
        super
        (
    "Bardo", BIANCA,
"Ogni mattina, se la Veggente quella notte ha scoperto un'aura bianca, il Moderatore la comunica pubblicamente.", 1, false,
    "Il vostro Ruolo, come quello dell’Oste, funzionerà meglio se resterete nascosti e lascerete che sia il Moderatore a dare le " +
            "informazioni dovute alla vostra presenza in gioco. Trovare un’Aura Bianca è di gran lunga più probabile, quindi, anche se di per sé " +
            "è un’informazione meno importante, potrebbe essere che il villaggio sappia fin da subito della vostra presenza in gioco. Ogni volta " +
            "che il Moderatore, al mattino, dirà che non giungono voci, voi saprete che la Veggente, se ancora in vita, avrà trovato un’Aura " +
            "Oscura, starà a voi capire come e quando condividere con gli altri questa informazione."
        );
    }

    @Override public boolean isBardo() { return true; }

    public static Ruolo getInstance() { return new Bardo(); }

}