package alessandro.stamera.wherewolfserver.classi.gestione_partita;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita;
import alessandro.stamera.wherewolfserver.classi.ruoli.Ballottaggio;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.RuoliFactory;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.RIUSCITO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.SCONFITTA;

public final class Partita
{

    public static final RuoliFactory FACTORY = new RuoliFactory();

    private final GiocatoriVivi vivi;

    private final Ballottaggio ballottaggio;

    private final GiocatoriEliminati eliminati;

    private Aura ultimoControllo;

    public Partita(String[][] giocatori)
    {
        vivi = new GiocatoriVivi();
        eliminati = new GiocatoriEliminati();
        FACTORY.annullaSegnalazioni();
        for(String[] giocatore : giocatori) vivi.aggiungiGiocatore(giocatore[0], FACTORY.getRuolo(giocatore[1]));
        ultimoControllo = NERA;
        ballottaggio = new Ballottaggio();
    }

    public void incrementaVoti(String nome, int numeroVoti)
    {
        if(vivi.isPresente(nome)) vivi.incrementaVoti(nome, numeroVoti);
        else ballottaggio.incrementaVoti(nome, numeroVoti);
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
        try
        {
            String nomePerdente = ballottaggio.getNomeGiocatorePerdente();
            terminaBallottaggio(nomePerdente);
            eliminaGiocatore(nomePerdente);
        }
        catch(IllegalArgumentException ex) { System.out.println(ex.getMessage()); }
        finally { for(int i = 0; i < ballottaggio.getNumeroGiocatori(); i++) terminaBallottaggio(ballottaggio.getNomeGiocatore(i)); }
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
        if(vivi.attaccoLupi(FACTORY.getRuolo(nomeLupo), nome) == RIUSCITO) eliminaGiocatore(nome);
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

    private void terminaBallottaggio(String nome)
    {
        Ruolo ruolo = ballottaggio.getRuolo(nome);
        ballottaggio.eliminaGiocatore(nome);
        vivi.aggiungiGiocatore(nome, ruolo);
    }

    private EsitoPartita getEsitoPartitaNegromante() { return getNegromante().getEsitoPartita(this); }

    private Ruolo getNegromante() { return getRuoloVivo(vivi.getNomeNegromante()); }

    private boolean controllaNumeroGuardie(int valore) { return confrontaValori(vivi.getNumeroGuardie(), valore); }

    private boolean controllaNumeroCreatureOmbra(int valore) { return confrontaValori(getNumeroCreatureOmbraVive(), valore); }

    private boolean confrontaValori(int valore1, int valore2) { return valore1 == valore2; }

    public int getNumeroGiocatoriVivi() { return vivi.getNumeroGiocatori(); }

    private void assassinioContadinoMostro(String nome)
    {
        for(String eliminazione : new String[] { nome, vivi.getNomeAssassino() }) eliminaGiocatore(eliminazione);
    }

    private void eliminazioneAngeloCustode() { eliminaGiocatore(getNomeAngeloCustode()); }

    private void eliminaGiocatore(String nome)
    {
        Ruolo ruolo = getRuoloVivo(nome);
        vivi.eliminaGiocatore(nome);
        eliminati.aggiungiGiocatore(nome, ruolo);
    }

    private String getNomeAngeloCustode()
    {
        String nome;
        if(vivi.isAngeloCustodePresente()) nome = vivi.getNomeAngeloCustode();
        else nome = eliminati.getNomeAngeloCustode();
        return nome;
    }

    private Ruolo getRuoloVivo(String nome) { return vivi.getRuolo(nome); }

}