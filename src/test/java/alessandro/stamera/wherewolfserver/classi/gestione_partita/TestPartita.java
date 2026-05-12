package alessandro.stamera.wherewolfserver.classi.gestione_partita;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.RIUSCITO;
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
        confermaEliminazioneMortiNotte();
        verificaEliminazione(giocatori[posizione][0]);
    }

    @Test public void testAttaccoAmatoAssassino()
    {
        String[][] giocatori = new String[][] { { "Enzo", "Angelo custode" }, { "Barbara", "Bardo" }, { "Maddalena", "Oste" } };
        int posizione = 1;
        inizializzaPartita(giocatori);
        segnalazioneAngeloCustode(giocatori[posizione][0]);
        attaccoAssassino(giocatori[posizione][0]);
        confermaEliminazioneMortiNotte();
        verificaEliminazione(giocatori[0][0]);
        verificaVero(isVivo(giocatori[posizione][0]));
    }

    @Test public void testSegnalazioneAzzeccagarbugli()
    {
        String[][] giocatori = new String[][] { { "Matteo", "Guardia" }, { "Ivan", "Altra guardia" }, { "Miriam", "Guardia corrotta" } };
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
        confermaEliminazioneMortiNotte();
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
        confermaEliminazioneMortiNotte();
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

    @Test public void testAttaccoAssassinoContadinoMostro()
    {
        String[][] soluzioni = new String[][] { { "Pietro", "Assassino" }, { "Mario", "Contadino mostro" }, { "Maria", "Contadino eroe" } };
        inizializzaPartita(soluzioni);
        attaccoAssassino(soluzioni[1][0]);
        confermaEliminazioneMortiNotte();
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
        confermaEliminazioneMortiNotte();
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
        confermaEliminazioneMortiNotte();
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
        confermaEliminazioneMortiNotte();
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
        confermaEliminazioneMortiNotte();
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
        confermaEliminazioneMortiNotte();
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
        confermaEliminazioneMortiNotte();
        verificaEliminazione(giocatori[posizione][0]);
    }

    @Test public void testPotereBorgomastro()
    {
        String[][] giocatori =
            new String[][] { { "Jacopo", "Borgomastro" }, { "Isra", "Angelo custode" }, { "Tania", "Mercante" }, { "Francesco", "Bocca di rosa" } };
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
        assertThat(FACTORY.getRuolo(giocatori[posizione][1]).getNumeroVoti()).isEqualTo(3);
    }

    @Test public void testPotereBracconiereUnLupo()
    {
        String[][] giocatori = new String[][] { { "Elisa", "Bracconiere" }, { "Edoardo", "Lupo del branco" }, { "Franca", "Giullare" } };
        inizializzaPartita(giocatori);
        segnalazioneBracconiere();
        int posizioneVittima = 2;
        assertThatIllegalStateException().isThrownBy(() -> attaccoLupi(giocatori[1][1], giocatori[posizioneVittima][0]))
            .withMessage("Potere del Bracconiere in corso. Proibito l'attacco dei lupi.");
        confermaEliminazioneMortiNotte();
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
        confermaEliminazioneMortiNotte();
        verificaEliminazione(giocatori[posizioneVittima][0]);
    }

    @Test public void testAttaccoLupoSolitarioCacciatore()
    {
        String lupo = "Lupo solitario", nomeLupo = "Katia", nomeCacciatore = "Valeria";
        inizializzaPartita(new String[][] { { nomeLupo, lupo }, { nomeCacciatore, "Cacciatore" }, { "Pino", "Prete" } });
        attaccoLupi(lupo, nomeCacciatore);
        confermaEliminazioneMortiNotte();
        verificaEliminati(nomeLupo, nomeCacciatore);
    }

    @Test public void testAttaccoUltimoLupo()
    {
        String lupo = "Lupo reietto", nomeLupo = "Salvatore", nomeCacciatore = "Pietro";
        inizializzaPartita(new String[][] { { nomeLupo, lupo }, { nomeCacciatore, "Cacciatore" }, { "Cristina", "Leprecauno" } });
        attaccoLupi(lupo, nomeCacciatore);
        confermaEliminazioneMortiNotte();
        verificaEliminati(nomeLupo, nomeCacciatore);
    }

    @Test public void testAttaccoUltimoLupoBranco()
    {
        String lupo = "Lupo del branco", nomeLupo = "Pasquale", nomeCacciatore = "Gregorio";
        inizializzaPartita(new String[][] { { nomeLupo, lupo }, { nomeCacciatore, "Cacciatore" }, { "Cristina", "Leprecauno" } });
        attaccoLupi(lupo, nomeCacciatore);
        confermaEliminazioneMortiNotte();
        verificaEliminati(nomeLupo, nomeCacciatore);
    }

    @Test public void testAttaccoNormaleCacciatore()
    {
        String lupo = "Lupo del branco", nomeLupo = "Biagio", nomeCacciatore = "Francesco";
        inizializzaPartita(new String[][] { { nomeLupo, lupo }, { nomeCacciatore, "Cacciatore" }, { "Cristina", "Giovane lupo" } });
        attaccoLupi(lupo, nomeCacciatore);
        confermaEliminazioneMortiNotte();
        verificaEliminazione(nomeCacciatore);
        verificaNonEliminato(lupo);
    }

    @Test public void testAttaccoNosferatuMorto()
    {
        String nomeVittima = "Gianmaria", nomeNosferatu = "Augusta", tipoLupo = "Lupo del branco";
        inizializzaPartita(new String[][] { { "Augusta", "Nosferatu" }, { nomeVittima, "Cacciatore di vampiri" }, { "Raimondo", tipoLupo } });
        attaccoLupi(tipoLupo, nomeVittima);
        progenizzazioneNosferatu(nomeVittima);
        confermaEliminazioneMortiNotte();
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

    @Test public void testGildataCapoBranco()
    {
        String[][] giocatori = new String[][] { { "Pasquale", "Capo branco" }, { "Pina", "Capo gilda" } };
        inizializzaPartita(giocatori);
        partita.gildata(giocatori[0][0]);
        confermaEliminazioneMortiNotte();
        verificaEliminazione(giocatori[1][0]);
    }

    @ParameterizedTest @CsvSource({ "Contadino eroe", "Contadino mostro" })
    public void testAttaccoContadino(String tipoContadino)
    {
        String nomeVittima = "Caio", nomeLupo = "Tizio", tipoLupo = "Capo branco";
        inizializzaPartita(new String[][] { { nomeLupo, tipoLupo }, { nomeVittima, tipoContadino }, { "Sempronio", "Peccatore" } });
        attaccoLupi(tipoLupo, nomeVittima);
        confermaEliminazioneMortiNotte();
        verificaEliminati(nomeVittima, nomeLupo);
    }

    @Test public void testAttaccoNosferatuRiuscito()
    {
        String nome = "Marco";
        inizializzaPartita(new String[][] { { nome, "Prete" }, { "Tina", "Nosferatu" } });
        attaccoLupi("Capo branco", nome);
        progenizzazioneNosferatu(nome);
        confermaEliminazioneMortiNotte();
        verificaNonEliminato(nome);
    }

    @Test public void testSuicidioCapoBranco()
    {
        String[][] giocatori = new String[][] { { "Marco", "Capo branco" }, { "Luca", "Nosferatu" } };
        inizializzaPartita(giocatori);
        attaccoLupi(giocatori[0][1], giocatori[0][0]);
        progenizzazioneNosferatu(giocatori[0][0]);
        confermaEliminazioneMortiNotte();
        verificaEliminati(giocatori[1][0]);
        verificaNonEliminato(giocatori[0][0]);
    }

    @Test public void testBloccoAttaccoPazzo()
    {
        String tipoLupo = "Lupo reietto", nomePazzo = "Angel", nomeVittima = "Xander";
        inizializzaPartita(new String[][] { { "Spike", "Lupo reietto" }, { nomePazzo, "Pazzo" }, { "Xander", "Giullare" } });
        attaccoLupi(tipoLupo, nomePazzo);
        confermaEliminazioneMortiNotte();
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
        confermaEliminazioneMortiNotte();
        verificaNonEliminato(nomeVittima);
    }

    @Test public void testInizioCrociata()
    {
        String tipoLupo = "Capo branco", nomeVittima = "Chloe";
        inizializzaPartita(new String[][] { { "Yorgos", tipoLupo }, { "James", "Inquisitore" }, { nomeVittima, "Templare" } });
        attaccoLupi(tipoLupo, nomeVittima);
        confermaEliminazioneMortiNotte();
        verificaEliminati(nomeVittima);
        verificaVero(isCrociataAvviata());
    }

    @Test public void testMancatoInizioCrociata()
    {
        String tipoLupo = "Capo branco", nomeVittima = "Eve";
        inizializzaPartita(new String[][] { { "Daniel", tipoLupo }, { "Wesley", "Inquisitore" }, { nomeVittima, "Goblin" } });
        attaccoLupi(tipoLupo, nomeVittima);
        confermaEliminazioneMortiNotte();
        verificaEliminati(nomeVittima);
        verificaFalso(isCrociataAvviata());
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Borgomastro", "Bracconiere", "Cacciatore", "Cacciatore di vampiri",
            "Cappuccetto rosso", "Eremita", "Guaritore", "Mago", "Medium", "Mercante", "Monaco", "Nonna", "Oratore", "Oste", "Peccatore", "Prete",
            "Sensitiva"
        }
    )
    public void testCriminalizzazioneCapoGilda(String nomeRuolo)
    {
        String nomeVittima = "Antonio";
        inizializzaPartita(new String[][] { { nomeVittima, nomeRuolo }, { "Davide", "Capo gilda" } });
        partita.gildata(nomeVittima);
        assertThat(partita.getNumeroCriminali()).isEqualTo(2);
    }

    private boolean isCrociataAvviata() { return partita.isCrociataAvviata(); }

    private void progenizzazioneNosferatu(String giocatori) { partita.progenizzazioneNosferatu(giocatori); }

    private void confermaEliminazioneMortiNotte() { partita.confermaEliminazioneMortiNotte(); }

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