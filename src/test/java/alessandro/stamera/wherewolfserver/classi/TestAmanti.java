package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Categoria.UOMINI;
import static org.assertj.core.api.Assertions.assertThat;
import static alessandro.stamera.wherewolfserver.classi.Fazione.AMANTI;

public final class TestAmanti
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new Amanti(null, null); }

    @Test public void testFazione() { assertThat(ruolo.getFazione()).isEqualTo(AMANTI); }

    @Test public void testCategoria() { assertThat(ruolo.getCategoria()).isEqualTo(UOMINI); }

    @Test public void testAura() { assertThat(ruolo.getAura()).isEqualTo(BIANCA); }

}