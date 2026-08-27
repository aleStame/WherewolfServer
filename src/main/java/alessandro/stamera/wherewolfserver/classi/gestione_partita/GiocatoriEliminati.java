package alessandro.stamera.wherewolfserver.classi.gestione_partita;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;

public final class GiocatoriEliminati extends Giocatori
{

    public boolean isBardoPresente()
    {
        boolean esito = false;
        for(int i = 0; i < getNumeroGiocatori() && !esito; i++) esito = isBardo(i);
        return esito;
    }

    public Aura controlloMedium(String nome) { return getGiocatore(nome).getRuolo().controlloMedium(); }

    public boolean isNegromante(String nome) { return getGiocatore(nome).isNegromante(); }

    public boolean isInquisitore(String nome) { return getGiocatore(nome).isInquisitore(); }

    private boolean isBardo(int posizione) { return getGiocatore(getNomeGiocatore(posizione)).getRuolo().isBardo(); }

}