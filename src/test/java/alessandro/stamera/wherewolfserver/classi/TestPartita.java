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
        for(int i = 0; i < numeroVoti.length; i++) incrementaVoti(getNomeGiocatoreEsempio(i + 1), numeroVoti[i]);
        terminaVotazioni();
        String[] soluzioni = new String[] { getNomeGiocatoreEsempio(2), getNomeGiocatoreEsempio(1) };
        for(String esempio : soluzioni) verificaAccusato(esempio);
    }

    @Test public void testUnanimita()
    {
        String nome = getNomeGiocatoreEsempio(0);
        incrementaVoti(nome, 3);
        terminaVotazioni();
        verificaAccusato(nome);
    }

    private void incrementaVoti(String nome, int numeroVoti) { partita.incrementaVoti(nome, numeroVoti); }

    private void terminaVotazioni() { partita.terminaVotazioni(); }

    private void verificaAccusato(String nome) { assertThat(partita.isAccusato(nome)).isTrue(); }

    private String getNomeGiocatoreEsempio(int posizione) { return ESEMPI_GIOCATORI[posizione][0]; }

}