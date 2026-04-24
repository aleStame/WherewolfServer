package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.EsitoPartita.VITTORIA;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class TestAssassino
{

    private static final String NOME = "Assassino";

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = FACTORY.getRuolo(NOME); }

    @Test public void testNome() { testStringa(ruolo.getNome(), NOME); }

    @Test public void testAura() { verificaAuraNera(ruolo.getAura()); }

    @Test public void tesDescrizione()
    {
        String soluzione =
            "La prima notte riconosce gli altri criminali. Una volta per partita, dalla seconda notte, può aprire gli occhi nel turno di un " +
            "mistico. Se quel mistico in gioco, viene ucciso. Altrimenti, l'Assassino indica un giocatore che viene avvisato ed ucciso.";
        testStringa(ruolo.getDescrizione(), soluzione);
    }

    @Test public void testCriminale() { verificaVero(ruolo.isCriminale()); }

    @Test public void testAssassino() { verificaVero(ruolo.isAssassino()); }

    @Test public void testCapoGilda() { verificaFalso(ruolo.isCapoGilda()); }

    @Test public void testLadra() { verificaFalso(ruolo.isLadra()); }

    @Test public void testSpia() { verificaFalso(ruolo.isSpia()); }

    @Test public void testControlloMedium() { verificaAuraNera(ruolo.controlloMedium()); }

    @Test public void testControllaVittoriaRomeo()
    {
        Partita partita = mock(Partita.class);
        when(partita.isFinita()).thenReturn(true);
        when(partita.isGiuliettaViva()).thenReturn(true);
        ruolo.romeizzazione();
        assertThat(ruolo.getEsitoPartita(partita)).isEqualTo(VITTORIA);
    }

    private void verificaAuraNera(Aura aura) { assertThat(aura).isEqualTo(NERA); }

    private void testStringa(String valore, String soluzione) { assertThat(valore).isEqualTo(soluzione); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}