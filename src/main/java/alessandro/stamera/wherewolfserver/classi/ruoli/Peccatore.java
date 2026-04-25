package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.fazioni.Villaggio;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.NERA;

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

    @Override public boolean isPeccatore() { return true; }

    public static Ruolo getInstance() { return new Peccatore(); }

}