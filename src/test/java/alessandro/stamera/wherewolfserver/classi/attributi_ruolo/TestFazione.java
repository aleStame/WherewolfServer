package alessandro.stamera.wherewolfserver.classi.attributi_ruolo;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.getFazione;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestFazione
{

    @ParameterizedTest @CsvSource
    (
        {
            "NESSUNA, -", "LUPO_BRANCO, Lupi del branco", "LUPO_SOLITARIO, Lupo solitario", "VAMPIRO, Vampiro", "NOSFERATU, Nosferatu",
            "NEGROMANTE, Negromante", "POSSEDUTO, Posseduto", "VILLAGGIO, Villaggio", "CITTA, Città", "CRIMINALI, Criminali", "AMANTI, Amanti",
            "INQUISIZIONE, Inquisizione"
        }
    )
    public void testStringaFazione(Fazione fazione, String descrizione) { assertThat(fazione.toString()).isEqualTo(descrizione); }

    @ParameterizedTest @CsvSource
    (
        {
            "-, NESSUNA", "Lupi del branco, LUPO_BRANCO", "Lupo solitario, LUPO_SOLITARIO", "Vampiro, VAMPIRO", "Nosferatu, NOSFERATU",
            "Negromante, NEGROMANTE", "Posseduto, POSSEDUTO", "Villaggio, VILLAGGIO", "Città, CITTA", "Criminali, CRIMINALI", "Amanti, AMANTI",
            "Inquisizione, INQUISIZIONE"
        }
    )
    public void testRicerca(String descrizione, Fazione fazione) { assertThat(getFazione(descrizione)).isEqualTo(fazione); }

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