package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestGoblin
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new Goblin(); }

    @Test public void testNome() { assertThat(ruolo.getNome()).isEqualTo("Goblin"); }

    @Test public void testFazione() { assertThat(ruolo.getFazione()).isEqualTo(Fazione.NESSUNA); }

    @Test public void testCategoria() { assertThat(ruolo.getCategoria()).isEqualTo(Categoria.NESSUNA); }

}