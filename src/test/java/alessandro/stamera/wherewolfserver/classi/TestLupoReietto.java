package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestLupoReietto
{

    @Test public void testNome() { assertThat(new LupoReietto().getNome()).isEqualTo("Lupo reietto"); }

    @Test public void testLune() { assertThat(new LupoReietto().getLune()).isEqualTo(3); }

}