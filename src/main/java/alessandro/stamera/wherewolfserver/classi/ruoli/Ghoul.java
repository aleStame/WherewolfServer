package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita;
import alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.SCONFITTA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.VITTORIA;

public class Ghoul extends Ruolo
{

    private Ghoul() { super("Ghoul", BIANCA, null, 2, false); }

    @Override public boolean isGhoul() { return true; }

    @Override public EsitoPartita getEsitoPartita(Partita partita)
    {
        EsitoPartita esito = super.getEsitoPartita(partita);
        if(isPartitaVinta(partita)) esito = VITTORIA;
        else if(isPartitaPersa(partita)) esito = SCONFITTA;
        return esito;
    }

    private boolean isPartitaPersa(Partita partita)
    {
        boolean perso = true;
        for(int i = 0; i < partita.getNumeroGiocatoriVivi() && perso; i++) perso = !(isGiocatoreAlleato(partita, partita.getNomeGiocatoreVivo(i)));
        return perso;
    }

    private boolean isPartitaVinta(Partita partita)
    {
        boolean vinto = true;
        for(int i = 0; i < partita.getNumeroGiocatoriVivi() && vinto; i++) vinto = isGiocatoreAlleato(partita, partita.getNomeGiocatoreVivo(i));
        return vinto;
    }

    public static Ruolo getInstance() { return new Ghoul(); }

    private boolean isGiocatoreAlleato(Partita partita, String nome)
    {
        return partita.isGhoulVivo(nome) || partita.isNosferatuVivo(nome) || partita.isProgenieNosferatuViva(nome);
    }

}