package alessandro.stamera.wherewolfserver.classi.gestione_partita;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import static alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.RuoloNullo.getInstance;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.RIUSCITO;

public final class GiocatoriMortiNotte extends Giocatori
{

    public Ruolo progenizzazioneNosferatu(String nome)
    {
        Ruolo ruolo = getRuolo(nome);
        EsitoAttacco esito = ruolo.attaccoNosferatu();
        Ruolo risultato = getInstance();
        if(esito == RIUSCITO)
        {
            risultato = ruolo;
            eliminaGiocatore(nome);
        }
        return risultato;
    }

}
