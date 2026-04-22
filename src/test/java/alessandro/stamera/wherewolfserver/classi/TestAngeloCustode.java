package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.EsitoPartita.VITTORIA;
import static alessandro.stamera.wherewolfserver.classi.EsitoPartita.SCONFITTA;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
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

    @Test public void testVittoriaRomeo()
    {
        ruolo.romeizzazione();
        Partita partita = getPartitaEsempio();
        when(partita.isFinita()).thenReturn(true);
        when(partita.isGiuliettaViva()).thenReturn(true);
        verificaEsitoPartita(partita, VITTORIA);
        ruolo.resettaRomeo();
    }

    @Test public void testSconfittaViaggio()
    {
        Partita partita = getPartitaEsempio();
        when(partita.isViaggioPartito()).thenReturn(true);
        when(partita.isViaggiatoreAmato()).thenReturn(true);
        verificaEsitoPartita(partita, SCONFITTA);
    }

    private void verificaEsitoPartita(Partita partita, EsitoPartita esito)
    {
        assertThat(ruolo.getEsitoPartita(partita)).isEqualTo(esito);
    }

    private Partita getPartitaEsempio() { return mock(Partita.class); }

    private void verificaStringa(String valore, String soluzione) { assertThat(valore).isEqualTo(soluzione); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}