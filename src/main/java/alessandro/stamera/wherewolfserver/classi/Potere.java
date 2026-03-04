package alessandro.stamera.wherewolfserver.classi;

public final class Potere extends Ruolo
{

    private boolean potereUtilizzato;

    public Potere(String nome, Fazione fazione, Aura aura, String descrizione, int lune, boolean mistico)
    {
        super(nome, fazione, aura, descrizione, lune, mistico);
        setUtilizzoPotere(false);
    }

    @Override public boolean isPotereUtilizzato() { return potereUtilizzato; }

    @Override public void utilizzaPotere() { setUtilizzoPotere(true); }

    private void setUtilizzoPotere(boolean potereUtilizzato) { this.potereUtilizzato = potereUtilizzato; }

}