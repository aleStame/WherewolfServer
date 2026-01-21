package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;

import static alessandro.stamera.wherewolfserver.classi.Fazione.CITTA;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestBoccaDiRosa
{

    @Test public void testNome() { assertThat(new BoccaDiRosa().getNome()).isEqualTo("Bocca di rosa"); }

    @Test public void testFazione() { assertThat(new BoccaDiRosa().getFazione()).isEqualTo(CITTA); }

}