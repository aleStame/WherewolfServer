package alessandro.stamera.wherewolfserver.classi.gestione_partita;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.NERA;
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
        verificaEliminazione(giocatori[posizione][0]);
    }

    @Test public void testAttaccoAmatoAssassino()
    {
        String[][] giocatori = new String[][] { { "Enzo", "Angelo custode" }, { "Barbara", "Bardo" }, { "Maddalena", "Oste" } };
        int posizione = 1;
        inizializzaPartita(giocatori);
        segnalazioneAngeloCustode(giocatori[posizione][0]);
        attaccoAssassino(giocatori[posizione][0]);
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
        assertThat(isVivo(giocatori[posizione][0])).isTrue();
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
        verificaFalso(partita.segnalazioneBorgomastroAvvenuta());
        partita.segnalazioneBorgomastro(giocatori[posizione][0]);
        verificaVero(partita.segnalazioneBorgomastroAvvenuta());
        incrementaVoti(giocatori[posizione][0], 1);
        assertThat(FACTORY.getRuolo(giocatori[posizione][1]).getNumeroVoti()).isEqualTo(3);
    }

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