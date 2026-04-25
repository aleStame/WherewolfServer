package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita;
import alessandro.stamera.wherewolfserver.classi.fazioni.Criminale;
import alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.SCONFITTA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.VITTORIA;

public final class Assassino extends Criminale
{

    private Assassino()
    {
        super
        (
    "Assassino", NERA,
"La prima notte riconosce gli altri criminali. Una volta per partita, dalla seconda notte, può aprire gli occhi nel turno di un " +
          "mistico. Se quel mistico in gioco, viene ucciso. Altrimenti, l'Assassino indica un giocatore che viene avvisato ed ucciso."
        );
    }

    @Override public boolean isAssassino() { return true; }

    @Override public EsitoPartita getEsitoPartita(Partita partita)
    {
        EsitoPartita esito;
        if(partita.isSoloCreatureOmbra() || partita.isSoloGuardie()) esito = SCONFITTA;
        else if(partita.isNoCreatureOmbra() && partita.isNoGuardie()) esito = VITTORIA;
        else esito = super.getEsitoPartita(partita);
        return esito;
    }

    public static Ruolo getInstance() { return new Assassino(); }

}
