package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestPeccatore
{

    private static final String NOME = "Peccatore";

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = FACTORY.getRuolo(NOME); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), NOME); }

    @Test public void testAura() { verificaAuraNera(ruolo.getAura()); }

    @Test public void testControlloMedium() { verificaAuraNera(ruolo.controlloMedium()); }

    @Test public void testDescrizione()
    {
        String descrizione =
            "La prima notte viene individuato dal Prete e apre gli occhi nel turno del Posseduto. Se rimane in gioco, vince con una vittoria " +
            "degli uomini, altrimenti con il Posseduto";
        verificaStringa(ruolo.getDescrizione(), descrizione);
    }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(1); }

    @Test public void testMistico() { verificaFalso(ruolo.isMistico()); }

    @Test public void testBardo() { verificaFalso(ruolo.isBardo()); }

    @Test public void testBecchino() { verificaFalso(ruolo.isBecchino()); }

    @Test public void testBracconiere() { verificaFalso(ruolo.isBracconiere()); }

    @Test public void testCacciatore() { verificaFalso(ruolo.isCacciatore()); }

    @Test public void testCacciatoreDiVampiri() { verificaFalso(ruolo.isCacciatoreDiVampiri()); }

    @Test public void testCappuccettoRosso() { verificaFalso(ruolo.isCappuccettoRosso()); }

    @Test public void testCitta() { verificaFalso(ruolo.isCitta()); }

    @Test public void testContadino() { verificaFalso(ruolo.isContadino()); }

    @Test public void testCriminale() { verificaFalso(ruolo.isCriminale()); }

    @Test public void testEremita() { verificaFalso(ruolo.isEremita()); }

    @Test public void testGhoul() { verificaFalso(ruolo.isGhoul()); }

    @Test public void testGuardia() { verificaFalso(ruolo.isGuardia()); }

    @Test public void testGuaritore() { verificaFalso(ruolo.isGuaritore()); }

    @Test public void testInquisizione() { verificaFalso(ruolo.isInquisizione()); }

    @Test public void testLeprecauno() { verificaFalso(ruolo.isLeprecauno()); }

    @Test public void testMago() { verificaFalso(ruolo.isMago()); }

    @Test public void testMegera() { verificaFalso(ruolo.isMegera()); }

    @Test public void testMedium() { verificaFalso(ruolo.isMedium()); }

    @Test public void testMonaco() { verificaFalso(ruolo.isMonaco()); }

    @Test public void testNonna() { verificaFalso(ruolo.isNonna()); }

    @Test public void testNosferatu() { verificaFalso(ruolo.isNosferatu()); }

    @Test public void testOste() { verificaFalso(ruolo.isOste()); }

    @Test public void testPazzo() { verificaFalso(ruolo.isPazzo()); }

    @Test public void testPeccatore() { assertThat(ruolo.isPeccatore()).isTrue(); }

    @Test public void testVillaggio() { assertThat(ruolo.isVillaggio()).isTrue(); }

    private void verificaStringa(String valore, String descrizione) { assertThat(valore).isEqualTo(descrizione); }

    private void verificaAuraNera(Aura aura) { assertThat(aura).isEqualTo(NERA); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}