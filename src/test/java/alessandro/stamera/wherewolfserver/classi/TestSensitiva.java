package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.EsitoAttacco.FALLITO;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestSensitiva
{

    private static final String NOME = "Sensitiva";

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = FACTORY.getRuolo(NOME); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), NOME); }

    @Test public void testAura() { verificaAuraBianca(ruolo.getAura()); }

    @Test public void testControlloMedium() { verificaAuraBianca(ruolo.controlloMedium()); }

    @Test public void testDescrizione()
    {
        String descrizione =
            "Se è in gioco, lo è al posto della Veggente. Ogni notte indica un giocatore (compresa sé stessa) e scopre se possiede fazione " +
            "Villaggio. I giocatori maledetti hanno fazione Maledetto solo ai fini delle condizioni di fine gioco, quindi vengono visti dalla " +
            "Sensitiva con la loro fazione originale";
        verificaStringa(ruolo.getDescrizione(), descrizione);
    }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(1); }

    @Test public void testMistico() { verificaVero(ruolo.isMistico()); }

    @Test public void testVillaggio() { verificaVero(ruolo.isVillaggio()); }

    @Test public void testAttaccoNosferatu() { assertThat(ruolo.attaccoNosferatu()).isEqualTo(FALLITO); }

    @Test public void testBardo() { verificaFalso(ruolo.isBardo()); }

    @Test public void testBecchino() { verificaFalso(ruolo.isBecchino()); }

    @Test public void testBracconiere() { verificaFalso(ruolo.isBracconiere()); }

    @Test public void testCacciatore() { verificaFalso(ruolo.isCacciatore()); }

    @Test public void testCacciatoreDiVampiri() { verificaFalso(ruolo.isCacciatoreDiVampiri()); }

    @Test public void testCappuccettoRosso() { verificaFalso(ruolo.isCappuccettoRosso()); }

    @Test public void testEremita() { verificaFalso(ruolo.isEremita()); }

    @Test public void testGuardia() { verificaFalso(ruolo.isGuardia()); }

    @Test public void testGuaritore() { verificaFalso(ruolo.isGuaritore()); }

    @Test public void testMago() { verificaFalso(ruolo.isMago()); }

    @Test public void testMedium() { verificaFalso(ruolo.isMedium()); }

    @Test public void testMonaco() { verificaFalso(ruolo.isMonaco()); }

    @Test public void testNonna() { verificaFalso(ruolo.isNonna()); }

    @Test public void testOste() { verificaFalso(ruolo.isOste()); }

    @Test public void testPeccatore() { verificaFalso(ruolo.isPeccatore()); }

    @Test public void testPrete() { verificaFalso(ruolo.isPrete()); }

    @Test public void testSensitiva() { verificaVero(ruolo.isSensitiva()); }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaAuraBianca(Aura aura) { assertThat(aura).isEqualTo(BIANCA); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}