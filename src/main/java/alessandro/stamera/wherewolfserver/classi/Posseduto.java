package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Fazione.POSSEDUTO;

public final class Posseduto extends Ruolo
{

    private Posseduto() { super("Posseduto", POSSEDUTO, null, null, 0, true); }

    public static Ruolo getInstance() { return new Posseduto(); }

}