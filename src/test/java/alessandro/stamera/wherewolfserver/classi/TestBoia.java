package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestBoia
{

    private static final String NOME = "Boia";

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = FACTORY.getRuolo(NOME); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), NOME); }

    @Test public void testAura() { verificaAuraNera(ruolo.getAura()); }

    @Test public void testDescrizione()
    {
        String descrizione =
            "La prima notte viene individuato dall'Inquisitore. Se non è accusato, può segnalare un giocatore durante il ballottaggio: se è un " +
            "mistico o una creatura dell'ombra, i voti di tutti gli altri accusati vengono azzerati alla fine della votazione";
        verificaStringa(ruolo.getDescrizione(), descrizione);
    }

    @Test public void testBoia() { verificaVero(ruolo.isBoia()); }

    @Test public void testInquisitore() { verificaFalso(ruolo.isInquisitore()); }

    @Test public void testTemplare() { verificaFalso(ruolo.isTemplare()); }

    @Test public void testInquisizione() { verificaVero(ruolo.isInquisizione()); }

    @Test public void testControlloMedium() { verificaAuraNera(ruolo.controlloMedium()); }

    private void verificaAuraNera(Aura aura) { assertThat(aura).isEqualTo(NERA); }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}