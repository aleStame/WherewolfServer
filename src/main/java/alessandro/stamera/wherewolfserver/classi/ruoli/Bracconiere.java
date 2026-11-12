package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Potere;
import alessandro.stamera.wherewolfserver.classi.fazioni.Villaggio;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;

public final class Bracconiere extends Villaggio
{

    private final Potere potere;

    private Bracconiere()
    {
        super
        (
    "Bracconiere", BIANCA,
"La prima notte scopre quanti lupi del branco sono in gioco e se è in gioco il Lupo solitario. Durante il turno dei lupi mannari può " +
          "segnalare la sua presenza: se in gioco è rimasto soltanto un lupo mannaro, questi non può attaccare",
     3, false,
    "Avete informazioni abbastanza precise sul numero di Lupi Mannari in gioco e sulla loro natura, ma per tutta la fase iniziale " +
            "della partita non potrete condividerle con il villaggio, in quanto sareste un facile bersaglio per il Lupi. Il vostro obiettivo " +
            "sarà quello di capire quando è rimasto un solo Lupo in gioco, se vi riuscirete potrete tenerlo in scacco e dare tempo al villaggio " +
            "di fare le giuste deduzioni durante il giorno. Ma attenzione, anche se doveste sopravvivere dopo aver usato il vostro potere, non " +
            "potrete essere del tutto certi che ci sia un Lupo Mannaro soltanto. Magari non siete stato ucciso proprio per far credere questo a " +
            "tutto il villaggio."
        );
        potere = new Potere();
    }

    @Override public boolean isBracconiere() { return true; }

    @Override public void utilizzaPotere() { potere.utilizzaPotere(); }

    @Override public boolean isPotereUtilizzato() { return potere.isPotereUtilizzato(); }

    @Override public void riabilitaPotere() { potere.riabilitaPotere(); }

    public static Ruolo getInstance() { return new Bracconiere(); }

}