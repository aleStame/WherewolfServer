package alessandro.stamera.wherewolfserver.classi.gestione_partita;

public final class GiocatoriEliminati extends Giocatori
{

    public boolean isBardoPresente()
    {
        boolean esito = false;
        for(int i = 0; i < getNumeroGiocatori() && !esito; i++) esito = isBardo(i);
        return esito;
    }

    private boolean isBardo(int posizione) { return getRuolo(getNomeGiocatore(posizione)).isBardo(); }

}