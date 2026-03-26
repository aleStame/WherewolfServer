package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;

public final class Mercante extends Citta
{

    public Mercante()
    {
        super
        (
    "Mercante", BIANCA,
"Può votare al ballottaggio anche se è accusato. In ogni votazione, non ha limite sul numero di giocatori per cui può votare. I voti " +
          "che il Mercante riceve vengono ridotti di uno per ogni altro giocatore della fazione Città in gioco."
        );
    }

    @Override public boolean isMercante() { return true; }

    public static Ruolo getInstance() { return new Mercante(); }

}