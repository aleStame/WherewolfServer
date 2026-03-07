package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Categoria.CREATURE_OMBRA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.LUPO_BRANCO;
import static alessandro.stamera.wherewolfserver.classi.Fazione.LUPO_SOLITARIO;
import static alessandro.stamera.wherewolfserver.classi.Fazione.VAMPIRO;
import static alessandro.stamera.wherewolfserver.classi.Fazione.NOSFERATU;
import static alessandro.stamera.wherewolfserver.classi.Fazione.NEGROMANTE;
import static alessandro.stamera.wherewolfserver.classi.Fazione.POSSEDUTO;
import static org.assertj.core.api.Assertions.assertThat;
import static alessandro.stamera.wherewolfserver.classi.Fazione.values;

public final class TestProtezioni
{

    private Protezioni protezioni;

    @BeforeEach public void setUp()
    {
        protezioni = new Protezioni();
    }

    @Test public void testGiulietta()
    {
        protezioni.aggiungiProtezione(CREATURE_OMBRA);
        for(Fazione fazione : new Fazione[] { LUPO_BRANCO, LUPO_SOLITARIO, VAMPIRO, NOSFERATU, NEGROMANTE, POSSEDUTO })
            assertThat(isPresente(fazione)).isTrue();
    }

    @Test public void testCappuccettoRosso()
    {
        Fazione[] fazioni = new Fazione[] { LUPO_BRANCO, LUPO_SOLITARIO };
        for(Fazione fazione : fazioni) protezioni.aggiungiProtezione(fazione);
        for(Fazione fazione : fazioni) assertThat(isPresente(fazione)).isTrue();
    }

    @Test public void testPerdiProtezioni()
    {
        protezioni.perdiProtezioni();
        for(Fazione fazione : values()) assertThat(isPresente(fazione)).isFalse();
    }

    private boolean isPresente(Fazione fazione) { return protezioni.isPresente(fazione); }

}