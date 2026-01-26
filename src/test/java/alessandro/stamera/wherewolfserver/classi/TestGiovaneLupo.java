package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestGiovaneLupo
{

    @Test public void testNome() { assertThat(new GiovaneLupo().getNome()).isEqualTo("Giovane lupo"); }

    @Test public void testCapoBranco() { assertThat(new GiovaneLupo().isCapoBranco()).isFalse(); }

}