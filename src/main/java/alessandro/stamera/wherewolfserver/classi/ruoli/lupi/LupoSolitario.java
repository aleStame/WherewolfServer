package alessandro.stamera.wherewolfserver.classi.ruoli.lupi;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import alessandro.stamera.wherewolfserver.classi.fazioni.Lupo;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.FALLITO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.LUPO_SOLITARIO;

public final class LupoSolitario extends Lupo
{

    private LupoSolitario()
    {
        super("Lupo solitario", null, 3);
        cambiaFazione(LUPO_SOLITARIO);
    }

    @Override public boolean isLupoSolitario() { return true; }

    @Override public EsitoAttacco attaccoLupi(Ruolo ruolo) { return FALLITO; }

    public static Ruolo getInstance() { return new LupoSolitario(); }

}