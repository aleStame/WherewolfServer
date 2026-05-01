package alessandro.stamera.wherewolfserver.classi.fazioni;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.INQUISIZIONE;

public class Inquisizione extends Ruolo
{

    public Inquisizione(String nome, Aura aura, String descrizione)
    {
        super(nome, INQUISIZIONE, aura, descrizione, 3, false);
    }

    @Override public boolean isInquisizione() { return true; }

}