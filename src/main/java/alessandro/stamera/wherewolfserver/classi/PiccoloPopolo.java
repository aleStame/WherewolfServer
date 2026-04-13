package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Fazione.NESSUNA;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;

public final class PiccoloPopolo extends Ruolo
{

    public PiccoloPopolo(String nome, Aura aura, String descrizione)
    {
        super(nome, NESSUNA, aura, descrizione, 1, true);
        aggiungiProtezione(FACTORY.getRuolo("Guaritore"), FACTORY.getRuolo("Mago"), FACTORY.getRuolo("Megera"));
    }

}