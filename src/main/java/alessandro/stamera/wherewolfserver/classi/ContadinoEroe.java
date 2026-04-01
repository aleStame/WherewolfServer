package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.EsitoAttacco.MORTO;

public final class ContadinoEroe extends Contadino
{

    private ContadinoEroe() { super(); }

    @Override public boolean isContadinoEroe() { return true; }

    @Override public EsitoAttacco attaccoLupi(Ruolo ruolo) { return MORTO; }

    public static Ruolo getInstance() { return new ContadinoEroe(); }

}