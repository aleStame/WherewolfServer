package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestMercante
{

    private static final String NOME = "Mercante";

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = FACTORY.getRuolo(NOME); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), NOME); }

    @Test public void testDescrizione()
    {
        String descrizione =
            "Può votare al ballottaggio anche se è accusato. In ogni votazione, non ha limite sul numero di giocatori per cui può votare. I " +
            "voti che il Mercante riceve vengono ridotti di uno per ogni altro giocatore della fazione Città in gioco.";
        verificaStringa(ruolo.getDescrizione(), descrizione);
    }

    @Test public void testControlloMedium() { verificaAuraBianca(ruolo.controlloMedium()); }

    @Test public void testAzzeccagarbugli() { verificaFalso(ruolo.isAzzeccagarbugli()); }

    @Test public void testBoccaDiRosa() { verificaFalso(ruolo.isBoccaDiRosa()); }

    @Test public void testBorgomastro() { verificaFalso(ruolo.isBorgomastro()); }

    @Test public void testMercante() { verificaVero(ruolo.isMercante()); }

    @Test public void testOratore() { verificaFalso(ruolo.isOratore()); }

    @Test public void testCitta() { verificaVero(ruolo.isCitta()); }

    @Test public void testConsigliGioco()
    {
        String messaggio =
            "Da soli, avete altrettanti voti di tutto il resto del villaggio, chiaramente non più di uno per persona. Pensate bene quante volte " +
            "votare, poiché se voterete per tutti sarà come se non aveste votato per nessuno. Il vostro obiettivo, durante i primi turni, dovrebbe " +
            "essere quello di cercare di accusare il maggior numero di giocatori possibili, per aumentare il numero di informazioni in gioco. Il " +
            "vostro potere risulta particolarmente efficace quando il numero dei giocatori diminuisce, perché un singolo voto in più può fare la " +
            "differenza. Per quanto possibile, cercate di capire se ci sono altri ruoli della Città, questo può essere utile per capire chi non " +
            "votare, ma anche per sapere quanto il vostro potere vi protegge durante le votazioni";
        verificaStringa(ruolo.getConsigli(), messaggio);
    }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaAuraBianca(Aura aura) { assertThat(aura).isEqualTo(BIANCA); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}