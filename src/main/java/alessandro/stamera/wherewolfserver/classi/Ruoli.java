package alessandro.stamera.wherewolfserver.classi;

import java.util.ArrayList;
import java.util.Iterator;
import static java.util.List.of;

public final class Ruoli implements Iterable<Ruolo>
{

    private final Ruolo[] ruoli;

    public Ruoli()
    {
        ruoli = new Ruolo[]
        {
            new AltraGuardia(), new AngeloCustode(), new Assassino(), new Azzeccagarbugli(), new Bardo(), new Becchino(), new BoccaDiRosa(),
            new Boia(), new Borgomastro(), new Bracconiere(), new Cacciatore(), new CacciatoreDiVampiri(), new CapoBranco(), new CapoGilda(),
            new ContadinoEroe(), new ContadinoLupo(), new ContadinoMostro(), new ContadinoNormale(), new Eremita(), new Ghoul(), new GiovaneLupo(),
            new Giulietta(), new Giullare(), new Goblin(), new GuardiaCorrotta(), new GuardiaPrincipale(), new Guaritore(), new Inquisitore(),
            new Ladra(), new LupoBranco(), new LupoReietto(), new LupoSolitario()
        };
    }

    @Override public Iterator<Ruolo> iterator() { return new ArrayList<>(of(ruoli)).iterator(); }

}