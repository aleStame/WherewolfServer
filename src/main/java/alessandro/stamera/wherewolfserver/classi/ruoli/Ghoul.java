package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita;
import alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.SCONFITTA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.VITTORIA;

public class Ghoul extends Ruolo
{

    private Ghoul()
    {
        super
        (
    "Ghoul", BIANCA,
"La prima notte riconosce la Megera, inoltre apre gli occhi nel turno del Vampiro e del Nosferatu. Se il Vampiro o il Nosferatu " +
          "dovessero essere uccisi durante il proprio turno, al loro posto morirà il Nosferatu. Vince se vince uno dei due.",
     2, false
        );
    }

    @Override public boolean isGhoul() { return true; }

    @Override public EsitoPartita getEsitoPartita(Partita partita)
    {
        EsitoPartita esito = super.getEsitoPartita(partita);
        if(partita.isNosferatuVincitore()) esito = VITTORIA;
        else esito = SCONFITTA;
        return esito;
    }

    public static Ruolo getInstance() { return new Ghoul(); }

    private boolean isGiocatoreAlleato(Partita partita, String nome)
    {
        return partita.isGhoulVivo(nome) || partita.isNosferatuVivo(nome) || partita.isProgenieNosferatuViva(nome);
    }

}