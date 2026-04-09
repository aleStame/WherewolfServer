package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestOratore
{

    private static final String NOME = "Oratore";

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = FACTORY.getRuolo(NOME); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), NOME); }

    @Test public void testAura() { verificaAuraBianca(ruolo.getAura()); }

    @Test public void testDescrizione()
    {
        String descrizione =
            "Può votare al ballottaggio anche se è accusato. Se un altro giocatore con fazione Città dovesse andare al rogo, il rogo viene " +
            "annullato. Se non è accusato, può segnalare uno o più giocatori durante il ballottaggio: se uno di quei giocatori dovesse andare al " +
            "rogo, il rogo viene annullato";
        verificaStringa(ruolo.getDescrizione(), descrizione);
    }

    @Test public void testControlloMedium() { verificaAuraBianca(ruolo.controlloMedium()); }

    @Test public void testAzzeccagarbugli() { verificaFalso(ruolo.isAzzeccagarbugli()); }

    @Test public void testBoccaDiRosa() { verificaFalso(ruolo.isBoccaDiRosa()); }

    @Test public void testBorgomastro() { verificaFalso(ruolo.isBorgomastro()); }

    @Test public void testMercante() { verificaFalso(ruolo.isMercante()); }

    @Test public void testOratore() { verificaVero(ruolo.isOratore()); }

    @Test public void testCitta() { verificaVero(ruolo.isCitta()); }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaAuraBianca(Aura aura) { assertThat(aura).isEqualTo(BIANCA); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}