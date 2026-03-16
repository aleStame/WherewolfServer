package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;

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

    @Override public void segnalazioneAzzeccagarbugli() { }

    public static Ruolo getInstance() { return new Azzeccagarbugli(); }

}
