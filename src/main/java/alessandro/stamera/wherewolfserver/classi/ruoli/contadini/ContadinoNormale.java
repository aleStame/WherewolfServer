package alessandro.stamera.wherewolfserver.classi.ruoli.contadini;

import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

public final class ContadinoNormale extends Contadino
{

    private ContadinoNormale() { super(); }

    @Override public boolean isContadinoNormale() { return true; }

    public static Ruolo getInstance() { return new ContadinoNormale(); }

}