package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;

public final class ContadinoNormale extends Contadino
{

    public ContadinoNormale() {
        super(BIANCA);
    }

    @Override public boolean isContadinoNormale() { return !super.isContadinoNormale(); }

}