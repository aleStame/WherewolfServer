package alessandro.stamera.wherewolfserver.classi.ruoli.contadini;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Tratto;
import alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.FALLITO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.*;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.TipoContadino.LUPO;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Tratto.CREATURA_OMBRA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Tratto.LUPO_MANNARO;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestContadinoLupo
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = getRuolo("Contadino discendente dei lupi"); }

    @Test public void testNome() { assertThat(ruolo.getNome()).isEqualTo("Contadino"); }

    @Test public void testContadino() { verificaVero(ruolo.isContadino()); }

    @Test public void testContadinoNormale() { verificaFalso(ruolo.isContadinoNormale()); }

    @Test public void testContadinoMostro() { verificaFalso(ruolo.isContadinoMostro()); }

    @Test public void testContadinoEroe() { verificaFalso(ruolo.isContadinoEroe()); }

    @Test public void testContadinoLupo() { verificaVero(ruolo.isContadinoLupo()); }

    @ParameterizedTest
    @CsvSource({ "Capo branco, LUPO_BRANCO", "Lupo del branco, LUPO_BRANCO", "Lupo reietto, LUPO_BRANCO", "Lupo solitario, LUPO_SOLITARIO" })
    public void testAttaccoLupi(String nome, Fazione fazione) { verificaContadinoLupo(nome, fazione); }

    @ParameterizedTest @MethodSource({ "getEsempiEsitiPartita" })
    public void testEsitoPartitaDopoAttacco(Partita partita, EsitoPartita esito)
    {
        verificaAttaccoFallito("Capo branco");
        assertThat(ruolo.getEsitoPartita(partita)).isEqualTo(esito);
    }

    @ParameterizedTest
    @CsvSource({ "Capo branco, LUPO_BRANCO", "Lupo del branco, LUPO_BRANCO", "Lupo reietto, LUPO_BRANCO", "Lupo solitario, LUPO_SOLITARIO" })
    public void testAttaccoLupiAngeloCustode(String nome, Fazione fazione)
    {
        ruolo.sceltaAngeloCustode();
        verificaContadinoLupo(nome, fazione);
    }

    @ParameterizedTest
    @CsvSource({ "Capo branco, LUPO_BRANCO", "Lupo del branco, LUPO_BRANCO", "Lupo reietto, LUPO_BRANCO", "Lupo solitario, LUPO_SOLITARIO" })
    public void testAttaccoLupiGiulietta(String nome, Fazione fazione)
    {
        ruolo.romeizzazione();
        verificaContadinoLupo(nome, fazione);
    }

    @Test public void testRipristino()
    {
        ruolo.ripristina();
        verificaFalso(ruolo.isCreaturaOmbra());
        verificaFalso(ruolo.isLupo());
    }

    @Test public void testTipoContadino() { assertThat(ruolo.getTipoContadino()).isEqualTo(LUPO); }

    private void verificaContadinoLupo(String nome, Fazione fazione)
    {
        verificaAttaccoFallito(nome);
        assertThat(ruolo.getAura()).isEqualTo(NERA);
        for(Tratto tratto : new Tratto[] { CREATURA_OMBRA, LUPO_MANNARO }) verificaVero(ruolo.isTrattoPresente(tratto));
        assertThat(ruolo.getFazione()).isEqualTo(fazione);
    }

    private void verificaAttaccoFallito(String nome) { assertThat(ruolo.attaccoLupi(getRuolo(nome))).isEqualTo(FALLITO); }

    private static Stream<Arguments> getEsempiEsitiPartita()
    {
        Partita[] partite = new Partita[]
        {
            new Partita(new String[][] { { "Aurora", "Oratore" }, { "Giulia", "Prete" } }),
            new Partita(new String[][] { { "Francesca", "Lupo del branco" }, { "Ermenegildo", "Peccatore" } }),
            new Partita(new String[][] { { "Noemi", "Capo branco" }, { "Elisa", "Lupo del branco" }, { "Damiano", "Pazzo" } })
        };
        return Stream.of(Arguments.of(partite[0], SCONFITTA), Arguments.of(partite[1], NON_FINITO), Arguments.of(partite[2], VITTORIA));
    }

    private Ruolo getRuolo(String nome) { return FACTORY.getRuolo(nome); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

}