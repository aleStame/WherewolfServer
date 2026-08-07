package alessandro.stamera.wherewolfserver.classi.ruoli.contadini;

import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.CONTADINO_LUPO_BECCATO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.RIUSCITO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.TipoContadino.LUPO;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestContadinoLupo
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = getRuolo("Contadino discendente dei lupi"); }

    @Test public void testNome() { assertThat(ruolo.getNome()).isEqualTo("Contadino"); }

    @Test public void testContadino() { verificaVero(ruolo.isContadino()); }

    @Test public void testContadinoNormale() { verificaFalso(ruolo.isContadinoNormale()); }

    @Test public void testContadinoMostro() { verificaFalso(ruolo.isContadinoMostro()); }

    @Test public void testContadinoEroe() { verificaFalso(ruolo.isContadinoEroe()); }

    @Test public void testContadinoLupo() { verificaVero(ruolo.isContadinoLupo()); }

    @ParameterizedTest @CsvSource({ "Capo branco", "Lupo del branco", "Lupo reietto", "Lupo solitario" })
    public void testAttaccoLupi(String tipoLupo) { assertThat(ruolo.attaccoLupi(getRuolo(tipoLupo))).isEqualTo(CONTADINO_LUPO_BECCATO); }

    @Test public void testRipristino()
    {
        ruolo.ripristina();
        verificaFalso(ruolo.isCreaturaOmbra());
        verificaFalso(ruolo.isLupo());
    }

    @Test public void testTipoContadino() { assertThat(ruolo.getTipoContadino()).isEqualTo(LUPO); }

    @Test public void testGildata() { assertThat(ruolo.gildata()).isEqualTo(RIUSCITO); }

    private Ruolo getRuolo(String nome) { return FACTORY.getRuolo(nome); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

}