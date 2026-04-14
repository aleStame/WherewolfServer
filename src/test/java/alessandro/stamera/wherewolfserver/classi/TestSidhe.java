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

    @BeforeEach public void setUp() { ruolo = getRuolo(NOME); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), NOME); }

    @Test public void testAura() { verificaAuraBianca(ruolo.getAura()); }

    @Test public void testControlloMedium() { verificaAuraBianca(ruolo.controlloMedium()); }

    @Test public void testDescrizione()
    {
        String descrizione = "La prima notte riconosce le altre creature del Piccolo Popolo (Goblin, Leprecauno). È protetta da tutti i Mistici.";
        verificaStringa(ruolo.getDescrizione(), descrizione);
    }

    @ParameterizedTest @CsvSource({ "Guaritore, Mago, Megera" }) public void testProtezioneMistici(String nome)
    {
        assertThat(ruolo.isProtezionePresente(getRuolo(nome))).isTrue();
        assertThat(ruolo.isTrattoPresente(PROTETTO)).isTrue();
    }

    @Test public void testAttaccoNegromante()
    {
        assertThat(ruolo.maledizione()).isFalse();
        assertThat(ruolo.isMaledetto()).isFalse();
        int numeroVoti = 2;
        ruolo.incrementaVoti(numeroVoti);
        assertThat(ruolo.getNumeroVoti()).isEqualTo(numeroVoti);
    }

    private Ruolo getRuolo(String nome) { return FACTORY.getRuolo(nome); }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaAuraBianca(Aura aura) { assertThat(aura).isEqualTo(BIANCA); }

}