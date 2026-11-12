package alessandro.stamera.wherewolfserver.classi.ruoli.lupi;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Tratto;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.FALLITO;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestLupoSolitario
{

    private static final String NOME = "Lupo solitario";

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = getRuolo(NOME); }

    @Test public void testNome() { assertThat(ruolo.getNome()).isEqualTo(NOME); }

    @Test public void testFazione() { assertThat(ruolo.getFazione()).isEqualTo(Fazione.LUPO_SOLITARIO); }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(3); }

    @Test public void testLupo() { verificaVero(ruolo.isLupo()); }

    @Test public void testCapoBranco() { verificaFalso(ruolo.isCapoBranco()); }

    @Test public void testLupoBranco() { verificaFalso(ruolo.isLupoBranco()); }

    @Test public void testGiovaneLupo() { verificaFalso(ruolo.isGiovaneLupo()); }

    @Test public void testLupoReietto() { verificaFalso(ruolo.isLupoReietto()); }

    @Test public void testLupoSolitario() { verificaVero(ruolo.isLupoSolitario()); }

    @ParameterizedTest @CsvSource({ "CREATURA_OMBRA, LUPO_MANNARO" })
    public void testTratto(Tratto tratto) { verificaVero(ruolo.isTrattoPresente(tratto)); }

    @ParameterizedTest @CsvSource({ "Capo branco, Lupo del branco, Lupo reietto, Contadino discendente dei lupi" })
    public void testAttaccoLupi(String nome) { assertThat(ruolo.attaccoLupi(getRuolo(nome))).isEqualTo(FALLITO); }

    @Test public void testConsigliGioco()
    {
        String messaggio =
            "La partita, da Lupo Solitario, sarà estremamente interessante. Da una parte, avete tutte le informazioni per fare in modo che i " +
            "Lupi del Branco vengano debellati in poco tempo. Dall’altra, dovete capire come far arrivare queste informazioni al villaggio senza " +
            "destare sospetti a vostra volta. Come dice il vostro nome, non avrete alleati, se non, eventualmente, il Traditore, che tuttavia " +
            "non conoscerà la vostra identità. Ricordatevi che voi sapete tutto dei Lupi del Branco, ma loro non sanno nulla di voi, se non " +
            "quello che farete loro credere. La vittoria del Lupo Solitario è ardua da raggiungere, ma proprio per questo, una di quelle che può " +
            "dare più soddisfazioni";
        assertThat(ruolo.getConsigli()).isEqualTo(messaggio);
    }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

    private Ruolo getRuolo(String nome) { return FACTORY.getRuolo(nome); }

}