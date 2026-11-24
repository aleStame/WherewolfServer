package alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.*;
import alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.RIUSCITO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoControlloSensitiva.NON_VILLAGGIO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.*;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static java.util.Arrays.stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

public final class TestRuolo
{

    private Ruolo ruolo;

    @BeforeEach public void setUp()
    {
        ruolo = new Ruolo(null, null, null, -1, false, null);
    }

    @Test public void testInizializzazione()
    {
        verificaAssenzaProtezioni();
    }

    @ParameterizedTest @CsvSource({ "Capo branco", "Lupo del branco", "Lupo solitario", "Lupo reietto" })
    public void testAttaccoLupi(String nome) { verificaAttaccoLupiRiuscito(nome); }

    @Test public void vampirizzazione() { verificaAttaccoRiuscito(ruolo.vampirizzazione()); }

    @Test public void testAttaccoNegromante() { verificaAttaccoRiuscito(ruolo.attaccoNegromante()); }

    @Test public void testControlloSensitiva() { assertThat(ruolo.controlloSensitiva()).isEqualTo(NON_VILLAGGIO); }

    @ParameterizedTest @MethodSource("getEsempiPartita")
    public void testEsitoPartita(Partita partita, EsitoPartita esito) { assertThat(ruolo.getEsitoPartita(partita)).isEqualTo(esito); }

    @Test public void testNonContadino()
    {
        verificaFalso(ruolo.isContadino());
        assertThatIllegalStateException().isThrownBy(() -> ruolo.getTipoContadino()).withMessage("ERRORE!!! Questo ruolo non è un contadino.");
    }

    @Test public void testAttaccoAssassino() { verificaAttaccoRiuscito(ruolo.attaccoAssassino()); }

    @Test public void testGildata() { verificaAttaccoRiuscito(ruolo.gildata()); }

    @Test public void testPassaPosseduto() { verificaAttaccoRiuscito(ruolo.passaPosseduto()); }

    @Test public void testSenzaFazione() { verificaVero(ruolo.isSenzaFazione()); }

    @Test public void testStrega() { verificaFalso(ruolo.isStrega()); }

    @Test public void testGuarigione() { assertThat(ruolo.guarigione()).isEqualTo(EsitoGuarigione.RIUSCITO); }

    @Test public void testAttaccoNosferatu() { verificaAttaccoRiuscito(ruolo.attaccoNosferatu()); }

    @Test public void testGiulietta() { verificaFalso(ruolo.isGiulietta()); }

    private static Stream<Arguments> getEsempiPartita()
    {
        Partita[] partite = new Partita[]
        {
            new Partita(new String[][] { { "Alessandro", "Altra guardia" }, { "Marco", "Guardia" }, { "Maurizio", "Assassino" } }),
            new Partita(new String[][] { { "Tommaso", "Altra guardia" }, { "Antonio", "Guardia" }, { "Aurora", "Capo gilda" } }),
            new Partita(new String[][] { { "Raffaele", "Altra guardia" }, { "Andrea", "Guardia" }, { "Elene", "Guardia corrotta" } }),
            new Partita(new String[][] { { "Francesco", "Altra guardia" }, { "Matteo", "Guardia" }, { "Monica", "Spia" } }),
            new Partita(new String[][] { { "Michele", "Altra guardia" }, { "Giulia", "Guardia" }, { "Sara", "Ladra" } })
        };
        Arguments[] argomenti = new Arguments[partite.length + 1];
        for(int i = 0; i < partite.length; i++) argomenti[i] = Arguments.of(partite[i], VITTORIA);
        argomenti[argomenti.length - 1] = Arguments.of(new Partita(new String[][]{ }), SCONFITTA);
        return stream(argomenti);
    }

    private void verificaAttaccoLupiRiuscito(String nome) { verificaAttaccoRiuscito(attaccoLupi(nome)); }

    private void verificaAttaccoRiuscito(EsitoAttacco esito) { assertThat(esito).isEqualTo(RIUSCITO); }

    private EsitoAttacco attaccoLupi(String nome) { return ruolo.attaccoLupi(FACTORY.getRuolo(nome)); }

    private void verificaAssenzaProtezioni()
    {
        verificaFalso(isProtezioneLupiPresente());
        verificaFalso(ruolo.isProtezioneNegromantePresente());
        verificaFalso(ruolo.isProtezioneVampiroPresente());
    }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

    private boolean isProtezioneLupiPresente() { return ruolo.isProtezioneLupiPresente(); }

}