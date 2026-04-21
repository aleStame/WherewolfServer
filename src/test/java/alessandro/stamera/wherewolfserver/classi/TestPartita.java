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
        String[] soluzioni = new String[] { ESEMPI_GIOCATORI[2][0], ESEMPI_GIOCATORI[1][0] };
        for(String[] esempio : ESEMPI_GIOCATORI) assertThat(partita.isAccusato(esempio[0])).isTrue();
    }

}