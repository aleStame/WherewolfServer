package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.GIULIETTA_BECCATA;
import static org.assertj.core.api.Assertions.assertThat;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;

public final class TestGiulietta
{

    private static final String NOME = "Giulietta";

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = FACTORY.getRuolo(NOME); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), NOME); }

    @Test public void testDescrizione()
    {
        String descrizione =
            "La prima notte indica un giocatore, Romeo, che la riconosce. Quel giocatore diventa protetto dalle creature dell'ombra finché " +
            "Giulietta è in gioco, e la sua fazione diventa Amanti. Se uno dei due viene ucciso di notte o messo al rogo, l'altro si uccide " +
            "durante la notte.";
        verificaStringa(ruolo.getDescrizione(), descrizione);
    }

    @Test public void testGiulietta() { assertThat(ruolo.isGiulietta()).isTrue(); }

    @Test public void testAngeloCustode() { assertThat(ruolo.isAngeloCustode()).isFalse(); }

    @ParameterizedTest @CsvSource({ "Capo branco", "Lupo del branco", "Lupo reietto", "Lupo solitario" })
    public void testAttaccoLupi(String nomeRuolo) { assertThat(ruolo.attaccoLupi(FACTORY.getRuolo(nomeRuolo))).isEqualTo(GIULIETTA_BECCATA); }

    @Test public void testAttaccoVampiro() { assertThat(ruolo.vampirizzazione()).isEqualTo(GIULIETTA_BECCATA); }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

}