package alessandro.stamera.wherewolfserver.classi.attributi_ruolo;

public final class Potere
{

    private boolean potereUtilizzato;

    public Potere() { riabilitaPotere(); }

    public boolean isPotereUtilizzato() { return potereUtilizzato; }

    public void utilizzaPotere() { setUtilizzoPotere(true); }

    public void riabilitaPotere() { setUtilizzoPotere(false); }

    private void setUtilizzoPotere(boolean potereUtilizzato) { this.potereUtilizzato = potereUtilizzato; }

}