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
        aggiungiGiocatore("Elena", "Mercante");
    }

    @Test public void testInserimentoGiocatori()
    {
        aggiungiGiocatore("Antonio", "Capo branco");
        assertThat(giocatori.getNumeroGiocatori()).isEqualTo(2);
    }

    @Test public void testEliminazioneGiocatori()
    {
        giocatori.eliminaGiocatore("Elena");
        assertThat(giocatori.getNumeroGiocatori()).isEqualTo(0);
    }

    private void aggiungiGiocatore(String nomeGiocatore, String nomeRuolo)
    {
        giocatori.aggiungiGiocatore(nomeGiocatore, FACTORY.getRuolo(nomeRuolo));
    }

}