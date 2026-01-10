package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestFazione
{

    @ParameterizedTest @CsvSource
    (
        {
            "NESSUNA, Fazione: -", "LUPO_BRANCO, Fazione: Lupi del branco", "LUPO_SOLITARIO, Fazione: Lupo solitario",
            "VAMPIRO, Fazione: Vampiro", "NOSFERATU, Fazione: Nosferatu", "NEGROMANTE, Fazione: Negromante", "POSSEDUTO, Fazione: Posseduto",
            "VILLAGGIO, Fazione: Villaggio", "CITTA, Fazione: Città", "CRIMINALI, Fazione: Criminali", "AMANTI, Fazione: Amanti",
            "INQUISIZIONE, Fazione: Inquisizione"
        }
    )
    public void testStringaFazione(Fazione fazione, String messaggio) { assertThat(fazione.toString()).isEqualTo(messaggio); }

}