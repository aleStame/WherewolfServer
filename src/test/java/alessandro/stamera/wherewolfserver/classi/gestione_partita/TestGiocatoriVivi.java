package alessandro.stamera.wherewolfserver.classi.gestione_partita;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoControlloSensitiva;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Misticismo;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.*;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestGiocatoriVivi
{

    private GiocatoriVivi giocatori;

    @BeforeEach public void setUp() { giocatori = new GiocatoriVivi(); }

    @Test public void testBallottaggioPuro()
    {
        String[][] giocatori = new String[][] { { "Giulio", "Pazzo" }, { "Cesare", "Peccatore" }, { "Augusto", "Prete" } };
        inizializzaGiocatori(giocatori);
        int[] numeroVoti = new int[] { 2, 1 };
        for(int i = 0; i < numeroVoti.length; i++) incrementaVoti(giocatori[i][0], numeroVoti[i]);
        verificaAccusati(giocatori[1][0], giocatori[0][0]);
    }

    @Test public void testUnanimita()
    {
        String[][] giocatori = new String[][] { { "Annibale", "Guaritore" }, { "Rodolfo", "Assassino" } };
        inizializzaGiocatori(giocatori);
        int posizione = 0;
        incrementaVoti(giocatori[posizione][0], 3);
        verificaAccusati(giocatori[posizione][0]);
    }

    @Test public void testPareggioPrimoPosto()
    {
        String[][] soluzioni = new String[][] { { "Gabriella", "Capo branco" }, { "Ezio", "Giullare" }, { "Marta", "Prete" } };
        inizializzaGiocatori(soluzioni);
        for(String[] giocatore : soluzioni) incrementaVoti(giocatore[0], 1);
        verificaAccusati(soluzioni[1][0], soluzioni[0][0], soluzioni[2][0]);
    }

    @Test public void testPareggioSecondoPosto()
    {
        String[][] soluzioni = new String[][] { { "Aldo", "Pazzo" }, { "Giovanni", "Guaritore" }, { "Giacomo", "Leprecauno" } };
        inizializzaGiocatori(soluzioni);
        int[] numeroVoti = new int[]{ 2, 1, 1 };
        for(int i = 0; i < numeroVoti.length; i++) incrementaVoti(soluzioni[i][0], numeroVoti[i]);
        verificaAccusati(soluzioni[0][0], soluzioni[2][0], soluzioni[1][0]);
    }

    @Test public void testSegnalazioneAngeloCustode()
    {
        String nomeAmato = "Giulia";
        String[][] giocatori = new String[][] { { "Francesca", "Angelo custode" }, { nomeAmato, "Assassino" }};
        inizializzaGiocatori(giocatori);
        segnalazioneAngeloCustode(nomeAmato);
        verificaVero(isAmato(nomeAmato));
        ripristina(nomeAmato);
    }

    @Test public void testAngeloCustodeAccusatoNonPresente()
    {
        String[][] giocatori = new String[][] { { "Domenico", "Angelo custode" }, { "Franco", "Goblin" }, { "Pamela", "Sidhe" } };
        inizializzaGiocatori(giocatori);
        int posizione = 2;
        segnalazioneAngeloCustode(giocatori[posizione][0]);
        incrementaVoti(giocatori[posizione][0], 3);
        verificaGiocatoreAccusato(getBallottaggio(), 0, giocatori[0][0]);
    }

    @Test public void testAngeloCustodeAccusatoPresente()
    {
        String[][] giocatori = new String[][] { { "Michelle", "Angelo custode" }, { "Fiona", "Altra guardia" }, { "Biagio", "Ladra" } };
        inizializzaGiocatori(giocatori);
        String nome = giocatori[1][0];
        segnalazioneAngeloCustode(nome);
        for(int i = 0; i < giocatori.length - 1; i++) incrementaVoti(giocatori[i][0], 2);
        verificaAccusati(giocatori[0][0]);
        ripristina(nome);
    }

    @Test public void testAttaccoAssassino()
    {
        String[][] giocatori = new String[][] { { "Cristian", "Giullare" }, { "Carmine", "Assassino" } };
        inizializzaGiocatori(giocatori);
        verificaAttaccoAssassino(giocatori[0][0], RIUSCITO);
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro", "Bracconiere", "Cacciatore",
            "Cacciatore di vampiri", "Capo branco", "Capo gilda", "Cappuccetto rosso", "Contadino eroe", "Contadino discendente dei lupi",
            "Contadino normale", "Eremita", "Ghoul", "Giovane lupo", "Giulietta", "Giullare", "Goblin", "Guardia", "Guardia corrotta", "Guaritore", "Inquisitore", "Ladra", "Leprecauno", "Lupo del branco", "Lupo reietto", "Lupo solitario", "Mago",
            "Medium", "Megera", "Mercante", "Monaco", "Negromante", "Nonna", "Nosferatu", "Oratore", "Oste", "Pazzo", "Peccatore", "Posseduto",
            "Prete", "Sidhe", "Spia", "Sensitiva", "Templare", "Vampiro"
        }
    )
    public void testAttaccoAmatoAssassino(String nomeRuolo)
    {
        String nomeVittima = "Maddalena";
        inizializzaGiocatori(new String[][] { { "Enzo", "Angelo custode" }, { "Barbara", "Assassino" }, { nomeVittima, nomeRuolo } });
        verificaAssassinioAmato(nomeVittima);
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro", "Bracconiere", "Cacciatore",
            "Cacciatore di vampiri", "Capo branco", "Capo gilda", "Cappuccetto rosso", "Contadino eroe", "Contadino discendente dei lupi",
            "Contadino normale", "Eremita", "Ghoul", "Giovane lupo", "Giulietta", "Giullare", "Goblin", "Guardia", "Guardia corrotta", "Guaritore", "Inquisitore", "Ladra", "Leprecauno", "Lupo del branco", "Lupo reietto", "Lupo solitario", "Mago",
            "Medium", "Megera", "Mercante", "Monaco", "Negromante", "Nonna", "Nosferatu", "Oratore", "Oste", "Pazzo", "Peccatore", "Posseduto",
            "Prete", "Sidhe", "Spia", "Sensitiva", "Templare", "Vampiro"
        }
    )
    public void testAttaccoAmatoRomeoAssassino(String nomeRuolo)
    {
        String nomeVittima = "Maddalena";
        inizializzaGiocatori(new String[][] { { "Enzo", "Angelo custode" }, { "Barbara", "Assassino" }, { nomeVittima, nomeRuolo } });
        giocatori.romeizzazione(nomeVittima);
        verificaAssassinioAmato(nomeVittima);
    }

    @Test public void testSegnalazioneAzzeccagarbugliAngeloCustode()
    {
        String nomeAngelo = "Carmine";
        String[][] giocatori = new String[][]
            { { nomeAngelo, "Angelo custode" }, { "Carmela", "Contadino eroe" }, { "Virginio", "Inquisitore" }, { "Giorgia", "Giullare" } };
        inizializzaGiocatori(giocatori);
        segnalazioneAzzeccagarbugli(nomeAngelo);
        for(int i = 2; i < giocatori.length; i++) incrementaVoti(giocatori[i][0], 1);
        verificaAccusati(nomeAngelo, giocatori[3][0], giocatori[2][0]);
    }

    @ParameterizedTest
    @CsvSource({ "Assassino", "Capo gilda", "Spia", "Ladra", "Bocca di rosa", "Borgomastro", "Mercante", "Oratore" })
    public void testAzzeramentoAzzeccagarbugli(String nomeRuolo)
    {
        String nome = "Rodolfo";
        aggiungiGiocatore(nome, nomeRuolo);
        segnalazioneAzzeccagarbugli(nome);
        incrementaVoti(nome, 4);
        assertThat(giocatori.getNumeroVoti(nome)).isZero();
        String nome2 = "Domenica";
        aggiungiGiocatore(nome2, "Guaritore");
        incrementaVoti(nome2, 3);
        verificaAccusati(nome2);
    }

    @Test public void testSegnalazioneAzzeccagarbugliAmato()
    {
        String[][] giocatori = new String[][]
            { { "Carmine", "Angelo custode" }, { "Carmela", "Contadino eroe" }, { "Virginio", "Inquisitore" }, { "Giorgia", "Giullare" } };
        int posizione = 2;
        inizializzaGiocatori(giocatori);
        segnalazioneAzzeccagarbugli(giocatori[posizione][0]);
        segnalazioneAngeloCustode(giocatori[posizione][0]);
        for(int i = posizione; i < giocatori.length; i++) incrementaVoti(giocatori[i][0], 1);
        verificaAccusati(giocatori[0][0], giocatori[3][0]);
        this.giocatori.resettaAmato();
    }

    @ParameterizedTest
    @CsvSource({ "Capo branco", "Lupo del branco", "Lupo reietto", "Lupo solitario", "Contadino discendente dei lupi" })
    public void testAttaccoLupiAngeloCustode(String nomeLupo)
    {
        String nome = "Rodolfo";
        aggiungiGiocatore(nome, "Angelo custode");
        verificaAttaccoLupo(nomeLupo, nome, RIUSCITO);
        ripristina(nome);
    }

    @ParameterizedTest
    @CsvSource({ "Capo branco", "Lupo del branco", "Lupo reietto", "Lupo solitario", "Contadino discendente dei lupi" })
    public void testAttaccoLupiAmato(String nomeLupo)
    {
        String nomeAmato = "Valentina";
        String[][] giocatori = new String[][] { { "Elena", "Angelo custode" }, { nomeAmato, "Giullare" } };
        inizializzaGiocatori(giocatori);
        segnalazioneAngeloCustode(nomeAmato);
        verificaAttaccoLupo(nomeLupo, nomeAmato, FALLITO);
        ripristina(nomeAmato);
    }

    @Test public void testSegnalazioneInquisitoreMisticoAssente()
    {
        String[][] giocatori = new String[][] { { "Andrea", "Inquisitore" }, { "Raffaella", "Bocca di rosa" }, { "Raffaele", "Mago" } };
        inizializzaGiocatori(giocatori);
        int posizioneVoto = 1, posizioneMistico = 2;
        segnalazioneInquisitore(giocatori[posizioneMistico][0]);
        incrementaVoti(giocatori[posizioneVoto][0], 2);
        verificaAccusati(giocatori[posizioneMistico][0], giocatori[posizioneVoto][0]);
    }

    @ParameterizedTest
    @CsvSource({ "Goblin", "Guaritore", "Leprecauno", "Mago", "Medium", "Megera", "Negromante", "Sensitiva", "Sidhe" })
    public void testSegnalazioneInquisitoreMisticoPresente(String nomeRuolo)
    {
        String nomeMistico = "Alberto";
        String[][] giocatori = new String[][] { { nomeMistico, nomeRuolo }, { "Tania", "Pazzo" }, { "Alessandro", "Inquisitore" } };
        inizializzaGiocatori(giocatori);
        segnalazioneInquisitore(nomeMistico);
        for(int i = 0; i < giocatori.length - 1; i++) incrementaVoti(giocatori[i][0], 2);
        verificaAccusati(nomeMistico, giocatori[1][0]);
    }

    @Test public void testSegnalazioneInquisitoreMisticoAssenteAmato()
    {
        String[][] giocatori = new String[][] { { "Elena", "Angelo custode" }, { "Irvano", "Medium" }, { "Luca", "Inquisitore" } };
        inizializzaGiocatori(giocatori);
        int posizioneMistico = 1;
        segnalazioneInquisitore(giocatori[posizioneMistico][0]);
        segnalazioneAngeloCustode(giocatori[posizioneMistico][0]);
        int posizioneVoto = 2;
        incrementaVoti(giocatori[posizioneVoto][0], 2);
        verificaAccusati(giocatori[0][0], giocatori[posizioneVoto][0]);
    }

    @ParameterizedTest
    @CsvSource({ "Goblin", "Guaritore", "Leprecauno", "Mago", "Medium", "Megera", "Negromante", "Sensitiva", "Sidhe" })
    public void testSegnalazioneInquisitoreMisticoPresenteAmato(String nomeRuolo)
    {
        String nomeMistico = "Davide";
        String[][] giocatori = new String[][] { { "Antonio", "Angelo custode" }, { nomeMistico, nomeRuolo }, { "Matteo", "Inquisitore" } };
        inizializzaGiocatori(giocatori);
        ripristina(nomeMistico);
        segnalazioneInquisitore(nomeMistico);
        segnalazioneAngeloCustode(nomeMistico);
        for(int i = 1; i < giocatori.length; i++) incrementaVoti(giocatori[i][0], 2);
        verificaAccusati(giocatori[0][0], giocatori[2][0]);
        ripristina(nomeMistico);
    }

    @Test public void testAttaccoVampiroAngeloCustode()
    {
        String[][] giocatori = new String[][] { { "Camilla", "Angelo custode" }, { "Edoardo", "Peccatore" } };
        inizializzaGiocatori(giocatori);
        int posizioneAmato = 1;
        segnalazioneAngeloCustode(giocatori[posizioneAmato][0]);
        verificaAttaccoVampiro(this.giocatori.attaccoVampiro(giocatori[0][0]), RIUSCITO);
        verificaNonAmato(giocatori[posizioneAmato][0]);
    }

    @Test public void testPossedutoAngeloCustode()
    {
        String[][] giocatori = new String[][] { { "Marco", "Angelo custode" }, { "Giorgia", "Posseduto" } };
        inizializzaGiocatori(giocatori);
        int posizioneAngeloCustode = 0, posizioneAmato = 1;
        segnalazioneAngeloCustode(giocatori[posizioneAmato][0]);
        this.giocatori.attaccoPosseduto(giocatori[posizioneAngeloCustode][0]);
        verificaVero(this.giocatori.isPosseduto(giocatori[posizioneAngeloCustode][0]));
        verificaNonAmato(giocatori[posizioneAmato][0]);
    }

    @Test public void testAttaccoAssassinoContadinoMostro()
    {
        String nome = "Matilde";
        aggiungiGiocatore(nome, "Contadino mostro");
        verificaAttaccoAssassino(nome, MORTO);
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Angelo custode", "Assassino", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro",
            "Bracconiere", "Cacciatore", "Cacciatore di vampiri", "Capo branco", "Capo gilda", "Cappuccetto rosso",
            "Contadino discendente dei lupi", "Contadino eroe", "Contadino mostro", "Contadino normale", "Eremita", "Ghoul", "Giulietta",
            "Giullare", "Goblin", "Guardia", "Guaritore", "Giovane lupo", "Inquisitore", "Leprecauno", "Lupo del branco", "Lupo reietto",
            "Lupo solitario", "Mago", "Medium", "Megera", "Monaco", "Negromante", "Nonna", "Nosferatu", "Oratore", "Pazzo", "Peccatore",
            "Posseduto", "Prete", "Sensitiva", "Sidhe", "Spia", "Templare"
        }
    )
    public void testSegnalatoAzzeccagarbugli(String ruolo)
    {
        String nome = "Anna";
        aggiungiGiocatore(nome, ruolo);
        giocatori.segnalazioneAzzeccagarbugli(nome);
        verificaVero(giocatori.isSegnalatoAzzeccagarbugli(nome));
        ripristina(nome);
    }

    @Test public void testGuardia()
    {
        String[][] giocatori = new String[][] { { "Federico", "Altra guardia" }, { "Jacopo", "Assassino" } };
        inizializzaGiocatori(giocatori);
        verificaVero(isGuardia(giocatori[0][0]));
        verificaFalso(isGuardia(giocatori[1][0]));
    }

    @Test public void testCreaturaOmbra()
    {
        String[][] giocatori = new String[][]{ { "Luisa", "Prete" }, { "Matteo", "Nosferatu" } };
        inizializzaGiocatori(giocatori);
        verificaFalso(isCreaturaOmbra(giocatori[0][0]));
        verificaVero(isCreaturaOmbra(giocatori[1][0]));
    }

    @Test public void testContaGuardie()
    {
        inizializzaGiocatori(new String[][]{ { "Sara", "Guardia" }, { "Elisa", "Altra guardia" }, { "Mario", "Pazzo" } });
        verificaNumeroIntero(giocatori.getNumeroGuardie(), 2);
    }

    @Test public void testContaCreatureOmbra()
    {
        inizializzaGiocatori
        (
            new String[][]{ { "Ivan", "Lupo del branco" }, { "Giulia", "Giovane lupo" }, { "Beatrice", "Nosferatu" }, { "Mario", "Goblin" } }
        );
        verificaNumeroIntero(giocatori.getNumeroCreatureOmbra(), 3);
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia, Angelo custode", "Azzeccagarbugli", "Bardo", "Borgomastro", "Becchino", "Bracconiere", "Cacciatore",
            "Cacciatore di vampiri", "Capo gilda", "Cappuccetto rosso", "Contadino eroe", "Contadino normale", "Eremita", "Ghoul", "Giulietta",
            "Giullare", "Guardia", "Guaritore", "Inquisitore", "Ladra", "Mago", "Medium", "Mercante", "Monaco", "Nonna", "Oratore", "Oste",
            "Pazzo", "Prete", "Spia", "Templare"
        }
    )
    public void testAuraChiara(String nomeRuolo)
    {
        String nome = "Concetta";
        aggiungiGiocatore(nome, nomeRuolo);
        assertThat(giocatori.getControlloVeggente(nome)).isEqualTo(BIANCA);
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Assassino", "Bocca di rosa", "Boia", "Capo branco", "Giovane lupo", "Lupo del branco", "Lupo reietto", "Lupo solitario", "Megera",
            "Negromante", "Nosferatu", "Peccatore", "Posseduto", "Guardia corrotta"
        }
    )
    public void testAuraOscura(String nomeRuolo)
    {
        String nome = "Gervaso";
        aggiungiGiocatore(nome, nomeRuolo);
        assertThat(giocatori.getControlloVeggente(nome)).isEqualTo(NERA);
        ripristina(nome);
    }

    @Test public void testNumeroCriminali()
    {
        inizializzaGiocatori(new String[][] { { "Claudia", "Assassino" }, { "Pamela", "Capo gilda" } });
        verificaNumeroIntero(giocatori.getNumeroCriminali(), 2);
    }

    @Test public void testNegromantePresente()
    {
        String nome = "Giorgia";
        inizializzaGiocatori(new String[][] { { "Pino", "Capo branco" }, { nome, "Negromante" } });
        verificaVero(isNegromantePresente());
        verificaStringa(giocatori.getNomeNegromante(), nome);
    }

    @Test public void testNegromanteAssente()
    {
        inizializzaGiocatori(new String[][] { { "Salvatore", "Lupo del branco" }, { "Franca", "Mercante" } });
        verificaFalso(isNegromantePresente());
    }

    @Test public void testNumeroMistici()
    {
        inizializzaGiocatori(new String[][] { { "Tizio", "Guaritore" }, { "Caio", "Goblin" }, { "Sempronio", "Bocca di rosa" } });
        verificaNumeroIntero(giocatori.getNumeroMistici(), 2);
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Capo branco", "Giovane lupo", "Goblin", "Guaritore", "Leprecauno", "Lupo del branco", "Lupo reietto", "Lupo solitario", "Mago",
            "Medium", "Megera", "Negromante", "Nosferatu", "Posseduto", "Sensitiva", "Sidhe"
        }
    )
    public void testSegnalazioneBoiaRiuscita(String nomeRuolo)
    {
        String nome = "Claudio";
        aggiungiGiocatore(nome, nomeRuolo);
        segnalazioneBoia(nome);
        verificaVero(isSegnalatoBoia(nome));
        giocatori.annullaSegnalazioneBoia(nome);
        verificaNonSegnalatoBoia(nome);
        ripristina(nome);
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Angelo custode", "Assassino", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro", "Bracconiere",
            "Cacciatore", "Cacciatore di vampiri", "Capo gilda", "Cappuccetto rosso", "Contadino eroe", "Contadino mostro", "Contadino normale",
            "Eremita", "Guardia", "Ghoul", "Giulietta", "Giullare", "Guardia corrotta", "Inquisitore", "Ladra", "Mercante", "Monaco", "Nonna",
            "Oratore", "Oste", "Pazzo", "Peccatore", "Prete", "Spia", "Templare"
        }
    )
    public void testSegnalazioneBoiaNonRiuscita(String nomeRuolo)
    {
        String nome = "Vanessa";
        aggiungiGiocatore(nome, nomeRuolo);
        segnalazioneBoia(nome);
        verificaNonSegnalatoBoia(nome);
    }

    @Test public void testNumeroLupi()
    {
        inizializzaGiocatori(new String[][] { { "Aurora", "Lupo del branco" }, { "Elisa", "Lupo del branco" }, { "Mohamed", "Bracconiere" } });
        verificaNumeroIntero(giocatori.getNumeroLupi(), 2);
    }

    @Test public void testBracconierePresente()
    {
        String nome = "Gianfranco";
        inizializzaGiocatori(new String[][] { { "Rosalba", "Bocca di rosa" }, { nome, "Bracconiere" } });
        verificaVero(isBracconierePresente());
        verificaStringa(giocatori.getNomeBracconiere(), nome);
    }

    @Test public void testBracconiereAssente()
    {
        aggiungiGiocatore("Franco", "Mercante");
        verificaFalso(isBracconierePresente());
    }

    @Test public void testUtilizzoPotereBracconiere()
    {
        inizializzaGiocatori(new String[][] { { "Ciccio", "Bracconiere" }, { "Piera", "Lupo del branco" } });
        verificaPotereBracconiereNonUtilizzato();
        giocatori.utilizzaPotereBracconiere();
        verificaVero(isPotereBracconiereUtilizzato());
        giocatori.riabilitaPotereBracconiere();
        verificaPotereBracconiereNonUtilizzato();
    }

    @Test public void testLupoSolitarioAssente() { verificaFalso(giocatori.isLupoSolitarioPresente()); }

    @Test public void testLupoSolitarioPresente()
    {
        inizializzaGiocatori(new String[][] { { "Anna", "Guardia" }, { "Maurizio", "Lupo solitario" } });
        verificaVero(giocatori.isLupoSolitarioPresente());
    }

    @Test public void testCacciatoreProtettoLupoSolitario()
    {
        inizializzaGiocatori(new String[][] { { "Andrea", "Cacciatore" }, { "Piero", "Lupo solitario" } });
        verificaCacciatoreProtetto();
    }

    @Test public void testCacciatoreProtettoUnLupo()
    {
        inizializzaGiocatori(new String[][] { { "Elisa", "Bracconiere" }, { "Edoardo", "Lupo del branco" }, { "Franca", "Giullare" } });
        verificaCacciatoreProtetto();
    }

    @Test public void testCacciatoreUltimoLupoBranco()
    {
        inizializzaGiocatori
        (
            new String[][] { { "Giulia", "Capo branco" }, { "Federico", "Lupo solitario" }, { "Carmine", "Bracconiere" }, { "Luisa", "Prete" } }
        );
        verificaCacciatoreProtetto();
    }

    @Test public void testCacciatoreNonProtetto()
    {
        inizializzaGiocatori(new String[][] { { "Cristian", "Cacciatore" }, { "Carmine", "Capo branco" }, { "Mario", "Lupo reietto" } });
        verificaFalso(isCacciatoreProtetto());
    }

    @Test public void testNomeNosferatu()
    {
        String soluzione = "Donatello";
        inizializzaGiocatori(new String[][] { { "Michelangelo", "Prete" }, { soluzione, "Nosferatu" } });
        verificaStringa(giocatori.getNomeNosferatu(), soluzione);
    }

    @Test public void testNumeroSenzaFazione()
    {
        inizializzaGiocatori(new String[][] { { "Raffaello", "Ghoul" }, { "Mattia", "Peccatore" }, { "Leonardo", "Pazzo" } });
        verificaNumeroIntero(giocatori.getNumeroSenzaFazione(), 2);
    }

    @Test public void testGildataCapoBranco()
    {
        String nome = "Giuseppe";
        aggiungiGiocatore(nome, "Capo branco");
        verificaAttaccoVampiro(giocatori.gildata(nome), MORTO);
    }

    @Test public void testNomeCapoGilda()
    {
        String nome = "Barbara";
        inizializzaGiocatori(new String[][] { { nome, "Capo gilda" }, { "Alessandro", "Bocca di rosa" } });
        verificaStringa(giocatori.getNomeCapoGilda(), nome);
    }

    @Test public void testInizioCrociata()
    {
        String tipoLupo = "Capo branco", nomeVittima = "Chloe";
        inizializzaGiocatori(new String[][] { { "Yorgos", tipoLupo }, { "James", "Inquisitore" }, { nomeVittima, "Templare" } });
        verificaAttaccoLupoRiuscito(tipoLupo, nomeVittima);
        verificaVero(isCrociataAvviata());
    }

    @Test public void testMancatoInizioCrociata()
    {
        String tipoLupo = "Capo branco", nomeVittima = "Eve";
        inizializzaGiocatori(new String[][] { { "Daniel", tipoLupo }, { "Wesley", "Inquisitore" }, { nomeVittima, "Goblin" } });
        verificaAttaccoLupoRiuscito(tipoLupo, nomeVittima);
        verificaFalso(isCrociataAvviata());
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia, MORTO", "Angelo custode, FALLITO", "Azzeccagarbugli, RIUSCITO", "Bardo, RIUSCITO", "Becchino, RIUSCITO",
            "Bocca di rosa, RIUSCITO", "Borgomastro, RIUSCITO", "Bracconiere, RIUSCITO", "Cacciatore, RIUSCITO", "Cacciatore di vampiri, RIUSCITO",
            "Capo branco, MORTO", "Cappuccetto rosso, RIUSCITO", "Contadino discendente dei lupi, RIUSCITO", "Contadino eroe, RIUSCITO",
            "Contadino normale, RIUSCITO", "Eremita, RIUSCITO", "Ghoul, FALLITO", "Giovane lupo, MORTO", "Giulietta, FALLITO",
            "Giullare, FALLITO", "Goblin, FALLITO", "Guardia, MORTO", "Guaritore, RIUSCITO", "Inquisitore, FALLITO", "Leprecauno, FALLITO",
            "Lupo del branco, MORTO", "Lupo reietto, MORTO", "Lupo solitario, MORTO", "Mago, RIUSCITO", "Medium, RIUSCITO", "Megera, FALLITO",
            "Mercante, RIUSCITO", "Monaco, RIUSCITO", "Negromante, FALLITO", "Nonna, RIUSCITO", "Nosferatu, FALLITO", "Oratore, RIUSCITO",
            "Oste, RIUSCITO", "Pazzo, FALLITO", "Peccatore, RIUSCITO", "Posseduto, FALLITO", "Prete, RIUSCITO", "Sensitiva, RIUSCITO",
            "Sidhe, FALLITO", "Templare, FALLITO"
        }
    )
    public void testCriminalizzazioneCapoGilda(String nomeRuolo, EsitoAttacco esito)
    {
        String nomeVittima = "Antonio";
        inizializzaGiocatori(new String[][] { { nomeVittima, nomeRuolo }, { "Davide", "Capo gilda" } });
        verificaGildata(nomeVittima, esito);
        ripristina(nomeVittima);
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Angelo custode", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Borgomastro", "Bracconiere", "Cacciatore",
            "Cappuccetto rosso", "Contadino discendente dei lupi", "Contadino eroe", "Contadino normale", "Ghoul", "Giulietta", "Giullare",
            "Guardia", "Inquisitore", "Mercante", "Monaco", "Nonna", "Oratore", "Oste", "Pazzo", "Peccatore", "Prete", "Templare"
        }
    )
    public void testCriminalizzazioneProgenieVampiro(String nomeRuolo)
    {
        String nomeVittima = "Antonio";
        inizializzaGiocatori(new String[][] { { nomeVittima, nomeRuolo }, { "Davide", "Capo gilda" }, { "Gioele", "Vampiro" } });
        verificaAttaccoVampiro(attaccoVampiro(nomeVittima), RIUSCITO);
        verificaGildata(nomeVittima, FALLITO);
        ripristina(nomeVittima);
    }

    @ParameterizedTest @CsvSource({ "Capo branco", "Lupo del branco", "Lupo reietto", "Lupo solitario" })
    public void testCriminalizzazioneContadinoMostroAttaccato(String tipoLupo)
    {
        String nomeContadino = "Giuseppe", nomeCapoGilda = "Elisa", nomeLupo = "Gaia";
        String[][] giocatori =
            new String[][] { { nomeCapoGilda, "Capo gilda" }, { nomeLupo, tipoLupo }, { nomeContadino, "Contadino discendente dei lupi" } };
        inizializzaGiocatori(giocatori);
        verificaAttaccoVampiro(this.giocatori.attaccoLupi(this.giocatori.getRuolo(nomeLupo), nomeContadino), FALLITO);
        verificaGildata(nomeContadino, MORTO);
        ripristina(nomeContadino);
    }

    @Test public void testCriminalizzazioneBecchino()
    {
        String nomeVittima = "Giulia";
        inizializzaGiocatori(new String[][] { { nomeVittima, "Becchino" }, { "Tania", "Capo gilda" } });
        giocatori.riconosciNegromante();
        verificaGildata(nomeVittima, FALLITO);
        ripristina(nomeVittima);
    }

    @Test public void testCriminalizzazioneContadinoLupo()
    {
        String tipoLupo = "Lupo del branco", nomeVittima = "Alberto";
        inizializzaGiocatori
        (
            new String[][] { { "Sara", tipoLupo }, { nomeVittima, "Contadino discendente dei lupi" }, { "Andrea", "Capo gilda" } }
        );
        verificaAttaccoLupo(tipoLupo, nomeVittima, FALLITO);
        verificaGildata(nomeVittima, MORTO);
    }

    @Test public void testCriminalizzazioneContadinoMostro()
    {
        String nomeVittima = "Alberto";
        inizializzaGiocatori(new String[][] { { nomeVittima, "Contadino mostro" }, { "Andrea", "Capo gilda" } });
        verificaGildata(nomeVittima, MORTO);
    }

    @ParameterizedTest
    @CsvSource({ "Capo branco", "Giovane lupo", "Lupo del branco", "Lupo reietto", "Lupo solitario", "Contadino discendente dei lupi" })
    public void testPerditaProtezioniCappuccettoRosso(String tipoLupo)
    {
        String nome = "Maria";
        aggiungiGiocatore("Maria", "Cappuccetto rosso");
        giocatori.annullaProtezioniCappuccettoRosso();
        verificaAttaccoLupo(tipoLupo, nome, RIUSCITO);
        ripristina(nome);
    }

    @Test public void testNonnaPresente()
    {
        aggiungiGiocatore("Francesca", "Nonna");
        verificaVero(isNonnaPresente());
    }

    @Test public void testNonnaAssente() { verificaFalso(isNonnaPresente()); }

    @Test public void testCappuccettoRossoPresente()
    {
        aggiungiGiocatore("Michela", "Cappuccetto rosso");
        verificaVero(isCappuccettoRossoPresente());
    }

    @Test public void testCappuccettoRossoAssente() { verificaFalso(isCappuccettoRossoPresente()); }

    @Test public void testPresenzaGuaritore()
    {
        String nome = "Giuseppina";
        aggiungiGiocatore(nome, "Guaritore");
        verificaVero(isGuaritorePresente());
        verificaStringa(giocatori.getNomeGuaritore(), nome);
        giocatori.eliminaGiocatore(nome);
        verificaFalso(isGuaritorePresente());
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia, NON_MISTICO", "Angelo custode, NON_MISTICO", "Assassino, NON_MISTICO", "Azzeccagarbugli, NON_MISTICO",
            "Bardo, NON_MISTICO", "Becchino, NON_MISTICO", "Bocca di rosa, NON_MISTICO", "Boia, NON_MISTICO", "Borgomastro, NON_MISTICO",
            "Bracconiere, NON_MISTICO", "Cacciatore, NON_MISTICO", "Cacciatore di vampiri, NON_MISTICO", "Capo branco, NON_MISTICO",
            "Capo gilda, NON_MISTICO", "Cappuccetto rosso, NON_MISTICO", "Contadino eroe, NON_MISTICO",
            "Contadino discendente dei lupi, NON_MISTICO", "Contadino mostro, NON_MISTICO", "Contadino normale, NON_MISTICO",
            "Eremita, NON_MISTICO", "Ghoul, NON_MISTICO", "Giovane lupo, NON_MISTICO", "Giulietta, NON_MISTICO", "Giullare, NON_MISTICO",
            "Goblin, MISTICO", "Guardia, NON_MISTICO", "Guardia corrotta, NON_MISTICO", "Guaritore, MISTICO", "Inquisitore, NON_MISTICO",
            "Ladra, NON_MISTICO", "Leprecauno, MISTICO", "Lupo del branco, NON_MISTICO", "Lupo reietto, NON_MISTICO", "Lupo solitario, NON_MISTICO",
            "Mago, MISTICO", "Medium, MISTICO", "Megera, MISTICO", "Mercante, NON_MISTICO", "Monaco, NON_MISTICO", "Negromante, MISTICO",
            "Nonna, NON_MISTICO", "Nosferatu, NON_MISTICO", "Oratore, NON_MISTICO", "Oste, NON_MISTICO", "Pazzo, NON_MISTICO",
            "Peccatore, NON_MISTICO", "Posseduto, NON_MISTICO", "Prete, NON_MISTICO", "Sidhe, MISTICO", "Spia, NON_MISTICO", "Sensitiva, MISTICO",
            "Sensitiva, MISTICO", "Templare, NON_MISTICO", "Vampiro, NON_MISTICO"
        }
    )
    public void testControlloMago(String nomeRuolo, Misticismo misticismo)
    {
        String nome = "Mario";
        aggiungiGiocatore(nome, nomeRuolo);
        assertThat(giocatori.controlloMago(nome)).isEqualTo(misticismo);
    }

    @Test public void testMagoAssente() { verificaFalso(isMagoPresente()); }

    @Test public void testMagoPresente()
    {
        String nome = "Merlino";
        aggiungiGiocatore(nome, "Mago");
        verificaVero(isMagoPresente());
        verificaStringa(giocatori.getNomeMago(), nome);
    }

    @Test public void testAttaccoNegromante()
    {
        String nome = "Mike";
        aggiungiGiocatore(nome, "Contadino mostro");
        verificaAttaccoVampiro(giocatori.attaccoNegromante(nome), MORTO);
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia, VILLAGGIO", "Angelo custode, NON_VILLAGGIO", "Assassino, NON_VILLAGGIO", "Azzeccagarbugli, NON_VILLAGGIO",
            "Bardo, VILLAGGIO", "Becchino, VILLAGGIO", "Bocca di rosa, NON_VILLAGGIO", "Boia, NON_VILLAGGIO", "Borgomastro, NON_VILLAGGIO",
            "Bracconiere, VILLAGGIO", "Cacciatore, VILLAGGIO", "Cacciatore di vampiri, VILLAGGIO", "Capo branco, NON_VILLAGGIO",
            "Capo gilda, NON_VILLAGGIO", "Cappuccetto rosso, VILLAGGIO", "Contadino eroe, VILLAGGIO", "Contadino discendente dei lupi, VILLAGGIO",
            "Contadino mostro, VILLAGGIO", "Contadino normale, VILLAGGIO", "Eremita, VILLAGGIO", "Ghoul, NON_VILLAGGIO",
            "Giovane lupo, NON_VILLAGGIO", "Giulietta, NON_VILLAGGIO", "Giullare, NON_VILLAGGIO", "Goblin, NON_VILLAGGIO", "Guardia, VILLAGGIO",
            "Guardia corrotta, NON_VILLAGGIO", "Guaritore, VILLAGGIO", "Inquisitore, NON_VILLAGGIO", "Ladra, NON_VILLAGGIO",
            "Leprecauno, NON_VILLAGGIO", "Lupo del branco, NON_VILLAGGIO", "Lupo reietto, NON_VILLAGGIO", "Lupo solitario, NON_VILLAGGIO",
            "Mago, VILLAGGIO", "Medium, VILLAGGIO", "Megera, NON_VILLAGGIO", "Mercante, NON_VILLAGGIO", "Monaco, VILLAGGIO",
            "Negromante, NON_VILLAGGIO", "Nonna, VILLAGGIO", "Nosferatu, NON_VILLAGGIO", "Oratore, NON_VILLAGGIO", "Oste, VILLAGGIO",
            "Pazzo, NON_VILLAGGIO", "Peccatore, VILLAGGIO", "Posseduto, NON_VILLAGGIO", "Prete, VILLAGGIO", "Sidhe, NON_VILLAGGIO",
            "Spia, NON_VILLAGGIO", "Sensitiva, VILLAGGIO", "Templare, NON_VILLAGGIO"
        }
    )
    public void testControlloSensitiva(String nomeRuolo, EsitoControlloSensitiva esito)
    {
        String nome = "Chiara";
        aggiungiGiocatore(nome, nomeRuolo);
        assertThat(giocatori.controlloSensitiva(nome)).isEqualTo(esito);
    }

    @Test public void testSensitivaPresente()
    {
        String nome = "Mariangela";
        aggiungiGiocatore(nome, "Sensitiva");
        verificaVero(isSensitivaPresente());
        verificaStringa(giocatori.getNomeSensitiva(), nome);
    }

    @Test public void testSensitivaAssente() { verificaFalso(isSensitivaPresente()); }

    @Test public void testGhoulPresente()
    {
        String nome = "Tony";
        aggiungiGiocatore(nome, "Ghoul");
        verificaVero(isGhoul(nome));
        verificaVero(giocatori.isGhoulPresente());
        verificaStringa(giocatori.getNomeGhoul(), nome);
    }

    @Test public void testGhoulAssente()
    {
        String nome = "Margherita";
        aggiungiGiocatore(nome, "Cacciatore");
        verificaFalso(isGhoul(nome));
    }

    @Test public void testNosferatuPresente()
    {
        String nome = "Matilde";
        aggiungiGiocatore(nome, "Nosferatu");
        verificaVero(isNosferatu(nome));
    }

    @Test public void testNosferatuAssente()
    {
        String nome = "Pamela";
        aggiungiGiocatore(nome, "Cacciatore di vampiri");
        verificaFalso(isNosferatu(nome));
    }

    @Test public void testLupoReiettoPresente()
    {
        aggiungiGiocatore("Giulia", "Lupo reietto");
        verificaVero(isLupoReiettoPresente());
    }

    @Test public void testLupoReiettoAssente() { verificaFalso(isLupoReiettoPresente()); }

    @Test public void testCapoBrancoPresente()
    {
        aggiungiGiocatore("Alessio", "Capo branco");
        verificaVero(isCapoBrancoPresente());
    }

    @Test public void testCapoBrancoAssente() { verificaFalso(isCapoBrancoPresente()); }

    @Test public void testLupoBrancoPresente()
    {
        aggiungiGiocatore("Morgana", "Lupo del branco");
        verificaVero(isLupoBrancoPresente());
    }

    @Test public void testLupoBrancoAssente() { verificaFalso(isLupoBrancoPresente()); }

    @Test public void testAmatoAssente()
    {
        aggiungiGiocatore("Gabriele", "Angelo custode");
        verificaFalso(isAmatoPresente());
    }

    @Test public void testAmatoPresente()
    {
        String nome = "Enrica";
        inizializzaGiocatori(new String[][] { { "Libero", "Angelo custode" }, { nome, "Bocca di rosa" } });
        giocatori.segnalazioneAngeloCustode(nome);
        verificaVero(isAmatoPresente());
    }

    @Test public void testFazioneNosferatu()
    {
        String nome = "Gigio";
        aggiungiGiocatore(nome, "Nosferatu");
        verificaVero(isFazioneNosferatu(nome));
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Angelo custode", "Assassino", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro",
            "Bracconiere", "Cacciatore", "Cacciatore di vampiri", "Capo branco", "Capo gilda", "Cappuccetto rosso", "Contadino eroe",
            "Contadino discendente dei lupi", "Contadino mostro", "Contadino normale", "Eremita", "Ghoul", "Giovane lupo", "Giulietta", "Giullare",
            "Goblin", "Guardia", "Guardia corrotta,", "Guaritore", "Inquisitore", "Ladra", "Leprecauno", "Lupo del branco", "Lupo reietto",
            "Lupo solitario", "Mago", "Medium", "Megera", "Mercante", "Monaco", "Negromante", "Nonna", "Oratore", "Oste", "Pazzo", "Peccatore",
            "Posseduto", "Prete", "Sidhe", "Spia", "Sensitiva", "Templare"
        }
    )
    public void testNonFazioneNosferatu(String nomeRuolo)
    {
        String nome = "Gioele";
        aggiungiGiocatore(nome, nomeRuolo);
        verificaFalso(isFazioneNosferatu(nome));
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Assassino", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro", "Bracconiere",
            "Cacciatore", "Cacciatore di vampiri", "Capo branco", "Capo gilda", "Cappuccetto rosso", "Contadino eroe",
            "Contadino discendente dei lupi", "Contadino mostro", "Contadino normale", "Eremita", "Ghoul", "Giovane lupo", "Giulietta", "Giullare",
            "Goblin", "Guardia", "Guardia corrotta", "Guaritore", "Inquisitore", "Ladra", "Leprecauno", "Lupo del branco", "Lupo reietto",
            "Lupo solitario", "Mago", "Medium", "Megera", "Mercante", "Monaco", "Negromante", "Nonna", "Nosferatu", "Oratore", "Oste", "Pazzo",
            "Peccatore", "Posseduto", "Prete", "Sidhe", "Spia", "Sensitiva", "Templare"
        }
    )
    public void testVampirizzazioneAngeloCustode(String nomeRuolo)
    {
        String nomeAngelo = "Milo", nomeAmato = "Tonio";
        inizializzaGiocatori(new String[][] { { nomeAmato, nomeRuolo }, { nomeAngelo, "Angelo custode" }, { "Lucio", "Vampiro" } });
        segnalazioneAngeloCustode(nomeAmato);
        verificaVero(isAmato(nomeAmato));
        verificaAttaccoVampiro(attaccoVampiro(nomeAngelo), RIUSCITO);
        verificaNonAmato(nomeAmato);
        ripristina(nomeAngelo);
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Assassino, RIUSCITO", "Azzeccagarbugli, RIUSCITO", "Cacciatore di vampiri, MORTO", "Capo branco, MORTO", "Cappuccetto rosso, RIUSCITO",
            "Contadino discendente dei lupi, RIUSCITO", "Contadino mostro, MORTO", "Eremita, FALLITO", "Ghoul, RIUSCITO", "Giulietta, RIUSCITO",
            "Giovane lupo, MORTO", "Goblin, FALLITO", "Guaritore, FALLITO", "Inquisitore, RIUSCITO", "Leprecauno, FALLITO",
            "Lupo del branco, MORTO", "Lupo reietto, MORTO", "Lupo solitario, MORTO", "Mago, FALLITO", "Medium, FALLITO", "Negromante, FALLITO"
        }
    )
    public void testAttaccoVampiro(String nomeRuolo, EsitoAttacco esito)
    {
        String nome = "Luca";
        inizializzaGiocatori(new String[][] { { "Paolo", "Vampiro" }, { nome, nomeRuolo } });
        verificaAttaccoVampiro(attaccoVampiro(nome), esito);
        ripristina(nome);
    }

    @ParameterizedTest @CsvSource({ "Capo branco", "Lupo del branco", "Lupo reietto", "Lupo solitario" })
    public void testAttaccoVampiroContadinoLupizzato(String tipoLupo)
    {
        String nome = "Carlo";
        inizializzaGiocatori(new String[][] { { "Giovanni", tipoLupo }, { nome, "Contadino discendente dei lupi" }, { "Pino", "Vampiro" } });
        verificaAttaccoLupo(tipoLupo, nome, FALLITO);
        verificaAttaccoVampiro(attaccoVampiro(nome), MORTO);
        ripristina(nome);
    }

    @Test public void testVampiroAssente() { verificaFalso(isVampiroPresente()); }

    @Test public void testVampiroPresente()
    {
        String nome = "Andrea";
        aggiungiGiocatore(nome, "Vampiro");
        verificaVero(isVampiroPresente());
        verificaStringa(giocatori.getNomeVampiro(), nome);
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Angelo custode", "Assassino", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro",
            "Bracconiere", "Cacciatore", "Cacciatore di vampiri", "Capo branco", "Capo gilda", "Cappuccetto rosso", "Contadino eroe",
            "Contadino discendente dei lupi", "Contadino mostro", "Contadino normale", "Ghoul", "Giovane lupo", "Giulietta", "Giullare", "Guardia",
            "Guardia corrotta", "Guaritore", "Inquisitore", "Lupo del branco", "Lupo reietto", "Lupo solitario", "Mago", "Medium", "Mercante",
            "Monaco", "Nonna", "Nosferatu", "Oratore", "Oste", "Pazzo", "Peccatore", "Posseduto", "Prete", "Spia", "Sensitiva", "Sensitiva",
            "Templare", "Vampiro"
        }
    )
    public void testMaledizione(String nomeRuolo)
    {
        String nome = "Mario";
        aggiungiGiocatore(nome, nomeRuolo);
        giocatori.maledizione(nome);
        verificaMaledetto(nome);
        ripristina(nome);
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
    public void testNonMistico(String nomeRuolo)
    {
        String nome = "Luigi";
        aggiungiGiocatore(nome, nomeRuolo);
        verificaFalso(isMistico(nome));
    }

    @ParameterizedTest
    @CsvSource({ "Goblin", "Guaritore", "Leprecauno", "Mago", "Medium", "Megera", "Negromante", "Sensitiva", "Sidhe" })
    public void testMistico(String nomeRuolo)
    {
        String nome = "Salvatore";
        aggiungiGiocatore(nome, nomeRuolo);
        verificaVero(isMistico(nome));
    }

    @ParameterizedTest @CsvSource({ "Guaritore", "Mago", "Medium", "Megera", "Sensitiva" })
    public void testMisticiMaledetti(String nomeRuolo)
    {
        String nome = "Gianluigi";
        aggiungiGiocatore(nome, nomeRuolo);
        giocatori.maledizione(nome);
        verificaMaledetto(nome);
        giocatori.ripristinaMistici();
        verificaFalso(isMaledetto(nome));
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Angelo custode", "Assassino", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro",
            "Bracconiere", "Cacciatore", "Cacciatore di vampiri", "Capo branco", "Capo gilda", "Cappuccetto rosso", "Contadino eroe",
            "Contadino discendente dei lupi", "Contadino mostro", "Contadino normale", "Eremita", "Ghoul", "Giovane lupo", "Giulietta", "Giullare",
            "Guardia", "Guardia corrotta", "Inquisitore", "Ladra", "Lupo del branco", "Lupo reietto", "Lupo solitario", "Megera", "Mercante",
            "Monaco", "Nonna", "Nosferatu", "Oratore", "Oste", "Pazzo", "Peccatore", "Posseduto", "Prete", "Spia", "Templare", "Vampiro"
        }
    )
    public void testNoNegromante(String nomeRuolo)
    {
        String nome = "Claudia";
        aggiungiGiocatore(nome, nomeRuolo);
        verificaFalso(isNegromante(nome));
    }

    @Test public void testNegromante()
    {
        String nome = "Massimiliano";
        aggiungiGiocatore(nome, "Negromante");
        verificaVero(isNegromante(nome));
        verificaStringa(giocatori.getNomeNegromante(), nome);
    }

    @Test public void testMaledizioneMegeraNonRomeizzata()
    {
        String nome = "Elisa";
        String[][] giocatori = new String[][] { { nome, "Megera" }, { "Alemanno", "Negromante" } };
        inizializzaGiocatori(giocatori);
        this.giocatori.attaccoNegromante(nome);
        for(String[] giocatore : giocatori) verificaMaledetto(giocatore[0]);
    }

    private void verificaAssassinioAmato(String nome)
    {
        segnalazioneAngeloCustode(nome);
        verificaAttaccoAssassino(nome, ANGELO_CUSTODE_MORTO);
        ripristina(nome);
    }

    private boolean isNegromante(String nome) { return giocatori.isNegromante(nome); }

    private void verificaMaledetto(String nome) { verificaVero(isMaledetto(nome)); }

    private boolean isMaledetto(String nome) { return giocatori.isMaledetto(nome); }

    private boolean isMistico(String nome) { return giocatori.isMistico(nome); }

    private void verificaAttaccoVampiro(EsitoAttacco nome, EsitoAttacco esito) { verificaAttacco(nome, esito); }

    private boolean isVampiroPresente() { return giocatori.isVampiroPresente(); }

    private EsitoAttacco attaccoVampiro(String nome) { return giocatori.attaccoVampiro(nome); }

    private void ripristina(String nome) { giocatori.ripristina(nome); }

    private boolean isFazioneNosferatu(String nome) { return giocatori.isFazioneNosferatu(nome); }

    private boolean isAmatoPresente() { return giocatori.isAmatoPresente(); }

    private boolean isLupoBrancoPresente() { return giocatori.isLupoBrancoPresente(); }

    private boolean isCapoBrancoPresente() { return giocatori.isCapoBrancoPresente(); }

    private boolean isLupoReiettoPresente() { return giocatori.isLupoReiettoPresente(); }

    private boolean isNosferatu(String nome) { return giocatori.isNosferatu(nome); }

    private boolean isGhoul(String nome) { return giocatori.isGhoul(nome); }

    private boolean isSensitivaPresente() { return giocatori.isSensitivaPresente(); }

    private boolean isMagoPresente() { return giocatori.isMagoPresente(); }

    private boolean isGuaritorePresente() { return giocatori.isGuaritorePresente(); }

    private boolean isCappuccettoRossoPresente() { return giocatori.isCappuccettoRossoPresente(); }

    private boolean isNonnaPresente() { return giocatori.isNonnaPresente(); }

    private void verificaGildata(String nome, EsitoAttacco esito)
    {
        verificaAttaccoVampiro(giocatori.gildata(nome), esito);
    }

    private void verificaAttaccoLupoRiuscito(String tipoLupo, String nomeVittima)
    {
        verificaAttaccoLupo(tipoLupo, nomeVittima, RIUSCITO);
    }

    private boolean isCrociataAvviata() { return giocatori.isCrociataAvviata(); }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaCacciatoreProtetto() { verificaVero(isCacciatoreProtetto()); }

    private boolean isCacciatoreProtetto() { return giocatori.isCacciatoreProtetto(); }

    private void verificaPotereBracconiereNonUtilizzato() { verificaFalso(isPotereBracconiereUtilizzato()); }

    private boolean isPotereBracconiereUtilizzato() { return giocatori.isPotereBracconiereUtilizzato(); }

    private boolean isBracconierePresente() { return giocatori.isBracconierePresente(); }

    private void verificaNonSegnalatoBoia(String nome) { verificaFalso(isSegnalatoBoia(nome)); }

    private void segnalazioneBoia(String nome) { giocatori.segnalazioneBoia(nome); }

    private boolean isSegnalatoBoia(String nome) { return giocatori.isSegnalatoBoia(nome); }

    private boolean isNegromantePresente() { return giocatori.isNegromantePresente(); }

    private void inizializzaGiocatori(String[][] giocatori)
    {
        for(String[] giocatore : giocatori) aggiungiGiocatore(giocatore[0], giocatore[1]);
    }

    private void verificaNumeroIntero(int valore, int risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

    private boolean isCreaturaOmbra(String nome) { return giocatori.isCreaturaOmbra(nome); }

    private boolean isGuardia(String nome) { return giocatori.isGuardia(nome); }

    private void segnalazioneAzzeccagarbugli(String nome) { giocatori.segnalazioneAzzeccagarbugli(nome); }

    private void aggiungiGiocatore(String nomeGiocatore, String nomeRuolo) { giocatori.aggiungiGiocatore(nomeGiocatore, getRuolo(nomeRuolo)); }

    private void verificaAccusati(String... soluzioni)
    {
        int numeroSoluzioni = soluzioni.length;
        Giocatori ballottaggio = getBallottaggio();
        verificaNumeroIntero(ballottaggio.getNumeroGiocatori(), numeroSoluzioni);
        for (int i = 0; i < numeroSoluzioni; i++) verificaGiocatoreAccusato(ballottaggio, i, soluzioni[i]);
    }

    private void verificaAttaccoAssassino(String nome, EsitoAttacco esito)
    {
        verificaAttaccoVampiro(giocatori.attaccoAssassino(nome), esito);
    }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void segnalazioneAngeloCustode(String nome) { giocatori.segnalazioneAngeloCustode(nome); }

    private void segnalazioneInquisitore(String nome) { giocatori.segnalazioneInquisitore(nome); }

    private void incrementaVoti(String nome, int voti) { giocatori.incrementaVoti(nome, voti); }

    private void verificaGiocatoreAccusato(Giocatori ballottaggio, int posizione, String nome)
    {
        verificaNomeGiocatore(ballottaggio.getNomeGiocatore(posizione), nome);
    }

    private void verificaNomeGiocatore(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private Giocatori getBallottaggio() { return giocatori.getBallottaggio(); }

    private Ruolo getRuolo(String nome) { return FACTORY.getRuolo(nome); }

    private void verificaAttaccoLupo(String nomeLupo, String nome, EsitoAttacco esito)
    {
        assertThat(giocatori.attaccoLupi(getRuolo(nomeLupo), nome)).isEqualTo(esito);
    }

    private void verificaNonAmato(String nome) { assertThat(isAmato(nome)).isFalse(); }

    private boolean isAmato(String nome) { return giocatori.isAmato(nome); }

    private void verificaAttacco(EsitoAttacco valore, EsitoAttacco risultato) { assertThat(valore).isEqualTo(risultato); }

}