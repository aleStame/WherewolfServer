package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;

public final class Ladra extends Criminale
{

    private final Potere potere;

    private Ladra()
    {
        super
        (
    "Ladra", BIANCA,
"La prima notte riconosce gli altri criminali. Una volta per partita, dalla seconda notte può aprire gli occhi nel turno di un " +
          "mistico. La prima volta che viene attaccata, è protetta dalle creature dell'ombra."
        );
        potere = new Potere();
    }

    @Override public boolean isLadra() { return true; }

    @Override public void utilizzaPotere()
    {
        perdiProtezioni();
        potere.utilizzaPotere();
    }

    @Override public boolean isPotereUtilizzato() { return potere.isPotereUtilizzato(); }

    public static Ruolo getInstance() { return new Ladra(); }

}