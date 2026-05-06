package alessandro.stamera.wherewolfserver.classi.ruoli.lupi;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita;
import alessandro.stamera.wherewolfserver.classi.fazioni.Lupo;
import alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.VITTORIA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.SCONFITTA;

public final class CapoBranco extends Lupo
{

    private CapoBranco()
    {
        super
        (
    "Capo branco",
"La prima notte individua il Traditore e riconosce i lupi del branco. Dalla seconda notte può indicare un giocatore, e questi " +
          "viene ucciso.",
     1
        );
    }

    @Override public boolean isCapoBranco() { return true; }

    @Override public EsitoPartita getEsitoPartita(Partita partita)
    {
        EsitoPartita esito = super.getEsitoPartita(partita);
        if(isPresentiSoloLupiConSenzaFazione(partita)) esito = VITTORIA;
        else if(isNessunLupoVivo(partita)) esito = SCONFITTA;
        return esito;
    }

    public static Ruolo getInstance() { return new CapoBranco(); }

    private boolean isPresentiSoloLupiConSenzaFazione(Partita partita)
    {
        return partita.getNumeroGiocatoriVivi() == getNumeroLupiConSenzaFazione(partita);
    }

    private int getNumeroLupiConSenzaFazione(Partita partita)
    {
        return partita.getNumeroLupiVivi() + partita.getNumeroSenzaFazioneVivi();
    }

    private boolean isNessunLupoVivo(Partita partita) { return partita.getNumeroLupiVivi() == 0; }

}