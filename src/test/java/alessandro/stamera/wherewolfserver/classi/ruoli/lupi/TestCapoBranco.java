package alessandro.stamera.wherewolfserver.classi.ruoli.lupi;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Tratto;
import alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.MORTO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.RIUSCITO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.FALLITO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.*;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestCapoBranco
{

    private static final String NOME = "Capo branco";

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = FACTORY.getRuolo(NOME); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), NOME); }

    @Test public void testDescrizione()
    {
        String descrizione =
            "La prima notte individua il Traditore e riconosce i lupi del branco. Dalla seconda notte può indicare un giocatore, e questi " +
            "viene ucciso.";
        verificaStringa(ruolo.getDescrizione(), descrizione);
    }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(1); }

    @Test public void testCapoBranco() { verificaVero(ruolo.isCapoBranco()); }

    @Test public void testLupoBranco() { verificaFalso(ruolo.isLupoBranco()); }

    @Test public void testGiovaneLupo() { verificaFalso(ruolo.isGiovaneLupo()); }

    @Test public void testLupoReietto() { verificaFalso(ruolo.isLupoReietto()); }

    @Test public void testLupo() { verificaVero(ruolo.isLupo()); }

    @ParameterizedTest @CsvSource({ "CREATURA_OMBRA, LUPO_MANNARO" })
    public void testTratti(Tratto tratto) { verificaVero(ruolo.isTrattoPresente(tratto)); }

    @ParameterizedTest @MethodSource("getEsitiPartite")
    public void testEsitoPartita(Partita partita, EsitoPartita esito) { assertThat(ruolo.getEsitoPartita(partita)).isEqualTo(esito); }

    @Test public void testSuicidio() { assertThat(ruolo.attaccoLupi(ruolo)).isEqualTo(RIUSCITO); }

    @ParameterizedTest
    @CsvSource({ "Lupo del branco", "Giovane lupo", "Lupo reietto", "Lupo solitario", "Contadino discendente dei lupi" })
    public void testAttaccoAltriLupi(String nome) { assertThat(ruolo.attaccoLupi(FACTORY.getRuolo(nome))).isEqualTo(FALLITO); }

    @Test public void testGildata() { assertThat(ruolo.gildata()).isEqualTo(MORTO); }

    private void verificaStringa(String valore, String descrizione) { assertThat(valore).isEqualTo(descrizione); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

    private static Stream<Arguments> getEsitiPartite()
    {
        Partita[] partite = new Partita[]
        {
            new Partita(new String[][] { { "Noemi", "Capo branco" }, { "Elisa", "Lupo del branco" }, { "Damiano", "Pazzo" } }),
            new Partita(new String[][] { { "Manfredi", "Azzeccagarbugli" }, { "Massimo", "Giullare" }, { "Luana", "Guaritore" } }),
            new Partita(new String[][] { { "Chiara", "Oratore" }, { "Giuseppe", "Capo branco" } })
        };
        return Stream.of(Arguments.of(partite[0], VITTORIA), Arguments.of(partite[1], SCONFITTA), Arguments.of(partite[2], NON_FINITO));
    }

}