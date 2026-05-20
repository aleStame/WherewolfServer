package alessandro.stamera.wherewolfserver.classi.ruoli.contadini;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.FALLITO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.MORTO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.CRIMINALI;

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

    @Override public EsitoAttacco gildata()
    {
        cambiaFazione(CRIMINALI);
        return getEsitoAttaccoDefault();
    }

    @Override public EsitoAttacco attaccoNegromante() { return MORTO; }

    public static Ruolo getInstance() { return new ContadinoMostro(); }

    private EsitoAttacco getEsitoAttaccoDefault() { return MORTO; }

}
