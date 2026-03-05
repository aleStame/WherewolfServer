package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Fazione.INQUISIZIONE;

public class Inquisizione extends Ruolo
{

    public Inquisizione(String nome, Aura aura, String descrizione)
    {
        super(nome, INQUISIZIONE, aura, descrizione, 3, false);
    }

    @Override public boolean isInquisizione() { return true; }

}