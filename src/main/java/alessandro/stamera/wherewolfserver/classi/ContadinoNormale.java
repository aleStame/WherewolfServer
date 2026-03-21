package alessandro.stamera.wherewolfserver.classi;

public final class ContadinoNormale extends Contadino
{

    private ContadinoNormale() { super(); }

    @Override public boolean isContadinoNormale() { return true; }

    public static Ruolo getInstance() { return new ContadinoNormale(); }

}