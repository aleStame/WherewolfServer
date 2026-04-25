package alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche;

public final class RuoloNullo extends Ruolo
{

    private RuoloNullo() { super("", null, null, "", 0, false); }

    public static Ruolo getInstance() { return new RuoloNullo(); }

}
