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

    private RuoliFactory factory;

    private Ruolo ruolo;

    @BeforeEach public void setUp()
    {
        factory = new RuoliFactory();
        ruolo = getRuolo("Contadino discendente dei lupi"); }

    @Test public void testNome() { assertThat(ruolo.getNome()).isEqualTo("Contadino"); }

    @Test public void testFazione() { assertThat(ruolo.getFazione()).isEqualTo(VILLAGGIO); }

    @Test public void testContadino() { verificaVero(ruolo.isContadino()); }

    @Test public void testContadinoNormale() { verificaFalso(ruolo.isContadinoNormale()); }

    @Test public void testContadinoMostro() { verificaFalso(ruolo.isContadinoMostro()); }

    @Test public void testContadinoEroe() { verificaFalso(ruolo.isContadinoEroe()); }

    @Test public void testContadinoLupo() { verificaVero(ruolo.isContadinoLupo()); }

    @ParameterizedTest @CsvSource({ "Capo branco, Lupo del branco, Giovane lupo, Lupo reietto, Lupo solitario" })
    public void testAttaccoLupi(String nome)
    {
        verificaFalso(ruolo.attacco(getRuolo(nome)));
        assertThat(ruolo.getAura()).isEqualTo(NERA);
        for(Tratto tratto : new Tratto[] { CREATURA_OMBRA, LUPO_MANNARO }) verificaVero(ruolo.isTrattoPresente(tratto));
    }

    private Ruolo getRuolo(String nome) { return factory.getRuolo(nome); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

}