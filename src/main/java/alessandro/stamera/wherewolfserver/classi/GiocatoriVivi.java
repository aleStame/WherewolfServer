package alessandro.stamera.wherewolfserver.classi;

public final class GiocatoriVivi extends Giocatori
{

    public Giocatori getBallottaggio()
    {
        Giocatori ballottaggio = creaBallottaggio();
        annullaVoti();
        return ballottaggio;
    }

    private Giocatori creaBallottaggio()
    {
        Giocatori ballottaggio = new Ballottaggio();
        int numeroVoti = getNumeroVoti(getNomeGiocatore(0));
        for(int i = 0; i < getNumeroGiocatori(); i++)
        {
            String nome = getNomeGiocatore(i);
            if(numeroVoti == getNumeroVoti(nome))
            {
                ballottaggio.aggiungiGiocatore(nome, getRuolo(nome));
                eliminaGiocatore(nome);
            }
        }
        numeroVoti = getNumeroVoti(getNomeGiocatore(0));
        if(ballottaggio.getNumeroGiocatori() < 2 && numeroVoti > 0)
        {
            for(int i = 0; i < getNumeroGiocatori(); i++)
            {
                String nome = getNomeGiocatore(i);
                if(numeroVoti == getNumeroVoti(nome))
                {
                    ballottaggio.aggiungiGiocatore(nome, getRuolo(nome));
                    eliminaGiocatore(nome);
                }
            }
        }
        ballottaggio.annullaVoti();
        return ballottaggio;
    }

}
