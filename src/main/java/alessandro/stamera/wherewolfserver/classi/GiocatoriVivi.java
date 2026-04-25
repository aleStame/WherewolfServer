package alessandro.stamera.wherewolfserver.classi;

import java.util.ArrayList;
import java.util.List;
import static alessandro.stamera.wherewolfserver.classi.EsitoAttacco.RIUSCITO;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;

public final class GiocatoriVivi extends Giocatori
{

    private static final int NON_TROVATO = -1;

    public Giocatori getBallottaggio()
    {
        Giocatori ballottaggio = creaBallottaggio();
        annullaVoti();
        ballottaggio.annullaVoti();
        return ballottaggio;
    }

    public void segnalazioneAngeloCustode(String nome) { getRuolo(nome).sceltaAngeloCustode(); }

    public EsitoAttacco attaccoAssassino(String nome) { return getRuolo(nome).attaccoAssassino(); }

    public EsitoAttacco attaccoLupi(Ruolo attaccante, String nome) { return getRuolo(nome).attaccoLupi(attaccante); }

    public EsitoAttacco attaccoNosferatu(String nome)
    {
        EsitoAttacco esito = attaccoNosferatuRuolo(nome);
        gestisciResetAmato(nome, esito);
        return esito;
    }

    public boolean isTrattoPresente(String nome, Tratto tratto) { return getRuolo(nome).isTrattoPresente(tratto); }

    public Fazione getFazione(String nome) { return getRuolo(nome).getFazione(); }

    public EsitoAttacco attaccoVampiro(String nome)
    {
        EsitoAttacco esito = vampirizzazioneRuolo(nome);
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

    public String getNomeAssassino()
    {
        int posizione = NON_TROVATO;
        for(int i = 0; i < getNumeroGiocatori() && posizione == NON_TROVATO; i++) if(isAssassino(i)) posizione = i;
        return getNomeGiocatore(posizione);
    }

    public void segnalazioneAzzeccagarbugli(String nome) { getRuolo(nome).segnalazioneAzzeccagarbugli(); }

    public boolean isGuardia(String nome) { return getRuolo(nome).isGuardia(); }

    public boolean isCreaturaOmbra(String nome) { return getRuolo(nome).isCreaturaOmbra(); }

    private boolean isAssassino(int posizione) { return isAssassino(getNomeGiocatore(posizione)); }

    private void gestisciResetAmato(String nome, EsitoAttacco esito)
    {
        if(esito == RIUSCITO && isAngeloCustode(nome)) resettaAmato();
    }

    private Giocatori creaBallottaggio()
    {
        Ballottaggio ballottaggio = new Ballottaggio();
        aggiungiGiocatoriBallottaggio(ballottaggio, getNumeroVotiPrimoClassificato());
        if(getNumeroGiocatori() > 0) estraiSecondoPosto(ballottaggio);
        gestisciSegnalazioni(ballottaggio);
        sistemazioneBallottaggio(ballottaggio);
        return ballottaggio;
    }

    private void sistemazioneBallottaggio(Ballottaggio ballottaggio)
    {
        if(ballottaggio.isAmatoPresente()) gestioneAmato(ballottaggio);
        if(!ballottaggio.isSegnalazioneAssente()) ballottaggio.annullaSegnalazioni();
    }

    private void estraiSecondoPosto(Ballottaggio ballottaggio)
    {
        int numeroVoti = getNumeroVotiPrimoClassificato();
        if(ballottaggio.getNumeroGiocatori() < 2 && numeroVoti > 0) aggiungiGiocatoriBallottaggio(ballottaggio, numeroVoti);
    }

    private void gestisciSegnalazioni(Ballottaggio ballottaggio)
    {
        int posizioneSegnalatoAzzeccagarbugli = getPosizioneSegnalatoAzzeccagarbugli(), posizioneInquisito = getPosizioneInquisito();
        if(posizioneSegnalatoAzzeccagarbugli != NON_TROVATO) gestisciSegnalazioneAzzeccagarbugli(ballottaggio, posizioneSegnalatoAzzeccagarbugli);
        if(posizioneInquisito != NON_TROVATO) gestisciSegnalazioneInquisitore(ballottaggio, posizioneInquisito);
        ballottaggio.annullaSegnalazioni();
    }

    private void gestisciSegnalazioneAzzeccagarbugli(Giocatori ballottaggio, int posizione)
    {
        String nome = getNomeGiocatore(posizione);
        if(!isCriminale(nome)) mandaBallottaggio(ballottaggio, nome);
    }

    private void gestisciSegnalazioneInquisitore(Ballottaggio ballottaggio, int posizione)
    {
        String nome = getNomeGiocatore(posizione);
        mandaBallottaggio(ballottaggio, nome);
    }

    private int getPosizioneSegnalatoAzzeccagarbugli()
    {
        int posizione = NON_TROVATO;
        for(int i = 0; i < getNumeroGiocatori() && posizione == NON_TROVATO; i++) if(isSegnalatoAzzeccagarbugli(getNomeGiocatore(i))) posizione = i;
        return posizione;
    }

    private int getPosizioneInquisito()
    {
        int posizione = NON_TROVATO;
        for(int i = 0; i < getNumeroGiocatori() && posizione == NON_TROVATO; i++) if(isInquisito(getNomeGiocatore(i))) posizione = i;
        return posizione;
    }

    private void gestioneAmato(Ballottaggio ballottaggio)
    {
        spostamentoAmato(ballottaggio);
        if(isAngeloCustodePresente()) spostamentoAngeloCustode(ballottaggio);
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

    private String[] estraiGiocatori(int numeroVoti) { return toArray(getListaNomi(numeroVoti)); }

    private List<String> getListaNomi(int numeroVoti)
    {
        List<String> nomi = new ArrayList<>();
        for(int i = 0; i < getNumeroGiocatori(); i++)
        {
            String nome = getNomeGiocatore(i);
            if(numeroVoti == getNumeroVoti(nome)) nomi.add(nome);
        }
        return nomi;
    }

    private String[] toArray(List<String> nomi)
    {
        String[] risultato = new String[nomi.size()];
        nomi.toArray(risultato);
        return risultato;
    }

    private int getNumeroVotiPrimoClassificato() { return getNumeroVoti(getNomeGiocatore(0)); }

    private EsitoAttacco attaccoNosferatuRuolo(String nome) { return getRuolo(nome).attaccoNosferatu(); }

    private EsitoAttacco vampirizzazioneRuolo(String nome) { return getRuolo(nome).vampirizzazione(); }

    private boolean isAssassino(String nome) { return getRuolo(nome).isAssassino(); }

}
