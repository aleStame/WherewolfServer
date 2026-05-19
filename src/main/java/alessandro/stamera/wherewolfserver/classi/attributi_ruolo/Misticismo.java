package alessandro.stamera.wherewolfserver.classi.attributi_ruolo;

import java.util.Arrays;
import static java.util.Arrays.stream;

public enum Misticismo
{

    MISTICO("Mistico"), NON_MISTICO("Non mistico");

    private final String messaggio;

    Misticismo(String messaggio) { this.messaggio = messaggio; }

    @Override public String toString() { return messaggio; }

    public static Misticismo getMisticismo(String descrizione)
    {
        return null;
        //return stream(values()).filter(valore -> valore.toString().equals(descrizione)).findFirst().get();
    }

}