package alessandro.stamera.wherewolfserver.classi;

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

    @Test public void testGiulietta()
    {
        Protezioni protezioni = new Protezioni();
        protezioni.aggiungiProtezione(CREATURE_OMBRA);
        for(Fazione fazione : new Fazione[] { LUPO_BRANCO, LUPO_SOLITARIO, VAMPIRO, NOSFERATU, NEGROMANTE, POSSEDUTO })
            assertThat(protezioni.isPresente(fazione)).isTrue();
    }

    @Test public void testLadra()
    {
        Protezioni protezioni = new Protezioni();
        protezioni.aggiungiProtezione(CREATURE_OMBRA);
        protezioni.perdiProtezioni();
        for(Fazione fazione : values()) assertThat(protezioni.isPresente(fazione)).isFalse();
    }

}