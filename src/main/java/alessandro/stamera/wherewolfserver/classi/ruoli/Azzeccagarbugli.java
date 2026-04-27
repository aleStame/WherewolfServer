package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita;
import alessandro.stamera.wherewolfserver.classi.fazioni.Citta;
import alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.SCONFITTA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.VITTORIA;

public final class Azzeccagarbugli extends Citta
{

    private Azzeccagarbugli()
    {
        super
        (
    "Azzeccagarbugli", BIANCA,
"Può votare al ballottaggio anche se è accusato e può segnalare un altro giocatore durante le accuse: se la sua fazione è Città o " +
          "Criminali, i voti che riceve vengono azzerati, altrimenti sarà accusato a prescindere dai voti ricevuti."
        );
    }

    @Override public boolean isAzzeccagarbugli() { return true; }

    @Override public EsitoPartita getEsitoPartita(Partita partita)
    {
        EsitoPartita esito = super.getEsitoPartita(partita);
        if(isPartitaSconfitta(partita)) esito = SCONFITTA;
        else if(isPartitaVinta(partita)) esito = VITTORIA;
        return esito;
    }

    public static Ruolo getInstance() { return new Azzeccagarbugli(); }

    private boolean isPartitaSconfitta(Partita partita)
    {
        return getNumeroGiocatoriVivi(partita) == 0 || partita.isSoloCreatureOmbra();
    }

    private boolean isPartitaVinta(Partita partita)
    {
        return partita.isNoCreatureOmbra() && getNumeroGiocatoriVivi(partita) >= 1;
    }

    private int getNumeroGiocatoriVivi(Partita partita) { return partita.getNumeroGiocatoriVivi(); }

}
