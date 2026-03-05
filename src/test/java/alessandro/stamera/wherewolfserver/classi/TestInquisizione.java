package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Categoria.UOMINI;
import static alessandro.stamera.wherewolfserver.classi.Fazione.INQUISIZIONE;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestInquisizione
{

    @Test public void testFazione()
    {
        assertThat(new Inquisizione(null, null, null).getFazione()).isEqualTo(INQUISIZIONE);
    }

    @Test public void testCategoria()
    {
        assertThat(new Inquisizione(null, null, null).getCategoria()).isEqualTo(UOMINI);
    }

}