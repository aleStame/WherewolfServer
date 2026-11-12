package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita;
import alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.FALLITO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.*;

public final class Pazzo extends Ruolo
{

    private Pazzo()
    {
        super
        (
    "Pazzo", BIANCA,
"Se viene eliminato perché ucciso dai lupi mannari, la notte successiva il loro attacco sarà annullato. Vince se viene eliminato " +
          "dall'attacco dei lupi mannari",
     1, false,
    "C'è un solo motivo perché i Lupo vorranno attaccarvi: dovranno credere veramente che siate chi dite di essere, e chi dite di essere " +
            "dovrà essere per loro un nemico prioritario. Sicuramente la scelta migliore è quella di fingervi Veggente o Strega. Ma attenzione: " +
            "se i Lupi capiranno che state bluffando, vi trascureranno e vi lasceranno in vita, mentre se non sarete credibili per il villaggio " +
            "finirete sul rogo. In entrambi i casi non raggiungerete il vostro scopo. Il Pazzo è un ruolo molto difficile da giocare. Dovrete " +
            "vincere ingannando tutti, i Lupi e il villaggio. I primi sopratutto dovranno essere davvero convinti della vostra identità, perché " +
            "attaccandovi perderebbero due turni preziosi durante la notte. Riuscire a vincere con questo ruolo spesso risulta tanto arduo quanto " +
            "gratificante."
        );
    }

    @Override public boolean isPazzo() { return true; }
    
    @Override public EsitoAttacco gildata() { return FALLITO; }

    @Override public EsitoPartita getEsitoPartita(Partita partita)
    {
        EsitoPartita esito = super.getEsitoPartita(partita);
        if(esito == VITTORIA && !partita.isPazzoUcciso()) esito = SCONFITTA;
        return esito;
    }

    public static Ruolo getInstance() { return new Pazzo(); }

}