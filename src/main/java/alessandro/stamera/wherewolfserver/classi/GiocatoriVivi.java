package alessandro.stamera.wherewolfserver.classi;

import java.util.LinkedHashMap;
import java.util.Map;

import static alessandro.stamera.wherewolfserver.classi.EsitoAttacco.RIUSCITO;
import static alessandro.stamera.wherewolfserver.classi.RuoloNullo.getInstance;

public final class GiocatoriVivi extends Giocatori
{

    private static final int NON_TROVATO = -1;

    private Ruolo ruoloAzzeccagarbugli, ruoloInquisitore;

    public GiocatoriVivi()
    {
        annullaSegnalazioneAzzeccagarbugli();
        annullaSegnalazioneInquisitore();
    }

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

    public EsitoAttacco attaccoLupi(Ruolo attaccante, String nome) { return getRuolo(nome).attaccoLupi(attaccante); }

    public void segnalazioneInquisitore(String nome) { ruoloInquisitore = getRuolo(nome); }

    public EsitoAttacco attaccoNosferatu(String nome)
    {
        EsitoAttacco esito = getRuolo(nome).attaccoNosferatu();
        if(esito == RIUSCITO && isAngeloCustode(nome)) resettaAmato();
        return esito;
    }

    public boolean isTrattoPresente(String nome, Tratto tratto) { return getRuolo(nome).isTrattoPresente(tratto); }

    public Fazione getFazione(String nome) { return getRuolo(nome).getFazione(); }

    private Giocatori creaBallottaggio()
    {
        Ballottaggio ballottaggio = new Ballottaggio();
        aggiungiGiocatoriBallottaggio(ballottaggio, getNumeroVotiPrimoClassificato());
        int numeroVoti = getNumeroVotiPrimoClassificato();
        if(ballottaggio.getNumeroGiocatori() < 2 && numeroVoti > 0) aggiungiGiocatoriBallottaggio(ballottaggio, numeroVoti);
        gestisciSegnalazioneAzzeccagarbugli(ballottaggio);
        gestisciSegnalazioneInquisitore(ballottaggio);
        if(ballottaggio.isAmatoPresente()) gestioneAmato(ballottaggio);
        return ballottaggio;
    }

    private void gestisciSegnalazioneAzzeccagarbugli(Ballottaggio ballottaggio)
    {
        int posizioneGiocatoreAzzeccagarbugli = getPosizioneGiocatoreSegnalazioneAzzeccagarbugli();
        if(posizioneGiocatoreAzzeccagarbugli != NON_TROVATO) mandaBallottaggio(ballottaggio, getNomeGiocatore(posizioneGiocatoreAzzeccagarbugli));
        annullaSegnalazioneAzzeccagarbugli();
    }

    private void gestisciSegnalazioneInquisitore(Ballottaggio ballottaggio)
    {
        int posizione = getPosizioneGiocatoreSegnalazioneInquisitore();
        if(posizione != NON_TROVATO) mandaBallottaggio(ballottaggio, getNomeGiocatore(posizione));
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

    private int getPosizioneGiocatoreSegnalazioneAzzeccagarbugli()
    {
        int posizione = NON_TROVATO;
        for(int i = 0; i < getNumeroGiocatori() && posizione == NON_TROVATO; i++) if(ruoloAzzeccagarbugli == getRuolo(getNomeGiocatore(i)))
            posizione = i;
        return posizione;
    }

    private int getPosizioneGiocatoreSegnalazioneInquisitore()
    {
        int posizione = NON_TROVATO;
        for(int i = 0; i < getNumeroGiocatori() && posizione == -1; i++) if(getRuolo(getNomeGiocatore(i)) == ruoloInquisitore) posizione = i;
        return posizione;
    }

    private void annullaSegnalazioneAzzeccagarbugli() { ruoloAzzeccagarbugli = getInstance(); }

    private void annullaSegnalazioneInquisitore() { ruoloInquisitore = getInstance(); }

}
