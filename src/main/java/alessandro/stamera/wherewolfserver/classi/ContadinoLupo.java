package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.NERA;

public final class ContadinoLupo extends Contadino
{

    public ContadinoLupo() { super(NERA); }

    @Override public boolean isContadinoLupo() { return !super.isContadinoLupo(); }

}
