package alessandro.stamera.wherewolfserver.classi;

import java.util.ArrayList;
import java.util.List;
import static alessandro.stamera.wherewolfserver.classi.Tratto.PROTETTO;
import static alessandro.stamera.wherewolfserver.classi.Tratto.MALEDETTO;
import static alessandro.stamera.wherewolfserver.classi.Fazione.LUPO_BRANCO;
import static alessandro.stamera.wherewolfserver.classi.Fazione.LUPO_SOLITARIO;

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
        protezioni.aggiungiProtezione(LUPO_BRANCO, LUPO_SOLITARIO);
    }

    public boolean isMaledetto() { return isPresente(MALEDETTO); }

    public void maledizione() { aggiungi(MALEDETTO); }

    public boolean isPresente(Tratto tratto) { return tratti.contains(tratto); }

    public boolean isProtezionePresente(Ruolo ruolo) { return protezioni.isPresente(ruolo); }

    public void aggiungiProtezione(Categoria categoria) { protezioni.aggiungiProtezione(categoria); }

    public void aggiungiProtezione(Ruolo... ruoli)
    {
        if(!tratti.contains(PROTETTO)) aggiungi(PROTETTO);
        protezioni.aggiungiProtezione(ruoli);
    }

    public void aggiungiProtezioneLupi()
    {
        if(!tratti.contains(PROTETTO)) aggiungi(PROTETTO);
        protezioni.aggiungiProtezioneLupi();
    }

    public void aggiungi(Tratto tratto) { tratti.add(tratto); }

    public void perdiProtezioni()
    {
        protezioni.perdiProtezioni();
        tratti.remove(PROTETTO);
    }

}