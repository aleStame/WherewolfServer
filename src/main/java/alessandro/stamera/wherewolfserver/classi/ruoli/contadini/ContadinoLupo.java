package alessandro.stamera.wherewolfserver.classi.ruoli.contadini;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.*;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita;
import alessandro.stamera.wherewolfserver.classi.fazioni.Lupo;
import alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.TipoContadino.LUPO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Tratto.LUPO_MANNARO;

public final class ContadinoLupo extends Contadino
{

    private ContadinoLupo() { super(LUPO); }

    @Override public boolean isContadinoLupo() { return true; }

    @Override public EsitoAttacco attaccoLupi(Ruolo ruolo) { return CONTADINO_LUPO_BECCATO; }

    @Override public boolean isLupo() { return isTrattoPresente(LUPO_MANNARO); }

    @Override public EsitoPartita getEsitoPartita(Partita partita)
    {
        EsitoPartita esito = super.getEsitoPartita(partita);
        if(isLupo()) esito = new Lupo(getNome(), getDescrizione(), getLune()).getEsitoPartita(partita);
        return esito;
    }

    public static Ruolo getInstance() { return new ContadinoLupo(); }

}
