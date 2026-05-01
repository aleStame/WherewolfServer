package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.gestione_partita.Giocatori;

import java.util.ArrayList;
import java.util.List;

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

    public String getNomeGiocatorePerdente()
    {
        String soluzione = getNomeGiocatore(0);
        if(isPareggioPresente())
            throw new IllegalArgumentException("Il villaggio non ha trovato accordo su chi mandare al rogo: non viene bruciato nessuno!");
        return soluzione;
    }

    public boolean isCitta(String nome) { return getRuolo(nome).isCitta(); }

    public void segnalazioneOratore(String nome) { }

    private boolean isPareggioPresente() { return getNomiPerdenti().size() > 1; }

    private List<String> getNomiPerdenti()
    {
        int numeroVoti = getNumeroVotiPrimoClassificato();
        List<String> soluzioni = new ArrayList<>();
        for(int i = 0; i < getNumeroGiocatori(); i++)
        {
            String nome = getNomeGiocatore(i);
            if(getNumeroVoti(nome) == numeroVoti) soluzioni.add(nome);
        }
        return soluzioni;
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
