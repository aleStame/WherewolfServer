package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita;
import alessandro.stamera.wherewolfserver.classi.fazioni.Inquisizione;
import alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.SCONFITTA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.VITTORIA;

public final class Boia extends Inquisizione
{

    private Boia()
    {
        super
        (
    "Boia", NERA,
"La prima notte viene individuato dall'Inquisitore. Se non è accusato, può segnalare un giocatore durante il ballottaggio: se è un " +
          "mistico o una creatura dell'ombra, i voti di tutti gli altri accusati vengono azzerati alla fine della votazione",
    "Come tutti i Ruoli che giocano per la Vittoria degli Uomini ma che hanno Aura Oscura, avete la difficoltà aggiuntiva di dover rendere "
            + "conto di un’eventuale indagine che la Veggente potrebbe fare su di voi. L’unico che potrebbe scagionarvi è l’Inquisitore, ma anche " +
            "lui (come voi) potrebbe non godere della collaborazione del villaggio, dato il vostro obiettivo.\nLa strategia migliore potrebbe " +
            "essere quella di giocare un profilo basso e scoprire il più possibile, prima di essere messi alle strette. Per certo l’Inquisitore, " +
            "conoscendo la vostra identità, cercherà di comunicare con voi durante il gioco: siate quindi bene attenti ai messaggi che vi mandano " +
            "gli altri giocatori. Nella vostra lotta contro i Mistici, cercate anche di ottenere tutte le informazioni che possono darvi prima di " +
            "metterli al rogo: in fondo, possono tornare utili alla vostra causa."
        );
    }

    @Override public boolean isBoia() { return true; }

    @Override public EsitoPartita getEsitoPartita(Partita partita)
    {
        EsitoPartita esito = super.getEsitoPartita(partita);
        if(esito == VITTORIA) esito = verificaPartitaMistici(partita);
        return esito;
    }

    public static Ruolo getInstance() { return new Boia(); }

    private EsitoPartita verificaPartitaMistici(Partita partita)
    {
        EsitoPartita esito = VITTORIA;
        if(partita.isMisticiPresenti()) esito = SCONFITTA;
        return esito;
    }

}
