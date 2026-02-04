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
    public void testLune(Aura aura) { verificaIntero(getContadino(aura).getLune(), 1); }

    @Test public void testContadino()
    {
        doCallRealMethod().when(ruolo).isContadino();
        verificaVero(ruolo.isContadino());
    }

    @Test public void testContadinoNormale() { verificaFalso(ruolo.isContadinoNormale()); }

    @Test public void testContadinoMostro() { verificaFalso(ruolo.isContadinoMostro()); }

    @Test public void testContadinoEroe() { verificaFalso(ruolo.isContadinoEroe()); }

    @Test public void testContadinoLupo() { verificaFalso(ruolo.isContadinoLupo()); }

    @Test public void testMistico() { verificaFalso(ruolo.isMistico()); }

    @ParameterizedTest @EnumSource(Aura.class) public void testSegnalazioneAzzeccagarbugli(Aura aura)
    {
        Ruolo contadino = getContadino(aura);
        int voti = 3;
        for(int i = 0; i < voti; i++) contadino.incrementaVoti();
        contadino.segnalazioneAzzeccagarbugli();
        verificaIntero(contadino.getNumeroVoti(), voti);
        verificaVero(contadino.isAccusato());
    }

    @Test public void testAzzeccagarbugli() { verificaFalso(ruolo.isAzzeccagarbugli()); }

    @Test public void testLupo() { verificaFalso(ruolo.isLupo()); }

    private Contadino getContadino(Aura aura) { return new Contadino(aura); }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaIntero(int valore, int risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}