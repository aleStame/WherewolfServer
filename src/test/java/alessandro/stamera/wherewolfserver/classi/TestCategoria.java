package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static alessandro.stamera.wherewolfserver.classi.Categoria.getCategoria;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestCategoria
{

    @ParameterizedTest @CsvSource({ "NESSUNA, -", "UOMINI, Uomini", "CREATURE_OMBRA, Creature dell'ombra" })
    public void testStringa(Categoria categoria, String descrizione) { assertThat(categoria.toString()).isEqualTo(descrizione); }

    @ParameterizedTest @CsvSource({ "-, NESSUNA", "Uomini, UOMINI", "Creature dell'ombra, CREATURE_OMBRA" })
    public void testRicerca(String descrizione, Categoria categoria) { assertThat(getCategoria(descrizione)).isEqualTo(categoria); }

}