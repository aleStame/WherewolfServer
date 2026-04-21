package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.EsitoAttacco.RIUSCITO;

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
        }
    }

    public boolean isEliminato(String nome) { return eliminati.isPresente(nome); }

    public boolean isVivo(String nome) { return vivi.isPresente(nome); }

    public void attaccoLupi(String nome)
    {
        if(vivi.attaccoLupi(FACTORY.getRuolo("Capo branco"), nome) == RIUSCITO) eliminaGiocatore(nome);
    }

    public void segnalazioneAzzeccagarbugli(String nome) { vivi.segnalazioneAzzeccagarbugli(nome); }

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

}