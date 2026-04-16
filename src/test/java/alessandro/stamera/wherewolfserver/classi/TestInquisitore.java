package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestInquisitore
{

    private static final String NOME = "Inquisitore";

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = FACTORY.getRuolo(NOME); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), NOME); }

    @Test public void testDescrizione()
    {
        String descrizione =
            "La prima notte individua il Boia e il Templare e scopre quanti mistici sono in gioco. Se viene indicato da un mistico, lo " +
            "riconosce. Può segnalare un giocatore durante le accuse: se è un mistico, sarà accusato a prescindere dai voti ricevuti.";
        verificaStringa(ruolo.getDescrizione(), descrizione);
    }

    @Test public void testAura() { verificaAuraBianca(ruolo.getAura()); }

    @Test public void testInquisizione() { verificaVero(ruolo.isInquisizione()); }

    @Test public void testBoia() { verificaFalso(ruolo.isBoia()); }

    @Test public void testInquisitore() { verificaVero(ruolo.isInquisitore()); }

    @Test public void testTemplare() { verificaFalso(ruolo.isTemplare()); }

    @Test public void testControlloMedium() { verificaAuraBianca(ruolo.controlloMedium()); }

    private void verificaAuraBianca(Aura aura) { assertThat(aura).isEqualTo(BIANCA); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

}