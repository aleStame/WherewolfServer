package alessandro.stamera.wherewolfserver.classi;

public final class ContadinoMostro extends Contadino
{

    private ContadinoMostro()
    {
        super();
        maledizione();
    }

    @Override public boolean isContadinoMostro() { return true; }

    public static Ruolo getInstance() { return new ContadinoMostro(); }

}
