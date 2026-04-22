package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.EsitoAttacco.FALLITO;
import static alessandro.stamera.wherewolfserver.classi.EsitoAttacco.RIUSCITO;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestGiocatoriVivi
{

    private static final String[][] ESEMPI_GIOCATORI = new String[][]
    {
        { "Marco", "Angelo custode" }, { "Giulio", "Pazzo" }, { "Cesare", "Peccatore" }, { "Augusto", "Prete" }, { "Annibale", "Guaritore" }
    };

    private GiocatoriVivi giocatori;

    @BeforeEach public void setUp()
    {
        FACTORY.annullaVoti();
        giocatori = new GiocatoriVivi();
        for(String[] esempio : ESEMPI_GIOCATORI) giocatori.aggiungiGiocatore(esempio[0], getRuolo(esempio[1]));
        giocatori.resettaAmato();
    }

    @Test public void testBallottaggioPuro()
    {
        int[] numeroVoti = new int[] { 2, 1 };
        for(int i = 0; i < numeroVoti.length; i++) incrementaVoti(i + 3, numeroVoti[i]);
        verificaAccusati(getNomeGiocatore(4), getNomeGiocatore(3));
    }

    @Test public void testUnanimita()
    {
        int posizione = 0;
        incrementaVoti(posizione, 3);
        verificaAccusati(getNomeGiocatore(posizione));
    }

    @Test public void testPareggioPrimoPosto()
    {
        for(int i = 2; i < getNumeroGiocatoriEsempio(); i++) incrementaVoti(i, 1);
        verificaAccusati(getNomeGiocatore(4), getNomeGiocatore(3), getNomeGiocatore(2));
    }

    @Test public void testPareggioSecondoPosto()
    {
        int[] numeroVoti = new int[]{ 2, 1, 1 };
        for(int i = 0; i < numeroVoti.length; i++) incrementaVoti(i, numeroVoti[i]);
        verificaAccusati(getNomeGiocatore(2), getNomeGiocatore(1), getNomeGiocatore(0));
    }

    @Test public void testSegnalazioneAngeloCustode()
    {
        String nome = getNomeGiocatore(3);
        segnalazioneAngeloCustode(nome);
        verificaVero(giocatori.isAmato(nome));
    }

    @Test public void testAngeloCustodeAccusatoNonPresente()
    {
        int posizione = 2;
        segnalazioneAngeloCustode(getNomeGiocatore(posizione));
        incrementaVoti(posizione, 3);
        verificaGiocatoreAccusato(getBallottaggio(), 0, getNomeGiocatore(0));
    }

    @Test public void testAngeloCustodeAccusatoPresente()
    {
        segnalazioneAngeloCustode(getNomeGiocatore(1));
        for(int i = 0; i < 3; i++) incrementaVoti(i, 2);
        verificaAccusati(getNomeGiocatore(2), getNomeGiocatore(0));
    }

    @Test public void testAttaccoAssassino() { verificaAttaccoAssassino(getNomeGiocatore(3), RIUSCITO); }

    @Test public void testAttaccoAmatoAssassino()
    {
        String nome = getNomeGiocatore(2);
        segnalazioneAngeloCustode(nome);
        verificaAttaccoAssassino(nome, FALLITO);
    }

    @Test public void testSegnalazioneAzzeccagarbugli()
    {
        segnalazioneAzzeccagarbugli(getNomeGiocatore(1));
        for(int i = 3; i < getNumeroGiocatoriEsempio(); i++) incrementaVoti(i, 1);
        verificaAccusati(getNomeGiocatore(4), getNomeGiocatore(3), getNomeGiocatore(1));
    }

    @Test public void testSegnalazioneAzzeccagarbugliAmato()
    {
        String nome = getNomeGiocatore(3);
        segnalazioneAzzeccagarbugli(nome);
        segnalazioneAngeloCustode(nome);
        for(int i = 3; i < getNumeroGiocatoriEsempio(); i++) incrementaVoti(i, 1);
        verificaAccusati(getNomeGiocatore(4), getNomeGiocatore(0));
    }

    @Test public void testAttaccoLupiAngeloCustode() { verificaAttaccoCapoBranco(getNomeGiocatore(0), RIUSCITO); }

    @Test public void testAttaccoLupiAmato()
    {
        String nome = getNomeGiocatore(2);
        segnalazioneAngeloCustode(nome);
        verificaAttaccoCapoBranco(nome, FALLITO);
    }

    @Test public void testSegnalazioneInquisitoreMisticoAssente()
    {
        String nomeMistico = getNomeGiocatore(getNumeroGiocatoriEsempio() - 1);
        giocatori.segnalazioneInquisitore(nomeMistico);
        int posizioneVoto = 1;
        incrementaVoti(posizioneVoto, 2);
        verificaAccusati(nomeMistico, getNomeGiocatore(posizioneVoto));
    }

    private void verificaAccusati(String... soluzioni)
    {
        int numeroSoluzioni = soluzioni.length;
        Giocatori ballottaggio = getBallottaggio();
        verificaNumeroAccusati(ballottaggio, numeroSoluzioni);
        for (int i = 0; i < numeroSoluzioni; i++) verificaGiocatoreAccusato(ballottaggio, i, soluzioni[i]);
    }

    private void verificaAttaccoAssassino(String nome, EsitoAttacco esito) { assertThat(giocatori.attaccoAssassino(nome)).isEqualTo(esito); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void segnalazioneAngeloCustode(String nome) { giocatori.segnalazioneAngeloCustode(nome); }

    private void incrementaVoti(int posizione, int voti) { giocatori.incrementaVoti(getNomeGiocatore(posizione), voti); }

    private void verificaGiocatoreAccusato(Giocatori ballottaggio, int posizione, String nome)
    {
        verificaNomeGiocatore(ballottaggio.getNomeGiocatore(posizione), nome);
    }

    private void verificaNomeGiocatore(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private String getNomeGiocatore(int posizione) { return ESEMPI_GIOCATORI[posizione][0]; }

    private void verificaNumeroAccusati(Giocatori ballottaggio, int numeroAccusati)
    {
        assertThat(ballottaggio.getNumeroGiocatori()).isEqualTo(numeroAccusati);
    }

    private Giocatori getBallottaggio() { return giocatori.getBallottaggio(); }

    private int getNumeroGiocatoriEsempio() { return ESEMPI_GIOCATORI.length; }

    private void segnalazioneAzzeccagarbugli(String nome) { giocatori.segnalazioneAzzeccagarbugli(nome); }

    private Ruolo getRuolo(String nome) { return FACTORY.getRuolo(nome); }

    private void verificaAttaccoCapoBranco(String nome, EsitoAttacco esito)
    {
        assertThat(giocatori.attaccoLupi(getRuolo("Capo branco"), nome)).isEqualTo(esito);
    }

}