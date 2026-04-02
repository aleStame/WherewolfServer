package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static alessandro.stamera.wherewolfserver.classi.EsitoAttacco.FALLITO;
import static alessandro.stamera.wherewolfserver.classi.EsitoAttacco.MORTO;
import static alessandro.stamera.wherewolfserver.classi.Fazione.VILLAGGIO;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestContadinoEroe
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = getRuolo("Contadino eroe"); }

    @Test public void testNome() { assertThat(ruolo.getNome()).isEqualTo("Contadino"); }

    @Test public void testFazione() { assertThat(ruolo.getFazione()).isEqualTo(VILLAGGIO); }

    @Test public void testContadino() { verificaVero(ruolo.isContadino()); }

    @Test public void testContadinoNormale() { verificaFalso(ruolo.isContadinoNormale()); }

    @Test public void testContadinoMostro() { verificaFalso(ruolo.isContadinoMostro()); }

    @Test public void testContadinoEroe() { verificaVero(ruolo.isContadinoEroe()); }

    @Test public void testContadinoLupo() { verificaFalso(ruolo.isContadinoLupo()); }

    @ParameterizedTest
    @CsvSource({ "Capo branco, Lupo del branco, Giovane lupo, Lupo reietto, Lupo solitario, Contadino discendente dei lupi" })
    public void testAttaccoLupiNonProtetto(String nome) { verificaEsitoAttacco(nome, MORTO); }

    @ParameterizedTest
    @CsvSource({ "Capo branco, Lupo del branco, Giovane lupo, Lupo reietto, Lupo solitario, Contadino discendente dei lupi" })
    public void testAttaccoLupiRomeo(String nome)
    {
        ruolo.romeizzazione();
        verificaAttaccoFallito(nome);
    }

    @ParameterizedTest
    @CsvSource({ "Capo branco, Lupo del branco, Giovane lupo, Lupo reietto, Lupo solitario, Contadino discendente dei lupi" })
    public void testAttaccoLupiAmato(String nome)
    {
        ruolo.sceltaAngeloCustode();
        verificaAttaccoFallito(nome);
    }

    private void verificaAttaccoFallito(String nome) { verificaEsitoAttacco(nome, FALLITO); }

    private void verificaEsitoAttacco(String nome, EsitoAttacco esito)
    {
        assertThat(ruolo.attaccoLupi(getRuolo(nome))).isEqualTo(esito);
    }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

    private Ruolo getRuolo(String nome) { return FACTORY.getRuolo(nome); }

}