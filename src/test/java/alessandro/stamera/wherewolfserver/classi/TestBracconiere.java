package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static org.assertj.core.api.Assertions.assertThat;

public class TestBracconiere
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new Bracconiere(); }

    @Test public void testNome() { assertThat(ruolo.getNome()).isEqualTo("Bracconiere"); }

    @Test public void testAura() { assertThat(ruolo.getAura()).isEqualTo(BIANCA); }

    @Test public void testDescrizione()
    {
        String descrizione =
            "La prima notte scopre quanti lupi del branco sono in gioco e se è in gioco il Lupo solitario. Durante il turno dei lupi mannari " +
            "può segnalare la sua presenza: se in gioco è rimasto soltanto un lupo mannaro, questi non può attaccare";
        assertThat(ruolo.getDescrizione()).isEqualTo(descrizione);
    }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(3); }

    @Test public void testMistico() { verificaFalso(ruolo.isMistico()); }

    @Test public void testLupo() { verificaFalso(ruolo.isLupo()); }

    @Test public void testContadino() { verificaFalso(ruolo.isContadino()); }

    @Test public void testBardo() { verificaFalso(ruolo.isBardo()); }

    @Test public void testBecchino() { verificaFalso(ruolo.isBecchino()); }

    @Test public void testBracconiere() { assertThat(ruolo.isBracconiere()).isTrue(); }

    @Test public void testCacciatore() { verificaFalso(ruolo.isCacciatore()); }

    @Test public void testCacciatoreDiVampiri() { verificaFalso(ruolo.isCacciatoreDiVampiri()); }

    @Test public void testCappuccettoRosso() { verificaFalso(ruolo.isCappuccettoRosso()); }

    @Test public void testVillaggio() { assertThat(ruolo.isVillaggio()).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}