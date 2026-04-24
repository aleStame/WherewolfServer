package alessandro.stamera.wherewolfserver.classi;

public final class Ballottaggio extends Giocatori
{

    private static final int NON_TROVATO = -1;

    public boolean isAmatoPresente() { return getPosizioneAmato() != NON_TROVATO; }

    public String getNomeAmato() { return getNomeGiocatore(getPosizioneAmato()); }

    public boolean isSegnalazioneAssente()
    {
        return controlloNessunInquisito() && controlloNienteAzzeccagarbugli() && controlloNessunAmato();
    }

    private boolean controlloNessunAmato() { return getPosizioneAmato() == NON_TROVATO; }

    private int getPosizioneAmato()
    {
        int posizione = NON_TROVATO;
        for(int i = 0; i < getNumeroGiocatori() && posizione == NON_TROVATO; i++) if(getRuolo(getNomeGiocatore(i)).isAmato()) posizione = i;
        return posizione;
    }

    private boolean controlloNessunInquisito()
    {
        boolean esito = true;
        for(int i = 0; i < getNumeroGiocatori() && esito; i++) esito = !getRuolo(getNomeGiocatore(i)).isInquisito();
        return esito;
    }

    private boolean controlloNienteAzzeccagarbugli()
    {
        boolean esito = true;
        for(int i = 0; i < getNumeroGiocatori() && esito; i++) esito = !getRuolo(getNomeGiocatore(i)).isSegnalatoAzzeccagarbugli();
        return esito;
    }

}
