package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.fazioni.Villaggio;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.NEGROMANTE;

public class Becchino extends Villaggio
{

    private boolean villaggio;

    private Becchino()
    {
        super
        (
    "Becchino", BIANCA,
"La prima notte scopre se il Negromante è in gioco. Durante il turno del Negromante sceglie se riconoscerlo. Se lo fa, la sua fazione " +
          "diventa Negromante. Altrimenti, ogni mattino, se sono stati eliminati giocatori maledetti dal mattino precedente, il Moderatore lo " +
          "annuncia pubblicamente",
     3, false
        );
        stabilisciVillaggio();
    }

    @Override public boolean isBecchino() { return true; }

    @Override public void riconosciNegromante()
    {
        cambiaFazione(NEGROMANTE);
        setVillaggio(false);
    }

    @Override public boolean isFazioneNegromante() { return getFazione() == NEGROMANTE; }

    @Override public void gildata() { if(isVillaggio()) super.gildata(); }

    @Override public boolean isVillaggio() { return villaggio; }

    @Override public void ripristinaFazioneOriginale()
    {
        super.ripristinaFazioneOriginale();
        stabilisciVillaggio();
    }

    @Override public void romeizzazione()
    {
        super.romeizzazione();
        if(!isVillaggio()) cambiaFazione(NEGROMANTE);
    }

    public static Ruolo getInstance() { return new Becchino(); }

    private void setVillaggio(boolean villaggio) { this.villaggio = villaggio; }

    private void stabilisciVillaggio() { setVillaggio(true); }

}