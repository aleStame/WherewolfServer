package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Fazione.CITTA;

public class Citta extends Ruolo
{

    public Citta(String nome, Aura aura, String descrizione) { super(nome, CITTA, aura, descrizione, 2, false); }

    @Override public void segnalazioneAzzeccagarbugli() { annullaVoti(); }

    @Override public boolean isCitta() { return true; }

}
