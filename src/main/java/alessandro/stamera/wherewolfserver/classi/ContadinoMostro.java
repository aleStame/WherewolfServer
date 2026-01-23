package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.NERA;

public final class ContadinoMostro extends Contadino
{

    public ContadinoMostro() { super(NERA); }

    @Override public boolean isContadinoMostro() { return !super.isContadinoMostro(); }

    @Override public boolean gildata() { return !super.gildata(); }

}
