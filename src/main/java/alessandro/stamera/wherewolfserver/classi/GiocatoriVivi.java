package alessandro.stamera.wherewolfserver.classi;

import java.util.LinkedHashMap;
import java.util.Map;

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
        Map<String, Ruolo> primoPosto = estraiGiocatori(getNumeroVoti(getNomeGiocatore(0)));
        for(String nome : primoPosto.keySet()) ballottaggio.aggiungiGiocatore(nome, primoPosto.get(nome));
        int numeroVoti = getNumeroVoti(getNomeGiocatore(0));
        if(ballottaggio.getNumeroGiocatori() < 2 && numeroVoti > 0)
        {
            Map<String, Ruolo> secondoPosto = estraiGiocatori(numeroVoti);
            for(String nome : secondoPosto.keySet()) ballottaggio.aggiungiGiocatore(nome, secondoPosto.get(nome));
        }
        ballottaggio.annullaVoti();
        return ballottaggio;
    }

    private Map<String, Ruolo> estraiGiocatori(int numeroVoti)
    {
        Map<String, Ruolo> giocatori = new LinkedHashMap<>();
        for(int i = 0; i < getNumeroGiocatori(); i++)
        {
            String nome = getNomeGiocatore(i);
            if(numeroVoti == getNumeroVoti(nome))
            {
                giocatori.put(nome, getRuolo(nome));
                eliminaGiocatore(nome);
            }
        }
        return giocatori;
    }

}
