package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.fazioni.Inquisizione;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;

public final class Templare extends Inquisizione
{

    private Templare()
    {
        super
        (
    "Templare", BIANCA, null,
    "La Strega è l'unico ruolo a poter fermare ogni notte l'attacco delle Creature dell'Ombra, a patto che riesca ad intuire le loro " +
            "mosse e ad agire preventivamente. Cercate di non rivelare la vostra identità se non assolutamente costretti, in quanto siete forse " +
            "l'unico bersaglio nella lista dei Lupi che viene ancora prima della Veggente. Dal momento che il vostro potere protegge sia " +
            "dall'attacco dei Lupi che da quello del Vampiro, ogni turno dovete decidere qual è la priorità maggiore perché i loro bersagli " +
            "saranno diversi e voi potete proteggere un giocatore soltanto. Capire se un giocatore è stato maledetto può risultare determinante " +
            "per leggere correttamente la partita. E' possibile infatti che la Veggente abbia visto un'aura nera su un giocatore che sostiene di " +
            "non possederla e che entrambi stiano dicendo il vero. Soltanto l'uso del vostro potere permetterà di discernere una situazione come " +
            "questa."
        );
    }

    @Override public boolean isTemplare() { return true; }

    public static Ruolo getInstance() { return new Templare(); }

}