package alessandro.stamera.wherewolfserver.classi.gestione_partita;

import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.RuoliFactory;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.RIUSCITO;

public final class Partita
{

    public static final RuoliFactory FACTORY = new RuoliFactory();

    private final GiocatoriVivi vivi;

    private Giocatori ballottaggio;

    private final Giocatori eliminati;

    public Partita(String[][] giocatori)
    {
        vivi = new GiocatoriVivi();
        eliminati = new Giocatori();
        FACTORY.annullaVoti();
        for(String[] giocatore : giocatori) vivi.aggiungiGiocatore(giocatore[0], FACTORY.getRuolo(giocatore[1]));
        vivi.resettaAmato();
    }

    public void incrementaVoti(String nome, int numeroVoti) { vivi.incrementaVoti(nome, numeroVoti); }

    public void terminaVotazioni() { ballottaggio = vivi.getBallottaggio(); }

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

    public boolean isSoloCreatureOmbra() { return getNumeroCreatureOmbra() == getNumeroGiocatoriVivi(); }

    public boolean isSoloGuardie() { return controllaNumeroGuardie(getNumeroGiocatoriVivi()); }

    public boolean isNoGuardie() { return controllaNumeroGuardie(0); }

    private boolean controllaNumeroGuardie(int valore) { return getNumeroGuardie() == valore; }

    private int getNumeroGuardie()
    {
        int numeroGuardie = 0;
        for(int i = 0; i < getNumeroGiocatoriVivi(); i++) if(isGuardia(i)) numeroGuardie++;
        return numeroGuardie;
    }

    private boolean isGuardia(int posizione) { return vivi.isGuardia(getNomeGiocatoreVivo(posizione)); }

    private int getNumeroCreatureOmbra()
    {
        int numeroCreatureOmbra = 0;
        for(int i = 0; i < getNumeroGiocatoriVivi(); i++) if(isCreaturaOmbra(i)) numeroCreatureOmbra++;
        return numeroCreatureOmbra;
    }

    private boolean isCreaturaOmbra(int posizione) { return vivi.isCreaturaOmbra(getNomeGiocatoreVivo(posizione)); }

    private int getNumeroGiocatoriVivi() { return vivi.getNumeroGiocatori(); }

    private void assassinioContadinoMostro(String nome)
    {
        for(String eliminazione : new String[] { nome, vivi.getNomeAssassino() }) eliminaGiocatore(eliminazione);
    }

    private void eliminazioneAngeloCustode() { eliminaGiocatore(getNomeAngeloCustode()); }

    private void eliminaGiocatore(String nome)
    {
        Ruolo ruolo = vivi.getRuolo(nome);
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

    private String getNomeGiocatoreVivo(int posizione) { return vivi.getNomeGiocatore(posizione); }

}