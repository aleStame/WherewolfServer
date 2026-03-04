package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;

public class Guardia extends Villaggio
{

    public Guardia(String nome) { super(nome, BIANCA, null, 2, false); }

    @Override public boolean isGuardia() { return true; }

}