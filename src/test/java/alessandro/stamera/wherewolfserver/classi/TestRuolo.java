package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static alessandro.stamera.wherewolfserver.classi.Aura.*;
import static alessandro.stamera.wherewolfserver.classi.Fazione.VILLAGGIO;
import static alessandro.stamera.wherewolfserver.classi.Fazione.NESSUNA;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestRuolo
{

    @ParameterizedTest @CsvSource({ "1, 2, 3" })
    public void testLune(int lune) { assertThat(getRuolo("Contadino", VILLAGGIO, BIANCA, lune).lune()).isEqualTo(lune); }

    @ParameterizedTest @CsvSource({ "BIANCA, NERA" })
    public void testAura(Aura aura) { assertThat(getRuolo("Ruolo", NESSUNA, aura, 1).aura()).isEqualTo(aura); }

    @ParameterizedTest @CsvSource
    (
        {
            "NESSUNA, NESSUNA", "LUPO_BRANCO, CREATURE_OMBRA", "LUPO_SOLITARIO, CREATURE_OMBRA", "VAMPIRO, CREATURE_OMBRA",
            "NOSFERATU, CREATURE_OMBRA", "NEGROMANTE, CREATURE_OMBRA", "POSSEDUTO, CREATURE_OMBRA", "VILLAGGIO, UOMINI", "CITTA, UOMINI",
            "CRIMINALI, UOMINI", "AMANTI, UOMINI", "INQUISIZIONE, UOMINI"
        }
    )
    public void testCategoria(Fazione fazione, Categoria categoria)
    {
        assertThat(getRuolo("Alessandro", fazione, NERA, 2).getCategoria()).isEqualTo(categoria);
    }

    private Ruolo getRuolo(String nome, Fazione fazione, Aura aura, int lune) { return new Ruolo(nome, fazione, aura, lune); }

}