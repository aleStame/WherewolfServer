package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.EsitoAttacco.FALLITO;
import static alessandro.stamera.wherewolfserver.classi.EsitoAttacco.MORTO;
import static alessandro.stamera.wherewolfserver.classi.EsitoAttacco.RIUSCITO;

public final class ContadinoEroe extends Contadino
{

    private ContadinoEroe() { super(); }

    @Override public boolean isContadinoEroe() { return true; }

    @Override public EsitoAttacco attaccoLupi(Ruolo ruolo)
    {
        EsitoAttacco esito;
        if(isRomeo()) esito = FALLITO;
        else esito = attaccoLupiContadinoNonRomeizzato(ruolo);
        return esito;
    }

    public static Ruolo getInstance() { return new ContadinoEroe(); }

    private EsitoAttacco attaccoLupiContadinoNonRomeizzato(Ruolo ruolo)
    {
        EsitoAttacco esito = super.attaccoLupi(ruolo);
        if(esito == RIUSCITO) esito = MORTO;
        return esito;
    }

}