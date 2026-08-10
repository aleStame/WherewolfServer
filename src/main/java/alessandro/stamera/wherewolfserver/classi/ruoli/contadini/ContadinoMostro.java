package alessandro.stamera.wherewolfserver.classi.ruoli.contadini;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.*;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.TipoContadino.MOSTRO;

public final class ContadinoMostro extends Contadino
{

    private ContadinoMostro()
    {
        super(MOSTRO);
        maledizione();
    }

    @Override public boolean isContadinoMostro() { return true; }

    @Override public EsitoAttacco attaccoLupi(Ruolo ruolo) { return getEsitoAttaccoDefault(); }

    @Override public EsitoAttacco attaccoNegromante()
    {
        return MORTO;
    }

    @Override public EsitoAttacco attaccoNosferatu() { return getEsitoAttaccoDefault(); }

    public static Ruolo getInstance() { return new ContadinoMostro(); }

    @Override public EsitoAttacco vampirizzazione() { return getEsitoAttaccoDefault(); }

    @Override public EsitoAttacco attaccoAssassino() { return getEsitoAttaccoDefault(); }

    private EsitoAttacco getEsitoAttaccoDefault() { return MORTO; }

}
