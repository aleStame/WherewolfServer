package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static alessandro.stamera.wherewolfserver.classi.EsitoAttacco.FALLITO;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
import static alessandro.stamera.wherewolfserver.classi.Tratto.PROTETTO;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestPiccoloPopolo
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new PiccoloPopolo(null, null, null); }

    @Test public void testFazione() { assertThat(ruolo.getFazione()).isEqualTo(Fazione.NESSUNA); }

    @Test public void testCategoria() { assertThat(ruolo.getCategoria()).isEqualTo(Categoria.NESSUNA); }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(1); }

    @Test public void testMistico() { verificaVero(ruolo.isMistico()); }

    @Test public void testSegnalazioneInquisitore()
    {
        assertThat(isAccusato()).isFalse();
        ruolo.segnalazioneInquisitore();
        verificaVero(isAccusato());
    }

    @ParameterizedTest @CsvSource({ "Guaritore, Mago, Megera" }) public void testProtezioneMistici(String nome)
    {
        verificaVero(ruolo.isProtezionePresente(FACTORY.getRuolo(nome)));
        verificaVero(ruolo.isTrattoPresente(PROTETTO));
    }

    @Test public void testAttaccoNegromante()
    {
        verificaFalso(ruolo.maledizione());
        verificaFalso(ruolo.isMaledetto());
        int numeroVoti = 2;
        ruolo.incrementaVoti(numeroVoti);
        assertThat(ruolo.getNumeroVoti()).isEqualTo(numeroVoti);
    }

    @Test public void testAttaccoNosferatu() { assertThat(ruolo.attaccoNosferatu()).isEqualTo(FALLITO); }

    @Test public void testAmanti() { verificaFalso(ruolo.isAmanti()); }

    @Test public void testCitta() { verificaFalso(ruolo.isCitta()); }

    @Test public void testCriminale() { verificaFalso(ruolo.isCriminale()); }

    @Test public void testGhoul() { verificaFalso(ruolo.isGhoul()); }

    @Test public void testGiullare() { verificaFalso(ruolo.isGiullare()); }

    @Test public void testInquisizione() { verificaFalso(ruolo.isInquisizione()); }

    @Test public void testLupo() { verificaFalso(ruolo.isLupo()); }

    @Test public void testNosferatu() { verificaFalso(ruolo.isNosferatu()); }

    @Test public void testVillaggio() { verificaFalso(ruolo.isVillaggio()); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

    private boolean isAccusato() { return ruolo.isAccusato(); }

}