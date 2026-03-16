package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;

public final class ContadinoNormale extends Contadino
{

    private ContadinoNormale() {
        super(BIANCA);
    }

    @Override public boolean isContadinoNormale() { return true; }

    public static Ruolo getInstance() { return new ContadinoNormale(); }

}