package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static alessandro.stamera.wherewolfserver.classi.Fazione.VILLAGGIO;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestContadino
{

    @ParameterizedTest @CsvSource({ "BIANCA", "NERA" })
    public void testNome(Aura aura) { verificaStringa(getContadino(aura).getNome(), "Contadino"); }

    @ParameterizedTest @CsvSource({ "BIANCA", "NERA" })
    public void testFazione(Aura aura) { assertThat(getContadino(aura).getFazione()).isEqualTo(VILLAGGIO); }

    @ParameterizedTest @CsvSource({ "BIANCA", "NERA" }) public void testDescrizione(Aura aura)
    {
        String descrizione =
            "Il Contadino ha una delle seguenti identità nascoste (a sua insaputa) : Semplice, Eroe, Discendente dei Lupi, Mostro.";
        verificaStringa(getContadino(aura).getDescrizione(), descrizione);
    }

    @ParameterizedTest @CsvSource({ "BIANCA", "NERA" })
    public void testLune(Aura aura) { assertThat(getContadino(aura).getLune()).isEqualTo(1); }

    @ParameterizedTest @CsvSource({ "BIANCA", "NERA" })
    public void testContadino(Aura aura) { assertThat(getContadino(aura).isContadino()).isTrue(); }

    @ParameterizedTest @CsvSource({ "BIANCA", "NERA" })
    public void testContadinoNormale(Aura aura) { verificaFalso(getContadino(aura).isContadinoNormale()); }

    @ParameterizedTest @CsvSource({ "BIANCA", "NERA" })
    public void testContadinoMostro(Aura aura) { verificaFalso(getContadino(aura).isContadinoMostro()); }

    private Contadino getContadino(Aura aura) { return new Contadino(aura); }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}