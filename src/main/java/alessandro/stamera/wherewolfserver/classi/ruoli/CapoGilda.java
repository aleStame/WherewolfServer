package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.fazioni.Criminale;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;

public final class CapoGilda extends Criminale
{

    private CapoGilda()
    {
        super
        (
    "Capo gilda", BIANCA,
"La prima notte riconosce gli altri criminali. Una volta per partita, dalla seconda notte, può aprire gli occhi nel turno di un " +
          "mistico. Se quel mistico non è in gioco, indica un giocatore. Se è una Guardia o un lupo mannaro, il Capo gilda viene ucciso. Se la " +
          "sua fazione è Città o Villaggio, riconosce il Capo gilda, altrimenti non accade nulla",
    "Il vostro Ruolo è molto importante fra i Criminali. Come l’Assassino, avete una sola occasione per usare il vostro potere e dovete " +
            "cercare di sfruttarlo al meglio. L’obiettivo è quello di riuscire a far passare dalla vostra parte qualche Ruolo importante e " +
            "sinergico con i vostri obiettivi, come la Veggente o il Borgomastro. Ma attenzione ai bersagli troppo interessanti, potrebbero " +
            "essere Guardie che vi stanno tendendo una trappola! Se sono poi presenti altri Criminali sarà poi vostro compito far capire al nuovo " +
            "arrivato chi sono i suoi alleati senza essere troppo palesi e a loro chi sia la nuova recluta della Gilda."
        );
    }

    @Override public boolean isCapoGilda() { return true; }

    public static Ruolo getInstance() { return new CapoGilda(); }

}
