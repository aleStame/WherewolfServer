package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestMonaco
{

    private static final String NOME = "Monaco";

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = FACTORY.getRuolo(NOME); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), NOME); }

    @Test public void testControlloMedium() { assertThat(ruolo.controlloMedium()).isEqualTo(BIANCA); }

    @Test public void testDescrizione()
    {
        String descrizione =
            "La prima notte scopre almeno due ruoli che non sono presenti nel gioco, fra quelli che sono tra i ruoli possibili per quella " +
            "partita.";
        verificaStringa(ruolo.getDescrizione(), descrizione);
    }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(1); }

    @Test public void testMistico() { verificaFalso(ruolo.isMistico()); }

    @Test public void testBardo() { verificaFalso(ruolo.isBardo()); }

    @Test public void testBecchino() { verificaFalso(ruolo.isBecchino()); }

    @Test public void testBracconiere() { verificaFalso(ruolo.isBracconiere()); }

    @Test public void testCacciatore() { verificaFalso(ruolo.isCacciatore()); }

    @Test public void testCacciatoreDiVampiri() { verificaFalso(ruolo.isCacciatoreDiVampiri()); }

    @Test public void testCappuccettoRosso() { verificaFalso(ruolo.isCappuccettoRosso()); }

    @Test public void testEremita() { verificaFalso(ruolo.isEremita()); }

    @Test public void testGuardia() { verificaFalso(ruolo.isGuardia()); }

    @Test public void testGuaritore() { verificaFalso(ruolo.isGuaritore()); }

    @Test public void testMago() { verificaFalso(ruolo.isMago()); }

    @Test public void testMonaco() { verificaVero(ruolo.isMonaco()); }

    @Test public void testMedium() { verificaFalso(ruolo.isMedium()); }

    @Test public void testNonna() { verificaFalso(ruolo.isNonna()); }

    @Test public void testOste() { verificaFalso(ruolo.isOste()); }

    @Test public void testPeccatore() { verificaFalso(ruolo.isPeccatore()); }

    @Test public void testPrete() { verificaFalso(ruolo.isPrete()); }

    @Test public void testSensitiva() { verificaFalso(ruolo.isSensitiva()); }

    @Test public void testVillaggio() { verificaVero(ruolo.isVillaggio()); }

    @Test public void testConsigliGioco()
    {
        String messaggio =
            "Sapere quali ruoli non sono in gioco può essere molto più importante di quanto non sembri a prima vista. La vostra conoscenza vi " +
            "permetterà in certe partite di poter tralasciare ragionamenti su potenziali minacce e in altre di smascherare degli impostori. Se " +
            "i ruoli che sapete non essere presenti sono Creature dell'Ombra, come il Vampiro, potrebbe risultare vincente scoprirsi da subito " +
            "per dare l'informazione al villaggio. In caso contrario, la tattica migliore è sicuramente quella di restare in attesa che " +
            "qualcuno faccia un passo falso e dica di essere un ruolo che voi sapete non essere in gioco.";
        verificaStringa(ruolo.getConsigli(), messaggio);
    }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}