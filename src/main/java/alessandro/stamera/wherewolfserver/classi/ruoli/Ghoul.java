package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita;
import alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.VITTORIA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.NESSUNA;

public class Ghoul extends Ruolo
{

    private Ghoul() { super("Ghoul", NESSUNA, BIANCA, null, 2, false); }

    @Override public boolean isGhoul() { return true; }

    @Override public EsitoPartita getEsitoPartita(Partita partita)
    {
        boolean vinto = true;
        for(int i = 0; i < partita.getNumeroGiocatoriVivi(); i++)
        {
            String nome = partita.getNomeGiocatoreVivo(i);
            vinto = (partita.isGhoulVivo(nome) || partita.isNosferatuVivo(nome) || partita.isProgenieNosferatuViva(nome));
        }
        EsitoPartita esito = super.getEsitoPartita(partita);
        if(vinto) esito = VITTORIA;
        return esito;
    }

    public static Ruolo getInstance() { return new Ghoul(); }

}