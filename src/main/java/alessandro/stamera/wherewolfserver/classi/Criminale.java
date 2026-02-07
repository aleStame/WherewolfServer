package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Fazione.CRIMINALI;

public class Criminale extends Ruolo
{

    private boolean potereUsato;

    public Criminale(String nome, Aura aura, String descrizione)
    {
        super(nome, CRIMINALI, aura, descrizione, 2, false);
        potereUsato = false;
    }

    @Override public void segnalazioneAzzeccagarbugli() { annullaVoti(); }

    @Override public boolean isPotereUtilizzato() { return potereUsato; }

    @Override public void utilizzaPotere() { potereUsato = true; }

    @Override public boolean isCriminale() { return true; }

}
