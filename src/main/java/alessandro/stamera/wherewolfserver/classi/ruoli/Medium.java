package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.fazioni.Villaggio;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;

public final class Medium extends Villaggio
{

    private Medium()
    {
        super
        (
    "Medium", BIANCA, "Dalla seconda notte, indica un giocatore eliminato e scopre se possedeva aura oscura.", 1, true,
    "Il ruolo del Medium è fondamentale per capire se il villaggio sta proseguendo nella giusta direzione e per smascherare eventuali " +
            "impostori. Il vostro ruolo all'inizio non sarà preponderante, ma se riuscirete ad arrivare alla terza o quarta notte, il vostro " +
            "responso servirà ad avvalorare delle teorie e a farne crollare altre. Ricordate che potete indagare anche sui giocatori che sono " +
            "stati uccisi durante la notte, perchè anche loro potrebbero aver avuto in vita aura nera."
        );
    }

    @Override public boolean isMedium() { return true; }

    public static Ruolo getInstance() { return new Medium(); }

}
