package alessandro.stamera.wherewolfserver.classi.fazioni;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoControlloSensitiva;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione;
import alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Categoria.UOMINI;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.RIUSCITO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.*;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.VILLAGGIO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.CRIMINALI;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestVillaggio
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new Villaggio(null, null, null, -1, true); }

    @Test public void testFazione() { verificaFazione(VILLAGGIO); }

    @Test public void testCategoria() { assertThat(ruolo.getCategoria()).isEqualTo(UOMINI); }

    @Test public void testAmanti() { verificaFalso(ruolo.isAmanti()); }

    @Test public void testCitta() { verificaFalso(ruolo.isCitta()); }

    @Test public void testCriminale() { verificaFalso(ruolo.isCriminale()); }

    @Test public void testLupo() { verificaFalso(ruolo.isLupo()); }

    @Test public void testNosferatu() { verificaFalso(ruolo.isNosferatu()); }

    @Test public void testPazzo() { verificaFalso(ruolo.isPazzo()); }

    @Test public void testPiccoloPopolo() { verificaFalso(ruolo.isPiccoloPopolo()); }

    @Test public void testPosseduto() { verificaFalso(ruolo.isPosseduto()); }

    @Test public void testVampiro() { verificaFalso(ruolo.isVampiro()); }

    @Test public void testVillaggio() { assertThat(ruolo.isVillaggio()).isTrue(); }

    @ParameterizedTest @MethodSource("getEsempiEsitiPartita")
    public void testEsitoPartita(Partita partita, EsitoPartita esito) { assertThat(ruolo.getEsitoPartita(partita)).isEqualTo(esito); }

    @ParameterizedTest @MethodSource("getEsempiEsitiPartitaPostGildata")
    public void testEsitoPartitaPostGildata(Partita partita, EsitoPartita esito)
    {
        assertThat(ruolo.gildata()).isEqualTo(RIUSCITO);
        verificaFazione(CRIMINALI);
        assertThat(ruolo.getEsitoPartita(partita)).isEqualTo(esito);
        FACTORY.annullaSegnalazioni();
    }

    @Test public void testControlloSensitiva() { assertThat(ruolo.controlloSensitiva()).isEqualTo(EsitoControlloSensitiva.VILLAGGIO); }

    private static Stream<Arguments> getEsempiEsitiPartita()
    {
        Partita[] partite = new Partita[]
        {
            new Partita(new String[][] { { "Noemi", "Bardo" }, { "Elisa", "Pazzo" } }),
            new Partita(new String[][] { { "Elena", "Assassino" }, { "Mattia", "Altra guardia" } }),
            new Partita(new String[][] { { "Federico", "Capo gilda" }, { "Otello", "Giullare" } })
        };
        return Stream.of(Arguments.of(partite[0], VITTORIA), Arguments.of(partite[1], VITTORIA), Arguments.of(partite[2], SCONFITTA));
    }

    private static Stream<Arguments> getEsempiEsitiPartitaPostGildata()
    {
        return Stream.of
        (
            Arguments.of(new Partita(new String[][] { { "Matteo", "Guardia" }, { "Marghe", "Altra guardia" } }), SCONFITTA),
            Arguments.of
            (
                new Partita(new String[][] { { "Giuseppe", "Prete" }, { "Salvatore", "Peccatore" }, { "Marino", "Bocca di rosa" } }), SCONFITTA
            ),
            Arguments.of(new Partita(new String[][] { { "Mike", "Capo gilda" }, { "Susan", "Prete" } }), VITTORIA)
        );
    }

    private void verificaFazione(Fazione fazione) { assertThat(ruolo.getFazione()).isEqualTo(fazione); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}