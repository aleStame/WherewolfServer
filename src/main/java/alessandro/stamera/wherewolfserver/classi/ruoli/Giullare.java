package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;

public final class Giullare extends Ruolo
{

    private Giullare()
    {
        super
        (
            "Giullare", BIANCA,
            "Se viene messo al rogo, il giorno successivo il rogo sarà annullato a prescindere dai voti e dai poteri utilizzati. " +
            "Vince se viene messo al rogo.",
            1, false
        );
    }

    @Override public boolean isGiullare() { return true; }

    public static Ruolo getInstance() { return new Giullare(); }

}