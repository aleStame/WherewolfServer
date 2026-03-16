package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import static alessandro.stamera.wherewolfserver.classi.IstanzaRuolo.CAPO_BRANCO;
import static alessandro.stamera.wherewolfserver.classi.IstanzaRuolo.LUPO_BRANCO;
import static alessandro.stamera.wherewolfserver.classi.IstanzaRuolo.GIOVANE_LUPO;
import static alessandro.stamera.wherewolfserver.classi.IstanzaRuolo.LUPO_REIETTO;
import static alessandro.stamera.wherewolfserver.classi.IstanzaRuolo.LUPO_SOLITARIO;
import static alessandro.stamera.wherewolfserver.classi.Tratto.PROTETTO;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestTratti
{

    private Tratti tratti;

    @BeforeEach public void setUp() { tratti = new Tratti(); }

    @ParameterizedTest @EnumSource(Tratto.class) public void testTrattoPresente(Tratto tratto)
    {
        tratti.aggiungi(tratto);
        assertThat(isPresente(tratto)).isTrue();
    }

    @ParameterizedTest @EnumSource(Tratto.class)
    public void testTrattoAssente(Tratto tratto) { assertThat(isPresente(tratto)).isFalse(); }

    @Test public void testCappuccettoRosso()
    {
        tratti.aggiungiLupi();
        IstanzaRuolo[] istanzaRuolo = new IstanzaRuolo[] { CAPO_BRANCO, LUPO_BRANCO, GIOVANE_LUPO, LUPO_REIETTO, LUPO_SOLITARIO };
        assertThat(isPresente(PROTETTO)).isTrue();
        for(IstanzaRuolo ruolo : istanzaRuolo) assertThat(tratti.isProtezionePresente(ruolo.getRuolo())).isTrue();
    }

    private boolean isPresente(Tratto tratto) { return tratti.isPresente(tratto); }

}