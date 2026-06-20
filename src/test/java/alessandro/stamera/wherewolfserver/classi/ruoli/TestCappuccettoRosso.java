package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.RIUSCITO;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestCappuccettoRosso
{

    private static final String NOME = "Cappuccetto rosso";

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = FACTORY.getRuolo(NOME); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), NOME); }

    @Test public void testAura() { verificaAuraBianca(ruolo.getAura()); }

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

    @Test public void testProtezioni() { verificaVero(isProtezioneLupiPresente()); }

    @Test public void testPerditaProtezioni()
    {
        ruolo.perdiProtezioni();
        verificaProtezioneLupiAssente();
    }

    @Test public void testMago() { verificaFalso(ruolo.isMago()); }

    @Test public void testMedium() { verificaFalso(ruolo.isMedium()); }

    @Test public void testMonaco() { verificaFalso(ruolo.isMonaco()); }

    @Test public void testNonna() { verificaFalso(ruolo.isNonna()); }

    @Test public void testOste() { verificaFalso(ruolo.isOste()); }

    @Test public void testPeccatore() { verificaFalso(ruolo.isPeccatore()); }

    @Test public void testSensitiva() { verificaFalso(ruolo.isSensitiva()); }

    @Test public void testControlloMedium() { verificaAuraBianca(ruolo.controlloMedium()); }

    @Test public void testPerditaProtezioniVampirizzazione()
    {
        assertThat(ruolo.vampirizzazione()).isEqualTo(RIUSCITO);
        verificaProtezioneLupiAssente();
    }

    private void verificaProtezioneLupiAssente() { verificaFalso(isProtezioneLupiPresente()); }

    private void verificaAuraBianca(Aura aura) { assertThat(aura).isEqualTo(BIANCA); }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

    private boolean isProtezioneLupiPresente() { return ruolo.isProtezioneLupiPresente(); }

}