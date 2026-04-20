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

    public void segnalazioneAngeloCustode(String nome) { getRuolo(nome).sceltaAngeloCustode(); }

    public boolean isAngeloCustodePresente()
    {
        boolean esito = false;
        for(int i = 0; i < getNumeroGiocatori() && !esito; i++) esito = isAngeloCustode(getNomeGiocatore(i));
        return esito;
    }

    public String getNomeAngeloCustode()
    {
        int posizione = -1;
        for(int i = 0; i < getNumeroGiocatori() && posizione == -1; i++) if(isAngeloCustode(getNomeGiocatore(i))) posizione = i;
        return getNomeGiocatore(posizione);
    }

    private Giocatori creaBallottaggio()
    {
        Giocatori ballottaggio = new Ballottaggio();
        aggiungiGiocatoriBallottaggio(ballottaggio, getNumeroVotiPrimoClassificato());
        int numeroVoti = getNumeroVotiPrimoClassificato();
        if(ballottaggio.getNumeroGiocatori() < 2 && numeroVoti > 0) aggiungiGiocatoriBallottaggio(ballottaggio, numeroVoti);
        ballottaggio.annullaVoti();
        int posizioneAmato = -1;
        for(int i = 0; posizioneAmato == -1 && i < ballottaggio.getNumeroGiocatori(); i++)
        {
            String nome = ballottaggio.getNomeGiocatore(i);
            if(ballottaggio.isAmato(nome)) posizioneAmato = i;
        }
        int posizioneAngeloCustode = -1;
        for(int i = 0; posizioneAngeloCustode == -1 && i < getNumeroGiocatori(); i++)
        {
            String nome =  getNomeGiocatore(i);
            if(isAngeloCustode(nome)) posizioneAngeloCustode = i;
        }
        if(posizioneAmato != -1 && posizioneAngeloCustode != -1)
        {
            String nomeAmato = ballottaggio.getNomeGiocatore(posizioneAmato);
            Ruolo amato = ballottaggio.getRuolo(nomeAmato);
            String nomeAngeloCustode = getNomeGiocatore(posizioneAngeloCustode);
            Ruolo angeloCustode = getRuolo(nomeAngeloCustode);
            ballottaggio.eliminaGiocatore(nomeAmato);
            ballottaggio.aggiungiGiocatore(nomeAngeloCustode, angeloCustode);
            aggiungiGiocatore(nomeAmato, amato);
            eliminaGiocatore(nomeAngeloCustode);
        }
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

}
