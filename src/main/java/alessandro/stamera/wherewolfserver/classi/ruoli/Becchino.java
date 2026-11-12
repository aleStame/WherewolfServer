package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita;
import alessandro.stamera.wherewolfserver.classi.fazioni.Villaggio;
import alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.NEGROMANTE;

public class Becchino extends Villaggio
{

    private Becchino()
    {
        super
        (
    "Becchino", BIANCA,
"La prima notte scopre se il Negromante è in gioco. Durante il turno del Negromante sceglie se riconoscerlo. Se lo fa, la sua fazione " +
          "diventa Negromante. Altrimenti, ogni mattino, se sono stati eliminati giocatori maledetti dal mattino precedente, il Moderatore lo " +
          "annuncia pubblicamente",
     3, false,
    "In ogni partita che farete con questo Ruolo avrete una scelta. Potrete scegliere se cercare di aiutare il villaggio contro il " +
            "rituale messo in atto dal Negromante, o collaborare con lui per realizzarlo. Sappiate che, comunque, il villaggio non si fiderà mai " +
            "completamente di voi, perché non potrà essere certo della vostra lealtà. Nel caso vi siate schierati con il Negromante, la prima " +
            "mossa dovrà per forza essere capire dalle sue parole e dalle sue azioni chi siano i due giocatori Maledetti, poiché, senza saperlo, " +
            "non potrete essergli di nessun aiuto."
        );
    }

    @Override public boolean isBecchino() { return true; }

    @Override public boolean isFazioneNegromante() { return getFazione() == NEGROMANTE; }

    @Override public EsitoPartita getEsitoPartita(Partita partita)
    {
        EsitoPartita esito = super.getEsitoPartita(partita);
        if(isFazioneNegromante()) esito = partita.isNegromanteVincitore();
        return esito;
    }

    public static Ruolo getInstance() { return new Becchino(); }

}