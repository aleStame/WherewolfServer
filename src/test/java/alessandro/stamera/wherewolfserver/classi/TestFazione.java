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

    @ParameterizedTest @CsvSource
    (
        {
            "NESSUNA, NESSUNA", "LUPO_BRANCO, CREATURE_OMBRA", "LUPO_SOLITARIO, CREATURE_OMBRA", "VAMPIRO, CREATURE_OMBRA",
            "NOSFERATU, CREATURE_OMBRA", "NEGROMANTE, CREATURE_OMBRA", "POSSEDUTO, CREATURE_OMBRA", "VILLAGGIO, UOMINI", "CITTA, UOMINI",
            "CRIMINALI, UOMINI", "AMANTI, UOMINI", "INQUISIZIONE, UOMINI"
        }
    )
    public void testCategoriaFazione(Fazione fazione, Categoria categoria) { assertThat(fazione.getCategoria()).isEqualTo(categoria); }

}