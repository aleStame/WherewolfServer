package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import alessandro.stamera.wherewolfserver.classi.fazioni.Amanti;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.FALLITO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.GIULIETTA_BECCATA;

public final class Giulietta extends Amanti
{

    private Giulietta()
    {
        super
        (
    "Giulietta",
"La prima notte indica un giocatore, Romeo, che la riconosce. Quel giocatore diventa protetto dalle creature dell'ombra finché " +
          "Giulietta è in gioco, e la sua fazione diventa Amanti. Se uno dei due viene ucciso di notte o messo al rogo, l'altro si uccide " +
          "durante la notte.",
    "La vostra sarà una partita molto difficile. Dovrete proteggere il vostro amato senza rendere troppo palese il vostro legame, in " +
            "quanto diverreste facile preda dei Lupi che eliminerebbero due giocatori in una volta sola. Come Giulietta, non sapete che ruolo " +
            "abbia il vostro amato, quindi il vostro primo obiettivo sarà capire chi sia in realtà il vostro Romeo per poterlo difendere al meglio."
        );
    }

    @Override public boolean isGiulietta() { return true; }

    @Override public EsitoAttacco gildata() { return FALLITO; }

    @Override public EsitoAttacco attaccoLupi(Ruolo lupo) { return getEsitoAttaccoDefault(); }

    @Override public EsitoAttacco vampirizzazione() { return getEsitoAttaccoDefault(); }

    private EsitoAttacco getEsitoAttaccoDefault() { return GIULIETTA_BECCATA; }

    public static Ruolo getInstance() { return new Giulietta(); }

}