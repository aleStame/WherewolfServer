package alessandro.stamera.wherewolfserver.classi;

public final class Ballottaggio extends Giocatori
{

    public boolean isAmatoPresente()
    {
        boolean esito = false;
        for(int i = 0; i < getNumeroGiocatori() && !esito; i++) esito = getRuolo(getNomeGiocatore(i)).isAmato();
        return esito;
    }

}
