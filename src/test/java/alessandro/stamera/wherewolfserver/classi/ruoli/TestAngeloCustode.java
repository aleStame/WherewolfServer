package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita;
import alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;
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

    @Test public void testAngeloCustode() { verificaVero(ruolo.isAngeloCustode()); }

    @Test public void testAmato()
    {
        ruolo.sceltaAngeloCustode();
        verificaFalso(ruolo.isAmato());
    }

    @Test public void testGiulietta() { verificaFalso(ruolo.isGiulietta()); }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Assassino", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro", "Bracconiere",
            "Cacciatore", "Cacciatore di vampiri", "Capo branco", "Capo gilda", "Cappuccetto rosso", "Contadino eroe",
            "Contadino discendente dei lupi", "Contadino mostro", "Contadino normale", "Eremita", "Ghoul", "Giovane lupo", "Giulietta", "Giullare",
            "Goblin", "Guardia", "Guardia corrotta", "Guaritore", "Inquisitore", "Ladra", "Leprecauno", "Lupo del branco", "Lupo reietto",
            "Lupo solitario", "Mago", "Medium", "Megera", "Mercante", "Monaco", "Negromante", "Nonna", "Nosferatu", "Oratore", "Oste", "Pazzo",
            "Peccatore", "Posseduto", "Prete", "Sidhe", "Spia", "Sensitiva", "Templare", "Vampiro"
        }
    )
    public void testVittoria(String nomeRuolo)
    {
        String nome = "Ezio";
        Partita partita = new Partita(new String[][] { { "Cesare", "Angelo custode" }, { nome, nomeRuolo } });
        partita.segnalazioneAngeloCustode(nome);
        verificaEsitoPartita(partita, VITTORIA);
        partita.ripristinaGiocatoreVivo(nome);
    }

    @ParameterizedTest @MethodSource("getEsempiPartitaPersa")
    public void testSconfittaNienteAmato(Partita partita) { verificaEsitoPartita(partita, SCONFITTA); }

    @Test public void testRomeizzazione()
    {
        ruolo.romeizzazione();
        assertThat(ruolo.getAura()).isEqualTo(BIANCA);
        verificaVero(ruolo.isRomeo());
        ruolo.ripristina();
    }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaEsitoPartita(Partita partita, EsitoPartita esito)
    {
        assertThat(ruolo.getEsitoPartita(partita)).isEqualTo(esito);
    }

    private static Stream<Arguments> getEsempiPartitaPersa()
    {
        return Stream.of(Arguments.of(getEsempioPartitaSenzaAmato()), Arguments.of(getEsempioPartitaViaggio()));
    }

    private static Partita getEsempioPartitaSenzaAmato()
    {
        return new Partita(new String[][] { { "Antonio", "Boia" }, { "Sebastiano", "Templare" } });
    }

    private static Partita getEsempioPartitaViaggio()
    {
        Partita partita = mock(Partita.class);
        when(partita.isViaggioPartito()).thenReturn(true);
        when(partita.isViaggiatoreAmato()).thenReturn(true);
        return partita;
    }

    private void verificaStringa(String valore, String soluzione) { assertThat(valore).isEqualTo(soluzione); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}