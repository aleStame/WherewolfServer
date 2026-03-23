package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestMedium
{

    private static final String NOME = "Medium";

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = FACTORY.getRuolo(NOME); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), NOME); }

    @Test public void testAura() { assertThat(ruolo.getAura()).isEqualTo(BIANCA); }

    @Test public void testDescrizione()
    {
        verificaStringa(ruolo.getDescrizione(), "Dalla seconda notte, indica un giocatore eliminato e scopre se possedeva aura oscura.");
    }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(1); }

    @Test public void testMistico() { assertThat(ruolo.isMistico()).isTrue(); }

    @Test public void testBardo() { verificaFalso(ruolo.isBardo()); }

    @Test public void testBecchino() { verificaFalso(ruolo.isBecchino()); }

    @Test public void testBracconiere() { verificaFalso(ruolo.isBracconiere()); }

    @Test public void testCacciatore() { verificaFalso(ruolo.isCacciatore()); }

    @Test public void testCacciatoreDiVampiri() { verificaFalso(ruolo.isCacciatoreDiVampiri()); }

    @Test public void testCappuccettoRosso() { verificaFalso(ruolo.isCappuccettoRosso()); }

    @Test public void testContadino() { verificaFalso(ruolo.isContadino()); }

    @Test public void testEremita() { verificaFalso(ruolo.isEremita()); }

    @Test public void testGuardia() { verificaFalso(ruolo.isGuardia()); }

    @Test public void testGuaritore() { verificaFalso(ruolo.isGuaritore()); }

    @Test public void testMago() { verificaFalso(ruolo.isMago()); }

    @Test public void testMedium() { assertThat(ruolo.isMedium()).isTrue(); }

    @Test public void testVillaggio() { assertThat(ruolo.isVillaggio()).isTrue(); }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}