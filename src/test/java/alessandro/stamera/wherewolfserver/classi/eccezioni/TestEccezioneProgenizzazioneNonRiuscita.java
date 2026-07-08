package alessandro.stamera.wherewolfserver.classi.eccezioni;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestEccezioneProgenizzazioneNonRiuscita
{

    private EccezioneProgenizzazioneNonRiuscita eccezione;

    @ParameterizedTest @MethodSource("getEsempiPartitaMorteAngeloCustode")
    public void testAvvisoMorteAngeloCustode(String nomeRuolo, String ruoloProgenizzatore, String messaggio)
    {
        eccezione = new EccezioneProgenizzazioneNonRiuscita
        (
            nomeRuolo, "Aldo", ruoloProgenizzatore, "Giacomo", "Angelo custode", "Giovanni"
        );
        verificaMessaggio(messaggio);
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Cacciatore di vampiri, Vampiro, " +
            "'Il tentativo di vampirizzazione del Cacciatore di vampiri (Katia) causa la morte del Ghoul (Valeria).\nAvvisa Valeria della sua " +
            "morte.'",
            "Contadino mostro, Vampiro, " +
            "'Il tentativo di vampirizzazione del Contadino mostro (Katia) causa la morte del Ghoul (Valeria).\nAvvisa Valeria della sua morte.'",
            "Cacciatore di vampiri, Nosferatu, " +
            "'Il tentativo di progenizzazione del Cacciatore di vampiri (Katia) causa la morte del Ghoul (Valeria).\nAvvisa Valeria della sua " +
            "morte.'",
            "Contadino mostro, Nosferatu, " +
            "'Il tentativo di progenizzazione del Contadino mostro (Katia) causa la morte del Ghoul (Valeria).\nAvvisa Valeria della sua morte.'"
        }
    )
    public void testAvvisoMorteGhoul(String nomeRuolo, String ruoloProgenizzatore, String messaggio)
    {
        System.out.println(nomeRuolo);
        System.out.println(ruoloProgenizzatore);
        eccezione = new EccezioneProgenizzazioneNonRiuscita
        (
            nomeRuolo, "Katia", ruoloProgenizzatore, "Fabio", "Ghoul", "Valeria"
        );
        verificaMessaggio(messaggio);
    }

    private static Stream<Arguments> getEsempiPartitaMorteAngeloCustode()
    {
        String[][] stringhe =
        {
            {
                "Cacciatore di vampiri", "Vampiro",
                "Il tentativo di vampirizzazione del Cacciatore di vampiri (Aldo) causa la morte dell'Angelo custode (Giovanni) del Vampiro amato "
                + "(Giacomo).\nAvvisa Giovanni della sua morte."
            },
            {
                "Cacciatore di vampiri", "Nosferatu",
                "Il tentativo di progenizzazione del Cacciatore di vampiri (Aldo) causa la morte dell'Angelo custode (Giovanni) del Nosferatu amato "
                + "(Giacomo).\nAvvisa Giovanni della sua morte."
            },
            {
                "Contadino mostro", "Vampiro",
                "Il tentativo di vampirizzazione del Contadino mostro (Aldo) causa la morte dell'Angelo custode (Giovanni) del Vampiro amato " +
                "(Giacomo).\nAvvisa Giovanni della sua morte."
            },
            {
                "Contadino mostro", "Nosferatu",
                "Il tentativo di progenizzazione del Contadino mostro (Aldo) causa la morte dell'Angelo custode (Giovanni) del Nosferatu amato " +
                "(Giacomo).\nAvvisa Giovanni della sua morte."
            }
        };
        return Stream.of
        (
            Arguments.of(stringhe[0][0], stringhe[0][1], stringhe[0][2]), Arguments.of(stringhe[1][0], stringhe[1][1], stringhe[1][2]),
            Arguments.of(stringhe[2][0], stringhe[2][1], stringhe[2][2]), Arguments.of(stringhe[3][0], stringhe[3][1], stringhe[3][2])
        );
    }

    private void verificaMessaggio(String messaggio) { assertThat(eccezione.getMessage()).isEqualTo(messaggio); }

}