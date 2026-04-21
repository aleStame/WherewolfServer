package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestPartita
{

    private static final String[][] ESEMPI_GIOCATORI =
            new String[][] { { "Marco", "Angelo custode" }, { "Giulio", "Pazzo" }, { "Cesare", "Peccatore" }, { "Augusto", "Prete" } };

    @Test public void testBallottaggioPuro()
    {
        Partita partita = new Partita(ESEMPI_GIOCATORI);
        int[] numeroVoti = new int[] { 2, 1 };
        for(int i = 0; i < numeroVoti.length; i++) partita.incrementaVoti(ESEMPI_GIOCATORI[i + 1][0], numeroVoti[i]);
        partita.terminaVotazioni();
        String[] soluzioni = new String[] { ESEMPI_GIOCATORI[2][0], ESEMPI_GIOCATORI[1][0] };
        for(String esempio : soluzioni) assertThat(partita.isAccusato(esempio)).isTrue();
    }

    @Test public void testUnanimita()
    {
        Partita partita = new Partita(ESEMPI_GIOCATORI);
        partita.incrementaVoti(ESEMPI_GIOCATORI[0][0], 3);
        partita.terminaVotazioni();
        assertThat(partita.isAccusato(ESEMPI_GIOCATORI[0][0])).isTrue();
    }

}