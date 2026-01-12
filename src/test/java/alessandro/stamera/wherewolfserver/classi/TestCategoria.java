package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestCategoria
{

    @ParameterizedTest @CsvSource({ "NESSUNA, -", "UOMINI, Uomini", "CREATURE_OMBRA, Creature dell'ombra" })
    public void testStringa(Categoria categoria, String descrizione) { assertThat(categoria.toString()).isEqualTo(descrizione); }

}