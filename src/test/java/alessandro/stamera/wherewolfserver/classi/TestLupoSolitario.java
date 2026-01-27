package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Fazione.LUPO_SOLITARIO;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestLupoSolitario
{

    @Test public void testNome() { assertThat(new LupoSolitario().getNome()).isEqualTo("Lupo solitario"); }

    //@Test public void testFazione() { assertThat(new LupoSolitario().getFazione()).isEqualTo(LUPO_SOLITARIO); }

}