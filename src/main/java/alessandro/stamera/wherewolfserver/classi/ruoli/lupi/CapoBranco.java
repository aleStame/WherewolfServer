package alessandro.stamera.wherewolfserver.classi.ruoli.lupi;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita;
import alessandro.stamera.wherewolfserver.classi.fazioni.Lupo;
import alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.RIUSCITO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.*;

public final class CapoBranco extends Lupo
{

    private CapoBranco()
    {
        super
        (
    "Capo branco",
"La prima notte individua il Traditore e riconosce i lupi del branco. Dalla seconda notte può indicare un giocatore, e questi " +
          "viene ucciso.", 1,
    "Come primo fra i Lupi Mannari, siete il nemico numero uno del Villaggio. Tutte le attenzioni dei giocatori saranno indirizzate a " +
            "scoprire la vostra identità. Inizialmente sarete tentati di giocare nell’ombra e fingervi magari un semplice Contadino, ma " +
            "ricordate che, se nessuno impersonerà un Ruolo importante diffondendo nel Villaggio false informazioni, gli abitanti avranno vita " +
            "facile nello stanarvi.Voi siete il Capo Branco, il Lupo più forte, ma ricordate che nel branco potrebbe esserci anche il Reietto " +
            "che ha lo scopo di uccidervi prima che la partita sia finita, quindi non fidatevi troppo nemmeno dei vostri compagni di branco."
        );
    }

    @Override public boolean isCapoBranco() { return true; }

    @Override public EsitoPartita getEsitoPartita(Partita partita)
    {
        EsitoPartita esito = super.getEsitoPartita(partita);
        if((esito == VITTORIA || esito == NON_FINITO) && (partita.isLupoReiettoVivo() && !partita.isLupoAttaccanteVivo())) esito = SCONFITTA;
        return esito;
    }

    @Override public EsitoAttacco attaccoLupi(Ruolo attaccante) { return RIUSCITO; }

    public static Ruolo getInstance() { return new CapoBranco(); }

}