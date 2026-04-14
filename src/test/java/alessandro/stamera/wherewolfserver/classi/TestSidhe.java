package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
import static alessandro.stamera.wherewolfserver.classi.Tratto.PROTETTO;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestSidhe
{

    private static final String NOME = "Sidhe";

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = FACTORY.getRuolo(NOME); }

    @Test public void testNome() { assertThat(ruolo.getNome()).isEqualTo(NOME); }

    @Test public void testAura() { verificaAuraBianca(ruolo.getAura()); }

    @Test public void testControlloMedium() { verificaAuraBianca(ruolo.controlloMedium()); }

    @Test public void testDescrizione()
    {
        String descrizione = "La prima notte riconosce le altre creature del Piccolo Popolo (Goblin, Leprecauno). È protetta da tutti i Mistici.";
        assertThat(ruolo.getDescrizione()).isEqualTo(descrizione);
    }

    @ParameterizedTest @CsvSource({ "Guaritore, Mago, Megera" }) public void testProtezioneMistici(String nome)
    {
        assertThat(ruolo.isProtezionePresente(FACTORY.getRuolo(nome))).isTrue();
        assertThat(ruolo.isTrattoPresente(PROTETTO)).isTrue();
    }

    private void verificaAuraBianca(Aura aura) { assertThat(aura).isEqualTo(BIANCA); }

}