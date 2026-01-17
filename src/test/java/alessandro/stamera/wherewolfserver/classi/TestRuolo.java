package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import static alessandro.stamera.wherewolfserver.classi.Aura.*;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestRuolo
{

    private static final String ESEMPIO_NOME = "Ruolo", ESEMPIO_DESCRIZIONE = "Descrizione generica";

    private static final int ESEMPIO_VOTI = 2;

    @ParameterizedTest @MethodSource("getComboEnum")
    public void testLune(Fazione fazione, Aura aura, int lune) { assertThat(getRuolo(fazione, aura, lune).getLune()).isEqualTo(lune); }

    @ParameterizedTest @MethodSource("getComboEnum")
    public void testAura(Fazione fazione, Aura aura, int lune) { assertThat(getRuolo(fazione, aura, lune).getAura()).isEqualTo(aura); }

    @ParameterizedTest @MethodSource("getComboEnum")
    public void testFazione(Fazione fazione, Aura aura, int lune) { assertThat(getRuolo(fazione, aura, lune).getFazione()).isEqualTo(fazione); }

    @ParameterizedTest @CsvSource
    (
        {
            "NESSUNA, NESSUNA", "LUPO_BRANCO, CREATURE_OMBRA", "LUPO_SOLITARIO, CREATURE_OMBRA", "VAMPIRO, CREATURE_OMBRA",
            "NOSFERATU, CREATURE_OMBRA", "NEGROMANTE, CREATURE_OMBRA", "POSSEDUTO, CREATURE_OMBRA", "VILLAGGIO, UOMINI", "CITTA, UOMINI",
            "CRIMINALI, UOMINI", "AMANTI, UOMINI", "INQUISIZIONE, UOMINI"
        }
    )
    public void testCategoria(Fazione fazione, Categoria categoria)
    {
        assertThat(getRuolo(fazione, NERA, 2).getCategoria()).isEqualTo(categoria);
    }

    @ParameterizedTest @MethodSource("getComboEnum")
    public void testNome(Fazione fazione, Aura aura, int lune) { verificaStringa(getRuolo(fazione, aura, lune).getNome(), ESEMPIO_NOME); }

    @ParameterizedTest @MethodSource("getComboEnum")
    public void testDescrizione(Fazione fazione, Aura aura, int lune)
    {
        verificaStringa(getRuolo(fazione, aura, lune).getDescrizione(), ESEMPIO_DESCRIZIONE);
    }

    @ParameterizedTest @MethodSource("getComboEnum")
    public void testContadino(Fazione fazione, Aura aura, int lune) { verificaFalso(getRuolo(fazione, aura, lune).isContadino()); }

    @ParameterizedTest @MethodSource("getComboEnum")
    public void testContadinoNormale(Fazione fazione, Aura aura, int lune) { verificaFalso(getRuolo(fazione, aura, lune).isContadinoNormale()); }

    @ParameterizedTest @MethodSource("getComboEnum")
    public void testContadinoMostro(Fazione fazione, Aura aura, int lune) { verificaFalso(getRuolo(fazione, aura, lune).isContadinoMostro()); }

    @ParameterizedTest @MethodSource("getComboEnum")
    public void testContadinoEroe(Fazione fazione, Aura aura, int lune) { verificaFalso(getRuolo(fazione, aura, lune).isContadinoEroe()); }

    @ParameterizedTest @MethodSource("getComboEnum")
    public void testContadinoLupo(Fazione fazione, Aura aura, int lune) { verificaFalso(getRuolo(fazione, aura, lune).isContadinoLupo()); }

    @ParameterizedTest @MethodSource("getComboEnum") public void testVoti(Fazione fazione, Aura aura, int lune)
    {
        assertThat(getEsempioVoti(fazione, aura, lune).getNumeroVoti()).isEqualTo(ESEMPIO_VOTI);
    }

    @ParameterizedTest @MethodSource("getComboEnum") public void testAnnullamentoVoti(Fazione fazione, Aura aura, int lune)
    {
        Ruolo ruolo = getEsempioVoti(fazione, aura, lune);
        ruolo.annullaVoti();
        assertThat(ruolo.getNumeroVoti()).isZero();
    }

    private static Stream<Arguments> getComboEnum()
    {
        List<Arguments> argomenti = new ArrayList<>();
        for(Fazione fazione : Fazione.values()) for(Aura aura : Aura.values()) for(int lune = 1; lune <= 3; lune++)
            argomenti.add(Arguments.of(fazione, aura, lune));
        return argomenti.stream();
    }

    private Ruolo getEsempioVoti(Fazione fazione, Aura aura, int lune)
    {
        Ruolo ruolo = getRuolo(fazione, aura, lune);
        for(int i = 0; i < ESEMPIO_VOTI; i++) ruolo.incrementaVoti();
        return ruolo;
    }

    private Ruolo getRuolo(Fazione fazione, Aura aura, int lune)
    {
        return new Ruolo(ESEMPIO_NOME, fazione, aura, ESEMPIO_DESCRIZIONE, lune);
    }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}