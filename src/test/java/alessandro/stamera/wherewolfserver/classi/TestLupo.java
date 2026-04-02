package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static alessandro.stamera.wherewolfserver.classi.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.Categoria.CREATURE_OMBRA;
import static alessandro.stamera.wherewolfserver.classi.EsitoAttacco.*;
import static alessandro.stamera.wherewolfserver.classi.Fazione.LUPO_BRANCO;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestLupo
{

    private Lupo ruolo;

    @BeforeEach public void setUp() { ruolo = new Lupo(null, null, 0); }

    @Test public void testAura() { verificaAuraNera(ruolo.getAura()); }

    @Test public void testFazione() { assertThat(ruolo.getFazione()).isEqualTo(LUPO_BRANCO); }

    @Test public void testCategoria() { assertThat(ruolo.getCategoria()).isEqualTo(CREATURE_OMBRA); }

    @Test public void testAngeloCustode() { verificaFalso(ruolo.isAngeloCustode()); }

    @Test public void testMistico() { verificaFalso(ruolo.isMistico()); }

    @Test public void testLupo() { verificaVero(ruolo.isLupo()); }

    @Test public void testCriminale() { verificaFalso(ruolo.isCriminale()); }

    @Test public void testAmato()
    {
        verificaFalso(isAmato());
        ruolo.sceltaAngeloCustode();
        verificaVero(isAmato());
    }

    @Test public void testAmanti() { verificaFalso(ruolo.isAmanti()); }

    @Test public void testCitta() { verificaFalso(ruolo.isCitta()); }

    @Test public void testGhoul() { verificaFalso(ruolo.isGhoul()); }

    @Test public void testGiullare() { verificaFalso(ruolo.isGiullare()); }

    @Test public void testGoblin() { verificaFalso(ruolo.isGoblin()); }

    @Test public void testInquisizione() { verificaFalso(ruolo.isInquisizione()); }

    @Test public void testMegera() { verificaFalso(ruolo.isMegera()); }

    @Test public void testVillaggio() { verificaFalso(ruolo.isVillaggio()); }

    @Test public void testControlloMedium() { verificaAuraNera(ruolo.controlloMedium()); }

    @Test public void testAttaccoNosferatu() { verificaAttacco(ruolo.attaccoNosferatu(), MORTO); }

    @ParameterizedTest
    @CsvSource({ "Capo branco, Lupo del branco, Giovane lupo, Lupo reietto, Contadino discendente dei lupi" })
    public void testAttaccoLupi(String nome) { verificaAttaccoLupi(nome, RIUSCITO); }

    @ParameterizedTest
    @CsvSource({ "Capo branco, Lupo del branco, Giovane lupo, Lupo reietto, Contadino discendente dei lupi" })
    public void testAttaccoAmato(String nome)
    {
        ruolo.sceltaAngeloCustode();
        verificaAttaccoLupi(nome, FALLITO);
    }

    @ParameterizedTest
    @CsvSource({ "Capo branco, Lupo del branco, Giovane lupo, Lupo reietto, Contadino discendente dei lupi" })
    public void testAttaccoRomeo(String nome)
    {
        ruolo.romeizzazione();
        verificaAttaccoLupi(nome, FALLITO);
    }

    private void verificaAttaccoLupi(String nome, EsitoAttacco esito) { verificaAttacco(ruolo.attaccoLupi(FACTORY.getRuolo(nome)), esito); }

    private void verificaAttacco(EsitoAttacco valore, EsitoAttacco risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaAuraNera(Aura aura) { assertThat(aura).isEqualTo(NERA); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

    private boolean isAmato() { return ruolo.isAmato(); }

}