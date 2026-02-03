package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import static alessandro.stamera.wherewolfserver.classi.Fazione.CRIMINALI;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doCallRealMethod;

@ExtendWith(MockitoExtension.class) public final class TestRuolo
{

    @Mock private Ruolo ruolo;

    private static final String ESEMPIO_NOME = "Ruolo", ESEMPIO_DESCRIZIONE = "Descrizione generica";

    private static final int ESEMPIO_VOTI = 2;

    @ParameterizedTest @MethodSource("getComboEnum") public void testLune(Fazione fazione, Aura aura, int lune, boolean mistico)
    {
        verificaIntero(getRuolo(fazione, aura, lune, mistico).getLune(), lune);
    }

    @ParameterizedTest @MethodSource("getComboEnum") public void testAura(Fazione fazione, Aura aura, int lune, boolean mistico)
    {
        assertThat(getRuolo(fazione, aura, lune, mistico).getAura()).isEqualTo(aura);
    }

    @ParameterizedTest @MethodSource("getComboEnum") public void testFazione(Fazione fazione, Aura aura, int lune, boolean mistico)
    {
        verificaFazione(getRuolo(fazione, aura, lune, mistico).getFazione(), fazione);
    }

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

    @ParameterizedTest @MethodSource("getComboEnum") public void testNome(Fazione fazione, Aura aura, int lune, boolean mistico)
    {
        verificaStringa(getRuolo(fazione, aura, lune, mistico).getNome(), ESEMPIO_NOME);
    }

    @ParameterizedTest @MethodSource("getComboEnum")
    public void testDescrizione(Fazione fazione, Aura aura, int lune, boolean mistico)
    {
        verificaStringa(getRuolo(fazione, aura, lune, mistico).getDescrizione(), ESEMPIO_DESCRIZIONE);
    }

    @ParameterizedTest @MethodSource("getComboEnum")
    public void testMistico(Fazione fazione, Aura aura, int lune, boolean mistico)
    {
        assertThat(getRuolo(fazione, aura, lune, mistico).isMistico()).isEqualTo(mistico);
    }

    @Test public void testContadino() { verificaFalso(ruolo.isContadino()); }

    @Test public void testContadinoNormale() { verificaFalso(ruolo.isContadinoNormale()); }

    @Test public void testContadinoMostro() { verificaFalso(ruolo.isContadinoMostro()); }

    @Test public void testContadinoEroe() { verificaFalso(ruolo.isContadinoEroe()); }

    @Test public void testContadinoLupo() { verificaFalso(ruolo.isContadinoLupo()); }

    @ParameterizedTest @MethodSource("getComboEnum")
    public void testVoti(Fazione fazione, Aura aura, int lune, boolean mistico) { verificaVoti(getEsempioVoti(fazione, aura, lune, mistico)); }

    @ParameterizedTest @MethodSource("getComboEnum")
    public void testAnnullamentoVoti(Fazione fazione, Aura aura, int lune, boolean mistico)
    {
        Ruolo ruolo = getEsempioVoti(fazione, aura, lune, mistico);
        ruolo.annullaVoti();
        assertThat(ruolo.getNumeroVoti()).isZero();
    }

    @Test public void testAmato() { verificaFalso(ruolo.isAmato()); }

    @ParameterizedTest @MethodSource("getComboEnum")
    public void testSceltaAngeloCustode(Fazione fazione, Aura aura, int lune, boolean mistico)
    {
        Ruolo ruolo = getRuolo(fazione, aura, lune, mistico);
        ruolo.sceltaAngeloCustode();
        verificaVero(ruolo.isAmato());
    }

    @Test public void testAngeloCustode() { verificaFalso(ruolo.isAngeloCustode()); }

    @Test public void testAssassino() { verificaFalso(ruolo.isAssassino()); }

    @Test public void testAssassinio() { verificaFalso(ruolo.assassinioAvvenuto()); }

    @Test public void testBecchino() { verificaFalso(ruolo.isBecchino()); }

    @ParameterizedTest @MethodSource("getComboFazioni")
    public void testCambioFazione(Fazione fazioneVecchia, Aura aura, int lune, boolean mistico, Fazione fazioneNuova)
    {
        Ruolo ruolo = getRuolo(fazioneVecchia, aura, lune, mistico);
        ruolo.cambiaFazione(fazioneNuova);
        verificaFazione(ruolo.getFazione(), fazioneNuova);
    }

    @Test public void testBoccaDiRosa() { verificaFalso(ruolo.isBoccaDiRosa()); }

    @Test public void testCapoGilda()
    {
        doCallRealMethod().when(ruolo).isCapoGilda();
        verificaFalso(ruolo.isCapoGilda());
    }

    @ParameterizedTest @MethodSource("getComboEnum")
    public void testGildata(Fazione fazione, Aura aura, int lune, boolean mistico)
    {
        Ruolo ruolo = getRuolo(fazione, aura, lune, mistico);
        verificaVero(ruolo.gildata());
        verificaFazione(ruolo.getFazione(), CRIMINALI);
    }

    @Test public void testCapoBranco()
    {
        doCallRealMethod().when(ruolo).isCapoBranco();
        verificaFalso(ruolo.isCapoBranco());
    }

    @Test public void testLupoBranco()
    {
        doCallRealMethod().when(ruolo).isLupoBranco();
        verificaFalso(ruolo.isLupoBranco());
    }

    @Test public void testGiovaneLupo()
    {
        doCallRealMethod().when(ruolo).isGiovaneLupo();
        verificaFalso(ruolo.isGiovaneLupo());
    }

    @Test public void testLupoReietto()
    {
        doCallRealMethod().when(ruolo).isLupoReietto();
        verificaFalso(ruolo.isLupoReietto());
    }

    @Test public void testLupoSolitario()
    {
        doCallRealMethod().when(ruolo).isLupoSolitario();
        verificaFalso(ruolo.isLupoSolitario());
    }

    @ParameterizedTest @MethodSource("getComboEnum")
    public void testSegnalazioneAzzeccagarbugli(Fazione fazione, Aura aura, int lune, boolean mistico)
    {
        Ruolo ruolo = getRuolo(fazione, aura, lune, mistico);
        ruolo.segnalazioneAzzeccagarbugli();
        assertThat(ruolo.isAccusato()).isTrue();
    }

    @Test public void testAzzeccagarbugli()
    {
        doCallRealMethod().when(ruolo).isAzzeccagarbugli();
        verificaFalso(ruolo.isAzzeccagarbugli());
    }

    @Test public void testAccusa()
    {
        doCallRealMethod().when(ruolo).isAccusato();
        verificaFalso(ruolo.isAccusato());
    }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFazione(Fazione valore, Fazione risultato) { assertThat(valore).isEqualTo(risultato); }

    private static Stream<Arguments> getComboFazioni()
    {
        List<Arguments> risultato = new ArrayList<>();
        for(Arguments argomenti : getComboEnum().toList()) for(Fazione fazioneNuova : Fazione.values())
            risultato.add(Arguments.of(argomenti.get()[0], argomenti.get()[1], argomenti.get()[2], argomenti.get()[3], fazioneNuova));
        return risultato.stream();
    }

    private static Stream<Arguments> getComboEnum()
    {
        List<Arguments> argomenti = new ArrayList<>();
        for(Fazione fazione : Fazione.values()) for(Aura aura : Aura.values()) for(int lune = 1; lune <= 3; lune++)
        {
            argomenti.add(Arguments.of(fazione, aura, lune, true));
            argomenti.add(Arguments.of(fazione, aura, lune, false));
        }
        return argomenti.stream();
    }

    private Ruolo getEsempioVoti(Fazione fazione, Aura aura, int lune, boolean mistico)
    {
        Ruolo ruolo = getRuolo(fazione, aura, lune, mistico);
        for(int i = 0; i < ESEMPIO_VOTI; i++) ruolo.incrementaVoti();
        return ruolo;
    }

    private Ruolo getRuolo(Fazione fazione, Aura aura, int lune, boolean mistico)
    {
        return new Ruolo(ESEMPIO_NOME, fazione, aura, ESEMPIO_DESCRIZIONE, lune, mistico);
    }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

    private void verificaVoti(Ruolo ruolo) { verificaIntero(ruolo.getNumeroVoti(), ESEMPIO_VOTI); }

    private void verificaIntero(int valore, int risultato) { assertThat(valore).isEqualTo(risultato); }

}