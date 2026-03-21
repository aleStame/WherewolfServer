package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.NESSUNA;

public final class Giullare extends Ruolo
{

    private Giullare()
    {
        super
        (
    "Giullare", NESSUNA, BIANCA,
"Se viene messo al rogo, il giorno successivo il rogo sarà annullato a prescindere dai voti e dai poteri utilizzati. Vince se viene " +
          "messo al rogo.",
    1, false
        );
    }

    @Override public boolean isGiullare() { return true; }

    public static Ruolo getInstance() { return new Giullare(); }

}