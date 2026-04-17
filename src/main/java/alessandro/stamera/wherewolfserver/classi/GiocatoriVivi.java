package alessandro.stamera.wherewolfserver.classi;

public final class GiocatoriVivi extends Giocatori
{

    public Giocatori getBallottaggio()
    {
        Giocatori ballottaggio = new Ballottaggio();
        for(int i = 0; i < 2; i++)
        {
            String nome = getNomeGiocatore(i);
            ballottaggio.aggiungiGiocatore(nome, getRuolo(nome));
        }
        return ballottaggio;
    }

}
