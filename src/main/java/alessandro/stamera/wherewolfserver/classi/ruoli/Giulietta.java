package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import alessandro.stamera.wherewolfserver.classi.fazioni.Amanti;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.FALLITO;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;

public final class Giulietta extends Amanti
{

    private Giulietta()
    {
        super
        (
    "Giulietta",
"La prima notte indica un giocatore, Romeo, che la riconosce. Quel giocatore diventa protetto dalle creature dell'ombra finché " +
          "Giulietta è in gioco, e la sua fazione diventa Amanti. Se uno dei due viene ucciso di notte o messo al rogo, l'altro si uccide " +
          "durante la notte."
        );
    }

    @Override public boolean isGiulietta() { return true; }

    @Override public EsitoAttacco gildata() { return FALLITO; }

    public static Ruolo getInstance() { return new Giulietta(); }

}