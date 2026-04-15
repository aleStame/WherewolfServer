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

    @Test public void testSegnalazioneInquisitore()
    {
        verificaFalso(isAccusato());
        ruolo.segnalazioneInquisitore();
        verificaVero(isAccusato());
    }

    @Test public void testBardo() { verificaFalso(ruolo.isBardo()); }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaAuraBianca(Aura aura) { assertThat(aura).isEqualTo(BIANCA); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private boolean isAccusato() { return ruolo.isAccusato(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}