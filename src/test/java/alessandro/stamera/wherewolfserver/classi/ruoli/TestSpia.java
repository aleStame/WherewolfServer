package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestSpia
{

    private static final String NOME = "Spia";

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = FACTORY.getRuolo(NOME); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), NOME); }

    @Test public void testControlloMedium() { verificaAuraBianca(ruolo.controlloMedium()); }

    @Test public void testDescrizione()
    {
        String descrizione =
            "La prima notte riconosce gli altri criminali. Può tenere gli occhi aperti durante le votazioni per le accuse. Se lo fa, in quella" +
            " votazione può votare per sé stesso";
        verificaStringa(ruolo.getDescrizione(), descrizione);
    }

    @Test public void testCriminale() { verificaVero(ruolo.isCriminale()); }

    @Test public void testAssassino() { verificaFalso(ruolo.isAssassino()); }

    @Test public void testCapoGilda() { verificaFalso(ruolo.isCapoGilda()); }

    @Test public void testLadra() { verificaFalso(ruolo.isLadra()); }

    @Test public void testSpia() { verificaVero(ruolo.isSpia()); }

    @Test public void testConsigliGioco()
    {
        String messaggio =
            "Le informazioni in vostro possesso saranno veramente tantissime. Dal modo in cui vota un giocatore si può capire davvero molto su " +
            "di lui, inoltre conoscere immediatamente l'identità del Borgomastro, del Mercante, dell'Azzeccagarbugli non appena useranno i loro " +
            "poteri. Il vostro scopo è quello di usare al meglio queste informazioni senza rivelare la vostra identità. Siete uno dei Criminali " +
            "con aura bianca, cercate di sfruttare al massimo questa caratteristica e di far sapere ai vostri alleati quello che avete capito " +
            "senza dare troppo nell'occhio.";
        verificaStringa(ruolo.getConsigli(), messaggio);
    }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaAuraBianca(Aura aura) { assertThat(aura).isEqualTo(BIANCA); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}