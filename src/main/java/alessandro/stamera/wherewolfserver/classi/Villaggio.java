package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Fazione.VILLAGGIO;
import static alessandro.stamera.wherewolfserver.classi.Fazione.CRIMINALI;

public class Villaggio extends Ruolo
{

    public Villaggio(String nome, Aura aura, String descrizione, int lune, boolean mistico)
    {
        super(nome, VILLAGGIO, aura, descrizione, lune, mistico);
    }

    @Override public void gildata() { cambiaFazione(CRIMINALI); }

}
