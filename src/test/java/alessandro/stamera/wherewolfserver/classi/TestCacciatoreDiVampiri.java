package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestCacciatoreDiVampiri
{

    @Test public void testNome() { assertThat(new CacciatoreDiVampiri().getNome()).isEqualTo("Cacciatore di vampiri"); }

}