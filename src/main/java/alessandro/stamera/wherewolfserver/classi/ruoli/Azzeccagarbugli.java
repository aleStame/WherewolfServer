package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.fazioni.Citta;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;

public final class Azzeccagarbugli extends Citta
{

    private Azzeccagarbugli()
    {
        super
        (
    "Azzeccagarbugli", BIANCA,
"Può votare al ballottaggio anche se è accusato e può segnalare un altro giocatore durante le accuse: se la sua fazione è Città o " +
          "Criminali, i voti che riceve vengono azzerati, altrimenti sarà accusato a prescindere dai voti ricevuti."
        );
    }

    @Override public boolean isAzzeccagarbugli() { return true; }

    public static Ruolo getInstance() { return new Azzeccagarbugli(); }

}
