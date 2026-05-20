package alessandro.stamera.wherewolfserver.classi.gestione_partita;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Misticismo.NON_MISTICO;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static org.assertj.core.api.Assertions.*;

public final class TestPartita
{

    private static final String ERRORE_ROGO_SALTATO =
        "Il villaggio non ha trovato accordo su chi mandare al rogo: non viene bruciato nessuno!";

    private Partita partita;

    @Test public void testBallottaggioPuro()
    {
        String[][] giocatori = new String[][] { { "Giulio", "Pazzo" }, { "Cesare", "Peccatore" }, { "Augusto", "Prete" } };
        inizializzaPartita(giocatori);
        int[] numeroVoti = new int[] { 2, 1 };
        for(int i = 0; i < numeroVoti.length; i++) incrementaVoti(giocatori[i][0], numeroVoti[i]);
        terminaVotazioni();
        verificaAccusati(giocatori[0][0], giocatori[1][0]);
    }

    @Test public void testUnanimita()
    {
        String[][] giocatori = new String[][] { { "Annibale", "Guaritore" }, { "Rodolfo", "Assassino" } };
        inizializzaPartita(giocatori);
        int posizione = 0;
        incrementaVoti(giocatori[posizione][0], 3);
        terminaVotazioni();
        verificaAccusati(giocatori[posizione][0]);
    }

    @Test public void testPareggioPrimoPosto()
    {
        String[][] soluzioni = new String[][] { { "Gabriella", "Capo branco" }, { "Ezio", "Giullare" }, { "Marta", "Prete" } };
        inizializzaPartita(soluzioni);
        for(String[] soluzione : soluzioni) incrementaVoti(soluzione[0], 1);
        terminaVotazioni();
        verificaAccusati(soluzioni[0][0], soluzioni[1][0], soluzioni[2][0]);
    }

    @Test public void testPareggioSecondoPosto()
    {
        String[][] soluzioni = new String[][] { { "Aldo", "Pazzo" }, { "Giovanni", "Guaritore" }, { "Giacomo", "Leprecauno" } };
        inizializzaPartita(soluzioni);
        int[] numeroVoti = new int[]{ 2, 1, 1 };
        for(int i = 0; i < numeroVoti.length; i++) incrementaVoti(soluzioni[i][0], numeroVoti[i]);
        terminaVotazioni();
        verificaAccusati(soluzioni[0][0], soluzioni[1][0], soluzioni[2][0]);
    }

    @Test public void testAngeloCustodeAccusatoNonPresente()
    {
        String[][] giocatori = new String[][] { { "Domenico", "Angelo custode" }, { "Franco", "Goblin" }, { "Pamela", "Sidhe" } };
        inizializzaPartita(giocatori);
        int posizione = 2;
        segnalazioneAngeloCustode(giocatori[posizione][0]);
        incrementaVoti(giocatori[posizione][0], 3);
        terminaVotazioni();
        verificaAccusati(giocatori[0][0]);
        verificaNonAccusato(giocatori[posizione][0]);
    }

    @Test public void testAngeloCustodeAccusatoPresente()
    {
        String[][] giocatori = new String[][] { { "Michelle", "Angelo custode" }, { "Fiona", "Altra guardia" }, { "Biagio", "Ladra" } };
        inizializzaPartita(giocatori);
        int posizione = 1;
        segnalazioneAngeloCustode(giocatori[posizione][0]);
        for(String[] giocatore : giocatori) incrementaVoti(giocatore[0], 2);
        terminaVotazioni();
        verificaAccusati(giocatori[2][0], giocatori[0][0]);
        verificaNonAccusato(giocatori[posizione][0]);
    }

    @Test public void testAttaccoAssassino()
    {
        String[][] giocatori = new String[][] { { "Giovanni", "Assassino" }, { "Federico", "Lupo reietto" } };
        inizializzaPartita(giocatori);
        int posizione = 1;
        attaccoAssassino(giocatori[posizione][0]);
        terminaNotte();
        verificaEliminazione(giocatori[posizione][0]);
    }

    @Test public void testAttaccoAmatoAssassino()
    {
        String[][] giocatori = new String[][] { { "Enzo", "Angelo custode" }, { "Barbara", "Bardo" }, { "Maddalena", "Oste" } };
        int posizione = 1;
        inizializzaPartita(giocatori);
        segnalazioneAngeloCustode(giocatori[posizione][0]);
        attaccoAssassino(giocatori[posizione][0]);
        terminaNotte();
        verificaEliminazione(giocatori[0][0]);
        verificaVero(isVivo(giocatori[posizione][0]));
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Angelo custode", "Bardo", "Becchino", "Boia", "Bracconiere", "Cacciatore", "Cacciatore di vampiri", "Capo branco",
            "Cappuccetto rosso", "Contadino discendente dei lupi", "Contadino eroe", "Contadino mostro", "Contadino normale", "Eremita", "Ghoul",
            "Giulietta", "Giullare", "Goblin", "Guardia", "Guaritore", "Giovane lupo", "Inquisitore", "Leprecauno", "Lupo del branco",
            "Lupo reietto", "Lupo solitario", "Mago", "Medium", "Megera", "Monaco", "Negromante", "Nonna", "Nosferatu", "Pazzo", "Peccatore",
            "Posseduto", "Prete", "Sensitiva", "Sidhe", "Templare"
        }
    )
    public void testSegnalazioneAzzeccagarbugli(String ruolo)
    {
        String[][] giocatori = new String[][] { { "Matteo", ruolo }, { "Ivan", "Oratore" }, { "Miriam", "Assassino" } };
        inizializzaPartita(giocatori);
        segnalazioneAzzeccagarbugli(giocatori[0][0]);
        for(int i = 1; i < giocatori.length; i++) incrementaVoti(giocatori[i][0], 1);
        terminaVotazioni();
        verificaAccusati(giocatori[0][0], giocatori[1][0], giocatori[2][0]);
    }

    @Test public void testSegnalazioneAzzeccagarbugliAmato()
    {
        String[][] giocatori = new String[][]
            { { "Carmine", "Angelo custode" }, { "Carmela", "Contadino eroe" }, { "Virginio", "Inquisitore" }, { "Giorgia", "Giullare" } };
        int posizione1 = 3, posizione2 = 1;
        inizializzaPartita(giocatori);
        segnalazioneAzzeccagarbugli(giocatori[posizione1][0]);
        segnalazioneAngeloCustode(giocatori[posizione1][0]);
        incrementaVoti(giocatori[posizione2][0], 2);
        terminaVotazioni();
        verificaAccusati(giocatori[0][0], giocatori[posizione2][0]);
        verificaNonAccusato(giocatori[posizione1][0]);
    }

    @ParameterizedTest @CsvSource({ "Capo branco, Lupo del branco, Lupo reietto, Lupo solitario" })
    public void testAttaccoLupiAngeloCustode(String nomeLupo)
    {
        String[][] giocatori = new String[][] { { "Walter", "Mago" }, { "Amelia", "Spia" } };
        inizializzaPartita(giocatori);
        int posizione = 0;
        attaccoLupi(nomeLupo, giocatori[posizione][0]);
        terminaNotte();
        verificaEliminazione(giocatori[posizione][0]);
    }

    @ParameterizedTest @CsvSource({ "Capo branco, Lupo del branco, Lupo reietto, Lupo solitario" })
    public void testAttaccoLupiAmato(String nomeLupo)
    {
        String[][] giocatori = new String[][] { { "Fabrizio", "Bocca di rosa" }, { "Franca", "Peccatore" } };
        inizializzaPartita(giocatori);
        int posizione = 1;
        segnalazioneAngeloCustode(giocatori[posizione][0]);
        attaccoLupi(nomeLupo, giocatori[posizione][0]);
        terminaNotte();
        verificaNonEliminato(giocatori[posizione][0]);
    }

    @Test public void testSegnalazioneInquisitoreMisticoAssente()
    {
        String[][] giocatori = new String[][] { { "Merlino", "Mago" }, { "Lidia", "Inquisitore" }, { "Noemi", "Boia" } };
        inizializzaPartita(giocatori);
        int posizioneMistico = 0, posizioneVoto = 2;
        segnalazioneInquisitore(giocatori[posizioneMistico][0]);
        incrementaVoti(giocatori[posizioneVoto][0], 2);
        terminaVotazioni();
        verificaAccusati(giocatori[posizioneMistico][0], giocatori[posizioneVoto][0]);
    }

    @Test public void testSegnalazioneInquisitoreMisticoPresente()
    {
        String[][] giocatori = new String[][] { { "Alberto", "Guaritore" }, { "Tania", "Pazzo" } };
        inizializzaPartita(giocatori);
        int posizioneMistico = 0;
        segnalazioneInquisitore(giocatori[posizioneMistico][0]);
        for(String[] giocatore : giocatori) incrementaVoti(giocatore[0], 2);
        terminaVotazioni();
        verificaAccusati(giocatori[posizioneMistico][0], giocatori[1][0]);
    }

    @Test public void testSegnalazioneInquisitoreMisticoAssenteAmato()
    {
        String[][] giocatori = new String[][] { { "Elena", "Angelo custode" }, { "Irvano", "Medium" }, { "Luca", "Inquisitore" } };
        inizializzaPartita(giocatori);
        int posizioneMistico = 1, posizioneVoto = 2;
        segnalazioneInquisitore(giocatori[posizioneMistico][0]);
        segnalazioneAngeloCustode(giocatori[posizioneMistico][0]);
        incrementaVoti(giocatori[posizioneVoto][0], 2);
        terminaVotazioni();
        verificaAccusati(giocatori[posizioneVoto][0], giocatori[0][0]);
    }

    @Test public void testSegnalazioneInquisitoreMisticoPresenteAmato()
    {
        String[][] giocatori = new String[][] { { "Antonio", "Angelo custode" }, { "Davide", "Leprecauno" }, { "Matteo", "Inquisitore" } };
        inizializzaPartita(giocatori);
        int posizioneMistico = 1;
        segnalazioneInquisitore(giocatori[posizioneMistico][0]);
        segnalazioneAngeloCustode(giocatori[posizioneMistico][0]);
        incrementaVoti(giocatori[posizioneMistico][0], 2);
        terminaVotazioni();
        verificaAccusati(giocatori[0][0]);
    }

    @Test public void testAttaccoAssassinoContadinoMostroNottiSuccessive()
    {
        String[][] soluzioni = new String[][] { { "Pietro", "Assassino" }, { "Mario", "Contadino mostro" }, { "Maria", "Contadino eroe" } };
        inizializzaPartita(soluzioni);
        terminaNotte();
        attaccoAssassino(soluzioni[1][0]);
        terminaNotte();
        for(int i = 0; i < soluzioni.length - 1; i++) verificaEliminazione(soluzioni[i][0]);
    }

    @Test public void testSoloCreatureOmbra()
    {
        inizializzaPartita(new String[][] { { "Raffaele", "Nosferatu" }, { "Aurora", "Capo branco" } });
        verificaVero(partita.isSoloCreatureOmbra());
    }

    @Test public void testSoloGuardie()
    {
        inizializzaPartita(new String[][] { { "Sara", "Guardia" }, { "Elisa", "Altra guardia" } });
        verificaVero(partita.isSoloGuardie());
    }

    @Test public void testNoGuardie()
    {
        inizializzaPartita(new String[][] { { "Cristian", "Pazzo" }, { "Alessio", "Capo gilda" } });
        verificaVero(partita.isNoGuardie());
    }

    @Test public void testNoCreatureOmbra()
    {
        inizializzaPartita(new String[][] { { "Clark", "Angelo custode" }, { "Lois", "Azzeccagarbugli" }, { "Jonathan", "Contadino eroe" } });
        verificaVero(partita.isNoCreatureOmbra());
    }

    @Test public void testPotereBardoNienteBardo()
    {
        String[][] giocatori = new String[][] { { "Stefano", "Bardo" }, { "Francesco", "Guaritore" }, { "Adriano", "Mago" } };
        inizializzaPartita(giocatori);
        assertThat(partita.getControlloVeggente(giocatori[2][0])).isEqualTo(BIANCA);
        partita.attaccoLupi("Lupo del branco", giocatori[0][0]);
        terminaNotte();
        verificaNienteCantoBardo();
    }

    @Test public void testPotereBardoAuraChiara()
    {
        String[][] giocatori = new String[][] { { "Isabella", "Bardo" }, { "Otello", "Mago" } };
        inizializzaPartita(giocatori);
        verificaControlloVeggente(giocatori[1][0], BIANCA);
        verificaVero(getCantoBardo());
    }

    @Test public void testPotereBardoAuraOscura()
    {
        String[][] giocatori = new String[][] { { "Ezio", "Posseduto" }, { "Virginio", "Bardo" } };
        inizializzaPartita(giocatori);
        verificaControlloVeggente(giocatori[0][0], NERA);
        verificaNienteCantoBardo();
    }

    @Test public void testPerdenteBallottaggio()
    {
        String[][] giocatori = new String[][] { { "Davide", "Prete" }, { "Margherita", "Guardia" } };
        inizializzaPartita(giocatori);
        for(String[] giocatore : giocatori) incrementaVoti(giocatore[0], 1);
        terminaVotazioni();
        int[] numeroVoti = new int[] { 1, 2 };
        for(int i = 0; i < numeroVoti.length; i++) incrementaVoti(giocatori[i][0], numeroVoti[i]);
        terminaBallottaggio();
        terminaNotte();
        verificaEliminazione(giocatori[1][0]);
        verificaNonEliminato(giocatori[0][0]);
    }

    @Test public void testPareggioBallottaggio()
    {
        String[][] giocatori = new String[][]{ { "Francesco", "Capo branco" }, { "Luca", "Altra guardia" } };
        inizializzaPartita(giocatori);
        for(String[] giocatore : giocatori) incrementaVoti(giocatore[0], 2);
        terminaVotazioni();
        for(String[] giocatore : giocatori) incrementaVoti(giocatore[0], 1);
        terminaBallottaggio();
        terminaNotte();
        for(String[] giocatore : giocatori) verificaNonEliminato(giocatore[0]);
    }

    @ParameterizedTest @CsvSource({ "Bocca di rosa", "Azzeccagarbugli" }) public void testRogoAnnullatoOratore(String nomeRuolo)
    {
        String[][] giocatori = new String[][] { { "Mario", nomeRuolo }, { "Dina", "Negromante" }, { "Enrica", "Oratore" } };
        inizializzaPartita(giocatori);
        for(int i = 0; i < giocatori.length - 1; i++) incrementaVoti(giocatori[i][0], 2);
        terminaVotazioni();
        incrementaVoti(giocatori[0][0], 3);
        assertThatIllegalStateException().isThrownBy(this::terminaBallottaggio).withMessage(ERRORE_ROGO_SALTATO);
        terminaNotte();
        for(String[] giocatore : giocatori) verificaNonEliminato(giocatore[0]);
    }

    @Test public void testSegnalazioneOratore()
    {
        String[][] giocatori = new String[][] { { "Antonella", "Prete" }, { "Luca", "Peccatore" }, { "Margherita", "Azzeccagarbugli" } };
        inizializzaPartita(giocatori);
        for(String[] giocatore : giocatori) incrementaVoti(giocatore[0], 1);
        terminaVotazioni();
        for(int i = 0; i < giocatori.length - 1; i++) segnalazioneOratore(giocatori[i][0]);
        incrementaVoti(giocatori[1][0], 3);
        assertThatIllegalStateException().isThrownBy(this::terminaBallottaggio).withMessage(ERRORE_ROGO_SALTATO);
        terminaNotte();
        for(String[] giocatore : giocatori) verificaNonEliminato(giocatore[0]);
    }

    @Test public void testSegnalazioneOratoreNonRiuscita()
    {
        String[][] giocatori = new String[][] { { "Aldo", "Capo branco" }, { "Giovanni", "Lupo del branco" }, { "Giacomo", "Giovane lupo" } };
        inizializzaPartita(giocatori);
        for(String[] giocatore : giocatori) incrementaVoti(giocatore[0], 1);
        terminaVotazioni();
        int posizione = 2;
        segnalazioneOratore(giocatori[0][0]);
        incrementaVoti(giocatori[posizione][0], 3);
        terminaBallottaggio();
        terminaNotte();
        verificaEliminazione(giocatori[posizione][0]);
    }

    @ParameterizedTest @CsvSource({ "Mercante", "Contadino mostro" }) public void testPotereBorgomastro(String ruolo)
    {
        String[][] giocatori =
            new String[][] { { "Jacopo", "Borgomastro" }, { "Isra", "Angelo custode" }, { "Tania", ruolo }, { "Francesco", "Bocca di rosa" } };
        inizializzaPartita(giocatori);
        attaccoLupi("Capo branco", giocatori[3][0]);
        int posizione = 2;
        incrementaVoti(giocatori[1][0], 2);
        incrementaVoti(giocatori[posizione][0], 3);
        terminaVotazioni();
        verificaFalso(isSegnalazioneBorgomastroAvvenuta());
        partita.segnalazioneBorgomastro(giocatori[posizione][0]);
        verificaVero(isSegnalazioneBorgomastroAvvenuta());
        incrementaVoti(giocatori[posizione][0], 1);
        verificaNumeroIntero(FACTORY.getRuolo(giocatori[posizione][1]).getNumeroVoti(), 3);
    }

    @Test public void testPotereBracconiereUnLupo()
    {
        String[][] giocatori = new String[][] { { "Elisa", "Bracconiere" }, { "Edoardo", "Lupo del branco" }, { "Franca", "Giullare" } };
        inizializzaPartita(giocatori);
        segnalazioneBracconiere();
        int posizioneVittima = 2;
        assertThatIllegalStateException().isThrownBy(() -> attaccoLupi(giocatori[1][1], giocatori[posizioneVittima][0]))
            .withMessage("Potere del Bracconiere in corso. Proibito l'attacco dei lupi.");
        terminaNotte();
        verificaNonEliminato(giocatori[posizioneVittima][0]);
    }

    @Test public void testPotereBracconiereDueLupi()
    {
        String[][] giocatori =
            new String[][] { { "Giulia", "Capo branco" }, { "Federico", "Giovane lupo" }, { "Carmine", "Bracconiere" }, { "Luisa", "Prete" } };
        inizializzaPartita(giocatori);
        segnalazioneBracconiere();
        int posizioneVittima = 3;
        attaccoLupi(giocatori[0][1], giocatori[posizioneVittima][0]);
        terminaNotte();
        verificaEliminazione(giocatori[posizioneVittima][0]);
    }

    @Test public void testAttaccoLupoSolitarioCacciatore()
    {
        String lupo = "Lupo solitario", nomeLupo = "Katia", nomeCacciatore = "Valeria";
        inizializzaPartita(new String[][] { { nomeLupo, lupo }, { nomeCacciatore, "Cacciatore" }, { "Pino", "Prete" } });
        attaccoLupi(lupo, nomeCacciatore);
        terminaNotte();
        verificaEliminati(nomeLupo, nomeCacciatore);
    }

    @Test public void testAttaccoUltimoLupo()
    {
        String lupo = "Lupo reietto", nomeLupo = "Salvatore", nomeCacciatore = "Pietro";
        inizializzaPartita(new String[][] { { nomeLupo, lupo }, { nomeCacciatore, "Cacciatore" }, { "Cristina", "Leprecauno" } });
        attaccoLupi(lupo, nomeCacciatore);
        terminaNotte();
        verificaEliminati(nomeLupo, nomeCacciatore);
    }

    @Test public void testAttaccoUltimoLupoBranco()
    {
        String lupo = "Lupo del branco", nomeLupo = "Pasquale", nomeCacciatore = "Gregorio";
        inizializzaPartita(new String[][] { { nomeLupo, lupo }, { nomeCacciatore, "Cacciatore" }, { "Cristina", "Leprecauno" } });
        attaccoLupi(lupo, nomeCacciatore);
        terminaNotte();
        verificaEliminati(nomeLupo, nomeCacciatore);
    }

    @Test public void testAttaccoNormaleCacciatore()
    {
        String lupo = "Lupo del branco", nomeLupo = "Biagio", nomeCacciatore = "Francesco";
        inizializzaPartita(new String[][] { { nomeLupo, lupo }, { nomeCacciatore, "Cacciatore" }, { "Cristina", "Giovane lupo" } });
        attaccoLupi(lupo, nomeCacciatore);
        terminaNotte();
        verificaEliminazione(nomeCacciatore);
        verificaNonEliminato(lupo);
    }

    @Test public void testAttaccoNosferatuMorto()
    {
        String nomeVittima = "Gianmaria", nomeNosferatu = "Augusta", tipoLupo = "Lupo del branco";
        inizializzaPartita(new String[][] { { "Augusta", "Nosferatu" }, { nomeVittima, "Cacciatore di vampiri" }, { "Raimondo", tipoLupo } });
        attaccoLupi(tipoLupo, nomeVittima);
        progenizzazioneNosferatu(nomeVittima);
        terminaNotte();
        verificaEliminati(nomeVittima, nomeNosferatu);
    }

    @Test public void testNumeroSenzaFazione()
    {
        inizializzaPartita(new String[][] { { "Raffaello", "Ghoul" }, { "Mattia", "Peccatore" }, { "Leonardo", "Pazzo" } });
        verificaNumeroIntero(partita.getNumeroSenzaFazioneVivi(), 2);
    }

    @Test public void testNumeroLupi()
    {
        inizializzaPartita(new String[][] { { "Aurora", "Lupo del branco" }, { "Elisa", "Lupo reietto" }, { "Mohamed", "Capo branco" } });
        verificaNumeroIntero(partita.getNumeroLupiVivi(), 3);
    }

    @ParameterizedTest @CsvSource({ "Contadino eroe", "Contadino mostro" })
    public void testAttaccoContadino(String tipoContadino)
    {
        String nomeVittima = "Caio", nomeLupo = "Tizio", tipoLupo = "Capo branco";
        inizializzaPartita(new String[][] { { nomeLupo, tipoLupo }, { nomeVittima, tipoContadino }, { "Sempronio", "Peccatore" } });
        attaccoLupi(tipoLupo, nomeVittima);
        terminaNotte();
        verificaEliminati(nomeVittima, nomeLupo);
    }

    @Test public void testAttaccoNosferatuRiuscito()
    {
        String nome = "Marco";
        inizializzaPartita(new String[][] { { nome, "Prete" }, { "Tina", "Nosferatu" } });
        attaccoLupi("Capo branco", nome);
        progenizzazioneNosferatu(nome);
        terminaNotte();
        verificaNonEliminato(nome);
    }

    @Test public void testSuicidioCapoBranco()
    {
        String[][] giocatori = new String[][] { { "Marco", "Capo branco" }, { "Luca", "Nosferatu" } };
        inizializzaPartita(giocatori);
        attaccoLupi(giocatori[0][1], giocatori[0][0]);
        progenizzazioneNosferatu(giocatori[0][0]);
        terminaNotte();
        verificaEliminati(giocatori[1][0]);
        verificaNonEliminato(giocatori[0][0]);
    }

    @Test public void testBloccoAttaccoPazzo()
    {
        String tipoLupo = "Lupo reietto", nomePazzo = "Angel", nomeVittima = "Xander";
        inizializzaPartita(new String[][] { { "Spike", "Lupo reietto" }, { nomePazzo, "Pazzo" }, { "Xander", "Giullare" } });
        attaccoLupi(tipoLupo, nomePazzo);
        terminaNotte();
        verificaEliminazione(nomePazzo);
        assertThatIllegalStateException().isThrownBy(() -> attaccoLupi(tipoLupo, nomeVittima))
            .withMessage("Il Pazzo è morto. L'attacco dei lupi non può essere eseguito.");
        verificaNonEliminato(nomeVittima);
    }

    @ParameterizedTest @CsvSource({ "Lupo del branco", "Giovane lupo", "Lupo reietto", "Lupo solitario" })
    public void attaccoAltriLupi(String tipoLupo)
    {
        String lupoAttaccante = "Capo branco", nomeVittima = "Mattia";
        inizializzaPartita(new String[][] { { "Andrea", lupoAttaccante }, { nomeVittima, tipoLupo } });
        attaccoLupi(lupoAttaccante, nomeVittima);
        terminaNotte();
        verificaNonEliminato(nomeVittima);
    }

    @Test public void testInizioCrociata()
    {
        String tipoLupo = "Capo branco", nomeVittima = "Chloe";
        inizializzaPartita(new String[][] { { "Yorgos", tipoLupo }, { "James", "Inquisitore" }, { nomeVittima, "Templare" } });
        attaccoLupi(tipoLupo, nomeVittima);
        terminaNotte();
        verificaEliminati(nomeVittima);
        verificaVero(isCrociataAvviata());
    }

    @Test public void testMancatoInizioCrociata()
    {
        String tipoLupo = "Capo branco", nomeVittima = "Eve";
        inizializzaPartita(new String[][] { { "Daniel", tipoLupo }, { "Wesley", "Inquisitore" }, { nomeVittima, "Goblin" } });
        attaccoLupi(tipoLupo, nomeVittima);
        terminaNotte();
        verificaEliminati(nomeVittima);
        verificaFalso(isCrociataAvviata());
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Angelo custode, 1", "Azzeccagarbugli, 2", "Bardo, 2", "Becchino, 2", "Bocca di rosa, 2", "Borgomastro, 2", "Bracconiere, 2",
            "Cacciatore, 2", "Cacciatore di vampiri, 2", "Cappuccetto rosso, 2", "Eremita, 2", "Ghoul, 1", "Giulietta, 1", "Giullare, 1",
            "Goblin, 1", "Guaritore, 2", "Inquisitore, 1", "Leprecauno, 1", "Mago, 2", "Medium, 2", "Megera, 1", "Mercante, 2", "Monaco, 2",
            "Negromante, 1", "Nonna, 2", "Nosferatu, 1", "Oratore, 2", "Pazzo, 1", "Oste, 2", "Peccatore, 2", "Posseduto, 1", "Prete, 2",
            "Sensitiva, 2", "Sidhe, 1", "Templare, 1"
        }
    )
    public void testCriminalizzazioneCapoGilda(String nomeRuolo, int numeroCriminali)
    {
        String nomeVittima = "Antonio";
        inizializzaPartita(new String[][] { { nomeVittima, nomeRuolo }, { "Davide", "Capo gilda" } });
        gildata(nomeVittima);
        verificaNumeroCriminali(numeroCriminali);
    }

    @ParameterizedTest
    @CsvSource({ "Altra guardia", "Capo branco", "Giovane lupo", "Guardia", "Lupo del branco", "Lupo reietto", "Lupo solitario" })
    public void testCriminalizzazioneCapoGildaMorto(String nomeRuolo)
    {
        String nomeVittima = "Arturo", nomeCapoGilda = "Raffaele";
        inizializzaPartita(new String[][] { { nomeVittima, nomeRuolo }, { nomeCapoGilda, "Capo gilda" } });
        gildata(nomeVittima);
        terminaNotte();
        verificaEliminazione(nomeCapoGilda);
    }

    @Test public void testCriminalizzazioneBecchino()
    {
        String nomeVittima = "Giulia";
        inizializzaPartita(new String[][] { { nomeVittima, "Becchino" }, { "Tania", "Capo gilda" } });
        partita.riconosciNegromante();
        gildata(nomeVittima);
        verificaNumeroCriminali(1);
    }

    @Test public void testCriminalizzazioneContadinoLupo()
    {
        String tipoLupo = "Lupo del branco", nomeVittima = "Alberto", nomeCapoGilda = "Andrea";
        inizializzaPartita
        (
            new String[][] { { "Sara", tipoLupo }, { nomeVittima, "Contadino discendente dei lupi" }, { nomeCapoGilda, "Capo gilda" } }
        );
        attaccoLupi(tipoLupo, nomeVittima);
        gildata(nomeVittima);
        terminaNotte();
        verificaEliminazione(nomeCapoGilda);
    }

    @Test public void testCriminalizzazioneContadinoMostro()
    {
        String nomeVittima = "Alberto", nomeCapoGilda = "Andrea";
        inizializzaPartita(new String[][] { { nomeVittima, "Contadino mostro" }, { nomeCapoGilda, "Capo gilda" } });
        gildata(nomeVittima);
        verificaNumeroCriminali(1);
        terminaNotte();
        verificaEliminazione(nomeCapoGilda);
    }

    @ParameterizedTest
    @CsvSource({ "Capo branco", "Lupo del branco", "Lupo reietto", "Lupo solitario", "Contadino discendente dei lupi" })
    public void testNessunaProtezioneCappuccettoRosso(String tipoLupo)
    {
        String nomeVittima = "Elena";
        inizializzaPartita(new String[][] { { nomeVittima, "Cappuccetto rosso" }, { "Andrea", tipoLupo } });
        attaccoLupi(tipoLupo, nomeVittima);
        terminaNotte();
        verificaEliminazione(nomeVittima);
    }

    @ParameterizedTest
    @CsvSource({ "Capo branco", "Lupo del branco", "Lupo reietto", "Lupo solitario", "Contadino discendente dei lupi" })
    public void testNessunaProtezioneCappuccettoRossoMorteNonna(String tipoLupo)
    {
        String nomeNonna = "Manfredi", nomeCappuccettoRosso = "Pina";
        inizializzaPartita(new String[][] { { nomeNonna, "Nonna" }, { "Damiano", tipoLupo }, { nomeCappuccettoRosso, "Cappuccetto rosso" } });
        incrementaVoti(nomeNonna, 3);
        terminaVotazioni();
        incrementaVoti(nomeNonna, 2);
        terminaBallottaggio();
        attaccoLupi(tipoLupo, nomeCappuccettoRosso);
        terminaNotte();
        verificaEliminati(nomeNonna, nomeCappuccettoRosso);
    }

    @ParameterizedTest
    @CsvSource({ "Capo branco", "Lupo del branco", "Lupo reietto", "Lupo solitario", "Contadino discendente dei lupi" })
    public void testProtezioneCappuccettoRosso(String tipoLupo)
    {
        String nomeCappuccettoRosso = "Claudia";
        inizializzaPartita(new String[][] { { "Salvatore", "Nonna" }, { "Noemi", tipoLupo }, { nomeCappuccettoRosso, "Cappuccetto rosso" } });
        attaccoLupi(tipoLupo, nomeCappuccettoRosso);
        terminaNotte();
        verificaNonEliminato(nomeCappuccettoRosso);
    }

    @ParameterizedTest @CsvSource({ "Capo branco", "Lupo del branco", "Lupo reietto", "Lupo solitario" })
    public void testGuarigioneContadinoMostro(String tipoLupo)
    {
        String nomeContadino = "Graziano", nomeGuaritore = "Perla", nomeLupo = "Leonardo";
        inizializzaPartita(new String[][] { { nomeContadino, "Contadino mostro" }, { nomeLupo, tipoLupo }, { nomeGuaritore, "Guaritore" } });
        attaccoLupi(tipoLupo, nomeContadino);
        partita.guarisci(nomeContadino);
        terminaNotte();
        verificaEliminati(nomeGuaritore, nomeLupo);
        verificaNonEliminato(nomeContadino);
    }

    @ParameterizedTest @CsvSource({ "1, 2" }) public void testContrattaccoContadinoMostro(int posizioneVittima)
    {
        String[][] giocatori = new String[][] { { "Carmine", "Contadino mostro" }, { "Carmela", "Peccatore" }, { "Giulia", "Posseduto" } };
        inizializzaPartita(giocatori);
        int posizione = 0;
        incrementaVoti(giocatori[posizione][0], 2);
        terminaVotazioni();
        for(int i = 1; i < giocatori.length; i++) partita.incrementaVotiContadinoMostro(giocatori[i][0]);
        terminaBallottaggio();
        String[] votantiContadinoMostro = partita.getVotatiContadinoMostro();
        for(int i = 1; i < giocatori.length; i++) assertThat(votantiContadinoMostro).contains(giocatori[i][0]);
        partita.contrattaccoContadinoMostro(giocatori[posizioneVittima][0]);
        verificaEliminazione(giocatori[posizioneVittima][0]);
        assertThat(partita.getVotatiContadinoMostro()).isEmpty();
    }

    @Test public void testNumeroNotte()
    {
        inizializzaPartita(new String[][]{ });
        verificaNumeroNotte(1);
        terminaNotte();
        verificaNumeroNotte(2);
    }

    @Test public void testAssassinioContadinoMostroPrimaNotte()
    {
        String nomeAssassino = "Maria", nomeVittima = "Giuseppe";
        inizializzaPartita(new String[][] { { nomeAssassino, "Assassino" }, { nomeVittima, "Contadino mostro" } });
        partita.attaccoAssassino(nomeVittima);
        terminaNotte();
        verificaEliminazione(nomeVittima);
        verificaNonEliminato(nomeAssassino);
    }

    @Test public void testControlloMagoContadinoMostroPrimaNotte()
    {
        String[][] giocatori = new String[][] { { "Harry", "Mago" }, { "Hagrid", "Contadino mostro" } };
        inizializzaPartita(giocatori);
        assertThat(partita.controlloMago(giocatori[1][0])).isEqualTo(NON_MISTICO);
        for(String[] giocatore : giocatori) verificaNonEliminato(giocatore[0]);
    }

    private void verificaNumeroNotte(int numeroNotte) { verificaNumeroIntero(partita.getNumeroNotte(), numeroNotte); }

    private void verificaNumeroCriminali(int risultato) { verificaNumeroIntero(partita.getNumeroCriminali(), risultato); }

    private void gildata(String nome) { partita.gildata(nome); }

    private boolean isCrociataAvviata() { return partita.isCrociataAvviata(); }

    private void progenizzazioneNosferatu(String giocatori) { partita.progenizzazioneNosferatu(giocatori); }

    private void terminaNotte() { partita.terminaNotte(); }

    private void verificaNumeroIntero(int valore, int risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaEliminati(String... nomi) { for(String nome : nomi) verificaEliminazione(nome); }

    private void segnalazioneBracconiere() { partita.segnalazioneBracconiere(); }

    private boolean isSegnalazioneBorgomastroAvvenuta() { return partita.segnalazioneBorgomastroAvvenuta(); }

    private void segnalazioneOratore(String nome) { partita.segnalazioneOratore(nome); }

    private void terminaBallottaggio() { partita.terminaBallottaggio(); }

    private void verificaNonEliminato(String nome) { verificaFalso(partita.isEliminato(nome)); }

    private void verificaControlloVeggente(String nome, Aura aura)
    {
        assertThat(partita.getControlloVeggente(nome)).isEqualTo(aura);
    }

    private void verificaNienteCantoBardo() { verificaFalso(getCantoBardo()); }

    private boolean getCantoBardo() { return partita.getCantoBardo(); }

    private void inizializzaPartita(String[][] giocatori) { partita = new Partita(giocatori); }

    private void incrementaVoti(String nome, int numeroVoti) { partita.incrementaVoti(nome, numeroVoti); }

    private void terminaVotazioni() { partita.terminaVotazioni(); }

    private void verificaAccusati(String... nomi) { for(String nome : nomi) verificaVero(isAccusato(nome)); }

    private void verificaNonAccusato(String nome) { verificaFalso(isAccusato(nome)); }

    private boolean isAccusato(String nome) { return partita.isAccusato(nome); }

    private void segnalazioneAngeloCustode(String nome) { partita.segnalazioneAngeloCustode(nome); }

    private void attaccoAssassino(String nome) { partita.attaccoAssassino(nome); }

    private void verificaEliminazione(String nome)
    {
        verificaVero(partita.isEliminato(nome));
        verificaFalso(isVivo(nome));
    }

    private boolean isVivo(String nome) { return partita.isVivo(nome); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

    private void segnalazioneAzzeccagarbugli(String nome) { partita.segnalazioneAzzeccagarbugli(nome); }

    private void attaccoLupi(String nomeLupo, String nome) { partita.attaccoLupi(nomeLupo, nome); }

    private void segnalazioneInquisitore(String nome) { partita.segnalazioneInquisitore(nome); }

}