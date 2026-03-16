package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.IstanzaRuolo.CAPO_BRANCO;
import static alessandro.stamera.wherewolfserver.classi.IstanzaRuolo.LUPO_BRANCO;
import static alessandro.stamera.wherewolfserver.classi.IstanzaRuolo.LUPO_SOLITARIO;
import static alessandro.stamera.wherewolfserver.classi.IstanzaRuolo.LUPO_REIETTO;
import static alessandro.stamera.wherewolfserver.classi.IstanzaRuolo.GIOVANE_LUPO;
import static alessandro.stamera.wherewolfserver.classi.IstanzaRuolo.values;
import static org.assertj.core.api.Assertions.assertThat;
import static alessandro.stamera.wherewolfserver.classi.IstanzaRuolo.LADRA;

public final class TestLadra
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = LADRA.getRuolo(); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), "Ladra"); }

    @Test public void testDescrizione()
    {
        String descrizione =
            "La prima notte riconosce gli altri criminali. Una volta per partita, dalla seconda notte può aprire gli occhi nel turno di un " +
            "mistico. La prima volta che viene attaccata, è protetta dalle creature dell'ombra.";
        verificaStringa(ruolo.getDescrizione(), descrizione);
    }

    @Test public void testAura() { assertThat(ruolo.getAura()).isEqualTo(BIANCA); }

    @Test public void testCriminale() { verificaVero(ruolo.isCriminale()); }

    @Test public void testAssassino() { verificaFalso(ruolo.isAssassino()); }

    @Test public void testCapoGilda() { verificaFalso(ruolo.isCapoGilda()); }

    @Test public void testLadra() { verificaVero(ruolo.isLadra()); }

    @Test public void testUtilizzoPotere()
    {
        verificaFalso(isPotereUtilizzato());
        for(IstanzaRuolo x : new IstanzaRuolo[] { CAPO_BRANCO, LUPO_BRANCO, LUPO_SOLITARIO, LUPO_REIETTO, GIOVANE_LUPO })
            verificaVero(ruolo.isProtezionePresente(x.getRuolo()));
        ruolo.utilizzaPotere();
        verificaVero(isPotereUtilizzato());
        for(IstanzaRuolo x : values()) verificaFalso(ruolo.isProtezionePresente(x.getRuolo()));
    }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

    private boolean isPotereUtilizzato() { return ruolo.isPotereUtilizzato(); }

}