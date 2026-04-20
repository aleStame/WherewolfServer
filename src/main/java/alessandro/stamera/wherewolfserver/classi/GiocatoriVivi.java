package alessandro.stamera.wherewolfserver.classi;

import java.util.LinkedHashMap;
import java.util.Map;

public final class GiocatoriVivi extends Giocatori
{

    public Giocatori getBallottaggio()
    {
        Giocatori ballottaggio = creaBallottaggio();
        this.annullaVoti();
        ballottaggio.annullaVoti();
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
        Ballottaggio ballottaggio = new Ballottaggio();
        aggiungiGiocatoriBallottaggio(ballottaggio, getNumeroVotiPrimoClassificato());
        for(int i = 0; i < ballottaggio.getNumeroGiocatori(); i++)
        {
            String nome = ballottaggio.getNomeGiocatore(i);
            System.out.println(nome + " " + ballottaggio.getNumeroVoti(nome));
        }
        System.out.println();
        int numeroVoti = getNumeroVotiPrimoClassificato();
        if(ballottaggio.getNumeroGiocatori() < 2 && numeroVoti > 0) aggiungiGiocatoriBallottaggio(ballottaggio, numeroVoti);
        for(int i = 0; i < ballottaggio.getNumeroGiocatori(); i++)
        {
            String nome = ballottaggio.getNomeGiocatore(i);
            System.out.println(nome + " " + ballottaggio.getNumeroVoti(nome));
        }
        System.out.println();
        if(ballottaggio.isAmatoPresente()) if(isAngeloCustodePresente()) scambioAngeloCustodeAmato(ballottaggio);
        for(int i = 0; i < ballottaggio.getNumeroGiocatori(); i++)
        {
            String nome = ballottaggio.getNomeGiocatore(i);
            System.out.println(nome + " " + ballottaggio.getNumeroVoti(nome));
        }
        System.out.println();
        return ballottaggio;
    }

    private void scambioAngeloCustodeAmato(Ballottaggio ballottaggio)
    {
        String nomeAngeloCustode = getNomeAngeloCustode(), nomeAmato = ballottaggio.getNomeAmato();
        Ruolo angeloCustode = getRuolo(nomeAngeloCustode), amato = ballottaggio.getRuolo(nomeAmato);
        eliminaGiocatore(nomeAngeloCustode);
        ballottaggio.aggiungiGiocatore(nomeAngeloCustode, angeloCustode);
        ballottaggio.eliminaGiocatore(nomeAmato);
        aggiungiGiocatore(nomeAmato, amato);
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
        for(String nome : nomi) if(numeroVoti == getNumeroVoti(nome))
        {
            giocatori.put(nome, getRuolo(nome));
            eliminaGiocatore(nome);
        }
        return giocatori;
    }

    private int getNumeroVotiPrimoClassificato() { return getNumeroVoti(getNomeGiocatore(0)); }

}
