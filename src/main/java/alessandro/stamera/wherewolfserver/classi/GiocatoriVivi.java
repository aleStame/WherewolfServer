package alessandro.stamera.wherewolfserver.classi;

import java.util.LinkedHashMap;
import java.util.Map;
import static alessandro.stamera.wherewolfserver.classi.EsitoAttacco.RIUSCITO;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
import static alessandro.stamera.wherewolfserver.classi.RuoloNullo.getInstance;

public final class GiocatoriVivi extends Giocatori
{

    private String nomeAzzeccagarbugli, nomeInquisitore;

    public GiocatoriVivi()
    {
        annullaSegnalazioneAzzeccagarbugli();
        annullaSegnalazioneInquisitore();
    }

    public Giocatori getBallottaggio()
    {
        Giocatori ballottaggio = creaBallottaggio();
        annullaVoti();
        ballottaggio.annullaVoti();
        return ballottaggio;
    }

    public void segnalazioneAngeloCustode(String nome) { getRuolo(nome).sceltaAngeloCustode(); }

    public EsitoAttacco attaccoAssassino(String nome) { return getRuolo(nome).attaccoAssassino(); }

    public void segnalazioneAzzeccagarbugli(String nome) { nomeAzzeccagarbugli = nome; }

    public EsitoAttacco attaccoLupi(Ruolo attaccante, String nome) { return getRuolo(nome).attaccoLupi(attaccante); }

    public void segnalazioneInquisitore(String nome) { nomeInquisitore = nome; }

    public EsitoAttacco attaccoNosferatu(String nome)
    {
        EsitoAttacco esito = getRuolo(nome).attaccoNosferatu();
        gestisciResetAmato(nome, esito);
        return esito;
    }

    public boolean isTrattoPresente(String nome, Tratto tratto) { return getRuolo(nome).isTrattoPresente(tratto); }

    public Fazione getFazione(String nome) { return getRuolo(nome).getFazione(); }

    public EsitoAttacco attaccoVampiro(String nome)
    {
        EsitoAttacco esito = getRuolo(nome).vampirizzazione();
        gestisciResetAmato(nome, esito);
        return esito;
    }

    public void attaccoPosseduto(String nome)
    {
        eliminaGiocatore(nome);
        aggiungiGiocatore(nome, FACTORY.getRuolo("Posseduto"));
        resettaAmato();
    }

    public boolean isPosseduto(String nome) { return getRuolo(nome).isPosseduto(); }

    @Override public int getNumeroVoti(String nome)
    {
        Ruolo ruolo = getRuolo(nome);
        if(nomeAzzeccagarbugli.equals(nome)) ruolo.annullaVoti();
        return ruolo.getNumeroVoti();
    }

    private void gestisciResetAmato(String nome, EsitoAttacco esito)
    {
        if(esito == RIUSCITO && isAngeloCustode(nome)) resettaAmato();
    }

    private Giocatori creaBallottaggio()
    {
        Ballottaggio ballottaggio = new Ballottaggio();
        aggiungiGiocatoriBallottaggio(ballottaggio, getNumeroVotiPrimoClassificato());
        int numeroVoti = getNumeroVotiPrimoClassificato();
        if(ballottaggio.getNumeroGiocatori() < 2 && numeroVoti > 0) aggiungiGiocatoriBallottaggio(ballottaggio, numeroVoti);
        gestisciSegnalazioni(ballottaggio);
        if(ballottaggio.isAmatoPresente()) gestioneAmato(ballottaggio);
        return ballottaggio;
    }

    private void gestisciSegnalazioni(Ballottaggio ballottaggio)
    {
        if(!nomeAzzeccagarbugli.isBlank()) gestisciSegnalazioneAzzeccagarbugli(ballottaggio);
        if(!nomeInquisitore.isBlank()) gestisciSegnalazioneInquisitore(ballottaggio);
    }

    private void gestisciSegnalazioneAzzeccagarbugli(Ballottaggio ballottaggio)
    {
        if(isPresente(nomeAzzeccagarbugli) && !getRuolo(nomeAzzeccagarbugli).isCriminale()) mandaBallottaggio(ballottaggio, nomeAzzeccagarbugli);
        annullaSegnalazioneAzzeccagarbugli();
    }

    private void gestisciSegnalazioneInquisitore(Ballottaggio ballottaggio)
    {
        if(isPresente(nomeInquisitore)) mandaBallottaggio(ballottaggio, nomeInquisitore);
        annullaSegnalazioneInquisitore();
    }

    private void gestioneAmato(Ballottaggio ballottaggio)
    {
        spostamentoAmato(ballottaggio);
        if(isAngeloCustodePresente() && !ballottaggio.isAngeloCustodePresente()) spostamentoAngeloCustode(ballottaggio);
    }

    private void spostamentoAngeloCustode(Ballottaggio ballottaggio)
    {
        mandaBallottaggio(ballottaggio, getNomeAngeloCustode());
    }

    private void mandaBallottaggio(Ballottaggio ballottaggio, String nome)
    {
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
        for(int i = 0; i < ballottaggio.getNumeroGiocatori(); i++) System.out.println(ballottaggio.getNomeGiocatore(i));
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

    private void annullaSegnalazioneAzzeccagarbugli() { nomeAzzeccagarbugli = new String(); }

    private void annullaSegnalazioneInquisitore() { nomeInquisitore = new String(); }

}
