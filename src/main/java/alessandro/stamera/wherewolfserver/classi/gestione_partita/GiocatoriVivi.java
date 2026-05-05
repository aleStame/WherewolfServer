package alessandro.stamera.wherewolfserver.classi.gestione_partita;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Tratto;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import java.util.ArrayList;
import java.util.List;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.RIUSCITO;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;

public final class GiocatoriVivi extends Giocatori
{

    private static final int NON_TROVATO = -1;

    public Ballottaggio getBallottaggio()
    {
        Ballottaggio ballottaggio = creaBallottaggio();
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

    public String getNomeAssassino() { return getNomeGiocatore(getPosizioneAssassino()); }

    public void segnalazioneAzzeccagarbugli(String nome) { getRuolo(nome).segnalazioneAzzeccagarbugli(); }

    public int getNumeroGuardie()
    {
        int numeroGuardie = 0;
        for(int i = 0; i < getNumeroGiocatori(); i++) if(isGuardia(i)) numeroGuardie++;
        return numeroGuardie;
    }

    public boolean isGuardia(String nome) { return getRuolo(nome).isGuardia(); }

    public boolean isCreaturaOmbra(String nome) { return getRuolo(nome).isCreaturaOmbra(); }

    public int getNumeroCreatureOmbra()
    {
        int numeroCreatureOmbra = 0;
        for(int i = 0; i < getNumeroGiocatori(); i++) if(isCreaturaOmbra(i)) numeroCreatureOmbra++;
        return numeroCreatureOmbra;
    }

    public Aura getControlloVeggente(String nome) { return getRuolo(nome).getAura(); }

    public int getNumeroCriminali()
    {
        int numeroCriminali = 0;
        for(int i = 0; i < getNumeroGiocatori(); i++) if(isCriminale(i)) numeroCriminali++;
        return numeroCriminali;
    }

    public boolean isNegromantePresente() { return getPosizioneNegromante() != NON_TROVATO; }

    public String getNomeNegromante() { return getNomeGiocatore(getPosizioneNegromante()); }

    public int getNumeroMistici()
    {
        int numeroMistici = 0;
        for(int i = 0; i < getNumeroGiocatori(); i++) if(isMistico(i)) numeroMistici++;
        return numeroMistici;
    }

    public int getNumeroLupi()
    {
        int numeroLupi = 0;
        for(int i = 0; i < getNumeroGiocatori(); i++) if(isLupo(i)) numeroLupi++;
        return numeroLupi;
    }

    public boolean isBracconierePresente() { return false; }

    public String getNomeBracconiere() { return null; }

    private boolean isMistico(int posizione) { return getRuolo(getNomeGiocatore(posizione)).isMistico(); }

    private boolean isLupo(int posizione) { return getRuolo(getNomeGiocatore(posizione)).isLupo(); }

    private boolean isCriminale(int posizione) { return getRuolo(getNomeGiocatore(posizione)).isCriminale(); }

    private boolean isCreaturaOmbra(int posizione) { return isCreaturaOmbra(getNomeGiocatore(posizione)); }

    private int getPosizioneAssassino()
    {
        int posizione = NON_TROVATO;
        for(int i = 0; i < getNumeroGiocatori() && posizione == NON_TROVATO; i++) if(isAssassino(i)) posizione = i;
        return posizione;
    }

    private int getPosizioneNegromante()
    {
        int posizione = NON_TROVATO;
        for(int i = 0; i < getNumeroGiocatori() && posizione == NON_TROVATO; i++) if(isNegromante(i)) posizione = i;
        return posizione;
    }

    private boolean isNegromante(int posizione) { return getRuolo(getNomeGiocatore(posizione)).isNegromante(); }

    private boolean isAssassino(int posizione) { return isAssassino(getNomeGiocatore(posizione)); }

    private void gestisciResetAmato(String nome, EsitoAttacco esito)
    {
        if(esito == RIUSCITO && isAngeloCustode(nome)) resettaAmato();
    }

    private Ballottaggio creaBallottaggio()
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
        if(controlloBallottaggioAzzeccagarbugli(nome)) mandaBallottaggio(ballottaggio, nome);
    }

    private boolean controlloBallottaggioAzzeccagarbugli(String nome) { return !isCriminale(nome) && !isCitta(nome); }

    private boolean isCitta(String nome) { return getRuolo(nome).isCitta(); }

    private void gestisciSegnalazioneInquisitore(Ballottaggio ballottaggio, int posizione)
    {
        String nome = getNomeGiocatore(posizione);
        mandaBallottaggio(ballottaggio, nome);
    }

    private int getPosizioneSegnalatoAzzeccagarbugli()
    {
        int posizione = NON_TROVATO;
        for(int i = 0; i < getNumeroGiocatori() && posizione == NON_TROVATO; i++) if(isSegnalatoAzzeccagarbugli(i)) posizione = i;
        return posizione;
    }

    private boolean isSegnalatoAzzeccagarbugli(int posizione) { return isSegnalatoAzzeccagarbugli(getNomeGiocatore(posizione)); }

    private int getPosizioneInquisito()
    {
        int posizione = NON_TROVATO;
        for(int i = 0; i < getNumeroGiocatori() && posizione == NON_TROVATO; i++) if(isInquisito(i)) posizione = i;
        return posizione;
    }

    private boolean isInquisito(int posizione) { return isInquisito(getNomeGiocatore(posizione)); }

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

    private EsitoAttacco attaccoNosferatuRuolo(String nome) { return getRuolo(nome).attaccoNosferatu(); }

    private EsitoAttacco vampirizzazioneRuolo(String nome) { return getRuolo(nome).vampirizzazione(); }

    private boolean isAssassino(String nome) { return getRuolo(nome).isAssassino(); }

    private boolean isGuardia(int posizione) { return isGuardia(getNomeGiocatore(posizione)); }

}
