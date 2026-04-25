package alessandro.stamera.wherewolfserver.classi.fazioni;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Potere;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.CRIMINALI;

public class Criminale extends Ruolo
{

    private final Potere potere;

    public Criminale(String nome, Aura aura, String descrizione)
    {
        super(nome, CRIMINALI, aura, descrizione, 2, false);
        potere = new Potere();
    }

    @Override public boolean isCriminale() { return true; }

    @Override public boolean isPotereUtilizzato() { return potere.isPotereUtilizzato(); }

    @Override public void utilizzaPotere() { potere.utilizzaPotere(); }

    @Override public int getNumeroVoti()
    {
        if(isSegnalatoAzzeccagarbugli()) annullaVoti();
        return super.getNumeroVoti();
    }

}
