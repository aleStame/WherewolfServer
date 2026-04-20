package alessandro.stamera.wherewolfserver.classi;

public final class Ballottaggio extends Giocatori
{

    public boolean isAmatoPresente() { return getPosizioneAmato() != -1; }

    public String getNomeAmato() { return getNomeGiocatore(getPosizioneAmato()); }

    private int getPosizioneAmato()
    {
        int posizione = -1;
        for(int i = 0; i < getNumeroGiocatori() && posizione == -1; i++) if(getRuolo(getNomeGiocatore(i)).isAmato()) posizione = i;
        return posizione;
    }

}
