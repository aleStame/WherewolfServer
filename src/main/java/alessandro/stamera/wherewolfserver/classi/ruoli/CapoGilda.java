package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.fazioni.Criminale;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;

public final class CapoGilda extends Criminale
{

    private CapoGilda()
    {
        super
        (
    "Capo gilda", BIANCA,
     "La prima notte riconosce gli altri criminali. Una volta per partita, dalla seconda notte, può aprire gli occhi nel turno di un " +
          "mistico. Se quel mistico non è in gioco, indica un giocatore. Se è una Guardia o un lupo mannaro, il Capo gilda viene ucciso. Se la " +
          "sua fazione è Città o Villaggio, riconosce il Capo gilda, altrimenti non accade nulla"
        );
    }

    @Override public boolean isCapoGilda() { return true; }

    public static Ruolo getInstance() { return new CapoGilda(); }

}
