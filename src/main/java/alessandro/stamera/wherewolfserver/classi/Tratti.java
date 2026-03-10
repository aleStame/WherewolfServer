package alessandro.stamera.wherewolfserver.classi;

import java.util.ArrayList;
import java.util.List;
import static alessandro.stamera.wherewolfserver.classi.Tratto.PROTETTO;

public final class Tratti
{

    private final List<Tratto> tratti;

    private final Protezioni protezioni;

    public Tratti()
    {
        tratti = new ArrayList<>();
        protezioni = new Protezioni();
    }

    public void aggiungiLupi()
    {
        aggiungi(PROTETTO);
        protezioni.aggiungiLupi();
    }

    public void aggiungi(Tratto tratto) { tratti.add(tratto); }

    public boolean isPresente(Tratto tratto) { return tratti.contains(tratto); }

    public boolean isProtezionePresente(Ruolo ruolo) { return protezioni.isPresente(ruolo); }

}