package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.VILLAGGIO;

public class Cacciatore extends Ruolo
{

    public Cacciatore()
    {
        super
        (
    "Cacciatore", VILLAGGIO, BIANCA,
"Se la Nonna si trasforma in lupo, il Cacciatore è protetto dal lupo ex Nonna. Se in gioco è rimasto l'ultimo lupo del branco (quindi " +
          "senza contare l'eventuale presenza del Lupo solitario) o solo il LUPO SOLITARIO, il Cacciatore è protetto da questo lupo e se viene " +
          "da questo attaccato, il lupo muore, anche se uno tra il Lupo ed il Cacciatore fosse Romeo, l'Amato o protetto dalla Strega.",
     1, false
        );
    }

    @Override public boolean isCacciatore() { return true; }

}