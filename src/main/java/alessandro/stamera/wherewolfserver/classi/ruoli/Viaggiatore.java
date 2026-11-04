package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita;
import alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.*;

public final class Viaggiatore extends Ruolo
{

    private Viaggiatore()
    {
        super
        (
    "Viaggiatore", BIANCA, null, 0, false,
    "La vostra condizione di vittoria è unica e differente da tutte le altre: dovete rimanere vivo. Se non fino alla fine del gioco, " +
            "almeno quanto basta da poter lasciare il villaggio. La scelta più importante sarà quella di decidere se rivelare o meno la vostra " +
            "identità al villaggio. Quello del Viaggiatore è uno dei ruoli verso cui le reazioni saranno più disparate: a volte vi crederanno e vi " +
            "trascureranno del tutto, a volte vi bruceranno subito poiché, non avendo schieramento, potreste comunque essere d’intralcio. Spesso " +
            "potrebbe risultare più efficace mentire sulla vostra identità, fingendo di essere un ruolo che nessuno ha interesse ad eliminare in " +
            "quel momento. Dovete solo capire quale può essere quel ruolo in quel contesto specifico."
        );
    }

    @Override public boolean isViaggiatore() { return true; }

    @Override public EsitoPartita getEsitoPartita(Partita partita)
    {
        EsitoPartita esito = super.getEsitoPartita(partita);
        if(esito == NON_FINITO) esito = getEsitoViaggio(partita);
        return esito;
    }

    private EsitoPartita getEsitoViaggio(Partita partita)
    {
        EsitoPartita esito = SCONFITTA;
        if(partita.isViaggioPartito()) esito = VITTORIA;
        return esito;
    }

    public static Ruolo getInstance() { return new Viaggiatore(); }

}