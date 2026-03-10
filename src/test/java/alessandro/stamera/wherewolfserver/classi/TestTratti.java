package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestTratti
{

    private Tratti tratti;

    @BeforeEach public void setUp() { tratti = new Tratti(); }

    @ParameterizedTest @EnumSource(Tratto.class) public void testTrattoPresente(Tratto tratto)
    {
        tratti.aggiungi(tratto);
        assertThat(isPresente(tratto)).isTrue();
    }

    @ParameterizedTest @EnumSource(Tratto.class)
    public void testTrattoAssente(Tratto tratto) { assertThat(isPresente(tratto)).isFalse(); }

    @Test public void testCappuccettoRosso()
    {
        tratti.aggiungiLupi();
        Ruolo[] ruoli = new Ruolo[] { new CapoBranco(), new LupoBranco(), new GiovaneLupo(), new LupoReietto(), new LupoSolitario() };
        for(Ruolo ruolo : ruoli) assertThat(tratti.isProtezionePresente(ruolo)).isTrue();
    }

    private boolean isPresente(Tratto tratto) { return tratti.isPresente(tratto); }

}