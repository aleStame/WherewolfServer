package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static alessandro.stamera.wherewolfserver.classi.Fazione.VILLAGGIO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doCallRealMethod;

@ExtendWith(MockitoExtension.class) public final class TestContadino
{

    @Mock private Contadino ruolo;

    @ParameterizedTest @EnumSource(Aura.class)
    public void testNome(Aura aura) { verificaStringa(getContadino(aura).getNome(), "Contadino"); }

    @ParameterizedTest @EnumSource(Aura.class)
    public void testFazione(Aura aura) { assertThat(getContadino(aura).getFazione()).isEqualTo(VILLAGGIO); }

    @ParameterizedTest @EnumSource(Aura.class) public void testDescrizione(Aura aura)
    {
        String descrizione =
            "Il Contadino ha una delle seguenti identità nascoste (a sua insaputa) : Semplice, Eroe, Discendente dei Lupi, Mostro.";
        verificaStringa(getContadino(aura).getDescrizione(), descrizione);
    }

    @ParameterizedTest @EnumSource(Aura.class)
    public void testLune(Aura aura) { assertThat(getContadino(aura).getLune()).isEqualTo(1); }

    @Test public void testContadino()
    {
        doCallRealMethod().when(ruolo).isContadino();
        assertThat(ruolo.isContadino()).isTrue();
    }

    @Test public void testContadinoNormale() { verificaFalso(ruolo.isContadinoNormale()); }

    @Test public void testContadinoMostro() { verificaFalso(ruolo.isContadinoMostro()); }

    @Test public void testContadinoEroe() { verificaFalso(ruolo.isContadinoEroe()); }

    @Test public void testContadinoLupo() { verificaFalso(ruolo.isContadinoLupo()); }

    @Test public void testMistico() { verificaFalso(ruolo.isMistico()); }

    private Contadino getContadino(Aura aura) { return new Contadino(aura); }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}