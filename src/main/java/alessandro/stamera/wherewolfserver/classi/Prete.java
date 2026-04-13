package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;

public final class Prete extends Villaggio
{

    private Prete() { super("Prete", BIANCA, null, 0, true); }

    public static Ruolo getInstance() { return new Prete(); }

}