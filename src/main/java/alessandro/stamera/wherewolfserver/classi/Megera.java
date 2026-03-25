package alessandro.stamera.wherewolfserver.classi;

public final class Megera extends Ruolo
{

    private Megera()
    {
        super
        (
    "Megera", null, null,
"La prima notte viene individuata da tutte le creature dell'ombra. Se viene indicata da un mistico, fino a che la Megera è in gioco, " +
          "quel giocatore diventa Maledetto, riceverà sempre responsi negativi e non potrà più proteggere. Vince con qualsiasi creatura dell'ombra",
     0, false
        );
    }

    public static Ruolo getInstance() { return new Megera(); }

}