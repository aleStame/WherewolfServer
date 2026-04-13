package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Fazione.NESSUNA;

public class PiccoloPopolo extends Ruolo
{

    public PiccoloPopolo(String nome, Aura aura, String descrizione)
    {
        super(nome, NESSUNA, aura, descrizione, 1, true);
    }

    @Override public boolean isPiccoloPopolo() { return true; }

}