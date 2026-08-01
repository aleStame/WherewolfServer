package alessandro.stamera.wherewolfserver.classi.gestione_partita;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.RuoloNullo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.*;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.CRIMINALI;
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
            "Posseduto, TROVATO_POSSEDUTO", "Prete, RIUSCITO", "Sidhe, FALLITO", "Spia, RIUSCITO", "Strega, FALLITO", "Sensitiva, FALLITO",
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
        verificaAttaccoAssassino(esito);
    }

    @ParameterizedTest @CsvSource({ "Angelo custode", "Giullare" })
    public void testAttaccoAssassinoVittimaAmata(String nomeRuolo)
    {
        cambiaRuolo(nomeRuolo);
        protezioneAngeloCustode();
        verificaAttaccoAssassino(ANGELO_CUSTODE_MORTO);
    }

    @ParameterizedTest
    @CsvSource({ "Goblin", "Guaritore", "Leprecauno", "Mago", "Medium", "Megera", "Sensitiva", "Sidhe", "Strega" })
    public void testSegnalazioneInquisitore(String nomeRuolo)
    {
        cambiaRuolo(nomeRuolo);
        verificaNonInquisito();
        giocatore.segnalazioneInquisitore();
        verificaVero(isInquisito());
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Angelo custode", "Assassino", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro",
            "Bracconiere", "Cacciatore", "Cacciatore di vampiri", "Capo branco", "Capo gilda", "Cappuccetto rosso", "Contadino eroe",
            "Contadino discendente dei lupi", "Contadino mostro", "Contadino normale", "Eremita", "Ghoul", "Giovane lupo", "Giulietta", "Giullare",
            "Guardia", "Guardia corrotta", "Inquisitore", "Ladra", "Lupo del branco", "Lupo reietto", "Lupo solitario", "Mercante", "Monaco",
            "Nonna", "Nosferatu", "Oratore", "Oste", "Pazzo", "Peccatore", "Posseduto", "Prete", "Spia", "Templare", "Vampiro"
        }
    )
    public void testSegnalazioneInquisitoreNonRiuscita(String nomeRuolo)
    {
        cambiaRuolo(nomeRuolo);
        verificaNonInquisito();
        giocatore.segnalazioneInquisitore();
        verificaNonInquisito();
    }

    @ParameterizedTest @CsvSource({ "Becchino" }) public void testCriminalizzazioneBecchino(String nomeRuolo)
    {
        cambiaRuolo(nomeRuolo);
        verificaAttacco(giocatore.criminalizzazione(), RIUSCITO);
        assertThat(giocatore.getFazione()).isEqualTo(CRIMINALI);
        verificaVero(giocatore.isCriminale());
    }

    private void verificaNonInquisito() { assertThat(isInquisito()).isFalse(); }

    private boolean isInquisito() { return giocatore.isInquisito(); }

    private void verificaAttaccoAssassino(EsitoAttacco esito) { verificaAttacco(giocatore.attaccoAssassino(), esito); }

    private void verificaAttacco(EsitoAttacco valore, EsitoAttacco risultato) { assertThat(valore).isEqualTo(risultato); }

    private void protezioneAngeloCustode() { giocatore.protezioneAngeloCustode(); }

    private void verificaNumeroVoti(int numeroVoti) { assertThat(giocatore.getNumeroVoti()).isEqualTo(numeroVoti); }

    private void verificaNonAmato() { assertThat(isAmato()).isFalse(); }

    private void cambiaRuolo(String nomeRuolo) { giocatore.cambiaRuolo(FACTORY.getRuolo(nomeRuolo)); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private boolean isAmato() { return giocatore.isAmato(); }

}