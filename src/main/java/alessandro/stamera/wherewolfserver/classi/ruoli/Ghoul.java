package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.NESSUNA;

public class Ghoul extends Ruolo
{

    private Ghoul() { super("Ghoul", NESSUNA, BIANCA, null, 2, false); }

    @Override public boolean isGhoul() { return true; }

    public static Ruolo getInstance() { return new Ghoul(); }

}