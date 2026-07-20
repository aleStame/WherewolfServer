package alessandro.stamera.wherewolfserver.classi.gestione_partita;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.TipoContadino;

public final class GiocatoriMortiNotte extends Giocatori
{

    public EsitoAttacco progenizzazioneNosferatu(String nome) { return getRuolo(nome).attaccoNosferatu(); }

    public boolean isLupo(String nome) { return getRuolo(nome).isLupo(); }

    public boolean isPazzo(String nome) { return getRuolo(nome).isPazzo(); }

    public boolean isContadino(String nome)
    {
        boolean esito = false;
        if(isPresente(nome))  esito = getRuolo(nome).isContadino();
        return esito;
    }

    public TipoContadino getTipoContadino(String nome) { return getRuolo(nome).getTipoContadino(); }

    public boolean isPossedutoPresente()
    {
        int posizione = -1;
        for(int i = 0; i < getNumeroGiocatori() && posizione == -1; i++) if(getRuolo(getNomeGiocatore(i)).isPosseduto()) posizione = i;
        return posizione != -1;
    }

    public String getNomePosseduto() { return null; }

}
