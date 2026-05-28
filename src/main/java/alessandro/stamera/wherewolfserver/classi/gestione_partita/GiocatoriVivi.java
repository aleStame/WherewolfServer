package alessandro.stamera.wherewolfserver.classi.gestione_partita;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.*;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import java.util.ArrayList;
import java.util.List;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.RIUSCITO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.NESSUNA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.NOSFERATU;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Misticismo.MISTICO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Misticismo.NON_MISTICO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Tratto.NON_MORTO;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;

public final class GiocatoriVivi extends Giocatori
{

    private static final int NON_TROVATO = -1;

    private boolean crociataAvviata;

    public GiocatoriVivi() { setCrociataAvviata(false); }

    public Ballottaggio getBallottaggio()
    {
        Ballottaggio ballottaggio = creaBallottaggio();
        annullaVoti();
        ballottaggio.annullaVoti();
        return ballottaggio;
    }

    public void segnalazioneAngeloCustode(String nome) { getRuolo(nome).sceltaAngeloCustode(); }

    public EsitoAttacco attaccoAssassino(String nome) { return getRuolo(nome).attaccoAssassino(); }

    public EsitoAttacco attaccoLupi(Ruolo attaccante, String nome)
    {
        EsitoAttacco esito = getRuolo(nome).attaccoLupi(attaccante);
        if(esito == RIUSCITO && isTemplare(nome) && isInquisitorePresente()) setCrociataAvviata(true);
        return esito;
    }

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

    public boolean isBracconierePresente() { return getPosizioneBracconiere() != NON_TROVATO; }

    public String getNomeBracconiere() { return getNomeGiocatore(getPosizioneBracconiere()); }

    public boolean isPotereBracconiereUtilizzato()
    {
        boolean esito = isBracconierePresente();
        if(esito) esito = getBracconiere().isPotereUtilizzato();
        return esito;
    }

    public void utilizzaPotereBracconiere() { if(getNumeroLupi() == 1) getBracconiere().utilizzaPotere(); }

    public void riabilitaPotereBracconiere() { getBracconiere().riabilitaPotere(); }

    public boolean isLupoSolitarioPresente() { return getPosizioneLupoSolitario() != NON_TROVATO; }

    public boolean isCacciatorePresente() { return getPosizioneCacciatore() != NON_TROVATO; }

    public boolean isCacciatoreProtetto() { return isUltimoLupoBrancoRimasto() || getNumeroLupi() == 1; }

    public String getNomeNosferatu() { return getNomeGiocatore(getPosizioneNosferatu()); }

    public int getNumeroSenzaFazione()
    {
        int numeroSenzaFazione = 0;
        for(int i = 0; i < getNumeroGiocatori(); i++) if(isSenzaFazione(i)) numeroSenzaFazione++;
        return numeroSenzaFazione;
    }

    public EsitoAttacco gildata(String nome) { return getRuolo(nome).gildata(); }

    public String getNomeCapoGilda() { return getNomeGiocatore(getPosizioneCapoGilda()); }

    public boolean isCrociataAvviata() { return crociataAvviata; }

    public void riconosciNegromante() { getRuolo(getPosizioneBecchino()).riconosciNegromante(); }

    public void annullaProtezioniCappuccettoRosso() { getRuolo(getPosizioneCappuccettoRosso()).perdiProtezioni(); }

    public boolean isNonnaPresente() { return getPosizioneNonna() != NON_TROVATO; }

    public boolean isCappuccettoRossoPresente() { return getPosizioneCappuccettoRosso() != NON_TROVATO; }

    public boolean isGuaritorePresente() { return getPosizioneGuaritore() != NON_TROVATO; }

    public String getNomeGuaritore() { return getNomeGiocatore(getPosizioneGuaritore()); }

    public Misticismo controlloMago(String nome)
    {
        Misticismo misticismo = NON_MISTICO;
        if(isMistico(nome)) misticismo = MISTICO;
        return misticismo;
    }

    public boolean isMagoPresente() { return getPosizioneMago() != NON_TROVATO; }

    public String getNomeMago() { return getNomeGiocatore(getPosizioneMago()); }

    public EsitoAttacco attaccoNegromante(String nome) { return getRuolo(nome).attaccoNegromante(); }

    public void romeizzazione(String nome) { getRuolo(nome).romeizzazione(); }

    public EsitoControlloSensitiva controlloSensitiva(String nome) { return getRuolo(nome).controlloSensitiva(); }

    public boolean isSensitivaPresente() { return getPosizioneSensitiva() != NON_TROVATO; }

    public String getNomeSensitiva() { return getNomeGiocatore(getPosizioneSensitiva()); }

    public boolean isGhoul(String nome)
    {
        boolean trovato = false;
        if(isPresente(nome)) trovato = getRuolo(nome).isGhoul();
        return trovato;
    }

    public boolean isNosferatu(String nome)
    {
        boolean trovato = false;
        if(isPresente(nome)) trovato = getRuolo(nome).isNosferatu();
        return trovato;
    }

    public boolean isProgenieNosferatu(String nome)
    {
        boolean trovato = false;
        if(isPresente(nome))
        {
            Ruolo ruolo = getRuolo(nome);
            return ruolo.isTrattoPresente(NON_MORTO) && ruolo.getFazione() == NOSFERATU;
        }
        return trovato;
    }

    public boolean isLupoReiettoPresente()
    {
        boolean trovato = false;
        for(int i = 0; i < getNumeroGiocatori() && !trovato; i++) trovato = getRuolo(i).isLupoReietto();
        return trovato;
    }

    public boolean isCapoBrancoPresente()
    {
        boolean trovato = false;
        for(int i = 0; i < getNumeroGiocatori() && !trovato; i++) trovato = getRuolo(i).isCapoBranco();
        return trovato;
    }

    public boolean isLupoBrancoPresente()
    {
        boolean trovato = false;
        for(int i = 0; i < getNumeroGiocatori() && !trovato; i++) trovato = getRuolo(i).isLupoBranco();
        return trovato;
    }

    private int getPosizioneSensitiva()
    {
        int posizione = NON_TROVATO;
        for(int i = 0; i < getNumeroGiocatori() && posizione == NON_TROVATO; i++) if(getRuolo(getNomeGiocatore(i)).isSensitiva()) posizione = i;
        return posizione;
    }

    private int getPosizioneMago()
    {
        int posizione = NON_TROVATO;
        for(int i = 0; i < getNumeroGiocatori() && posizione == NON_TROVATO; i++) if(isMago(i)) posizione = i;
        return posizione;
    }

    private boolean isMago(int posizione) { return getRuolo(getNomeGiocatore(posizione)).isMago(); }

    private int getPosizioneGuaritore()
    {
        int posizione = NON_TROVATO;
        for(int i = 0; i < getNumeroGiocatori() && posizione == NON_TROVATO; i++) if(isGuaritore(i)) posizione = i;
        return posizione;
    }

    private boolean isGuaritore(int posizione) { return getRuolo(posizione).isGuaritore(); }

    private int getPosizioneCappuccettoRosso()
    {
        int posizione = NON_TROVATO;
        for(int i = 0; i < getNumeroGiocatori() && posizione == NON_TROVATO; i++) if(isCappuccettoRosso(i)) posizione = i;
        return posizione;
    }

    private boolean isCappuccettoRosso(int posizione) { return getRuolo(posizione).isCappuccettoRosso(); }

    private int getPosizioneNonna()
    {
        int posizione = NON_TROVATO;
        for(int i = 0; i < getNumeroGiocatori() && posizione == NON_TROVATO; i++) if(isNonna(i)) posizione = i;
        return posizione;
    }

    private boolean isNonna(int posizione) { return getRuolo(posizione).isNonna(); }

    private int getPosizioneBecchino()
    {
        int posizione = NON_TROVATO;
        for(int i = 0; i < getNumeroGiocatori() && posizione == NON_TROVATO; i++) if(isBecchino(i)) posizione = i;
        return posizione;
    }

    private boolean isBecchino(int posizione) { return getRuolo(posizione).isBecchino(); }

    private boolean isInquisitorePresente() { return getPosizioneInquisitore() != NON_TROVATO; }

    private void setCrociataAvviata(boolean crociataAvviata) { this.crociataAvviata = crociataAvviata; }

    private int getPosizioneInquisitore()
    {
        int posizione = NON_TROVATO;
        for(int i = 0; i < getNumeroGiocatori() && posizione == NON_TROVATO; i++) if(isInquisitore(i)) posizione = i;
        return posizione;
    }

    private boolean isInquisitore(int posizione) { return isInquisitore(getNomeGiocatore(posizione)); }

    private boolean isInquisitore(String nome) { return getRuolo(nome).isInquisitore(); }

    private boolean isTemplare(String nome) { return getRuolo(nome).isTemplare(); }

    private int getPosizioneCacciatore()
    {
        int posizione = NON_TROVATO;
        for(int i = 0; i < getNumeroGiocatori() && posizione == NON_TROVATO; i++) if(isCacciatore(i)) posizione = i;
        return posizione;
    }

    private boolean isCacciatore(int posizione) { return getRuolo(posizione).isCacciatore(); }

    private int getPosizioneCapoGilda()
    {
        int posizione = NON_TROVATO;
        for(int i = 0; i < getNumeroGiocatori() && posizione == NON_TROVATO; i++) if(isCapoGilda(i)) posizione = i;
        return posizione;
    }

    private boolean isCapoGilda(int posizione) { return getRuolo(posizione).isCapoGilda(); }

    private boolean isSenzaFazione(int posizione) { return getFazione(posizione) == NESSUNA; }

    private Fazione getFazione(int posizione) { return getRuolo(posizione).getFazione(); }

    private int getPosizioneNosferatu()
    {
        int posizione = NON_TROVATO;
        for(int i = 0; i < getNumeroGiocatori() && posizione == NON_TROVATO; i++) if(isNosferatu(i)) posizione = i;
        return posizione;
    }

    private boolean isNosferatu(int posizione) { return getRuolo(posizione).isNosferatu(); }

    private boolean isUltimoLupoBrancoRimasto() { return getNumeroLupi() == 2 && isLupoSolitarioPresente(); }

    private int getPosizioneLupoSolitario()
    {
        int posizione = NON_TROVATO;
        for(int i = 0; i < getNumeroGiocatori() && posizione == NON_TROVATO; i++) if(isLupoSolitario(i)) posizione = i;
        return posizione;
    }

    private boolean isLupoSolitario(int posizione) { return getRuolo(posizione).isLupoSolitario(); }

    private Ruolo getBracconiere() { return getRuolo(getNomeBracconiere()); }

    private int getPosizioneBracconiere()
    {
        int posizione = NON_TROVATO;
        for(int i = 0; i < getNumeroGiocatori() && posizione == NON_TROVATO; i++) if(isBracconiere(i)) posizione = i;
        return posizione;
    }

    private boolean isBracconiere(int posizione) { return getRuolo(posizione).isBracconiere(); }

    private boolean isMistico(int posizione) { return isMistico(getNomeGiocatore(posizione)); }

    private boolean isMistico(String nome) { return getRuolo(nome).isMistico(); }

    private boolean isLupo(int posizione) { return getRuolo(posizione).isLupo(); }

    private boolean isCriminale(int posizione) { return getRuolo(posizione).isCriminale(); }

    private Ruolo getRuolo(int posizione) { return getRuolo(getNomeGiocatore(posizione)); }

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

    private boolean isNegromante(int posizione) { return getRuolo(posizione).isNegromante(); }

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