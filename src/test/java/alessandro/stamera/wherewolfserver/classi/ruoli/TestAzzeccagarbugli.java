package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita;
import alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.*;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestAzzeccagarbugli
{

    private static final String NOME = "Azzeccagarbugli";

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = FACTORY.getRuolo(NOME); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), NOME); }

    @Test public void testDescrizione()
    {
        String descrizione =
            "Può votare al ballottaggio anche se è accusato e può segnalare un altro giocatore durante le accuse: se la sua fazione è Città " +
            "o Criminali, i voti che riceve vengono azzerati, altrimenti sarà accusato a prescindere dai voti ricevuti.";
        verificaStringa(ruolo.getDescrizione(), descrizione);
    }

    @Test public void testAura() { verificaAuraBianca(ruolo.getAura()); }

    @Test public void testBoccaDiRosa() { verificaFalso(ruolo.isBoccaDiRosa()); }

    @Test public void testAzzeccagarbugli() { verificaVero(ruolo.isAzzeccagarbugli()); }

    @Test public void testBorgomastro() { verificaFalso(ruolo.isBorgomastro()); }

    @Test public void testMercante() { verificaFalso(ruolo.isMercante()); }

    @Test public void testOratore() { verificaFalso(ruolo.isOratore()); }

    @Test public void testCitta() { verificaVero(ruolo.isCitta()); }

    @Test public void testControlloMedium() { verificaAuraBianca(ruolo.controlloMedium()); }

    @ParameterizedTest @MethodSource({ "getEsempiEsitiPartita" })
    public void testEsitoPartita(Partita partita, EsitoPartita esito) { assertThat(ruolo.getEsitoPartita(partita)).isEqualTo(esito); }

    private void verificaAuraBianca(Aura aura) { assertThat(aura).isEqualTo(BIANCA); }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

    private static Stream<Arguments> getEsempiEsitiPartita()
    {
        Partita[] partita = new Partita[]
        {
            new Partita(new String[][] { }), new Partita(new String[][] { { "Michael", "Capo branco" }, { "Freddie", "Nosferatu" } }),
            new Partita(new String[][] { { "Paolo", "Contadino eroe" }, { "Michele", "Mercante" } }),
            new Partita(new String[][] { { "Marina", "Nosferatu" }, { "Giacomo", "Contadino normale" } })
        };
        return Stream.of
        (
            Arguments.of(partita[0], SCONFITTA), Arguments.of(partita[1], SCONFITTA), Arguments.of(partita[2], VITTORIA),
            Arguments.of(partita[3], NON_FINITO)
        );
    }

}