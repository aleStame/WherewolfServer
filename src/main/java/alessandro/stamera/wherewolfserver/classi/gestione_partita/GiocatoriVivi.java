package alessandro.stamera.wherewolfserver.classi.gestione_partita;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.*;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.*;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.*;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Misticismo.MISTICO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Misticismo.NON_MISTICO;
import static java.util.Arrays.stream;

public final class GiocatoriVivi extends Giocatori
{

    private static final int NON_TROVATO = -1;

    public GiocatoriVivi() { }

    public Ballottaggio getBallottaggio()
    {
        Ballottaggio ballottaggio = creaBallottaggio();
        annullaVoti();
        ballottaggio.annullaVoti();
        return ballottaggio;
    }

    public void segnalazioneAngeloCustode(String nome) { getGiocatore(nome).protezioneAngeloCustode(); }

    public EsitoAttacco attaccoAssassino(String nome) { return getGiocatore(nome).attaccoAssassino(); }

    public EsitoAttacco attaccoLupi(Ruolo attaccante, String nome)
    {
        EsitoAttacco esito = getGiocatore(nome).attaccoLupi(attaccante);
        switch(esito)
        {
            case RIUSCITO -> esito = gestioneAttaccoRiuscito(attaccante, nome);
            case ANGELO_CUSTODE_MORTO -> esito = gestioneAttaccoAngeloCustode(nome, attaccante);
            case FALLITO ->
                { if(isCappuccettoRossoDaSvegliare(attaccante, nome)) esito = ULTIMO_LUPO_SVEGLIA_CAPPUCCETTO_ROSSO; }
            case ULTIMO_LUPO_UCCIDE_CAPPUCCETTO_ROSSO -> { if(isProtezioneAngeloCustodeAttiva(nome)) esito = ULTIMO_LUPO_SVEGLIA_CAPPUCCETTO_ROSSO; }
            case ULTIMO_LUPO_SVEGLIA_CAPPUCCETTO_ROSSO ->
                { if(isProtezioneAngeloCustodeNonAttiva(nome)) esito = ULTIMO_LUPO_UCCIDE_CAPPUCCETTO_ROSSO; }
        }
        return esito;
    }

    public boolean isTrattoPresente(String nome, Tratto tratto) { return getGiocatore(nome).isTrattoPresente(tratto); }

    public Fazione getFazione(String nome) { return getGiocatore(nome).getFazione(); }

    public EsitoAttacco attaccoVampiro(String nome)
    {
        EsitoAttacco esito = vampirizzazioneRuolo(nome);
        if(esito == MORTO && isGhoulPresente()) esito = GHOUL_MORTO;
        gestisciResetAmato(nome, esito);
        return esito;
    }

    public boolean isPosseduto(String nome) { return getGiocatore(nome).getRuolo().isPosseduto(); }

    public String getNomeAssassino() { return getNomeGiocatore(getPosizioneAssassino()); }

    public int getNumeroGuardie()
    {
        int numeroGuardie = 0;
        for(int i = 0; i < getNumeroGiocatori(); i++) if(isGuardia(i)) numeroGuardie++;
        return numeroGuardie;
    }

    public boolean isGuardia(String nome) { return getGiocatore(nome).isGuardia(); }

    public boolean isCreaturaOmbra(String nome) { return getGiocatore(nome).getRuolo().isCreaturaOmbra(); }

    public int getNumeroCreatureOmbra()
    {
        int numeroCreatureOmbra = 0;
        for(int i = 0; i < getNumeroGiocatori(); i++) if(isCreaturaOmbra(i)) numeroCreatureOmbra++;
        return numeroCreatureOmbra;
    }

    public Aura getControlloVeggente(String nome) { return getGiocatore(nome).getAura(); }

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

    public int getNumeroLupiBranco()
    {
        int numeroLupi = 0;
        for(int i = 0; i < getNumeroGiocatori(); i++) if(getFazione(i) == LUPO_BRANCO) numeroLupi++;
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

    public void utilizzaPotereBracconiere() { if(isRimastoUltimoLupo()) getBracconiere().utilizzaPotere(); }

    public void riabilitaPotereBracconiere() { getBracconiere().riabilitaPotere(); }

    public boolean isLupoSolitarioPresente() { return getPosizioneLupoSolitario() != NON_TROVATO; }

    public boolean isCacciatorePresente() { return getPosizioneCacciatore() != NON_TROVATO; }

    public String getNomeNosferatu() { return getNomeGiocatore(getPosizioneNosferatu()); }

    public int getNumeroSenzaFazione()
    {
        int numeroSenzaFazione = 0;
        for(int i = 0; i < getNumeroGiocatori(); i++) if(isSenzaFazione(i)) numeroSenzaFazione++;
        return numeroSenzaFazione;
    }

    public String getNomeCapoGilda() { return getNomeGiocatore(getPosizioneCapoGilda()); }

    public void riconosciNegromante() { getGiocatore(getPosizioneBecchino()).riconosciNegromante(); }

    public void annullaProtezioniCappuccettoRosso()
    {
        getGiocatore(getNomeCappuccettoRosso()).perdiProtezioni();
    }

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

    public EsitoAttacco attaccoNegromante(String nome)
    {
        Giocatore giocatore = getGiocatore(nome);
        EsitoAttacco esito = giocatore.attaccoNegromante();
        if(esito == RIUSCITO && giocatore.getRuolo().isMegera()) getGiocatore(getNomeNegromante()).maledizione();
        return esito;
    }

    public EsitoControlloSensitiva controlloSensitiva(String nome) { return getGiocatore(nome).getRuolo().controlloSensitiva(); }

    public boolean isSensitivaPresente() { return getPosizioneSensitiva() != NON_TROVATO; }

    public String getNomeSensitiva() { return getNomeGiocatore(getPosizioneSensitiva()); }

    public boolean isGhoul(String nome)
    {
        boolean trovato = false;
        if(isPresente(nome)) trovato = getGiocatore(nome).getRuolo().isGhoul();
        return trovato;
    }

    public boolean isVampiro(String nome)
    {
        boolean trovato = false;
        if(isPresente(nome)) trovato = getGiocatore(nome).getRuolo().isVampiro();
        return trovato;
    }

    public boolean isNosferatu(String nome)
    {
        boolean trovato = false;
        if(isPresente(nome)) trovato = getGiocatore(nome).isNosferatu();
        return trovato;
    }

    public boolean isProgenieNosferatu(String nome)
    {
        boolean trovato = false;
        if(isPresente(nome))
        {
            Giocatore giocatore = getGiocatore(nome);
            trovato = giocatore.isNonMorto() && giocatore.getFazione() == NOSFERATU;
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

    public boolean isAmatoPresente() { return getPosizioneAmato() != NON_TROVATO; }

    public boolean isGhoulPresente() { return getPosizioneGhoul() != NON_TROVATO; }

    public String getNomeGhoul() { return getNomeGiocatore(getPosizioneGhoul()); }

    public boolean isFazioneNosferatu(String nome) { return getFazione(nome) == NOSFERATU; }

    public boolean isVampiroPresente() { return getPosizioneVampiro() != NON_TROVATO; }

    public String getNomeVampiro() { return getNomeGiocatore(getPosizioneVampiro()); }

    public void maledizione(String nome) { getGiocatore(nome).maledizione(); }

    public boolean isMaledetto(String nome) { return getGiocatore(nome).isMaledetto(); }

    public boolean isMistico(String nome) { return getGiocatore(nome).getRuolo().isMistico(); }

    public boolean isNegromante(String nome) { return getGiocatore(nome).isNegromante(); }

    public void protezioneStrega(String nome)
    {
        Ruolo stregato = getGiocatore(nome).getRuolo();
        stregato.protezioneStrega();
        aggiungiProtezioneCreatureOmbra(nome);
    }

    public void aggiungiProtezioneCreatureOmbra(String nome) { getGiocatore(nome).aggiungiProtezione(getCreatureOmbra()); }

    public boolean isStregato(String nome) { return getGiocatore(nome).getRuolo().isStregato(); }

    public boolean isVampiroAmato()
    {
        boolean esito = false;
        if(isVampiroPresente()) esito = isAmato(getNomeVampiro());
        return esito;
    }

    public boolean isCacciatoreDiVampiriPresente() { return getPosizioneCacciatoreDiVampiri() != NON_TROVATO; }

    public String getNomeCacciatoreDiVampiri() { return getNomeGiocatore(getPosizioneCacciatoreDiVampiri()); }

    public String getNomeAmato() { return getNomeGiocatore(getPosizioneAmato()); }

    public void assorbiRuolo(String nomeAssorbitore, String nomeAssorbito)
    {
        Giocatore giocatore = getGiocatore(nomeAssorbito);
        eliminaGiocatore(nomeAssorbitore);
        aggiungiGiocatore(nomeAssorbitore, giocatore);
        eliminaGiocatore(nomeAssorbito);
    }

    public boolean isCapoBranco(String nome) { return getGiocatore(nome).getRuolo().isCapoBranco(); }

    public boolean isLupo(String nome) { return getGiocatore(nome).isLupo(); }

    public Aura getAura(String nome) { return getGiocatore(nome).getAura(); }

    public void annullaMaledizione(String nome) { getGiocatore(nome).annullaMaledizione(); }

    public boolean isCacciatoreProtetto() { return isCacciatorePresente() && isRimastoUnSoloLupo(); }

    @Override public void eliminaGiocatore(String nome)
    {
        super.eliminaGiocatore(nome);
        perditaProtezioniAmato();
        if(isCappuccettoNonnaPresenti()) gestisciProtezioneNonna();
    }

    @Override public void aggiungiGiocatore(String nome, Giocatore giocatore)
    {
        super.aggiungiGiocatore(nome, giocatore);
        if(isCappuccettoNonnaPresenti()) gestisciProtezioneNonna();
    }

    public void segnalazioneInquisitore(String nome) { getGiocatore(nome).segnalazioneInquisitore(); }

    public void segnalazioneAzzeccagarbugli(String nome) { getGiocatore(nome).segnalazioneAzzeccagarbugli(); }

    public EsitoAttacco gildata(String nome) { return getGiocatore(nome).gildata(); }

    public EsitoAttacco passaPosseduto(String nome)
    {
        boolean isAngeloCustode = isAngeloCustode(nome);
        EsitoAttacco esito = getGiocatore(nome).passaPosseduto();
        if(isAngeloCustode && esito == RIUSCITO) getGiocatoreAmato().annullaProtezioneAngeloCustode();
        return esito;
    }

    public void romeizzazione(String nome)
    {
        getGiocatore(nome).romeizzazione();
        aggiungiProtezioneCreatureOmbra(nome);
    }

    private Giocatore getGiocatoreAmato() { return getGiocatore(getNomeAmato()); }

    private boolean isProtezioneAngeloCustodeNonAttiva(String nome) { return isAmato(nome) && !isAngeloCustodePresente(); }

    private boolean isCappuccettoRossoDaSvegliare(Ruolo attaccante, String nome)
    {
        return isCappuccettoRossoProtetto(nome, attaccante) && isNonnaPresente() && isRimastoUltimoLupo();
    }

    private EsitoAttacco gestioneAttaccoRiuscito(Ruolo attaccante, String nome)
    {
        EsitoAttacco esito = RIUSCITO;
        if(isCappuccettoRossoProtetto(nome, attaccante)) esito = ULTIMO_LUPO_SVEGLIA_CAPPUCCETTO_ROSSO;
        else if(isCappuccettoRosso(nome)) esito = getEsitoAttaccoCappuccettoRosso();
        return esito;
    }

    private EsitoAttacco getEsitoAttaccoCappuccettoRosso()
    {
        EsitoAttacco esito = ULTIMO_LUPO_UCCIDE_CAPPUCCETTO_ROSSO;
        if(isCappuccettoRossoAmato()) esito = ULTIMO_LUPO_SVEGLIA_CAPPUCCETTO_ROSSO;
        return esito;
    }

    private boolean isCappuccettoRossoAmato() { return isProtezioneAngeloCustodeAttiva(getNomeCappuccettoRosso()); }

    private String getNomeCappuccettoRosso() { return getNomeGiocatore(getPosizioneCappuccettoRosso()); }

    private boolean isProtezioneAngeloCustodeAttiva(String nome) { return isAmato(nome) && isAngeloCustodePresente(); }

    private void gestisciProtezioneNonna()
    {
        if(isRimastoUnSoloLupo()) aggiungiProtezioneNonna();
        else perditaProtezioniNonna();
    }

    private void perditaProtezioniNonna() { getNonna().perdiProtezioni(); }

    private Giocatore getNonna() { return getGiocatore(getPosizioneNonna()); }

    private Ruolo[] getCreatureOmbra()
    {
        List<Ruolo> ruoli = new ArrayList<>();
        for(int i = 0; i < getNumeroGiocatori(); i++) ruoli.add(getRuolo(i));
        return ruoli.stream().filter(Ruolo::isCreaturaOmbra).toList().toArray(new Ruolo[0]);
    }

    private boolean isRimastoUnSoloLupo() { return isRimastoUltimoLupo() || isLupoSolitarioPresente(); }

    private EsitoAttacco gestioneAttaccoAngeloCustode(String nome, Ruolo lupo)
    {
        EsitoAttacco esito = ANGELO_CUSTODE_MORTO;
        if(isCappuccettoRossoProtetto(nome, lupo)) esito = ULTIMO_LUPO_SVEGLIA_CAPPUCCETTO_ROSSO;
        else if(!isAngeloCustodePresente()) esito = RIUSCITO;
        return esito;
    }

    private boolean isCappuccettoRossoProtetto(String nome, Ruolo attaccante)
    {
        return isCappuccettoRosso(nome) && getGiocatore(nome).isProtezionePresente(attaccante);
    }

    private boolean isCappuccettoRosso(String nome) { return getGiocatore(nome).isCappuccettoRosso(); }

    public boolean isTemplarePresente() { return getPosizioneTemplare() != NON_TROVATO; }

    private int getPosizioneTemplare()
    {
        int posizione = NON_TROVATO;
        for(int i = 0; i < getNumeroGiocatori() && posizione == NON_TROVATO; i++) if(isTemplare(i)) posizione = i;
        return posizione;
    }

    private boolean isTemplare(int posizione) { return isTemplare(getNomeGiocatore(posizione)); }

    private void perditaProtezioniAmato() { if(isAngeloCustodeMorto()) getGiocatoreAmato().perdiProtezioni(); }

    private boolean isAngeloCustodeMorto() { return isAmatoPresente() && !isAngeloCustodePresente(); }

    private void aggiungiProtezioneNonna() { if(isCappuccettoNonnaPresenti()) aggiornaProtezioneNonna(); }

    private boolean isCappuccettoNonnaPresenti() { return isCappuccettoRossoPresente() && isNonnaPresente(); }

    private void aggiornaProtezioneNonna()
    {
        if(isLupoSolitarioPresente()) aggiungiProtezioneNonna(getPosizioneLupoSolitario());
        else if(isRimastoUltimoLupo()) gestioneProtezioneUltimoLupo();
    }

    private boolean isRimastoUltimoLupo() { return getNumeroLupiBranco() == 1; }

    private void gestioneProtezioneUltimoLupo()
    {
        int posizione = NON_TROVATO;
        for(int i = 0; i < getNumeroGiocatori() && posizione == NON_TROVATO; i++) if(isLupo(i)) posizione = i;
        aggiungiProtezioneNonna(posizione);
    }

    private void aggiungiProtezioneNonna(int posizione) { getCappuccettoRosso().aggiungiProtezione(getRuolo(posizione)); }

    private Ruolo getRuolo(int posizione) { return getGiocatore(posizione).getRuolo(); }

    private Giocatore getCappuccettoRosso() { return getGiocatore(getPosizioneCappuccettoRosso()); }

    private int getPosizioneCacciatoreDiVampiri()
    {
        int posizione = NON_TROVATO;
        for(int i = 0; i < getNumeroGiocatori() && posizione == NON_TROVATO; i++) if(isCacciatoreDiVampiri(i)) posizione = i;
        return posizione;
    }

    private boolean isCacciatoreDiVampiri(int posizione) { return isCacciatoreDiVampiri(getNomeGiocatore(posizione)); }

    private boolean isCacciatoreDiVampiri(String nome) { return getGiocatore(nome).isCacciatoreDiVampiri(); }

    private int getPosizioneVampiro()
    {
        int posizione = NON_TROVATO;
        for(int i = 0; i < getNumeroGiocatori() && posizione == NON_TROVATO; i++) if(isVampiro(i)) posizione = i;
        return posizione;
    }

    private int getPosizioneGhoul()
    {
        int posizione = NON_TROVATO;
        for(int i = 0; i < getNumeroGiocatori() && posizione == NON_TROVATO; i++) if(isGhoul(i)) posizione = i;
        return posizione;
    }

    private boolean isGhoul(int posizione) { return isGhoul(getNomeGiocatore(posizione)); }

    private boolean isVampiro(int posizione) { return isVampiro(getNomeGiocatore(posizione)); }

    private int getPosizioneAmato()
    {
        int posizione = NON_TROVATO;
        for(int i = 0; i < getNumeroGiocatori() && posizione == NON_TROVATO; i++) if(isAmato(i)) posizione = i;
        return posizione;
    }

    private boolean isAmato(int posizione) { return getGiocatore(posizione).isAmato(); }

    private int getPosizioneSensitiva()
    {
        int posizione = NON_TROVATO;
        for(int i = 0; i < getNumeroGiocatori() && posizione == NON_TROVATO; i++) if(isSensitiva(i)) posizione = i;
        return posizione;
    }

    private boolean isSensitiva(int posizione) { return getGiocatore(posizione).isSensitiva(); }

    private int getPosizioneMago()
    {
        int posizione = NON_TROVATO;
        for(int i = 0; i < getNumeroGiocatori() && posizione == NON_TROVATO; i++) if(isMago(i)) posizione = i;
        return posizione;
    }

    private boolean isMago(int posizione) { return getGiocatore(posizione).getRuolo().isMago(); }

    private int getPosizioneGuaritore()
    {
        int posizione = NON_TROVATO;
        for(int i = 0; i < getNumeroGiocatori() && posizione == NON_TROVATO; i++) if(isGuaritore(i)) posizione = i;
        return posizione;
    }

    private boolean isGuaritore(int posizione) { return getGiocatore(posizione).isGuaritore(); }

    private int getPosizioneCappuccettoRosso()
    {
        int posizione = NON_TROVATO;
        for(int i = 0; i < getNumeroGiocatori() && posizione == NON_TROVATO; i++) if(isCappuccettoRosso(i)) posizione = i;
        return posizione;
    }

    private boolean isCappuccettoRosso(int posizione) { return getGiocatore(posizione).isCappuccettoRosso(); }

    private int getPosizioneNonna()
    {
        int posizione = NON_TROVATO;
        for(int i = 0; i < getNumeroGiocatori() && posizione == NON_TROVATO; i++) if(isNonna(i)) posizione = i;
        return posizione;
    }

    private boolean isNonna(int posizione) { return getGiocatore(posizione).isNonna(); }

    private int getPosizioneBecchino()
    {
        int posizione = NON_TROVATO;
        for(int i = 0; i < getNumeroGiocatori() && posizione == NON_TROVATO; i++) if(isBecchino(i)) posizione = i;
        return posizione;
    }

    private boolean isBecchino(int posizione) { return getRuolo(posizione).isBecchino(); }

    private boolean isTemplare(String nome) { return getGiocatore(nome).getRuolo().isTemplare(); }

    private int getPosizioneCacciatore()
    {
        int posizione = NON_TROVATO;
        for(int i = 0; i < getNumeroGiocatori() && posizione == NON_TROVATO; i++) if(isCacciatore(i)) posizione = i;
        return posizione;
    }

    private boolean isCacciatore(int posizione) { return getGiocatore(posizione).isCacciatore(); }

    private int getPosizioneCapoGilda()
    {
        int posizione = NON_TROVATO;
        for(int i = 0; i < getNumeroGiocatori() && posizione == NON_TROVATO; i++) if(isCapoGilda(i)) posizione = i;
        return posizione;
    }

    private boolean isCapoGilda(int posizione) { return getGiocatore(posizione).isCapoGilda(); }

    private boolean isSenzaFazione(int posizione) { return getFazione(posizione) == NESSUNA; }

    private Fazione getFazione(int posizione) { return getGiocatore(posizione).getFazione(); }

    private int getPosizioneNosferatu()
    {
        int posizione = NON_TROVATO;
        for(int i = 0; i < getNumeroGiocatori() && posizione == NON_TROVATO; i++) if(isNosferatu(i)) posizione = i;
        return posizione;
    }

    private boolean isNosferatu(int posizione) { return getGiocatore(posizione).isNosferatu(); }

    private int getPosizioneLupoSolitario()
    {
        int posizione = NON_TROVATO;
        for(int i = 0; i < getNumeroGiocatori() && posizione == NON_TROVATO; i++) if(isLupoSolitario(i)) posizione = i;
        return posizione;
    }

    private boolean isLupoSolitario(int posizione) { return getGiocatore(posizione).isLupoSolitario(); }

    private Ruolo getBracconiere() { return getGiocatore(getNomeBracconiere()).getRuolo(); }

    private int getPosizioneBracconiere()
    {
        int posizione = NON_TROVATO;
        for(int i = 0; i < getNumeroGiocatori() && posizione == NON_TROVATO; i++) if(isBracconiere(i)) posizione = i;
        return posizione;
    }

    private boolean isBracconiere(int posizione) { return getGiocatore(posizione).isBracconiere(); }

    private boolean isMistico(int posizione) { return isMistico(getNomeGiocatore(posizione)); }

    private boolean isLupo(int posizione) { return getGiocatore(posizione).isLupo(); }

    private boolean isCriminale(int posizione) { return getGiocatore(posizione).isCriminale(); }

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

    private boolean isNegromante(int posizione) { return getGiocatore(posizione).isNegromante(); }

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
        caricaAccusabili(ballottaggio);
        sistemazioneBallottaggio(ballottaggio);
        return ballottaggio;
    }

    private void sistemazioneBallottaggio(Ballottaggio ballottaggio)
    {
        if(isAmatoSalvabile(ballottaggio)) scambioAngeloAmato(ballottaggio);
    }

    private void scambioAngeloAmato(Ballottaggio ballottaggio)
    {
        salvataggioAmatoDaBallottaggio(ballottaggio);
        if(isAngeloCustodePresente()) accusaAngeloCustode(ballottaggio);
    }

    private boolean isAmatoSalvabile(Ballottaggio ballottaggio)
    {
        return ballottaggio.isAmatoPresente() && isAngeloCustodePresente(ballottaggio);
    }

    private boolean isAngeloCustodePresente(Ballottaggio ballottaggio)
    {
        return isAngeloCustodePresente() || ballottaggio.isAngeloCustodePresente();
    }

    private void accusaAngeloCustode(Ballottaggio ballottaggio)
    {
        String nomeAngeloCustode = getNomeAngeloCustode();
        Giocatore angeloCustode = getGiocatore(nomeAngeloCustode);
        ballottaggio.aggiungiGiocatore(nomeAngeloCustode, angeloCustode);
        eliminaGiocatore(nomeAngeloCustode);
    }

    private void salvataggioAmatoDaBallottaggio(Ballottaggio ballottaggio)
    {
        String nomeAmato = ballottaggio.getNomeAmato();
        aggiungiGiocatore(nomeAmato, ballottaggio.getGiocatore(nomeAmato));
        ballottaggio.eliminaGiocatore(nomeAmato);
    }

    private void estraiSecondoPosto(Ballottaggio ballottaggio)
    {
        if(isBallottaggioIncompleto(ballottaggio)) aggiungiGiocatoriBallottaggio(ballottaggio, getNumeroVotiPrimoClassificato());
    }

    private boolean isBallottaggioIncompleto(Ballottaggio ballottaggio)
    {
        return ballottaggio.getNumeroGiocatori() < 2 && getNumeroVotiPrimoClassificato() > 0;
    }

    private void caricaAccusabili(Ballottaggio ballottaggio)
    {
        for(String nome : getNomiGiocatoriAccusabili()) mandaBallottaggio(ballottaggio, nome);
    }

    private void mandaBallottaggio(Giocatori ballottaggio, String nome)
    {
        Giocatore giocatore = getGiocatore(nome);
        eliminaGiocatore(nome);
        ballottaggio.aggiungiGiocatore(nome, giocatore);
    }

    private void aggiungiGiocatoriBallottaggio(Giocatori ballottaggio, int numeroVoti)
    {
        for(String nome : getNomiGiocatoriVoti(numeroVoti)) mandaBallottaggio(ballottaggio, nome);
    }

    private String[] getNomiGiocatoriAccusabili()
    {
        return getNomiGiocatoriCondizione(nome -> getGiocatore(nome).isAccusabile());
    }

    private String[] getNomiGiocatoriVoti(int numeroVoti)
    {
        return getNomiGiocatoriCondizione(nome -> getNumeroVoti(nome) == numeroVoti);
    }

    private String[] getNomiGiocatoriCondizione(Predicate<String> predicato)
    {
        return stream(getNomiGiocatoriVivi()).filter(predicato).toList().toArray(new String[0]);
    }

    private String[] getNomiGiocatoriVivi()
    {
        String[] nomi = new String[getNumeroGiocatori()];
        for(int i = 0; i < nomi.length; i++) nomi[i] = getNomeGiocatore(i);
        return nomi;
    }

    private EsitoAttacco vampirizzazioneRuolo(String nome) { return getGiocatore(nome).vampirizzazione(); }

    private boolean isAssassino(String nome) { return getGiocatore(nome).getRuolo().isAssassino(); }

    private boolean isGuardia(int posizione) { return isGuardia(getNomeGiocatore(posizione)); }

    private Giocatore getGiocatore(int posizione) { return getGiocatore(getNomeGiocatore(posizione)); }

}