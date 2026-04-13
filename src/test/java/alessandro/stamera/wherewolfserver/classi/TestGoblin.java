package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static alessandro.stamera.wherewolfserver.classi.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.EsitoAttacco.FALLITO;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
import static alessandro.stamera.wherewolfserver.classi.Tratto.PROTETTO;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestGoblin
{

    private static final String NOME = "Goblin";

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = getRuolo(NOME); }

    @Test public void testNome() { assertThat(ruolo.getNome()).isEqualTo(NOME); }

    @Test public void testLune() { verificaNumeroIntero(ruolo.getLune(), 1); }

    @Test public void testDescrizione()
    {
        String descrizione = "La prima notte riconosce le altre creature del piccolo popolo ed è protetto da tutti i mistici.";
        assertThat(ruolo.getDescrizione()).isEqualTo(descrizione);
    }

    @Test public void testFazione() { assertThat(ruolo.getFazione()).isEqualTo(Fazione.NESSUNA); }

    @Test public void testCategoria() { assertThat(ruolo.getCategoria()).isEqualTo(Categoria.NESSUNA); }

    @Test public void testAura() { verificaAuraNera(ruolo.getAura()); }

    @Test public void testMistico() { verificaVero(ruolo.isMistico()); }

    @Test public void testCitta() { verificaFalso(ruolo.isCitta()); }

    @Test public void testCriminale() { verificaFalso(ruolo.isCriminale()); }

    @Test public void testGhoul() { verificaFalso(ruolo.isGhoul()); }

    @Test public void testGiullare() { verificaFalso(ruolo.isGiullare()); }

    @Test public void testGoblin() { verificaVero(ruolo.isGoblin()); }

    @Test public void testInquisizione() { verificaFalso(ruolo.isInquisizione()); }

    @Test public void testLeprecauno() { verificaFalso(ruolo.isLeprecauno()); }

    @Test public void testLupo() { verificaFalso(ruolo.isLupo()); }

    @Test public void testVillaggio() { verificaFalso(ruolo.isVillaggio()); }

    @Test public void testControlloMedium() { verificaAuraNera(ruolo.controlloMedium()); }

    @Test public void testNosferatu() { verificaFalso(ruolo.isNosferatu()); }

    private void verificaNumeroIntero(int valore, int risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaAuraNera(Aura aura) { assertThat(aura).isEqualTo(NERA); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

    private boolean isAccusato() { return ruolo.isAccusato(); }

    private Ruolo getRuolo(String nome) { return FACTORY.getRuolo(nome); }

}