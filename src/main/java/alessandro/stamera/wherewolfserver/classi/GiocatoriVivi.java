package alessandro.stamera.wherewolfserver.classi;

import java.util.ArrayList;
import java.util.List;
import static alessandro.stamera.wherewolfserver.classi.EsitoAttacco.RIUSCITO;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;

public final class GiocatoriVivi extends Giocatori
{

    public Giocatori getBallottaggio()
    {
        Giocatori ballottaggio = creaBallottaggio();
        annullaVoti();
        ballottaggio.annullaVoti();
        return ballottaggio;
    }

    public void segnalazioneAngeloCustode(String nome) { getRuolo(nome).sceltaAngeloCustode(); }

    public EsitoAttacco attaccoAssassino(String nome) { return getRuolo(nome).attaccoAssassino(); }

    public void segnalazioneAzzeccagarbugli(String nome) { getRuolo(nome).segnalazioneAzzeccagarbugli(); }

    public EsitoAttacco attaccoLupi(Ruolo attaccante, String nome) { return getRuolo(nome).attaccoLupi(attaccante); }

    public void segnalazioneInquisitore(String nome) { getRuolo(nome).segnalazioneInquisitore(); }

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
        for(int i = 0; i < ballottaggio.getNumeroGiocatori(); i++)
        {
            String nome = ballottaggio.getNomeGiocatore(i);
            ballottaggio.getRuolo(nome).annullaSegnalazioneInquisitore();
            ballottaggio.getRuolo(nome).annullaSegnalazioneAzzeccagarbugli();
        }
        return ballottaggio;
    }

    private void gestisciSegnalazioni(Ballottaggio ballottaggio)
    {
        int posizioneSegnalatoAzzeccagarbugli = getPosizioneSegnalatoAzzeccagarbugli(), posizioneInquisito = getPosizioneInquisito();
        if(posizioneSegnalatoAzzeccagarbugli != -1) gestisciSegnalazioneAzzeccagarbugli(ballottaggio, posizioneSegnalatoAzzeccagarbugli);
        if(posizioneInquisito != -1) gestisciSegnalazioneInquisitore(ballottaggio, posizioneInquisito);
    }

    private void gestisciSegnalazioneAzzeccagarbugli(Giocatori ballottaggio, int posizione)
    {
        String nome = getNomeGiocatore(posizione);
        Ruolo ruolo = getRuolo(nome);
        if(!ruolo.isCriminale()) mandaBallottaggio(ballottaggio, nome);
        ruolo.annullaSegnalazioneAzzeccagarbugli();
    }

    private void gestisciSegnalazioneInquisitore(Ballottaggio ballottaggio, int posizione)
    {
        String nome = getNomeGiocatore(posizione);
        mandaBallottaggio(ballottaggio, nome);
        ballottaggio.getRuolo(nome).annullaSegnalazioneInquisitore();
    }

    private int getPosizioneSegnalatoAzzeccagarbugli()
    {
        int posizione = -1;
        for(int i = 0; i < getNumeroGiocatori() && posizione == -1; i++) if(getRuolo(getNomeGiocatore(i)).isSegnalatoAzzeccagarbugli())
            posizione = i;
        return posizione;
    }

    private int getPosizioneInquisito()
    {
        int posizione = -1;
        for(int i = 0; i < getNumeroGiocatori() && posizione == -1; i++) if(getRuolo(getNomeGiocatore(i)).isInquisito()) posizione = i;
        return posizione;
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

    private void mandaBallottaggio(Giocatori ballottaggio, String nome)
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
        for(String nome : estraiGiocatori(numeroVoti)) mandaBallottaggio(ballottaggio, nome);
    }

    private String[] estraiGiocatori(int numeroVoti)
    {
        List<String> nomi = new ArrayList<>();
        for(int i = 0; i < getNumeroGiocatori(); i++)
        {
            String nome = getNomeGiocatore(i);
            if(numeroVoti == getNumeroVoti(nome)) nomi.add(nome);
        }
        String[] risultato = new String[nomi.size()];
        for(int i = 0; i < risultato.length; i++) risultato[i] = nomi.get(i);
        return risultato;
    }

    private int getNumeroVotiPrimoClassificato() { return getNumeroVoti(getNomeGiocatore(0)); }

}
