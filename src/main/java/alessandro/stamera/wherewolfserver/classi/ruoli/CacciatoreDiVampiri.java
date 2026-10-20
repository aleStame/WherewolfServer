package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.fazioni.Villaggio;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.MORTO;

public final class CacciatoreDiVampiri extends Villaggio
{

    private CacciatoreDiVampiri()
    {
        super
        (
    "Cacciatore di vampiri", BIANCA,
"La prima notte scopre il Vampiro è in gioco. È protetto dal Vampiro e, se viene attaccato, viene avvisato e lo elimina", 2,
    false,
    "Il Vampiro è una delle minacce più insidiose per il Villaggio e voi siete l’unica reale risorsa contro di esso. La vostra strategia " +
            "dovrebbe essere quella di farvi attaccare da lui traendolo in inganno, magari fingendo di essere una facile preda da attaccare. La " +
            "parte difficile del vostro Ruolo sarà anche quella di far in modo che i giocatori si ricordino della minaccia del Vampiro, senza " +
            "essere costretti a rivelarvi, annullando del tutto la possibilità di ucciderlo. Il Nosferatu è una minaccia del tutto diversa dal " +
            "Vampiro. Da una parte, egli è un pericolo ancora maggiore per i Lupi Mannari, e quindi saranno loro i primi a cercare di ucciderlo. " +
            "Dall’altra, voi per ucciderlo avrete una sola possibilità, poiché, per farlo, dovreste farvi prima uccidere voi stessi!"
        );
    }

    @Override public boolean isCacciatoreDiVampiri() { return true; }

    @Override public EsitoAttacco vampirizzazione() { return getMorto(); }

    @Override public EsitoAttacco attaccoNosferatu() { return getMorto(); }

    private EsitoAttacco getMorto() { return MORTO; }

    public static Ruolo getInstance() { return new CacciatoreDiVampiri(); }

}