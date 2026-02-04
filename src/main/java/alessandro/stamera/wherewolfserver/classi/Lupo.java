package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.LUPO_BRANCO;

public class Lupo extends Ruolo
{

    public Lupo(String nome, String descrizione, int lune) { super(nome, LUPO_BRANCO, NERA, descrizione, lune, false); }

    @Override public boolean gildata() { return false; }

    @Override public boolean isLupo() { return !super.isLupo(); }

}
