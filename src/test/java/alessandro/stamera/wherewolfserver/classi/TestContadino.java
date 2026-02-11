package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static alessandro.stamera.wherewolfserver.classi.Categoria.UOMINI;
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
        verificaVero(ruolo.isContadino());
    }

    @Test public void testMistico()
    {
        doCallRealMethod().when(ruolo).isMistico();
        verificaFalso(ruolo.isMistico());
    }

    @Test public void testAzzeccagarbugli()
    {
        doCallRealMethod().when(ruolo).isAzzeccagarbugli();
        verificaFalso(ruolo.isAzzeccagarbugli());
    }

    @Test public void testLupo()
    {
        doCallRealMethod().when(ruolo).isLupo();
        verificaFalso(ruolo.isLupo());
    }

    @Test public void testBardo()
    {
        doCallRealMethod().when(ruolo).isBardo();
        verificaFalso(ruolo.isBardo());
    }

    @Test public void testBecchino()
    {
        doCallRealMethod().when(ruolo).isBecchino();
        verificaFalso(ruolo.isBecchino());
    }

    @ParameterizedTest @EnumSource(Aura.class)
    public void testCategoria(Aura aura) { assertThat(getContadino(aura).getCategoria()).isEqualTo(UOMINI); }

    @ParameterizedTest @EnumSource(Aura.class) public void testSceltaAngeloCustode(Aura aura)
    {
        Ruolo ruolo = getContadino(aura);
        verificaFalso(ruolo.isAmato());
        ruolo.sceltaAngeloCustode();
        verificaVero(ruolo.isAmato());
    }

    @Test public void testCriminale()
    {
        doCallRealMethod().when(ruolo).isCriminale();
        verificaFalso(ruolo.isCriminale());
    }

    @Test public void testBoia()
    {
        doCallRealMethod().when(ruolo).isBoia();
        verificaFalso(ruolo.isBoia());
    }

    private Contadino getContadino(Aura aura) { return new Contadino(aura); }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}