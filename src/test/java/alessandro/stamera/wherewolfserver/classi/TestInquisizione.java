package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Categoria.UOMINI;
import static alessandro.stamera.wherewolfserver.classi.Fazione.INQUISIZIONE;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestInquisizione
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new Inquisizione(null, null, null); }

    @Test public void testFazione() { assertThat(ruolo.getFazione()).isEqualTo(INQUISIZIONE); }

    @Test public void testCategoria() { assertThat(ruolo.getCategoria()).isEqualTo(UOMINI); }

}