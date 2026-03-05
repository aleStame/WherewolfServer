package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;

public final class Guaritore extends Villaggio
{

    private final Potere potere;

    public Guaritore()
    {
        super("Guaritore", BIANCA, null, 1, true);
        potere = new Potere();
    }

    @Override public boolean isGuaritore() { return true; }

    @Override public boolean isPotereUtilizzato() { return potere.isPotereUtilizzato(); }

    @Override public void utilizzaPotere() { potere.utilizzaPotere(); }

}
