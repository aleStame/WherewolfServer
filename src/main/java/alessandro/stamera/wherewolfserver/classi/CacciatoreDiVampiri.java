package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.VILLAGGIO;

public final class CacciatoreDiVampiri extends Ruolo
{

    public CacciatoreDiVampiri()
    {
        super
        (
    "Cacciatore di vampiri", VILLAGGIO, BIANCA,
"La prima notte scopre il Vampiro è in gioco. È protetto dal Vampiro e, se viene attaccato, viene avvisato e lo elimina", 2,
    false
        );
    }

}