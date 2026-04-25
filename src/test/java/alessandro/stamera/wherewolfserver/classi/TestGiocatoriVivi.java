package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static alessandro.stamera.wherewolfserver.classi.EsitoAttacco.*;
import static alessandro.stamera.wherewolfserver.classi.Fazione.NOSFERATU;
import static alessandro.stamera.wherewolfserver.classi.Fazione.VAMPIRO;
import static alessandro.stamera.wherewolfserver.classi.Fazione.AMANTI;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
import static alessandro.stamera.wherewolfserver.classi.Tratto.NON_MORTO;
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
        for(String[] esempio : ESEMPI_GIOCATORI) aggiungiGiocatore(esempio[0], esempio[1]);
        FACTORY.resettaRomeo();
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
        verificaVero(isAmato(nome));
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

    @Test public void testSegnalazioneAzzeccagarbugliAngeloCustode()
    {
        segnalazioneAzzeccagarbugli(getNomeGiocatore(1));
        for(int i = 3; i < getNumeroGiocatoriEsempio(); i++) incrementaVoti(i, 1);
        verificaAccusati(getNomeGiocatore(4), getNomeGiocatore(3), getNomeGiocatore(1));
    }

    @Test public void testSegnalazioneAzzeccagarbugliCriminale()
    {
        String nome = "Rodolfo";
        aggiungiGiocatore(nome, "Assassino");
        segnalazioneAzzeccagarbugli(nome);
        giocatori.incrementaVoti(nome, 4);
        assertThat(giocatori.getNumeroVoti(nome)).isZero();
    }

    @Test public void testSegnalazioneAzzeccagarbugliAmato()
    {
        String nome = getNomeGiocatore(4);
        segnalazioneAzzeccagarbugli(nome);
        segnalazioneAngeloCustode(nome);
        for(int i = 3; i < getNumeroGiocatoriEsempio(); i++) incrementaVoti(i, 1);
        verificaAccusati(getNomeGiocatore(3), getNomeGiocatore(0));
    }

    @ParameterizedTest
    @CsvSource({ "Capo branco, Lupo del branco, Lupo reietto, Lupo solitario, Contadino discendente dei lupi" })
    public void testAttaccoLupiAngeloCustode(String nomeLupo) { verificaAttaccoLupo(nomeLupo, getNomeGiocatore(0), RIUSCITO); }

    @ParameterizedTest
    @CsvSource({ "Capo branco, Lupo del branco, Lupo reietto, Lupo solitario, contadino discendente dei lupi" })
    public void testAttaccoLupiAmato(String nomeLupo)
    {
        String nome = getNomeGiocatore(2);
        segnalazioneAngeloCustode(nome);
        verificaAttaccoLupo(nomeLupo, nome, FALLITO);
    }

    @Test public void testSegnalazioneInquisitoreMisticoAssente()
    {
        String nomeMistico = getNomeGiocatore(getNumeroGiocatoriEsempio() - 1);
        segnalazioneInquisitore(nomeMistico);
        int posizioneVoto = 1;
        incrementaVoti(posizioneVoto, 2);
        verificaAccusati(nomeMistico, getNomeGiocatore(posizioneVoto));
    }

    @Test public void testSegnalazioneInquisitoreMisticoPresente()
    {
        int posizioneMistico = getNumeroGiocatoriEsempio() - 1;
        String nomeMistico = getNomeGiocatore(posizioneMistico);
        segnalazioneInquisitore(nomeMistico);
        for(int i = 3; i < getNumeroGiocatoriEsempio(); i++) incrementaVoti(i, 2);
        verificaAccusati(nomeMistico, getNomeGiocatore(posizioneMistico - 1));
    }

    @Test public void testSegnalazioneInquisitoreMisticoAssenteAmato()
    {
        String nomeMistico = getNomeGiocatore(getNumeroGiocatoriEsempio() - 1);
        segnalazioneInquisitore(nomeMistico);
        segnalazioneAngeloCustode(nomeMistico);
        int posizioneVoto = 1;
        incrementaVoti(posizioneVoto, 2);
        verificaAccusati(getNomeGiocatore(posizioneVoto), getNomeGiocatore(0));
    }

    @Test public void testSegnalazioneInquisitoreMisticoPresenteAmato()
    {
        int posizioneMistico = getNumeroGiocatoriEsempio() - 1;
        String nomeMistico = getNomeGiocatore(posizioneMistico);
        segnalazioneInquisitore(nomeMistico);
        segnalazioneAngeloCustode(nomeMistico);
        for(int i = 3; i < getNumeroGiocatoriEsempio(); i++) incrementaVoti(i, 2);
        verificaAccusati(getNomeGiocatore(posizioneMistico - 1), getNomeGiocatore(0));
    }

    @Test public void testAttaccoNosferatuAngeloCustode()
    {
        String nomeAmato = getNomeGiocatore(3), nomeAngelo = getNomeGiocatore(0);
        segnalazioneAngeloCustode(nomeAmato);
        verificaProgenie(nomeAmato, nomeAngelo, NOSFERATU);
        resettaAngeloCustode();
    }

    @Test public void testAttaccoVampiroAngeloCustode()
    {
        String nomeAmato = getNomeGiocatore(3), nomeAngelo = getNomeGiocatore(0);
        segnalazioneAngeloCustode(nomeAmato);
        verificaProgenie(nomeAmato, nomeAngelo, VAMPIRO);
        resettaAngeloCustode();
    }

    @Test public void testPossedutoAngeloCustode()
    {
        String nomeAngelo = getNomeGiocatore(0), nomeAmato = getNomeGiocatore(1);
        segnalazioneAngeloCustode(nomeAmato);
        giocatori.attaccoPosseduto(nomeAngelo);
        assertThat(giocatori.isPosseduto(nomeAngelo)).isTrue();
        verificaNonAmato(nomeAmato);
    }

    @Test public void testAttaccoAssassinoContadinoMostro()
    {
        String nome = "Matilde";
        aggiungiGiocatore(nome, "Contadino mostro");
        verificaAttaccoAssassino(nome, MORTO);
    }

    @Test public void testSegnalatoAzzeccagarbugli()
    {
        String nome = "Anna";
        aggiungiGiocatore(nome, "Leprecauno");
        giocatori.segnalazioneAzzeccagarbugli(nome);
        assertThat(giocatori.isSegnalatoAzzeccagarbugli(nome)).isTrue();
    }

    @Test public void testGuardia()
    {
        String[][] giocatori = new String[][] { { "Federico", "Altra guardia" }, { "Jacopo", "Assassino" } };
        for(String[] giocatore : giocatori) aggiungiGiocatore(giocatore[0], giocatore[1]);
        assertThat(isGuardia(giocatori[0][0])).isTrue();
        assertThat(isGuardia(giocatori[1][0])).isFalse();
    }

    private boolean isGuardia(String nome) { return giocatori.isGuardia(nome); }

    private void segnalazioneAzzeccagarbugli(String nome) { giocatori.segnalazioneAzzeccagarbugli(nome); }

    private void aggiungiGiocatore(String nomeGiocatore, String nomeRuolo) { giocatori.aggiungiGiocatore(nomeGiocatore, getRuolo(nomeRuolo)); }

    private void verificaProgenie(String nomeAmato, String nomeAngelo, Fazione fazione)
    {
        EsitoAttacco esito = null;
        switch(fazione)
        {
            case NOSFERATU -> esito = giocatori.attaccoNosferatu(nomeAngelo);
            case VAMPIRO -> esito = giocatori.attaccoVampiro(nomeAngelo);
        }
        assertThat(esito).isEqualTo(RIUSCITO);
        verificaNonAmato(nomeAmato);
        verificaVero(giocatori.isTrattoPresente(nomeAngelo, NON_MORTO));
        assertThat(giocatori.getFazione(nomeAngelo)).isEqualTo(fazione);
    }

    private void verificaAccusati(String... soluzioni)
    {
        int numeroSoluzioni = soluzioni.length;
        Giocatori ballottaggio = getBallottaggio();
        assertThat(ballottaggio.getNumeroGiocatori()).isEqualTo(numeroSoluzioni);
        for (int i = 0; i < numeroSoluzioni; i++) verificaGiocatoreAccusato(ballottaggio, i, soluzioni[i]);
    }

    private void verificaAttaccoAssassino(String nome, EsitoAttacco esito)
    {
        assertThat(giocatori.attaccoAssassino(nome)).isEqualTo(esito);
    }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void segnalazioneAngeloCustode(String nome) { giocatori.segnalazioneAngeloCustode(nome); }

    private void segnalazioneInquisitore(String nome) { giocatori.segnalazioneInquisitore(nome); }

    private void incrementaVoti(int posizione, int voti)
    {
        giocatori.incrementaVoti(getNomeGiocatore(posizione), voti);
    }

    private void verificaGiocatoreAccusato(Giocatori ballottaggio, int posizione, String nome)
    {
        verificaNomeGiocatore(ballottaggio.getNomeGiocatore(posizione), nome);
    }

    private void verificaNomeGiocatore(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private String getNomeGiocatore(int posizione) { return ESEMPI_GIOCATORI[posizione][0]; }

    private Giocatori getBallottaggio() { return giocatori.getBallottaggio(); }

    private int getNumeroGiocatoriEsempio() { return ESEMPI_GIOCATORI.length; }

    private Ruolo getRuolo(String nome) { return FACTORY.getRuolo(nome); }

    private void verificaAttaccoLupo(String nomeLupo, String nome, EsitoAttacco esito)
    {
        assertThat(giocatori.attaccoLupi(getRuolo(nomeLupo), nome)).isEqualTo(esito);
    }

    private void verificaNonAmato(String nome) { assertThat(isAmato(nome)).isFalse(); }

    private boolean isAmato(String nome) { return giocatori.isAmato(nome); }

    private void resettaAngeloCustode()
    {
        Ruolo ruolo = giocatori.getRuolo(getNomeGiocatore(0));
        ruolo.cambiaFazione(AMANTI);
        ruolo.eliminaTratto(NON_MORTO);
    }

}