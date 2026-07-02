package alessandro.stamera.wherewolfserver.classi.ruoli.contadini;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.FALLITO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.MORTO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.CRIMINALI;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.TipoContadino.MOSTRO;

public final class ContadinoMostro extends Contadino
{

    private ContadinoMostro()
    {
        super(MOSTRO);
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

    @Override public EsitoAttacco attaccoAssassino()
    {
        EsitoAttacco esito = getEsitoAttaccoDefault();
        if(isAmato()) esito = FALLITO;
        return esito;
    }

    @Override public EsitoAttacco gildata()
    {
        cambiaFazione(CRIMINALI);
        return getEsitoAttaccoDefault();
    }

    @Override public EsitoAttacco attaccoNegromante()
    {
        EsitoAttacco esito = MORTO;
        if(isRomeo()) esito = FALLITO;
        return esito;
    }

    @Override public void ripristina()
    {
        super.ripristina();
        maledizione();
    }

    public static Ruolo getInstance() { return new ContadinoMostro(); }

    @Override public EsitoAttacco vampirizzazione() { return getEsitoAttaccoDefault(); }

    private EsitoAttacco getEsitoAttaccoDefault() { return MORTO; }

}
