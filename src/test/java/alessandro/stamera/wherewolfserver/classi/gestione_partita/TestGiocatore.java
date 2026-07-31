package alessandro.stamera.wherewolfserver.classi.gestione_partita;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.RuoloNullo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.ANGELO_CUSTODE_MORTO;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestGiocatore
{

    private Giocatore giocatore;

   @BeforeEach public void setUp() { giocatore = new Giocatore(RuoloNullo.getInstance()); }

    private static final int ESEMPIO_VOTI = 3;

    @Test public void testVoti()
    {
        giocatore.incrementaVoti(ESEMPIO_VOTI);
        verificaNumeroVoti(ESEMPIO_VOTI);
        giocatore.annullaVoti();
        verificaNumeroVoti(0);
        giocatore.maledizione();
        verificaNumeroVoti(1);
        verificaVero(giocatore.isMaledetto());
        assertThat(giocatore.getAura()).isEqualTo(NERA);
    }

    @Test public void testAmato()
    {
        verificaNonAmato();
        protezioneAngeloCustode();
        verificaVero(isAmato());
        giocatore.annullaProtezioneAngeloCustode();
        verificaNonAmato();
    }

    @Test public void testOratore()
    {
        cambiaRuolo("Oratore");
        verificaVero(giocatore.isOratore());
    }

    @Test public void testAngeloCustode()
    {
        cambiaRuolo("Angelo custode");
        verificaVero(giocatore.isAngeloCustode());
    }

    @ParameterizedTest @CsvSource({ "Azzeccagarbugli", "Bocca di rosa", "Borgomastro", "Mercante", "Oratore" })
    public void testCitta(String nomeRuolo)
    {
        cambiaRuolo(nomeRuolo);
        verificaVero(giocatore.isCitta());
    }

    @Test public void testContadinoMostro()
    {
        cambiaRuolo("Contadino mostro");
        verificaVero(giocatore.isContadinoMostro());
    }

    @Test public void testNosferatu()
    {
        cambiaRuolo("Nosferatu");
        verificaVero(giocatore.isNosferatu());
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia, RIUSCITO", "Angelo custode, RIUSCITO", "Assassino, RIUSCITO", "Azzeccagarbugli, RIUSCITO", "Bardo, RIUSCITO",
            "Becchino, RIUSCITO", "Bocca di rosa, RIUSCITO", "Bocca di rosa, RIUSCITO", "Boia, RIUSCITO", "Borgomastro, RIUSCITO",
            "Bracconiere, RIUSCITO", "Cacciatore, RIUSCITO", "Cacciatore di vampiri, MORTO", "Capo branco, MORTO", "Capo gilda, RIUSCITO",
            "Cappuccetto rosso, RIUSCITO", "Contadino eroe, RIUSCITO", "Contadino discendente dei lupi, RIUSCITO", "Contadino mostro, MORTO",
            "Contadino normale, RIUSCITO", "Eremita, FALLITO", "Ghoul, RIUSCITO", "Giovane lupo, MORTO", "Giulietta, RIUSCITO",
            "Giullare, RIUSCITO", "Goblin, FALLITO", "Guardia, RIUSCITO", "Guardia corrotta, RIUSCITO", "Guaritore, FALLITO",
            "Inquisitore, RIUSCITO", "Ladra, FALLITO", "Leprecauno, FALLITO", "Lupo del branco, MORTO", "Lupo reietto, MORTO",
            "Lupo solitario, MORTO", "Mago, FALLITO", "Medium, FALLITO", "Megera, FALLITO", "Mercante, RIUSCITO", "Monaco, RIUSCITO",
            "Negromante, FALLITO", "Nonna, RIUSCITO", "Oratore, RIUSCITO", "Oste, RIUSCITO", "Pazzo, RIUSCITO", "Peccatore, RIUSCITO",
            "Posseduto, TROVATO_POSSEDUTO", "Prete, RIUSCITO", "Sidhe, FALLITO", "Spia, RIUSCITO", "Strega, RIUSCITO", "Sensitiva, FALLITO",
            "Templare, RIUSCITO", "Vampiro, RIUSCITO"
        }
    )
    public void testProgenieNosferatu(String nomeRuolo, EsitoAttacco esito)
    {
        cambiaRuolo(nomeRuolo);
        assertThat(giocatore.progenizzazioneNosferatu()).isEqualTo(esito);
    }

    @ParameterizedTest @CsvSource({ "Angelo custode, RIUSCITO", "Giullare, RIUSCITO" })
    public void testAttaccoAssassino(String nomeRuolo, EsitoAttacco esito)
    {
        cambiaRuolo(nomeRuolo);
        assertThat(giocatore.attaccoAssassino()).isEqualTo(esito);
    }

    @ParameterizedTest @CsvSource({ "Angelo custode", "Giullare" })
    public void testAttaccoAssassinoVittimaAmata(String nomeRuolo)
    {
        cambiaRuolo(nomeRuolo);
        protezioneAngeloCustode();
        assertThat(giocatore.attaccoAssassino()).isEqualTo(ANGELO_CUSTODE_MORTO);
    }

    private void protezioneAngeloCustode() { giocatore.protezioneAngeloCustode(); }

    private void verificaNumeroVoti(int numeroVoti) { assertThat(giocatore.getNumeroVoti()).isEqualTo(numeroVoti); }

    private void verificaNonAmato() { assertThat(isAmato()).isFalse(); }

    private void cambiaRuolo(String nomeRuolo) { giocatore.cambiaRuolo(FACTORY.getRuolo(nomeRuolo)); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private boolean isAmato() { return giocatore.isAmato(); }

}