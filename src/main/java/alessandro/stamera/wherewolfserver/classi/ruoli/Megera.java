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
     3, true
        );
    }

    @Override public boolean isMegera() { return true; }

    @Override public EsitoAttacco gildata() { return FALLITO; }

    public static Ruolo getInstance() { return new Megera(); }

}