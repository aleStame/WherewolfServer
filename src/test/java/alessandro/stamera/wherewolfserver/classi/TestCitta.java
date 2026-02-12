package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;

import static alessandro.stamera.wherewolfserver.classi.Categoria.UOMINI;
import static alessandro.stamera.wherewolfserver.classi.Fazione.CITTA;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestCitta
{

    @Test public void testFazione() { assertThat(new Citta(null, null, null).getFazione()).isEqualTo(CITTA); }

    @Test public void testCategoria() { assertThat(new Citta(null, null, null).getCategoria()).isEqualTo(UOMINI); }

}