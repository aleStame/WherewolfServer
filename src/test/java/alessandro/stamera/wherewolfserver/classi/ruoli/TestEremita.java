package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.EREMITA_BECCATO;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestEremita
{

    private static final String NOME = "Eremita";

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = getRuolo(NOME); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), NOME); }

    @Test public void testDescrizione()
    {
        verificaStringa(ruolo.getDescrizione(), "È protetto dalle creature dell'ombra");
    }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(1); }

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

    @Test public void testNonna() { verificaFalso(ruolo.isNonna()); }

    @Test public void testOste() { verificaFalso(ruolo.isOste()); }

    @Test public void testPeccatore() { verificaFalso(ruolo.isPeccatore()); }

    @Test public void testPrete() { verificaFalso(ruolo.isPrete()); }

    @Test public void testSensitiva() { verificaFalso(ruolo.isSensitiva()); }

    @Test public void testVillaggio() { verificaVero(ruolo.isVillaggio()); }

    @Test public void testControlloMedium() { verificaAuraBianca(ruolo.controlloMedium()); }

    @ParameterizedTest
    @CsvSource({ "Capo branco", "Lupo del branco", "Lupo reietto", "Lupo solitario", "Contadino discendente dei lupi" })
    public void testAttaccoLupi(String nome) { verificaAttacco(ruolo.attaccoLupi(getRuolo(nome))); }

    @Test public void testNegromanteEremita() { verificaAttacco(ruolo.attaccoNegromante()); }

    @Test public void testVampirizzazione() { verificaAttacco(ruolo.vampirizzazione()); }

    @Test public void testProgenizzazioneNosferatu() { verificaAttacco(ruolo.attaccoNosferatu());}

    @Test public void testConsigliGioco()
    {
        String messaggio =
            "Essendo immuni all'attacco di tutte le Creature dell'Ombra, dovete cercare di guadagnarvi la fiducia del Villaggio, poichè l'unica " +
            "possibilità per voi di essere eliminati è quell di finire al rogo. Potrebbe non essere facile, poichè è vero che voi non morirete " +
            "mai di notte, ma questo è vero anche per i Lupi Mannari, e questo potrebbe risultare sospetto col tempo. Una strategia efficace " +
            "potrebbe essere anche quella di fingervi un ruolo importante per farvi attaccare dai Lupi Mannari o dal Vampiro e togliergli una " +
            "notte di azione. L'unico inconveniente di questa strategia è che, se quel ruolo dovesse essere in gioco, il giocatore potrebbe " +
            "uscire allo scoperto per cercare di smascherarvi facendo crollare il vostro piano e mettendo in pericolo lui stesso.";
        verificaStringa(ruolo.getConsigli(), messaggio);
    }

    private void verificaAttacco(EsitoAttacco esito) { assertThat(esito).isEqualTo(EREMITA_BECCATO); }

    private void verificaAuraBianca(Aura aura) { assertThat(aura).isEqualTo(BIANCA); }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

    private Ruolo getRuolo(String nome) { return FACTORY.getRuolo(nome); }

}