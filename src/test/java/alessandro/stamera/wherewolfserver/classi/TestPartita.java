package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestPartita
{

    private static final String[][] ESEMPI_GIOCATORI =
            new String[][] { { "Marco", "Angelo custode" }, { "Giulio", "Pazzo" }, { "Cesare", "Peccatore" }, { "Augusto", "Prete" } };

    private Partita partita;

    @BeforeEach public void setUp() { partita = new Partita(ESEMPI_GIOCATORI); }

    @Test public void testBallottaggioPuro()
    {
        int[] numeroVoti = new int[] { 2, 1 };
        for(int i = 0; i < numeroVoti.length; i++) incrementaVoti(i + 1, numeroVoti[i]);
        terminaVotazioni();
        String[] soluzioni = new String[] { getNomeGiocatoreEsempio(2), getNomeGiocatoreEsempio(1) };
        for(String esempio : soluzioni) verificaAccusato(esempio);
    }

    @Test public void testUnanimita()
    {
        int posizione = 0;
        incrementaVoti(posizione, 3);
        terminaVotazioni();
        verificaAccusato(getNomeGiocatoreEsempio(0));
    }

    @Test public void testPareggioPrimoPosto()
    {
        int numeroGiocatori = ESEMPI_GIOCATORI.length;
        for(int i = 1; i < numeroGiocatori; i++) incrementaVoti(i, 1);
        terminaVotazioni();
        for(int i = 1; i < numeroGiocatori; i++) verificaAccusato(getNomeGiocatoreEsempio(i));
    }

    @Test public void testPareggioSecondoPosto()
    {
        int[] numeroVoti = new int[]{ 2, 1, 1 };
        for(int i = 0; i < numeroVoti.length; i++) incrementaVoti(i, numeroVoti[i]);
        terminaVotazioni();
        String[] soluzioni = new String[] { getNomeGiocatoreEsempio(2), getNomeGiocatoreEsempio(1), getNomeGiocatoreEsempio(0) };
        for(String soluzione : soluzioni) verificaAccusato(soluzione);
    }

    @Test public void testAngeloCustodeAccusatoNonPresente()
    {
        int posizione = 2;
        String nome = getNomeGiocatoreEsempio(posizione);
        segnalazioneAngeloCustode(nome);
        incrementaVoti(posizione, 3);
        terminaVotazioni();
        verificaAccusato(getNomeGiocatoreEsempio(0));
        verificaNonAccusato(nome);
    }

    @Test public void testAngeloCustodeAccusatoPresente()
    {
        String nome = getNomeGiocatoreEsempio(1);
        segnalazioneAngeloCustode(nome);
        for(int i = 0; i < 3; i++) incrementaVoti(i, 2);
        terminaVotazioni();
        String[] soluzioni = new String[] { getNomeGiocatoreEsempio(2), getNomeGiocatoreEsempio(0) };
        for(String soluzione : soluzioni) verificaAccusato(soluzione);
        verificaNonAccusato(nome);
    }

    @Test public void testAttaccoAssassino()
    {
        String nome = getNomeGiocatoreEsempio(2);
        attaccoAssassino(nome);
        verificaEliminazione(nome);
    }

    @Test public void testAttaccoAmatoAssassino()
    {
        String nomeAngeloCustode = getNomeGiocatoreEsempio(0), nomeAmato = getNomeGiocatoreEsempio(3);
        segnalazioneAngeloCustode(nomeAmato);
        attaccoAssassino(nomeAmato);
        verificaEliminazione(nomeAngeloCustode);
        verificaVero(isVivo(nomeAmato));
    }

    private void incrementaVoti(int posizione, int numeroVoti) { partita.incrementaVoti(getNomeGiocatoreEsempio(posizione), numeroVoti); }

    private void terminaVotazioni() { partita.terminaVotazioni(); }

    private void verificaAccusato(String nome) { verificaVero(isAccusato(nome)); }

    private void verificaNonAccusato(String nome) { verificaFalso(isAccusato(nome)); }

    private String getNomeGiocatoreEsempio(int posizione) { return ESEMPI_GIOCATORI[posizione][0]; }

    private boolean isAccusato(String nome) { return partita.isAccusato(nome); }

    private void segnalazioneAngeloCustode(String nome) { partita.segnalazioneAngeloCustode(nome); }

    private void attaccoAssassino(String nome) { partita.attaccoAssassino(nome); }

    private void verificaEliminazione(String nome)
    {
        verificaVero(partita.isEliminato(nome));
        verificaFalso(isVivo(nome));
    }

    private boolean isVivo(String nome) { return partita.isVivo(nome); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}