package alessandro.stamera.wherewolfserver.classi.gestione_partita;

import java.util.ArrayList;
import java.util.List;

public final class Ballottaggio extends Giocatori
{

    private static final int NON_TROVATO = -1;

    private boolean segnalazioneBorgomastro;

    public Ballottaggio() { segnalazioneBorgomastro = false; }

    public boolean isAmatoPresente() { return getPosizioneAmato() != NON_TROVATO; }

    public String getNomeAmato() { return getNomeGiocatore(getPosizioneAmato()); }

    public void segnalazioneBorgomastro() { segnalazioneBorgomastro = true; }

    @Override public void aggiungiGiocatore(String nome, Giocatore giocatore)
    {
        giocatore.annullaVoti();
        giocatore.annullaSegnalazioneInquisitore();
        giocatore.annullaSegnalazioneAzzeccagarbugli();
        super.aggiungiGiocatore(nome, giocatore);
    }

    /*@Override public void segnalazioneBoia(String nome)
    {
        super.segnalazioneBoia(nome);
        if(isSegnalatoBoia(nome)) annullaVotiBoia(nome);
    }*/

    public String getNomeGiocatorePerdente()
    {
        String soluzione = getNomeGiocatore(0);
        String messaggio = "Il villaggio non ha trovato accordo su chi mandare al rogo: non viene bruciato nessuno!";
        boolean pareggio = isPareggioPresente();
        annullaVoti();
        if(pareggio) throw new IllegalArgumentException(messaggio);
        boolean segnalato = isSegnalatoOratore(soluzione);
        annullaSegnalazioneOratore();
        if(segnalato) throw new IllegalStateException(messaggio);
        return soluzione;
    }

    public boolean isCitta(String nome) { return getGiocatore(nome).isCitta(); }

    public void segnalazioneOratore(String nome) { getGiocatore(nome).getRuolo().segnalazioneOratore(); }

    public boolean isSegnalazioneBorgomastroAvvenuta() { return segnalazioneBorgomastro; }

    private boolean isSegnalatoOratore(String nome) { return getGiocatore(nome).getRuolo().isSegnalatoOratore(); }

    private void annullaSegnalazioneOratore()
    {
        for(int i = 0; i < getNumeroGiocatori(); i++) annullaSegnalazioneOratore(i);
    }

    private void annullaSegnalazioneOratore(int posizione) { annullaSegnalazioneOratore(getNomeGiocatore(posizione)); }

    private void annullaSegnalazioneOratore(String nome) { getGiocatore(nome).getRuolo().annullaSegnalazioneOratore(); }

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
        for(int i = 0; i < getNumeroGiocatori() && posizione == NON_TROVATO; i++) if(isAmato(i)) posizione = i;
        return posizione;
    }

    private boolean isAmato(int posizione) { return isAmato(getNomeGiocatore(posizione)); }

}
