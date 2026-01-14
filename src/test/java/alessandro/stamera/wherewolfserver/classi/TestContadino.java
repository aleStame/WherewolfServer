package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static alessandro.stamera.wherewolfserver.classi.Fazione.VILLAGGIO;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestContadino
{

    @ParameterizedTest @CsvSource({ "BIANCA", "NERA" })
    public void testNome(Aura aura) { assertThat(getContadino(aura).getNome()).isEqualTo("Contadino"); }

    @ParameterizedTest @CsvSource({ "BIANCA", "NERA" })
    public void testFazione(Aura aura) { assertThat(getContadino(aura).getFazione()).isEqualTo(VILLAGGIO); }

    @ParameterizedTest @CsvSource({ "BIANCA", "NERA" }) public void testDescrizione(Aura aura)
    {
        String descrizione =
            "Il Contadino ha una delle seguenti identità nascoste (a sua insaputa) : Semplice, Eroe, Discendente dei Lupi, Mostro.";
        assertThat(getContadino(aura).getDescrizione()).isEqualTo(descrizione);
    }

    @ParameterizedTest @CsvSource({ "BIANCA", "NERA" })
    public void testLune(Aura aura) { assertThat(getContadino(aura).getLune()).isEqualTo(1); }

    @ParameterizedTest @CsvSource({ "BIANCA", "NERA" })
    public void testContadino(Aura aura) { assertThat(getContadino(aura).isContadino()).isTrue(); }

    @ParameterizedTest @CsvSource({ "BIANCA", "NERA" })
    public void testContadinoNormale(Aura aura) { assertThat(getContadino(aura).isContadinoNormale()).isFalse(); }

    @ParameterizedTest @CsvSource({ "BIANCA", "NERA" })
    public void testContadinoMostro(Aura aura) { assertThat(getContadino(aura).isContadinoMostro()).isFalse(); }

    private Contadino getContadino(Aura aura) { return new Contadino(aura); }

}