package alessandro.stamera.wherewolfserver.classi.ruoli.contadini;

import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.TipoContadino.NORMALE;

public final class ContadinoNormale extends Contadino
{

    private ContadinoNormale() { super(NORMALE); }

    @Override public boolean isContadinoNormale() { return true; }

    public static Ruolo getInstance() { return new ContadinoNormale(); }

}