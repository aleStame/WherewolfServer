package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import static alessandro.stamera.wherewolfserver.classi.Fazione.CRIMINALI;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doCallRealMethod;

public final class TestRuolo
{

    private Ruolo ruolo;

    private static final String ESEMPIO_NOME = "Ruolo", ESEMPIO_DESCRIZIONE = "Descrizione generica";

    private static final int ESEMPIO_VOTI = 2;

    @BeforeEach public void setUp() { ruolo = mock(Ruolo.class); }

    @ParameterizedTest @MethodSource("getComboEnum")
    public void testLune(Fazione fazione, Aura aura, int lune) { assertThat(getRuolo(fazione, aura, lune).getLune()).isEqualTo(lune); }

    @ParameterizedTest @MethodSource("getComboEnum")
    public void testAura(Fazione fazione, Aura aura, int lune) { assertThat(getRuolo(fazione, aura, lune).getAura()).isEqualTo(aura); }

    @ParameterizedTest @MethodSource("getComboEnum")
    public void testFazione(Fazione fazione, Aura aura, int lune) { verificaFazione(getRuolo(fazione, aura, lune).getFazione(), fazione); }

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
        when(ruolo.getFazione()).thenReturn(fazione);
        doCallRealMethod().when(ruolo).getCategoria();
        assertThat(ruolo.getCategoria()).isEqualTo(categoria);
    }

    @ParameterizedTest @MethodSource("getComboEnum")
    public void testNome(Fazione fazione, Aura aura, int lune) { verificaStringa(getRuolo(fazione, aura, lune).getNome(), ESEMPIO_NOME); }

    @ParameterizedTest @MethodSource("getComboEnum")
    public void testDescrizione(Fazione fazione, Aura aura, int lune)
    {
        verificaStringa(getRuolo(fazione, aura, lune).getDescrizione(), ESEMPIO_DESCRIZIONE);
    }

    @Test public void testContadino() { verificaFalso(ruolo.isContadino()); }

    @Test public void testContadinoNormale() { verificaFalso(ruolo.isContadinoNormale()); }

    @Test public void testContadinoMostro() { verificaFalso(ruolo.isContadinoMostro()); }

    @Test public void testContadinoEroe() { verificaFalso(ruolo.isContadinoEroe()); }

    @Test public void testContadinoLupo() { verificaFalso(ruolo.isContadinoLupo()); }

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

    @Test public void testAmato() { verificaFalso(ruolo.isAmato()); }

    @ParameterizedTest @MethodSource("getComboEnum") public void testSceltaAngeloCustode(Fazione fazione, Aura aura, int lune)
    {
        Ruolo ruolo = getRuolo(fazione, aura, lune);
        ruolo.sceltaAngeloCustode();
        assertThat(ruolo.isAmato()).isTrue();
    }

    @Test public void testAngeloCustode() { verificaFalso(ruolo.isAngeloCustode()); }

    @Test public void testAssassino() { verificaFalso(ruolo.isAssassino()); }

    @Test public void testAssassinio() { verificaFalso(ruolo.assassinioAvvenuto()); }

    @Test public void testBecchino() { verificaFalso(ruolo.isBecchino()); }

    @ParameterizedTest @MethodSource("getComboFazioni")
    public void testCambioFazione(Fazione fazioneVecchia, Aura aura, int lune, Fazione fazioneNuova)
    {
        Ruolo ruolo = getRuolo(fazioneVecchia, aura, lune);
        ruolo.cambiaFazione(fazioneNuova);
        verificaFazione(ruolo.getFazione(), fazioneNuova);
    }

    @Test public void testBoccaDiRosa() { verificaFalso(ruolo.isBoccaDiRosa()); }

    @ParameterizedTest @MethodSource("getComboFazioni") public void testGildata(Fazione fazione, Aura aura, int lune)
    {
        Ruolo ruolo = getRuolo(fazione, aura, lune);
        assertThat(ruolo.gildata()).isTrue();
        verificaFazione(ruolo.getFazione(), CRIMINALI);
    }

    private void verificaFazione(Fazione valore, Fazione risultato) { assertThat(valore).isEqualTo(risultato); }

    private static Stream<Arguments> getComboFazioni()
    {
        List<Arguments> risultato = new ArrayList<>();
        for(Arguments argomenti : getComboEnum().toList()) for(Fazione fazioneNuova : Fazione.values())
            risultato.add(Arguments.of(argomenti.get()[0], argomenti.get()[1], argomenti.get()[2], fazioneNuova));
        return risultato.stream();
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