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
        annullaVoti();
        ballottaggio.annullaVoti();
        return ballottaggio;
    }

    //private Giocatori creaBallottaggio() {}

}
