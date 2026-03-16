package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import static alessandro.stamera.wherewolfserver.classi.IstanzaRuolo.CAPO_BRANCO;
import static alessandro.stamera.wherewolfserver.classi.IstanzaRuolo.LUPO_BRANCO;
import static alessandro.stamera.wherewolfserver.classi.IstanzaRuolo.LUPO_REIETTO;
import static alessandro.stamera.wherewolfserver.classi.IstanzaRuolo.GIOVANE_LUPO;
import static alessandro.stamera.wherewolfserver.classi.IstanzaRuolo.LUPO_SOLITARIO;
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
        verificaVoti();
        ruolo.annullaVoti();
        verificaNessunVoto();
    }

    @Test public void testSegnalazioneAzzeccagarbugli()
    {
        incrementaVoti();
        ruolo.segnalazioneAzzeccagarbugli();
        verificaVoti();
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

    @Test public void testRomeizzazione()
    {
        ruolo.romeizzazione();
        for(IstanzaRuolo istanzaRuolo : new IstanzaRuolo[] { CAPO_BRANCO, LUPO_BRANCO, LUPO_SOLITARIO, LUPO_REIETTO, GIOVANE_LUPO })
            verificaVero(ruolo.isProtezionePresente(istanzaRuolo.getRuolo()));
    }

    @Test public void testSegnalazioneInquisitore()
    {
        verificaLibero();
        ruolo.segnalazioneInquisitore();
        verificaLibero();
    }

    @ParameterizedTest @EnumSource(IstanzaRuolo.class)
    public void testAttaccoRuoloNonProtetto(IstanzaRuolo istanza) { verificaVero(ruolo.attacco(istanza.getRuolo())); }

    @ParameterizedTest @EnumSource(IstanzaRuolo.class) public void testAttaccoRuoloProtetto(IstanzaRuolo istanza)
    {
        Ruolo attaccante = istanza.getRuolo();
        ruolo.aggiungiProtezione(attaccante);
        verificaFalso(ruolo.attacco(attaccante));
    }

    private void verificaAccusato() { verificaVero(isAccusato()); }

    private void verificaLibero() { verificaFalso(isAccusato()); }

    private void incrementaVoti() { ruolo.incrementaVoti(ESEMPIO_VOTI); }

    private boolean isAccusato() { return ruolo.isAccusato(); }

    private boolean isAmato() { return ruolo.isAmato(); }

    private void verificaNessunVoto() { assertThat(getNumeroVoti()).isZero(); }

    private void verificaVoti() { assertThat(getNumeroVoti()).isEqualTo(ESEMPIO_VOTI); }

    private int getNumeroVoti() { return ruolo.getNumeroVoti(); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

    private Fazione getFazione() { return ruolo.getFazione(); }

}