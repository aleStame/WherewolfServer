package alessandro.stamera.wherewolfserver.classi;

public final class ContadinoEroe extends Contadino
{

    private ContadinoEroe() { super(); }

    @Override public boolean isContadinoEroe() { return true; }

    public static Ruolo getInstance() { return new ContadinoEroe(); }

}