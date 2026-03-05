package alessandro.stamera.wherewolfserver.classi;

public class Potere
{

    private boolean potereUtilizzato;

    public Potere() { setUtilizzoPotere(false); }

    public boolean isPotereUtilizzato() { return potereUtilizzato; }

    public void utilizzaPotere() { setUtilizzoPotere(true); }

    private void setUtilizzoPotere(boolean potereUtilizzato) { this.potereUtilizzato = potereUtilizzato; }

}