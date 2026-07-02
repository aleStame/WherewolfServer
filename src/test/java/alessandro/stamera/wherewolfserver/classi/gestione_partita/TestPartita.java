package alessandro.stamera.wherewolfserver.classi.gestione_partita;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Misticismo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.NONNA_BECCATA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoControlloSensitiva.VILLAGGIO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Misticismo.MISTICO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Misticismo.NON_MISTICO;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
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
        ripristinaGiocatoreVivo(nome);
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
        ripristinaGiocatoreVivo(nomeAmato);
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
    public void testAttaccoAmatoRomeoAssassino(String nomeRuolo)
    {
        String nomeAngelo = "Enzo", nomeAssassino = "Barbara", nomeVittima = "Maddalena";
        inizializzaPartita(new String[][] { { nomeAngelo, "Angelo custode" }, { nomeAssassino, "Assassino" }, { nomeVittima, nomeRuolo } });
        romeizzazione(nomeVittima);
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
        ripristinaGiocatoreVivo(nomeVittima);
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
        segnalazioneAzzeccagarbugli(nome);
        for(int i = 1; i < giocatori.length; i++) incrementaVoti(giocatori[i][0], 1);
        terminaVotazioni();
        verificaAccusati(nome, giocatori[1][0], giocatori[2][0]);
        partita.ripristinaGiocatoreBallottaggio(nome);
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
        ripristinaGiocatoreVivo(giocatori[posizione1][0]);
    }

    @ParameterizedTest @CsvSource({ "Capo branco", "Lupo del branco", "Lupo reietto", "Lupo solitario" })
    public void testAttaccoLupiAngeloCustode(String nomeLupo)
    {
        String[][] giocatori = new String[][] { { "Walter", "Mago" }, { "Amelia", "Spia" } };
        inizializzaPartita(giocatori);
        int posizione = 0;
        attaccoLupi(nomeLupo, giocatori[posizione][0]);
        terminaNotte();
        verificaEliminazione(giocatori[posizione][0]);
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

    @ParameterizedTest @CsvSource({ "Capo branco", "Lupo del branco", "Lupo reietto", "Lupo solitario" })
    public void testAttaccoLupiAmato(String nomeLupo)
    {
        String[][] giocatori = new String[][] { { "Fabrizio", "Bocca di rosa" }, { "Franca", "Peccatore" } };
        inizializzaPartita(giocatori);
        String nomeAmato = giocatori[1][0];
        segnalazioneAngeloCustode(nomeAmato);
        attaccoLupi(nomeLupo, nomeAmato);
        terminaNotte();
        verificaNonEliminati(nomeAmato);
        ripristinaGiocatoreVivo(nomeAmato);
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
        ripristinaGiocatoreVivo(nome);
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
        ripristinaGiocatoreVivo(nomeSegnalato);
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
        partita.ripristinaGiocatori();
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
        verificaNonEliminati(lupo);
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

    @Test public void testAttaccoNosferatuContadinoMostro()
    {
        String nomeVittima = "Gianmaria", nomeNosferatu = "Augusta", tipoLupo = "Lupo del branco", nomeLupo = "Renato";
        inizializzaPartita(new String[][] { { "Augusta", "Nosferatu" }, { nomeVittima, "Contadino mostro" }, { nomeLupo, tipoLupo } });
        String messaggio =
            "L'attacco al Contadino mostro (Gianmaria) causa la morte anche del lupo attaccante (Renato).\nAvvisa entrambi i giocatori della " +
            "loro morte.";
        assertThatIllegalArgumentException().isThrownBy(() ->attaccoLupi(tipoLupo, nomeVittima)).withMessage(messaggio);
        progenizzazioneNosferatu(nomeVittima);
        terminaNotte();
        verificaEliminati(nomeNosferatu, nomeLupo);
        verificaNonEliminati(nomeVittima);
    }

    @Test public void testAttaccoNosferatuContadinoMostroRomeo()
    {
        String nomeVittima = "Gianmaria", nomeNosferatu = "Augusta", nomeLupo = "Renato";
        inizializzaPartita(new String[][] { { "Augusta", "Nosferatu" }, { nomeVittima, "Contadino mostro" }, { nomeLupo, "Assassino" } });
        romeizzazione(nomeVittima);
        attaccoAssassino(nomeVittima);
        progenizzazioneNosferatu(nomeVittima);
        terminaNotte();
        verificaEliminati(nomeVittima, nomeNosferatu);
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
        verificaNumeroIntero(partita.getNumeroLupiVivi(), 3);
    }

    @Test public void testAttaccoNosferatuRiuscito()
    {
        String nome = "Marco";
        inizializzaPartita(new String[][] { { nome, "Prete" }, { "Tina", "Nosferatu" } });
        attaccoLupi("Capo branco", nome);
        progenizzazioneNosferatu(nome);
        terminaNotte();
        verificaNonEliminati(nome);
        verificaFazioneNosferatu(nome);
        ripristinaGiocatoreVivo(nome);
    }

    @Test public void testSuicidioCapoBranco()
    {
        String[][] giocatori = new String[][] { { "Marco", "Capo branco" }, { "Luca", "Nosferatu" } };
        inizializzaPartita(giocatori);
        attaccoLupi(giocatori[0][1], giocatori[0][0]);
        progenizzazioneNosferatu(giocatori[0][0]);
        terminaNotte();
        verificaEliminati(giocatori[1][0]);
        verificaNonEliminati(giocatori[0][0]);
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
        ripristinaGiocatoreVivo(nomeVittima);
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
        ripristinaGiocatoreVivo(nomeVittima);
    }

    @ParameterizedTest
    @CsvSource( { "Capo branco", "Contadino mostro", "Giovane lupo", "Guardia", "Lupo del branco", "Lupo reietto", "Lupo solitario" })
    public void testCriminalizzazioneCapoGildaMorto(String nomeRuolo)
    {
        String nomeVittima = "Arturo", nomeCapoGilda = "Raffaele";
        inizializzaPartita(new String[][] { { nomeVittima, nomeRuolo }, { nomeCapoGilda, "Capo gilda" } });
        verificaFallimentoGildata(nomeVittima, "Impossibile criminalizzare Arturo.\nRaffaele muore.");
        terminaNotte();
        verificaEliminazione(nomeCapoGilda);
    }

    @ParameterizedTest @CsvSource({ "Capo branco", "Lupo del branco", "Lupo reietto", "Lupo solitario" })
    public void testCriminalizzazioneCapoGildaMortoContadinoLupizzato(String tipoLupo)
    {
        String nomeVittima = "Arturo", nomeCapoGilda = "Raffaele", nomeLupo = "Ale";
        String[][] giocatori =
            new String[][] { { nomeVittima, "Contadino discendente dei lupi" }, { nomeCapoGilda, "Capo gilda" }, { nomeLupo, tipoLupo } };
        inizializzaPartita(giocatori);
        attaccoLupi(nomeLupo, nomeVittima);
        verificaFallimentoGildata(nomeVittima, "Impossibile criminalizzare Arturo.\nRaffaele muore.");
        terminaNotte();
        verificaEliminazione(nomeCapoGilda);
    }

    @Test public void testCriminalizzazioneBecchino()
    {
        String nomeVittima = "Giulia";
        inizializzaPartita(new String[][] { { nomeVittima, "Becchino" }, { "Tania", "Capo gilda" } });
        partita.riconosciNegromante();
        verificaFallimentoGildata(nomeVittima, "Impossibile criminalizzare " + nomeVittima + ".");
    }

    @Test public void testCriminalizzazioneContadinoLupo()
    {
        String tipoLupo = "Lupo del branco", nomeVittima = "Alberto", nomeCapoGilda = "Andrea";
        inizializzaPartita
        (
            new String[][] { { "Sara", tipoLupo }, { nomeVittima, "Contadino discendente dei lupi" }, { nomeCapoGilda, "Capo gilda" } }
        );
        attaccoLupi(tipoLupo, nomeVittima);
        verificaFallimentoGildata(nomeVittima, "Impossibile criminalizzare " + nomeVittima + ".\n" + nomeCapoGilda + " muore.");
        terminaNotte();
        verificaEliminazione(nomeCapoGilda);
    }

    @Test public void testCriminalizzazioneContadinoMostro()
    {
        String nomeVittima = "Alberto", nomeCapoGilda = "Andrea";
        inizializzaPartita(new String[][] { { nomeVittima, "Contadino mostro" }, { nomeCapoGilda, "Capo gilda" } });
        verificaFallimentoGildata(nomeVittima, "Impossibile criminalizzare " + nomeVittima + ".\n" + nomeCapoGilda + " muore.");
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
        verificaNonEliminati(nomeCappuccettoRosso);
    }

    @ParameterizedTest @CsvSource({ "Capo branco", "Lupo del branco", "Lupo reietto", "Lupo solitario" })
    public void testGuarigioneContadinoMostro(String tipoLupo)
    {
        String nomeContadino = "Graziano", nomeGuaritore = "Perla", nomeLupo = "Leonardo";
        inizializzaPartita(new String[][] { { nomeContadino, "Contadino mostro" }, { nomeLupo, tipoLupo }, { nomeGuaritore, "Guaritore" } });
        String messaggio =
            "L'attacco al Contadino mostro (Graziano) causa la morte anche del lupo attaccante (Leonardo).\nAvvisa entrambi i giocatori della " +
            "loro morte.";
        assertThatIllegalArgumentException().isThrownBy(() -> attaccoLupi(tipoLupo, nomeContadino)).withMessage(messaggio);
        partita.guarisci(nomeContadino);
        terminaNotte();
        verificaEliminati(nomeGuaritore, nomeLupo);
        verificaNonEliminati(nomeContadino);
    }

    @ParameterizedTest @CsvSource({ "Capo branco", "Lupo del branco", "Lupo reietto", "Lupo solitario" })
    public void testGuarigioneContadinoLupo(String tipoLupo)
    {
        String nomeContadino = "Graziano", nomeLupo = "Leonardo";
        String[][] giocatori = new String[][]
        {
            { nomeContadino, "Contadino discendente dei lupi" }, { nomeLupo, tipoLupo }, { "Fabrizio", "Guaritore" }, { "Gea", "Assassino" }
        };
        inizializzaPartita(giocatori);
        attaccoLupi(tipoLupo, nomeContadino);
        attaccoAssassino(nomeContadino);
        partita.guarisci(nomeContadino);
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
        String[][] giocatori = new String[][] { { "Antonio", "Contadino mostro" }, { "Carlo", "Sensitiva" } };
        inizializzaPartita(giocatori);
        terminaNotte();
        int posizioneContadino = 0;
        verificaVillaggio(giocatori[posizioneContadino][0]);
        terminaNotte();
        verificaEliminazione(giocatori[1][0]);
        verificaNonEliminati(giocatori[posizioneContadino][0]);
    }

    @ParameterizedTest @CsvSource({ "Capo branco", "Lupo del branco", "Lupo reietto", "Lupo solitario" })
    public void testAttaccoLupiEremita(String tipoLupo)
    {
        String[][] giocatori = new String[][] { { "Margerita", "Eremita" }, { "Tony", tipoLupo } };
        inizializzaPartita(giocatori);
        attaccoLupi(tipoLupo, giocatori[0][0]);
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
        inizializzaPartita(new String[][] { { nomeVittima, "Prete" }, { "Lois", tipoLupo } });
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
        String nome = "Maria";
        inizializzaPartita(new String[][] { { "Carlo", "Angelo custode" }, { nome, "Prete" } });
        segnalazioneAngeloCustode(nome);
        for(int i = 0; i < 2; i++)
        {
            attaccoLupi("Capo branco", nome);
            terminaNotte();
        }
        verificaAmatoNonVivo();
    }

    @Test public void testAmatoPresente()
    {
        String nome = "Pina";
        inizializzaPartita(new String[][] { { "Francesco", "Angelo custode" }, { nome, "Peccatore" } });
        segnalazioneAngeloCustode(nome);
        verificaVero(isAmatoVivo());
        ripristinaGiocatoreVivo(nome);
    }

    @ParameterizedTest @CsvSource({ "Capo branco", "Lupo del branco", "Lupo reietto", "Lupo solitario" })
    public void testMorteGhoulCacciatoreNosferatu(String tipoLupo)
    {
        String[][] giocatori = new String[][]
        {
            { "Primo", tipoLupo }, { "Secondo", "Nosferatu" }, { "Terzo", "Cacciatore di vampiri" }, { "Quarto", "Ghoul" }
        };
        inizializzaPartita(giocatori);
        String nomeVittima = giocatori[2][0];
        attaccoLupi(tipoLupo, nomeVittima);
        progenizzazioneNosferatu(nomeVittima);
        terminaNotte();
        verificaEliminati(giocatori[3][0], nomeVittima);
    }

    @ParameterizedTest @CsvSource({ "Capo branco", "Lupo del branco", "Lupo reietto", "Lupo solitario" })
    public void testMorteGhoulContadinoMostroNosferatu(String tipoLupo)
    {
        String[][] giocatori = new String[][]
        {
            { "Primo", tipoLupo }, { "Secondo", "Nosferatu" }, { "Terzo", "Contadino mostro" }, { "Quarto", "Ghoul" }
        };
        inizializzaPartita(giocatori);
        String nomeVittima = giocatori[2][0], messaggio =
            "L'attacco al Contadino mostro (Terzo) causa la morte anche del lupo attaccante (Primo).\nAvvisa entrambi i giocatori della loro " +
            "morte.";
        assertThatIllegalArgumentException().isThrownBy(() ->attaccoLupi(tipoLupo, nomeVittima)).withMessage(messaggio);
        progenizzazioneNosferatu(nomeVittima);
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
            "Bocca di rosa, NERA", "Boia, NERA", "Borgomastro, BIANCA", "Bracconiere, BIANCA", "Cacciatore, BIANCA", "Cacciatore di vampiri, BIANCA",
            "Capo branco, NERA", "Capo gilda, BIANCA", "Cappuccetto rosso, BIANCA", "Contadino eroe, BIANCA",
            "Contadino discendente dei lupi, BIANCA", "Contadino mostro, BIANCA", "Contadino normale, BIANCA", "Eremita, BIANCA", "Ghoul, BIANCA",
            "Giovane lupo, NERA", "Giulietta, BIANCA", "Giullare, BIANCA", "Goblin, NERA", "Guardia, BIANCA", "Guardia corrotta, NERA",
            "Guaritore, BIANCA", "Inquisitore, BIANCA", "Ladra, BIANCA", "Leprecauno, BIANCA", "Lupo del branco, NERA", "Lupo reietto, NERA",
            "Lupo solitario, NERA", "Mago, BIANCA", "Medium, BIANCA", "Megera, NERA", "Mercante, BIANCA", "Monaco, BIANCA", "Negromante, NERA",
            "Nonna, BIANCA", "Nosferatu, NERA", "Oste, BIANCA", "Pazzo, BIANCA", "Peccatore, NERA", "Posseduto, NERA",
            "Prete, BIANCA", "Sidhe, BIANCA", "Spia, BIANCA", "Sensitiva, BIANCA", "Templare, BIANCA", "Vampiro, NERA"
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
            { "Primo", "Oste" }, { "Secondo", "Vampiro" }, { nomeCacciatore, "Cacciatore di vampiri" }, { "Quarto", "Ghoul" }
        };
        inizializzaPartita(giocatori);
        String messaggio =
            "Il tentativo di vampirizzazione del Cacciatore di vampiri (Terzo) causa la morte del Ghoul (Quarto).\nAvvisa Quarto della sua morte";
        verificaMortePostAttacco(nomeCacciatore, messaggio, nomeGhoul);
    }

    @Test public void testMorteGhoulContadinoMostroVampiro()
    {
        String nomeContadinoMostro = "Edd", nomeGhoul = "Eddy";
        String[][] giocatori = new String[][] { { "Ed", "Vampiro" }, { nomeContadinoMostro, "Contadino mostro" }, { nomeGhoul, "Ghoul" } };
        inizializzaPartita(giocatori);
        //verificaMortePostAttacco(nomeContadinoMostro, "Impossibile vampirizzare Edd.\nEddy muore.", nomeGhoul);
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Angelo custode", "Assassino", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro",
            "Bracconiere", "Cacciatore", "Cappuccetto rosso", "Contadino eroe", "Contadino discendente dei lupi", "Contadino normale", "Ghoul",
            "Giulietta", "Giullare", "Guardia", "Guardia corrotta", "Inquisitore", "Mercante", "Monaco", "Nonna", "Nosferatu", "Oratore", "Oste",
            "Pazzo", "Peccatore", "Prete", "Spia", "Templare", "Vampiro"
        }
    )
    public void testCriminalizzazioneProgenieVampiro(String nomeRuolo)
    {
        String nomeVittima = "Antonio";
        String[][] giocatori = new String[][] { { nomeVittima, nomeRuolo }, { "Davide", "Capo gilda" }, { "Gioele", "Vampiro" } };
        inizializzaPartita(giocatori);
        verificaAttaccoVampiroRiuscito(nomeVittima);
        verificaFallimentoGildata(nomeVittima, "Impossibile criminalizzare Antonio.");
        for(String nome : estraiNomiGiocatori(giocatori)) ripristinaGiocatoreVivo(nome);
    }

    @ParameterizedTest @CsvSource( { "Assassino", "Azzeccagarbugli", "Cappuccetto rosso", "Ghoul", "Giulietta", "Inquisitore" } )
    public void testVampirizzazioneRiuscita(String nomeRuolo)
    {
        String nomeVittima = "Rino";
        inizializzaPartita(new String[][] { { nomeVittima, nomeRuolo }, { "Pino", "Vampiro" } });
        verificaAttaccoVampiroRiuscito(nomeVittima);
        ripristinaGiocatoreVivo(nomeVittima);
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
        { "Cacciatore di vampiri", "Capo branco", "Contadino mostro", "Giovane lupo", "Lupo del branco", "Lupo reietto", "Lupo solitario" }
    )
    public void testMorteVampiro(String nomeRuolo)
    {
        String nomeVittima = "Luca", nomeVampiro = "Paolo";
        inizializzaPartita(new String[][] { { nomeVittima, nomeRuolo }, { nomeVampiro, "Vampiro" } });
        //verificaMortePostAttacco(nomeVittima, "Impossibile vampirizzare Luca.\nPaolo muore.", nomeVampiro);
    }

    @ParameterizedTest @CsvSource({ "Capo branco", "Lupo del branco", "Lupo reietto", "Lupo solitario" })
    public void testMorteVampiroContadinoLupizzato(String tipoLupo)
    {
        String nomeVittima = "Luca", nomeVampiro = "Paolo", nomeLupo = "Lino";
        String[][] giocatori =
            new String[][] { { nomeVittima, "Contadino discendente dei lupi" }, { nomeVampiro, "Vampiro" }, { nomeLupo, tipoLupo } };
        inizializzaPartita(giocatori);
        attaccoLupi(nomeLupo, nomeVittima);
        //verificaMortePostAttacco(nomeVittima, "Impossibile vampirizzare Luca.\nPaolo muore.", nomeVampiro);
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
        romeizzazione(nomeRomeo);
        verificaAttaccoVampiroRiuscito(nomeGiulietta);
        attaccoAssassino(nomeGiulietta);
        terminaNotte();
        verificaEliminazione(nomeGiulietta);
        verificaNonEliminati(nomeRomeo);
        ripristinaGiocatoreVivo(nomeRomeo);
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
        attaccoLupi("Carla", nomeGiulietta);
        terminaNotte();
        verificaEliminazione(nomeGiulietta);
        verificaNonEliminati(nomeRomeo);
        ripristinaGiocatoreVivo(nomeRomeo);
    }

    @Test public void testMaledizioneGuaritore()
    {
        String nomeGuaritore = "Jack", nomeMegera = "Megera";
        inizializzaPartita(new String[][] { { nomeGuaritore, "Guaritore" }, { nomeMegera, "Megera" }, { "Hal", "Assassino" } });
        attaccoAssassino(nomeMegera);
        partita.guarisci(nomeMegera);
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
            "Altra guardia", "Angelo custode", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro", "Bracconiere",
            "Cacciatore", "Cacciatore di vampiri", "Capo branco", "Capo gilda", "Cappuccetto rosso", "Contadino eroe",
            "Contadino discendente dei lupi", "Contadino mostro", "Contadino normale", "Ghoul", "Giovane lupo", "Giulietta", "Giullare", "Goblin",
            "Guardia", "Guardia corrotta", "Guaritore", "Inquisitore", "Leprecauno", "Lupo del branco", "Lupo reietto", "Lupo reietto",
            "Lupo solitario", "Mago", "Medium", "Megera", "Mercante", "Monaco", "Negromante", "Nonna", "Oratore", "Oste", "Pazzo", "Peccatore",
            "Sidhe", "Spia", "Sensitiva", "Templare", "Vampiro"
        }
    )
    public void testPoterePosseduto(String nomeRuolo)
    {
        String nomePosseduto = "Tommaso", nomeNuovoPosseduto = "Tania";
        inizializzaPartita(new String[][] { { "Elena", "Assassino" }, { nomePosseduto, "Posseduto" }, { nomeNuovoPosseduto, nomeRuolo } });
        attaccoAssassino(nomePosseduto);
        verificaCorrettezzaPossessione(nomeNuovoPosseduto);
    }

    @Test public void testPoterePossedutoPrete()
    {
        String nomePosseduto = "Alessandro", nomePrete = "Michelangelo";
        inizializzaPartita(new String[][] { { "Elena", "Assassino" }, { nomePosseduto, "Posseduto" }, { nomePrete, "Prete" } });
        attaccoAssassino(nomePosseduto);
        assertThatIllegalArgumentException().isThrownBy(() -> passaPosseduto(nomePrete)).withMessage("Impossibile possedere il Prete.");
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
        attaccoLupi(nomeLupo, nomePrete);
        progenizzazioneNosferatu(nomePrete);
        verificaCorrettezzaPossessione(nomePrete);
    }

    @Test public void testVampirizzazionePosseduto()
    {
        String nomeVampiro = "Ale", nomePosseduto = "Franz";
        inizializzaPartita(new String[][] { { nomeVampiro, "Vampiro" }, { nomePosseduto, "Posseduto" } });
        attaccoVampiro(nomePosseduto);
        verificaEliminazione(nomePosseduto);
        verificaVero(partita.isPosseduto(nomeVampiro));
    }

    @Test public void testPossessioneLadraNonRiuscita()
    {
        String nomeLadra = "Piera", nomePosseduto = "Assunta";
        inizializzaPartita(new String[][] { { nomeLadra, "Ladra" }, { "Giuseppe", "Assassino" }, { nomePosseduto, "Posseduto" } });
        attaccoAssassino(nomePosseduto);
        assertThatIllegalArgumentException().isThrownBy(() -> passaPosseduto(nomeLadra)).withMessage("Impossibile possedere Piera.");
    }

    @Test public void testAttaccoVampiroAmatoCacciatoreDiVampiri()
    {
        String nomeVampiro = "Stefano", nomeGhoul = "Biagio", nomeCacciatore = "Herbert", nomeAngelo = "Francesco";
        String[][] giocatori = new String[][]
        {
            { nomeVampiro, "Vampiro" }, { nomeGhoul, "Ghoul" }, { nomeCacciatore, "Cacciatore di vampiri" }, { nomeAngelo, "Angelo custode" }
        };
        inizializzaPartita(giocatori);
        segnalazioneAngeloCustode(nomeVampiro);
        String messaggio =
            "Il tentativo di vampirizzazione del Cacciatore di vampiri (Herbert) causa la morte dell'Angelo custode (Francesco) del Vampiro " +
            "amato (Stefano).\nAvvisa Francesco della sua morte.";
        assertThatIllegalStateException().isThrownBy(() -> attaccoVampiro(nomeCacciatore)).withMessage(messaggio);
        terminaNotte();
        verificaNonEliminati(nomeVampiro, nomeCacciatore, nomeGhoul);
        verificaEliminati(nomeAngelo);
        ripristinaGiocatoreVivo(nomeVampiro);
    }

    @ParameterizedTest @MethodSource("getEsempiAttacchiContadini")
    public void testAttaccoCapoBrancoContadino(String tipoContadino, String messaggio)
    {
        String nomeLupo = "Iris", tipoLupo = "Capo branco", nomeVittima = "Filippo";
        inizializzaPartita(new String[][]{ { nomeLupo, tipoLupo }, { nomeVittima, tipoContadino } });
        verificaEccezioneAttaccoContadino(messaggio, tipoLupo, nomeVittima, nomeLupo);
    }

    @ParameterizedTest @MethodSource("getEsempiAttacchiContadini")
    public void testAttaccoCapoBrancoContadinoAmato(String tipoContadino, String messaggio)
    {
        String nomeLupo = "Iris", tipoLupo = "Capo branco", nomeVittima = "Filippo";
        inizializzaPartita(new String[][]{ { nomeLupo, tipoLupo }, { nomeVittima, tipoContadino } });
        segnalazioneAngeloCustode(nomeLupo);
        verificaEccezioneAttaccoContadino(messaggio, tipoLupo, nomeVittima, nomeLupo);
    }

    @Test public void testAttaccoCapoBrancoNonna()
    {
        String nomeLupo = "Ciro", tipoLupo = "Capo branco", nomeVittima = "Federica";
        inizializzaPartita(new String[][] { { nomeLupo, tipoLupo }, { nomeVittima, "Nonna" } });
        String messaggio =
            "Il Capo branco (Ciro) ha beccato la Nonna (Federica).\nSveglia Federica e avvisa i due giocatori che Ciro è eliminato e che " +
            "Federica è il Capo branco.";
        assertThatIllegalArgumentException().isThrownBy(() -> attaccoLupi(tipoLupo, nomeVittima)).withMessage(messaggio);
        verificaVero(partita.isCapoBranco(nomeVittima));
        verificaEliminati(nomeLupo);
    }

    private void verificaEccezioneAttaccoContadino(String messaggio, String tipoLupo, String nomeVittima, String nomeLupo)
    {
        assertThatIllegalArgumentException().isThrownBy(() -> attaccoLupi(tipoLupo, nomeVittima)).withMessage(messaggio);
        terminaNotte();
        verificaEliminati(nomeLupo, nomeVittima);
    }

    private static Stream<Arguments> getEsempiAttacchiContadini()
    {
        return Stream.of
        (
            Arguments.of
            (
    "Contadino eroe",
               "L'attacco al Contadino eroe (Filippo) causa la morte anche del lupo attaccante (Iris).\nAvvisa entrambi i giocatori della loro " +
               "morte."
            ),
            Arguments.of
            (
    "Contadino mostro",
               "L'attacco al Contadino mostro (Filippo) causa la morte anche del lupo attaccante (Iris).\nAvvisa entrambi i giocatori della " +
               "loro morte."
            )
        );
    }

    private void verificaAttaccoLupiAngeloCustodeFallito(String tipoLupo, String nome, String messaggio)
    {
        assertThatIllegalStateException().isThrownBy(() -> attaccoLupi(tipoLupo, nome)).withMessage(messaggio);
        verificaNonEliminati(nome);
        ripristinaGiocatoreVivo(nome);
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

    private void verificaMortePostAttacco(String nomeVittima, String messaggio, String nomeMorto)
    {
        verificaFallimentoVampirizzazione(nomeVittima, messaggio);
        terminaNotte();
        verificaEliminati(nomeMorto);
    }

    private void verificaAttaccoVampiroRiuscito(String nome)
    {
        assertThatNoException().isThrownBy(() -> attaccoVampiro(nome));
    }

    private void verificaFallimentoVampirizzazione(String nomeVittima, String messaggio)
    {
        assertThatIllegalStateException().isThrownBy(() -> attaccoVampiro(nomeVittima)).withMessage(messaggio);
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

    private void ripristinaGiocatoreVivo(String nome) { partita.ripristinaGiocatoreVivo(nome); }

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

    private void progenizzazioneNosferatu(String giocatori) { partita.progenizzazioneNosferatu(giocatori); }

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

    private void segnalazioneAzzeccagarbugli(String nome) { partita.segnalazioneAzzeccagarbugli(nome); }

    private void attaccoLupi(String nomeLupo, String nome) { partita.attaccoLupi(nomeLupo, nome); }

    private void segnalazioneInquisitore(String nome) { partita.segnalazioneInquisitore(nome); }

}