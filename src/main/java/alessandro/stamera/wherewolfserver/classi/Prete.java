package alessandro.stamera.wherewolfserver.classi;

public final class Prete extends Villaggio
{

    private Prete() { super("Prete", null, null, 0, true); }

    public static Ruolo getInstance() { return new Prete(); }

}