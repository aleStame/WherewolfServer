package alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche;

import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Categoria.UOMINI;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public final class TestUomini
{

    @Test public void testCategoria() { assertThat(mock(Uomini.class).getCategoria()).isEqualTo(UOMINI); }

}