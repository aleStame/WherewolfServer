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
        aggiungiGiocatoriBallottaggio(ballottaggio, getNumeroVotiPrimoClassificato());
        int numeroVoti = getNumeroVotiPrimoClassificato();
        if(ballottaggio.getNumeroGiocatori() < 2 && numeroVoti > 0) aggiungiGiocatoriBallottaggio(ballottaggio, numeroVoti);
        ballottaggio.annullaVoti();
        return ballottaggio;
    }

    private void aggiungiGiocatoriBallottaggio(Giocatori ballottaggio, int numeroVoti)
    {
        Map<String, Ruolo> giocatori = estraiGiocatori(numeroVoti);
        for(String nome : giocatori.keySet()) ballottaggio.aggiungiGiocatore(nome, giocatori.get(nome));
    }

    private Map<String, Ruolo> estraiGiocatori(int numeroVoti)
    {
        Map<String, Ruolo> giocatori = new LinkedHashMap<>();
        String[] nomi = new String[getNumeroGiocatori()];
        for(int i = 0; i < nomi.length; i++) nomi[i] = getNomeGiocatore(i);
        for(String nome : nomi)
        {
            if(numeroVoti == getNumeroVoti(nome))
            {
                giocatori.put(nome, getRuolo(nome));
                eliminaGiocatore(nome);
            }
        }
        return giocatori;
    }

    private int getNumeroVotiPrimoClassificato() { return getNumeroVoti(getNomeGiocatore(0)); }

    public void segnalazioneAngeloCustode(String nome) { }

    public boolean isAmato(String nome) { return false; }

}
