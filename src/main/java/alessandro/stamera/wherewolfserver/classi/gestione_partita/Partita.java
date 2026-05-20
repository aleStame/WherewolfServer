package alessandro.stamera.wherewolfserver.classi.gestione_partita;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Misticismo;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.RuoliFactory;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import java.util.ArrayList;
import java.util.List;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.MORTO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.RIUSCITO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.SCONFITTA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Misticismo.MISTICO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Misticismo.NON_MISTICO;

public final class Partita
{

    public static final RuoliFactory FACTORY = new RuoliFactory();

    private final GiocatoriVivi vivi;

    private final Ballottaggio ballottaggio;

    private final GiocatoriEliminati eliminati;

    private final GiocatoriMortiNotte mortiNotte;

    private Aura ultimoControllo;

    private final List<String> votantiContadinoMostro;

    private boolean pazzoUcciso;

    private int numeroNotte;

    public Partita(String[][] giocatori)
    {
        vivi = new GiocatoriVivi();
        eliminati = new GiocatoriEliminati();
        FACTORY.annullaSegnalazioni();
        for(String[] giocatore : giocatori) aggiungiGiocatoreVivo(giocatore[0], FACTORY.getRuolo(giocatore[1]));
        ultimoControllo = NERA;
        ballottaggio = new Ballottaggio();
        mortiNotte = new GiocatoriMortiNotte();
        setPazzoUcciso(false);
        perdiProtezioniCappuccettoRosso();
        votantiContadinoMostro = new ArrayList<>();
        numeroNotte = 1;
    }

    public void incrementaVoti(String nome, int numeroVoti)
    {
        if(vivi.isPresente(nome)) vivi.incrementaVoti(nome, numeroVoti);
        else incrementaVotiBallottaggio(nome, numeroVoti);
    }

    public void terminaVotazioni()
    {
        Ballottaggio temp = vivi.getBallottaggio();
        for(int i = 0; i < temp.getNumeroGiocatori(); i++)
        {
            String nome = temp.getNomeGiocatore(i);
            ballottaggio.aggiungiGiocatore(nome, temp.getRuolo(nome));
        }
    }

    public void terminaBallottaggio()
    {
        try { eliminaPerdente(); } catch(IllegalArgumentException ignored) {  } finally { svuotaBallottaggio(); }
    }

    public boolean isAccusato(String nome) { return ballottaggio.isPresente(nome); }

    public void segnalazioneAngeloCustode(String nome) { vivi.segnalazioneAngeloCustode(nome); }

    public void attaccoAssassino(String nome)
    {
        switch(vivi.attaccoAssassino(nome))
        {
            case RIUSCITO -> eliminaGiocatore(nome);
            case FALLITO -> eliminazioneAngeloCustode();
            case MORTO -> assassinioContadinoMostro(nome);
        }
    }

    public boolean isEliminato(String nome) { return eliminati.isPresente(nome); }

    public boolean isVivo(String nome) { return vivi.isPresente(nome); }

    public void attaccoLupi(String nomeLupo, String nome)
    {
        if(pazzoUcciso) throw new IllegalStateException("Il Pazzo è morto. L'attacco dei lupi non può essere eseguito.");
        if(vivi.isPotereBracconiereUtilizzato()) gestisciPotereBracconiere();
        switch(attaccoLupi(FACTORY.getRuolo(nomeLupo), nome))
        {
            case RIUSCITO -> eliminaGiocatore(nome);
            case MORTO -> doppiaEliminazione(nomeLupo, nome);
        }
    }

    public void segnalazioneAzzeccagarbugli(String nome) { vivi.segnalazioneAzzeccagarbugli(nome); }

    public void segnalazioneInquisitore(String nome) { vivi.segnalazioneInquisitore(nome); }

    public boolean isFinita() { return false; }

    public boolean isGiuliettaViva() { return false; }

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
        switch(mortiNotte.progenizzazioneNosferatu(nome))
        {
            case RIUSCITO -> risorgiGiocatore(nome);
            case MORTO -> nosferatuControLupo(nome);
        }
    }

    public int getNumeroSenzaFazioneVivi() { return vivi.getNumeroSenzaFazione(); }

    public int getNumeroLupiVivi() { return vivi.getNumeroLupi(); }

    public void gildata(String nome)
    {
        EsitoAttacco esito = vivi.gildata(nome);
        if(esito == MORTO) eliminaGiocatore(vivi.getNomeCapoGilda());
    }

    public int getNumeroGiocatoriVivi() { return vivi.getNumeroGiocatori(); }

    public void riconosciNegromante() { vivi.riconosciNegromante(); }

    public boolean isCrociataAvviata() { return vivi.isCrociataAvviata(); }

    public void guarisci(String nome)
    {
        aggiungiGiocatoreVivo(nome, getRuoloMortoNotte(nome));
        mortiNotte.eliminaGiocatore(nome);
        if(vivi.isContadinoMostro(nome)) eliminaGuaritore();
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
        numeroNotte++;
    }

    public Misticismo controlloMago(String nome)
    {
        Misticismo misticismo = NON_MISTICO;
        if(getRuoloVivo(nome).isMistico()) misticismo = MISTICO;
        if(vivi.isContadinoMostro(nome) && getNumeroNotte() > 1)
        {
            System.out.println("eccoci");
            int posizione = -1;
            for(int i = 0; i < getNumeroGiocatoriVivi() && posizione == -1; i++) if(getRuoloVivo(vivi.getNomeGiocatore(i)).isMago()) posizione = i;
            eliminaGiocatore(vivi.getNomeGiocatore(posizione));
        }
        return misticismo;
    }

    private void confermaEliminazioneMortiNotte()
    {
        String[] nomi = new String[mortiNotte.getNumeroGiocatori()];
        for(int i = 0; i < nomi.length; i++) nomi[i] = mortiNotte.getNomeGiocatore(i);
        for(String nome : nomi) confermaEliminazioneMortoNotte(nome);
        perdiProtezioniCappuccettoRosso();
    }

    private void confermaEliminazioneMortoNotte(String nome)
    {
        eliminati.aggiungiGiocatore(nome, getRuoloMortoNotte(nome));
        eliminaGiocatoreMortoNotte(nome);
    }

    private void eliminaGuaritore() { eliminaGiocatore(vivi.getNomeGuaritore()); }

    private void nosferatuControLupo(String nome)
    {
        eliminaGiocatore(vivi.getNomeNosferatu());
        if(mortiNotte.isLupo(nome)) risorgiGiocatore(nome);
    }

    private void perdiProtezioniCappuccettoRosso()
    {
        if(vivi.isCappuccettoRossoPresente() && !vivi.isNonnaPresente()) vivi.annullaProtezioniCappuccettoRosso();
    }

    private void risorgiGiocatore(String nome)
    {
        aggiungiGiocatoreVivo(nome, getRuoloMortoNotte(nome));
        eliminaGiocatoreMortoNotte(nome);
    }

    private EsitoAttacco attaccoLupi(Ruolo ruolo, String nome)
    {
        EsitoAttacco esito = vivi.attaccoLupi(ruolo, nome);
        if(esito == RIUSCITO && isProtezineUltimoLupoAttiva()) esito = MORTO;
        return esito;
    }

    private boolean isProtezineUltimoLupoAttiva() { return vivi.isCacciatorePresente() && vivi.isCacciatoreProtetto(); }

    private void doppiaEliminazione(String nomeLupo, String nome)
    {
        eliminaGiocatore(nome);
        boolean fatto = false;
        for(int i = 0; i < getNumeroGiocatoriVivi() && !fatto; i++)
        {
            String x = vivi.getNomeGiocatore(i);
            if(getRuoloVivo(x).getNome().equals(nomeLupo))
            {
                eliminaGiocatore(x);
                fatto = true;
            }
        }
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
        aggiungiGiocatoreVivo(nome, ballottaggio.getRuolo(nome));
        ballottaggio.eliminaGiocatore(nome);
        perdiProtezioniCappuccettoRosso();
    }

    private void aggiungiGiocatoreVivo(String nome, Ruolo ruolo) { vivi.aggiungiGiocatore(nome, ruolo); }

    private EsitoPartita getEsitoPartitaNegromante() { return getNegromante().getEsitoPartita(this); }

    private Ruolo getNegromante() { return getRuoloVivo(vivi.getNomeNegromante()); }

    private boolean controllaNumeroGuardie(int valore) { return confrontaValori(vivi.getNumeroGuardie(), valore); }

    private boolean controllaNumeroCreatureOmbra(int valore) { return confrontaValori(getNumeroCreatureOmbraVive(), valore); }

    private boolean confrontaValori(int valore1, int valore2) { return valore1 == valore2; }

    private void eliminaGiocatoreMortoNotte(String nome) { mortiNotte.eliminaGiocatore(nome); }

    private void assassinioContadinoMostro(String nome)
    {
        if(getNumeroNotte() > 1) eliminaGiocatori(nome, vivi.getNomeAssassino());
        else eliminaGiocatore(nome);
    }

    private void eliminazioneAngeloCustode() { eliminaGiocatore(getNomeAngeloCustode()); }

    private void eliminaGiocatori(String... nomi) { for(String nome : nomi) eliminaGiocatore(nome); }

    private void eliminaGiocatore(String nome)
    {
        mortiNotte.aggiungiGiocatore(nome, getRuoloVivo(nome));
        vivi.eliminaGiocatore(nome);
        setPazzoUcciso(mortiNotte.isPazzo(nome));
    }

    private void setPazzoUcciso(boolean pazzoUcciso) { this.pazzoUcciso = pazzoUcciso; }

    private String getNomeAngeloCustode()
    {
        String nome;
        if(vivi.isAngeloCustodePresente()) nome = vivi.getNomeAngeloCustode();
        else nome = eliminati.getNomeAngeloCustode();
        return nome;
    }

    private Ruolo getRuoloVivo(String nome) { return vivi.getRuolo(nome); }

    private Ruolo getRuoloMortoNotte(String nome) { return mortiNotte.getRuolo(nome); }

}