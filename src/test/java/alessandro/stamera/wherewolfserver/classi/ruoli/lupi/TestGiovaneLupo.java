package alessandro.stamera.wherewolfserver.classi.ruoli.lupi;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Tratto;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Categoria.CREATURE_OMBRA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.LUPO_BRANCO;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestGiovaneLupo
{

    private static final String NOME = "Giovane lupo";

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = FACTORY.getRuolo(NOME); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), NOME); }

    @Test public void testDescrizione()
    {
        String descrizione =
            "La prima notte individua il Traditore e riconosce i lupi del branco e nelle altre apre gli occhi durante il turno dei lupi " +
            "mannari. Se viene messo al rogo, la notte successiva i lupi del branco attaccheranno due volte";
        verificaStringa(ruolo.getDescrizione(), descrizione);
    }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(1); }

    @Test public void testCapoBranco() { verificaFalso(ruolo.isCapoBranco()); }

    @Test public void testLupoBranco() { verificaFalso(ruolo.isLupoBranco()); }

    @Test public void testGiovaneLupo() { verificaVero(ruolo.isGiovaneLupo()); }

    @Test public void testLupoReietto() { verificaFalso(ruolo.isLupoReietto()); }

    @Test public void testAura() { assertThat(ruolo.getAura()).isEqualTo(NERA); }

    @Test public void testFazione() { assertThat(ruolo.getFazione()).isEqualTo(LUPO_BRANCO); }

    @Test public void testCategoria() { assertThat(ruolo.getCategoria()).isEqualTo(CREATURE_OMBRA); }

    @ParameterizedTest @CsvSource({ "CREATURA_OMBRA, LUPO_MANNARO" })
    public void testTratti(Tratto tratto) { verificaVero(ruolo.isTrattoPresente(tratto)); }

    private void verificaStringa(String valore, String soluzione) { assertThat(valore).isEqualTo(soluzione); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}