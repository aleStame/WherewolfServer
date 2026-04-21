package alessandro.stamera.wherewolfserver.classi;

import java.util.LinkedHashMap;
import java.util.Map;
import static alessandro.stamera.wherewolfserver.classi.RuoloNullo.getInstance;

public final class GiocatoriVivi extends Giocatori
{

    private Ruolo ruoloAzzeccagarbugli;

    public GiocatoriVivi() { ruoloAzzeccagarbugli = getInstance(); }

    public Giocatori getBallottaggio()
    {
        Giocatori ballottaggio = creaBallottaggio();
        this.annullaVoti();
        ballottaggio.annullaVoti();

        return ballottaggio;
    }

    public void segnalazioneAngeloCustode(String nome) { getRuolo(nome).sceltaAngeloCustode(); }

    public EsitoAttacco attaccoAssassino(String nome) { return getRuolo(nome).attaccoAssassino(); }

    public void segnalazioneAzzeccagarbugli(String nome) { ruoloAzzeccagarbugli = getRuolo(nome); }

    private Giocatori creaBallottaggio()
    {
        Ballottaggio ballottaggio = new Ballottaggio();
        aggiungiGiocatoriBallottaggio(ballottaggio, getNumeroVotiPrimoClassificato());
        int numeroVoti = getNumeroVotiPrimoClassificato();
        if(ballottaggio.getNumeroGiocatori() < 2 && numeroVoti > 0) aggiungiGiocatoriBallottaggio(ballottaggio, numeroVoti);
        if(ballottaggio.isAmatoPresente()) gestioneAmato(ballottaggio);
        boolean fatto = false;
        for(int i = 0; i < getNumeroGiocatori() && !fatto; i++)
        {
            String nome = getNomeGiocatore(i);
            Ruolo ruolo = getRuolo(nome);
            if(ruoloAzzeccagarbugli == ruolo)
            {
                eliminaGiocatore(nome);
                ballottaggio.aggiungiGiocatore(nome, ruolo);
                fatto = true;
            }
        }
        ruoloAzzeccagarbugli = getInstance();
        return ballottaggio;
    }

    private void gestioneAmato(Ballottaggio ballottaggio)
    {
        spostamentoAmato(ballottaggio);
        if(isAngeloCustodePresente() && !ballottaggio.isAngeloCustodePresente()) spostamentoAngeloCustode(ballottaggio);
    }

    private void spostamentoAngeloCustode(Ballottaggio ballottaggio)
    {
        String nome = getNomeAngeloCustode();
        Ruolo ruolo = getRuolo(nome);
        eliminaGiocatore(nome);
        ballottaggio.aggiungiGiocatore(nome, ruolo);
    }

    private void spostamentoAmato(Ballottaggio ballottaggio)
    {
        String nome = ballottaggio.getNomeAmato();
        Ruolo ruolo = ballottaggio.getRuolo(nome);
        ballottaggio.eliminaGiocatore(nome);
        aggiungiGiocatore(nome, ruolo);
    }

    private void aggiungiGiocatoriBallottaggio(Giocatori ballottaggio, int numeroVoti)
    {
        System.out.println(numeroVoti);
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
