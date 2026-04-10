package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.NERA;

public final class Peccatore extends Villaggio
{

    private Peccatore()
    {
        super
        (
    "Peccatore", NERA,
"La prima notte viene individuato dal Prete e apre gli occhi nel turno del Posseduto. Se rimane in gioco, vince con una vittoria degli " +
          "uomini, altrimenti con il Posseduto",
     1, false
        );
    }

    public static Ruolo getInstance() { return new Peccatore(); }

}