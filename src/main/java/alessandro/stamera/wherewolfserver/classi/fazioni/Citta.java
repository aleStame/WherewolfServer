package alessandro.stamera.wherewolfserver.classi.fazioni;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.RIUSCITO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.CITTA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.CRIMINALI;

public class Citta extends Ruolo
{

    public Citta(String nome, Aura aura, String descrizione) { super(nome, CITTA, aura, descrizione, 2, false); }

    @Override public boolean isCitta() { return true; }

    @Override public EsitoAttacco gildata()
    {
        cambiaFazione(CRIMINALI);
        return RIUSCITO;
    }

}