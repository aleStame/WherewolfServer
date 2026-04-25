package alessandro.stamera.wherewolfserver.classi.fazioni;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.VILLAGGIO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.CRIMINALI;

public class Villaggio extends Ruolo
{

    public Villaggio(String nome, Aura aura, String descrizione, int lune, boolean mistico)
    {
        super(nome, VILLAGGIO, aura, descrizione, lune, mistico);
    }

    @Override public void gildata() { cambiaFazione(CRIMINALI); }

    @Override public boolean isVillaggio() { return true; }

}
