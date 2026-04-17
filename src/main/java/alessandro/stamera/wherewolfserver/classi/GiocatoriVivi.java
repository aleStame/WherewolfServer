package alessandro.stamera.wherewolfserver.classi;

public final class GiocatoriVivi extends Giocatori
{

    public Giocatori getBallottaggio()
    {
        Giocatori ballottaggio = new Ballottaggio();
        for(int i = 0; i < 2; i++)
        {
            String nome = getNomeGiocatore(i);
            if(getNumeroVoti(nome) > 0) ballottaggio.aggiungiGiocatore(nome, getRuolo(nome));
        }
        for(int i = 0; i < getNumeroGiocatori(); i++) annullaVoti(getNomeGiocatore(i));
        for(int i = 0; i < ballottaggio.getNumeroGiocatori(); i++) ballottaggio.annullaVoti(ballottaggio.getNomeGiocatore(i));
        return ballottaggio;
    }

}
