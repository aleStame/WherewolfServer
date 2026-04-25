package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;

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

    @Override public boolean isPazzo() { return true; }

    public static Ruolo getInstance() { return new Pazzo(); }

}