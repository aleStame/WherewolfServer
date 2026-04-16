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
        aggiungiGiocatore(ESEMPIO_GIOCATORE, "Angelo custode");
        aggiungiGiocatore("Giulio", "Pazzo");
        aggiungiGiocatore("Cesare", "Peccatore");
    }

    @Test public void testInserimentoGiocatori()
    {
        aggiungiGiocatore("Antonio", "Capo branco");
        verificaNumeroGiocatori(4);
    }

    @Test public void testEliminazioneGiocatori()
    {
        giocatori.eliminaGiocatore(ESEMPIO_GIOCATORE);
        verificaNumeroGiocatori(2);
    }

    @Test public void testVotazione()
    {
        int numeroVoti = 3;
        giocatori.incrementaVoti(ESEMPIO_GIOCATORE, numeroVoti);
        verificaNumeroIntero(giocatori.getNumeroVoti(ESEMPIO_GIOCATORE), numeroVoti);
    }

    private void aggiungiGiocatore(String nomeGiocatore, String nomeRuolo)
    {
        giocatori.aggiungiGiocatore(nomeGiocatore, FACTORY.getRuolo(nomeRuolo));
    }

    private void verificaNumeroGiocatori(int numeroGiocatori)
    {
        verificaNumeroIntero(giocatori.getNumeroGiocatori(), numeroGiocatori);
    }

    private void verificaNumeroIntero(int valore, int risultato) { assertThat(valore).isEqualTo(risultato); }

}