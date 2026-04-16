package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestGiocatori
{

    private Giocatori giocatori;

    @BeforeEach public void setUp()
    {
        giocatori = new Giocatori();
        giocatori.aggiungiGiocatore("Elena", FACTORY.getRuolo("Mercante"));
    }

    @Test public void testInserimentoGiocatori()
    {
        giocatori.aggiungiGiocatore("Antonio", FACTORY.getRuolo("Capo branco"));
        assertThat(giocatori.getNumeroGiocatori()).isEqualTo(2);
    }

    @Test public void testEliminazioneGiocatori()
    {
        giocatori.eliminaGiocatore("Elena");
        assertThat(giocatori.getNumeroGiocatori()).isEqualTo(0);
    }

}