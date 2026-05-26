package alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche;

import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Categoria.UOMINI;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;

public final class TestUomini
{

    @Test public void testCategoria()
    {
        Uomini ruolo = mock(Uomini.class);
        doCallRealMethod().when(ruolo).getCategoria();
        assertThat(ruolo.getCategoria()).isEqualTo(UOMINI);
    }

}