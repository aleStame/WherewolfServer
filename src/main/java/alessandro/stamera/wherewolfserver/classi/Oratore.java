package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;

public final class Oratore extends Citta
{

    private Oratore()
    {
        super
        (
    "Oratore", BIANCA,
"Può votare al ballottaggio anche se è accusato. Se un altro giocatore con fazione Città dovesse andare al rogo, il rogo viene " +
          "annullato. Se non è accusato, può segnalare uno o più giocatori durante il ballottaggio: se uno di quei giocatori dovesse andare al " +
          "rogo, il rogo viene annullato"
        );
    }

    @Override public boolean isOratore() { return true; }

    public static Ruolo getInstance() { return new Oratore(); }

}