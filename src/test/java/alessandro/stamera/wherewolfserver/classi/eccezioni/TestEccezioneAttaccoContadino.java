package alessandro.stamera.wherewolfserver.classi.eccezioni;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.TipoContadino;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.TipoContadino.EROE;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.TipoContadino.MOSTRO;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestEccezioneAttaccoContadino
{

    @ParameterizedTest @MethodSource("getEsempiAttacchi")
    public void testAttaccoContadino(TipoContadino tipoContadino, String messaggio)
    {
        assertThat(new EccezioneAttaccoContadino(tipoContadino, "Nadia", "Elena").getMessage()).isEqualTo(messaggio);
    }

    private static Stream<Arguments> getEsempiAttacchi()
    {
        return Stream.of
        (
            Arguments.of
            (
    EROE,
               "L'attacco al Contadino eroe (Nadia) causa la morte anche del lupo attaccante (Elena).\nAvvisa entrambi i giocatori della loro morte."
            ),
            Arguments.of
            (
                MOSTRO,
                "L'attacco al Contadino mostro (Nadia) causa la morte anche del lupo attaccante (Elena).\nAvvisa entrambi i giocatori della " +
                "loro morte."
            )
        );
    }

}