package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;

public final class ContadinoEroe extends Contadino
{

    private ContadinoEroe() { super(BIANCA); }

    @Override public boolean isContadinoEroe() { return true; }

    public static Ruolo getInstance() { return new ContadinoEroe(); }

}