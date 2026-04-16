package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestGiocatori
{

    @Test public void testInserimentoGiocatori()
    {
        Giocatori giocatori = new Giocatori();
        giocatori.aggiungiGiocatore("Antonio", FACTORY.getRuolo("Capo branco"));
        assertThat(giocatori.getNumeroGiocatore()).isEqualTo(1);
    }

}