package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.NOSFERATU;

public final class Nosferatu extends Ruolo
{

    private Nosferatu() { super("Nosferatu", NOSFERATU, NERA, null, 0, true); }

    public static Ruolo getInstance() { return new Nosferatu(); }

}