package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita;
import alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.CreatureOmbra;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.SCONFITTA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.VITTORIA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.NEGROMANTE;

public final class Negromante extends CreatureOmbra
{

    private Negromante()
    {
        super
        (
    "Negromante", NEGROMANTE, NERA,
"La prima notte individua la Megera, riconosce il Becchino e indica due giocatori che diventano maledetti fino a che il Negromante è " +
          "in gioco. Se all'inizio del giorno sono stati maledetti due o più giocatori maledetti, il Moderatore lo comunica pubblicamente. Il " +
          "mattino successivo, se il Negromante è ancora in gioco, tutti i giocatori di fazione diversa da Negromante vengono eliminati.",
     3, true
        );
    }

    @Override public boolean isNegromante() { return true; }

    @Override public boolean isFazioneNegromante() { return true; }

    @Override public EsitoPartita getEsitoPartita(Partita partita)
    {
        EsitoPartita esito = SCONFITTA;
        if(isPartitaVinta(partita)) esito = VITTORIA;
        return esito;
    }

    public static Ruolo getInstance() { return new Negromante(); }

    private boolean isPartitaVinta(Partita partita)
    {
        return partita.getNumeroGiocatoriVivi() == 1 && partita.isNegromantePresente();
    }

}