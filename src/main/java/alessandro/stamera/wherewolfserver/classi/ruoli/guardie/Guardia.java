package alessandro.stamera.wherewolfserver.classi.ruoli.guardie;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import alessandro.stamera.wherewolfserver.classi.fazioni.Villaggio;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.FALLITO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.MORTO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Tratto.NON_MORTO;

public class Guardia extends Villaggio
{

    public Guardia(String nome, Aura aura, String descrizione) { super(nome, aura, descrizione, 2, false); }

    @Override public boolean isGuardia() { return true; }

    @Override public EsitoAttacco gildata()
    {
        EsitoAttacco esito = MORTO;
        if(isTrattoPresente(NON_MORTO)) esito = FALLITO;
        return esito;
    }

}