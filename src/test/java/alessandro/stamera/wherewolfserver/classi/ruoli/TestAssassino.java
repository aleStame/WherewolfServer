package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita;
import alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.SCONFITTA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.VITTORIA;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
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
        when(partita.isSoloCreatureOmbra()).thenReturn(false);
        ruolo.romeizzazione();
        assertThat(ruolo.getEsitoPartita(partita)).isEqualTo(VITTORIA);
    }

    @ParameterizedTest @MethodSource("getCasiEsitoPartita")
    public void testSconfittaCreatureOmbra(String[][] giocatori, EsitoPartita esito)
    {
        assertThat(ruolo.getEsitoPartita(new Partita(giocatori))).isEqualTo(esito);
    }

    private void verificaAuraNera(Aura aura) { assertThat(aura).isEqualTo(NERA); }

    private void testStringa(String valore, String soluzione) { assertThat(valore).isEqualTo(soluzione); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

    private static Stream<Arguments> getCasiEsitoPartita()
    {
        return Stream.of
        (
            Arguments.of(new String[][] { { "Raffaele", "Nosferatu" }, { "Aurora", "Capo branco" } }, SCONFITTA),
            Arguments.of(new String[][] { { "Matteo", "Guardia" }, { "Marghe", "Altra guardia" } }, SCONFITTA),
            Arguments.of(new String[][] { { "Giuseppe", "Prete" }, { "Salvatore", "Peccatore" }, { "Marino", "Bocca di rosa" } }, VITTORIA)
        );
    }

}