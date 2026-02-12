package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static alessandro.stamera.wherewolfserver.classi.Categoria.UOMINI;
import static alessandro.stamera.wherewolfserver.classi.Fazione.CITTA;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestCitta
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new Citta(null, null, null); }

    @Test public void testFazione() { assertThat(ruolo.getFazione()).isEqualTo(CITTA); }

    @Test public void testCategoria() { assertThat(ruolo.getCategoria()).isEqualTo(UOMINI); }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(2); }

    @Test public void testMistico() { assertThat(ruolo.isMistico()).isFalse(); }

}