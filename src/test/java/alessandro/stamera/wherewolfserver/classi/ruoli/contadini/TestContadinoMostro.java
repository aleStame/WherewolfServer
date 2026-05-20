package alessandro.stamera.wherewolfserver.classi.ruoli.contadini;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.FALLITO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.MORTO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.CRIMINALI;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.VILLAGGIO;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Tratto.NON_MORTO;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestContadinoMostro
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = getRuolo("Contadino mostro"); }

    @Test public void testNome() { assertThat(ruolo.getNome()).isEqualTo("Contadino"); }

    @Test public void testFazione() { assertThat(ruolo.getFazione()).isEqualTo(VILLAGGIO); }

    @Test public void testMaledetto()
    {
        verificaVero(ruolo.isMaledetto());
        assertThat(ruolo.getAura()).isEqualTo(NERA);
    }

    @Test public void testVoti()
    {
        ruolo.incrementaVoti(2);
        assertThat(ruolo.getNumeroVoti()).isEqualTo(3);
    }

    @Test public void testNonMorto() { verificaFalso(ruolo.isTrattoPresente(NON_MORTO)); }

    @Test public void testContadino() { verificaVero(ruolo.isContadino()); }

    @Test public void testContadinoNormale() { verificaFalso(ruolo.isContadinoNormale()); }

    @Test public void testContadinoMostro() { verificaVero(ruolo.isContadinoMostro()); }

    @Test public void testContadinoEroe() { verificaFalso(ruolo.isContadinoEroe()); }

    @Test public void testContadinoLupo() { verificaFalso(ruolo.isContadinoLupo()); }

    @Test public void testAttaccoNosferatu() { verificaAttaccoMorto(ruolo.attaccoNosferatu()); }

    @ParameterizedTest
    @CsvSource({ "Capo branco, Lupo del branco, Lupo reietto, Lupo solitario, Contadino discendente dei lupi" })
    public void testAttaccoLupi(String nome) { verificaAttaccoMorto(attaccoLupi(nome)); }

    @ParameterizedTest
    @CsvSource({ "Capo branco, Lupo del branco, Lupo reietto, Lupo solitario, Contadino discendente dei lupi" })
    public void testAttaccoLupiRomeo(String nome)
    {
        ruolo.romeizzazione();
        verificaAttacco(attaccoLupi(nome), FALLITO);
    }

    @Test public void testAttaccoAssassino() { verificaAttacco(ruolo.attaccoAssassino(), MORTO); }

    @Test public void testGildata()
    {
        verificaAttacco(ruolo.gildata(), MORTO);
        assertThat(ruolo.getFazione()).isEqualTo(CRIMINALI);
    }

    @Test public void testAttaccoNegromante() { verificaAttaccoMorto(ruolo.attaccoNegromante()); }

    private void verificaAttaccoMorto(EsitoAttacco esito) { verificaAttacco(esito, MORTO); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

    private EsitoAttacco attaccoLupi(String nome) { return ruolo.attaccoLupi(getRuolo(nome)); }

    private Ruolo getRuolo(String nome) { return FACTORY.getRuolo(nome); }

    private void verificaAttacco(EsitoAttacco valore, EsitoAttacco risultato) { assertThat(valore).isEqualTo(risultato); }

}