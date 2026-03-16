package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.NERA;

public final class ContadinoLupo extends Contadino
{

    private ContadinoLupo() { super(NERA); }

    @Override public boolean isContadinoLupo() { return true; }

    public static Ruolo getInstance() { return new ContadinoLupo(); }

}
