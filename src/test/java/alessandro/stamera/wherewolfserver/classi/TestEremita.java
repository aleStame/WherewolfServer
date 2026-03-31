package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestEremita
{

    private static final int ESEMPIO_VOTI = 2;

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = FACTORY.getRuolo("Eremita"); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), "Eremita"); }

    @Test public void testAura() { verificaAuraBianca(ruolo.getAura()); }

    @Test
    public void testDescrizione() { verificaStringa(ruolo.getDescrizione(), "È protetto dalle creature dell'ombra"); }

    @Test public void testLune() { verificaNumeroIntero(ruolo.getLune(), 1); }

    @Test public void testMistico() { verificaFalso(ruolo.isMistico()); }

    @Test public void testBardo() { verificaFalso(ruolo.isBardo()); }

    @Test public void testBecchino() { verificaFalso(ruolo.isBecchino()); }

    @Test public void testBracconiere() { verificaFalso(ruolo.isBracconiere()); }

    @Test public void testCacciatore() { verificaFalso(ruolo.isCacciatore()); }

    @Test public void testCacciatoreDiVampiri() { verificaFalso(ruolo.isCacciatoreDiVampiri()); }

    @Test public void testCappuccettoRosso() { verificaFalso(ruolo.isCappuccettoRosso()); }

    @Test public void testEremita() { verificaVero(ruolo.isEremita()); }

    @Test public void testGuardia() { verificaFalso(ruolo.isGuardia()); }

    @Test public void testGuaritore() { verificaFalso(ruolo.isGuaritore()); }

    @Test public void testMago() { verificaFalso(ruolo.isMago()); }

    @Test public void testMonaco() { verificaFalso(ruolo.isMonaco()); }

    @Test public void testVillaggio() { verificaVero(ruolo.isVillaggio()); }

    @Test public void testProtezioni()
    {
        ruolo.aggiungiProtezioneCreatureOmbra();
        verificaVero(ruolo.isProtezioneLupiPresente());
        verificaVero(ruolo.isProtezioneNegromantePresente());
    }

    @Test public void testControlloMedium() { verificaAuraBianca(ruolo.controlloMedium()); }

    @Test public void testVoti()
    {
        ruolo.incrementaVoti(ESEMPIO_VOTI);
        verificaNumeroVoti();
        verificaNonMaledetto();
        ruolo.maledizione();
        verificaNonMaledetto();
        verificaNumeroVoti();
        verificaAuraBianca(ruolo.getAura());
    }

    private void verificaAuraBianca(Aura aura) { assertThat(aura).isEqualTo(BIANCA); }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaNumeroVoti() { verificaNumeroIntero(ruolo.getNumeroVoti(), ESEMPIO_VOTI); }

    private void verificaNumeroIntero(int valore, int risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaNonMaledetto() { verificaFalso(ruolo.isMaledetto()); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}