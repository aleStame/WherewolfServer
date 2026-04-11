package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestPiccoloPopolo
{

    @Test public void testFazione() { assertThat(new PiccoloPopolo(null, null, null).getFazione()).isEqualTo(Fazione.NESSUNA); }

    @Test public void testCategoria() { assertThat(new PiccoloPopolo(null, null, null).getCategoria()).isEqualTo(Categoria.NESSUNA); }

}