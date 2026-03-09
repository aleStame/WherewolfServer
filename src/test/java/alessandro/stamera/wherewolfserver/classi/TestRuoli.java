package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestRuoli
{

    @Test public void testRuoli()
    {
        Ruolo[] soluzione = new Ruolo[]
        {
            new AltraGuardia(), new AngeloCustode(), new Assassino(), new Azzeccagarbugli(), new Bardo(), new Becchino(), new BoccaDiRosa(),
            new Boia(), new Borgomastro(), new Bracconiere(), new Cacciatore(), new CacciatoreDiVampiri(), new CapoBranco(), new CapoGilda(),
            new ContadinoEroe(), new ContadinoLupo(), new ContadinoMostro(), new ContadinoNormale(), new Eremita(), new Ghoul(), new GiovaneLupo(),
            new Giulietta(), new Giullare(), new Goblin(), new GuardiaCorrotta(), new GuardiaPrincipale(), new Guaritore(), new Inquisitore(),
            new Ladra(), new LupoBranco(), new LupoReietto(), new LupoSolitario()
        };
        assertThat(new Ruoli()).usingRecursiveFieldByFieldElementComparator().containsExactly(soluzione);
    }

}