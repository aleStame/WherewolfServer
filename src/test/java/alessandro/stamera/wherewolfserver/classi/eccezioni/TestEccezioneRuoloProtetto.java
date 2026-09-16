package alessandro.stamera.wherewolfserver.classi.eccezioni;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestEccezioneRuoloProtetto
{

    @ParameterizedTest @MethodSource("esempiRuoli") public void testEccezioneRuoliProtetti(String nomeRuolo, String messaggio)
    {
        assertThat(new EccezioneRuoloProtetto(nomeRuolo, "Giancarlo").getMessage()).isEqualTo(messaggio);
    }

    @Test public void testEccezioneLupoReietto()
    {
        String messaggio = "Avvisa il Capo branco (Gianluigi) che non può attaccare il Lupo reietto (Ermenegildo).";
        assertThat(new EccezioneRuoloProtetto("Gianluigi", "Lupo reietto", "Ermenegildo").getMessage()).isEqualTo(messaggio);
    }

    private static Stream<Arguments> esempiRuoli()
    {
        String[][] parametri =
        {
            {
                "Ladra",
                "Impossible uccidere la Ladra (Giancarlo) perché protetta almeno una volta dalle Creature dell'ombra.\nAvvisa i lupi dell'attacco " +
                "fallito."
            },
            { "Eremita", "Giancarlo è l'Eremita.\nAvvisa i lupi che l'attacco fallisce" }
        };
        return Stream.of(Arguments.of(parametri[0][0], parametri[0][1]), Arguments.of(parametri[1][0], parametri[1][1]));
    }

}