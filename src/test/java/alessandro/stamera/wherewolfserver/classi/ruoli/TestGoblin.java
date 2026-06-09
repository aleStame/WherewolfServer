package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Categoria;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Tratto.PROTETTO;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestGoblin
{

    private static final String NOME = "Goblin";

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = FACTORY.getRuolo(NOME); }

    @Test public void testNome() { assertThat(ruolo.getNome()).isEqualTo(NOME); }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(1); }

    @Test public void testDescrizione()
    {
        String descrizione = "La prima notte riconosce le altre creature del piccolo popolo ed è protetto da tutti i mistici.";
        assertThat(ruolo.getDescrizione()).isEqualTo(descrizione);
    }

    @Test public void testFazione() { assertThat(ruolo.getFazione()).isEqualTo(Fazione.NESSUNA); }

    @Test public void testCategoria() { assertThat(ruolo.getCategoria()).isEqualTo(Categoria.NESSUNA); }

    @Test public void testAura() { verificaAuraNera(ruolo.getAura()); }

    @Test public void testControlloMedium() { verificaAuraNera(ruolo.controlloMedium()); }

    @ParameterizedTest @CsvSource({ "Guaritore", "Mago", "Megera" }) public void testProtezioneMistici(String nome)
    {
        verificaVero(ruolo.isProtezionePresente(FACTORY.getRuolo(nome)));
        verificaVero(ruolo.isTrattoPresente(PROTETTO));
    }

    @Test public void testAttaccoNegromante()
    {
        verificaFalso(ruolo.maledizione());
        verificaFalso(ruolo.isMaledetto());
        int numeroVoti = 2;
        ruolo.incrementaVoti(numeroVoti);
        assertThat(ruolo.getNumeroVoti()).isEqualTo(numeroVoti);
    }

    @Test public void testPiccoloPopolo() { verificaVero(ruolo.isPiccoloPopolo()); }

    @Test public void testGoblin() { verificaVero(ruolo.isGoblin()); }

    @Test public void testLeprecauno() { verificaFalso(ruolo.isLeprecauno()); }

    @Test public void testSidhe() { verificaFalso(ruolo.isSidhe()); }

    private void verificaAuraNera(Aura aura) { assertThat(aura).isEqualTo(NERA); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}