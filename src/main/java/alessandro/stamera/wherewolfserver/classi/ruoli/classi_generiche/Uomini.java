package alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Categoria;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Categoria.UOMINI;

public class Uomini extends Ruolo
{

    public Uomini(String nome, Fazione fazione, Aura aura, String descrizione, int lune, boolean mistico)
    {
        super(nome, fazione, aura, descrizione, lune, mistico);
    }

    @Override public Categoria getCategoria() { return UOMINI; }

}