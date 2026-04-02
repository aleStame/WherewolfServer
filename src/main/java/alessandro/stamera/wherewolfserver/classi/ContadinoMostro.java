package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.EsitoAttacco.MORTO;

public final class ContadinoMostro extends Contadino
{

    private ContadinoMostro()
    {
        super();
        maledizione();
    }

    @Override public boolean isContadinoMostro() { return true; }

    @Override public EsitoAttacco attaccoNosferatu() { return MORTO; }

    @Override public EsitoAttacco attaccoLupi(Ruolo ruolo) { return MORTO; }

    public static Ruolo getInstance() { return new ContadinoMostro(); }

}
