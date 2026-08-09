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
        super("Lupo solitario", null, 3);
    }

    @Override public boolean isLupoSolitario() { return true; }

    @Override public EsitoAttacco attaccoLupi(Ruolo ruolo) { return FALLITO; }

    @Override public Fazione getFazione() { return LUPO_SOLITARIO; }

    public static Ruolo getInstance() { return new LupoSolitario(); }

}