package alessandro.stamera.wherewolfserver.classi.gestione_partita;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.*;
import alessandro.stamera.wherewolfserver.classi.eccezioni.*;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.RuoliFactory;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.RuoloNullo;
import jakarta.annotation.Nonnull;

import java.util.ArrayList;
import java.util.List;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.*;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoControlloSensitiva.VILLAGGIO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.SCONFITTA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.VITTORIA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Misticismo.NON_MISTICO;
import static java.util.Arrays.stream;

public final class Partita
{

    public static final RuoliFactory FACTORY = new RuoliFactory();

    private final GiocatoriVivi vivi;

    private final Ballottaggio ballottaggio;

    private final GiocatoriEliminati eliminati;

    private final GiocatoriMortiNotte mortiNotte;

    private Aura ultimoControllo;

    private final List<String> votantiContadinoMostro;

    private final String[] maledettiNegromante;

    private boolean pazzoUcciso, potereStregaUsato, crociataAvviata;

    private int numeroNotte;

    public Partita(String[][] giocatori)
    {
        vivi = new GiocatoriVivi();
        eliminati = new GiocatoriEliminati();
        inizializzaGiocatori(giocatori);
        ultimoControllo = NERA;
        ballottaggio = new Ballottaggio();
        mortiNotte = new GiocatoriMortiNotte();
        setPazzoUcciso(false);
        perdiProtezioniCappuccettoRosso();
        votantiContadinoMostro = new ArrayList<>();
        numeroNotte = 1;
        potereStregaUsato = false;
        maledettiNegromante = new String[2];
        crociataAvviata = false;
    }

    public void incrementaVoti(String nome, int numeroVoti)
    {
        if(isVivo(nome)) vivi.incrementaVoti(nome, numeroVoti);
        else incrementaVotiBallottaggio(nome, numeroVoti);
    }

    public void terminaVotazioni()
    {
        Ballottaggio temp = vivi.getBallottaggio();
        for(int i = 0; i < temp.getNumeroGiocatori(); i++)
        {
            String nome = temp.getNomeGiocatore(i);
            ballottaggio.aggiungiGiocatore(nome, temp.getGiocatore(nome));
        }
    }

    public void terminaBallottaggio()
    {
        try { eliminaPerdente(); } catch(IllegalArgumentException ignored) {  } finally { svuotaBallottaggio(); }
        perdiProtezioniCappuccettoRosso();
    }

    public boolean isAccusato(String nome) { return ballottaggio.isPresente(nome); }

    public void segnalazioneAngeloCustode(String nome) { vivi.segnalazioneAngeloCustode(nome); }

    public void attaccoAssassino(String nome)
    {
        switch(vivi.attaccoAssassino(nome))
        {
            case RIUSCITO -> eliminaGiocatore(nome);
            case ANGELO_CUSTODE_MORTO -> gestisciAssassinioAmato();
            case MORTO -> assassinioContadinoMostro(nome);
        }
    }

    private void gestisciAssassinioAmato()
    {
        String nomeAngeloCustode = getNomeAngeloCustodeVivo();
        eliminazioneAngeloCustode();
        throw new EccezioneAssassinoAmato(vivi.getNomeAmato(), vivi.getNomeAssassino(), nomeAngeloCustode);
    }

    public boolean isEliminato(String nome) { return eliminati.isPresente(nome); }

    public boolean isVivo(String nome) { return vivi.isPresente(nome); }

    public void attaccoLupi(String tipoLupo, String nome)
    {
        gestisciEccezioniAttaccoLupi();
        String nomeLupo = getNomeGiocatoreLupo(tipoLupo);
        switch(attaccoLupi(getGiocatore(nomeLupo).getRuolo(), nome))
        {
            case RIUSCITO, TROVATO_POSSEDUTO -> eliminaGiocatore(nome);
            case MORTO -> doppiaEliminazione(nomeLupo, nome);
            case FALLITO -> nessunaEliminazione(nome);
            case NONNA_BECCATA -> lupizzazioneNonna(nome, nomeLupo, tipoLupo);
            case CONTADINO_LUPO_BECCATO -> throw new EccezioneContadinoLupo(nome, getFazione(nomeLupo).toString());
            case ANGELO_CUSTODE_MORTO -> eccezioneMorteAngeloCustode(tipoLupo, nome, nomeLupo);
            case ULTIMO_LUPO_SVEGLIA_CAPPUCCETTO_ROSSO -> throw new EccezioneCappuccettoRosso(tipoLupo, nomeLupo, nome);
            case ULTIMO_LUPO_UCCIDE_CAPPUCCETTO_ROSSO -> eccezioneEliminazioneCappuccettoRosso(tipoLupo, nome, nomeLupo);
        }
    }

    //public void segnalazioneAzzeccagarbugli(String nome) { vivi.segnalazioneAzzeccagarbugli(nome); }

    public void segnalazioneInquisitore(String nome) { vivi.segnalazioneInquisitore(nome); }

    public boolean isViaggioPartito() { return false; }

    public boolean isViaggiatoreAmato() { return false; }

    public boolean isSoloCreatureOmbra() { return controllaNumeroCreatureOmbra(getNumeroGiocatoriVivi()); }

    public boolean isSoloGuardie() { return controllaNumeroGuardie(getNumeroGiocatoriVivi()); }

    public boolean isNoGuardie() { return controllaNumeroGuardie(0); }

    public boolean isNoCreatureOmbra() { return controllaNumeroCreatureOmbra(0); }

    public int getNumeroCreatureOmbraVive() { return vivi.getNumeroCreatureOmbra(); }

    public Aura getControlloVeggente(String nome)
    {
        ultimoControllo = vivi.getControlloVeggente(nome);
        return ultimoControllo;
    }

    public boolean getCantoBardo()
    {
        boolean esito = !eliminati.isBardoPresente();
        if(esito) esito = (ultimoControllo == BIANCA);
        return esito;
    }

    public int getNumeroCriminali() { return vivi.getNumeroCriminali(); }

    public boolean isNoGiocatoriVivi() { return confrontaValori(getNumeroGiocatoriVivi(), 0); }

    public boolean isNegromantePresente() { return vivi.isNegromantePresente(); }

    public EsitoPartita isNegromanteVincitore()
    {
        EsitoPartita esito = SCONFITTA;
        if(vivi.isNegromantePresente()) esito = getEsitoPartitaNegromante();
        return esito;
    }

    public boolean isMisticiPresenti() { return vivi.getNumeroMistici() > 0; }

    public void segnalazioneOratore(String nome) { ballottaggio.segnalazioneOratore(nome); }

    public boolean segnalazioneBorgomastroAvvenuta() { return ballottaggio.isSegnalazioneBorgomastroAvvenuta(); }

    public void segnalazioneBorgomastro(String nome)
    {
        int numeroVoti = getNumeroRuoliCittaPresenti();
        if(ballottaggio.isContadinoMostro(nome)) numeroVoti = 1;
        incrementaVotiBallottaggio(nome, numeroVoti);
        ballottaggio.segnalazioneBorgomastro();
    }

    public void segnalazioneBracconiere() { vivi.utilizzaPotereBracconiere(); }

    public void progenizzazioneNosferatu(String nome)
    {
        String nomeNosferatu = vivi.getNomeNosferatu();
        switch(attaccoNosferatu(nome))
        {
            case RIUSCITO -> nosferatizzazione(nome);
            case MORTO -> morteNosferatu(nome);
            case GHOUL_MORTO -> gestioneMorteGhoul(nome, nomeNosferatu);
            case TROVATO_POSSEDUTO -> gestionePosseduto(nome, nomeNosferatu);
        }
    }

    public int getNumeroSenzaFazioneVivi() { return vivi.getNumeroSenzaFazione(); }

    public int getNumeroLupiBrancoVivi() { return vivi.getNumeroLupiBranco(); }

    public void gildata(String nome)
    {
        EsitoAttacco esito = vivi.gildata(nome);
        if(esito != RIUSCITO) gestioneAttaccoNonRiuscito(nome, esito);
    }

    public int getNumeroGiocatoriVivi() { return vivi.getNumeroGiocatori(); }

    public void riconosciNegromante() { vivi.riconosciNegromante(); }

    public boolean isCrociataAvviata() { return crociataAvviata; }

    public void guarisci(String nome)
    {
        aggiungiGiocatoreVivo(nome, getGiocatore(nome));
        mortiNotte.eliminaGiocatore(nome);
        if(vivi.isMegera(nome)) maledizioneGuaritore();
        else if(vivi.isContadinoMostro(nome)) eliminaGuaritore();
    }

    public void incrementaVotiContadinoMostro(String nome)
    {
        votantiContadinoMostro.add(nome);
        String nomeContadino = ballottaggio.getNomeContadinoMostro();
        ballottaggio.annullaVoti(nomeContadino);
        incrementaVoti(nomeContadino, votantiContadinoMostro.size());
    }

    public String[] getVotatiContadinoMostro() { return votantiContadinoMostro.toArray(new String[0]); }

    public void contrattaccoContadinoMostro(String nome)
    {
        eliminaGiocatore(nome);
        votantiContadinoMostro.clear();
        confermaEliminazioneMortiNotte();
    }

    public int getNumeroNotte() { return numeroNotte; }

    public void terminaNotte()
    {
        confermaEliminazioneMortiNotte();
        if(isAmatoSenzaAngelo()) perditaProtezioniAmato();
        numeroNotte++;
    }

    private void perditaProtezioniAmato() { getGiocatoreAmato().perdiProtezioni(); }

    private boolean isAmatoSenzaAngelo() { return eliminati.isAngeloCustodePresente() && isAmatoVivo(); }

    private Giocatore getGiocatoreAmato() { return getGiocatore(vivi.getNomeAmato()); }

    public Misticismo controlloMago(String nome)
    {
        Misticismo misticismo = eseguiControlloMago(nome);
        gestisciInterazioniMago(nome);
        return misticismo;
    }

    public void attaccoNegromante(String nome)
    {
        switch(vivi.attaccoNegromante(nome))
        {
            case MORTO -> eliminaGiocatore(getNomeNegromante());
            case FALLITO -> throw new IllegalStateException("Scegli un'altra persona da attaccare.");
            case RIUSCITO -> maledettiNegromante[getPosizioneLiberaMaledettiNegromante()] = nome;
        }
    }

    public void segnalazioneAzzeccagarbugli(String nome) { vivi.segnalazioneAzzeccagarbugli(nome); }

    public boolean isLupoBranco(String nome) { return vivi.isLupoBranco(nome); }

    public boolean isLupoReietto(String nome) { return getGiocatore(nome).getRuolo().isLupoReietto(); }

    public boolean isLupoSolitario(String nome) { return getGiocatore(nome).getRuolo().isLupoSolitario(); }

    private void eccezioneEliminazioneCappuccettoRosso(String tipoLupo, String nome, String nomeLupo)
    {
        eliminaGiocatore(nome);
        throw new EccezioneCappuccettoRosso(tipoLupo, nomeLupo, nome);
    }

    private void eccezioneMorteAngeloCustode(String tipoLupo, String nome, String nomeLupo)
    {
        eliminazioneAngeloCustode();
        throw new EccezioneAttaccoAmato(tipoLupo, nomeLupo, getNomeRuolo(nome), nome, mortiNotte.getNomeAngeloCustode());
    }

    private void gestisciEccezioniAttaccoLupi()
    {
        if(pazzoUcciso) throw new IllegalStateException("Il Pazzo è morto. L'attacco dei lupi non può essere eseguito.");
        if(vivi.isPotereBracconiereUtilizzato()) gestisciPotereBracconiere();
    }

    private void nosferatizzazione(String nome)
    {
        boolean esito = mortiNotte.isAngeloCustode(nome);
        risorgiGiocatore(nome);
        if(esito) vivi.resettaAmato();
    }

    private EsitoAttacco attaccoNosferatu(String nomeVittima)
    {
        EsitoAttacco esito = mortiNotte.progenizzazioneNosferatu(nomeVittima);
        if(esito == MORTO && isGhoulPresente()) esito = GHOUL_MORTO;
        return esito;
    }

    private void lupizzazioneNonna(String nomeNonna, String nomeLupo, String tipoLupo)
    {
        vivi.assorbiRuolo(nomeNonna, nomeLupo);
        eliminati.aggiungiGiocatore(nomeLupo, new Giocatore(RuoloNullo.getInstance()));
        if(vivi.isCacciatorePresente()) getGiocatoreCacciatore().aggiungiProtezione(getGiocatore(nomeNonna).getRuolo());
        throw new EccezioneNonnaBeccata(nomeLupo, tipoLupo, nomeNonna);
    }

    private Giocatore getGiocatoreCacciatore() { return getGiocatore(vivi.getNomeCacciatore()); }

    private void nessunaEliminazione(String nome)
    {
        if(vivi.isEremita(nome)) throw new IllegalArgumentException(nome + " è l'Eremita, i lupi non possono ucciderlo.");
        else if(vivi.isCacciatore(nome))
            throw new IllegalStateException(nome + " è il Cacciatore ed è protetto dall'attacco del lupo ex Nonna.\nAvvisa i lupi dell'attacco fallito.");
        else if(!vivi.isLupo(nome))
        {
            boolean valore = vivi.isRomeo(nome);
            if(potereStregaUsato) valore = false;
            throw new EccezioneAttaccoGiocatoreProtetto(valore, nome);
        }
    }

    private void gestisciInterazioniMago(String nome)
    {
        if(vivi.isMegera(nome)) malediciMago();
        else if(morteMagoConContadinoMostro(nome)) eliminaMago();
    }

    private void eliminaMago() { eliminaGiocatore(getNomeMagoVivo()); }

    private boolean morteMagoConContadinoMostro(String nome) { return vivi.isContadinoMostro(nome) && !isPrimaNotte(); }

    private boolean isPrimaNotte() { return getNumeroNotte() == 1; }

    private String getNomeMagoVivo() { return vivi.getNomeMago(); }

    private int getPosizioneLiberaMaledettiNegromante()
    {
        int posizione = 0;
        if(maledettiNegromante[posizione] != null) posizione = 1;
        return posizione;
    }

    public void romeizzazione(String nome)
    {
        vivi.romeizzazione(nome);
        if(vivi.isRomeo(nome)) vivi.aggiungiProtezioneCreatureOmbra(nome);
    }

    public EsitoControlloSensitiva controlloSensitiva(String nome)
    {
        EsitoControlloSensitiva esito = vivi.controlloSensitiva(nome);
        if(esito == VILLAGGIO && isSensitivaEliminabile(nome)) eliminaSensitiva();
        return esito;
    }

    private boolean isSensitivaEliminabile(String nome) { return isPrimaNotte() || vivi.isContadinoMostro(nome); }

    private void eliminaSensitiva() { eliminaGiocatore(vivi.getNomeSensitiva()); }

    public String getNomeGiocatoreVivo(int posizione) { return vivi.getNomeGiocatore(posizione); }

    public boolean isGhoulVivo(String nome) { return vivi.isGhoul(nome); }

    public boolean isNosferatuVivo(String nome) { return vivi.isNosferatu(nome); }

    public boolean isProgenieNosferatuViva(String nome) { return vivi.isProgenieNosferatu(nome); }

    public boolean isLupoReiettoVivo() { return vivi.isLupoReiettoPresente(); }

    public boolean isLupoAttaccanteVivo() { return vivi.isCapoBrancoPresente() || vivi.isLupoBrancoPresente(); }

    public boolean isCriminaliPresenti() { return getNumeroCriminali() > 0; }

    public boolean isAmatoVivo() { return vivi.isAmatoPresente(); }

    public boolean isFazioneNosferatu(String nome)
    {
        boolean esito = false;
        if(isVivo(nome)) esito = vivi.isFazioneNosferatu(nome);
        return esito;
    }

    public boolean isNosferatuVincitore()
    {
        boolean esito = false;
        if(isNosferatuPresente()) esito = verificaVittoriaNosferatu();
        return esito;
    }

    private boolean isNosferatuPresente() { return vivi.isNosferatuPresente() || eliminati.isNosferatuPresente(); }

    public Aura controlloMedium(String nome) { return eliminati.controlloMedium(nome); }

    public void attaccoVampiro(String nome)
    {
        String nomeVampiro = getNomeVampiroVivo();
        switch(vivi.attaccoVampiro(nome))
        {
            case FALLITO -> throw new IllegalArgumentException("Impossibile vampirizzare " + nome + ".");
            case MORTO -> gestioneMorteVampiro(nome);
            case TROVATO_POSSEDUTO -> gestionePosseduto(nome, nomeVampiro);
            case GHOUL_MORTO -> gestioneMorteGhoul(nome, nomeVampiro);
        }
    }

    public boolean isMaledetto(String nome) { return vivi.isMaledetto(nome); }

    public void passaPosseduto(String nome)
    {
        switch(vivi.passaPosseduto(nome))
        {
            case FALLITO -> throw new IllegalArgumentException("Impossibile possedere " + nome + ".");
            case MORTO -> throw new IllegalArgumentException("Impossibile possedere il Prete.");
            case RIUSCITO -> gestionePassaggioPossedutoRiuscito(nome);
        }
    }

    public boolean isPosseduto(String nome) { return getGiocatore(nome).getRuolo().isPosseduto(); }

    public void protezioneStrega(String nome)
    {
        vivi.protezioneStrega(nome);
        potereStregaUsato = true;
    }

    public boolean isCapoBranco(String nome) { return vivi.getGiocatore(nome).getRuolo().isCapoBranco(); }

    public Aura getAura(String nome) { return getGiocatore(nome).getAura(); }

    public Fazione getFazione(String nome) { return getGiocatore(nome).getFazione(); }

    public boolean isAmato(String nome) { return getGiocatore(nome).isAmato(); }

    private void gestionePassaggioPossedutoRiuscito(String nome)
    {
        if(vivi.isAngeloCustode(nome)) annullaProtezioneAngeloCustode();
        possessione(nome);
    }

    private void possessione(String nome) { getGiocatore(nome).cambiaRuolo(getPossedutoMortoNotte()); }

    private void annullaProtezioneAngeloCustode() { getGiocatoreAmato().annullaProtezioneAngeloCustode(); }

    private Ruolo getPossedutoMortoNotte() { return getGiocatorePossedutoMorto().getRuolo(); }

    private Giocatore getGiocatorePossedutoMorto() { return getGiocatore(mortiNotte.getNomePosseduto()); }

    private void gestionePosseduto(String nomePosseduto, String nomeProgenizzatore)
    {
        if(vivi.isVampiro(nomeProgenizzatore)) eliminaGiocatori(nomePosseduto);
        eccezioneProgenizzazionePosseduto(nomePosseduto, nomeProgenizzatore);
    }

    private void eccezioneProgenizzazionePosseduto(String nomePosseduto, String nomeProgenizzatore)
    {
        String ruoloProgenizzatore = getNomeRuolo(nomeProgenizzatore);
        possessione(nomeProgenizzatore);
        confermaEliminazioneMortiNotte();
        throw new EccezioneProgenizzazionePosseduto(ruoloProgenizzatore, nomeProgenizzatore, nomePosseduto);
    }

    private boolean verificaVittoriaNosferatu() { return isPartitaVinta(getNosferatu()); }

    private Ruolo getNosferatu() { return getGiocatoreNosferatu().getRuolo(); }

    private Giocatore getGiocatoreNosferatu() { return getGiocatore(getNomeNosferatu()); }

    private String getNomeNosferatu()
    {
        String risultato;
        if(vivi.isNosferatuPresente()) risultato = vivi.getNomeNosferatu();
        else risultato = eliminati.getNomeNosferatu();
        return risultato;
    }

    private void malediciMago() { vivi.maledizione(getNomeMagoVivo()); }

    private Misticismo eseguiControlloMago(String nome)
    {
        Misticismo misticismo = vivi.controlloMago(nome);
        String nomeMago = getNomeMagoVivo();
        if(isMaledetto(nomeMago)) misticismo = NON_MISTICO;
        return misticismo;
    }

    private void maledizioneGuaritore() { vivi.maledizione(vivi.getNomeGuaritore()); }

    private void gestioneMorteGhoul(String nomeVittima, String nomeProgenizzatore)
    {
        String nomeGhoul = vivi.getNomeGhoul();
        eliminaGhoul();
        throw new EccezioneProgenizzazioneNonRiuscitaConGhoul(getNomeRuolo(nomeVittima), nomeVittima, getNomeRuolo(nomeProgenizzatore), nomeGhoul);
    }

    private void gestioneMorteVampiro(String nomeLupo)
    {
        String nomeVampiro = getNomeVampiroVivo();
        eliminaGiocatori(nomeVampiro);
        throw new EccezioneMorteProgenizzatore(getNomeRuolo(nomeVampiro), getNomeRuolo(nomeLupo), nomeLupo, nomeVampiro);
    }

    private String getNomeRuolo(String nome)
    {
        String risultato;
        if(isVivo(nome)) risultato = getNomeRuoloVivo(nome);
        else risultato = getNomeRuoloMorto(nome);
        return risultato;
    }

    private String getNomeVampiroVivo() { return vivi.getNomeVampiro(); }

    private String getNomeRuoloVivo(String nomeVittima) { return vivi.getNomeRuolo(nomeVittima); }

    private void gestioneAttaccoNonRiuscito(String nome, EsitoAttacco esito)
    {
        switch(esito)
        {
            case FALLITO -> throw new EccezioneGildata(nome);
            case MORTO -> gestioneMorteCapoGilda(nome);
        }
    }

    private void gestioneMorteCapoGilda(String nome)
    {
        String nomeCapoGilda = vivi.getNomeCapoGilda();
        eliminaGiocatore(nomeCapoGilda);
        throw new EccezioneGildata(nome, nomeCapoGilda);
    }

    private boolean isPartitaVinta(Ruolo ruolo) { return ruolo.getEsitoPartita(this) == VITTORIA; }

    private void eliminaGhoul() { eliminaGiocatore(getNomeGhoul()); }

    private void inizializzaGiocatori(String[][] giocatori)
    {
        stream(giocatori).forEach(giocatore -> aggiungiGiocatoreVivo(giocatore[0], new Giocatore(FACTORY.getRuolo(giocatore[1]))));
    }

    private void confermaEliminazioneMortiNotte()
    {
        for(String nome : getNomiMortiNotte()) confermaEliminazioneMortoNotte(nome);
        perdiProtezioniCappuccettoRosso();
    }

    private String[] getNomiMortiNotte()
    {
        String[] nomi = new String[mortiNotte.getNumeroGiocatori()];
        for(int i = 0; i < nomi.length; i++) nomi[i] = mortiNotte.getNomeGiocatore(i);
        return nomi;
    }

    private void confermaEliminazioneMortoNotte(String nome)
    {
        Giocatore giocatore = getGiocatore(nome);
        eliminati.aggiungiGiocatore(nome, giocatore);
        if(eliminati.isMegera(nome)) annullaMaledizioniMegera();
        else if(giocatore.isNegromante()) annullaMaledizioniNegromante();
        else if(giocatore.isInquisitore() && vivi.isTemplarePresente()) crociataAvviata = true;
        eliminaGiocatoreMortoNotte(nome);
    }

    private void annullaMaledizioniMegera()
    {
        for(int i = 0; i < getNumeroGiocatoriVivi(); i++) annullaMaledizioneMegera(getNomeGiocatoreVivo(i));
    }

    private void annullaMaledizioneMegera(String nome) { if(isMaledettoNonNegromante(nome)) vivi.annullaMaledizione(nome); }

    private boolean isMaledettoNonNegromante(String nome) { return isMaledetto(nome) && !isMaledettoNegromante(nome); }

    private boolean isMaledettoNegromante(String nome) { return stream(maledettiNegromante).toList().contains(nome); }

    private void annullaMaledizioniNegromante()
    {
        for(String nomeMaledetto : maledettiNegromante) if(nomeMaledetto != null) vivi.annullaMaledizione(nomeMaledetto);
    }

    private void eliminaGuaritore() { eliminaGiocatore(vivi.getNomeGuaritore()); }

    private void morteNosferatu(String nomeVittima)
    {
        String nomeNosferatu = vivi.getNomeNosferatu(), ruoloProgenizzatore = getNomeRuolo(nomeNosferatu), ruoloMorto = getNomeRuolo(nomeVittima);
        controlliMorteNosferatu(nomeVittima, nomeNosferatu);
        throw new EccezioneMorteProgenizzatore(ruoloProgenizzatore, ruoloMorto, nomeVittima, nomeNosferatu);
    }

    private String getNomeRuoloMorto(String nomeVittima) { return mortiNotte.getNomeRuolo(nomeVittima); }

    private void controlliMorteNosferatu(String nome, String nomeVittima)
    {
        eliminaGiocatore(nomeVittima);
        if((mortiNotte.isContadinoMostro(nome) && !mortiNotte.isRomeo(nome)) || mortiNotte.isLupo(nome)) risorgiGiocatore(nome);
    }

    private String getNomeGhoul() { return vivi.getNomeGhoul(); }

    private boolean isGhoulPresente() { return vivi.isGhoulPresente(); }

    private void perdiProtezioniCappuccettoRosso()
    {
        if(vivi.isCappuccettoRossoPresente() && !vivi.isNonnaPresente()) vivi.annullaProtezioniCappuccettoRosso();
    }

    private void risorgiGiocatore(String nome)
    {
        aggiungiGiocatoreVivo(nome, getGiocatore(nome));
        eliminaGiocatoreMortoNotte(nome);
    }

    private EsitoAttacco attaccoLupi(Ruolo ruolo, String nome)
    {
        EsitoAttacco esito = vivi.attaccoLupi(ruolo, nome);
        if(esito == RIUSCITO && isProtezioneUltimoLupoAttiva()) esito = MORTO;
        return esito;
    }

    private boolean isProtezioneUltimoLupoAttiva() { return vivi.isCacciatorePresente() && vivi.getNumeroLupiBranco() == 1; }

    private void doppiaEliminazione(String nomeLupo, String nomeVittima)
    {
        eliminaGiocatori(nomeLupo, nomeVittima);
        if(mortiNotte.isContadino(nomeVittima)) throw new EccezioneAttaccoContadino(mortiNotte.getTipoContadino(nomeVittima), nomeVittima, nomeLupo);
    }

    private String getNomeGiocatoreLupo(String tipoLupo) { return getNomeGiocatoreVivo(getPosizioneLupo(tipoLupo)); }

    private int getPosizioneLupo(String tipoLupo)
    {
        int posizione = -1;
        for(int i = 0; i < getNumeroGiocatoriVivi() && posizione == -1; i++)
            if(getGiocatore(getNomeGiocatoreVivo(i)).getRuolo().getNome().equals(tipoLupo)) posizione = i;
        return posizione;
    }

    private void gestisciPotereBracconiere()
    {
        vivi.riabilitaPotereBracconiere();
        throw new IllegalStateException("Potere del Bracconiere in corso. Proibito l'attacco dei lupi.");
    }

    private void incrementaVotiBallottaggio(String nome, int numeroVoti) { ballottaggio.incrementaVoti(nome, numeroVoti); }

    private int getNumeroRuoliCittaPresenti() { return vivi.getNumeroRuoliCitta() + ballottaggio.getNumeroRuoliCitta(); }

    private void svuotaBallottaggio()
    {
        for(int i = 0; i < ballottaggio.getNumeroGiocatori(); i++) terminaBallottaggio(ballottaggio.getNomeGiocatore(i));
    }

    private void eliminaPerdente()
    {
        String nome = ballottaggio.getNomeGiocatorePerdente();
        if(isEccezioneOratore(nome))
            throw new IllegalStateException("Il villaggio non ha trovato accordo su chi mandare al rogo: non viene bruciato nessuno!");
        terminaBallottaggio(nome);
        eliminaGiocatore(nome);
        perdiProtezioniCappuccettoRosso();
    }

    private boolean isEccezioneOratore(String nome) { return ballottaggio.isCitta(nome) && isOratorePresente(); }

    private boolean isOratorePresente() { return vivi.isOratorePresente() || ballottaggio.isOratorePresente(); }

    private void terminaBallottaggio(String nome)
    {
        aggiungiGiocatoreVivo(nome, ballottaggio.getGiocatore(nome));
        ballottaggio.eliminaGiocatore(nome);
        perdiProtezioniCappuccettoRosso();
    }

    private void aggiungiGiocatoreVivo(String nome, Giocatore giocatore) { vivi.aggiungiGiocatore(nome, giocatore); }

    private EsitoPartita getEsitoPartitaNegromante() { return getNegromante().getEsitoPartita(this); }

    private Ruolo getNegromante() { return getGiocatore(getNomeNegromante()).getRuolo(); }

    private String getNomeNegromante() { return vivi.getNomeNegromante(); }

    private boolean controllaNumeroGuardie(int valore) { return confrontaValori(vivi.getNumeroGuardie(), valore); }

    private boolean controllaNumeroCreatureOmbra(int valore) { return confrontaValori(getNumeroCreatureOmbraVive(), valore); }

    private boolean confrontaValori(int valore1, int valore2) { return valore1 == valore2; }

    private void eliminaGiocatoreMortoNotte(String nome) { mortiNotte.eliminaGiocatore(nome); }

    private void assassinioContadinoMostro(String nome)
    {
        if(!isPrimaNotte()) eliminaGiocatori(nome, vivi.getNomeAssassino());
        else eliminaGiocatore(nome);
    }

    private void eliminazioneAngeloCustode() { eliminaGiocatore(getNomeAngeloCustodeVivo()); }

    private void eliminaGiocatori(String... nomi) { for(String nome : nomi) eliminaGiocatore(nome); }

    private void eliminaGiocatore(String nome)
    {
        mortiNotte.aggiungiGiocatore(nome, getGiocatore(nome));
        vivi.eliminaGiocatore(nome);
        setPazzoUcciso(mortiNotte.isPazzo(nome));
    }

    private void setPazzoUcciso(boolean pazzoUcciso) { this.pazzoUcciso = pazzoUcciso; }

    private String getNomeAngeloCustodeVivo() { return vivi.getNomeAngeloCustode(); }

    private Giocatore getGiocatore(String nome)
    {
        Giocatore giocatore;
        if(isVivo(nome)) giocatore = vivi.getGiocatore(nome);
        else if(mortiNotte.isPresente(nome)) giocatore = mortiNotte.getGiocatore(nome);
        else giocatore = eliminati.getGiocatore(nome);
        return giocatore;
    }

}