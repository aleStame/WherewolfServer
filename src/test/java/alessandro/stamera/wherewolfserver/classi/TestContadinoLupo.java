package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static alessandro.stamera.wherewolfserver.classi.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.VILLAGGIO;
import static alessandro.stamera.wherewolfserver.classi.Tratto.CREATURA_OMBRA;
import static alessandro.stamera.wherewolfserver.classi.Tratto.LUPO_MANNARO;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestContadinoLupo
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new RuoliFactory().getRuolo("Contadino discendente dei lupi"); }

    @Test public void testNome() { assertThat(ruolo.getNome()).isEqualTo("Contadino"); }

    @Test public void testFazione() { assertThat(ruolo.getFazione()).isEqualTo(VILLAGGIO); }

    @Test public void testContadino() { verificaVero(ruolo.isContadino()); }

    @Test public void testContadinoNormale() { verificaFalso(ruolo.isContadinoNormale()); }

    @Test public void testContadinoMostro() { verificaFalso(ruolo.isContadinoMostro()); }

    @Test public void testContadinoEroe() { verificaFalso(ruolo.isContadinoEroe()); }

    @Test public void testContadinoLupo() { verificaVero(ruolo.isContadinoLupo()); }

    @ParameterizedTest @CsvSource({ "CAPO_BRANCO, LUPO_BRANCO, GIOVANE_LUPO, LUPO_REIETTO, LUPO_SOLITARIO" })
    public void testAttaccoLupi(IstanzaRuolo istanza)
    {
        verificaFalso(ruolo.attacco(istanza.getRuolo()));
        assertThat(ruolo.getAura()).isEqualTo(NERA);
        for(Tratto tratto : new Tratto[] { CREATURA_OMBRA, LUPO_MANNARO }) verificaVero(ruolo.isTrattoPresente(tratto));
    }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

}