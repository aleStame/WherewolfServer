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
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.*;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoControlloSensitiva.NON_VILLAGGIO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.NON_FINITO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.SCONFITTA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.*;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Tratto.*;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class TestRuolo
{

    private static final int ESEMPIO_VOTI = 3;

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new Ruolo(null, null, null, -1, false); }

    @Test public void testInizializzazione()
    {
        verificaFalso(isAmato());
        verificaAssenzaProtezioni();
    }

    @ParameterizedTest
    @CsvSource({ "Capo branco", "Lupo del branco", "Lupo solitario", "Lupo reietto", "Contadino discendente dei lupi" })
    public void testAttaccoLupiAmato(String nome)
    {
        sceltaAngeloCustode();
        verificaVero(isAmato());
        verificaProtetto();
        verificaVero(isProtezioneLupiPresente());
        verificaAttacco(attaccoLupi(nome), ANGELO_CUSTODE_MORTO);
        verificaAssenzaProtezioni();
        ruolo.ripristina();
    }

    @Test public void testGildata()
    {
        Fazione fazione = getFazione();
        assertThat(ruolo.gildata()).isEqualTo(FALLITO);
        verificaFazione(fazione);
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

    @Test public void testAttaccoNosferatu()
    {
        verificaAttaccoRiuscito(attaccoNosferatu());
        verificaTrattoPresente(NON_MORTO);
        verificaFazione(NOSFERATU);
    }

    @Test public void testAttaccoNosferatuAmato()
    {
        sceltaAngeloCustode();
        verificaAttaccoFallito(ruolo.attaccoNosferatu());
        verificaAssenzaProtezioni();
    }

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
        verificaFazione(AMANTI);
        verificaVero(ruolo.isProtezionePresente(FACTORY.getRuolo(nomeCreaturaOmbra)));
        verificaVero(ruolo.isRomeo());
    }

    @Test public void attaccoAssassino() { verificaAttaccoRiuscito(assassinio()); }

    @Test public void testAttaccoAssassinoAmato() { verificaAttaccoAmato(); }

    @Test public void testAttaccoAssassinoAmatoStregato()
    {
        ruolo.protezioneStrega();
        verificaAttaccoAmato();
    }

    @Test public void testAttaccoAssassinoAmatoRomeo()
    {
        romeizzazione();
        verificaAttaccoAmato();
    }

    @Test public void vampirizzazione()
    {
        verificaAttaccoRiuscito(ruolo.vampirizzazione());
        verificaAuraNera();
        verificaFazione(VAMPIRO);
        verificaTrattoPresente(NON_MORTO);
    }

    @Test public void testSegnalazioneAzzeccagarbugli()
    {
        verificaNonSegnalato();
        ruolo.segnalazioneAzzeccagarbugli();
        verificaVero(isSegnalatoAzzeccagarbugli());
        ruolo.annullaSegnalazioneAzzeccagarbugli();
        verificaNonSegnalato();
    }

    @Test public void testSegnalazioneInquisitore()
    {
        verificaNonInquisito();
        ruolo.segnalazioneInquisitore();
        verificaNonInquisito();
        ruolo.annullaSegnalazioneInquisitore();
        verificaNonInquisito();
    }

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

    @Test public void testRipristino()
    {
        ruolo.ripristina();
        verificaFalso(ruolo.isRomeo());
        verificaFalso(ruolo.isAmato());
        verificaFalso(ruolo.isSegnalatoAzzeccagarbugli());
        verificaFalso(ruolo.isMaledetto());
        verificaFalso(ruolo.isTrattoPresente(NON_MORTO));
        verificaFazione(NESSUNA);
        verificaNonInquisito();
        for(IstanzaRuolo istanza : IstanzaRuolo.values()) ruolo.isProtezionePresente(istanza.getRuolo());
    }

    @Test public void testNonContadino()
    {
        verificaFalso(ruolo.isContadino());
        assertThatIllegalStateException().isThrownBy(() -> ruolo.getTipoContadino()).withMessage("ERRORE!!! Questo ruolo non è un contadino.");
    }

    private void verificaAttaccoAmato()
    {
        sceltaAngeloCustode();
        verificaAttacco(assassinio(), ANGELO_CUSTODE_MORTO);
        ruolo.ripristina();
    }

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

    private void verificaNonSegnalato() { verificaFalso(isSegnalatoAzzeccagarbugli()); }

    private void verificaNonInquisito() { verificaFalso(isInquisito()); }

    private boolean isSegnalatoOratore() { return ruolo.isSegnalatoOratore(); }

    private boolean isSegnalatoAzzeccagarbugli() { return ruolo.isSegnalatoAzzeccagarbugli(); }

    private boolean isInquisito() { return ruolo.isInquisito(); }

    private EsitoAttacco attaccoNosferatu() { return ruolo.attaccoNosferatu(); }

    private EsitoAttacco assassinio() { return ruolo.attaccoAssassino(); }

    private void romeizzazione() { ruolo.romeizzazione(); }

    private void sceltaAngeloCustode() { ruolo.sceltaAngeloCustode(); }

    private boolean maledizione() { return ruolo.maledizione(); }

    private boolean isMaledetto() { return ruolo.isMaledetto(); }

    private void verificaAttaccoFallito(EsitoAttacco esito) { verificaAttacco(esito, FALLITO); }

    private void verificaAttaccoLupiRiuscito(String nome) { verificaAttaccoRiuscito(attaccoLupi(nome)); }

    private void verificaAttaccoRiuscito(EsitoAttacco esito) { verificaAttacco(esito, RIUSCITO); }

    private void verificaAttacco(EsitoAttacco valore, EsitoAttacco risultato) { assertThat(valore).isEqualTo(risultato); }

    private EsitoAttacco attaccoLupi(String nome) { return ruolo.attaccoLupi(FACTORY.getRuolo(nome)); }

    private void verificaAssenzaProtezioni()
    {
        verificaFalso(isProtezioneLupiPresente());
        verificaFalso(ruolo.isProtezioneNegromantePresente());
        verificaFalso(ruolo.isProtezioneVampiroPresente());
    }

    private void verificaProtetto() { verificaTrattoPresente(PROTETTO); }

    private boolean isAmato() { return ruolo.isAmato(); }

    private void verificaTrattoPresente(Tratto tratto) { verificaVero(isTrattoPresente(tratto)); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

    private void verificaFazione(Fazione risultato) { assertThat(getFazione()).isEqualTo(risultato); }

    private Fazione getFazione() { return ruolo.getFazione(); }

    private boolean isProtezioneLupiPresente() { return ruolo.isProtezioneLupiPresente(); }

    private boolean isTrattoPresente(Tratto tratto) { return ruolo.isTrattoPresente(tratto); }

    private void verificaAuraNera() { assertThat(ruolo.getAura()).isEqualTo(NERA); }

}