package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Tratto.MALEDETTO;

public final class ContadinoMostro extends Contadino
{

    private ContadinoMostro()
    {
        super();
        aggiungiTratto(MALEDETTO);
    }

    @Override public boolean isContadinoMostro() { return true; }

    public static Ruolo getInstance() { return new ContadinoMostro(); }

}
