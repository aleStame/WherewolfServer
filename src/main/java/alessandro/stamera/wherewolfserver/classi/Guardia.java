package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;

public class Guardia extends Villaggio
{

    public Guardia(String nome)
    {
        super
        (
            nome, BIANCA,
  "La prima notte riconosce le altre guardie, poi scopre dal moderatore il numero di criminali presenti in gioco.", 2, false
        );
    }

    @Override public boolean isGuardia() { return true; }

}