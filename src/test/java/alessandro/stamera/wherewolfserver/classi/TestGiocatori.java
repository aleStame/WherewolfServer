package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestGiocatori
{

    private static final String ESEMPIO_GIOCATORE = "Marco";

    private Giocatori giocatori;

    @BeforeEach public void setUp()
    {
        giocatori = new Giocatori();
        aggiungiGiocatore(ESEMPIO_GIOCATORE, "Mercante");
    }

    @Test public void testInserimentoGiocatori()
    {
        aggiungiGiocatore("Antonio", "Capo branco");
        verificaNumeroGiocatori(2);
    }

    @Test public void testEliminazioneGiocatori()
    {
        giocatori.eliminaGiocatore(ESEMPIO_GIOCATORE);
        verificaNumeroGiocatori(0);
    }

    @Test public void testVotazione()
    {
        int numeroVoti = 3;
        giocatori.incrementaVoti(ESEMPIO_GIOCATORE, numeroVoti);
        assertThat(giocatori.getNumeroVoti(ESEMPIO_GIOCATORE)).isEqualTo(numeroVoti);
    }

    private void aggiungiGiocatore(String nomeGiocatore, String nomeRuolo)
    {
        giocatori.aggiungiGiocatore(nomeGiocatore, FACTORY.getRuolo(nomeRuolo));
    }

    private void verificaNumeroGiocatori(int numeroGiocatori)
    {
        assertThat(giocatori.getNumeroGiocatori()).isEqualTo(numeroGiocatori);
    }

}