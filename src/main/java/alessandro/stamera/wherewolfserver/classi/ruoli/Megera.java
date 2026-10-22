package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.FALLITO;

public final class Megera extends Ruolo
{

    private Megera()
    {
        super
        (
    "Megera", NERA,
"La prima notte viene individuata da tutte le creature dell'ombra. Se viene indicata da un mistico, fino a che la Megera è in gioco, " +
          "quel giocatore diventa Maledetto, riceverà sempre responsi negativi e non potrà più proteggere. Vince con qualsiasi creatura dell'ombra",
     3, true,
    "Giocherete la partita allo stesso tempo da sola e con tutte le fazioni dell’Ombra. E’ molto probabile che qualcuno dei vostri " +
            "alleati cercherà di farvi capire in qualche modo la sua identità durante il corso del gioco, quindi tenete le orecchie bene aperte a " +
            "tutto quello che vi viene detto. A parte questo, la vostra strategia consisterà nel farvi indicare dai Mistici del villaggio e nel " +
            "cercare di restare in vita il più possibile da quel momento in poi. Anche se potrebbe sembrarvi poco, a volte è sufficiente un " +
            "responso sbagliato dato alla Veggente per dare inizio ad una serie di ragionamenti che possono portare in poco tempo alle " +
            "conclusioni più errate."
        );
    }

    @Override public boolean isMegera() { return true; }

    @Override public EsitoAttacco gildata() { return FALLITO; }

    public static Ruolo getInstance() { return new Megera(); }

}