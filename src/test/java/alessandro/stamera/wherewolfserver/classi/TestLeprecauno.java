package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
import static alessandro.stamera.wherewolfserver.classi.Tratto.PROTETTO;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestLeprecauno
{

    private static final String NOME = "Leprecauno";

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = FACTORY.getRuolo(NOME); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), NOME); }

    @Test public void testAura() { verificaAuraBianca(ruolo.getAura()); }

    @Test public void testDescrizione()
    {
        String descrizione = "La prima notte riconosce le altre creature del Piccolo Popolo. Inoltre, è protetto da tutti i mistici";
        verificaStringa(ruolo.getDescrizione(), descrizione);
    }

    @Test public void testControlloMedium() { verificaAuraBianca(ruolo.controlloMedium()); }

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

    @Test public void testPiccoloPopolo() { verificaVero(ruolo.isPiccoloPopolo()); }

    @Test public void testGoblin() { verificaFalso(ruolo.isGoblin()); }

    @Test public void testLeprecauno() { verificaVero(ruolo.isLeprecauno()); }

    @Test public void testSidhe() { verificaFalso(ruolo.isSidhe()); }

    private void verificaAuraBianca(Aura aura) { assertThat(aura).isEqualTo(BIANCA); }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}