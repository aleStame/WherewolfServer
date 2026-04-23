package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.EsitoAttacco.FALLITO;
import static alessandro.stamera.wherewolfserver.classi.EsitoAttacco.MORTO;

public final class ContadinoMostro extends Contadino
{

    private ContadinoMostro()
    {
        super();
        maledizione();
    }

    @Override public boolean isContadinoMostro() { return true; }

    @Override public EsitoAttacco attaccoNosferatu() { return getEsitoAttaccoDefault(); }

    @Override public EsitoAttacco attaccoLupi(Ruolo ruolo)
    {
        EsitoAttacco esito = getEsitoAttaccoDefault();
        if(isRomeo()) esito = FALLITO;
        return esito;
    }

    @Override public EsitoAttacco attaccoAssassino() { return getEsitoAttaccoDefault(); }

    public static Ruolo getInstance() { return new ContadinoMostro(); }

    private EsitoAttacco getEsitoAttaccoDefault() { return MORTO; }

}
