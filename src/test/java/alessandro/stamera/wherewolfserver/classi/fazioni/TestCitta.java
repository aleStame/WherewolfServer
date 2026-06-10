package alessandro.stamera.wherewolfserver.classi.fazioni;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita;
import alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Categoria.UOMINI;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.RIUSCITO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.SCONFITTA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.VITTORIA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.CITTA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.CRIMINALI;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestCitta
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new Citta(null, null, null); }

    @Test public void testFazione() { verificaFazione(CITTA); }

    @Test public void testCategoria() { assertThat(ruolo.getCategoria()).isEqualTo(UOMINI); }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(2); }

    @Test public void testMistico() { verificaFalso(ruolo.isMistico()); }

    @Test public void testAmanti() { verificaFalso(ruolo.isAmanti()); }

    @Test public void testBoia() { verificaFalso(ruolo.isBoia()); }

    @Test public void testCitta() { assertThat(ruolo.isCitta()).isTrue(); }

    @Test public void testCriminale() { verificaFalso(ruolo.isCriminale()); }

    @Test public void testGhoul() { verificaFalso(ruolo.isGhoul()); }

    @Test public void testGiullare() { verificaFalso(ruolo.isGiullare()); }

    @Test public void testInquisizione() { verificaFalso(ruolo.isInquisizione()); }

    @Test public void testLupo() { verificaFalso(ruolo.isLupo()); }

    @Test public void testMegera() { verificaFalso(ruolo.isMegera()); }

    @Test public void testNosferatu() { verificaFalso(ruolo.isNosferatu()); }

    @Test public void testPazzo() { verificaFalso(ruolo.isPazzo()); }

    @Test public void testPiccoloPopolo() { verificaFalso(ruolo.isPiccoloPopolo()); }

    @Test public void testPeccatore() { verificaFalso(ruolo.isPeccatore()); }

    @Test public void testPosseduto() { verificaFalso(ruolo.isPosseduto()); }

    @Test public void testVillaggio() { verificaFalso(ruolo.isVillaggio()); }

    @Test public void testGoblin() { verificaFalso(ruolo.isGoblin()); }

    @Test public void testVittoria()
    {
        Partita partita = new Partita(new String[][] { { "Paolo", "Contadino eroe" }, { "Michele", "Mercante" } });
        assertThat(ruolo.getEsitoPartita(partita)).isEqualTo(VITTORIA);
    }

    @ParameterizedTest @MethodSource("getEsempiEsitiPartitaPostGildata")
    public void testEsitoPartitaPostGildata(Partita partita, EsitoPartita esito)
    {
        assertThat(ruolo.gildata()).isEqualTo(RIUSCITO);
        verificaFazione(CRIMINALI);
        assertThat(ruolo.getEsitoPartita(partita)).isEqualTo(esito);
        FACTORY.annullaSegnalazioni();
    }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

    private void verificaFazione(Fazione fazione) { assertThat(ruolo.getFazione()).isEqualTo(fazione); }

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

}