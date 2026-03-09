package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestTratti
{

    @ParameterizedTest @EnumSource(Tratto.class) public void testTrattoPresente(Tratto tratto)
    {
        Tratti tratti = new Tratti();
        tratti.aggiungi(tratto);
        assertThat(tratti.isPresente(tratto)).isTrue();
    }

    @ParameterizedTest @EnumSource(Tratto.class) public void testTrattoAssente(Tratto tratto)
    {
        Tratti tratti = new Tratti();
        assertThat(tratti.isPresente(tratto)).isFalse();
    }

}