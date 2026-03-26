package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;

public final class Mercante extends Citta
{

    public Mercante() { super("Mercante", BIANCA, null); }

    @Override public boolean isMercante() { return true; }

    public static Ruolo getInstance() { return new Mercante(); }

}