package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.SCONFITTA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.VITTORIA;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class TestAngeloCustode
{

    private static final String NOME = "Angelo custode";

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = FACTORY.getRuolo(NOME); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), NOME); }

    @Test public void testDescrizione()
    {
        String soluzione =
            "La prima notte indica un giocatore, l'Amato, che viene avvisato. Se quel giocatore dovesse essere accusato, l'Angelo custode sarà " +
            "accusato al suo posto. Se dovesse essere attaccato e ucciso durante la notte, sarà invece attaccato, avvisato e ucciso l'Angelo " +
            "custode.";
        verificaStringa(ruolo.getDescrizione(), soluzione);
    }

    @Test public void testAngeloCustode() { assertThat(ruolo.isAngeloCustode()).isTrue(); }

    @Test public void testAmato()
    {
        ruolo.sceltaAngeloCustode();
        verificaFalso(ruolo.isAmato());
    }

    @Test public void testGiulietta() { verificaFalso(ruolo.isGiulietta()); }

    @Test public void testVittoriaAmatoVivo()
    {
        String nome = "Ezio";
        Partita partita = new Partita(new String[][] { { "Cesare", "Angelo custode" }, { nome, "Peccatore" } });
        partita.segnalazioneAngeloCustode(nome);
        assertThat(ruolo.getEsitoPartita(partita)).isEqualTo(VITTORIA);
        partita.ripristinaGiocatoreVivo(nome);
    }

    @Test public void testSconfittaNienteAmato()
    {
        Partita partita = new Partita(new String[][] { { "Antonio", "Boia" }, { "Sebastiano", "Templare" } });
        assertThat(ruolo.getEsitoPartita(partita)).isEqualTo(SCONFITTA);
    }

    @Test public void testSconfittaViaggio()
    {
        Partita partita = mock(Partita.class);
        when(partita.isViaggioPartito()).thenReturn(true);
        when(partita.isViaggiatoreAmato()).thenReturn(true);
        assertThat(ruolo.getEsitoPartita(partita));
    }

    private void verificaStringa(String valore, String soluzione) { assertThat(valore).isEqualTo(soluzione); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}