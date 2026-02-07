package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.CITTA;

public final class Azzeccagarbugli extends Ruolo
{

    public Azzeccagarbugli()
    {
        super
        (
    "Azzeccagarbugli", CITTA, BIANCA,
"Può votare al ballottaggio anche se è accusato e può segnalare un altro giocatore durante le accuse: se la sua fazione è Città o " +
          "Criminali, i voti che riceve vengono azzerati, altrimenti sarà accusato a prescindere dai voti ricevuti.",
     2, false
        );
    }

    @Override public boolean isAzzeccagarbugli() { return true; }

    @Override public void segnalazioneAzzeccagarbugli() { annullaVoti(); }

}
