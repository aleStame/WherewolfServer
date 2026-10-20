package alessandro.stamera.wherewolfserver.classi.ruoli.guardie;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import alessandro.stamera.wherewolfserver.classi.fazioni.Villaggio;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.MORTO;

public class Guardia extends Villaggio
{

    public Guardia(String nome, Aura aura, String descrizione)
    {
        super
        (
            nome, aura, descrizione, 2, false,
    "Se non vi sono Criminali, avrete una partita più tranquilla: avere qualcuno che conosce il vostro Ruolo è sicuramente molto utile " +
            "durante le Votazioni e le difese. Attenzione solo a non essere scambiati per i due Lupi Mannari! Il Ruolo delle Guardie diventa " +
            "molto più interessante, e delicato, qualora vi siano in gioco dei Criminali. In questo caso, la sopravvivenza di una delle due " +
            "Guardie diventa di vitale importanza per la sicurezza del villaggio. Cercate di non rivelarvi subito: più rimarrete nascosti " +
            "maggiori saranno le possibilità di salvarvi dall’Assassino e di catturare il Capo Gilda. Rivelarsi Guardia può essere pericoloso " +
            "anche se si pensa che possa essere presente il Vampiro. Se dovesse infatti trasformare una delle Guardie, avrebbe modo di sapere " +
            "facilmente chi sono anche le altre."
        );
    }

    @Override public boolean isGuardia() { return true; }

    @Override public EsitoAttacco gildata() { return MORTO; }

}