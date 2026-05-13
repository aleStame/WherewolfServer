package alessandro.stamera.wherewolfserver.classi.ruoli.guardie;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import alessandro.stamera.wherewolfserver.classi.fazioni.Villaggio;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.MORTO;

public class Guardia extends Villaggio
{

    public Guardia(String nome, Aura aura, String descrizione) { super(nome, aura, descrizione, 2, false); }

    @Override public boolean isGuardia() { return true; }

    @Override public EsitoAttacco gildata() { return MORTO; }

}