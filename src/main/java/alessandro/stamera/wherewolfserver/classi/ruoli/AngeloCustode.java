package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita;
import alessandro.stamera.wherewolfserver.classi.fazioni.Amanti;
import alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.*;

public final class AngeloCustode extends Amanti
{

    private AngeloCustode()
    {
        super
        (
      "Angelo custode",
  "La prima notte indica un giocatore, l'Amato, che viene avvisato. Se quel giocatore dovesse essere accusato, l'Angelo custode sarà " +
            "accusato al suo posto. Se dovesse essere attaccato e ucciso durante la notte, sarà invece attaccato, avvisato e ucciso l'Angelo " +
            "custode."
        );
    }

    @Override public boolean isAmato() { return false; }

    @Override public boolean isAngeloCustode() { return true; }

    @Override public void sceltaAngeloCustode() { }

    @Override public EsitoPartita getEsitoPartita(Partita partita)
    {
        EsitoPartita esito = super.getEsitoPartita(partita);
        if(esito == NON_FINITO)
        {
            esito = isPartitaVintaConAmato(partita);
            if(partita.isViaggiatoreAmato() && partita.isViaggioPartito()) esito = SCONFITTA;
        }
        return esito;
    }

    public static Ruolo getInstance() { return new AngeloCustode(); }

    private EsitoPartita isPartitaVintaConAmato(Partita partita)
    {
        EsitoPartita esito = SCONFITTA;
        if(partita.isAmatoVivo()) esito = VITTORIA;
        return esito;
    }

}