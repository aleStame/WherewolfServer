package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.Categoria.CREATURE_OMBRA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.NOSFERATU;
import static alessandro.stamera.wherewolfserver.classi.Tratto.CREATURA_OMBRA;
import static org.assertj.core.api.Assertions.assertThat;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;

public final class TestNosferatu
{

    private static final String NOME = "Nosferatu";

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = FACTORY.getRuolo(NOME); }

    @Test public void testNome() { assertThat(ruolo.getNome()).isEqualTo(NOME); }

    @Test public void testFazione() { assertThat(ruolo.getFazione()).isEqualTo(NOSFERATU); }

    @Test public void testCategoria() { assertThat(ruolo.getCategoria()).isEqualTo(CREATURE_OMBRA); }

    @Test public void testAura() { verificaAuraNera(ruolo.getAura()); }

    @Test public void testControlloMedium() { verificaAuraNera(ruolo.controlloMedium()); }

    @Test public void testMistico() { verificaFalso(ruolo.isMistico()); }

    @Test public void testCreatureOmbra() { verificaVero(ruolo.isTrattoPresente(CREATURA_OMBRA)); }

    @Test public void testNosferatu() { verificaVero(ruolo.isNosferatu()); }

    @Test public void testAmanti() { verificaFalso(ruolo.isAmanti()); }

    @Test public void testCitta() { verificaFalso(ruolo.isCitta()); }

    @Test public void testCriminale() { verificaFalso(ruolo.isCriminale()); }

    @Test public void testGhoul() { verificaFalso(ruolo.isGhoul()); }

    @Test public void testGiullare() { verificaFalso(ruolo.isGiullare()); }

    @Test public void testGoblin() { verificaFalso(ruolo.isGoblin()); }

    @Test public void testInquisizione() { verificaFalso(ruolo.isInquisizione()); }

    @Test public void testLupo() { verificaFalso(ruolo.isLupo()); }

    private void verificaAuraNera(Aura aura) { assertThat(aura).isEqualTo(NERA); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}