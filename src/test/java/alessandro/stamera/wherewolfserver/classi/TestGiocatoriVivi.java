package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestGiocatoriVivi
{

    private static final String[][] ESEMPI_GIOCATORI =
        new String[][] { { "Marco", "Angelo custode" }, { "Giulio", "Pazzo" }, { "Cesare", "Peccatore" } };

    private GiocatoriVivi giocatori;

    @BeforeEach public void setUp()
    {
        giocatori = new GiocatoriVivi();
        for(String[] esempio : ESEMPI_GIOCATORI) giocatori.aggiungiGiocatore(esempio[0], FACTORY.getRuolo(esempio[1]));
    }

    @Test public void testBallottaggioPuro()
    {
        int[] numeroVoti = new int[] { 2, 1 };
        for(int i = 0; i < numeroVoti.length; i++) incrementaVoti(i + 1, 0, numeroVoti[i]);
        assertThat(giocatori.getBallottaggio().getNumeroGiocatori()).isEqualTo(2);
    }

    @Test public void testUnanimita()
    {
        incrementaVoti(0, 0, 3);
        Giocatori ballottaggio = giocatori.getBallottaggio();
        assertThat(ballottaggio.getNumeroGiocatori()).isEqualTo(1);
        assertThat(ballottaggio.getNomeGiocatore(0)).isEqualTo(ESEMPI_GIOCATORI[0][0]);
    }

    private void incrementaVoti(int posizioneGiocatore, int posizioneRuolo, int voti)
    {
        giocatori.incrementaVoti(ESEMPI_GIOCATORI[posizioneGiocatore][posizioneRuolo], voti);
    }

}