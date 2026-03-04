package alessandro.stamera.wherewolfserver.classi;

public class Guardia extends Villaggio
{

    public Guardia(String nome, Aura aura)
    {
        super
        (
            nome, aura,
  "La prima notte riconosce le altre guardie, poi scopre dal moderatore il numero di criminali presenti in gioco.", 2, false
        );
    }

    @Override public boolean isGuardia() { return true; }

}