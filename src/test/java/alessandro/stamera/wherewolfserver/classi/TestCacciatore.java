package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Categoria.UOMINI;
import static alessandro.stamera.wherewolfserver.classi.Fazione.VILLAGGIO;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestCacciatore
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new Cacciatore(); }

    @Test public void testNome() { assertThat(ruolo.getNome()).isEqualTo("Cacciatore"); }

    @Test public void testFazione() { assertThat(ruolo.getFazione()).isEqualTo(VILLAGGIO); }

    @Test public void testCategoria() { assertThat(ruolo.getCategoria()).isEqualTo(UOMINI); }

    @Test public void testAura() { assertThat(ruolo.getAura()).isEqualTo(BIANCA); }

    @Test public void testMistico() { assertThat(ruolo.isMistico()).isFalse(); }

    @Test public void testLupo() { assertThat(ruolo.isLupo()).isFalse(); }

}