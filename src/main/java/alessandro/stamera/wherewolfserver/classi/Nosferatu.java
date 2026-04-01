package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.NOSFERATU;
import static alessandro.stamera.wherewolfserver.classi.Tratto.CREATURA_OMBRA;

public final class Nosferatu extends Ruolo
{

    private Nosferatu()
    {
        super("Nosferatu", NOSFERATU, NERA, null, 0, false);
        aggiungiTratti(CREATURA_OMBRA);
    }

    @Override public boolean isNosferatu() { return true; }

    public static Ruolo getInstance() { return new Nosferatu(); }

}