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
        for(int i = 0; i < numeroVoti.length; i++) incrementaVoti(ESEMPI_GIOCATORI[i + 1][0], numeroVoti[i]);
        terminaVotazioni();
        String[] soluzioni = new String[] { ESEMPI_GIOCATORI[2][0], ESEMPI_GIOCATORI[1][0] };
        for(String esempio : soluzioni) verificaAccusato(esempio);
    }

    @Test public void testUnanimita()
    {
        incrementaVoti(ESEMPI_GIOCATORI[0][0], 3);
        terminaVotazioni();
        verificaAccusato(ESEMPI_GIOCATORI[0][0]);
    }

    private void incrementaVoti(String nome, int numeroVoti) { partita.incrementaVoti(nome, numeroVoti); }

    private void terminaVotazioni() { partita.terminaVotazioni(); }

    private void verificaAccusato(String nome) { assertThat(partita.isAccusato(nome)).isTrue(); }

}