package alessandro.stamera.wherewolfserver.classi.fazioni;

import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Uomini;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.AMANTI;

public class Amanti extends Uomini
{

    public Amanti(String nome, String descrizione) { super(nome, AMANTI, BIANCA, descrizione, 2, false); }

    @Override public boolean isAmanti() { return true; }

}