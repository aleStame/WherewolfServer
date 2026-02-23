package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;

import static alessandro.stamera.wherewolfserver.classi.Categoria.UOMINI;
import static org.assertj.core.api.Assertions.assertThat;
import static alessandro.stamera.wherewolfserver.classi.Fazione.AMANTI;

public final class TestAmanti
{

    @Test public void testFazione() { assertThat(new Amanti(null, null).getFazione()).isEqualTo(AMANTI); }

    @Test public void testCategoria() { assertThat(new Amanti(null, null).getCategoria()).isEqualTo(UOMINI); }

}