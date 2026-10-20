package alessandro.stamera.wherewolfserver.classi.ruoli.contadini;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.TipoContadino;
import alessandro.stamera.wherewolfserver.classi.fazioni.Villaggio;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;

public class Contadino extends Villaggio
{

    private final TipoContadino tipoContadino;

    public Contadino(TipoContadino tipoContadino)
    {
        super
        (
        "Contadino", BIANCA,
    "Il Contadino ha una delle seguenti identità nascoste (a sua insaputa) : Semplice, Eroe, Discendente dei Lupi, Mostro.", 1,
        false,
        "Non sottovalutate l’importanza dei Contadini all’interno del villaggio. I Lupi Mannari ci penseranno bene prima di attaccare un " +
                "Contadino, in quanto un Lupo in più o in meno potrebbe risultare determinante per la vittoria o la sconfitta, e probabilmente lo " +
                "faranno solo in caso di estrema necessità.\nIl Mostro, agli occhi del giocatore che lo gioca, è un Contadino come gli altri e " +
                "come tale verrà giocato. Tuttavia, la possibilità che il Mostro sia una delle tre possibili identità segrete aggiungerà pathos a " +
                "questo Ruolo. Il Mostro è l’unico ruolo a giocare per il Villaggio ed essere, allo stesso tempo, una delle Creature dell’Ombra " +
                "che devono essere eliminate. Potenzialmente pericolosissimo sia per il Villaggio che per l’Ombra, è uno dei Ruoli più " +
                "inquietanti e imprevedibili. Se giocherete il Mostro, non guarderete più un Contadino allo stesso modo."
        );
        this.tipoContadino = tipoContadino;
    }

    @Override public boolean isContadino() { return true; }

    @Override public TipoContadino getTipoContadino() { return tipoContadino; }

}
