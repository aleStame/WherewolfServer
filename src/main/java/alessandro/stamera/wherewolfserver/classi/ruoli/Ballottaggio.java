package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.gestione_partita.Giocatori;

public final class Ballottaggio extends Giocatori
{

    private static final int NON_TROVATO = -1;

    public boolean isAmatoPresente() { return getPosizioneAmato() != NON_TROVATO; }

    public String getNomeAmato() { return getNomeGiocatore(getPosizioneAmato()); }

    public boolean isSegnalazioneAssente() { return controlloNessunInquisito() && controlloNienteAzzeccagarbugli(); }

    @Override public void segnalazioneBoia(String nome)
    {
        super.segnalazioneBoia(nome);
        if(isSegnalatoBoia(nome)) annullaVotiBoia(nome);
    }

    public void annullaSegnalazioni()
    {
        annullaSegnalazioneAzzeccagarbugli();
        annullaSegnalazioneInquisitore();
    }

    private void annullaVotiBoia(String nome) { for(int i = 0; i < getNumeroGiocatori(); i++) annullaVotiBoia(nome, i); }

    private void annullaVotiBoia(String nome, int posizione)
    {
        String chiave = getNomeGiocatore(posizione);
        if(!chiave.equals(nome)) annullaVoti(chiave);
    }

    private int getPosizioneAmato()
    {
        int posizione = NON_TROVATO;
        for(int i = 0; i < getNumeroGiocatori() && posizione == NON_TROVATO; i++) if(isAmato(getNomeGiocatore(i))) posizione = i;
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
        for(int i = 0; i < getNumeroGiocatori(); i++) annullaSegnalazioneAzzeccagarbugli(getNomeGiocatore(i));
    }

    private void annullaSegnalazioneInquisitore(String nome) { getRuolo(nome).annullaSegnalazioneInquisitore(); }

    private void annullaSegnalazioneAzzeccagarbugli(String nome) { getRuolo(nome).annullaSegnalazioneAzzeccagarbugli(); }

}
