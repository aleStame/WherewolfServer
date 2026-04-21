package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.EsitoAttacco.FALLITO;
import static alessandro.stamera.wherewolfserver.classi.EsitoAttacco.RIUSCITO;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestGiocatoriVivi
{

    private static final String[][] ESEMPI_GIOCATORI =
        new String[][] { { "Marco", "Angelo custode" }, { "Giulio", "Pazzo" }, { "Cesare", "Peccatore" }, { "Augusto", "Prete" } };

    private GiocatoriVivi giocatori;

    @BeforeEach public void setUp()
    {
        FACTORY.annullaVoti();
        giocatori = new GiocatoriVivi();
        for(String[] esempio : ESEMPI_GIOCATORI) giocatori.aggiungiGiocatore(esempio[0], FACTORY.getRuolo(esempio[1]));
        giocatori.resettaAmato();
    }

    @Test public void testBallottaggioPuro()
    {
        int[] numeroVoti = new int[] { 2, 1 };
        for(int i = 0; i < numeroVoti.length; i++) incrementaVoti(i + 1, numeroVoti[i]);
        verificaAccusati(getNomeGiocatore(2), getNomeGiocatore(1));
    }

    @Test public void testUnanimita()
    {
        incrementaVoti(0, 3);
        Giocatori ballottaggio = getBallottaggio();
        verificaNumeroAccusati(ballottaggio, 1);
        verificaGiocatoreAccusato(ballottaggio, 0, getNomeGiocatore(0));
    }

    @Test public void testPareggioPrimoPosto()
    {
        for(int i = 1; i < ESEMPI_GIOCATORI.length; i++) incrementaVoti(i, 1);
        verificaNumeroAccusati(getBallottaggio(), 3);
    }

    @Test public void testPareggioSecondoPosto()
    {
        int[] numeroVoti = new int[]{ 2, 1, 1 };
        for(int i = 0; i < numeroVoti.length; i++) incrementaVoti(i, numeroVoti[i]);
        verificaAccusati(getNomeGiocatore(2), getNomeGiocatore(1), getNomeGiocatore(0));
    }

    @Test public void testSegnalazioneAngeloCustode()
    {
        String nome = getNomeGiocatore(3);
        segnalazioneAngeloCustode(nome);
        verificaVero(giocatori.isAmato(nome));
    }

    @Test public void testAngeloCustodeAccusatoNonPresente()
    {
        int posizione = 2;
        segnalazioneAngeloCustode(getNomeGiocatore(posizione));
        incrementaVoti(posizione, 3);
        verificaGiocatoreAccusato(getBallottaggio(), 0, getNomeGiocatore(0));
    }

    @Test public void testAngeloCustodeAccusatoPresente()
    {
        segnalazioneAngeloCustode(getNomeGiocatore(1));
        for(int i = 0; i < 3; i++) incrementaVoti(i, 2);
        verificaAccusati(getNomeGiocatore(2), getNomeGiocatore(0));
    }

    @Test public void testAttaccoAssassino() { verificaAttaccoAssassino(getNomeGiocatore(3), RIUSCITO); }

    @Test public void testAttaccoAmatoAssassino()
    {
        String nome = getNomeGiocatore(2);
        segnalazioneAngeloCustode(nome);
        verificaAttaccoAssassino(nome, FALLITO);
    }

    @Test public void testSegnalazioneAzzeccagarbugli()
    {
        giocatori.segnalazioneAzzeccagarbugli(getNomeGiocatore(1));
        for(int i = 1; i < ESEMPI_GIOCATORI.length; i++) incrementaVoti(i, 1);
        verificaAccusati(getNomeGiocatore(3), getNomeGiocatore(2), getNomeGiocatore(1));
    }

    private void verificaAccusati(String... soluzioni)
    {
        int numeroSoluzioni = soluzioni.length;
        Giocatori ballottaggio = getBallottaggio();
        verificaNumeroAccusati(ballottaggio, numeroSoluzioni);
        for (int i = 0; i < numeroSoluzioni; i++) verificaGiocatoreAccusato(ballottaggio, i, soluzioni[i]);
    }

    private void verificaAttaccoAssassino(String nome, EsitoAttacco esito) { assertThat(giocatori.attaccoAssassino(nome)).isEqualTo(esito); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void segnalazioneAngeloCustode(String nome) { giocatori.segnalazioneAngeloCustode(nome); }

    private void incrementaVoti(int posizione, int voti) { giocatori.incrementaVoti(getNomeGiocatore(posizione), voti); }

    private void verificaGiocatoreAccusato(Giocatori ballottaggio, int posizione, String nome)
    {
        verificaNomeGiocatore(ballottaggio.getNomeGiocatore(posizione), nome);
    }

    private void verificaNomeGiocatore(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private String getNomeGiocatore(int posizione) { return ESEMPI_GIOCATORI[posizione][0]; }

    private void verificaNumeroAccusati(Giocatori ballottaggio, int numeroAccusati)
    {
        assertThat(ballottaggio.getNumeroGiocatori()).isEqualTo(numeroAccusati);
    }

    private Giocatori getBallottaggio() { return giocatori.getBallottaggio(); }

}