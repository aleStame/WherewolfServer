package alessandro.stamera.wherewolfserver.classi.gestione_partita;

public final class GiocatoriEliminati extends Giocatori
{

    public boolean isBardoPresente()
    {
        boolean esito = false;
        for(int i = 0; i < getNumeroGiocatori() && !esito; i++) esito = (getRuolo(getNomeGiocatore(i)).isBardo());
        return esito;
    }

}