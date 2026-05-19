package alessandro.stamera.wherewolfserver.classi.attributi_ruolo;

public enum Misticismo
{

    MISTICO("Mistico"), NON_MISTICO("Non mistico");

    private final String messaggio;

    Misticismo(String messaggio) { this.messaggio = messaggio; }

    @Override public String toString() { return messaggio; }

}