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
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.NON_FINITO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.SCONFITTA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Tratto.*;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class TestRuolo
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new Ruolo(null, null, null, -1, false); }

    @Test public void testInizializzazione()
    {
        verificaAssenzaProtezioni();
    }

    @ParameterizedTest
    @CsvSource( { "Capo branco", "Lupo del branco", "Lupo solitario", "Lupo reietto", "Contadino discendente dei lupi" } )
    public void testAttaccoLupoRomeo(String nome)
    {
        romeizzazione();
        verificaProtetto();
        //verificaAttaccoLupiRiuscito(nome);
    }

    @ParameterizedTest
    @CsvSource({ "Capo branco", "Lupo del branco", "Lupo solitario", "Lupo reietto", "Contadino discendente dei lupi" })
    public void testAttaccoRuoloNonProtetto(String nome) { verificaAttaccoLupiRiuscito(nome); }

    @Test public void testAttaccoNegromanteRomeo()
    {
        romeizzazione();
        verificaFalso(maledizione());
        verificaFalso(isMaledetto());
    }

    @ParameterizedTest
    @CsvSource({ "Capo branco", "Lupo del branco", "Lupo solitario", "Lupo reietto", "Contadino discendente dei lupi", "Nosferatu", "Posseduto" })
    public void testRomeizzazione(String nomeCreaturaOmbra)
    {
        romeizzazione();
        verificaProtetto();
        verificaVero(ruolo.isProtezionePresente(FACTORY.getRuolo(nomeCreaturaOmbra)));
        verificaVero(ruolo.isRomeo());
    }

    @Test public void vampirizzazione() { verificaAttaccoRiuscito(ruolo.vampirizzazione()); }

    @Test public void testSegnalazioneBoia()
    {
        ruolo.segnalazioneBoia();
        verificaFalso(ruolo.isSegnalatoBoia());
    }

    @Test public void testSegnalazioneOratore()
    {
        ruolo.segnalazioneOratore();
        verificaVero(isSegnalatoOratore());
        ruolo.annullaSegnalazioneOratore();
        verificaFalso(isSegnalatoOratore());
    }

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

    private static Stream<Arguments> getEsempiPartita()
    {
        return Stream.of(Arguments.of(getPartitaGenerica(), NON_FINITO), Arguments.of(new Partita(new String[][]{ }), SCONFITTA));
    }

    private static Partita getPartitaGenerica()
    {
        Partita partita = mock(Partita.class);
        when(partita.isNoGiocatoriVivi()).thenReturn(false);
        return partita;
    }

    private boolean isSegnalatoOratore() { return ruolo.isSegnalatoOratore(); }

    private void romeizzazione() { ruolo.romeizzazione(); }

    private boolean maledizione() { return ruolo.maledizione(); }

    private boolean isMaledetto() { return ruolo.isMaledetto(); }

    private void verificaAttaccoLupiRiuscito(String nome) { verificaAttaccoRiuscito(attaccoLupi(nome)); }

    private void verificaAttaccoRiuscito(EsitoAttacco esito) { assertThat(esito).isEqualTo(RIUSCITO); }

    private EsitoAttacco attaccoLupi(String nome) { return ruolo.attaccoLupi(FACTORY.getRuolo(nome)); }

    private void verificaAssenzaProtezioni()
    {
        verificaFalso(isProtezioneLupiPresente());
        verificaFalso(ruolo.isProtezioneNegromantePresente());
        verificaFalso(ruolo.isProtezioneVampiroPresente());
    }

    private void verificaProtetto() { verificaVero(ruolo.isTrattoPresente(PROTETTO)); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

    private boolean isProtezioneLupiPresente() { return ruolo.isProtezioneLupiPresente(); }

}