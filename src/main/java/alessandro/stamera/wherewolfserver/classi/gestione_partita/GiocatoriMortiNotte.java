package alessandro.stamera.wherewolfserver.classi.gestione_partita;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.TipoContadino;

public final class GiocatoriMortiNotte extends Giocatori
{

    public EsitoAttacco progenizzazioneNosferatu(String nome) { return getRuolo(nome).attaccoNosferatu(); }

    public boolean isLupo(String nome) { return getRuolo(nome).isLupo(); }

    public boolean isPazzo(String nome) { return getRuolo(nome).isPazzo(); }

    public boolean isContadino(String nome) { return false; }

    public TipoContadino getTipoContadino(String nome) { return null; }

}
