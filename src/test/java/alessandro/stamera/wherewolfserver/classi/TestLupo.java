package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static alessandro.stamera.wherewolfserver.classi.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.LUPO_BRANCO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.doCallRealMethod;

public final class TestLupo
{

    @ParameterizedTest @CsvSource({ "1, 2, 3" }) public void testFazione(int lune) { verificaLupo(getRuoloEsempio(lune)); }

    @ParameterizedTest @CsvSource({ "1, 2, 3" })
    public void testAura(int lune) { assertThat(getRuoloEsempio(lune).getAura()).isEqualTo(NERA); }

    @ParameterizedTest @CsvSource({ "1, 2, 3" }) public void testGildata(int lune)
    {
        Ruolo ruolo = getRuoloEsempio(lune);
        verificaFalso(ruolo.gildata());
        verificaLupo(ruolo);
    }

    @Test public void testCapoBranco()
    {
        Ruolo ruolo = mock(Ruolo.class);
        doCallRealMethod().when(ruolo).isCapoBranco();
        assertThat(ruolo.isCapoBranco()).isFalse();
    }

    @ParameterizedTest @CsvSource({ "1, 2, 3" })
    public void testLupoBranco(int lune) { verificaFalso(getRuoloEsempio(lune).isLupoBranco()); }

    private void verificaLupo(Ruolo ruolo) { assertThat(ruolo.getFazione()).isEqualTo(LUPO_BRANCO); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

    private Ruolo getRuoloEsempio(int lune) { return new Lupo("Ruolo", "Descrizione generica", lune); }

}