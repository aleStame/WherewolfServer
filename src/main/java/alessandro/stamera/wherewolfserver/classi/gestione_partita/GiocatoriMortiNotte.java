package alessandro.stamera.wherewolfserver.classi.gestione_partita;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.TipoContadino;

public final class GiocatoriMortiNotte extends Giocatori
{

    public EsitoAttacco progenizzazioneNosferatu(String nome) { return getGiocatore(nome).progenizzazioneNosferatu(); }

    public boolean isLupo(String nome) { return getGiocatore(nome).isLupo(); }

    public boolean isPazzo(String nome) { return getGiocatore(nome).getRuolo().isPazzo(); }

    public boolean isContadino(String nome)
    {
        boolean esito = false;
        if(isPresente(nome))  esito = getGiocatore(nome).isContadino();
        return esito;
    }

    public TipoContadino getTipoContadino(String nome) { return getGiocatore(nome).getRuolo().getTipoContadino(); }

    public boolean isPossedutoPresente() { return getPosizionePosseduto() != -1; }

    public String getNomePosseduto() { return getNomeGiocatore(getPosizionePosseduto()); }

    private int getPosizionePosseduto()
    {
        int posizione = -1;
        for(int i = 0; i < getNumeroGiocatori() && posizione == -1; i++) if(getGiocatore(getNomeGiocatore(i)).getRuolo().isPosseduto()) posizione = i;
        return posizione;
    }

}
