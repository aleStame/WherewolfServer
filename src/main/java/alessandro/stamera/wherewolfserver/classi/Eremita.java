package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Categoria.CREATURE_OMBRA;

public final class Eremita extends Villaggio
{

    private final Protezioni protezioni;

    public Eremita()
    {
        super("Eremita", BIANCA, "È protetto dalle creature dell'ombra", 1, false);
        protezioni = new Protezioni();
        protezioni.aggiungiProtezione(CREATURE_OMBRA);
    }

    @Override public boolean isEremita() { return true; }

    @Override public boolean isProtetto(Fazione fazione) { return protezioni.isPresente(fazione); }

}
