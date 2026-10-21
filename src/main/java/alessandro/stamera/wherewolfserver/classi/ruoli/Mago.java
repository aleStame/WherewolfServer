package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.fazioni.Villaggio;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;

public final class Mago extends Villaggio
{

    private Mago()
    {
        super
        (
    "Mago", BIANCA, "Ogni notte indica un giocatore e scopre se è mistico.", 1, true,
    "Il ruolo del Mago è fondamentale per smascherare eventuali impostori. i Mistici sono la difesa principale del villaggio contro " +
            "tutte le Creature dell'Ombra, di conseguenza i ruoli dotati di poteri mistici saranno spesso i più utilizzati come copertura dalle " +
            "Creature dell'Ombra stesse. Oltre ad esse, spesso anche il Pazzo, cercando di attirare su di sé l'attacco dei Lupi, si professerà " +
            "Mistico e lo stesso potrebbero fare Ghoul e Traditore. Usando il vostro potere in combinazione con quello della Veggente dovrete " +
            "capire quali Mistici sono affidabili, quali non lo sono, ma non costituiscono una minaccia reale e quali invece sono un pericolo " +
            "da eliminare. Non dimenticate la possibilità che la Megera sia in gioco. Dal momento che trovate un altro Mistico, e dubitate di " +
            "lui, una strategia valida potrebbe essere quella di indicarlo di nuovo per vedere se il responso del moderatore è cambiato o meno. " +
            "Se sapete che ci sono in gioco ruoli dell'Inquisizione pensateci molto bene prima di esporvi. Per il resto del villaggio " +
            "l'Inquisizione non è un problema, ma per voi e per gli altri Mistici è una minaccia reale quanto le Creature dell'Ombra."
        );
    }

    @Override public boolean isMago() { return true; }

    public static Ruolo getInstance() { return new Mago(); }

}
