package alessandro.stamera.wherewolfserver.classi.ruoli.lupi;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import alessandro.stamera.wherewolfserver.classi.fazioni.Lupo;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.RIUSCITO;

public final class LupoBranco extends Lupo
{

    private LupoBranco()
    {
        super
        (
    "Lupo del branco",
"La prima notte individua il Traditore e riconosce i lupi del branco. Dalla seconda notte può indicare un giocatore che verrà " +
          "ucciso se è il lupo più potente in gioco.",
     1);
    }

    @Override public boolean isLupoBranco() { return true; }

    @Override public EsitoAttacco attaccoLupi(Ruolo lupo)
    {
        EsitoAttacco esito = super.attaccoLupi(lupo);
        if(lupo.isCapoBranco()) esito = RIUSCITO;
        return esito;
    }

    public static Ruolo getInstance() { return new LupoBranco(); }

}