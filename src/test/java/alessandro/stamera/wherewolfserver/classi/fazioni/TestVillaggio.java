package alessandro.stamera.wherewolfserver.classi.fazioni;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoControlloSensitiva;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita;
import alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Categoria.UOMINI;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.*;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.VILLAGGIO;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestVillaggio
{

    private Ruolo ruolo;

    @BeforeEach public void setUp()
    {
        ruolo = new Villaggio(null, null, null, -1, true, null);
    }

    @Test public void testFazione() { assertThat(ruolo.getFazione()).isEqualTo(VILLAGGIO); }

    @Test public void testCategoria() { assertThat(ruolo.getCategoria()).isEqualTo(UOMINI); }

    @Test public void testAmanti() { verificaFalso(ruolo.isAmanti()); }

    @Test public void testCitta() { verificaFalso(ruolo.isCitta()); }

    @Test public void testCriminale() { verificaFalso(ruolo.isCriminale()); }

    @Test public void testLupo() { verificaFalso(ruolo.isLupo()); }

    @Test public void testNosferatu() { verificaFalso(ruolo.isNosferatu()); }

    @Test public void testPazzo() { verificaFalso(ruolo.isPazzo()); }

    @Test public void testPiccoloPopolo() { verificaFalso(ruolo.isPiccoloPopolo()); }

    @Test public void testPosseduto() { verificaFalso(ruolo.isPosseduto()); }

    @Test public void testVampiro() { verificaFalso(ruolo.isVampiro()); }

    @Test public void testVillaggio() { assertThat(ruolo.isVillaggio()).isTrue(); }

    @ParameterizedTest @MethodSource("getEsempiEsitiPartita")
    public void testEsitoPartita(Partita partita, EsitoPartita esito) { assertThat(ruolo.getEsitoPartita(partita)).isEqualTo(esito); }

    @Test public void testControlloSensitiva()
    {
        assertThat(ruolo.controlloSensitiva()).isEqualTo(EsitoControlloSensitiva.VILLAGGIO);
    }

    private static Stream<Arguments> getEsempiEsitiPartita()
    {
        Partita[] partite = new Partita[]
        {
            new Partita(new String[][] { { "Noemi", "Bardo" }, { "Elisa", "Pazzo" } }),
            new Partita(new String[][] { { "Alessandro", "Altra guardia" }, { "Marco", "Guardia" }, { "Maurizio", "Assassino" } }),
            new Partita(new String[][] { { "Tommaso", "Altra guardia" }, { "Antonio", "Guardia" }, { "Aurora", "Capo gilda" } }),
            new Partita(new String[][] { { "Raffaele", "Altra guardia" }, { "Andrea", "Guardia" }, { "Elene", "Guardia corrotta" } }),
            new Partita(new String[][] { { "Francesco", "Altra guardia" }, { "Matteo", "Guardia" }, { "Monica", "Spia" } }),
            new Partita(new String[][] { { "Michele", "Altra guardia" }, { "Giulia", "Guardia" }, { "Sara", "Ladra" } }),
            new Partita(new String[][] { { "Alessandro", "Altra guardia" }, { "Maurizio", "Assassino" } }),
            new Partita(new String[][] { { "Tommaso", "Altra guardia" }, { "Aurora", "Capo gilda" } }),
            new Partita(new String[][] { { "Raffaele", "Altra guardia" }, { "Elena", "Guardia corrotta" } }),
            new Partita(new String[][] { { "Salvatore", "Altra guardia" }, { "Natalia", "Spia" } }),
            new Partita(new String[][] { { "Franco", "Altra guardia" }, { "Martina", "Angelo custode" } }),
            new Partita(new String[][] { { "Giuseppe", "Altra guardia" }, { "Luciana", "Azzeccagarbugli" } }),
            new Partita(new String[][] { { "Boris", "Altra guardia" }, { "Margherita", "Bardo" } }),
            new Partita(new String[][] { { "Ivano", "Altra guardia" }, { "Scarlett", "Becchino" } }),
            new Partita(new String[][] { { "Gigi", "Altra guardia" }, { "Anna", "Bocca di rosa" } }),
            new Partita(new String[][] { { "Santo", "Altra guardia" }, { "Francesca", "Borgomastro" } }),
            new Partita(new String[][] { { "Sandro", "Altra guardia" }, { "Domenica", "Boia" } }),
            new Partita(new String[][] { { "Enrico", "Altra guardia" }, { "Wanda", "Bracconiere" } }),
            new Partita(new String[][] { { "Robert", "Altra guardia" }, { "Florence", "Cacciatore" } }),
            new Partita(new String[][] { { "Giosuè", "Altra guardia" }, { "Patrizia", "Cacciatore di vampiri" } }),
            new Partita(new String[][] { { "Pippo", "Altra guardia" }, { "Marta", "Capo gilda" } }),
            new Partita(new String[][] { { "Mike", "Altra guardia" }, { "Jessi", "Cappuccetto rosso" } }),
            new Partita(new String[][] { { "Vlad", "Altra guardia" }, { "Lila", "Contadino discendente dei lupi" } }),
            new Partita(new String[][] { { "Paolo", "Altra guardia" }, { "Nadia", "Contadino eroe" } }),
            new Partita(new String[][] { { "Gianluca", "Altra guardia" }, { "Lina", "Contadino normale" } }),
            new Partita(new String[][] { { "Gianluigi", "Altra guardia" }, { "Dina", "Eremita" } }),
            new Partita(new String[][] { { "Biagio", "Altra guardia" }, { "Pina", "Giulietta" } }),
            new Partita(new String[][] { { "Peppe", "Altra guardia" }, { "Brenda", "Giullare" } }),
            new Partita(new String[][] { { "Alessio", "Altra guardia" }, { "Elisabetta", "Guaritore" } }),
            new Partita(new String[][] { { "Andrea", "Altra guardia" }, { "Michelle", "Inquisitore" } }),
            new Partita(new String[][] { { "Andy", "Altra guardia" }, { "Federica", "Mago" } }),
            new Partita(new String[][] { { "Giacomo", "Altra guardia" }, { "Tania", "Medium" } }),
            new Partita(new String[][] { { "Giorgio", "Altra guardia" }, { "Giorgia", "Mercante" } }),
            new Partita(new String[][] { { "Rosario", "Altra guardia" }, { "Lana", "Monaco" } }),
            new Partita(new String[][] { { "Amedeo", "Altra guardia" }, { "Ginevra", "Nonna" } }),
            new Partita(new String[][] { { "Vasco", "Altra guardia" }, { "Genea", "Oratore" } }),
            new Partita(new String[][] { { "Fabio", "Altra guardia" }, { "Meg", "Oste" } }),
            new Partita(new String[][] { { "Luciano", "Altra guardia" }, { "Lily", "Pazzo" } }),
            new Partita(new String[][] { { "Fred", "Altra guardia" }, { "Franca", "Peccatore" } }),
            new Partita(new String[][] { { "Piero", "Altra guardia" }, { "Cléo", "Prete" } }),
            new Partita(new String[][] { { "Virginia", "Altra guardia" }, { "Serena", "Spia" } }),
            new Partita(new String[][] { { "Alvise", "Altra guardia" }, { "Barbara", "Strega" } }),
            new Partita(new String[][] { { "William", "Altra guardia" }, { "Mara", "Sensitiva" } }),
            new Partita(new String[][] { { "Manuel", "Altra guardia" }, { "Camilla", "Templare" } }),
            new Partita(new String[][] { { "Damiano", "Altra guardia" }, { "Tara", "Veggente" } }),
            new Partita(new String[][] { { "Michele", "Altra guardia" }, { "Maria", "Guardia" }, { "Sara", "Ladra" } }),
            new Partita(new String[][] { { "Federico", "Capo gilda" }, { "Otello", "Giullare" } }),
            new Partita(new String[][] { { "Chris", "Altra guardia" }, { "Iaia", "Capo branco" } }),
            new Partita(new String[][] { { "Bradley", "Altra guardia" }, { "Yoko", "Contadino mostro" } }),
            new Partita(new String[][] { { "Patrick", "Altra guardia" }, { "Elizabeth", "Giovane lupo" } }),
            new Partita(new String[][] { { "Ian", "Altra guardia" }, { "Kathryn", "Lupo del branco" } }),
            new Partita(new String[][] { { "Hugh", "Altra guardia" }, { "Irina", "Lupo reietto" } }),
            new Partita(new String[][] { { "Ryan", "Altra guardia" }, { "Tatiana", "Lupo solitario" } }),
            new Partita(new String[][] { { "John", "Altra guardia" }, { "Karen", "Negromante" } }),
            new Partita(new String[][] { { "Joseph", "Altra guardia" }, { "Morgane", "Nosferatu" } }),
            new Partita(new String[][] { { "Anthony", "Altra guardia" }, { "Dalia", "Posseduto" } }),
            new Partita(new String[][] { { "James", "Altra guardia" }, { "Anna Maria", "Vampiro" } })
        };
        return
            Stream.of
            (
                Arguments.of(partite[0], VITTORIA), Arguments.of(partite[1], VITTORIA), Arguments.of(partite[2], VITTORIA),
                Arguments.of(partite[3], VITTORIA), Arguments.of(partite[4], VITTORIA), Arguments.of(partite[5], VITTORIA),
                Arguments.of(partite[4], VITTORIA), Arguments.of(partite[5], VITTORIA), Arguments.of(partite[6], VITTORIA),
                Arguments.of(partite[7], VITTORIA), Arguments.of(partite[8], VITTORIA), Arguments.of(partite[9], VITTORIA),
                Arguments.of(partite[10], VITTORIA), Arguments.of(partite[11], VITTORIA), Arguments.of(partite[12], VITTORIA),
                Arguments.of(partite[13], VITTORIA), Arguments.of(partite[14], VITTORIA), Arguments.of(partite[15], VITTORIA),
                Arguments.of(partite[16], VITTORIA), Arguments.of(partite[17], VITTORIA), Arguments.of(partite[18], VITTORIA),
                Arguments.of(partite[19], VITTORIA), Arguments.of(partite[20], VITTORIA), Arguments.of(partite[21], VITTORIA),
                Arguments.of(partite[22], VITTORIA), Arguments.of(partite[23], VITTORIA), Arguments.of(partite[24], VITTORIA),
                Arguments.of(partite[25], VITTORIA), Arguments.of(partite[26], VITTORIA), Arguments.of(partite[27], VITTORIA),
                Arguments.of(partite[28], VITTORIA), Arguments.of(partite[29], VITTORIA), Arguments.of(partite[30], VITTORIA),
                Arguments.of(partite[31], VITTORIA), Arguments.of(partite[32], VITTORIA), Arguments.of(partite[33], VITTORIA),
                Arguments.of(partite[34], VITTORIA), Arguments.of(partite[35], VITTORIA), Arguments.of(partite[36], VITTORIA),
                Arguments.of(partite[37], VITTORIA), Arguments.of(partite[38], VITTORIA), Arguments.of(partite[39], VITTORIA),
                Arguments.of(partite[40], VITTORIA), Arguments.of(partite[41], VITTORIA), Arguments.of(partite[42], VITTORIA),
                Arguments.of(partite[43], VITTORIA), Arguments.of(partite[44], VITTORIA), Arguments.of(partite[45], VITTORIA),
                Arguments.of(partite[46], SCONFITTA), Arguments.of(partite[47], SCONFITTA), Arguments.of(partite[48], SCONFITTA),
                Arguments.of(partite[49], SCONFITTA), Arguments.of(partite[50], SCONFITTA), Arguments.of(partite[51], SCONFITTA),
                Arguments.of(partite[52], SCONFITTA), Arguments.of(partite[53], SCONFITTA), Arguments.of(partite[54], SCONFITTA),
                Arguments.of(partite[55], SCONFITTA), Arguments.of(partite[56], SCONFITTA)
            );
    }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}