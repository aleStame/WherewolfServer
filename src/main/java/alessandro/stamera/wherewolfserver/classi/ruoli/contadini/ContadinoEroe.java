package alessandro.stamera.wherewolfserver.classi.ruoli.contadini;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.FALLITO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.MORTO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.RIUSCITO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.TipoContadino.EROE;

public final class ContadinoEroe extends Contadino
{

    private ContadinoEroe() { super(EROE); }

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