package alessandro.stamera.wherewolfserver.classi.ruoli.contadini;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Categoria.CREATURE_OMBRA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.*;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione;
import alessandro.stamera.wherewolfserver.classi.fazioni.Lupo;
import alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.TipoContadino.LUPO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Tratto.CREATURA_OMBRA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Tratto.LUPO_MANNARO;

public final class ContadinoLupo extends Contadino
{

    private ContadinoLupo() { super(LUPO); }

    @Override public boolean isContadinoLupo() { return true; }

    @Override public EsitoAttacco attaccoLupi(Ruolo ruolo)
    {
        attivazioneLupo(ruolo.getFazione());
        return FALLITO;
    }

    @Override public EsitoAttacco gildata()
    {
        EsitoAttacco esito = MORTO;
        if(!isLupo()) esito = super.gildata();
        return esito;
    }

    @Override public boolean isLupo() { return isTrattoPresente(LUPO_MANNARO); }

    @Override public EsitoPartita getEsitoPartita(Partita partita)
    {
        EsitoPartita esito = super.getEsitoPartita(partita);
        if(isLupo()) esito = new Lupo(getNome(), getDescrizione(), getLune()).getEsitoPartita(partita);
        return esito;
    }

    @Override public void ripristina()
    {
        super.ripristina();
        eliminaTratti(CREATURA_OMBRA, LUPO_MANNARO);
    }

    @Override public EsitoAttacco vampirizzazione()
    {
        EsitoAttacco esito = MORTO;
        if(!isLupo()) esito = super.vampirizzazione();
        return esito;
    }

    public static Ruolo getInstance() { return new ContadinoLupo(); }

    private void aggiungiTrattiOscuri() { aggiungiTratti(CREATURA_OMBRA, LUPO_MANNARO); }

    private void attivazioneLupo(Fazione fazione)
    {
        aggiungiTrattiOscuri();
        cambiaFazione(fazione);
        cambiaCategoria(CREATURE_OMBRA);
    }

}
