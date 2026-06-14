package alessandro.stamera.wherewolfserver.classi.fazioni;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione;
import alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Categoria.CREATURE_OMBRA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.*;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.*;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.LUPO_BRANCO;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestLupo
{

    private Lupo ruolo;

    @BeforeEach public void setUp() { ruolo = new Lupo(null, null, 0); }

    @Test public void testAura() { verificaAuraNera(ruolo.getAura()); }

    @Test public void testFazione() { assertThat(getFazione()).isEqualTo(LUPO_BRANCO); }

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

    @Test public void testNosferatu() { verificaFalso(ruolo.isNosferatu()); }

    @Test public void testPazzo() { verificaFalso(ruolo.isPazzo()); }

    @Test public void testPiccoloPopolo() { verificaFalso(ruolo.isPiccoloPopolo()); }

    @Test public void testPosseduto() { verificaFalso(ruolo.isPosseduto()); }

    @Test public void testVampiro() { verificaFalso(ruolo.isVampiro()); }

    @Test public void testVillaggio() { verificaFalso(ruolo.isVillaggio()); }

    @Test public void testControlloMedium() { verificaAuraNera(ruolo.controlloMedium()); }

    @Test public void testAttaccoNosferatu() { verificaAttacco(ruolo.attaccoNosferatu(), MORTO); }

    @ParameterizedTest @CsvSource({ "Capo branco", "Lupo del branco", "Lupo reietto", "Contadino discendente dei lupi" })
    public void testAttaccoAmato(String nome)
    {
        ruolo.sceltaAngeloCustode();
        verificaAttaccoLupiFallito(nome);
    }

    @ParameterizedTest @CsvSource({ "Capo branco", "Lupo del branco", "Lupo reietto", "Contadino discendente dei lupi" })
    public void testAttaccoRomeo(String nome)
    {
        ruolo.romeizzazione();
        verificaAttaccoLupiFallito(nome);
    }

    @Test public void testSegnalazioneBoia()
    {
        ruolo.segnalazioneBoia();
        verificaVero(isSegnalatoBoia());
        ruolo.annullaSegnalazioneBoia();
        verificaFalso(isSegnalatoBoia());
    }

    @Test public void testGildata()
    {
        Fazione fazione = getFazione();
        verificaAttacco(ruolo.gildata(), MORTO);
        assertThat(getFazione()).isEqualTo(fazione);
    }

    @Test public void testEsitoPartita()
    {
        Partita partita = new Partita(new String[][] { { "Noemi", "Capo branco" }, { "Elisa", "Lupo del branco" }, { "Damiano", "Pazzo" } });
        assertThat(ruolo.getEsitoPartita(partita)).isEqualTo(VITTORIA);
    }

    private Fazione getFazione() { return ruolo.getFazione(); }

    private boolean isSegnalatoBoia() { return ruolo.isSegnalatoBoia(); }

    private void verificaAttaccoLupiFallito(String nome) { verificaAttacco(ruolo.attaccoLupi(FACTORY.getRuolo(nome)), FALLITO); }

    private void verificaAttacco(EsitoAttacco valore, EsitoAttacco risultato)
    {
        assertThat(valore).isEqualTo(risultato);
    }

    private void verificaAuraNera(Aura aura) { assertThat(aura).isEqualTo(NERA); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

    private boolean isAmato() { return ruolo.isAmato(); }

}