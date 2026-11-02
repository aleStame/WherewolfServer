package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

public final class Traditore extends Ruolo
{

    private Traditore()
    {
        super
        (
    "Traditore", null, null, 0, false,
    "Il vostro obiettivo è quello di far vincere i Lupi, ma è ostacolato dal fatto che non li conoscerete. Per prima cosa dovrete " +
            "quindi cercare di capire chi siano per aiutarli nel loro compito. Ricordatevi che loro conoscono la vostra identità quindi " +
            "potrebbero darvi segnali durante la partita per farsi a loro volta riconoscere. Dal momento che possedete aura bianca, il vostro " +
            "compito dovrà essere quello di confondere le idee al villaggio e dare tempo ai vostri alleati di eliminare bersagli scomodi. E' " +
            "molto probabile che questo vi porterà ad essere scoperti prima della fine della partita, ma la vostra sopravvivenza è inutile " +
            "rispetto a quella dei Lupi che proteggete."
        );
    }

    @Override public boolean isTraditore() { return true; }

    public static Ruolo getInstance() { return new Traditore(); }

}
