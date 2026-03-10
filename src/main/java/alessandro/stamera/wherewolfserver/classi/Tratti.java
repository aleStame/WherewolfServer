package alessandro.stamera.wherewolfserver.classi;

import java.util.ArrayList;
import java.util.List;

public final class Tratti
{

    private final List<Tratto> tratti;

    public Tratti() { tratti = new ArrayList<>(); }

    public void aggiungi(Tratto tratto) { tratti.add(tratto); }

    public boolean isPresente(Tratto tratto) { return tratti.contains(tratto); }

    public void aggiungiLupi() { }

    public boolean isProtezionePresente(Ruolo ruolo) { return false; }

}