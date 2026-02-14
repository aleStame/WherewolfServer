package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Categoria.UOMINI;
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

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(1); }

    @Test public void testMistico() { verificaFalso(ruolo.isMistico()); }

    @Test public void testAngeloCustode() { verificaFalso(ruolo.isAngeloCustode()); }

    @Test public void testBecchino() { verificaFalso(ruolo.isBecchino()); }

    @Test public void testCapoGilda() { verificaFalso(ruolo.isCapoGilda()); }

    @Test public void testBardo() { verificaVero(ruolo.isBardo()); }

     @Test public void testGildata()
    {
        verificaVero(ruolo.gildata());
        verificaFazione(CRIMINALI);
    }

    @Test public void testLupo() { verificaFalso(ruolo.isLupo()); }

    @Test public void testContadino() { verificaFalso(ruolo.isContadino()); }

    @Test public void testCategoria() { assertThat(ruolo.getCategoria()).isEqualTo(UOMINI); }

    @Test public void testCriminale() { verificaFalso(ruolo.isCriminale()); }

    @Test public void testBoia() { verificaFalso(ruolo.isBoia()); }

    @Test public void isCitta() { verificaFalso(ruolo.isCitta()); }

    @Test public void testBracconiere() { verificaFalso(ruolo.isBracconiere()); }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaFazione(Fazione risultato) { assertThat(ruolo.getFazione()).isEqualTo(risultato); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

}