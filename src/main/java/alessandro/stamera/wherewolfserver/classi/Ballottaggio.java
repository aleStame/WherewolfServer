package alessandro.stamera.wherewolfserver.classi;

public final class Ballottaggio extends Giocatori
{

    public boolean isAmatoPresente()
    {
        boolean esito = false;
        for(int i = 0; i < getNumeroGiocatori() && !esito; i++) esito = getRuolo(getNomeGiocatore(i)).isAmato();
        return esito;
    }

    public String getNomeAmato()
    {
        int posizione = -1;
        for(int i = 0; i < getNumeroGiocatori() && posizione == -1; i++) if(getRuolo(getNomeGiocatore(i)).isAmato()) posizione = i;
        return getNomeGiocatore(posizione);
    }

}
