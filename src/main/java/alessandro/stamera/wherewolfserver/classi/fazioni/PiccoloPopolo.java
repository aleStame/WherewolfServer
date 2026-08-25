package alessandro.stamera.wherewolfserver.classi.fazioni;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.FALLITO;

public class PiccoloPopolo extends Ruolo
{

    public PiccoloPopolo(String nome, Aura aura, String descrizione) { super(nome, aura, descrizione, 1, true); }

    @Override public EsitoAttacco gildata() { return FALLITO; }

    @Override public boolean isPiccoloPopolo() { return true; }

}