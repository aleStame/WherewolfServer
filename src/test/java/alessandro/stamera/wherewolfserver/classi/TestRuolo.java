package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static alessandro.stamera.wherewolfserver.classi.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.EsitoAttacco.FALLITO;
import static alessandro.stamera.wherewolfserver.classi.EsitoAttacco.RIUSCITO;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
import static alessandro.stamera.wherewolfserver.classi.Tratto.PROTETTO;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestRuolo
{

    private static final int ESEMPIO_VOTI = 3;

    private Ruolo ruolo;

    @BeforeEach public void setUp()
    {
        ruolo = new Ruolo(null, null, null, null, -1, false);
    }

    @Test public void testInizializzazione()
    {
        verificaNessunVoto();
        verificaFalso(isAmato());
        verificaLibero();
        verificaAssenzaProtezioni();
    }

    @Test public void testAccusato()
    {
        ruolo.accusa();
        verificaAccusato();
        ruolo.libera();
        verificaLibero();
    }

    @Test public void testVoti()
    {
        incrementaVoti();
        verificaVoti(ESEMPIO_VOTI);
        ruolo.annullaVoti();
        verificaNessunVoto();
        ruolo.maledizione();
        verificaVoti(1);
        verificaVero(ruolo.isMaledetto());
        assertThat(ruolo.getAura()).isEqualTo(NERA);
    }

    @Test public void testSegnalazioneAzzeccagarbugli()
    {
        incrementaVoti();
        ruolo.segnalazioneAzzeccagarbugli();
        verificaVoti(ESEMPIO_VOTI);
        verificaAccusato();
    }

    @ParameterizedTest
    @CsvSource({ "Capo branco, Lupo del branco, Lupo solitario, Lupo reietto, Giovane lupo, Contadino discendente dei lupi" })
    public void testSceltaAngeloCustode(String nome)
    {
        ruolo.sceltaAngeloCustode();
        verificaVero(isAmato());
        verificaProtetto();
        verificaVero(isProtezioneLupiPresente());
        verificaVero(isProtezioneNegromantePresente());
        assertThat(attaccoLupi(nome)).isEqualTo(FALLITO);
        verificaAssenzaProtezioni();
    }

    @Test public void testGildata()
    {
        Fazione fazione = getFazione();
        ruolo.gildata();
        assertThat(getFazione()).isEqualTo(fazione);
    }

    @ParameterizedTest
    @CsvSource( { "Capo branco, Lupo del branco, Lupo solitario, Lupo reietto, Giovane lupo, Contadino discendente dei lupi" } )
    public void testRomeizzazioneMorte(String nome)
    {
        ruolo.romeizzazione();
        verificaProtetto();
        assertThat(attaccoLupi(nome)).isEqualTo(RIUSCITO);
    }

    @Test public void testSegnalazioneInquisitore()
    {
        verificaLibero();
        ruolo.segnalazioneInquisitore();
        verificaLibero();
    }

    @ParameterizedTest
    @CsvSource({ "Capo branco, Lupo del branco, Lupo solitario, Lupo reietto, Giovane lupo, Contadino discendente dei lupi" })
    public void testAttaccoRuoloNonProtetto(String nome) { assertThat(attaccoLupi(nome)).isEqualTo(RIUSCITO); }

    @Test public void testAttaccoNosferatu() { assertThat(ruolo.attaccoNosferatu()).isEqualTo(RIUSCITO); }

    private EsitoAttacco attaccoLupi(String nome) { return ruolo.attaccoLupi(getRuolo(nome)); }

    private void verificaAssenzaProtezioni()
    {
        verificaFalso(isProtezioneLupiPresente());
        verificaFalso(isProtezioneNegromantePresente());
    }

    private void verificaProtetto() { verificaVero(ruolo.isTrattoPresente(PROTETTO)); }

    private void verificaAccusato() { verificaVero(isAccusato()); }

    private void verificaLibero() { verificaFalso(isAccusato()); }

    private void incrementaVoti() { ruolo.incrementaVoti(ESEMPIO_VOTI); }

    private boolean isAccusato() { return ruolo.isAccusato(); }

    private boolean isAmato() { return ruolo.isAmato(); }

    private void verificaNessunVoto() { assertThat(getNumeroVoti()).isZero(); }

    private void verificaVoti(int voti) { assertThat(getNumeroVoti()).isEqualTo(voti); }

    private int getNumeroVoti() { return ruolo.getNumeroVoti(); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

    private Fazione getFazione() { return ruolo.getFazione(); }

    private boolean isProtezioneLupiPresente() { return ruolo.isProtezioneLupiPresente(); }

    private boolean isProtezioneNegromantePresente() { return ruolo.isProtezioneNegromantePresente(); }

    private Ruolo getRuolo(String nome) { return FACTORY.getRuolo(nome); }

}