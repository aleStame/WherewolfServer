package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Categoria.CREATURE_OMBRA;

public final class Ladra extends Criminale
{

    private final Protezioni protezioni;

    private final Potere potere;

    public Ladra()
    {
        super("Ladra", BIANCA, null);
        protezioni = new Protezioni();
        protezioni.aggiungiProtezione(CREATURE_OMBRA);
        potere = new Potere();
    }

    @Override public boolean isLadra() { return true; }

    @Override public void utilizzaPotere()
    {
        protezioni.perdiProtezioni();
        potere.utilizzaPotere();
    }

    @Override public boolean isPotereUtilizzato() { return potere.isPotereUtilizzato(); }

    @Override public boolean isProtezionePresente(Fazione fazione) { return protezioni.isPresente(fazione); }

}