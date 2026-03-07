package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.LUPO_BRANCO;
import static alessandro.stamera.wherewolfserver.classi.Fazione.LUPO_SOLITARIO;
import static alessandro.stamera.wherewolfserver.classi.Fazione.values;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestCappuccettoRosso
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new CappuccettoRosso(); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), "Cappuccetto rosso"); }

    @Test public void testAura() { assertThat(ruolo.getAura()).isEqualTo(BIANCA); }

    @Test public void testDescrizione()
    {
        String descrizione =
            "Finché la Nonna è in gioco (anche se essa riceve il tratto Non morto o diventa il Posseduto) e non si è trasformata in Lupo, " +
            "Cappuccetto rosso è protetta dall'attacco dei Lupi. Se l'ultimo Lupo in gioco (sia esso l'ultimo Lupo del Branco o il Lupo " +
            "solitario) attacca Cappuccetto rosso, quest'ultima apre gli occhi e lo riconosce, anche se fosse Romeo o protetta dalla Strega";
        verificaStringa(ruolo.getDescrizione(), descrizione);
    }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(1); }

    @Test public void testMistico() { verificaFalso(ruolo.isMistico()); }

    @Test public void testVillaggio() { verificaVero(ruolo.isVillaggio()); }

    @Test public void testBardo() { verificaFalso(ruolo.isBardo()); }

    @Test public void testBecchino() { verificaFalso(ruolo.isBecchino()); }

    @Test public void testBracconiere() { verificaFalso(ruolo.isBracconiere()); }

    @Test public void testCacciatore() { verificaFalso(ruolo.isCacciatore()); }

    @Test public void testCacciatoreDiVampiri() { verificaFalso(ruolo.isCacciatoreDiVampiri()); }

    @Test public void testCappuccettoRosso() { verificaVero(ruolo.isCappuccettoRosso()); }

    @Test public void testContadino() { verificaFalso(ruolo.isContadino()); }

    @Test public void testGuardia() { verificaFalso(ruolo.isGuardia()); }

    @Test public void testGuaritore() { verificaFalso(ruolo.isGuaritore()); }

    @Test public void testProtezioni()
    {
        for(Fazione fazione : new Fazione[]{ LUPO_BRANCO, LUPO_SOLITARIO }) verificaVero(isProtetto(fazione));
    }

    @Test public void testPerditaProtezioni()
    {
        ruolo.perdiProtezioni();
        for(Fazione fazione : values()) verificaFalso(isProtetto(fazione));
    }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

    private boolean isProtetto(Fazione fazione) { return ruolo.isProtetto(fazione); }

}