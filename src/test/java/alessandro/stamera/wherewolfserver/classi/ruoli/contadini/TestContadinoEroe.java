package alessandro.stamera.wherewolfserver.classi.ruoli.contadini;

import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.MORTO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.VILLAGGIO;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
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
    public void testAttaccoLupi(String nome) { assertThat(ruolo.attaccoLupi(getRuolo(nome))).isEqualTo(MORTO); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

    private Ruolo getRuolo(String nome) { return FACTORY.getRuolo(nome); }

}