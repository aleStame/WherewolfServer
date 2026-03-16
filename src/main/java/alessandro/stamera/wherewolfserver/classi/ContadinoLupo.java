package alessandro.stamera.wherewolfserver.classi;

public final class ContadinoLupo extends Contadino
{

    private ContadinoLupo() { super(); }

    @Override public boolean isContadinoLupo() { return true; }

    public static Ruolo getInstance() { return new ContadinoLupo(); }

}
