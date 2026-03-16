package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.IstanzaRuolo.CAPO_BRANCO;
import static alessandro.stamera.wherewolfserver.classi.IstanzaRuolo.LUPO_BRANCO;
import static alessandro.stamera.wherewolfserver.classi.IstanzaRuolo.LUPO_SOLITARIO;
import static org.assertj.core.api.Assertions.assertThat;
import static alessandro.stamera.wherewolfserver.classi.IstanzaRuolo.EREMITA;

public final class TestEremita
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = EREMITA.getRuolo(); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), "Eremita"); }

    @Test public void testAura() { assertThat(ruolo.getAura()).isEqualTo(BIANCA); }

    @Test public void testDescrizione() { verificaStringa(ruolo.getDescrizione(), "È protetto dalle creature dell'ombra"); }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(1); }

    @Test public void testMistico() { verificaFalso(ruolo.isMistico()); }

    @Test public void testBardo() { verificaFalso(ruolo.isBardo()); }

    @Test public void testBecchino() { verificaFalso(ruolo.isBecchino()); }

    @Test public void testBracconiere() { verificaFalso(ruolo.isBracconiere()); }

    @Test public void testCacciatore() { verificaFalso(ruolo.isCacciatore()); }

    @Test public void testCacciatoreDiVampiri() { verificaFalso(ruolo.isCacciatoreDiVampiri()); }

    @Test public void testCappuccettoRosso() { verificaFalso(ruolo.isCappuccettoRosso()); }

    @Test public void testEremita() { verificaVero(ruolo.isEremita()); }

    @Test public void testGuardia() { verificaFalso(ruolo.isGuardia()); }

    @Test public void testGuaritore() { verificaFalso(ruolo.isGuaritore()); }

    @Test public void testVillaggio() { verificaVero(ruolo.isVillaggio()); }

    @Test public void testProtezioni()
    {
        for(IstanzaRuolo istanzaRuolo : new IstanzaRuolo[] { CAPO_BRANCO, LUPO_BRANCO, LUPO_SOLITARIO }) verificaVero(ruolo.isProtezionePresente(istanzaRuolo.getRuolo()));
    }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}