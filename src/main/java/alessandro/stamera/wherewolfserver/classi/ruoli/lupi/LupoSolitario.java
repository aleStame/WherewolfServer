package alessandro.stamera.wherewolfserver.classi.ruoli.lupi;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione;
import alessandro.stamera.wherewolfserver.classi.fazioni.Lupo;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.FALLITO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.LUPO_SOLITARIO;

public final class LupoSolitario extends Lupo
{

    private LupoSolitario()
    {
        super
        (
    "Lupo solitario", null, 3,
    "La partita, da Lupo Solitario, sarà estremamente interessante. Da una parte, avete tutte le informazioni per fare in modo che i " +
            "Lupi del Branco vengano debellati in poco tempo. Dall’altra, dovete capire come far arrivare queste informazioni al villaggio senza " +
            "destare sospetti a vostra volta. Come dice il vostro nome, non avrete alleati, se non, eventualmente, il Traditore, che tuttavia " +
            "non conoscerà la vostra identità. Ricordatevi che voi sapete tutto dei Lupi del Branco, ma loro non sanno nulla di voi, se non " +
            "quello che farete loro credere. La vittoria del Lupo Solitario è ardua da raggiungere, ma proprio per questo, una di quelle che può " +
            "dare più soddisfazioni"
        );
    }

    @Override public boolean isLupoSolitario() { return true; }

    @Override public EsitoAttacco attaccoLupi(Ruolo ruolo) { return FALLITO; }

    @Override public Fazione getFazione() { return LUPO_SOLITARIO; }

    public static Ruolo getInstance() { return new LupoSolitario(); }

}