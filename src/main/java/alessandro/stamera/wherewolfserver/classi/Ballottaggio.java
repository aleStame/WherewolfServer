package alessandro.stamera.wherewolfserver.classi;

public final class Ballottaggio extends Giocatori
{

    private static final int NON_TROVATO = -1;

    public boolean isAmatoPresente() { return getPosizioneAmato() != NON_TROVATO; }

    public String getNomeAmato() { return getNomeGiocatore(getPosizioneAmato()); }

    public boolean isSegnalazioneAssente() { return controlloNessunInquisito() && controlloNienteAzzeccagarbugli(); }

    public void annullaSegnalazioni()
    {
        annullaSegnalazioneAzzeccagarbugli();
        annullaSegnalazioneInquisitore();
    }

    private int getPosizioneAmato()
    {
        int posizione = NON_TROVATO;
        for(int i = 0; i < getNumeroGiocatori() && posizione == NON_TROVATO; i++) if(getRuolo(getNomeGiocatore(i)).isAmato()) posizione = i;
        return posizione;
    }

    private boolean controlloNessunInquisito()
    {
        boolean esito = true;
        for(int i = 0; i < getNumeroGiocatori() && esito; i++) esito = !isInquisito(getNomeGiocatore(i));
        return esito;
    }

    private boolean controlloNienteAzzeccagarbugli()
    {
        boolean esito = true;
        for(int i = 0; i < getNumeroGiocatori() && esito; i++) esito = !isSegnalatoAzzeccagarbugli(getNomeGiocatore(i));
        return esito;
    }

    private void annullaSegnalazioneInquisitore()
    {
        for(int i = 0; i < getNumeroGiocatori(); i++) annullaSegnalazioneInquisitore(getNomeGiocatore(i));
    }

    private void annullaSegnalazioneAzzeccagarbugli()
    {
        for(int i = 0; i < getNumeroGiocatori(); i++) getRuolo(getNomeGiocatore(i)).annullaSegnalazioneAzzeccagarbugli();
    }

    private void annullaSegnalazioneInquisitore(String nome) { getRuolo(nome).annullaSegnalazioneInquisitore(); }

}
