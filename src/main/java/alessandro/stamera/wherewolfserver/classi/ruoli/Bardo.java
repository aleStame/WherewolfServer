package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita;
import alessandro.stamera.wherewolfserver.classi.fazioni.Villaggio;
import alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.SCONFITTA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.VITTORIA;

public final class Bardo extends Villaggio
{

    private Bardo()
    {
        super
        (
    "Bardo", BIANCA,
"Ogni mattina, se la Veggente quella notte ha scoperto un'aura bianca, il Moderatore la comunica pubblicamente.", 1, false
        );
    }

    @Override public boolean isBardo() { return true; }

    @Override public EsitoPartita getEsitoPartita(Partita partita)
    {
        EsitoPartita esito = super.getEsitoPartita(partita);
        if(isPartitaSconfitta(partita)) esito = SCONFITTA;
        else if(isPartitaVinta(partita)) esito = VITTORIA;
        return esito;
    }

    private boolean isPartitaVinta(Partita partita)
    {
        return partita.getNumeroGiocatoriVivi() > 1 && partita.isNoCreatureOmbra();
    }

    private boolean isPartitaSconfitta(Partita partita)
    {
        return partita.isSoloCreatureOmbra() || (partita.isNoGuardie() && partita.getNumeroCriminali() >= 1);
    }

    public static Ruolo getInstance() { return new Bardo(); }

}
