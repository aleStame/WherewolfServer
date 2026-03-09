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

    @Test public void testCreatureOmbra()
    {
        protezioni.aggiungiProtezione(CREATURE_OMBRA);
        verificaProtezioni(new Fazione[] { LUPO_BRANCO, LUPO_SOLITARIO, VAMPIRO, NOSFERATU, NEGROMANTE, POSSEDUTO });
    }

    @Test public void testCappuccettoRosso()
    {
        protezioni.aggiungiLupi();
        Ruolo[] ruoli = new Ruolo[] { new CapoBranco(), new LupoBranco(), new GiovaneLupo(), new LupoReietto(), new LupoSolitario() };
        for(Ruolo ruolo : ruoli) assertThat(protezioni.isPresente(ruolo)).isTrue();
    }

    @Test public void testPerdiProtezioni()
    {
        protezioni.perdiProtezioni();
        for(Fazione fazione : values()) assertThat(isPresente(fazione)).isFalse();
    }

    private void verificaProtezioni(Fazione[] fazioni) { for(Fazione fazione : fazioni) assertThat(isPresente(fazione)).isTrue(); }

    private boolean isPresente(Fazione fazione) { return protezioni.isPresente(fazione); }

}