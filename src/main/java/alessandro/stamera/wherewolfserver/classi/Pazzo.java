package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;

public final class Pazzo extends Ruolo
{

    private Pazzo()
    {
        super
        (
    "Pazzo", Fazione.NESSUNA, BIANCA,
"Se viene eliminato perché ucciso dai lupi mannari, la notte successiva il loro attacco sarà annullato. Vince se viene eliminato " +
          "dall'attacco dei lupi mannari",
     1, false
        );
    }

    public static Ruolo getInstance() { return new Pazzo(); }

}