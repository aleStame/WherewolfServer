package alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione;

public class CreatureOmbra extends Ruolo
{

    public CreatureOmbra(String nome, Fazione fazione, Aura aura, String descrizione, int lune, boolean mistico)
    {
        super(nome, fazione, aura, descrizione, lune, mistico);
    }

}