package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Fazione.CRIMINALI;

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
