package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import static alessandro.stamera.wherewolfserver.classi.Aura.NERA;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestRuolo
{

    private static final int ESEMPIO_VOTI = 3;

    private Ruolo ruolo;

    @BeforeEach
    public void setUp() { ruolo = new Ruolo(null, null, null, null, -1, false); }

    @Test public void testInizializzazione()
    {
        verificaNessunVoto();
        verificaFalso(isAmato());
        verificaLibero();
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

    @Test public void testSceltaAngeloCustode()
    {
        ruolo.sceltaAngeloCustode();
        verificaVero(isAmato());
    }

    @Test public void testGildata()
    {
        Fazione fazione = getFazione();
        ruolo.gildata();
        assertThat(getFazione()).isEqualTo(fazione);
    }

    @ParameterizedTest @CsvSource({ "CAPO_BRANCO, LUPO_BRANCO, LUPO_SOLITARIO, LUPO_REIETTO, GIOVANE_LUPO" })
    public void testRomeizzazione(IstanzaRuolo istanza)
    {
        ruolo.romeizzazione();
        verificaVero(ruolo.isProtezionePresente(istanza.getRuolo()));
    }

    @Test public void testSegnalazioneInquisitore()
    {
        verificaLibero();
        ruolo.segnalazioneInquisitore();
        verificaLibero();
    }

    @ParameterizedTest @EnumSource(IstanzaRuolo.class)
    public void testAttaccoRuoloNonProtetto(IstanzaRuolo istanza) { verificaVero(ruolo.attacco(istanza.getRuolo())); }

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

}