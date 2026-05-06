package alessandro.stamera.wherewolfserver.classi.gestione_partita;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.*;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.NOSFERATU;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.VAMPIRO;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Tratto.NON_MORTO;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestGiocatoriVivi
{

    private GiocatoriVivi giocatori;

    @BeforeEach public void setUp()
    {
        FACTORY.annullaSegnalazioni();
        giocatori = new GiocatoriVivi();
    }

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
        String[][] giocatori = new String[][] { { "Francesca", "Angelo custode" }, { "Giulia", "Assassino" }};
        inizializzaGiocatori(giocatori);
        int posizione = 1;
        segnalazioneAngeloCustode(giocatori[posizione][0]);
        verificaVero(isAmato(giocatori[posizione][0]));
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
        segnalazioneAngeloCustode(giocatori[1][0]);
        for(int i = 0; i < giocatori.length - 1; i++) incrementaVoti(giocatori[i][0], 2);
        verificaAccusati(giocatori[0][0]);
    }

    @Test public void testAttaccoAssassino()
    {
        String[][] giocatori = new String[][] { { "Cristian", "Giullare" }, { "Carmine", "Assassino" } };
        inizializzaGiocatori(giocatori);
        verificaAttaccoAssassino(giocatori[0][0], RIUSCITO);
    }

    @Test public void testAttaccoAmatoAssassino()
    {
        String[][] giocatori = new String[][] { { "Enzo", "Angelo custode" }, { "Barbara", "Bardo" }, { "Maddalena", "Oste" } };
        inizializzaGiocatori(giocatori);
        int posizione = 2;
        segnalazioneAngeloCustode(giocatori[posizione][0]);
        verificaAttaccoAssassino(giocatori[posizione][0], FALLITO);
    }

    @Test public void testSegnalazioneAzzeccagarbugliAngeloCustode()
    {
        String[][] giocatori = new String[][]
            { { "Carmine", "Angelo custode" }, { "Carmela", "Contadino eroe" }, { "Virginio", "Inquisitore" }, { "Giorgia", "Giullare" } };
        inizializzaGiocatori(giocatori);
        segnalazioneAzzeccagarbugli(giocatori[0][0]);
        for(int i = 2; i < giocatori.length; i++) incrementaVoti(giocatori[i][0], 1);
        verificaAccusati(giocatori[0][0], giocatori[3][0], giocatori[2][0]);
    }

    @ParameterizedTest @CsvSource({ "Assassino, Capo gilda, Spia, Ladra, Bocca di rosa, Borgomastro, Mercante, Oratore" })
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
    }

    @ParameterizedTest
    @CsvSource({ "Capo branco, Lupo del branco, Lupo reietto, Lupo solitario, Contadino discendente dei lupi" })
    public void testAttaccoLupiAngeloCustode(String nomeLupo)
    {
        String nome = "Rodolfo";
        aggiungiGiocatore(nome, "Angelo custode");
        verificaAttaccoLupo(nomeLupo, nome, RIUSCITO);
    }

    @ParameterizedTest
    @CsvSource({ "Capo branco, Lupo del branco, Lupo reietto, Lupo solitario, contadino discendente dei lupi" })
    public void testAttaccoLupiAmato(String nomeLupo)
    {
        String[][] giocatori = new String[][] { { "Elena", "Angelo custode" }, { "Valentina", "Giullare" } };
        inizializzaGiocatori(giocatori);
        int posizione = 1;
        segnalazioneAngeloCustode(giocatori[posizione][0]);
        verificaAttaccoLupo(nomeLupo, giocatori[posizione][0], FALLITO);
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

    @Test public void testSegnalazioneInquisitoreMisticoPresente()
    {
        String[][] giocatori = new String[][] { { "Alberto", "Guaritore" }, { "Tania", "Pazzo" }, { "Alessandro", "Inquisitore" } };
        inizializzaGiocatori(giocatori);
        int posizioneMistico = 0;
        segnalazioneInquisitore(giocatori[posizioneMistico][0]);
        for(int i = 0; i < giocatori.length - 1; i++) incrementaVoti(giocatori[i][0], 2);
        verificaAccusati(giocatori[posizioneMistico][0], giocatori[1][0]);
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

    @Test public void testSegnalazioneInquisitoreMisticoPresenteAmato()
    {
        String[][] giocatori = new String[][] { { "Antonio", "Angelo custode" }, { "Davide", "Leprecauno" }, { "Matteo", "Inquisitore" } };
        inizializzaGiocatori(giocatori);
        int posizioneMistico = 1;
        segnalazioneInquisitore(giocatori[posizioneMistico][0]);
        segnalazioneAngeloCustode(giocatori[posizioneMistico][0]);
        for(int i = 1; i < giocatori.length; i++) incrementaVoti(giocatori[i][0], 2);
        verificaAccusati(giocatori[0][0], giocatori[2][0]);
    }

    @Test public void testAttaccoNosferatuAngeloCustode()
    {
        String[][] giocatori = new String[][] { { "Silvia", "Angelo custode" }, { "Piergiorgio", "Inquisitore" } };
        inizializzaGiocatori(giocatori);
        int posizioneAmato = 1;
        segnalazioneAngeloCustode(giocatori[posizioneAmato][0]);
        verificaProgenie(giocatori[posizioneAmato][0], giocatori[0][0], NOSFERATU);
    }

    @Test public void testAttaccoVampiroAngeloCustode()
    {
        String[][] giocatori = new String[][] { { "Camilla", "Angelo custode" }, { "Edoardo", "Peccatore" } };
        inizializzaGiocatori(giocatori);
        int posizioneAmato = 1;
        segnalazioneAngeloCustode(giocatori[posizioneAmato][0]);
        verificaProgenie(giocatori[posizioneAmato][0], giocatori[0][0], VAMPIRO);
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

    @Test public void testSegnalatoAzzeccagarbugli()
    {
        String nome = "Anna";
        aggiungiGiocatore(nome, "Leprecauno");
        giocatori.segnalazioneAzzeccagarbugli(nome);
        verificaVero(giocatori.isSegnalatoAzzeccagarbugli(nome));
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
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Angelo custode", "Assassino", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro", "Bracconiere",
            "Cacciatore", "Cacciatore di vampiri", "Capo gilda", "Cappuccetto rosso", "Contadino discendente dei lupi", "Contadino eroe",
            "Contadino mostro", "Contadino normale", "Eremita", "Guardia", "Ghoul", "Giulietta", "Giullare", "Guardia corrotta", "Inquisitore",
            "Ladra", "Mercante", "Monaco", "Nonna", "Oratore", "Oste", "Pazzo", "Peccatore", "Prete", "Spia", "Templare"
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

    private void verificaProgenie(String nomeAmato, String nomeAngelo, Fazione fazione)
    {
        EsitoAttacco esito = null;
        switch(fazione)
        {
            case NOSFERATU -> esito = giocatori.attaccoNosferatu(nomeAngelo);
            case VAMPIRO -> esito = giocatori.attaccoVampiro(nomeAngelo);
        }
        assertThat(esito).isEqualTo(RIUSCITO);
        verificaNonAmato(nomeAmato);
        verificaVero(giocatori.isTrattoPresente(nomeAngelo, NON_MORTO));
        assertThat(giocatori.getFazione(nomeAngelo)).isEqualTo(fazione);
    }

    private void verificaAccusati(String... soluzioni)
    {
        int numeroSoluzioni = soluzioni.length;
        Giocatori ballottaggio = getBallottaggio();
        verificaNumeroIntero(ballottaggio.getNumeroGiocatori(), numeroSoluzioni);
        for (int i = 0; i < numeroSoluzioni; i++) verificaGiocatoreAccusato(ballottaggio, i, soluzioni[i]);
    }

    private void verificaAttaccoAssassino(String nome, EsitoAttacco esito)
    {
        assertThat(giocatori.attaccoAssassino(nome)).isEqualTo(esito);
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

}