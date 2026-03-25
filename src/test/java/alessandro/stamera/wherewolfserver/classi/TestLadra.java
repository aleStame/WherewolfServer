package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
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

    @Test public void testAura() { verificaAuraBianca(ruolo.getAura()); }

    @Test public void testCriminale() { verificaVero(ruolo.isCriminale()); }

    @Test public void testAssassino() { verificaFalso(ruolo.isAssassino()); }

    @Test public void testCapoGilda() { verificaFalso(ruolo.isCapoGilda()); }

    @Test public void testLadra() { verificaVero(ruolo.isLadra()); }

    @Test public void testUtilizzoPotere()
    {
        verificaFalso(isPotereUtilizzato());
        Ruolo[] lupi = FACTORY.getLupi();
        for(Ruolo lupo : lupi) verificaVero(isProtezionePresente(lupo));
        ruolo.utilizzaPotere();
        verificaVero(isPotereUtilizzato());
        verificaFalso(ruolo.isTrattoPresente(PROTETTO));
        for(Ruolo lupo : lupi) verificaFalso(isProtezionePresente(lupo));
    }

    @Test public void testControlloMedium() { verificaAuraBianca(ruolo.controlloMedium()); }

    private void verificaAuraBianca(Aura aura) { assertThat(aura).isEqualTo(BIANCA); }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

    private boolean isPotereUtilizzato() { return ruolo.isPotereUtilizzato(); }

    private boolean isProtezionePresente(Ruolo ruolo) { return this.ruolo.isProtezionePresente(ruolo); }

}