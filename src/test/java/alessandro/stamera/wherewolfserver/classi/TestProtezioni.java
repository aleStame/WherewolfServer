package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static alessandro.stamera.wherewolfserver.classi.Categoria.CREATURE_OMBRA;
import static alessandro.stamera.wherewolfserver.classi.IstanzaRuolo.CAPO_BRANCO;
import static alessandro.stamera.wherewolfserver.classi.IstanzaRuolo.LUPO_BRANCO;
import static alessandro.stamera.wherewolfserver.classi.IstanzaRuolo.GIOVANE_LUPO;
import static alessandro.stamera.wherewolfserver.classi.IstanzaRuolo.LUPO_REIETTO;
import static alessandro.stamera.wherewolfserver.classi.IstanzaRuolo.LUPO_SOLITARIO;
import static org.assertj.core.api.Assertions.assertThat;
import static alessandro.stamera.wherewolfserver.classi.IstanzaRuolo.values;

public final class TestProtezioni
{

    private Protezioni protezioni;

    @BeforeEach public void setUp()
    {
        protezioni = new Protezioni();
    }

    @Test public void testCreatureOmbra()
    {
        protezioni.aggiungiProtezioneCreatureOmbra();
        verificaProtezioni(new IstanzaRuolo[] { CAPO_BRANCO, LUPO_BRANCO, LUPO_SOLITARIO, LUPO_REIETTO, GIOVANE_LUPO });
    }

    @Test public void testCappuccettoRosso()
    {
        protezioni.aggiungiProtezione(Fazione.LUPO_BRANCO, Fazione.LUPO_SOLITARIO);
        verificaProtezioni(new IstanzaRuolo[] { CAPO_BRANCO, LUPO_BRANCO, GIOVANE_LUPO, LUPO_REIETTO, LUPO_SOLITARIO });
    }

    @Test public void testPerdiProtezioni()
    {
        protezioni.perdiProtezioni();
        for(IstanzaRuolo istanza : values()) assertThat(isPresente(istanza.getRuolo())).isFalse();
    }

    private void verificaProtezioni(IstanzaRuolo[] istanzaRuolo) { for(IstanzaRuolo x : istanzaRuolo) assertThat(isPresente(x.getRuolo())).isTrue(); }

    private boolean isPresente(Ruolo ruolo) { return protezioni.isPresente(ruolo); }

}