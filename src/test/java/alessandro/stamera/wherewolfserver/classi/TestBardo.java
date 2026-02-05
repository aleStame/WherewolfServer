package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.VILLAGGIO;
import static alessandro.stamera.wherewolfserver.classi.Fazione.CRIMINALI;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestBardo
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new Bardo(); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), "Bardo"); }

    @Test public void testDescrizione()
    {
        verificaStringa
        (
            ruolo.getDescrizione(),
    "Ogni mattina, se la Veggente quella notte ha scoperto un'aura bianca, il Moderatore la comunica pubblicamente."
        );
    }

    @Test public void testFazione() { verificaFazione(VILLAGGIO); }

    @Test public void testBianca() { assertThat(ruolo.getAura()).isEqualTo(BIANCA); }

    @Test public void testLune() { verificaIntero(ruolo.getLune(), 1); }

    @Test public void testMistico() { verificaFalso(ruolo.isMistico()); }

    @Test public void testAngeloCustode() { verificaFalso(ruolo.isAngeloCustode()); }

    @Test public void testBecchino() { verificaFalso(ruolo.isBecchino()); }

    @Test public void testBoccaDiRosa() { verificaFalso(ruolo.isBoccaDiRosa()); }

    @Test public void testCapoGilda() { verificaFalso(ruolo.isCapoGilda()); }

    @Test public void testAzzeccagarbugli() { verificaFalso(ruolo.isCapoGilda()); }

    @Test public void testBardo() { verificaVero(ruolo.isBardo()); }

    @Test public void testAssassino() { verificaFalso(ruolo.isAssassino()); }

    @Test public void testSegnalazioneAzzeccagarbugli()
    {
        int voti = 3;
        for(int i = 0; i < voti; i++) ruolo.incrementaVoti();
        ruolo.segnalazioneAzzeccagarbugli();
        verificaIntero(ruolo.getNumeroVoti(), voti);
        verificaVero(ruolo.isAccusato());
    }

    @Test public void testGildata()
    {
        verificaVero(ruolo.gildata());
        verificaFazione(CRIMINALI);
    }

    @Test public void testLupo() { verificaFalso(ruolo.isLupo()); }

    @Test public void testContadino() { verificaFalso(ruolo.isContadino()); }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaFazione(Fazione risultato) { assertThat(ruolo.getFazione()).isEqualTo(risultato); }

    private void verificaIntero(int valore, int risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

}