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
        incrementaVoti();
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
        segnalazioneInquisitore();
        verificaAccusabile();
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
        segnalazioneInquisitore();
        verificaNonAccusabile();
    }

    @ParameterizedTest @CsvSource({ "Becchino" }) public void testCriminalizzazioneBecchino(String nomeRuolo)
    {
        cambiaRuolo(nomeRuolo);
        verificaAttacco(giocatore.criminalizzazione(), RIUSCITO);
        assertThat(giocatore.getFazione()).isEqualTo(CRIMINALI);
        verificaVero(giocatore.isCriminale());
    }

    @ParameterizedTest @CsvSource({ "Capo branco", "Giovane lupo", "Lupo del branco", "Lupo reietto", "Lupo solitario" })
    public void testLupo(String tipoLupo)
    {
        cambiaRuolo(tipoLupo);
        verificaVero(isLupo());
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Angelo custode", "Assassino", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Bracconiere",
            "Borgomastro", "Cacciatore", "Cacciatore di vampiri", "Capo gilda", "Cappuccetto rosso", "Contadino eroe",
            "Contadino discendente dei lupi", "Contadino mostro", "Contadino normale", "Eremita", "Ghoul", "Giulietta", "Giullare", "Goblin",
            "Guardia", "Guardia corrotta", "Guaritore", "Inquisitore", "Ladra", "Leprecauno", "Mago", "Medium", "Megera", "Mercante", "Monaco",
            "Negromante", "Nonna", "Nosferatu", "Oratore", "Oste", "Pazzo", "Peccatore", "Posseduto", "Prete", "Sidhe", "Spia", "Strega",
            "Sensitiva", "Templare", "Vampiro"
        }
    )
    public void testNonLupo(String nomeLupo)
    {
        cambiaRuolo(nomeLupo);
        verificaFalso(isLupo());
    }

    @Test public void testBracconiere()
    {
        cambiaRuolo("Bracconiere");
        verificaVero(isBracconiere());
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Angelo custode", "Assassino", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro",
            "Cacciatore", "Cacciatore di vampiri", "Capo branco", "Capo gilda", "Cappuccetto rosso", "Contadino eroe",
            "Contadino discendente dei lupi", "Contadino mostro", "Contadino normale", "Eremita", "Ghoul", "Giovane lupo", "Giulietta", "Giullare",
            "Goblin", "Guardia", "Guardia corrotta", "Guaritore", "Inquisitore", "Ladra", "Leprecauno", "Lupo del branco", "Lupo reietto",
            "Lupo solitario", "Mago", "Medium", "Megera", "Mercante", "Monaco", "Negromante", "Nonna", "Nosferatu", "Oratore", "Oste", "Pazzo",
            "Peccatore", "Posseduto", "Prete", "Sidhe", "Spia", "Strega", "Sensitiva", "Templare", "Vampiro"
        }
    )
    public void testNonBracconiere(String nomeLupo)
    {
        cambiaRuolo(nomeLupo);
        verificaFalso(isBracconiere());
    }

    @ParameterizedTest
    @CsvSource ( { "Assassino", "Bocca di rosa", "Borgomastro", "Capo gilda", "Guardia corrotta", "Ladra", "Oratore", "Spia" } )
    public void testAnnullamentoVotiDopoSegnalazioneAzzeccagarbugli(String nomeRuolo)
    {
        cambiaRuolo(nomeRuolo);
        incrementaVoti();
        verificaNumeroVoti(ESEMPIO_VOTI);
        segnalazioneAzzeccagarbugli();
        assertThat(giocatore.getNumeroVoti()).isZero();
        verificaNonAccusabile();
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Angelo custode", "Bardo", "Becchino", "Boia", "Bracconiere", "Cacciatore", "Cacciatore di vampiri", "Capo branco",
            "Cappuccetto rosso", "Contadino eroe", "Contadino discendente dei lupi", "Contadino mostro", "Contadino normale", "Eremita", "Ghoul",
            "Giovane lupo", "Giulietta", "Giullare", "Goblin", "Guardia", "Guaritore", "Inquisitore", "Leprecauno", "Lupo del branco",
            "Lupo reietto", "Lupo solitario", "Mago", "Medium", "Megera", "Monaco", "Negromante", "Nonna", "Nosferatu", "Oste", "Pazzo",
            "Peccatore", "Posseduto", "Prete", "Sidhe", "Strega", "Sensitiva", "Templare", "Vampiro"
        }
    )
    public void testAccusaDopoSegnalazioneAzzeccagarbugli(String nomeRuolo)
    {
        cambiaRuolo(nomeRuolo);
        incrementaVoti();
        verificaNumeroVoti(ESEMPIO_VOTI);
        segnalazioneAzzeccagarbugli();
        verificaNumeroVoti(ESEMPIO_VOTI);
        verificaAccusabile();
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Angelo custode", "Assassino", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro",
            "Bracconiere", "Cacciatore di vampiri", "Capo branco", "Capo gilda", "Cappuccetto rosso", "Contadino eroe",
            "Contadino discendente dei lupi", "Contadino mostro", "Contadino normale", "Eremita", "Ghoul", "Giovane lupo", "Giulietta", "Giullare",
            "Goblin", "Guardia", "Guardia corrotta", "Guaritore", "Inquisitore", "Ladra", "Leprecauno", "Lupo del branco", "Lupo reietto",
            "Lupo solitario", "Mago", "Medium", "Megera", "Mercante", "Monaco", "Negromante", "Nonna", "Nosferatu", "Oratore", "Oste", "Pazzo",
            "Peccatore", "Posseduto", "Prete", "Sidhe", "Spia", "Strega", "Sensitiva", "Templare", "Vampiro"
        }
    )
    public void testNonCacciatore(String nomeRuolo)
    {
        cambiaRuolo(nomeRuolo);
        verificaFalso(isCacciatore());
    }

    @Test public void testCacciatore()
    {
        cambiaRuolo("Cacciatore");
        verificaVero(isCacciatore());
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Angelo custode", "Assassino", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro",
            "Bracconiere", "Cacciatore", "Cacciatore di vampiri", "Capo branco", "Capo gilda", "Cappuccetto rosso", "Contadino eroe",
            "Contadino discendente dei lupi", "Contadino mostro", "Contadino normale", "Eremita", "Ghoul", "Giovane lupo", "Giulietta", "Giullare",
            "Goblin", "Guardia", "Guardia corrotta", "Guaritore", "Inquisitore", "Ladra", "Leprecauno", "Lupo del branco", "Lupo reietto",
            "Mago", "Medium", "Megera", "Mercante", "Monaco", "Negromante", "Nonna", "Nosferatu", "Oratore", "Oste", "Pazzo", "Peccatore",
            "Posseduto", "Prete", "Sidhe", "Spia", "Strega", "Sensitiva", "Templare", "Vampiro"
        }
    )
    public void testNonLupoSolitario(String nomeRuolo)
    {
        cambiaRuolo(nomeRuolo);
        verificaFalso(giocatore.isLupoSolitario());
    }

    @Test public void testLupoSolitario()
    {
        cambiaRuolo("Lupo solitario");
        verificaVero(giocatore.isLupoSolitario());
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Angelo custode", "Assassino", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro",
            "Bracconiere", "Cacciatore", "Cacciatore di vampiri", "Capo branco", "Capo gilda", "Cappuccetto rosso", "Contadino eroe",
            "Contadino discendente dei lupi", "Contadino mostro", "Contadino normale", "Eremita", "Ghoul", "Giovane lupo", "Giulietta", "Giullare",
            "Goblin", "Guardia", "Guardia corrotta", "Guaritore", "Inquisitore", "Ladra", "Leprecauno", "Lupo del branco", "Lupo reietto",
            "Lupo solitario", "Mago", "Medium", "Megera", "Mercante", "Monaco", "Nonna", "Nosferatu", "Oratore", "Oste", "Pazzo", "Peccatore",
            "Posseduto", "Prete", "Sidhe", "Spia", "Strega", "Sensitiva", "Templare", "Vampiro"
        }
    )
    public void testNonNegromante(String nome)
    {
        cambiaRuolo(nome);
        verificaFalso(giocatore.isNegromante());
    }

    @Test public void testNegromante()
    {
        cambiaRuolo("Negromante");
        verificaVero(giocatore.isNegromante());
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Angelo custode", "Assassino", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro",
            "Bracconiere", "Cacciatore", "Cacciatore di vampiri", "Capo branco", "Cappuccetto rosso", "Contadino eroe",
            "Contadino discendente dei lupi", "Contadino mostro", "Contadino normale", "Eremita", "Ghoul", "Giovane lupo", "Giulietta", "Giullare",
            "Goblin", "Guardia", "Guardia corrotta", "Guaritore", "Inquisitore", "Ladra", "Leprecauno", "Lupo del branco", "Lupo reietto",
            "Lupo solitario", "Mago", "Medium", "Megera", "Mercante", "Monaco", "Negromante", "Nonna", "Nosferatu", "Oratore", "Oste", "Pazzo",
            "Peccatore", "Posseduto", "Prete", "Sidhe", "Spia", "Strega", "Sensitiva", "Templare", "Vampiro"
        }
    )
    public void testNoCapoGilda(String nomeRuolo)
    {
        cambiaRuolo(nomeRuolo);
        verificaVero(giocatore.isCapoGilda());
    }

    @Test public void testCapoGilda()
    {
        cambiaRuolo("Capo gilda");
        verificaFalso(giocatore.isCapoGilda());
    }

    private void verificaAccusabile() { verificaVero(isAccusabile()); }

    private boolean isCacciatore() { return giocatore.isCacciatore(); }

    private void verificaNonAccusabile() { verificaFalso(isAccusabile()); }

    private void segnalazioneInquisitore() { giocatore.segnalazioneInquisitore(); }

    private boolean isAccusabile() { return giocatore.isAccusabile(); }

    private void segnalazioneAzzeccagarbugli() { giocatore.segnalazioneAzzeccagarbugli(); }

    private void incrementaVoti() { giocatore.incrementaVoti(ESEMPIO_VOTI); }

    private boolean isLupo() { return giocatore.isLupo(); }

    private boolean isBracconiere() { return giocatore.isBracconiere(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

    private void verificaAttaccoAssassino(EsitoAttacco esito) { verificaAttacco(giocatore.attaccoAssassino(), esito); }

    private void verificaAttacco(EsitoAttacco valore, EsitoAttacco risultato) { assertThat(valore).isEqualTo(risultato); }

    private void protezioneAngeloCustode() { giocatore.protezioneAngeloCustode(); }

    private void verificaNumeroVoti(int numeroVoti) { assertThat(giocatore.getNumeroVoti()).isEqualTo(numeroVoti); }

    private void verificaNonAmato() { assertThat(isAmato()).isFalse(); }

    private void cambiaRuolo(String nomeRuolo) { giocatore.cambiaRuolo(FACTORY.getRuolo(nomeRuolo)); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private boolean isAmato() { return giocatore.isAmato(); }

}