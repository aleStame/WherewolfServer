package alessandro.stamera.wherewolfserver.classi;

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
            case FALLITO ->
            {
                String nomeAngelo;
                if(vivi.isAngeloCustodePresente()) nomeAngelo = vivi.getNomeAngeloCustode();
                else nomeAngelo = eliminati.getNomeAngeloCustode();
                eliminaGiocatore(nomeAngelo);
            }
        }

    }

    public boolean isEliminato(String nome) { return eliminati.isPresente(nome); }

    public boolean isVivo(String nome) { return vivi.isPresente(nome); }

    private void eliminaGiocatore(String nome)
    {
        Ruolo ruolo = vivi.getRuolo(nome);
        vivi.eliminaGiocatore(nome);
        eliminati.aggiungiGiocatore(nome, ruolo);
    }

}