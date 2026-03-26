package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestMegera
{

    private static final String NOME = "Megera";

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = FACTORY.getRuolo(NOME); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), NOME); }

    @Test public void testFazione() { assertThat(ruolo.getFazione()).isEqualTo(Fazione.NESSUNA); }

    @Test public void testCategoria() { assertThat(ruolo.getCategoria()).isEqualTo(Categoria.NESSUNA); }

    @Test public void testAura() { verificaAuraNera(ruolo.getAura()); }

    @Test public void testControlloMedium() { verificaAuraNera(ruolo.controlloMedium()); }

    @Test public void testDescrizione()
    {
        String descrizione =
            "La prima notte viene individuata da tutte le creature dell'ombra. Se viene indicata da un mistico, fino a che la Megera è in gioco, " +
            "quel giocatore diventa Maledetto, riceverà sempre responsi negativi e non potrà più proteggere. Vince con qualsiasi creatura " +
            "dell'ombra";
        verificaStringa(ruolo.getDescrizione(), descrizione);
    }

    @Test public void testMistico() { assertThat(ruolo.isMistico()).isTrue(); }

    @Test public void testCitta() { assertThat(ruolo.isCitta()).isFalse(); }

    @Test public void testCriminale() { assertThat(ruolo.isCriminale()).isFalse(); }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaAuraNera(Aura aura) { assertThat(aura).isEqualTo(NERA); }

}