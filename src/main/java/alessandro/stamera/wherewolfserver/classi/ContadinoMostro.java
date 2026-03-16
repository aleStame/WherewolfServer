package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.NERA;

public final class ContadinoMostro extends Contadino
{

    private ContadinoMostro() { super(NERA); }

    @Override public boolean isContadinoMostro() { return true; }

    public static Ruolo getInstance() { return new ContadinoMostro(); }

}
