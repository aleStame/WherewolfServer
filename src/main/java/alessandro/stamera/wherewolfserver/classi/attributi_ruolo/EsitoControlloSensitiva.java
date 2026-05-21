package alessandro.stamera.wherewolfserver.classi.attributi_ruolo;

public enum EsitoControlloSensitiva
{

    VILLAGGIO("Villaggio"), NON_VILLAGGIO("Non villaggio");

    private final String messaggio;

    EsitoControlloSensitiva(String messaggio) { this.messaggio = messaggio; }

    @Override public String toString() { return messaggio; }

    public static EsitoControlloSensitiva getEsitoControlloSensitiva(String messaggio) { return null; }

}