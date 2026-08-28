package alessandro.stamera.wherewolfserver.classi.gestione_partita;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Misticismo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoControlloSensitiva.VILLAGGIO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Misticismo.MISTICO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Misticismo.NON_MISTICO;
import static java.util.Arrays.stream;
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
        String nome = "Pamela";
        String[][] giocatori = new String[][] { { "Domenico", "Angelo custode" }, { "Franco", "Goblin" }, { nome, "Sidhe" } };
        inizializzaPartita(giocatori);
        segnalazioneAngeloCustode(nome);
        incrementaVoti(nome, 3);
        terminaVotazioni();
        verificaAccusati(giocatori[0][0]);
        verificaNonAccusato(nome);
    }

    @Test public void testAngeloCustodeAccusatoPresente()
    {
        String nomeAmato = "Fiona";
        String[][] giocatori = new String[][] { { "Michelle", "Angelo custode" }, { nomeAmato, "Altra guardia" }, { "Biagio", "Ladra" } };
        inizializzaPartita(giocatori);
        segnalazioneAngeloCustode(nomeAmato);
        for(String[] giocatore : giocatori) incrementaVoti(giocatore[0], 2);
        terminaVotazioni();
        verificaAccusati(giocatori[2][0], giocatori[0][0]);
        verificaNonAccusato(nomeAmato);
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

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro", "Bracconiere", "Cacciatore",
            "Cacciatore di vampiri", "Capo branco", "Capo gilda", "Cappuccetto rosso", "Contadino eroe", "Contadino discendente dei lupi",
            "Contadino normale", "Eremita", "Ghoul", "Giovane lupo", "Giulietta", "Giullare", "Goblin", "Guardia", "Guardia corrotta", "Guaritore",
            "Inquisitore", "Ladra", "Leprecauno", "Lupo del branco", "Lupo reietto", "Lupo solitario", "Mago", "Medium", "Megera", "Mercante",
            "Monaco", "Negromante", "Nonna", "Nosferatu", "Oratore", "Oste", "Pazzo", "Peccatore", "Posseduto", "Prete", "Sidhe", "Spia",
            "Sensitiva", "Templare", "Vampiro"
        }
    )
    public void testAttaccoAmatoAssassino(String nomeRuolo)
    {
        String nomeAngelo = "Enzo", nomeAssassino = "Barbara", nomeVittima = "Maddalena";
        inizializzaPartita(new String[][] { { nomeAngelo, "Angelo custode" }, { nomeAssassino, "Assassino" }, { nomeVittima, nomeRuolo } });
        verificaAttaccoAssassinoAmato(nomeVittima, nomeAssassino, nomeAngelo);
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro", "Bracconiere", "Cacciatore",
            "Cacciatore di vampiri", "Capo branco", "Capo gilda", "Cappuccetto rosso", "Contadino eroe", "Contadino discendente dei lupi",
            "Contadino normale", "Eremita", "Ghoul", "Giovane lupo", "Giulietta", "Giullare", "Goblin", "Guardia", "Guardia corrotta", "Guaritore",
            "Inquisitore", "Ladra", "Leprecauno", "Lupo del branco", "Lupo reietto", "Lupo solitario", "Mago", "Medium", "Megera", "Mercante",
            "Monaco", "Negromante", "Nonna", "Nosferatu", "Oratore", "Oste", "Pazzo", "Peccatore", "Posseduto", "Prete", "Sidhe", "Spia",
            "Sensitiva", "Templare", "Vampiro"
        }
    )
    public void testGuarigioneAngeloPostAttaccoAmato(String nomeRuolo)
    {
        String nomeAngelo = "Enzo", nomeAssassino = "Barbara", nomeVittima = "Maddalena";
        inizializzaPartita(new String[][] { { nomeAngelo, "Angelo custode" }, { nomeAssassino, "Assassino" }, { nomeVittima, nomeRuolo } });
        segnalazioneAngeloCustode(nomeVittima);
        String messaggio =
            "L'attacco dell'amato (Maddalena) da parte dell'Assassino (Barbara) causa la morte del suo Angelo custode (Enzo).\nAvvisa Enzo " +
            "dell'attacco subito.";
        assertThatIllegalStateException().isThrownBy(() -> attaccoAssassino(nomeVittima)).withMessage(messaggio);
        guarisci(nomeAngelo);
        terminaNotte();
        verificaNonEliminati(nomeAngelo, nomeVittima);
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro", "Bracconiere", "Cacciatore",
            "Cacciatore di vampiri", "Capo branco", "Capo gilda", "Cappuccetto rosso", "Contadino eroe", "Contadino discendente dei lupi",
            "Contadino normale", "Eremita", "Ghoul", "Giovane lupo", "Giulietta", "Giullare", "Goblin", "Guardia", "Guardia corrotta", "Guaritore",
            "Inquisitore", "Ladra", "Leprecauno", "Lupo del branco", "Lupo reietto", "Lupo solitario", "Mago", "Medium", "Megera", "Mercante",
            "Monaco", "Negromante", "Nonna", "Nosferatu", "Oratore", "Oste", "Pazzo", "Peccatore", "Posseduto", "Prete", "Sidhe", "Spia",
            "Sensitiva", "Templare", "Vampiro"
        }
    )
    public void testAttaccoAmatoRomeoAssassino(String nomeRuolo)
    {
        String nomeAngelo = "Enzo", nomeAssassino = "Barbara", nomeVittima = "Maddalena";
        inizializzaPartita(new String[][] { { nomeAngelo, "Angelo custode" }, { nomeAssassino, "Assassino" }, { nomeVittima, nomeRuolo } });
        //romeizzazione(nomeVittima);
        verificaAttaccoAssassinoAmato(nomeVittima, nomeAssassino, nomeAngelo);
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro", "Bracconiere", "Cacciatore",
            "Cacciatore di vampiri", "Capo branco", "Capo gilda", "Cappuccetto rosso", "Contadino eroe", "Contadino discendente dei lupi",
            "Contadino normale", "Eremita", "Ghoul", "Giovane lupo", "Giulietta", "Giullare", "Goblin", "Guardia", "Guardia corrotta", "Guaritore",
            "Inquisitore", "Ladra", "Leprecauno", "Lupo del branco", "Lupo reietto", "Lupo solitario", "Mago", "Medium", "Megera", "Mercante",
            "Monaco", "Negromante", "Nonna", "Nosferatu", "Oratore", "Oste", "Pazzo", "Peccatore", "Posseduto", "Prete", "Sidhe", "Spia",
            "Sensitiva", "Templare", "Vampiro"
        }
    )
    public void testAttaccoAmatoStregatoAssassino(String nomeRuolo)
    {
        String nomeAngelo = "Enzo", nomeAssassino = "Barbara", nomeVittima = "Maddalena";
        String[][] giocatori =
            new String[][] { { "Willow", "Strega" }, { nomeAngelo, "Angelo custode" }, { nomeAssassino, "Assassino" }, { nomeVittima, nomeRuolo } };
        inizializzaPartita(giocatori);
        protezioneStrega(nomeVittima);
        verificaAttaccoAssassinoAmato(nomeVittima, nomeAssassino, nomeAngelo);
    }

    private void verificaAttaccoAssassinoAmato(String nomeVittima, String nomeAssassino, String nomeAngelo)
    {
        segnalazioneAngeloCustode(nomeVittima);
        String messaggio =
            "L'attacco dell'amato (" + nomeVittima + ") da parte dell'Assassino (" + nomeAssassino + ") causa la morte del suo Angelo custode (" +
            nomeAngelo + ").\nAvvisa " + nomeAngelo + " dell'attacco subito.";
        assertThatIllegalStateException().isThrownBy(() -> attaccoAssassino(nomeVittima)).withMessage(messaggio);
        terminaNotte();
        verificaEliminazione(nomeAngelo);
        verificaNonEliminati(nomeVittima);
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Angelo custode", "Bardo", "Becchino", "Boia", "Bracconiere", "Cacciatore", "Cacciatore di vampiri", "Capo branco",
            "Cappuccetto rosso", "Contadino eroe", "Contadino mostro", "Contadino normale", "Eremita", "Ghoul", "Giulietta", "Giullare", "Goblin",
            "Guardia", "Guaritore", "Giovane lupo", "Inquisitore", "Leprecauno", "Lupo del branco", "Lupo reietto", "Lupo solitario", "Mago",
            "Medium", "Megera", "Monaco", "Negromante", "Nonna", "Nosferatu", "Pazzo", "Peccatore", "Posseduto", "Prete", "Sensitiva", "Sidhe",
            "Templare"
        }
    )
    public void testSegnalazioneAzzeccagarbugli(String ruolo)
    {
        String nome = "Matteo";
        String[][] giocatori = new String[][] { { nome, ruolo }, { "Ivan", "Oratore" }, { "Miriam", "Assassino" } };
        inizializzaPartita(giocatori);
        partita.segnalazioneAzzeccagarbugli(nome);
        for(int i = 1; i < giocatori.length; i++) incrementaVoti(giocatori[i][0], 1);
        terminaVotazioni();
        verificaAccusati(nome, giocatori[1][0], giocatori[2][0]);
    }

    @Test public void testSegnalazioneAzzeccagarbugliAmato()
    {
        String nomeAmato = "Carmela", nomeVittima = "Virginio";
        String[][] giocatori = new String[][]
            { { "Carmine", "Angelo custode" }, { nomeAmato, "Contadino eroe" }, { nomeVittima, "Inquisitore" }, { "Giorgia", "Giullare" } };
        inizializzaPartita(giocatori);
        partita.segnalazioneAzzeccagarbugli(nomeAmato);
        segnalazioneAngeloCustode(nomeAmato);
        incrementaVoti(nomeVittima, 2);
        terminaVotazioni();
        verificaAccusati(giocatori[0][0], nomeVittima);
        verificaNonAccusato(nomeAmato);
    }

    @ParameterizedTest @CsvSource({ "Capo branco", "Lupo del branco", "Lupo reietto", "Lupo solitario" })
    public void testAttaccoLupiAngeloCustode(String nomeLupo)
    {
        String nomeAngeloCustode = "Walter";
        inizializzaPartita(new String[][] { { nomeAngeloCustode, "Angelo custode" }, { "Amelia", "Spia" }, { "Tony", nomeLupo } });
        attaccoLupi(nomeLupo, nomeAngeloCustode);
        terminaNotte();
        verificaEliminazione(nomeAngeloCustode);
    }

    @ParameterizedTest @CsvSource({ "Capo branco", "Lupo del branco", "Lupo reietto", "Lupo solitario" })
    public void testAttaccoLupiAngeloCustodeRomeizzato(String tipoLupo)
    {
        String nome = "Luca", nomeLupo = "Mario";
        inizializzaPartita(new String[][] { { nome, "Angelo custode" }, { nomeLupo, tipoLupo }, { "Lucio", "Giulietta" } });
        romeizzazione(nome);
        String messaggio = "Luca non muore perché Romeo.\nAvvisa i lupi della sua mancata morte.";
        verificaAttaccoLupiAngeloCustodeFallito(tipoLupo, nome, messaggio);
    }

    @ParameterizedTest @CsvSource({ "Capo branco", "Lupo del branco", "Lupo reietto", "Lupo solitario" })
    public void testAttaccoLupiAngeloCustodeStregato(String tipoLupo)
    {
        String nome = "Gregorio";
        inizializzaPartita(new String[][] { { nome, "Angelo custode"}, { "Vinicio", "Strega" }, { "Francesca", tipoLupo } });
        protezioneStrega(nome);
        String messaggio = "Gregorio non muore perché protetto dalla Strega.\nAvvisa i lupi della sua mancata morte.";
        verificaAttaccoLupiAngeloCustodeFallito(tipoLupo, nome, messaggio);
    }

    @ParameterizedTest @MethodSource("getEsempioAttaccoAmato")
    public void testAttaccoLupiAmato(String tipoLupo, String nomeRuolo, String messaggio)
    {
        String nomeAngeloCustode = "Erode", nomeAmato = "Giuseppe";
        inizializzaPartita(new String[][] { { nomeAngeloCustode, "Angelo custode" }, { "Maria", tipoLupo }, { nomeAmato, nomeRuolo } });
        segnalazioneAngeloCustode(nomeAmato);
        verificaAmato(nomeAmato);
        assertThatIllegalStateException().isThrownBy(() -> attaccoLupi(tipoLupo, nomeAmato)).withMessage(messaggio);
        terminaNotte();
        verificaNonEliminati(nomeAmato);
        verificaEliminazione(nomeAngeloCustode);
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

    @ParameterizedTest
    @CsvSource({ "Goblin", "Guaritore", "Leprecauno", "Mago", "Medium", "Megera", "Negromante", "Sensitiva", "Sidhe" })
    public void testSegnalazioneInquisitoreMisticoPresente(String nomeRuolo)
    {
        String nomeMistico = "Alberto";
        String[][] giocatori = new String[][] { { nomeMistico, nomeRuolo }, { "Tania", "Pazzo" } };
        inizializzaPartita(giocatori);
        segnalazioneInquisitore(nomeMistico);
        for(String[] giocatore : giocatori) incrementaVoti(giocatore[0], 2);
        terminaVotazioni();
        verificaAccusati(nomeMistico, giocatori[1][0]);
    }

    @Test public void testSegnalazioneInquisitoreMisticoAssenteAmato()
    {
        String nome = "Irvano";
        String[][] giocatori = new String[][] { { "Elena", "Angelo custode" }, { nome, "Medium" }, { "Luca", "Inquisitore" } };
        inizializzaPartita(giocatori);
        int posizioneVoto = 2;
        segnalazioneInquisitore(nome);
        segnalazioneAngeloCustode(nome);
        incrementaVoti(giocatori[posizioneVoto][0], 2);
        terminaVotazioni();
        verificaAccusati(giocatori[posizioneVoto][0], giocatori[0][0]);
    }

    @ParameterizedTest
    @CsvSource({ "Goblin", "Guaritore", "Leprecauno", "Mago", "Medium", "Megera", "Negromante", "Sensitiva", "Sidhe" })
    public void testSegnalazioneInquisitoreMisticoPresenteAmato(String nomeRuolo)
    {
        String nomeSegnalato = "Davide";
        String[][] giocatori = new String[][] { { "Antonio", "Angelo custode" }, { nomeSegnalato, nomeRuolo }, { "Matteo", "Inquisitore" } };
        inizializzaPartita(giocatori);
        segnalazioneInquisitore(nomeSegnalato);
        segnalazioneAngeloCustode(nomeSegnalato);
        incrementaVoti(nomeSegnalato, 2);
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
        String tipoLupo = "Lupo del branco";
        String[][] giocatori =
            new String[][] { { "Stefano", "Bardo" }, { "Francesco", "Guaritore" }, { "Adriano", "Mago" }, { "Cristian", tipoLupo } };
        inizializzaPartita(giocatori);
        verificaControlloVeggente(giocatori[2][0], BIANCA);
        attaccoLupi(tipoLupo, giocatori[0][0]);
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
        verificaNonEliminati(giocatori[0][0]);
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
        verificaNonEliminati(estraiNomiGiocatori(giocatori));
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
        verificaNonEliminati(estraiNomiGiocatori(giocatori));
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
        verificaNonEliminati(estraiNomiGiocatori(giocatori));
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
        String tipoLupo = "Capo branco";
        String[][] giocatori = new String[][]
        {
            { "Jacopo", "Borgomastro" }, { "Isra", "Angelo custode" }, { "Tania", ruolo }, { "Francesco", "Bocca di rosa" }, { "Alex", tipoLupo }
        };
        inizializzaPartita(giocatori);
        attaccoLupi(tipoLupo, giocatori[3][0]);
        int posizione = 2;
        incrementaVoti(giocatori[1][0], 2);
        incrementaVoti(giocatori[posizione][0], 3);
        terminaVotazioni();
        verificaFalso(isSegnalazioneBorgomastroAvvenuta());
        partita.segnalazioneBorgomastro(giocatori[posizione][0]);
        verificaVero(isSegnalazioneBorgomastroAvvenuta());
        incrementaVoti(giocatori[posizione][0], 1);
        //verificaNumeroIntero(FACTORY.getRuolo(giocatori[posizione][1]).getNumeroVoti(), 3);
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
        verificaNonEliminati(giocatori[posizioneVittima][0]);
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

    /*@Test public void testAttaccoLupoSolitarioCacciatore()
    {
        String lupo = "Lupo solitario", nomeLupo = "Katia", nomeCacciatore = "Valeria";
        inizializzaPartita(new String[][] { { nomeLupo, lupo }, { nomeCacciatore, "Cacciatore" }, { "Pino", "Prete" } });
        attaccoLupi(lupo, nomeCacciatore);
        terminaNotte();
        verificaEliminati(nomeLupo, nomeCacciatore);
    }*/

    @Test public void testAttaccoUltimoLupoBranco()
    {
        String lupo = "Lupo del branco", nomeLupo = "Pasquale", nomeCacciatore = "Gregorio";
        inizializzaPartita(new String[][] { { nomeLupo, lupo }, { nomeCacciatore, "Cacciatore" }, { "Cristina", "Leprecauno" } });
        attaccoLupi(lupo, nomeCacciatore);
        terminaNotte();
        verificaNonEliminati(nomeLupo, nomeCacciatore);
    }

    @Test public void testAttaccoNormaleCacciatore()
    {
        String lupo = "Lupo del branco", nomeLupo = "Biagio", nomeCacciatore = "Francesco";
        inizializzaPartita(new String[][] { { nomeLupo, lupo }, { nomeCacciatore, "Cacciatore" }, { "Cristina", "Giovane lupo" } });
        attaccoLupi(lupo, nomeCacciatore);
        terminaNotte();
        verificaEliminazione(nomeCacciatore);
        verificaNonEliminati(lupo);
    }

    @Test public void testAttaccoNosferatuMorto()
    {
        String nomeVittima = "Gianmaria", nomeNosferatu = "Augusta", tipoLupo = "Lupo del branco";
        inizializzaPartita(new String[][] { { "Augusta", "Nosferatu" }, { nomeVittima, "Cacciatore di vampiri" }, { "Raimondo", tipoLupo } });
        attaccoLupi(tipoLupo, nomeVittima);
        String messaggio = "Impossibile progenizzare il Cacciatore di vampiri (Gianmaria).\nAvvisa il Nosferatu (Augusta) della sua morte.";
        assertThatIllegalArgumentException().isThrownBy(() -> progenizzazioneNosferatu(nomeVittima)).withMessage(messaggio);
        terminaNotte();
        verificaEliminati(nomeVittima, nomeNosferatu);
    }

    @Test public void testAttaccoNosferatuContadinoMostro()
    {
        String nomeVittima = "Gianmaria", nomeNosferatu = "Augusta", tipoLupo = "Lupo del branco", nomeLupo = "Renato";
        inizializzaPartita(new String[][] { { "Augusta", "Nosferatu" }, { nomeVittima, "Contadino mostro" }, { nomeLupo, tipoLupo } });
        String messaggioLupo =
            "L'attacco al Contadino mostro (Gianmaria) causa la morte anche del lupo attaccante (Renato).\nAvvisa entrambi i giocatori della " +
            "loro morte.";
        assertThatIllegalArgumentException().isThrownBy(() -> attaccoLupi(tipoLupo, nomeVittima)).withMessage(messaggioLupo);
        String messaggioNosferatu = "Impossibile progenizzare il Contadino mostro (Gianmaria).\nAvvisa il Nosferatu (Augusta) della sua morte.";
        assertThatIllegalArgumentException().isThrownBy(() -> progenizzazioneNosferatu(nomeVittima)).withMessage(messaggioNosferatu);
        terminaNotte();
        verificaEliminati(nomeNosferatu, nomeLupo);
        verificaNonEliminati(nomeVittima);
    }

    @Test public void testAttaccoNosferatuContadinoMostroRomeo()
    {
        String nomeVittima = "Gianmaria", nomeLupo = "Renato";
        inizializzaPartita(new String[][] { { "Augusta", "Nosferatu" }, { nomeVittima, "Contadino mostro" }, { nomeLupo, "Assassino" } });
        romeizzazione(nomeVittima);
        attaccoAssassino(nomeVittima);
        progenizzazioneNosferatu(nomeVittima);
        terminaNotte();
        verificaEliminati(nomeVittima);
    }

    @Test public void testAttaccoNosferatuFallito()
    {
        String nomeVittima = "Paolo";
        inizializzaPartita(new String[][] { { "Assunta", "Nosferatu" }, { nomeVittima, "Eremita" }, { "Franca", "Assassino" } });
        attaccoAssassino(nomeVittima);
        progenizzazioneNosferatu(nomeVittima);
        terminaNotte();
        verificaEliminati(nomeVittima);
    }

    @Test public void testNumeroSenzaFazione()
    {
        inizializzaPartita(new String[][] { { "Raffaello", "Ghoul" }, { "Mattia", "Peccatore" }, { "Leonardo", "Pazzo" } });
        verificaNumeroIntero(partita.getNumeroSenzaFazioneVivi(), 2);
    }

    @Test public void testNumeroLupi()
    {
        inizializzaPartita(new String[][] { { "Aurora", "Lupo del branco" }, { "Elisa", "Lupo reietto" }, { "Mohamed", "Capo branco" } });
        verificaNumeroIntero(partita.getNumeroLupiBrancoVivi(), 3);
    }

    @Test public void testAttaccoNosferatuRiuscito()
    {
        String nome = "Marco", tipoLupo = "Capo branco";
        inizializzaPartita(new String[][] { { nome, "Prete" }, { "Tina", "Nosferatu" }, { "Rolando", tipoLupo } });
        attaccoLupi(tipoLupo, nome);
        progenizzazioneNosferatu(nome);
        terminaNotte();
        verificaNonEliminati(nome);
        verificaFazioneNosferatu(nome);
    }

    @Test public void testSuicidioCapoBranco()
    {
        String nomeVittima = "Marco", tipoLupo = "Capo branco", nomeNosferatu = "Luca";
        inizializzaPartita(new String[][] { { nomeVittima, tipoLupo }, { nomeNosferatu, "Nosferatu" } });
        String messaggio = "Impossibile progenizzare il Capo branco (Marco).\nAvvisa il Nosferatu (Luca) della sua morte.";
        verificaMorteGhoul(tipoLupo, nomeVittima, messaggio, nomeNosferatu);
        verificaNonEliminati(nomeVittima);
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
        verificaNonEliminati(nomeVittima);
    }

    @ParameterizedTest @CsvSource({ "Lupo del branco", "Lupo reietto", "Lupo solitario" })
    public void attaccoAltriLupi(String tipoLupo)
    {
        String lupoAttaccante = "Capo branco", nomeVittima = "Mattia";
        inizializzaPartita(new String[][] { { "Andrea", lupoAttaccante }, { nomeVittima, tipoLupo } });
        attaccoLupi(lupoAttaccante, nomeVittima);
        terminaNotte();
        verificaNonEliminati(nomeVittima);
    }

    @Test public void testInizioCrociata()
    {
        String tipoLupo = "Capo branco", nomeVittima = "Chloe";
        inizializzaPartita(new String[][] { { "Yorgos", tipoLupo }, { "James", "Templare" }, { nomeVittima, "Inquisitore" } });
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
            "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Borgomastro", "Bracconiere", "Cacciatore", "Cacciatore di vampiri",
            "Cappuccetto rosso", "Contadino eroe", "Contadino discendente dei lupi", "Contadino normale", "Eremita", "Guaritore", "Mago",
            "Medium", "Mercante", "Monaco", "Nonna", "Oratore", "Oste", "Peccatore", "Prete", "Sensitiva"
        }
    )
    public void testCriminalizzazioneCapoGildaRiuscita(String nomeRuolo)
    {
        String nomeVittima = "Antonio";
        inizializzaPartita(new String[][] { { nomeVittima, nomeRuolo }, { "Davide", "Capo gilda" } });
        assertThatNoException().isThrownBy(() -> gildata(nomeVittima));
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Angelo custode", "Assassino", "Boia", "Ghoul", "Giulietta", "Giullare", "Goblin", "Guardia corrotta", "Inquisitore", "Ladra",
            "Leprecauno", "Megera", "Negromante", "Nosferatu", "Pazzo", "Posseduto", "Sidhe", "Spia", "Templare", "Vampiro"
        }
    )
    public void testCriminalizzazioneCapoGildaFallita(String nomeRuolo)
    {
        String nomeVittima = "Antonio";
        inizializzaPartita(new String[][] { { nomeVittima, nomeRuolo }, { "Davide", "Capo gilda" } });
        verificaFallimentoGildata(nomeVittima, "Impossibile criminalizzare " + nomeVittima + ".");
    }

    @ParameterizedTest
    @CsvSource( { "Capo branco", "Contadino mostro", "Giovane lupo", "Guardia", "Lupo del branco", "Lupo reietto", "Lupo solitario" })
    public void testCriminalizzazioneCapoGildaMorto(String nomeRuolo)
    {
        String nomeVittima = "Arturo", nomeCapoGilda = "Raffaele";
        inizializzaPartita(new String[][] { { nomeVittima, nomeRuolo }, { nomeCapoGilda, "Capo gilda" } });
        verificaMorteCapoGilda(nomeVittima, "Impossibile criminalizzare Arturo.\nIl Capo gilda (Raffaele) muore.", nomeCapoGilda);
    }

    @ParameterizedTest
    @CsvSource( { "Capo branco", "Contadino mostro", "Giovane lupo", "Guardia", "Lupo del branco", "Lupo reietto", "Lupo solitario" })
    public void testCriminalizzazioneCapoGildaAmatoMorto(String nomeRuolo)
    {
        String nomeVittima = "Arturo", nomeCapoGilda = "Raffaele";
        inizializzaPartita(new String[][] { { nomeVittima, nomeRuolo }, { nomeCapoGilda, "Capo gilda" } });
        segnalazioneAngeloCustode(nomeCapoGilda);
        verificaMorteCapoGilda(nomeVittima, "Impossibile criminalizzare Arturo.\nIl Capo gilda (Raffaele) muore.", nomeCapoGilda);
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Capo branco, 'Il Contadino discendente dei lupi (Arturo) è stato attaccato dai Lupi del branco, pertanto adesso fa parte della " +
            "loro fazione.\nSveglia Arturo e fagli riconoscere gli altri lupi.'",
            "Lupo del branco, 'Il Contadino discendente dei lupi (Arturo) è stato attaccato dai Lupi del branco, pertanto adesso fa parte " +
            "della loro fazione.\nSveglia Arturo e fagli riconoscere gli altri lupi.'",
            "Lupo reietto, 'Il Contadino discendente dei lupi (Arturo) è stato attaccato dai Lupi del branco, pertanto adesso fa parte della " +
            "loro fazione.\nSveglia Arturo e fagli riconoscere gli altri lupi.'",
            "Lupo solitario, 'Il Contadino discendente dei lupi (Arturo) è stato attaccato dal Lupo solitario, pertanto anche lui diventa tale" +
            ".\nSveglia Arturo e fagli riconoscere il Lupo solitario che lo ha attaccato.'"
        }
    )
    public void testCriminalizzazioneCapoGildaMortoContadinoLupizzato(String tipoLupo, String messaggio)
    {
        String nomeVittima = "Arturo", nomeCapoGilda = "Raffaele", nomeLupo = "Ale";
        String[][] giocatori =
            new String[][] { { nomeVittima, "Contadino discendente dei lupi" }, { nomeCapoGilda, "Capo gilda" }, { nomeLupo, tipoLupo } };
        inizializzaPartita(giocatori);
        verificaVittimaSbagliata(tipoLupo, nomeVittima, messaggio);
        verificaMorteCapoGilda(nomeVittima, "Impossibile criminalizzare Arturo.\nIl Capo gilda (Raffaele) muore.", nomeCapoGilda);
    }

    @Test public void testCriminalizzazioneBecchino()
    {
        String nomeVittima = "Giulia";
        inizializzaPartita(new String[][] { { nomeVittima, "Becchino" }, { "Tania", "Capo gilda" } });
        partita.riconosciNegromante();
        verificaFallimentoGildata(nomeVittima, "Impossibile criminalizzare " + nomeVittima + ".");
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Capo branco, 'Il Contadino discendente dei lupi (Mario) è stato attaccato dai Lupi del branco, pertanto adesso fa parte della " +
            "loro fazione.\nSveglia Mario e fagli riconoscere gli altri lupi.'",
            "Lupo del branco, 'Il Contadino discendente dei lupi (Mario) è stato attaccato dai Lupi del branco, pertanto adesso fa parte della " +
            "loro fazione.\nSveglia Mario e fagli riconoscere gli altri lupi.'",
            "Lupo reietto, 'Il Contadino discendente dei lupi (Mario) è stato attaccato dai Lupi del branco, pertanto adesso fa parte della " +
            "loro fazione.\nSveglia Mario e fagli riconoscere gli altri lupi.'",
            "Lupo solitario, 'Il Contadino discendente dei lupi (Mario) è stato attaccato dal Lupo solitario, pertanto anche lui diventa tale" +
            ".\nSveglia Mario e fagli riconoscere il Lupo solitario che lo ha attaccato.'"
        }
    )
    public void testCriminalizzazioneContadinoLupo(String tipoLupo, String messaggio)
    {
        String nomeVittima = "Mario", nomeCapoGilda = "Andrea";
        inizializzaPartita
        (
            new String[][] { { "Sara", tipoLupo }, { nomeVittima, "Contadino discendente dei lupi" }, { nomeCapoGilda, "Capo gilda" } }
        );
        verificaVittimaSbagliata(tipoLupo, nomeVittima, messaggio);
        verificaMorteCapoGilda(nomeVittima, "Impossibile criminalizzare Mario.\nIl Capo gilda (Andrea) muore.", nomeCapoGilda);
    }

    @Test public void testCriminalizzazioneContadinoMostro()
    {
        String nomeVittima = "Alberto", nomeCapoGilda = "Andrea";
        inizializzaPartita(new String[][] { { nomeVittima, "Contadino mostro" }, { nomeCapoGilda, "Capo gilda" } });
        String messaggio = "Impossibile criminalizzare " + nomeVittima + ".\nIl Capo gilda (" + nomeCapoGilda + ") muore.";
        verificaMorteCapoGilda(nomeVittima, messaggio, nomeCapoGilda);
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Capo branco, 'Dal momento che non ci sono altri lupi del branco, il Cappuccetto rosso (Elena) riconosce il Capo branco (Andrea).'",
            "Lupo del branco, 'Dal momento che non ci sono altri lupi del branco, il Cappuccetto rosso (Elena) riconosce il Lupo del branco " +
            "(Andrea).'",
            "Lupo reietto, 'Dal momento che non ci sono altri lupi del branco, il Cappuccetto rosso (Elena) riconosce il Lupo reietto (Andrea).'",
            "Lupo solitario, 'Andrea è il Lupo solitario. Cappuccetto rosso (Elena) si sveglia e lo riconosce'"
        }
    )
    public void testNessunaProtezioneCappuccettoRosso(String tipoLupo, String messaggio)
    {
        String nomeVittima = "Elena";
        inizializzaPartita(new String[][] { { nomeVittima, "Cappuccetto rosso" }, { "Andrea", tipoLupo } });
        assertThatIllegalStateException().isThrownBy(() -> attaccoLupi(tipoLupo, nomeVittima)).withMessage(messaggio);
        terminaNotte();
        verificaEliminazione(nomeVittima);
    }

    @ParameterizedTest @CsvSource({ "Capo branco", "Lupo del branco", "Lupo reietto" })
    public void testNessunaProtezioneCappuccettoRossoMorteNonna(String tipoLupo)
    {
        String nomeNonna = "Manfredi", nomeCappuccettoRosso = "Pina";
        String[][] giocatori = new String[][]
        {
            { nomeNonna, "Nonna" }, { "Damiano", tipoLupo }, { nomeCappuccettoRosso, "Cappuccetto rosso" }, { "Tony", "Lupo solitario" }
        };
        inizializzaPartita(giocatori);
        incrementaVoti(nomeNonna, 3);
        terminaVotazioni();
        incrementaVoti(nomeNonna, 2);
        terminaBallottaggio();
        String messaggio =
            "Dal momento che non ci sono altri lupi del branco, il Cappuccetto rosso (Pina) riconosce il " + tipoLupo + " (Damiano).";
        assertThatIllegalStateException().isThrownBy(() -> attaccoLupi(tipoLupo, nomeCappuccettoRosso)).withMessage(messaggio);
        terminaNotte();
        verificaEliminati(nomeNonna, nomeCappuccettoRosso);
    }

    @ParameterizedTest @CsvSource({ "Capo branco", "Lupo del branco", "Lupo reietto", "Lupo solitario" })
    public void testGuarigioneContadinoMostro(String tipoLupo)
    {
        String nomeContadino = "Graziano", nomeGuaritore = "Perla", nomeLupo = "Leonardo";
        inizializzaPartita(new String[][] { { nomeContadino, "Contadino mostro" }, { nomeLupo, tipoLupo }, { nomeGuaritore, "Guaritore" } });
        String messaggio =
            "L'attacco al Contadino mostro (Graziano) causa la morte anche del lupo attaccante (Leonardo).\nAvvisa entrambi i giocatori della " +
            "loro morte.";
        verificaVittimaSbagliata(tipoLupo, nomeContadino, messaggio);
        guarisci(nomeContadino);
        terminaNotte();
        verificaEliminati(nomeGuaritore, nomeLupo);
        verificaNonEliminati(nomeContadino);
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Capo branco, 'Il Contadino discendente dei lupi (Graziano) è stato attaccato dai Lupi del branco, pertanto adesso fa parte della " +
            "loro fazione.\nSveglia Graziano e fagli riconoscere gli altri lupi.'",
            "Lupo del branco, 'Il Contadino discendente dei lupi (Graziano) è stato attaccato dai Lupi del branco, pertanto adesso fa parte " +
            "della loro fazione.\nSveglia Graziano e fagli riconoscere gli altri lupi.'",
            "Lupo reietto, 'Il Contadino discendente dei lupi (Graziano) è stato attaccato dai Lupi del branco, pertanto adesso fa parte della " +
            "loro fazione.\nSveglia Graziano e fagli riconoscere gli altri lupi.'",
            "Lupo solitario, 'Il Contadino discendente dei lupi (Graziano) è stato attaccato dal Lupo solitario, pertanto anche lui diventa " +
            "tale.\nSveglia Graziano e fagli riconoscere il Lupo solitario che lo ha attaccato.'"
        }
    )
    public void testGuarigioneContadinoLupo(String tipoLupo, String messaggio)
    {
        String nomeContadino = "Graziano", nomeLupo = "Leonardo";
        String[][] giocatori = new String[][]
        {
            { nomeContadino, "Contadino discendente dei lupi" }, { nomeLupo, tipoLupo }, { "Fabrizio", "Guaritore" }, { "Gea", "Assassino" }
        };
        inizializzaPartita(giocatori);
        verificaVittimaSbagliata(tipoLupo, nomeContadino, messaggio);
        attaccoAssassino(nomeContadino);
        guarisci(nomeContadino);
        terminaNotte();
        verificaNonEliminati(nomeContadino);
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
        verificaNonEliminati(nomeAssassino);
    }

    @Test public void testControlloMagoContadinoMostroPrimaNotte()
    {
        String[][] giocatori = new String[][] { { "Harry", "Mago" }, { "Hagrid", "Contadino mostro" } };
        inizializzaPartita(giocatori);
        verificaNonMistico(giocatori[1][0]);
        verificaNonEliminati(estraiNomiGiocatori(giocatori));
    }

    @Test public void testControlloMagoContadinoMostro()
    {
        String[][] giocatori = new String[][] { { "Massimo", "Mago" }, { "Christian", "Contadino mostro" } };
        inizializzaPartita(giocatori);
        terminaNotte();
        int indiceContadino = 1;
        verificaNonMistico(giocatori[indiceContadino][0]);
        terminaNotte();
        verificaEliminazione(giocatori[0][0]);
        verificaNonEliminati(giocatori[indiceContadino][0]);
    }

    @Test public void testMorteNegromante()
    {
        String[][] giocatori = new String[][] { { "Dina", "Contadino mostro" }, { "Giuseppe", "Negromante" } };
        inizializzaPartita(giocatori);
        int posizioneVittima = 0;
        attaccoNegromante(giocatori[posizioneVittima][0]);
        terminaNotte();
        verificaNonEliminati(giocatori[posizioneVittima][0]);
        verificaEliminazione(giocatori[1][0]);
    }

    @Test public void testNegromanteContadinoMostroRomeizzato()
    {
        String[][] giocatori = new String[][] { { "Lino", "Contadino mostro" }, { "Dino", "Negromante" }, { "Pino", "Giulietta" } };
        inizializzaPartita(giocatori);
        int posizioneVittima = 0;
        romeizzazione(giocatori[posizioneVittima][0]);
        assertThatIllegalStateException().isThrownBy(() -> attaccoNegromante(giocatori[posizioneVittima][0]))
            .withMessage("Scegli un'altra persona da attaccare.");
        terminaNotte();
        verificaNonEliminati(estraiNomiGiocatori(giocatori));
    }

    @Test public void testControlloSensitivaContadinoMostroPrimaNotte()
    {
        String[][] giocatori = new String[][] { { "Elettra", "Contadino mostro" }, { "Gianluca", "Sensitiva" } };
        inizializzaPartita(giocatori);
        int posizioneContadino = 0;
        verificaVillaggio(giocatori[posizioneContadino][0]);
        verificaNonEliminati(estraiNomiGiocatori(giocatori));
    }

    @Test public void testControlloSensitivaContadinoMostro()
    {
        String nomeContadinoMostro = "Antonio", nomeSensitiva = "Carlo";
        String[][] giocatori = new String[][] { { nomeContadinoMostro, "Contadino mostro" }, { "Carlo", "Sensitiva" } };
        inizializzaPartita(giocatori);
        terminaNotte();
        verificaVillaggio(nomeContadinoMostro);
        terminaNotte();
        verificaEliminazione(nomeSensitiva);
        verificaNonEliminati(nomeContadinoMostro);
    }

    @ParameterizedTest @CsvSource({ "Capo branco", "Lupo del branco", "Lupo reietto", "Lupo solitario" })
    public void testAttaccoLupiEremita(String tipoLupo)
    {
        String[][] giocatori = new String[][] { { "Margerita", "Eremita" }, { "Tony", tipoLupo } };
        inizializzaPartita(giocatori);
        assertThatIllegalArgumentException().isThrownBy(() -> attaccoLupi(tipoLupo, giocatori[0][0]))
            .withMessage("Margerita è l'Eremita, i lupi non possono ucciderlo.");
        terminaNotte();
        verificaNonEliminati(estraiNomiGiocatori(giocatori));
    }

    @Test public void testGhoulVivoPresente()
    {
        String nome = "Tony";
        inizializzaPartita(new String[][] { { nome, "Ghoul" } });
        verificaVero(isGhoulVivo(nome));
    }

    @Test public void testGhoulVivoAssente()
    {
        String nome = "Margherita";
        inizializzaPartita(new String[][] { { nome, "Cacciatore" } });
        verificaGhoulNonVivo(nome);
    }

    @Test public void testGhoulMorto()
    {
        String nomeGhoul = "Massimo", tipoLupo = "Capo branco";
        inizializzaPartita(new String[][] { { nomeGhoul, "Ghoul" }, { "Tullio", tipoLupo } });
        attaccoLupi(tipoLupo, nomeGhoul);
        terminaNotte();
        verificaGhoulNonVivo(nomeGhoul);
    }

    @Test public void testNosferatuVivoPresente()
    {
        String nome = "Matilde";
        inizializzaPartita(new String[][] { { nome, "Nosferatu" } });
        verificaVero(isNosferatuVivo(nome));
    }

    @Test public void testNosferatuVivoAssente()
    {
        String nome = "Pamela";
        inizializzaPartita(new String[][] { { nome, "Cacciatore di vampiri" } });
        verificaNosferatuNonVivo(nome);
    }

    @Test public void testNosferatuMorto()
    {
        String nomeNosferatu = "Cesare", tipoLupo = "Lupo del branco";
        inizializzaPartita(new String[][] { { nomeNosferatu, "Nosferatu" }, { "Annibale", tipoLupo } });
        attaccoLupi(tipoLupo, nomeNosferatu);
        terminaNotte();
        verificaNosferatuNonVivo(nomeNosferatu);
    }

    @Test public void testProgenieNosferatuViva()
    {
        String nomeVittima = "Pina", tipoLupo = "Lupo solitario";
        inizializzaPartita(new String[][] { { nomeVittima, "Bocca di rosa" }, { "Ugo", tipoLupo }, { "Mariangela", "Nosferatu" } });
        attaccoLupi(tipoLupo, nomeVittima);
        progenizzazioneNosferatu(nomeVittima);
        terminaNotte();
        verificaVero(isProgenieNosferatuViva(nomeVittima));
    }

    @Test public void testNoProgenieNosferatu()
    {
        String nome = "Mark";
        inizializzaPartita(new String[][] { { nome, "Azzeccagarbugli" } });
        verificaProgenieNosferatuNonViva(nome);
    }

    @Test public void testProgenieNosferatuMorta()
    {
        String nomeVittima = "Clark", tipoLupo = "Lupo reietto";
        inizializzaPartita(new String[][] { { nomeVittima, "Prete" }, { "Lois", tipoLupo }, { "Luthor", "Nosferatu" } });
        attaccoLupi(tipoLupo, nomeVittima);
        progenizzazioneNosferatu(nomeVittima);
        terminaNotte();
        attaccoLupi(tipoLupo, nomeVittima);
        terminaNotte();
        verificaProgenieNosferatuNonViva(nomeVittima);
    }

    @Test public void testNessunGiocatoreVivo()
    {
        inizializzaPartita(new String[][] { });
        verificaVero(isNoGiocatoriVivi());
    }

    @Test public void testGiocatoriViviPresenti()
    {
        inizializzaPartita(new String[][] { { "Clark", "Guaritore" } });
        verificaFalso(isNoGiocatoriVivi());
    }

    @Test public void testLupoReiettoNonVivo()
    {
        inizializzaPartita(new String[][]{ });
        verificaFalso(isLupoReiettoVivo());
    }

    @Test public void testLupoReiettoVivo()
    {
        inizializzaPartita(new String[][]{ { "Camilla", "Lupo reietto" } });
        verificaVero(isLupoReiettoVivo());
    }

    @Test public void testLupoAttaccanteNonVivo()
    {
        inizializzaPartita(new String[][] { });
        verificaFalso(isLupoAttaccanteVivo());
    }

    @ParameterizedTest @CsvSource({ "Capo branco", "Lupo del branco" }) public void testLupoAttaccanteVivo(String tipoLupo)
    {
        inizializzaPartita(new String[][] { { "Adriana", tipoLupo } });
        verificaVero(isLupoAttaccanteVivo());
    }

    @Test public void testCriminaliAssenti()
    {
        inizializzaPartita(new String[][] { });
        verificaFalso(isCriminaliPresenti());
    }

    @ParameterizedTest @CsvSource({ "Assassino", "Capo gilda", "Guardia corrotta", "Ladra", "Spia" })
    public void testCriminaliPresenti(String tipoLupo)
    {
        inizializzaPartita(new String[][] { { "Herbert", tipoLupo } });
        verificaVero(isCriminaliPresenti());
    }

    @Test public void testAmatoNonPresente()
    {
        inizializzaPartita(new String[][] { });
        verificaAmatoNonVivo();
    }

    @Test public void testAmatoMorto()
    {
        String nome = "Maria", tipoLupo = "Capo branco";
        inizializzaPartita(new String[][] { { "Carlo", "Angelo custode" }, { nome, "Prete" }, { "Virginio", tipoLupo } });
        segnalazioneAngeloCustode(nome);
        String messaggio =
            "Il Capo branco (Virginio) non può attaccare il Prete amato (Maria).\nAvvisa l'Angelo custode (Carlo) della sua morte.";
        assertThatIllegalStateException().isThrownBy(() -> attaccoLupi(tipoLupo, nome)).withMessage(messaggio);
        terminaNotte();
        attaccoLupi(tipoLupo, nome);
        terminaNotte();
        verificaAmatoNonVivo();
    }

    @Test public void testGuarigioneAmatoMorto()
    {
        String nome = "Maria", tipoLupo = "Capo branco";
        inizializzaPartita(new String[][] { { "Carlo", "Angelo custode" }, { nome, "Prete" }, { "Virginio", tipoLupo } });
        segnalazioneAngeloCustode(nome);
        String messaggio =
            "Il Capo branco (Virginio) non può attaccare il Prete amato (Maria).\nAvvisa l'Angelo custode (Carlo) della sua morte.";
        assertThatIllegalStateException().isThrownBy(() -> attaccoLupi(tipoLupo, nome)).withMessage(messaggio);
        terminaNotte();
        attaccoLupi(tipoLupo, nome);
        guarisci(nome);
        terminaNotte();
        verificaNonEliminati(nome);
    }

    @Test public void testAmatoPresente()
    {
        String nome = "Pina";
        inizializzaPartita(new String[][] { { "Francesco", "Angelo custode" }, { nome, "Peccatore" } });
        segnalazioneAngeloCustode(nome);
        verificaVero(isAmatoVivo());
    }

    @ParameterizedTest @CsvSource({ "Capo branco", "Lupo del branco", "Lupo reietto", "Lupo solitario" })
    public void testMorteGhoulCacciatoreNosferatu(String tipoLupo)
    {
        String nomeVittima = "Terzo", nomeGhoul = "Quarto";
        String[][] giocatori = new String[][]
        {
            { "Primo", tipoLupo }, { "Secondo", "Nosferatu" }, { nomeVittima, "Cacciatore di vampiri" }, { nomeGhoul, "Ghoul" }
        };
        inizializzaPartita(giocatori);
        verificaMorteGhoul(tipoLupo, nomeVittima, "Il tentativo di progenizzazione del Cacciatore di vampiri (Terzo) causa la morte del Ghoul (Quarto).\nAvvisa Quarto della sua morte.", nomeGhoul);
    }

    @ParameterizedTest @CsvSource({ "Capo branco", "Lupo del branco", "Lupo reietto", "Lupo solitario" })
    public void testMorteGhoulAmatoCacciatoreNosferatu(String tipoLupo)
    {
        String nomeVittima = "Terzo", nomeGhoul = "Quarto";
        String[][] giocatori = new String[][]
        {
            { "Primo", tipoLupo }, { "Secondo", "Nosferatu" }, { nomeVittima, "Cacciatore di vampiri" }, { nomeGhoul, "Ghoul" }
        };
        inizializzaPartita(giocatori);
        segnalazioneAngeloCustode(nomeGhoul);
        verificaMorteGhoul(tipoLupo, nomeVittima, "Il tentativo di progenizzazione del Cacciatore di vampiri (Terzo) causa la morte del Ghoul (Quarto).\nAvvisa Quarto della sua morte.", nomeGhoul);
    }

    @ParameterizedTest @CsvSource({ "Capo branco", "Lupo del branco", "Lupo reietto", "Lupo solitario" })
    public void testMorteGhoulContadinoMostroNosferatu(String tipoLupo)
    {
        String[][] giocatori = new String[][]
        {
            { "Primo", tipoLupo }, { "Secondo", "Nosferatu" }, { "Terzo", "Contadino mostro" }, { "Quarto", "Ghoul" }
        };
        inizializzaPartita(giocatori);
        String nomeVittima = giocatori[2][0];
        String messaggioLupi =
            "L'attacco al Contadino mostro (Terzo) causa la morte anche del lupo attaccante (Primo).\nAvvisa entrambi i giocatori della loro morte.";
        String messaggioNosferatu =
            "Il tentativo di progenizzazione del Contadino mostro (Terzo) causa la morte del Ghoul (Quarto).\nAvvisa Quarto della sua morte.";
        verificaVittimaSbagliata(tipoLupo, nomeVittima, messaggioLupi);
        verificaFallimentoProgenizzazione(nomeVittima, messaggioNosferatu);
        terminaNotte();
        verificaEliminati(giocatori[0][0], giocatori[3][0]);
    }

    @Test public void testFazioneNosferatu()
    {
        String nome = "Gigio";
        inizializzaPartita(new String[][] { { nome, "Nosferatu" } });
        verificaFazioneNosferatu(nome);
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
        inizializzaPartita(new String[][] { { nome, nomeRuolo } });
        verificaFalso(isFazioneNosferatu(nome));
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia, BIANCA", "Angelo custode, BIANCA", "Assassino, NERA", "Azzeccagarbugli, BIANCA", "Bardo, BIANCA", "Becchino, BIANCA",
            "Bocca di rosa, NERA", "Boia, NERA", "Borgomastro, BIANCA", "Bracconiere, BIANCA", "Cacciatore, BIANCA",
            "Cacciatore di vampiri, BIANCA", "Capo branco, NERA", "Capo gilda, BIANCA", "Cappuccetto rosso, BIANCA", "Contadino eroe, BIANCA",
            "Contadino discendente dei lupi, BIANCA", "Contadino mostro, BIANCA", "Contadino normale, BIANCA", "Eremita, BIANCA", "Ghoul, BIANCA",
            "Giovane lupo, NERA", "Giulietta, BIANCA", "Giullare, BIANCA", "Goblin, NERA", "Guardia, BIANCA", "Guardia corrotta, NERA",
            "Guaritore, BIANCA", "Inquisitore, BIANCA", "Ladra, BIANCA", "Leprecauno, BIANCA", "Lupo del branco, NERA", "Lupo reietto, NERA",
            "Lupo solitario, NERA", "Mago, BIANCA", "Medium, BIANCA", "Megera, NERA", "Mercante, BIANCA", "Monaco, BIANCA", "Negromante, NERA",
            "Nonna, BIANCA", "Nosferatu, NERA", "Oste, BIANCA", "Pazzo, BIANCA", "Peccatore, NERA", "Posseduto, NERA", "Prete, BIANCA",
            "Sidhe, BIANCA", "Spia, BIANCA", "Sensitiva, BIANCA", "Templare, BIANCA", "Vampiro, NERA"
        }
    )
    public void testControlloMedium(String nomeRuolo, Aura aura)
    {
        String nome = "Marco";
        inizializzaPartita(new String[][] { { nome, nomeRuolo } });
        incrementaVoti(nome, 1);
        terminaVotazioni();
        incrementaVoti(nome, 1);
        terminaBallottaggio();
        terminaNotte();
        verificaControlloMedium(nome, aura);
    }

    @Test public void testControlloMediumOratore()
    {
        String nomeVittima = "Chiara";
        inizializzaPartita(new String[][] { { "Gennaro", "Assassino" }, { nomeVittima, "Oratore" } });
        attaccoAssassino(nomeVittima);
        terminaNotte();
        verificaControlloMedium(nomeVittima, BIANCA);
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
            "Mago, MISTICO", "Medium, MISTICO", "Mercante, NON_MISTICO", "Monaco, NON_MISTICO", "Negromante, MISTICO", "Nonna, NON_MISTICO",
            "Nosferatu, NON_MISTICO", "Oratore, NON_MISTICO", "Oste, NON_MISTICO", "Pazzo, NON_MISTICO", "Peccatore, NON_MISTICO",
            "Posseduto, NON_MISTICO", "Prete, NON_MISTICO", "Sidhe, MISTICO", "Spia, NON_MISTICO", "Sensitiva, MISTICO", "Sensitiva, MISTICO",
            "Templare, NON_MISTICO", "Vampiro, NON_MISTICO"
        }
    )
    public void testMisticismo(String nomeRuolo, Misticismo misticismo)
    {
        String nome = "Mario";
        inizializzaPartita(new String[][] { { "Wario", "Mago" }, { nome, nomeRuolo } });
        verificaControlloMago(nome, misticismo);
    }

    @Test public void testMorteGhoulCacciatoreVampiro()
    {
        String nomeCacciatore = "Terzo", nomeGhoul = "Quarto";
        String[][] giocatori = new String[][]
        {
            { "Primo", "Oste" }, { "Secondo", "Vampiro" }, { nomeCacciatore, "Cacciatore di vampiri" }, { nomeGhoul, "Ghoul" }
        };
        inizializzaPartita(giocatori);
        String messaggio =
            "Il tentativo di vampirizzazione del Cacciatore di vampiri (Terzo) causa la morte del Ghoul (Quarto).\nAvvisa Quarto della sua morte.";
        verificaMortePostAttaccoVampiro(nomeCacciatore, messaggio, nomeGhoul);
    }

    @Test public void testMorteGhoulAmatoCacciatoreVampiro()
    {
        String nomeCacciatore = "Terzo", nomeGhoul = "Quarto";
        String[][] giocatori = new String[][]
        {
            { "Primo", "Oste" }, { "Secondo", "Vampiro" }, { nomeCacciatore, "Cacciatore di vampiri" }, { nomeGhoul, "Ghoul" }
        };
        inizializzaPartita(giocatori);
        segnalazioneAngeloCustode(nomeGhoul);
        String messaggio =
            "Il tentativo di vampirizzazione del Cacciatore di vampiri (Terzo) causa la morte del Ghoul (Quarto).\nAvvisa Quarto della sua morte.";
        verificaMortePostAttaccoVampiro(nomeCacciatore, messaggio, nomeGhoul);
    }

    @Test public void testMorteGhoulContadinoMostroVampiro()
    {
        String nomeContadinoMostro = "Edd", nomeGhoul = "Eddy";
        String[][] giocatori = new String[][] { { "Ed", "Vampiro" }, { nomeContadinoMostro, "Contadino mostro" }, { nomeGhoul, "Ghoul" } };
        inizializzaPartita(giocatori);
        String messaggio =
            "Il tentativo di vampirizzazione del Contadino mostro (Edd) causa la morte del Ghoul (Eddy).\nAvvisa Eddy della sua morte.";
        verificaMortePostAttaccoVampiro(nomeContadinoMostro, messaggio, nomeGhoul);
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Angelo custode", "Assassino", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro",
            "Bracconiere", "Cacciatore", "Cappuccetto rosso", "Contadino eroe", "Contadino discendente dei lupi", "Contadino normale", "Ghoul",
            "Giullare", "Guardia", "Guardia corrotta", "Inquisitore", "Mercante", "Monaco", "Nonna", "Oratore", "Oste", "Pazzo", "Peccatore",
            "Prete", "Spia", "Templare", "Vampiro"
        }
    )
    public void testCriminalizzazioneProgenieVampiro(String nomeRuolo)
    {
        String nomeVittima = "Antonio";
        String[][] giocatori = new String[][] { { nomeVittima, nomeRuolo }, { "Davide", "Capo gilda" }, { "Gioele", "Vampiro" } };
        inizializzaPartita(giocatori);
        verificaAttaccoVampiroRiuscito(nomeVittima);
        verificaFallimentoGildata(nomeVittima, "Impossibile criminalizzare Antonio.");
    }

    @ParameterizedTest @CsvSource( { "Assassino", "Azzeccagarbugli", "Cappuccetto rosso", "Ghoul", "Giulietta", "Inquisitore" } )
    public void testVampirizzazioneRiuscita(String nomeRuolo)
    {
        String nomeVittima = "Rino";
        inizializzaPartita(new String[][] { { nomeVittima, nomeRuolo }, { "Pino", "Vampiro" } });
        verificaAttaccoVampiroRiuscito(nomeVittima);
    }

    @ParameterizedTest @CsvSource({ "Eremita", "Goblin", "Guaritore", "Leprecauno", "Mago", "Medium", "Negromante" })
    public void testVampirizzazioneFallita(String nomeRuolo)
    {
        String nomeVittima = "Lino";
        inizializzaPartita(new String[][] { { nomeVittima, nomeRuolo }, { "Gino", "Vampiro" } });
        assertThatIllegalArgumentException().isThrownBy(() -> attaccoVampiro(nomeVittima)).withMessage("Impossibile vampirizzare Lino.");
    }

    @ParameterizedTest @CsvSource
    (
        {
            "'Capo branco', 'Impossibile vampirizzare il Capo branco (Giuliano).\nAvvisa il Vampiro (Michele) della sua morte.'",
            "'Lupo del branco', 'Impossibile vampirizzare il Lupo del branco (Giuliano).\nAvvisa il Vampiro (Michele) della sua morte.'",
            "'Lupo reietto', 'Impossibile vampirizzare il Lupo reietto (Giuliano).\nAvvisa il Vampiro (Michele) della sua morte.'",
            "'Lupo solitario', 'Impossibile vampirizzare il Lupo solitario (Giuliano).\nAvvisa il Vampiro (Michele) della sua morte.'",
            "'Cacciatore di vampiri', 'Impossibile vampirizzare il Cacciatore di vampiri (Giuliano).\nAvvisa il Vampiro (Michele) della sua " +
            "morte.'"
        }
    )
    public void testMorteVampiro(String nomeRuolo, String messaggio)
    {
        String nomeVittima = "Giuliano", nomeVampiro = "Michele";
        inizializzaPartita(new String[][] { { nomeVittima, nomeRuolo }, { nomeVampiro, "Vampiro" } });
        verificaMortePostAttaccoVampiro(nomeVittima, messaggio, nomeVampiro);
    }

    @ParameterizedTest @CsvSource
    (
        {
            "'Capo branco', 'Impossibile vampirizzare il Capo branco (Giuliano).\nAvvisa il Vampiro (Michele) della sua morte.'",
            "'Lupo del branco', 'Impossibile vampirizzare il Lupo del branco (Giuliano).\nAvvisa il Vampiro (Michele) della sua morte.'",
            "'Lupo reietto', 'Impossibile vampirizzare il Lupo reietto (Giuliano).\nAvvisa il Vampiro (Michele) della sua morte.'",
            "'Lupo solitario', 'Impossibile vampirizzare il Lupo solitario (Giuliano).\nAvvisa il Vampiro (Michele) della sua morte.'",
            "'Cacciatore di vampiri', 'Impossibile vampirizzare il Cacciatore di vampiri (Giuliano).\nAvvisa il Vampiro (Michele) della sua morte.'"
        }
    )
    public void testMorteVampiroAmato(String nomeRuolo, String messaggio)
    {
        String nomeVittima = "Giuliano", nomeVampiro = "Michele";
        inizializzaPartita(new String[][] { { nomeVittima, nomeRuolo }, { nomeVampiro, "Vampiro" }, { "Gabriele", "Angelo custode" } });
        segnalazioneAngeloCustode(nomeVampiro);
        verificaMortePostAttaccoVampiro(nomeVittima, messaggio, nomeVampiro);
    }

    @ParameterizedTest @CsvSource
    (
        {
            "'Capo branco', 'Impossibile progenizzare il Capo branco (Giuliano).\nAvvisa il Nosferatu (Michele) della sua morte.'",
            "'Lupo del branco', 'Impossibile progenizzare il Lupo del branco (Giuliano).\nAvvisa il Nosferatu (Michele) della sua morte.'",
            "'Lupo reietto', 'Impossibile progenizzare il Lupo reietto (Giuliano).\nAvvisa il Nosferatu (Michele) della sua morte.'",
            "'Lupo solitario', 'Impossibile progenizzare il Lupo solitario (Giuliano).\nAvvisa il Nosferatu (Michele) della sua morte.'",
            "'Cacciatore di vampiri', 'Impossibile progenizzare il Cacciatore di vampiri (Giuliano).\nAvvisa il Nosferatu (Michele) della sua " +
            "morte.'"
        }
    )
    public void testMorteNosferatu(String nomeRuolo, String messaggio)
    {
        String nomeVittima = "Giuliano", nomeNosferatu = "Michele";
        inizializzaPartita(new String[][] { { nomeVittima, nomeRuolo }, { nomeNosferatu, "Nosferatu" }, { "Giampiero", "Assassino" } });
        attaccoAssassino(nomeVittima);
        verificaMortePostAttaccoNosferatu(nomeVittima, messaggio, nomeNosferatu);
    }

    @ParameterizedTest @CsvSource
    (
        {
            "'Capo branco', 'Impossibile progenizzare il Capo branco (Giuliano).\nAvvisa il Nosferatu (Michele) della sua morte.'",
            "'Lupo del branco', 'Impossibile progenizzare il Lupo del branco (Giuliano).\nAvvisa il Nosferatu (Michele) della sua morte.'",
            "'Lupo reietto', 'Impossibile progenizzare il Lupo reietto (Giuliano).\nAvvisa il Nosferatu (Michele) della sua morte.'",
            "'Lupo solitario', 'Impossibile progenizzare il Lupo solitario (Giuliano).\nAvvisa il Nosferatu (Michele) della sua morte.'",
            "'Cacciatore di vampiri', 'Impossibile progenizzare il Cacciatore di vampiri (Giuliano).\nAvvisa il Nosferatu (Michele) della sua " +
            "morte.'"
        }
    )
    public void testMorteNosferatuAmato(String nomeRuolo, String messaggio)
    {
        String nomeVittima = "Giuliano", nomeNosferatu = "Michele";
        inizializzaPartita(new String[][] { { nomeVittima, nomeRuolo }, { nomeNosferatu, "Nosferatu" }, { "Giampiero", "Assassino" } });
        segnalazioneAngeloCustode(nomeNosferatu);
        attaccoAssassino(nomeVittima);
        verificaMortePostAttaccoNosferatu(nomeVittima, messaggio, nomeNosferatu);
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Capo branco, 'Il Contadino discendente dei lupi (Luca) è stato attaccato dai Lupi del branco, pertanto adesso fa parte della " +
            "loro fazione.\nSveglia Luca e fagli riconoscere gli altri lupi.'",
            "Lupo del branco, 'Il Contadino discendente dei lupi (Luca) è stato attaccato dai Lupi del branco, pertanto adesso fa parte della " +
            "loro fazione.\nSveglia Luca e fagli riconoscere gli altri lupi.'",
            "Lupo reietto, 'Il Contadino discendente dei lupi (Luca) è stato attaccato dai Lupi del branco, pertanto adesso fa parte della " +
            "loro fazione.\nSveglia Luca e fagli riconoscere gli altri lupi.'",
            "Lupo solitario, 'Il Contadino discendente dei lupi (Luca) è stato attaccato dal Lupo solitario, pertanto anche lui diventa tale" +
            ".\nSveglia Luca e fagli riconoscere il Lupo solitario che lo ha attaccato.'"
        }
    )
    public void testMorteVampiroContadinoLupizzato(String tipoLupo, String messaggioErrore)
    {
        String nomeVittima = "Luca", nomeVampiro = "Paolo", nomeLupo = "Lino";
        String[][] giocatori =
            new String[][] { { nomeVittima, "Contadino discendente dei lupi" }, { nomeVampiro, "Vampiro" }, { nomeLupo, tipoLupo } };
        inizializzaPartita(giocatori);
        verificaVittimaSbagliata(tipoLupo, nomeVittima, messaggioErrore);
        String messaggio = "Impossibile vampirizzare il Contadino discendente dei lupi (Luca).\nAvvisa il Vampiro (Paolo) della sua morte.";
        verificaMortePostAttaccoVampiro(nomeVittima, messaggio, nomeVampiro);
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Angelo custode", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro", "Bracconiere",
            "Cacciatore", "Cacciatore di vampiri", "Capo branco", "Cappuccetto rosso", "Contadino eroe", "Contadino discendente dei lupi",
            "Contadino mostro", "Contadino normale", "Eremita", "Ghoul", "Giovane lupo", "Giullare", "Goblin", "Guardia", "Guaritore",
            "Inquisitore", "Leprecauno", "Lupo del branco", "Lupo reietto", "Lupo solitario", "Mago", "Medium", "Megera", "Mercante", "Monaco",
            "Negromante", "Nonna", "Nosferatu", "Oratore", "Oste", "Pazzo", "Peccatore", "Posseduto", "Prete", "Sidhe", "Sensitiva", "Templare",
            "Vampiro"
        }
    )
    public void testVampirizzazioneGiulietta(String nomeRuolo)
    {
        String nomeRomeo = "Brian", nomeGiulietta = "Elliott", nomeVampiro = "John";
        String[][] giocatori =
            new String[][] { { nomeVampiro, "Vampiro" }, { nomeGiulietta, "Giulietta" }, { "Carla", "Assassino" }, { nomeRomeo, nomeRuolo } };
        inizializzaPartita(giocatori);
        //romeizzazione(nomeRomeo);
        verificaAttaccoVampiroRiuscito(nomeGiulietta);
        attaccoAssassino(nomeGiulietta);
        terminaNotte();
        verificaEliminazione(nomeGiulietta);
        verificaNonEliminati(nomeRomeo);
    }

    @ParameterizedTest @CsvSource({ "Assassino", "Capo gilda", "Guardia corrotta", "Spia" })
    public void testVampirizzazioneGiuliettaCriminali(String nomeRuolo)
    {
        String nomeRomeo = "Brian", nomeGiulietta = "Elliott", nomeVampiro = "John", nomeLupo = "Carla";
        String[][] giocatori =
            new String[][] { { nomeVampiro, "Vampiro" }, { nomeGiulietta, "Giulietta" }, { nomeLupo, "Capo branco" }, { nomeRomeo, nomeRuolo } };
        inizializzaPartita(giocatori);
        romeizzazione(nomeRomeo);
        verificaAttaccoVampiroRiuscito(nomeGiulietta);
        attaccoLupi("Capo branco", nomeGiulietta);
        terminaNotte();
        verificaEliminazione(nomeGiulietta);
        verificaNonEliminati(nomeRomeo);
    }

    @Test public void testMaledizioneGuaritore()
    {
        String nomeGuaritore = "Jack", nomeMegera = "Megera";
        inizializzaPartita(new String[][] { { nomeGuaritore, "Guaritore" }, { nomeMegera, "Megera" }, { "Hal", "Assassino" } });
        attaccoAssassino(nomeMegera);
        guarisci(nomeMegera);
        terminaNotte();
        verificaNonEliminati(nomeGuaritore, nomeMegera);
        verificaMaledetto(nomeGuaritore);
        attaccoAssassino(nomeMegera);
        terminaNotte();
        verificaNonMaledetto(nomeGuaritore);
    }

    @ParameterizedTest @CsvSource({ "Goblin", "Guaritore", "Leprecauno", "Medium", "Negromante", "Sensitiva", "Sidhe" })
    public void testMaledizioneMago(String nomeRuolo)
    {
        String nomeMegera = "Marco", nomeMistico = "Emma", nomeMago = "Annalisa";
        inizializzaPartita(new String[][] { { nomeMegera, "Megera" }, { nomeMistico, nomeRuolo }, { nomeMago, "Mago" }, { "Ivan", "Assassino" } });
        verificaMistico(nomeMegera);
        verificaMaledetto(nomeMago);
        verificaControlloMago(nomeMegera, NON_MISTICO);
        attaccoAssassino(nomeMegera);
        terminaNotte();
        verificaNonMaledetto(nomeMago);
        verificaMistico(nomeMistico);
    }

    @Test public void testMaledizioneMegeraNonRomeizzata()
    {
        String nome = "Elisa";
        String[][] giocatori = new String[][] { { nome, "Megera" }, { "Alemanno", "Negromante" } };
        inizializzaPartita(giocatori);
        partita.attaccoNegromante(nome);
        for(String[] giocatore : giocatori) verificaMaledetto(giocatore[0]);
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro", "Bracconiere", "Cacciatore",
            "Cacciatore di vampiri", "Capo branco", "Capo gilda", "Cappuccetto rosso", "Contadino eroe", "Contadino discendente dei lupi",
            "Contadino mostro", "Contadino normale", "Ghoul", "Giovane lupo", "Giulietta", "Giullare", "Goblin", "Guardia", "Guardia corrotta",
            "Guaritore", "Inquisitore", "Leprecauno", "Lupo del branco", "Lupo reietto", "Lupo reietto", "Lupo solitario", "Mago", "Medium",
            "Megera", "Mercante", "Monaco", "Negromante", "Nonna", "Oratore", "Oste", "Pazzo", "Peccatore", "Sidhe", "Spia", "Sensitiva",
            "Templare", "Vampiro"
        }
    )
    public void testPoterePosseduto(String nomeRuolo)
    {
        String nomePosseduto = "Tommaso", nomeNuovoPosseduto = "Tania";
        inizializzaPartita(new String[][] { { "Elena", "Assassino" }, { nomePosseduto, "Posseduto" }, { nomeNuovoPosseduto, nomeRuolo } });
        attaccoAssassino(nomePosseduto);
        verificaCorrettezzaPossessione(nomeNuovoPosseduto);
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro", "Bracconiere", "Cacciatore",
            "Cacciatore di vampiri", "Capo branco", "Capo gilda", "Cappuccetto rosso", "Contadino eroe", "Contadino discendente dei lupi",
            "Contadino mostro", "Contadino normale", "Eremita", "Ghoul", "Giovane lupo", "Giulietta",
            "Giullare", "Goblin", "Guardia", "Guardia corrotta", "Guaritore", "Inquisitore", "Ladra", "Leprecauno", "Lupo del branco",
            "Lupo reietto", "Lupo solitario", "Mago", "Medium", "Megera", "Mercante", "Monaco", "Negromante", "Nonna", "Nosferatu", "Oratore",
            "Oste", "Pazzo", "Peccatore", "Prete", "Sidhe", "Spia", "Strega", "Sensitiva", "Templare", "Vampiro"
        }
    )
    public void testPoterePossedutoAngeloCustode(String nomeRuolo)
    {
        String nomeAngelo = "Noe", nome = "Banner", nomePosseduto = "Damiano";
        String[][] giocatori =
            new String[][] { { nomeAngelo, "Angelo custode" }, { "Ely", "Assassino" }, { nome, nomeRuolo }, { nomePosseduto, "Posseduto" } };
        inizializzaPartita(giocatori);
        segnalazioneAngeloCustode(nome);
        attaccoAssassino(nomePosseduto);
        partita.passaPosseduto(nomeAngelo);
        verificaFalso(isAmato(nome));
    }

    @ParameterizedTest @CsvSource({ "Capo branco", "Lupo del branco", "Lupo reietto", "Lupo solitario" })
    public void testPoterePossedutoAngeloCustodeAssassino(String tipoLupo)
    {
        String nomeAngelo = "Noe", nomeAssassino = "Ely", nomePosseduto = "Damiano";
        String[][] giocatori = new String[][]
        {
            { nomeAngelo, "Angelo custode" }, { nomeAssassino, "Assassino" }, { "Banner", tipoLupo }, { nomePosseduto, "Posseduto" }
        };
        inizializzaPartita(giocatori);
        segnalazioneAngeloCustode(nomeAssassino);
        attaccoLupi(tipoLupo, nomePosseduto);
        partita.passaPosseduto(nomeAngelo);
        verificaFalso(isAmato(nomeAssassino));
    }

    @ParameterizedTest @CsvSource({ "Capo branco", "Lupo del branco", "Lupo reietto", "Lupo solitario" })
    public void testPoterePossedutoPrete(String tipoLupo)
    {
        String nomePosseduto = "Alessandro", nomePrete = "Michelangelo";
        inizializzaPartita(new String[][] { { "Elena", tipoLupo }, { nomePosseduto, "Posseduto" }, { nomePrete, "Prete" } });
        attaccoPossedutoPrete(tipoLupo, nomePosseduto, nomePrete);
    }

    @ParameterizedTest @CsvSource({ "Capo branco", "Lupo del branco", "Lupo reietto", "Lupo solitario" })
    public void testPoterePossedutoAmatoPrete(String tipoLupo)
    {
        String nomeAngelo = "Sigismondo", nomePosseduto = "Alessandro", nomePrete = "Michelangelo";
        String[][] giocatori = new String[][]
        {
            { "Elena", tipoLupo }, { nomePosseduto, "Posseduto" }, { nomePrete, "Prete" }, { nomeAngelo, "Angelo custode" }
        };
        inizializzaPartita(giocatori);
        attaccoPossedutoPreteAmato(tipoLupo, nomePosseduto, nomeAngelo, nomePrete);
    }

    @ParameterizedTest @CsvSource({ "Capo branco", "Lupo del branco", "Lupo reietto", "Lupo solitario" })
    public void testPoterePossedutoAmatoPreteStregato(String tipoLupo)
    {
        String nomeAngelo = "Sigismondo", nomePosseduto = "Alessandro", nomePrete = "Michelangelo";
        String[][] giocatori = new String[][]
        {
            { "Elena", tipoLupo }, { nomePosseduto, "Posseduto" }, { nomePrete, "Prete" }, { nomeAngelo, "Angelo custode" },
            { "Gianmario", "Strega" }
        };
        inizializzaPartita(giocatori);
        protezioneStrega(nomePrete);
        attaccoPossedutoPreteAmato(tipoLupo, nomePosseduto, nomeAngelo, nomePrete);
    }

    @ParameterizedTest @CsvSource({ "Capo branco", "Lupo del branco", "Lupo reietto", "Lupo solitario" })
    public void testPoterePossedutoAmatoPreteRomeizzato(String tipoLupo)
    {
        String nomeAngelo = "Sigismondo", nomePosseduto = "Alessandro", nomePrete = "Michelangelo";
        String[][] giocatori = new String[][]
        {
            { "Elena", tipoLupo }, { nomePosseduto, "Posseduto" }, { nomePrete, "Prete" }, { nomeAngelo, "Angelo custode" },
            { "Rosalba", "Giulietta" }
        };
        inizializzaPartita(giocatori);
        //romeizzazione(nomePrete);
        attaccoPossedutoPreteAmato(tipoLupo, nomePosseduto, nomeAngelo, nomePrete);
    }

    @Test public void testPoterePossedutoPreteVampirizzato()
    {
        String nomePosseduto = "Ringo", nomePrete = "John";
        String[][] giocatori =
            new String[][] { { "Yoko", "Assassino" }, { nomePosseduto, "Posseduto" }, { nomePrete, "Prete" }, { "Mick", "Vampiro" } };
        inizializzaPartita(giocatori);
        attaccoAssassino(nomePosseduto);
        verificaAttaccoVampiroRiuscito(nomePrete);
        verificaCorrettezzaPossessione(nomePrete);
    }

    @Test public void testPoterePossedutoPreteNosferatizzato()
    {
        String nomePosseduto = "Ringo", nomePrete = "John", nomeLupo = "Gennaro";
        String[][] giocatori = new String[][]
        {
            { "Yoko", "Assassino" }, { nomePosseduto, "Posseduto" }, { nomePrete, "Prete" }, { "Mick", "Nosferatu" }, { nomeLupo, "Capo branco" }
        };
        inizializzaPartita(giocatori);
        attaccoAssassino(nomePosseduto);
        attaccoLupi("Capo branco", nomePrete);
        progenizzazioneNosferatu(nomePrete);
        verificaCorrettezzaPossessione(nomePrete);
    }

    @Test public void testVampirizzazionePosseduto()
    {
        String nomeVampiro = "Ale", nomePosseduto = "Franz";
        inizializzaPartita(new String[][] { { nomeVampiro, "Vampiro" }, { nomePosseduto, "Posseduto" } });
        verificaFallimentoVampirizzazionePosseduto(nomePosseduto, nomeVampiro);
    }

    @Test public void testVampirizzazionePossedutoAmato()
    {
        String nomeVampiro = "Ale", nomePosseduto = "Franz";
        inizializzaPartita(new String[][] { { nomeVampiro, "Vampiro" }, { nomePosseduto, "Posseduto" }, { "Nino", "Angelo custode" } });
        verificaFallimentoVampirizzazionePossedutoAmato(nomePosseduto, nomeVampiro);
    }

    @Test public void testVampirizzazionePossedutoAmatoVampiroRomeo()
    {
        String nomeVampiro = "Ale", nomePosseduto = "Franz";
        inizializzaPartita(new String[][] { { nomeVampiro, "Vampiro" }, { nomePosseduto, "Posseduto" }, { "Nino", "Angelo custode" } });
        //romeizzazione(nomeVampiro);
        verificaFallimentoVampirizzazionePossedutoAmato(nomePosseduto, nomeVampiro);
    }

    @Test public void testVampirizzazionePossedutoAmatoVampiroStregato()
    {
        String nomeVampiro = "Ale", nomePosseduto = "Franz";
        inizializzaPartita(new String[][] { { nomeVampiro, "Vampiro" }, { nomePosseduto, "Posseduto" }, { "Nino", "Angelo custode" } });
        protezioneStrega(nomeVampiro);
        verificaFallimentoVampirizzazionePossedutoAmato(nomePosseduto, nomeVampiro);
    }

    @ParameterizedTest @CsvSource({ "Capo branco", "Lupo del branco", "Lupo reietto", "Lupo solitario" })
    public void testNosferatizzazionePossedutoAmato(String tipoLupo)
    {
        String nomeNosferatu = "Ale", nomePosseduto = "Franz", nomeAngelo = "Nino";
        String[][] giocatori =
            new String[][] { { nomeNosferatu, "Nosferatu" }, { nomePosseduto, "Posseduto" }, { nomeAngelo, "Angelo custode" }, { "Mario", tipoLupo } };
        inizializzaPartita(giocatori);
        verificaFallimentoNosferatizzazionePossedutoAmato(nomeAngelo, nomePosseduto, nomeNosferatu, tipoLupo);
    }

    @ParameterizedTest @CsvSource({ "Capo branco", "Lupo del branco", "Lupo reietto", "Lupo solitario" })
    public void testNosferatizzazionePossedutoAmatoNosferatuRomeo(String tipoLupo)
    {
        String nomeNosferatu = "Ale", nomePosseduto = "Franz", nomeAngelo = "Nino";
        String[][] giocatori = new String[][]
        {
            { nomeNosferatu, "Nosferatu" }, { nomePosseduto, "Posseduto" }, { nomeAngelo, "Angelo custode" }, { "Andrea", tipoLupo },
            { "Luca", "Giulietta" }
        };
        inizializzaPartita(giocatori);
        //romeizzazione(nomeNosferatu);
        verificaFallimentoNosferatizzazionePossedutoAmato(nomeAngelo, nomePosseduto, nomeNosferatu, tipoLupo);
    }

    @ParameterizedTest @CsvSource({ "Capo branco", "Lupo del branco", "Lupo reietto", "Lupo solitario" })
    public void testNosferatizzazionePossedutoAmatoNosferatuStregato(String tipoLupo)
    {
        String nomeNosferatu = "Ale", nomePosseduto = "Franz", nomeAngelo = "Nino";
        String[][] giocatori =
            new String[][] { { nomeNosferatu, "Nosferatu" }, { nomePosseduto, "Posseduto" }, { nomeAngelo, "Angelo custode" }, { "Bob", tipoLupo } };
        inizializzaPartita(giocatori);
        protezioneStrega(nomeNosferatu);
        verificaFallimentoNosferatizzazionePossedutoAmato(nomeAngelo, nomePosseduto, nomeNosferatu, tipoLupo);
    }

    @Test public void testPossessioneLadraNonRiuscita()
    {
        String nomeLadra = "Piera", nomePosseduto = "Assunta";
        inizializzaPartita(new String[][] { { nomeLadra, "Ladra" }, { "Giuseppe", "Assassino" }, { nomePosseduto, "Posseduto" } });
        attaccoAssassino(nomePosseduto);
        assertThatIllegalArgumentException().isThrownBy(() -> passaPosseduto(nomeLadra)).withMessage("Impossibile possedere Piera.");
    }

    @ParameterizedTest @MethodSource("getEsempiAttacchiContadini")
    public void testAttaccoLupiContadino(String tipoContadino, String tipoLupo, String messaggio)
    {
        String nomeLupo = "Iris", nomeVittima = "Filippo";
        inizializzaPartita(new String[][]{ { nomeLupo, tipoLupo }, { nomeVittima, tipoContadino } });
        verificaEccezioneAttaccoContadino(messaggio, tipoLupo, nomeVittima, nomeLupo);
    }

    @ParameterizedTest @MethodSource("getEsempiAttacchiContadini")
    public void testAttaccoLupiContadinoAmato(String tipoContadino, String tipoLupo, String messaggio)
    {
        String nomeLupo = "Iris", nomeVittima = "Filippo";
        inizializzaPartita(new String[][]{ { nomeLupo, tipoLupo }, { nomeVittima, tipoContadino } });
        segnalazioneAngeloCustode(nomeLupo);
        verificaEccezioneAttaccoContadino(messaggio, tipoLupo, nomeVittima, nomeLupo);
    }

    @Test public void testAttaccoCapoBrancoNonna()
    {
        String nomeLupo = "Ciro", tipoLupo = "Capo branco", nomeVittima = "Federica", nomeCacciatore = "Alfredo";
        inizializzaPartita(new String[][] { { nomeLupo, tipoLupo }, { nomeVittima, "Nonna" }, { nomeCacciatore, "Cacciatore" } });
        String messaggioNonna =
            "Il Capo branco (Ciro) ha beccato la Nonna (Federica).\nSveglia Federica e avvisa i due giocatori che Ciro è eliminato e che " +
            "Federica è il Capo branco.";
        verificaAttaccoNonna(tipoLupo, nomeVittima, messaggioNonna, nomeLupo);
        verificaCapoBranco(nomeVittima);
        verificaProtezioneCacciatore(tipoLupo, nomeCacciatore);
    }

    @Test public void testAttaccoCapoBrancoAmatoNonna()
    {
        String nomeLupo = "Ciro", tipoLupo = "Capo branco", nomeVittima = "Federica", nomeCacciatore = "Alfredo";
        String[][] giocatori =
            new String[][] { { nomeLupo, tipoLupo }, { nomeVittima, "Nonna" }, { nomeCacciatore, "Cacciatore" }, { "Pino", "Angelo custode" } };
        inizializzaPartita(giocatori);
        String messaggio =
            "Il Capo branco (Ciro) ha beccato la Nonna (Federica).\nSveglia Federica e avvisa i due giocatori che Ciro è eliminato e che " +
            "Federica è il Capo branco.";
        segnalazioneAngeloCustode(nomeLupo);
        verificaAttaccoNonna(tipoLupo, nomeVittima, messaggio, nomeLupo);
        verificaCapoBranco(nomeVittima);
        verificaProtezioneCacciatore(tipoLupo, nomeCacciatore);
    }

    @Test public void testAttaccoLupoBrancoNonna()
    {
        String nomeLupo = "Ciro", tipoLupo = "Lupo del branco", nomeVittima = "Federica", nomeCacciatore = "Sandra";
        inizializzaPartita(new String[][] { { nomeLupo, tipoLupo }, { nomeVittima, "Nonna" }, { nomeCacciatore, "Cacciatore" } });
        String messaggio =
            "Il Lupo del branco (Ciro) ha beccato la Nonna (Federica).\nSveglia Federica e avvisa i due giocatori che Ciro è eliminato e che " +
            "Federica è il Lupo del branco.";
        verificaAttaccoNonna(tipoLupo, nomeVittima, messaggio, nomeLupo);
        verificaLupoBranco(nomeVittima);
        verificaProtezioneCacciatore(tipoLupo, nomeCacciatore);
    }

    @Test public void testAttaccoLupoBrancoAmatoNonna()
    {
        String nomeLupo = "Ciro", tipoLupo = "Lupo del branco", nomeVittima = "Federica", nomeCacciatore = "Giorgia";
        inizializzaPartita(new String[][] { { nomeLupo, tipoLupo }, { nomeVittima, "Nonna" }, { nomeCacciatore, "Cacciatore" } });
        segnalazioneAngeloCustode(nomeLupo);
        String messaggio =
            "Il Lupo del branco (Ciro) ha beccato la Nonna (Federica).\nSveglia Federica e avvisa i due giocatori che Ciro è eliminato e che " +
            "Federica è il Lupo del branco.";
        verificaAttaccoNonna(tipoLupo, nomeVittima, messaggio, nomeLupo);
        verificaLupoBranco(nomeVittima);
        verificaProtezioneCacciatore(tipoLupo, nomeCacciatore);
    }

    @Test public void testAttaccoLupoReiettoNonna()
    {
        String nomeLupo = "Ciro", tipoLupo = "Lupo reietto", nomeVittima = "Federica", nomeCacciatore = "Lucia";
        inizializzaPartita(new String[][] { { nomeLupo, tipoLupo }, { nomeVittima, "Nonna" }, { nomeCacciatore, "Cacciatore" } });
        String messaggio =
            "Il Lupo reietto (Ciro) ha beccato la Nonna (Federica).\nSveglia Federica e avvisa i due giocatori che Ciro è eliminato e che " +
            "Federica è il Lupo reietto.";
        verificaAttaccoNonna(tipoLupo, nomeVittima, messaggio, nomeLupo);
        verificaLupoReietto(nomeVittima);
        verificaProtezioneCacciatore(tipoLupo, nomeCacciatore);
    }

    @Test public void testAttaccoLupoReiettoAmatoNonna()
    {
        String nomeLupo = "Ciro", tipoLupo = "Lupo reietto", nomeVittima = "Federica", nomeCacciatore = "Lucia";
        inizializzaPartita(new String[][] { { nomeLupo, tipoLupo }, { nomeVittima, "Nonna" }, { nomeCacciatore, "Cacciatore" } });
        segnalazioneAngeloCustode(nomeLupo);
        String messaggio =
            "Il Lupo reietto (Ciro) ha beccato la Nonna (Federica).\nSveglia Federica e avvisa i due giocatori che Ciro è eliminato e che " +
            "Federica è il Lupo reietto.";
        verificaAttaccoNonna(tipoLupo, nomeVittima, messaggio, nomeLupo);
        verificaLupoReietto(nomeVittima);
        verificaProtezioneCacciatore(tipoLupo, nomeCacciatore);
    }

    @Test public void testAttaccoLupoSolitarioNonna()
    {
        String nomeLupo = "Ciro", tipoLupo = "Lupo solitario", nomeVittima = "Federica", nomeCacciatore = "Lucia";
        inizializzaPartita(new String[][] { { nomeLupo, tipoLupo }, { nomeVittima, "Nonna" }, { nomeCacciatore, "Cacciatore" } });
        String messaggio =
            "Il Lupo solitario (Ciro) ha beccato la Nonna (Federica).\nSveglia Federica e avvisa i due giocatori che Ciro è eliminato e che " +
            "Federica è il Lupo solitario.";
        verificaAttaccoNonna(tipoLupo, nomeVittima, messaggio, nomeLupo);
        verificaLupoSolitario(nomeVittima);
        verificaProtezioneCacciatore(tipoLupo, nomeCacciatore);
    }

    @Test public void testAttaccoLupoSolitarioAmatoNonna()
    {
        String nomeLupo = "Ciro", tipoLupo = "Lupo solitario", nomeVittima = "Federica", nomeCacciatore = "Lucia";
        String[][] giocatori =
            new String[][] { { nomeLupo, tipoLupo }, { nomeVittima, "Nonna" }, { nomeCacciatore, "Cacciatore" }, { "John", "Angelo custode" } };
        inizializzaPartita(giocatori);
        segnalazioneAngeloCustode(nomeLupo);
        String messaggio =
            "Il Lupo solitario (Ciro) ha beccato la Nonna (Federica).\nSveglia Federica e avvisa i due giocatori che Ciro è eliminato e che " +
            "Federica è il Lupo solitario.";
        verificaAttaccoNonna(tipoLupo, nomeVittima, messaggio, nomeLupo);
        verificaLupoSolitario(nomeVittima);
        verificaProtezioneCacciatore(tipoLupo, nomeCacciatore);
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Capo branco, LUPO_BRANCO, 'Il Contadino discendente dei lupi (Mario) è stato attaccato dai Lupi del branco, pertanto adesso fa " +
            "parte della loro fazione.\nSveglia Mario e fagli riconoscere gli altri lupi.'",
            "Lupo del branco, LUPO_BRANCO, 'Il Contadino discendente dei lupi (Mario) è stato attaccato dai Lupi del branco, pertanto adesso " +
            "fa parte della loro fazione.\nSveglia Mario e fagli riconoscere gli altri lupi.'",
            "Lupo reietto, LUPO_BRANCO, 'Il Contadino discendente dei lupi (Mario) è stato attaccato dai Lupi del branco, pertanto adesso fa " +
            "parte della loro fazione.\nSveglia Mario e fagli riconoscere gli altri lupi.'",
            "Lupo solitario, LUPO_SOLITARIO, 'Il Contadino discendente dei lupi (Mario) è stato attaccato dal Lupo solitario, pertanto anche " +
            "lui diventa tale.\nSveglia Mario e fagli riconoscere il Lupo solitario che lo ha attaccato.'"
        }
    )
    public void testLupizzazioneContadino(String tipoLupo, Fazione fazione, String messaggio)
    {
        String nomeContadino = "Mario";
        String[][] giocatori =
            new String[][] { { nomeContadino, "Contadino discendente dei lupi" }, { "Piera", tipoLupo }, { "Sofia", "Angelo custode" } };
        inizializzaPartita(giocatori);
        segnalazioneAngeloCustode(nomeContadino);
        verificaVittimaSbagliata(tipoLupo, nomeContadino, messaggio);
        assertThat(partita.getAura(nomeContadino)).isEqualTo(NERA);
        assertThat(partita.getFazione(nomeContadino)).isEqualTo(fazione);
    }

    @Test public void testRomeizzazioneAngeloCustode()
    {
        String nomeRomeo = "Piero";
        inizializzaPartita(new String[][] { { nomeRomeo, "Angelo custode" }, { "Alberto", "Giulietta" } });
        //romeizzazione(nomeRomeo);
        verificaControlloVeggente(nomeRomeo, BIANCA);
        //verificaVero(partita.isRomeo(nomeRomeo));
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Angelo custode", "Assassino", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro",
            "Bracconiere", "Cacciatore", "Cacciatore di vampiri", "Capo branco", "Capo gilda", "Cappuccetto rosso", "Contadino eroe",
            "Contadino discendente dei lupi", "Contadino mostro", "Eremita", "Ghoul", "Giovane lupo", "Giulietta", "Giullare", "Guardia",
            "Guardia corrotta", "Ladra", "Lupo del branco", "Lupo reietto", "Lupo solitario", "Mercante", "Monaco", "Nonna", "Nosferatu",
            "Oratore", "Oste", "Pazzo", "Peccatore", "Posseduto", "Prete", "Spia", "Templare", "Vampiro"
        }
    )
    public void testSegnalazioneInquisitoreNonRiuscitaBallottaggio(String nomeRuolo)
    {
        String nome = "Romina", nomeInquisitore = "Immanuel";
        inizializzaPartita(new String[][] { { nome, nomeRuolo }, { nomeInquisitore, "Inquisitore" }, { "Stefano", "Guaritore" } });
        segnalazioneInquisitore(nome);
        incrementaVoti(nome, 1);
        incrementaVoti(nomeInquisitore, 1);
        terminaVotazioni();
        verificaAccusati(nomeInquisitore, nome);
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Angelo custode", "Assassino", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro",
            "Bracconiere", "Cacciatore", "Cacciatore di vampiri", "Capo branco", "Capo gilda", "Cappuccetto rosso", "Contadino eroe",
            "Contadino discendente dei lupi", "Contadino mostro", "Eremita", "Ghoul", "Giovane lupo", "Giulietta", "Giullare", "Guardia",
            "Guardia corrotta", "Ladra", "Lupo del branco", "Lupo reietto", "Lupo solitario", "Mercante", "Monaco", "Nonna", "Nosferatu",
            "Oratore", "Oste", "Pazzo", "Peccatore", "Posseduto", "Prete", "Spia", "Templare", "Vampiro"
        }
    )
    public void testSegnalazioneInquisitoreNonRiuscitaNonBallottaggio(String nomeRuolo)
    {
        String nome = "Romina", nomeVittima = "Stefano", nomeInquisitore = "Immanuel";
        inizializzaPartita(new String[][] { { nome, nomeRuolo }, { nomeInquisitore, "Inquisitore" }, { nomeVittima, "Guaritore" } });
        segnalazioneInquisitore(nome);
        incrementaVoti(nomeInquisitore, 2);
        incrementaVoti(nomeVittima, 2);
        terminaVotazioni();
        verificaAccusati(nomeInquisitore, nomeVittima);
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Angelo custode", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro", "Bracconiere",
            "Cacciatore", "Cacciatore di vampiri", "Capo branco", "Capo gilda", "Cappuccetto rosso", "Contadino eroe",
            "Contadino discendente dei lupi", "Contadino normale", "Ghoul", "Giovane lupo", "Giulietta", "Giullare", "Guardia", "Guardia corrotta",
            "Guaritore", "Inquisitore", "Lupo del branco", "Lupo reietto", "Lupo solitario", "Mago", "Medium", "Megera", "Mercante", "Monaco",
            "Negromante", "Nonna", "Nosferatu", "Oratore", "Oste", "Pazzo", "Peccatore", "Posseduto", "Prete", "Spia", "Sensitiva", "Templare",
            "Vampiro"
        }
    )
    public void testFunzionamentoNegromante(String nomeRuolo)
    {
        String nomeNegromante = "Circe", nomeAssassino = "Agamennone", nome = "Christopher";
        inizializzaPartita(new String[][] { { "Circe", "Negromante" }, { nomeAssassino, "Assassino" }, { nome, nomeRuolo } });
        attaccoNegromante(nomeAssassino);
        attaccoNegromante(nome);
        verificaMaledetto(nomeAssassino);
        verificaMaledetto(nome);
        attaccoAssassino(nomeNegromante);
        terminaNotte();
        verificaNonMaledetto(nomeAssassino);
        verificaNonMaledetto(nome);
    }

    @ParameterizedTest @MethodSource("getEsempioCombinazioni")
    public void testNosferatizzazioneAngeloCustode(String nomeRuolo, String tipoLupo)
    {
        String nomeAngelo = "Miriam", nome = "Sara";
        String[][] giocatori =
            new String[][] { { nomeAngelo, "Angelo custode" }, { nome, nomeRuolo }, { "Gianella", tipoLupo }, { "Giuliano", "Nosferatu" } };
        inizializzaPartita(giocatori);
        segnalazioneAngeloCustode(nome);
        attaccoLupi(tipoLupo, nomeAngelo);
        progenizzazioneNosferatu(nomeAngelo);
        verificaFalso(isAmato(nome));
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro", "Bracconiere", "Cacciatore",
            "Cacciatore di vampiri", "Capo branco", "Capo gilda", "Cappuccetto rosso", "Contadino eroe", "Contadino discendente dei lupi",
            "Contadino mostro", "Contadino normale", "Eremita", "Ghoul", "Giovane lupo", "Giullare", "Goblin", "Guardia", "Guardia corrotta",
            "Guaritore", "Inquisitore", "Ladra", "Leprecauno", "Lupo del branco", "Lupo reietto", "Lupo solitario", "Mago", "Medium", "Megera",
            "Mercante", "Monaco", "Negromante", "Nonna", "Oratore", "Oste", "Pazzo", "Peccatore", "Posseduto", "Prete", "Sidhe", "Spia", "Strega",
            "Sensitiva", "Templare", "Vampiro"
        }
    )
    public void testNosferatizzazioneAngeloCustodeRomeizzato(String nomeRuolo)
    {
        String nomeAngelo = "Miriam", nome = "Sara";
        String[][] giocatori = new String[][]
        {
            { "Alfonso", "Assassino" }, { nomeAngelo, "Angelo custode" }, { nome, nomeRuolo }, { "Giuliano", "Nosferatu" },
            { "Pino", "Giulietta" }
        };
        inizializzaPartita(giocatori);
        romeizzazione(nomeAngelo);
        nosferatizzazioneAngeloCustodeAmatoProtetto(nome, nomeAngelo);
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro", "Bracconiere", "Cacciatore",
            "Cacciatore di vampiri", "Capo branco", "Capo gilda", "Cappuccetto rosso", "Contadino eroe", "Contadino discendente dei lupi",
            "Contadino mostro", "Contadino normale", "Eremita", "Ghoul", "Giovane lupo", "Giulietta", "Giullare", "Goblin", "Guardia",
            "Guardia corrotta", "Guaritore", "Inquisitore", "Ladra", "Leprecauno", "Lupo del branco", "Lupo reietto", "Lupo solitario", "Mago",
            "Medium", "Megera", "Mercante", "Monaco", "Negromante", "Nonna", "Oratore", "Oste", "Pazzo", "Peccatore", "Posseduto", "Prete", "Sidhe",
            "Spia", "Sensitiva", "Templare", "Vampiro"
        }
    )
    public void testNosferatizzazioneAngeloCustodeStregato(String nomeRuolo)
    {
        String nomeAngelo = "Miriam", nome = "Sara";
        String[][] giocatori = new String[][]
        {
            { "Alfonso", "Assassino" }, { nomeAngelo, "Angelo custode" }, { nome, nomeRuolo }, { "Giuliano", "Nosferatu" }, { "Pino", "Strega" }
        };
        inizializzaPartita(giocatori);
        protezioneStrega(nomeAngelo);
        nosferatizzazioneAngeloCustodeAmatoProtetto(nome, nomeAngelo);
    }

    @Test public void testAttaccoLupoSolitarioCappuccettoRossoAmatoSenzaAngeloCustode()
    {
        String nomeVittima = "Leonardo", tipoLupo = "Lupo solitario", nomeAngelo = "Gianni";
        inizializzaPartita(new String[][] { { nomeVittima, "Cappuccetto rosso" }, { "Dante", tipoLupo }, { nomeAngelo, "Angelo custode" } });
        segnalazioneAngeloCustode(nomeVittima);
        incrementaVoti(nomeAngelo, 3);
        terminaVotazioni();
        incrementaVoti(nomeAngelo, 4);
        terminaBallottaggio();
        String messaggio = "Dante è il Lupo solitario. Cappuccetto rosso (Leonardo) si sveglia e lo riconosce";
        assertThatIllegalStateException().isThrownBy(() -> attaccoLupi(tipoLupo, nomeVittima)).withMessage(messaggio);
        terminaNotte();
        verificaEliminati(nomeVittima);
    }

    @Test public void testAttaccoLupoSolitarioCappuccettoRossoAmatoConAngeloCustode()
    {
        String nomeVittima = "Leonardo", tipoLupo = "Lupo solitario", nomeAngelo = "Gianni";
        inizializzaPartita(new String[][] { { nomeVittima, "Cappuccetto rosso" }, { "Dante", tipoLupo }, { nomeAngelo, "Angelo custode" } });
        segnalazioneAngeloCustode(nomeVittima);
        String messaggio = "Dante è il Lupo solitario. Cappuccetto rosso (Leonardo) si sveglia e lo riconosce";
        assertThatIllegalStateException().isThrownBy(() -> attaccoLupi(tipoLupo, nomeVittima)).withMessage(messaggio);
        terminaNotte();
        verificaNonEliminati(nomeVittima);
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Capo branco, 'Dal momento che non ci sono altri lupi del branco, il Cappuccetto rosso (Beatrice) riconosce il Capo branco (Dante).'",
            "Lupo del branco, 'Dal momento che non ci sono altri lupi del branco, il Cappuccetto rosso (Beatrice) riconosce il Lupo del branco " +
            "(Dante).'",
            "Lupo reietto, 'Dal momento che non ci sono altri lupi del branco, il Cappuccetto rosso (Beatrice) riconosce il Lupo reietto " +
            "(Dante).'",
            "Lupo solitario, 'Dante è il Lupo solitario. Cappuccetto rosso (Beatrice) si sveglia e lo riconosce'"
        }
    )
    public void testAttaccoUltimoLupoCappuccettoRossoAmato(String tipoLupo, String messaggio)
    {
        String nomeVittima = "Beatrice";
        String[][] giocatori = new String[][]
        {
            { nomeVittima, "Cappuccetto rosso" }, { "Dante", tipoLupo }, { "Virgilio", "Nonna" }, { "Adele", "Angelo custode" }
        };
        inizializzaPartita(giocatori);
        segnalazioneAngeloCustode(nomeVittima);
        assertThatIllegalStateException().isThrownBy(() -> attaccoLupi(tipoLupo, nomeVittima)).withMessage(messaggio);
        terminaNotte();
        verificaNonEliminati(nomeVittima);
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Capo branco, 'Dal momento che non ci sono altri lupi del branco, il Cappuccetto rosso (Beatrice) riconosce il Capo branco (Dante).'",
            "Lupo del branco, 'Dal momento che non ci sono altri lupi del branco, il Cappuccetto rosso (Beatrice) riconosce il Lupo del branco " +
            "(Dante).'",
            "Lupo reietto, 'Dal momento che non ci sono altri lupi del branco, il Cappuccetto rosso (Beatrice) riconosce il Lupo reietto " +
            "(Dante).'",
            "Lupo solitario, 'Dante è il Lupo solitario. Cappuccetto rosso (Beatrice) si sveglia e lo riconosce'"
        }
    )
    public void testAttaccoUltimoLupoCappuccettoRossoAmatoSenzaNonna(String tipoLupo, String messaggio)
    {
        String nomeVittima = "Beatrice";
        String[][] giocatori = new String[][] { { nomeVittima, "Cappuccetto rosso" }, { "Dante", tipoLupo }, { "Adele", "Angelo custode" } };
        inizializzaPartita(giocatori);
        segnalazioneAngeloCustode(nomeVittima);
        assertThatIllegalStateException().isThrownBy(() -> attaccoLupi(tipoLupo, nomeVittima)).withMessage(messaggio);
        terminaNotte();
        verificaNonEliminati(nomeVittima);
    }

    private void verificaLupoBranco(String nome) { verificaVero(partita.isLupoBranco(nome)); }

    private void verificaCapoBranco(String nome) { verificaVero(partita.isCapoBranco(nome)); }

    private void verificaLupoReietto(String nome) { verificaVero(partita.isLupoReietto(nome)); }

    private void verificaLupoSolitario(String nome) { verificaVero(partita.isLupoSolitario(nome)); }

    private void verificaProtezioneCacciatore(String tipoLupo, String nomeCacciatore)
    {
        terminaNotte();
        String messaggioCacciatore = nomeCacciatore + " è il Cacciatore ed è protetto dall'attacco del lupo ex Nonna.\nAvvisa i lupi dell'attacco fallito.";
        assertThatIllegalStateException().isThrownBy(() -> attaccoLupi(tipoLupo, nomeCacciatore)).withMessage(messaggioCacciatore);
        verificaNonEliminati(nomeCacciatore);
    }

    private void nosferatizzazioneAngeloCustodeAmatoProtetto(String nome, String nomeAngelo)
    {
        segnalazioneAngeloCustode(nome);
        attaccoAssassino(nomeAngelo);
        progenizzazioneNosferatu(nomeAngelo);
        verificaAmato(nome);
    }

    private void verificaAmato(String nome) { verificaVero(isAmato(nome)); }

    private boolean isAmato(String nome) { return partita.isAmato(nome); }

    private static Stream<Arguments> getEsempioCombinazioni()
    {
        String[] tipiLupo = { "Capo branco", "Lupo del branco", "Lupo reietto", "Lupo solitario" }, altriRuoli =
        {
            "Altra guardia", "Angelo custode", "Assassino", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro",
            "Bracconiere", "Cacciatore", "Cacciatore di vampiri", "Capo gilda", "Contadino eroe", "Contadino mostro", "Contadino normale",
            "Eremita", "Ghoul", "Giullare", "Goblin", "Guardia", "Guardia corrotta", "Guaritore", "Inquisitore", "Ladra", "Leprecauno", "Mago",
            "Medium", "Megera", "Mercante", "Monaco", "Negromante", "Nonna", "Oratore", "Oste", "Pazzo", "Peccatore", "Posseduto", "Prete",
            "Sidhe", "Spia", "Strega", "Sensitiva", "Templare", "Vampiro"
        };
        List<Arguments> argomenti = new ArrayList<>();
        for(String nomeRuolo : altriRuoli) for(String tipoLupo : tipiLupo) argomenti.add(Arguments.of(nomeRuolo, tipoLupo));
        return argomenti.stream();
    }

    private void verificaFallimentoVampirizzazionePossedutoAmato(String nomePosseduto, String nomeVampiro)
    {
        segnalazioneAngeloCustode(nomePosseduto);
        verificaFallimentoVampirizzazionePosseduto(nomePosseduto, nomeVampiro);
    }

    private void verificaFallimentoNosferatizzazionePossedutoAmato(String nomeAngelo, String nomePosseduto, String nomeNosferatu, String tipoLupo)
    {
        segnalazioneAngeloCustode(nomePosseduto);
        attaccoLupi(tipoLupo, nomeAngelo);
        terminaNotte();
        verificaFallimentoNosferatizzazionePosseduto(nomePosseduto, nomeNosferatu, tipoLupo);
    }

    private void verificaFallimentoVampirizzazionePosseduto(String nomePosseduto, String nomeVampiro)
    {
        String messaggio =
            "Il Vampiro (" + nomeVampiro + ") non può vampirizzare il Posseduto (" + nomePosseduto + ").\n" + nomeVampiro +
            " diventerà il Posseduto e " + nomePosseduto + " che morirà.";
        assertThatIllegalArgumentException().isThrownBy(() -> attaccoVampiro(nomePosseduto)).withMessage(messaggio);
        verificaEliminazione(nomePosseduto);
        verificaVero(partita.isPosseduto(nomeVampiro));
    }

    private void verificaFallimentoNosferatizzazionePosseduto(String nomePosseduto, String nomeNosferatu, String tipoLupo)
    {
        String messaggio =
            "Il Nosferatu (" + nomeNosferatu + ") non può progenizzare il Posseduto (" + nomePosseduto + ").\n" + nomeNosferatu +
            " diventerà il Posseduto e " + nomePosseduto + " che morirà.";
        attaccoLupi(tipoLupo, nomePosseduto);
        assertThatIllegalArgumentException().isThrownBy(() -> progenizzazioneNosferatu(nomePosseduto)).withMessage(messaggio);
        verificaEliminazione(nomePosseduto);
        verificaVero(partita.isPosseduto(nomeNosferatu));
    }

    private void attaccoPossedutoPreteAmato(String tipoLupo, String nomePosseduto, String nomeAngelo, String nomePrete)
    {
        segnalazioneAngeloCustode(nomePosseduto);
        attaccoLupi(tipoLupo, nomeAngelo);
        terminaNotte();
        attaccoPossedutoPrete(tipoLupo, nomePosseduto, nomePrete);
    }

    private void attaccoPossedutoPrete(String tipoLupo, String nomePosseduto, String nomePrete)
    {
        attaccoLupi(tipoLupo, nomePosseduto);
        assertThatIllegalArgumentException().isThrownBy(() -> passaPosseduto(nomePrete)).withMessage("Impossibile possedere il Prete.");
    }

    private void verificaMorteGhoul(String tipoLupo, String nomeVittima, String messaggio, String nomeGhoul)
    {
        attaccoLupi(tipoLupo, nomeVittima);
        verificaMortePostAttaccoNosferatu(nomeVittima, messaggio, nomeGhoul);
    }

    private static Stream<Arguments> getEsempioAttaccoAmato()
    {
        String[] tipiLupo = { "Capo branco", "Lupo del branco", "Lupo reietto", "Lupo solitario" };
        String[] nomiRuoli =
        {
            "Altra guardia", "Assassino", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro", "Bracconiere",
            "Cacciatore", "Cacciatore di vampiri", "Capo gilda", "Contadino eroe", "Contadino mostro", "Contadino normale", "Eremita", "Ghoul",
            "Giulietta", "Giullare", "Goblin", "Guardia", "Guardia corrotta", "Guaritore", "Inquisitore", "Ladra", "Leprecauno", "Mago", "Medium",
            "Megera", "Mercante", "Monaco", "Negromante", "Nonna", "Nosferatu", "Oratore", "Oste", "Pazzo", "Peccatore", "Posseduto", "Prete",
            "Sidhe", "Spia", "Strega", "Sensitiva", "Templare", "Vampiro"
        };
        List<Arguments> argomenti = new ArrayList<>();
        for(String tipoLupo : tipiLupo) for(String nomeRuolo : nomiRuoli)
        {
            String messaggio =
                "Il " + tipoLupo + " (Maria) non può attaccare il " + nomeRuolo + " amato (Giuseppe).\nAvvisa l'Angelo custode (Erode) della " +
                "sua morte.";
            argomenti.add(Arguments.of(tipoLupo, nomeRuolo, messaggio));
        }
        return argomenti.stream();
    }

    private void guarisci(String nome) { partita.guarisci(nome); }

    private void verificaMorteCapoGilda(String nomeVittima, String messaggio, String nomeCapoGilda)
    {
        verificaFallimentoGildata(nomeVittima, messaggio);
        terminaNotte();
        verificaEliminazione(nomeCapoGilda);
    }

    private void verificaAttaccoNonna(String tipoLupo, String nomeVittima, String messaggio, String nomeLupo)
    {
        verificaVittimaSbagliata(tipoLupo, nomeVittima, messaggio);
        verificaEliminati(nomeLupo);
    }

    private void verificaEccezioneAttaccoContadino(String messaggio, String tipoLupo, String nomeVittima, String nomeLupo)
    {
        verificaVittimaSbagliata(tipoLupo, nomeVittima, messaggio);
        terminaNotte();
        verificaEliminati(nomeLupo, nomeVittima);
    }

    private void verificaVittimaSbagliata(String tipoLupo, String nomeVittima, String messaggio)
    {
        assertThatIllegalArgumentException().isThrownBy(() -> attaccoLupi(tipoLupo, nomeVittima)).withMessage(messaggio);
    }

    private static Stream<Arguments> getEsempiAttacchiContadini()
    {
        String[][] contadini =
        {
            {
                "Contadino eroe",
                "L'attacco al Contadino eroe (Filippo) causa la morte anche del lupo attaccante (Iris).\nAvvisa entrambi i giocatori della loro " +
                "morte."
            },
            {
                "Contadino mostro",
                "L'attacco al Contadino mostro (Filippo) causa la morte anche del lupo attaccante (Iris).\nAvvisa entrambi i giocatori della " +
                "loro morte."
            }
        };
        String[] lupi = { "Capo branco", "Lupo del branco", "Lupo reietto", "Lupo solitario" };
        List<Arguments> argomenti = new ArrayList<>();
        for(String[] contadino : contadini) for(String lupo : lupi) argomenti.add(Arguments.of(contadino[0], lupo, contadino[1]));
        return argomenti.stream();
    }

    private void verificaAttaccoLupiAngeloCustodeFallito(String tipoLupo, String nome, String messaggio)
    {
        assertThatIllegalStateException().isThrownBy(() -> attaccoLupi(tipoLupo, nome)).withMessage(messaggio);
        verificaNonEliminati(nome);
    }

    private void protezioneStrega(String nome) { partita.protezioneStrega(nome); }

    private void romeizzazione(String nome) { partita.romeizzazione(nome); }

    private void verificaCorrettezzaPossessione(String nome)
    {
        passaPosseduto(nome);
        verificaVero(partita.isPosseduto(nome));
    }

    private void passaPosseduto(String nome) { partita.passaPosseduto(nome); }

    private void verificaNonMaledetto(String nome) { verificaFalso(isMaledetto(nome)); }

    private void verificaMistico(String nomeMegera) { verificaControlloMago(nomeMegera, MISTICO); }

    private void verificaControlloMago(String nomeMistico, Misticismo misticismo)
    {
        assertThat(partita.controlloMago(nomeMistico)).isEqualTo(misticismo);
    }

    private void verificaMaledetto(String nome) { verificaVero(isMaledetto(nome)); }

    private boolean isMaledetto(String nomeMago) { return partita.isMaledetto(nomeMago); }

    private void verificaMortePostAttaccoVampiro(String nomeVittima, String messaggio, String nomeMorto)
    {
        verificaFallimentoVampirizzazione(nomeVittima, messaggio);
        terminaNotte();
        verificaEliminati(nomeMorto);
    }

    private void verificaMortePostAttaccoNosferatu(String nomeVittima, String messaggio, String nomeMorto)
    {
        verificaFallimentoProgenizzazione(nomeVittima, messaggio);
        terminaNotte();
        verificaEliminati(nomeMorto);
    }

    private void verificaAttaccoVampiroRiuscito(String nome)
    {
        assertThatNoException().isThrownBy(() -> attaccoVampiro(nome));
    }

    private void verificaFallimentoVampirizzazione(String nomeVittima, String messaggio)
    {
        assertThatIllegalArgumentException().isThrownBy(() -> attaccoVampiro(nomeVittima)).withMessage(messaggio);
    }

    private void verificaFallimentoProgenizzazione(String nomeVittima, String messaggio)
    {
        assertThatIllegalArgumentException().isThrownBy(() -> progenizzazioneNosferatu(nomeVittima)).withMessage(messaggio);
    }

    private void verificaFallimentoGildata(String nomeVittima, String messaggio)
    {
        assertThatIllegalArgumentException().isThrownBy(() -> gildata(nomeVittima)).withMessage(messaggio);
    }

    private void attaccoVampiro(String nome) { partita.attaccoVampiro(nome); }

    private Misticismo controlloMago(String nome) { return partita.controlloMago(nome); }

    private void verificaControlloMedium(String nomeVittima, Aura risultato)
    {
        assertThat(partita.controlloMedium(nomeVittima)).isEqualTo(risultato);
    }

    private void verificaFazioneNosferatu(String nome) { verificaVero(isFazioneNosferatu(nome)); }

    private boolean isFazioneNosferatu(String nome) { return partita.isFazioneNosferatu(nome); }

    private void verificaAmatoNonVivo() { verificaFalso(isAmatoVivo()); }

    private boolean isAmatoVivo() { return partita.isAmatoVivo(); }

    private boolean isCriminaliPresenti() { return partita.isCriminaliPresenti(); }

    private boolean isLupoAttaccanteVivo() { return partita.isLupoAttaccanteVivo(); }

    private boolean isLupoReiettoVivo() { return partita.isLupoReiettoVivo(); }

    private boolean isNoGiocatoriVivi() { return partita.isNoGiocatoriVivi(); }

    private void verificaProgenieNosferatuNonViva(String nome) { verificaFalso(isProgenieNosferatuViva(nome)); }

    private boolean isProgenieNosferatuViva(String nome) { return partita.isProgenieNosferatuViva(nome); }

    private void verificaNosferatuNonVivo(String nome) { verificaFalso(isNosferatuVivo(nome)); }

    private boolean isNosferatuVivo(String nome) { return partita.isNosferatuVivo(nome); }

    private void verificaGhoulNonVivo(String nome) { verificaFalso(isGhoulVivo(nome)); }

    private boolean isGhoulVivo(String nomeGhoul) { return partita.isGhoulVivo(nomeGhoul); }

    private String[] estraiNomiGiocatori(String[][] giocatori)
    {
        return stream(giocatori).map(giocatore -> giocatore[0]).toList().toArray(new String[0]);
    }

    private void verificaVillaggio(String nome) { assertThat(partita.controlloSensitiva(nome)).isEqualTo(VILLAGGIO); }

    private void verificaNonMistico(String nome) { assertThat(controlloMago(nome)).isEqualTo(NON_MISTICO); }

    private void attaccoNegromante(String nome) { partita.attaccoNegromante(nome); }

    private void verificaNumeroNotte(int numeroNotte) { verificaNumeroIntero(partita.getNumeroNotte(), numeroNotte); }

    private void gildata(String nome) { partita.gildata(nome); }

    private boolean isCrociataAvviata() { return partita.isCrociataAvviata(); }

    private void progenizzazioneNosferatu(String nome) { partita.progenizzazioneNosferatu(nome); }

    private void terminaNotte() { partita.terminaNotte(); }

    private void verificaNumeroIntero(int valore, int risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaEliminati(String... nomi) { for(String nome : nomi) verificaEliminazione(nome); }

    private void segnalazioneBracconiere() { partita.segnalazioneBracconiere(); }

    private boolean isSegnalazioneBorgomastroAvvenuta() { return partita.segnalazioneBorgomastroAvvenuta(); }

    private void segnalazioneOratore(String nome) { partita.segnalazioneOratore(nome); }

    private void terminaBallottaggio() { partita.terminaBallottaggio(); }

    private void verificaNonEliminati(String... nomi) { for(String nome : nomi) verificaFalso(partita.isEliminato(nome)); }

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

    private void attaccoLupi(String nomeLupo, String nome) { partita.attaccoLupi(nomeLupo, nome); }

    private void segnalazioneInquisitore(String nome) { partita.segnalazioneInquisitore(nome); }

}