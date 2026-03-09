package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Categoria.CREATURE_OMBRA;

public final class Ladra extends Criminale
{

    private final Protezioni protezioni;

    private final Potere potere;

    public Ladra()
    {
        super
        (
    "Ladra", BIANCA,
"La prima notte riconosce gli altri criminali. Una volta per partita, dalla seconda notte può aprire gli occhi nel turno di un " +
          "mistico. La prima volta che viene attaccata, è protetta dalle creature dell'ombra."
        );
        protezioni = getProtezioni();
        potere = new Potere();
    }

    @Override public boolean isLadra() { return true; }

    @Override public void utilizzaPotere()
    {
        perdiProtezioni();
        potere.utilizzaPotere();
    }

    @Override public boolean isPotereUtilizzato() { return potere.isPotereUtilizzato(); }

    @Override public boolean isProtezionePresente(Fazione fazione) { return protezioni.isPresente(fazione); }

    @Override public void perdiProtezioni() { protezioni.perdiProtezioni(); }

    private Protezioni getProtezioni()
    {
        Protezioni protezioni = new Protezioni();
        protezioni.aggiungiProtezione(CREATURE_OMBRA);
        return protezioni;
    }

}