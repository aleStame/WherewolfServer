package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import static alessandro.stamera.wherewolfserver.classi.Fazione.VILLAGGIO;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestContadino
{

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

    @ParameterizedTest @EnumSource(Aura.class)
    public void testContadino(Aura aura) { assertThat(getContadino(aura).isContadino()).isTrue(); }

    @ParameterizedTest @EnumSource(Aura.class)
    public void testContadinoNormale(Aura aura) { verificaFalso(getContadino(aura).isContadinoNormale()); }

    @ParameterizedTest @EnumSource(Aura.class)
    public void testContadinoMostro(Aura aura) { verificaFalso(getContadino(aura).isContadinoMostro()); }

    @ParameterizedTest @EnumSource(Aura.class)
    public void testContadinoEroe(Aura aura) { verificaFalso(getContadino(aura).isContadinoEroe()); }

    @ParameterizedTest @EnumSource(Aura.class)
    public void testContadinoLupo(Aura aura) { verificaFalso(getContadino(aura).isContadinoLupo()); }

    private Contadino getContadino(Aura aura) { return new Contadino(aura); }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}