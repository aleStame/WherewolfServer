package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.FALLITO;

public final class Pazzo extends Ruolo
{

    private Pazzo()
    {
        super
        (
    "Pazzo", BIANCA,
"Se viene eliminato perché ucciso dai lupi mannari, la notte successiva il loro attacco sarà annullato. Vince se viene eliminato " +
          "dall'attacco dei lupi mannari",
     1, false
        );
    }

    @Override public boolean isPazzo() { return true; }
    
    @Override public EsitoAttacco gildata() { return FALLITO; }

    public static Ruolo getInstance() { return new Pazzo(); }

}