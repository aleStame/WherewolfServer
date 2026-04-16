package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestGiocatoriVivi
{

    @Test public void testBallottaggioPuro()
    {
        Giocatori giocatori = new GiocatoriVivi();
        giocatori.aggiungiGiocatore("Marco", FACTORY.getRuolo("Angelo custode"));
        giocatori.aggiungiGiocatore("Giulio", FACTORY.getRuolo("Pazzo"));
        giocatori.aggiungiGiocatore("Cesare", FACTORY.getRuolo("Peccatore"));
        giocatori.incrementaVoti("Giulio", 2);
        giocatori.incrementaVoti("Cesare", 1);
        Giocatori ballottaggio = giocatori.getBallottaggio();
        assertThat(ballottaggio.getNumeroGiocatori()).isEqualTo(2);
    }

}