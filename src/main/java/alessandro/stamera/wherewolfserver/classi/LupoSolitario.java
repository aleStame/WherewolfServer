package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Fazione.LUPO_SOLITARIO;

public final class LupoSolitario extends Lupo
{

    public LupoSolitario()
    {
        super("Lupo solitario", null, 3);
        cambiaFazione(LUPO_SOLITARIO);
    }

    @Override public boolean isLupoSolitario() { return true; }

}