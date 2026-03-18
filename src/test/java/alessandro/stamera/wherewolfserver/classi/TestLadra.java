package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestLadra
{

    private RuoliFactory factory;

    private Ruolo ruolo;

    @BeforeEach public void setUp()
    {
        factory = new RuoliFactory();
        ruolo = factory.getRuolo("Ladra");
    }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), "Ladra"); }

    @Test public void testDescrizione()
    {
        String descrizione =
            "La prima notte riconosce gli altri criminali. Una volta per partita, dalla seconda notte può aprire gli occhi nel turno di un " +
            "mistico. La prima volta che viene attaccata, è protetta dalle creature dell'ombra.";
        verificaStringa(ruolo.getDescrizione(), descrizione);
    }

    @Test public void testAura() { assertThat(ruolo.getAura()).isEqualTo(BIANCA); }

    @Test public void testCriminale() { verificaVero(ruolo.isCriminale()); }

    @Test public void testAssassino() { verificaFalso(ruolo.isAssassino()); }

    @Test public void testCapoGilda() { verificaFalso(ruolo.isCapoGilda()); }

    @Test public void testLadra() { verificaVero(ruolo.isLadra()); }

    @ParameterizedTest @CsvSource({ "Capo branco, Lupo del branco, Giovane lupo, Lupo reietto, Lupo solitario" })
    public void testUtilizzoPotere(String nome)
    {
        verificaFalso(isPotereUtilizzato());
        verificaVero(ruolo.isProtezionePresente(getRuolo(nome)));
        ruolo.utilizzaPotere();
        verificaVero(isPotereUtilizzato());
        for(int i = 0; i < factory.getNumeroRuoli(); i++) verificaFalso(ruolo.isProtezionePresente(getRuolo(factory.getNome(i))));
    }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

    private boolean isPotereUtilizzato() { return ruolo.isPotereUtilizzato(); }

    private Ruolo getRuolo(String nome) { return factory.getRuolo(nome); }

}