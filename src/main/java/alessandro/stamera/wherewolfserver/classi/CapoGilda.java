package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.CRIMINALI;

public final class CapoGilda extends Ruolo
{

    public CapoGilda()
    {
        super
        (
    "Capo gilda", CRIMINALI, BIANCA,
"La prima notte riconosce gli altri criminali. Una volta per partita, dalla seconda notte, può aprire gli occhi nel turno di un " +
          "mistico. Se quel mistico non è in gioco, indica un giocatore. Se è una Guardia o un lupo mannaro, il Capo gilda viene ucciso. Se la " +
          "sua fazione è Città o Villaggio, riconosce il Capo gilda, altrimenti non accade nulla",
    2, false
        );
    }

    public void segnalazioneAzzeccagarbugli() { annullaVoti(); }

    @Override public boolean isCapoGilda() { return true; }

}
