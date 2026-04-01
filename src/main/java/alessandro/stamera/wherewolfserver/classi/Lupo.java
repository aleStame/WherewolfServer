package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.EsitoAttacco.MORTO;
import static alessandro.stamera.wherewolfserver.classi.Fazione.LUPO_BRANCO;
import static alessandro.stamera.wherewolfserver.classi.Tratto.CREATURA_OMBRA;
import static alessandro.stamera.wherewolfserver.classi.Tratto.LUPO_MANNARO;

public class Lupo extends Ruolo
{

    public Lupo(String nome, String descrizione, int lune)
    {
        super(nome, LUPO_BRANCO, NERA, descrizione, lune, false);
        aggiungiTratti(CREATURA_OMBRA, LUPO_MANNARO);
    }

    @Override public boolean isLupo() { return true; }

    @Override public EsitoAttacco attaccoNosferatu() { return MORTO; }

}
