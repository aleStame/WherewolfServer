package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;

public final class CacciatoreDiVampiri extends Villaggio
{

    private CacciatoreDiVampiri()
    {
        super
        (
    "Cacciatore di vampiri", BIANCA,
"La prima notte scopre il Vampiro è in gioco. È protetto dal Vampiro e, se viene attaccato, viene avvisato e lo elimina", 2,
    false
        );
    }

    @Override public boolean isCacciatoreDiVampiri() { return true; }

    public static Ruolo getInstance() { return new CacciatoreDiVampiri(); }

}