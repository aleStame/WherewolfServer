package alessandro.stamera.wherewolfserver.classi.ruoli.lupi;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Tratto;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.FALLITO;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestLupoReietto
{

    private static final String NOME = "Lupo reietto";

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = getRuolo(NOME); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), NOME); }

    @Test public void testDescrizione()
    {
        String descrizione =
            "La prima notte individua il Traditore e la Megera e riconosce i lupi del branco. Dalla prima notte, se è il lupo più potente in " +
            "gioco, durante il turno dei lupi, può indicare un giocatore che viene ucciso. É protetto dal Capo branco e perde la partita se " +
            "questi alla fine è ancora in gioco.";
        verificaStringa(ruolo.getDescrizione(), descrizione);
    }

    @Test public void testLupo() { verificaVero(ruolo.isLupo()); }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(3); }

    @Test public void testCapoBranco() { verificaFalso(ruolo.isCapoBranco()); }

    @Test public void testLupoBranco() { verificaFalso(ruolo.isLupoBranco()); }

    @Test public void testGiovaneLupo() { verificaFalso(ruolo.isGiovaneLupo()); }

    @Test public void testLupoReietto() { verificaVero(ruolo.isLupoReietto()); }

    @ParameterizedTest @CsvSource({ "CREATURA_OMBRA, LUPO_MANNARO" })
    public void testTratto(Tratto tratto) { verificaVero(ruolo.isTrattoPresente(tratto)); }

    @ParameterizedTest @CsvSource({ "Capo branco, Lupo del branco, Lupo reietto, Contadino discendente dei lupi" })
    public void testAttaccoLupi(String nome) { assertThat(ruolo.attaccoLupi(getRuolo(nome))).isEqualTo(FALLITO); }

    @Test public void testConsigliGioco()
    {
        String messaggio =
            "Il vostro è un obiettivo difficile. Dovrete evitare il rogo e guadagnarvi la fiducia del villaggio e, allo stesso tempo, fare in " +
            "modo che il Capo Branco sia eliminato prima della fine della partita. Per fare questo dovrete ricorrere ad una strategia indiretta, " +
            "magari appoggiandolo solo per tradirlo quando meno se lo aspetta, la vostra sopravvivenza non è indispensabile per la vostra " +
            "vittoria. Ricordate anche che questa è una vostra sfida personale, gli altri Lupi del Branco non avranno alcun interesse nel suo " +
            "esito. Vincere la sfida contro il villaggio e quella contro il Capo Branco potrebbe rivelarsi arduo, ma di certo molto soddisfacente " +
            "qualora vi riusciste.";
        verificaStringa(ruolo.getConsigli(), messaggio);
    }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

    private Ruolo getRuolo(String nome) { return FACTORY.getRuolo(nome); }

}