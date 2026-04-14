package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.Categoria.CREATURE_OMBRA;
import static alessandro.stamera.wherewolfserver.classi.EsitoAttacco.FALLITO;
import static alessandro.stamera.wherewolfserver.classi.EsitoAttacco.RIUSCITO;
import static alessandro.stamera.wherewolfserver.classi.Fazione.NOSFERATU;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
import static alessandro.stamera.wherewolfserver.classi.Tratto.NON_MORTO;
import static alessandro.stamera.wherewolfserver.classi.Tratto.PROTETTO;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestLadra
{

    private static final String NOME = "Ladra";

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = FACTORY.getRuolo(NOME); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), NOME); }

    @Test public void testDescrizione()
    {
        String descrizione =
            "La prima notte riconosce gli altri criminali. Una volta per partita, dalla seconda notte può aprire gli occhi nel turno di un " +
            "mistico. La prima volta che viene attaccata, è protetta dalle creature dell'ombra.";
        verificaStringa(ruolo.getDescrizione(), descrizione);
    }

    @Test public void testAura() { verificaAuraBianca(getAura()); }

    @Test public void testCriminale() { verificaVero(ruolo.isCriminale()); }

    @Test public void testAssassino() { verificaFalso(ruolo.isAssassino()); }

    @Test public void testCapoGilda() { verificaFalso(ruolo.isCapoGilda()); }

    @Test public void testLadra() { verificaVero(ruolo.isLadra()); }

    @Test public void testSpia() { verificaFalso(ruolo.isSpia()); }

    @ParameterizedTest
    @CsvSource({ "Capo branco, Lupo del branco, Lupo reietto, Lupo solitario, Contadino discendente dei lupi" })
    public void testUtilizzoPotereLupi(String nome)
    {
        verificaProtetto();
        verificaAttaccoLupi(nome, FALLITO);
        verificaPotereUtilizzato();
        verificaNonProtetto();
        verificaAttaccoLupi(nome, RIUSCITO);
    }

    @Test public void testAttaccoNosferatu()
    {
        verificaProtetto();
        verificaAttaccoNosferatu(FALLITO);
        verificaPotereUtilizzato();
        verificaNonProtetto();
        verificaAttaccoNosferatu(RIUSCITO);
        verificaVero(isTrattoPresente(NON_MORTO));
        assertThat(ruolo.getFazione()).isEqualTo(NOSFERATU);
        assertThat(ruolo.getCategoria()).isEqualTo(CREATURE_OMBRA);
    }

    @Test public void testAttaccoNegromante()
    {
        verificaProtetto();
        verificaFalso(maledizione());
        verificaPotereUtilizzato();
        verificaFalso(isMaledetto());
        verificaNonProtetto();
        verificaVero(maledizione());
        verificaVero(isMaledetto());
        verificaAura(getAura(), NERA);
    }

    @Test public void testControlloMedium() { verificaAuraBianca(ruolo.controlloMedium()); }

    private Aura getAura() { return ruolo.getAura(); }

    private boolean maledizione() { return ruolo.maledizione(); }

    private boolean isMaledetto() { return ruolo.isMaledetto(); }

    private void verificaProtetto() { verificaVero(isProtetto()); }

    private void verificaPotereUtilizzato() { verificaVero(ruolo.isPotereUtilizzato()); }

    private void verificaNonProtetto() { verificaFalso(isProtetto()); }

    private boolean isProtetto() { return isTrattoPresente(PROTETTO); }

    private boolean isTrattoPresente(Tratto tratto) { return ruolo.isTrattoPresente(tratto); }

    private void verificaAttaccoLupi(String nome, EsitoAttacco esito)
    {
        assertThat(ruolo.attaccoLupi(FACTORY.getRuolo(nome))).isEqualTo(esito);
    }

    private void verificaAttaccoNosferatu(EsitoAttacco esito) { assertThat(ruolo.attaccoNosferatu()).isEqualTo(esito); }

    private void verificaAuraBianca(Aura aura) { verificaAura(aura, BIANCA); }

    private void verificaAura(Aura valore, Aura risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}