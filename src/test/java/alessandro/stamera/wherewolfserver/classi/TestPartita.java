package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestPartita
{

    private static final String[][] ESEMPI_GIOCATORI = new String[][]
    {
        { "Marco", "Angelo custode" }, { "Giulio", "Pazzo" }, { "Cesare", "Peccatore" }, { "Augusto", "Prete" }, { "Eva", "Capo branco" },
        { "Annibale", "Guaritore" }
    };

    private Partita partita;

    @BeforeEach public void setUp() { partita = new Partita(ESEMPI_GIOCATORI); }

    @Test public void testBallottaggioPuro()
    {
        int[] numeroVoti = new int[] { 2, 1 };
        for(int i = 0; i < numeroVoti.length; i++) incrementaVoti(i + 1, numeroVoti[i]);
        terminaVotazioni();
        verificaAccusati(getNomeGiocatoreEsempio(2), getNomeGiocatoreEsempio(1));
    }

    @Test public void testUnanimita()
    {
        int posizione = 0;
        incrementaVoti(posizione, 3);
        terminaVotazioni();
        verificaAccusati(getNomeGiocatoreEsempio(0));
    }

    @Test public void testPareggioPrimoPosto()
    {
        int numeroGiocatori = getNumeroGiocatoriEsempio();
        for(int i = 1; i < numeroGiocatori; i++) incrementaVoti(i, 1);
        terminaVotazioni();
        verificaAccusati(estraiUltimiTreEsempi());
    }

    @Test public void testPareggioSecondoPosto()
    {
        int[] numeroVoti = new int[]{ 2, 1, 1 };
        for(int i = 0; i < numeroVoti.length; i++) incrementaVoti(i, numeroVoti[i]);
        terminaVotazioni();
        verificaAccusati(getNomeGiocatoreEsempio(2), getNomeGiocatoreEsempio(1), getNomeGiocatoreEsempio(0));
    }

    @Test public void testAngeloCustodeAccusatoNonPresente()
    {
        int posizione = 2;
        String nome = getNomeGiocatoreEsempio(posizione);
        segnalazioneAngeloCustode(nome);
        incrementaVoti(posizione, 3);
        terminaVotazioni();
        verificaAccusati(getNomeGiocatoreEsempio(0));
        verificaNonAccusato(nome);
    }

    @Test public void testAngeloCustodeAccusatoPresente()
    {
        String nome = getNomeGiocatoreEsempio(1);
        segnalazioneAngeloCustode(nome);
        for(int i = 0; i < 3; i++) incrementaVoti(i, 2);
        terminaVotazioni();
        verificaAccusati(getNomeGiocatoreEsempio(2), getNomeGiocatoreEsempio(0));
        verificaNonAccusato(nome);
    }

    @Test public void testAttaccoAssassino()
    {
        String nome = getNomeGiocatoreEsempio(2);
        attaccoAssassino(nome);
        verificaEliminazione(nome);
    }

    @Test public void testAttaccoAmatoAssassino()
    {
        String nomeAngeloCustode = getNomeGiocatoreEsempio(0), nomeAmato = getNomeGiocatoreEsempio(3);
        segnalazioneAngeloCustode(nomeAmato);
        attaccoAssassino(nomeAmato);
        verificaEliminazione(nomeAngeloCustode);
        verificaVero(isVivo(nomeAmato));
    }

    @Test public void testSegnalazioneAzzeccagarbugli()
    {
        segnalazioneAzzeccagarbugli(getNomeGiocatoreEsempio(1));
        int numeroGiocatori = getNumeroGiocatoriEsempio();
        for(int i = 2; i < numeroGiocatori; i++) incrementaVoti(i, 1);
        terminaVotazioni();
        verificaAccusati(estraiUltimiTreEsempi());
    }

    @Test public void testSegnalazioneAzzeccagarbugliAmato()
    {
        String nome = getNomeGiocatoreEsempio(2);
        segnalazioneAzzeccagarbugli(nome);
        segnalazioneAngeloCustode(nome);
        for(int i = 2; i < getNumeroGiocatoriEsempio(); i++) incrementaVoti(i, 1);
        terminaVotazioni();
        verificaAccusati(getNomeGiocatoreEsempio(3), getNomeGiocatoreEsempio(0));
        verificaNonAccusato(nome);
    }

    @Test public void testAttaccoLupiAngeloCustode()
    {
        String nome = getNomeGiocatoreEsempio(0);
        attaccoLupi(nome);
        verificaEliminazione(nome);
    }

    @Test public void testAttaccoLupiAmato()
    {
        String nome = getNomeGiocatoreEsempio(1);
        segnalazioneAngeloCustode(nome);
        attaccoLupi(nome);
        assertThat(isVivo(nome)).isTrue();
    }

    @Test public void testSegnalazioneInquisitoreMisticoAssente()
    {
        String nomeMistico = getNomeGiocatoreEsempio(getNumeroGiocatoriEsempio() - 1);
        partita.segnalazioneInquisitore(nomeMistico);
        int posizioneVoto = 1;
        incrementaVoti(posizioneVoto, 2);
        terminaVotazioni();
        verificaAccusati(nomeMistico, getNomeGiocatoreEsempio(posizioneVoto));
    }

    @Test public void testSegnalazioneInquisitoreMisticoPresente()
    {
        int posizioneMistico = getNumeroGiocatoriEsempio() - 1;
        String nomeMistico = getNomeGiocatoreEsempio(posizioneMistico);
        partita.segnalazioneInquisitore(nomeMistico);
        for(int i = 3; i < getNumeroGiocatoriEsempio(); i++) incrementaVoti(i, 2);
        terminaVotazioni();
        verificaAccusati(nomeMistico, getNomeGiocatoreEsempio(posizioneMistico - 1));
    }

    @Test public void testSegnalazioneInquisitoreMisticoAssenteAmato()
    {
        String nomeMistico = getNomeGiocatoreEsempio(getNumeroGiocatoriEsempio() - 1);
        partita.segnalazioneInquisitore(nomeMistico);
        segnalazioneAngeloCustode(nomeMistico);
        int posizioneVoto = 1;
        incrementaVoti(posizioneVoto, 2);
        terminaVotazioni();
        verificaAccusati(getNomeGiocatoreEsempio(posizioneVoto), getNomeGiocatoreEsempio(0));
    }

    @Test public void testSegnalazioneInquisitoreMisticoPresenteAmato()
    {
        int posizioneMistico = getNumeroGiocatoriEsempio() - 1;
        String nomeMistico = getNomeGiocatoreEsempio(posizioneMistico);
        partita.segnalazioneInquisitore(nomeMistico);
        segnalazioneAngeloCustode(nomeMistico);
        for(int i = 3; i < getNumeroGiocatoriEsempio(); i++) incrementaVoti(i, 2);
        terminaVotazioni();
        verificaAccusati(getNomeGiocatoreEsempio(posizioneMistico - 1), getNomeGiocatoreEsempio(0));
    }

    private void incrementaVoti(int posizione, int numeroVoti) { partita.incrementaVoti(getNomeGiocatoreEsempio(posizione), numeroVoti); }

    private void terminaVotazioni() { partita.terminaVotazioni(); }

    private void verificaAccusati(String... nomi) { for(String nome : nomi) verificaVero(isAccusato(nome)); }

    private void verificaNonAccusato(String nome) { verificaFalso(isAccusato(nome)); }

    private String[] estraiUltimiTreEsempi()
    {
        String[] soluzioni = new String[getNumeroGiocatoriEsempio() - 1];
        for(int i = 0; i < soluzioni.length; i++) soluzioni[i] = getNomeGiocatoreEsempio(i + 1);
        return soluzioni;
    }

    private String getNomeGiocatoreEsempio(int posizione) { return ESEMPI_GIOCATORI[posizione][0]; }

    private boolean isAccusato(String nome) { return partita.isAccusato(nome); }

    private void segnalazioneAngeloCustode(String nome) { partita.segnalazioneAngeloCustode(nome); }

    private void attaccoAssassino(String nome) { partita.attaccoAssassino(nome); }

    private void verificaEliminazione(String nome)
    {
        verificaVero(partita.isEliminato(nome));
        verificaFalso(isVivo(nome));
    }

    private boolean isVivo(String nome) { return partita.isVivo(nome); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

    private int getNumeroGiocatoriEsempio() { return ESEMPI_GIOCATORI.length; }

    private void segnalazioneAzzeccagarbugli(String nome) { partita.segnalazioneAzzeccagarbugli(nome); }

    private void attaccoLupi(String nome) { partita.attaccoLupi(nome); }

}