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
        EsitoPartita esito = super.getEsitoPartita(partita);
        if(isPartitaVinta(partita)) esito = SCONFITTA;
        else if(isPartitaPersa(partita)) esito = VITTORIA;
        return esito;
    }

    public static Ruolo getInstance() { return new Assassino(); }

    private boolean isPartitaVinta(Partita partita) { return partita.isSoloCreatureOmbra() || partita.isSoloGuardie(); }

    private boolean isPartitaPersa(Partita partita) { return partita.isNoCreatureOmbra() && partita.isNoGuardie(); }

}
