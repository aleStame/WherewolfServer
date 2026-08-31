package alessandro.stamera.wherewolfserver.classi.gestione_partita;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Tratto;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.RuoloNullo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

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

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia, BIANCA", "Angelo custode, BIANCA", "Assassino, NERA", "Azzeccagarbugli, BIANCA", "Bardo, BIANCA", "Becchino, BIANCA",
            "Bocca di rosa, NERA", "Boia, NERA", "Borgomastro, BIANCA", "Bracconiere, BIANCA", "Cacciatore, BIANCA",
            "Cacciatore di vampiri, BIANCA", "Capo branco, NERA", "Capo gilda, BIANCA", "Cappuccetto rosso, BIANCA", "Contadino eroe, BIANCA",
            "Contadino discendente dei lupi, BIANCA", "Contadino mostro, NERA", "Contadino normale, BIANCA", "Eremita, BIANCA", "Ghoul, BIANCA",
            "Giovane lupo, NERA", "Giulietta, BIANCA", "Giullare, BIANCA", "Goblin, NERA", "Guardia, BIANCA", "Guardia corrotta, NERA",
            "Guaritore, BIANCA", "Inquisitore, BIANCA", "Ladra, BIANCA", "Leprecauno, BIANCA", "Lupo del branco, NERA", "Lupo reietto, NERA",
            "Lupo solitario, NERA", "Mago, BIANCA", "Medium, BIANCA", "Megera, NERA", "Mercante, BIANCA", "Monaco, BIANCA", "Negromante, NERA",
            "Nonna, BIANCA", "Nosferatu, NERA", "Oratore, BIANCA", "Oste, BIANCA", "Pazzo, BIANCA", "Peccatore, NERA", "Posseduto, NERA",
            "Prete, BIANCA", "Sidhe, BIANCA", "Spia, BIANCA", "Sensitiva, BIANCA", "Templare, BIANCA", "Vampiro, NERA"
        }
    )
    public void testVoti(String nomeRuolo, Aura aura)
    {
        cambiaRuolo(nomeRuolo);
        incrementaVoti();
        verificaNumeroVoti(ESEMPIO_VOTI);
        giocatore.annullaVoti();
        verificaNumeroVoti(0);
        giocatore.maledizione();
        verificaNumeroVoti(1);
        verificaMaledetto();
        verificaAura(NERA);
        annullaMaledizione();
        verificaAura(aura);
        verificaNonMaledetto();

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

    @ParameterizedTest @CsvSource({ "Angelo custode, RIUSCITO", "Contadino mostro, MORTO", "Giullare, RIUSCITO" })
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
        verificaFalso(isLupoSolitario());
    }

    @Test public void testLupoSolitario()
    {
        cambiaRuolo("Lupo solitario");
        verificaVero(isLupoSolitario());
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
        verificaFalso(isNegromante());
    }

    @Test public void testNegromante()
    {
        cambiaRuolo("Negromante");
        verificaVero(isNegromante());
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
        verificaFalso(isCapoGilda());
    }

    @Test public void testCapoGilda()
    {
        cambiaRuolo("Capo gilda");
        verificaVero(isCapoGilda());
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Angelo custode", "Assassino", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro",
            "Bracconiere", "Cacciatore", "Cacciatore di vampiri", "Capo branco", "Capo gilda", "Cappuccetto rosso", "Contadino eroe",
            "Contadino discendente dei lupi", "Contadino mostro", "Contadino normale", "Eremita", "Ghoul", "Giovane lupo", "Giulietta", "Giullare",
            "Goblin", "Guardia", "Guardia corrotta", "Guaritore", "Inquisitore", "Ladra", "Leprecauno", "Lupo del branco", "Lupo reietto",
            "Lupo solitario", "Mago", "Medium", "Megera", "Mercante", "Monaco", "Negromante", "Nosferatu", "Oratore", "Oste", "Pazzo", "Peccatore",
            "Posseduto", "Prete", "Sidhe", "Spia", "Strega", "Sensitiva", "Templare", "Vampiro"
        }
    )
    public void testNoNonna(String nomeRuolo)
    {
        cambiaRuolo(nomeRuolo);
        verificaFalso(isNonna());
    }

    @Test public void testNonna()
    {
        cambiaRuolo("Nonna");
        verificaVero(isNonna());
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Angelo custode", "Assassino", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro",
            "Bracconiere", "Cacciatore", "Cacciatore di vampiri", "Capo branco", "Capo gilda", "Contadino eroe", "Contadino discendente dei lupi",
            "Contadino mostro", "Contadino normale", "Eremita", "Ghoul", "Giovane lupo", "Giulietta", "Giullare", "Goblin", "Guardia",
            "Guardia corrotta", "Guaritore", "Inquisitore", "Ladra", "Leprecauno", "Lupo del branco", "Lupo reietto", "Lupo solitario", "Mago",
            "Medium", "Megera", "Mercante", "Monaco", "Negromante", "Nonna", "Nosferatu", "Oratore", "Oste", "Pazzo", "Peccatore", "Posseduto",
            "Prete", "Sidhe", "Spia", "Strega", "Sensitiva", "Templare", "Vampiro"
        }
    )
    public void testNoCappuccettoRosso(String nomeRuolo)
    {
        cambiaRuolo(nomeRuolo);
        verificaFalso(isCappuccettoRosso());
    }

    @Test public void testCappuccettoRosso()
    {
        utilizzaCappuccettoRosso();
        verificaVero(isCappuccettoRosso());
    }

    @Test public void testAttaccoLupoSolitarioCappuccettoRosso()
    {
        utilizzaCappuccettoRosso();
        verificaAttaccoLupoSolitario(ULTIMO_LUPO_UCCIDE_CAPPUCCETTO_ROSSO);
    }

    @Test public void testAttaccoLupoSolitarioCappuccettoRossoAmato()
    {
        utilizzaCappuccettoRosso();
        protezioneAngeloCustode();
        verificaLupoSolitarioSvegliaCappuccettoRosso();
    }

    @Test public void testAttaccoLupoSolitarioCappuccettoRossoProtetto()
    {
        utilizzaCappuccettoRosso();
        giocatore.aggiungiProtezione(getLupoSolitario());
        verificaLupoSolitarioSvegliaCappuccettoRosso();
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Angelo custode", "Assassino", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro",
            "Bracconiere", "Cacciatore", "Cacciatore di vampiri", "Capo branco", "Capo gilda", "Cappuccetto rosso", "Contadino eroe",
            "Contadino discendente dei lupi", "Contadino mostro", "Contadino normale", "Eremita", "Ghoul", "Giovane lupo", "Giulietta", "Giullare",
            "Goblin", "Guardia", "Guardia corrotta", "Inquisitore", "Ladra", "Leprecauno", "Lupo del branco", "Lupo reietto", "Lupo solitario",
            "Mago", "Medium", "Megera", "Mercante", "Monaco", "Negromante", "Nonna", "Nosferatu", "Oratore", "Oste", "Pazzo", "Peccatore",
            "Posseduto", "Prete", "Sidhe", "Spia", "Strega", "Sensitiva", "Templare", "Vampiro"
        }
    )
    public void testNoGuaritore(String nomeRuolo)
    {
        cambiaRuolo(nomeRuolo);
        verificaFalso(isGuaritore());
    }

    @Test public void testGuaritore()
    {
        cambiaRuolo("Guaritore");
        verificaVero(isGuaritore());
    }

    @ParameterizedTest @CsvSource({ "Capo branco", "Lupo del branco", "Lupo reietto", "Lupo solitario" })
    public void testGildataContadinoDopoLupizzazione(String tipoLupo)
    {
        lupizzazioneContadino(tipoLupo);
        verificaEsitoGildata(MORTO);
    }

    @Test public void testCriminalizzazioneBecchinoDopoRiconoscimentoNegromante()
    {
        cambiaRuolo("Becchino");
        giocatore.riconosciNegromante();
        verificaEsitoGildata(FALLITO);
    }

    @ParameterizedTest @CsvSource({ "Capo branco", "Lupo del branco", "Lupo reietto", "Lupo solitario" })
    public void testVampirizzazioneContadinoDopoLupizzazione(String tipoLupo)
    {
        lupizzazioneContadino(tipoLupo);
        verificaEsitoVampirizzazione(MORTO);
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia, RIUSCITO", "Angelo custode, RIUSCITO", "Assassino, RIUSCITO", "Azzeccagarbugli, RIUSCITO", "Bardo, RIUSCITO",
            "Becchino, RIUSCITO", "Bocca di rosa, RIUSCITO", "Boia, RIUSCITO", "Borgomastro, RIUSCITO", "Bracconiere, RIUSCITO",
            "Cacciatore, RIUSCITO", "Cacciatore di vampiri, MORTO", "Capo branco, MORTO", "Capo gilda, RIUSCITO", "Cappuccetto rosso, RIUSCITO",
            "Contadino eroe, RIUSCITO", "Contadino discendente dei lupi, RIUSCITO", "Contadino mostro, MORTO", "Contadino normale, RIUSCITO",
            "Eremita, FALLITO", "Ghoul, RIUSCITO", "Giovane lupo, MORTO", "Giulietta, RIUSCITO", "Giullare, RIUSCITO", "Goblin, FALLITO",
            "Guardia, RIUSCITO", "Guardia corrotta, RIUSCITO", "Guaritore, FALLITO", "Inquisitore, RIUSCITO", "Leprecauno, FALLITO",
            "Lupo del branco, MORTO", "Lupo reietto, MORTO", "Lupo solitario, MORTO", "Mago, FALLITO", "Medium, FALLITO", "Megera, FALLITO",
            "Mercante, RIUSCITO", "Monaco, RIUSCITO", "Negromante, FALLITO", "Nonna, RIUSCITO", "Nosferatu, FALLITO", "Oratore, RIUSCITO",
            "Oste, RIUSCITO", "Pazzo, RIUSCITO", "Peccatore, RIUSCITO", "Posseduto, TROVATO_POSSEDUTO", "Prete, RIUSCITO", "Sidhe, FALLITO",
            "Spia, RIUSCITO", "Strega, FALLITO", "Sensitiva, FALLITO", "Templare, RIUSCITO"
        }
    )
    public void testVampirizzazione(String nomeRuolo, EsitoAttacco esito)
    {
        cambiaRuolo(nomeRuolo);
        verificaEsitoVampirizzazione(esito);
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Assassino", "Angelo custode", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro",
            "Bracconiere", "Cacciatore", "Cacciatore di vampiri", "Capo branco", "Capo gilda", "Cappuccetto rosso", "Contadino eroe",
            "Contadino discendente dei lupi", "Contadino normale", "Ghoul", "Giovane lupo", "Giulietta", "Giullare", "Guardia", "Guardia corrotta",
            "Guaritore", "Inquisitore", "Lupo del branco", "Lupo reietto", "Lupo solitario", "Mago", "Medium", "Megera", "Mercante", "Monaco",
            "Negromante", "Nonna", "Nosferatu", "Oratore", "Oste", "Pazzo", "Peccatore", "Posseduto", "Prete", "Spia", "Sensitiva", "Templare",
            "Vampiro"
        }
    )
    public void testFunzionamentoNegromante(String nomeRuolo)
    {
        cambiaRuolo(nomeRuolo);
        verificaAttacco(giocatore.attaccoNegromante(), RIUSCITO);
        verificaMaledetto();
        annullaMaledizione();
        verificaNonMaledetto();
    }

    @Test public void testPassaPossedutoPrete()
    {
        cambiaRuolo("Prete");
        verificaEsitoPassaggioPosseduto(MORTO);
        verificaEsitoVampirizzazione(RIUSCITO);
        verificaEsitoPassaggioPosseduto(RIUSCITO);
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Angelo custode", "Assassino", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro",
            "Bracconiere", "Cacciatore", "Cacciatore di vampiri", "Capo branco", "Capo gilda", "Cappuccetto rosso", "Contadino eroe",
            "Contadino discendente dei lupi", "Contadino mostro", "Contadino normale", "Eremita", "Ghoul", "Giovane lupo", "Giullare", "Goblin",
            "Guardia", "Guardia corrotta", "Guaritore", "Inquisitore", "Ladra", "Leprecauno", "Lupo del branco", "Lupo reietto", "Lupo solitario",
            "Mago", "Medium", "Megera", "Mercante", "Monaco", "Negromante", "Nonna", "Nosferatu", "Oratore", "Oste", "Pazzo", "Peccatore",
            "Posseduto", "Prete", "Sidhe", "Spia", "Strega", "Sensitiva", "Templare", "Vampiro"
        }
    )
    public void testRomeizzazione(String nomeRuolo)
    {
        cambiaRuolo(nomeRuolo);
        giocatore.romeizzazione();
        verificaVero(giocatore.isRomeo());
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Angelo custode", "Assassino", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro",
            "Bracconiere", "Cacciatore", "Cacciatore di vampiri", "Capo branco", "Capo gilda", "Cappuccetto rosso", "Eremita", "Ghoul",
            "Giovane lupo", "Giullare", "Goblin", "Guardia", "Guardia corrotta", "Guaritore", "Inquisitore", "Ladra", "Leprecauno", "Lupo del branco",
            "Lupo reietto", "Lupo solitario", "Mago", "Medium", "Megera", "Mercante", "Monaco", "Negromante", "Nonna", "Nosferatu", "Oratore", "Oste",
            "Pazzo", "Peccatore", "Posseduto", "Prete", "Sidhe", "Spia", "Strega", "Sensitiva", "Templare", "Vampiro"
        }
    )
    public void testNonContadino(String nomeRuolo)
    {
        cambiaRuolo(nomeRuolo);
        verificaFalso(isContadino());
    }

    @ParameterizedTest
    @CsvSource({ "Contadino eroe", "Contadino discendente dei lupi", "Contadino normale", "Contadino mostro" })
    public void testContadino(String tipoContadino)
    {
        cambiaRuolo(tipoContadino);
        verificaVero(isContadino());
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Angelo custode", "Assassino", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro",
            "Bracconiere", "Cacciatore", "Cacciatore di vampiri", "Capo branco", "Capo gilda", "Cappuccetto rosso", "Contadino discendente dei lupi",
            "Contadino mostro", "Contadino normale", "Eremita", "Ghoul", "Giovane lupo", "Giullare", "Goblin", "Guardia", "Guardia corrotta",
            "Guaritore", "Inquisitore", "Ladra", "Leprecauno", "Lupo del branco", "Lupo reietto", "Lupo solitario", "Mago", "Medium", "Megera",
            "Mercante", "Monaco", "Negromante", "Nonna", "Nosferatu", "Oratore", "Oste", "Pazzo", "Peccatore", "Posseduto", "Prete", "Sidhe", "Spia",
            "Strega", "Sensitiva", "Templare", "Vampiro"
        }
    )
    public void testNoContadinoEroe(String nomeRuolo)
    {
        cambiaRuolo(nomeRuolo);
        verificaFalso(isContadinoEroe());
    }

    @Test public void testContadinoEroe()
    {
        cambiaRuolo("Contadino eroe");
        verificaVero(isContadinoEroe());
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Angelo custode", "Assassino", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro",
            "Bracconiere", "Cacciatore", "Cacciatore di vampiri", "Capo branco", "Capo gilda", "Cappuccetto rosso", "Contadino eroe",
            "Contadino mostro", "Contadino normale", "Eremita", "Ghoul", "Giovane lupo", "Giullare", "Goblin", "Guardia", "Guardia corrotta",
            "Guaritore", "Inquisitore", "Ladra", "Leprecauno", "Lupo del branco", "Lupo reietto", "Lupo solitario", "Mago", "Medium", "Megera",
            "Mercante", "Monaco", "Negromante", "Nonna", "Nosferatu", "Oratore", "Oste", "Pazzo", "Peccatore", "Posseduto", "Prete", "Sidhe", "Spia",
            "Strega", "Sensitiva", "Templare", "Vampiro"
        }
    )
    public void testNoContadinoLupo(String nomeRuolo)
    {
        cambiaRuolo(nomeRuolo);
        verificaFalso(isContadinoLupo());
    }

    @Test public void testContadinoLupo()
    {
        cambiaRuolo("Contadino discendente dei lupi");
        verificaVero(isContadinoLupo());
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Angelo custode", "Assassino", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro",
            "Bracconiere", "Cacciatore", "Cacciatore di vampiri", "Capo branco", "Capo gilda", "Cappuccetto rosso", "Contadino discendente dei lupi",
            "Contadino eroe", "Contadino mostro", "Contadino normale", "Eremita", "Ghoul", "Giovane lupo", "Giullare", "Goblin", "Guardia",
            "Guardia corrotta", "Guaritore", "Inquisitore", "Ladra", "Leprecauno", "Lupo del branco", "Lupo reietto", "Lupo solitario", "Mago",
            "Medium", "Mercante", "Monaco", "Negromante", "Nonna", "Nosferatu", "Oratore", "Oste", "Pazzo", "Peccatore", "Posseduto", "Prete",
            "Sidhe", "Spia", "Strega", "Sensitiva", "Templare", "Vampiro"
        }
    )
    public void testNoMegera(String nomeRuolo)
    {
        cambiaRuolo(nomeRuolo);
        verificaFalso(isMegera());
    }

    @Test public void testMegera()
    {
        cambiaRuolo("Megera");
        verificaVero(isMegera());
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Angelo custode", "Assassino", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro",
            "Bracconiere", "Cacciatore", "Cacciatore di vampiri", "Capo branco", "Capo gilda", "Cappuccetto rosso", "Eremita", "Ghoul",
            "Giovane lupo", "Giullare", "Goblin", "Guardia", "Guardia corrotta", "Guaritore", "Inquisitore", "Ladra", "Leprecauno", "Lupo del branco",
            "Lupo reietto", "Lupo solitario", "Mago", "Medium", "Megera", "Mercante", "Monaco", "Negromante", "Nonna", "Nosferatu", "Oratore", "Oste",
            "Pazzo", "Peccatore", "Posseduto", "Prete", "Sidhe", "Spia", "Strega", "Sensitiva", "Templare", "Vampiro"
        }
    )
    public void testNomeRuolo(String nomeRuolo) { verificaNomeRuolo(nomeRuolo, nomeRuolo); }

    @ParameterizedTest
    @CsvSource({ "Contadino discendente dei lupi", "Contadino eroe", "Contadino mostro", "Contadino normale" })
    public void testNomeContadino(String tipoContadino) { verificaNomeRuolo(tipoContadino, "Contadino"); }

    @ParameterizedTest @EnumSource(Tratto.class) public void testTrattoPresente(Tratto tratto)
    {
        giocatore.aggiungiTratto(tratto);
        verificaVero(giocatore.isTrattoPresente(tratto));
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Angelo custode", "Assassino", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro",
            "Bracconiere", "Cacciatore", "Capo branco", "Capo gilda", "Cappuccetto rosso", "Contadino discendente dei lupi", "Contadino eroe",
            "Contadino mostro", "Contadino normale", "Eremita", "Ghoul", "Giovane lupo", "Giullare", "Goblin", "Guardia", "Guardia corrotta",
            "Guaritore", "Inquisitore", "Ladra", "Leprecauno", "Lupo del branco", "Lupo reietto", "Lupo solitario", "Mago", "Medium", "Megera",
            "Mercante", "Monaco", "Negromante", "Nonna", "Nosferatu", "Oratore", "Oste", "Pazzo", "Peccatore", "Posseduto", "Prete", "Sidhe", "Spia",
            "Strega", "Sensitiva", "Templare", "Vampiro"
        }
    )
    public void testNoCacciatoreDiVampiri(String nomeRuolo)
    {
        cambiaRuolo(nomeRuolo);
        verificaFalso(isCacciatoreDiVampiri());
    }

    @Test public void testCacciatoreDiVampiri()
    {
        cambiaRuolo("Cacciatore di vampiri");
        verificaVero(isCacciatoreDiVampiri());
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Angelo custode", "Assassino", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro",
            "Bracconiere", "Cacciatore", "Capo branco", "Capo gilda", "Cappuccetto rosso", "Contadino discendente dei lupi", "Contadino eroe",
            "Contadino mostro", "Contadino normale", "Eremita", "Ghoul", "Giovane lupo", "Giullare", "Goblin", "Guardia", "Guardia corrotta",
            "Guaritore", "Inquisitore", "Ladra", "Leprecauno", "Lupo del branco", "Lupo reietto", "Lupo solitario", "Mago", "Medium", "Megera",
            "Mercante", "Monaco", "Negromante", "Nonna", "Nosferatu", "Oratore", "Oste", "Pazzo", "Peccatore", "Posseduto", "Prete", "Sidhe",
            "Spia", "Strega", "Templare", "Vampiro"
        }
    )
    public void testNoSensitiva(String nomeRuolo)
    {
        cambiaRuolo(nomeRuolo);
        verificaFalso(isSensitiva());
    }

    @Test public void testSensitiva()
    {
        cambiaRuolo("Sensitiva");
        verificaVero(isSensitiva());
    }

    @Test public void testGiocatoreStregato()
    {
        verificaFalso(giocatore.isStregato());
        giocatore.protezioneStrega();
        verificaVero(giocatore.isStregato());
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Angelo custode", "Assassino", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro",
            "Bracconiere", "Cacciatore", "Capo branco", "Capo gilda", "Cappuccetto rosso", "Contadino discendente dei lupi", "Contadino eroe",
            "Contadino mostro", "Contadino normale", "Ghoul", "Giovane lupo", "Giullare", "Goblin", "Guardia", "Guardia corrotta", "Guaritore",
            "Inquisitore", "Ladra", "Leprecauno", "Lupo del branco", "Lupo reietto", "Lupo solitario", "Mago", "Medium", "Megera", "Mercante",
            "Monaco", "Negromante", "Nonna", "Nosferatu", "Oratore", "Oste", "Pazzo", "Peccatore", "Posseduto", "Prete", "Sensitiva", "Sidhe",
            "Spia", "Strega", "Templare", "Vampiro"
        }
    )
    public void testNoEremita(String nomeRuolo)
    {
        cambiaRuolo(nomeRuolo);
        verificaFalso(isEremita());
    }

    @Test public void testEremita()
    {
        cambiaRuolo("Eremita");
        verificaVero(isEremita());
    }

    @Test public void testIsVampiro()
    {
        cambiaRuolo("Vampiro");
        verificaVero(isVampiro());
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Angelo custode", "Assassino", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro",
            "Bracconiere", "Cacciatore", "Capo branco", "Capo gilda", "Cappuccetto rosso", "Contadino discendente dei lupi", "Contadino eroe",
            "Contadino mostro", "Contadino normale", "Eremita", "Ghoul", "Giovane lupo", "Giullare", "Goblin", "Guardia", "Guardia corrotta",
            "Guaritore", "Inquisitore", "Ladra", "Leprecauno", "Lupo del branco", "Lupo reietto", "Lupo solitario", "Mago", "Medium", "Megera",
            "Mercante", "Monaco", "Negromante", "Nonna", "Nosferatu", "Oratore", "Oste", "Pazzo", "Peccatore", "Posseduto", "Prete", "Sensitiva",
            "Sidhe", "Spia", "Strega", "Templare"
        }
    )
    public void testNoVampiro(String nomeRuolo)
    {
        cambiaRuolo(nomeRuolo);
        verificaFalso(isVampiro());
    }

    @ParameterizedTest @CsvSource({ "Capo branco", "Lupo del branco", "Lupo reietto" })
    public void testPerditaProtezioniCacciatore(String tipoLupo)
    {
        cambiaRuolo("Cacciatore");
        Ruolo lupo = FACTORY.getRuolo(tipoLupo);
        giocatore.aggiungiProtezione(lupo);
        giocatore.perdiProtezione(lupo);
        verificaFalso(giocatore.isProtezionePresente(lupo));
    }

    @ParameterizedTest @CsvSource({ "Capo branco", "Lupo del branco", "Lupo reietto", "Lupo solitario" })
    public void testLupoExNonna(String tipoLupo)
    {
        cambiaRuolo("Nonna");
        verificaAttacco(giocatore.attaccoLupi(FACTORY.getRuolo(tipoLupo)), NONNA_BECCATA);
        verificaVero(isLupoExNonna());
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Angelo custode", "Assassino", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro",
            "Bracconiere", "Cacciatore", "Capo branco", "Capo gilda", "Cappuccetto rosso", "Contadino discendente dei lupi", "Contadino eroe",
            "Contadino mostro", "Contadino normale", "Eremita", "Ghoul", "Giovane lupo", "Giullare", "Goblin", "Guardia", "Guardia corrotta",
            "Guaritore", "Inquisitore", "Ladra", "Leprecauno", "Lupo del branco", "Lupo reietto", "Lupo solitario", "Mago", "Medium", "Megera",
            "Mercante", "Monaco", "Negromante", "Nonna", "Nosferatu", "Oratore", "Oste", "Pazzo", "Peccatore", "Posseduto", "Prete", "Sensitiva",
            "Sidhe", "Spia", "Strega", "Templare", "Vampiro"
        }
    )
    public void testNoLupoExNonna(String nomeRuolo)
    {
        cambiaRuolo(nomeRuolo);
        verificaFalso(isLupoExNonna());
    }

    @Test public void testSegnalazioneBoiaNonRiuscita()
    {
        cambiaRuolo("Cacciatore");
        giocatore.segnalazioneBoia();
        verificaFalso(giocatore.isSegnalatoBoia());
    }

    private boolean isLupoExNonna() { return giocatore.isLupoExNonna(); }

    private boolean isVampiro() { return giocatore.isVampiro(); }

    private boolean isEremita() { return giocatore.isEremita(); }

    private boolean isSensitiva() { return giocatore.isSensitiva(); }

    private boolean isCacciatoreDiVampiri() { return giocatore.isCacciatoreDiVampiri(); }

    private void verificaNomeRuolo(String nomeRuolo, String soluzione)
    {
        cambiaRuolo(nomeRuolo);
        assertThat(giocatore.getNomeRuolo()).isEqualTo(soluzione);
    }

    private boolean isMegera() { return giocatore.isMegera(); }

    private boolean isContadinoLupo() { return giocatore.isContadinoLupo(); }

    private boolean isContadinoEroe() { return giocatore.isContadinoEroe(); }

    private boolean isContadino() { return giocatore.isContadino(); }

    private void verificaEsitoPassaggioPosseduto(EsitoAttacco esito) { verificaAttacco(giocatore.passaPosseduto(), esito); }

    private void verificaEsitoVampirizzazione(EsitoAttacco esito) { verificaAttacco(giocatore.vampirizzazione(), esito); }

    private boolean isLupoSolitario() { return giocatore.isLupoSolitario(); }

    private void verificaAura(Aura aura) { assertThat(giocatore.getAura()).isEqualTo(aura); }

    private void verificaNonMaledetto() { verificaFalso(isMaledetto()); }

    private void verificaMaledetto() { verificaVero(isMaledetto()); }

    private boolean isMaledetto() { return giocatore.isMaledetto(); }

    private void annullaMaledizione() { giocatore.annullaMaledizione(); }

    private void verificaEsitoGildata(EsitoAttacco esito) { verificaAttacco(giocatore.gildata(), esito); }

    private void lupizzazioneContadino(String tipoLupo)
    {
        cambiaRuolo("Contadino discendente dei lupi");
        verificaAttacco(giocatore.attaccoLupi(FACTORY.getRuolo(tipoLupo)), CONTADINO_LUPO_BECCATO);
    }

    private void verificaLupoSolitarioSvegliaCappuccettoRosso()
    {
        verificaAttaccoLupoSolitario(ULTIMO_LUPO_SVEGLIA_CAPPUCCETTO_ROSSO);
    }

    private boolean isGuaritore() { return giocatore.isGuaritore(); }

    private void utilizzaCappuccettoRosso() { cambiaRuolo("Cappuccetto rosso"); }

    private void verificaAttaccoLupoSolitario(EsitoAttacco esito)
    {
        assertThat(giocatore.attaccoLupi(getLupoSolitario())).isEqualTo(esito);
    }

    private Ruolo getLupoSolitario() { return FACTORY.getRuolo("Lupo solitario"); }

    private boolean isCappuccettoRosso() { return giocatore.isCappuccettoRosso(); }

    private boolean isNonna() { return giocatore.isNonna(); }

    private boolean isCapoGilda() { return giocatore.isCapoGilda(); }

    private boolean isNegromante() { return giocatore.isNegromante(); }

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