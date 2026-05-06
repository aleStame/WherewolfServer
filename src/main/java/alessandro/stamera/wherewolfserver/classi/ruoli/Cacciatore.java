package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import alessandro.stamera.wherewolfserver.classi.fazioni.Villaggio;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.MORTO;

public final class Cacciatore extends Villaggio
{

    private Cacciatore()
    {
        super
        (
    "Cacciatore", BIANCA,
"Se la Nonna si trasforma in lupo, il Cacciatore è protetto dal lupo ex Nonna. Se in gioco è rimasto l'ultimo lupo del branco (quindi " +
          "senza contare l'eventuale presenza del Lupo solitario) o solo il LUPO SOLITARIO, il Cacciatore è protetto da questo lupo e se viene " +
          "da questo attaccato, il lupo muore, anche se uno tra il Lupo ed il Cacciatore fosse Romeo, l'Amato o protetto dalla Strega.",
     1, false
        );
    }

    @Override public boolean isCacciatore() { return true; }

    @Override public EsitoAttacco attaccoLupi(Ruolo lupo)
    {
        EsitoAttacco esito = super.attaccoLupi(lupo);
        if(lupo.isLupoSolitario()) esito = MORTO;
        return esito;
    }

    public static Ruolo getInstance() { return new Cacciatore(); }

}