package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestGiocatoriVivi
{

    @Test public void testBallottaggioPuro()
    {
        GiocatoriVivi giocatori = new GiocatoriVivi();
        String[][] esempi = new String[][] { { "Marco", "Angelo custode" }, { "Giulio", "Pazzo" }, { "Cesare", "Peccatore" } };
        for(String[] esempio : esempi) giocatori.aggiungiGiocatore(esempio[0], FACTORY.getRuolo(esempio[1]));
        int[] numeroVoti = new int[] { 2, 1 };
        for(int i = 0; i < numeroVoti.length; i++) giocatori.incrementaVoti(esempi[i + 1][0], numeroVoti[i]);
        assertThat(giocatori.getBallottaggio().getNumeroGiocatori()).isEqualTo(2);
    }

}