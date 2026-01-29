package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static alessandro.stamera.wherewolfserver.classi.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.LUPO_BRANCO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doCallRealMethod;

@ExtendWith(MockitoExtension.class) public final class TestLupo
{

    @Mock private Lupo ruolo;

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
        doCallRealMethod().when(ruolo).isCapoBranco();
        assertThat(ruolo.isCapoBranco()).isFalse();
    }

    @Test public void testLupoBranco()
    {
        doCallRealMethod().when(ruolo).isLupoBranco();
        assertThat(ruolo.isLupoBranco()).isFalse();
    }

    @Test public void testLupoReietto()
    {
        doCallRealMethod().when(ruolo).isLupoReietto();
        assertThat(ruolo.isLupoReietto()).isFalse();
    }

    @Test public void testLupoSolitario()
    {
        doCallRealMethod().when(ruolo).isLupoSolitario();
        assertThat(ruolo.isLupoSolitario()).isFalse();
    }

    private void verificaLupo(Ruolo ruolo) { assertThat(ruolo.getFazione()).isEqualTo(LUPO_BRANCO); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

    private Ruolo getRuoloEsempio(int lune) { return new Lupo("Ruolo", "Descrizione generica", lune); }

}