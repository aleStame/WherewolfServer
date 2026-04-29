package alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Tratto;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.FALLITO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.RIUSCITO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.*;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Tratto.NON_MORTO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Tratto.PROTETTO;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestRuolo
{

    private static final int ESEMPIO_VOTI = 3;

    private Ruolo ruolo;

    @BeforeEach public void setUp()
    {
        ruolo = new Ruolo(null, null, null, null, -1, false);
    }

    @Test public void testInizializzazione()
    {
        verificaNessunVoto();
        verificaFalso(isAmato());
        verificaAssenzaProtezioni();
    }

    @Test public void testVoti()
    {
        ruolo.incrementaVoti(ESEMPIO_VOTI);
        verificaVoti(ESEMPIO_VOTI);
        ruolo.annullaVoti();
        verificaNessunVoto();
        verificaVero(maledizione());
        verificaVoti(1);
        verificaVero(isMaledetto());
        verificaAuraNera();
    }

    @ParameterizedTest
    @CsvSource({ "Capo branco, Lupo del branco, Lupo solitario, Lupo reietto, Contadino discendente dei lupi" })
    public void testAttaccoLupiAmato(String nome)
    {
        sceltaAngeloCustode();
        verificaVero(isAmato());
        verificaProtetto();
        verificaVero(isProtezioneLupiPresente());
        verificaAttaccoFallito(attaccoLupi(nome));
        verificaAssenzaProtezioni();
    }

    @Test public void testGildata()
    {
        Fazione fazione = getFazione();
        ruolo.gildata();
        verificaFazione(fazione);
    }

    @ParameterizedTest
    @CsvSource( { "Capo branco, Lupo del branco, Lupo solitario, Lupo reietto, Contadino discendente dei lupi" } )
    public void testAttaccoLupoRomeo(String nome)
    {
        romeizzazione();
        verificaProtetto();
        verificaAttaccoLupiRiuscito(nome);
    }

    @ParameterizedTest
    @CsvSource({ "Capo branco, Lupo del branco, Lupo solitario, Lupo reietto, Contadino discendente dei lupi" })
    public void testAttaccoRuoloNonProtetto(String nome) { verificaAttaccoLupiRiuscito(nome); }

    @Test public void testAttaccoNosferatu()
    {
        verificaAttaccoRiuscito(attaccoNosferatu());
        verificaTrattoPresente(NON_MORTO);
        verificaFazione(NOSFERATU);
    }

    @Test public void testAttaccoNosferatuAmato()
    {
        sceltaAngeloCustode();
        verificaAttaccoFallito(ruolo.attaccoNosferatu());
        verificaAssenzaProtezioni();
    }

    @Test public void testAttaccoNegromanteRomeo()
    {
        romeizzazione();
        verificaFalso(maledizione());
        verificaFalso(isMaledetto());
    }

    @ParameterizedTest
    @CsvSource({ "Capo branco, Lupo del branco, Lupo solitario, Lupo reietto, Contadino discendente dei lupi, Nosferatu, Posseduto" })
    public void testRomeizzazione(String nomeCreaturaOmbra)
    {
        romeizzazione();
        verificaProtetto();
        verificaFazione(AMANTI);
        verificaVero(ruolo.isProtezionePresente(FACTORY.getRuolo(nomeCreaturaOmbra)));
    }

    @Test public void attaccoAssassino() { verificaAttaccoRiuscito(assassinio()); }

    @Test public void attaccoAssassinoAmato()
    {
        sceltaAngeloCustode();
        verificaAttaccoFallito(assassinio());
    }

    @Test public void vampirizzazione()
    {
        verificaAttaccoRiuscito(ruolo.vampirizzazione());
        verificaAuraNera();
        verificaFazione(VAMPIRO);
        verificaTrattoPresente(NON_MORTO);
    }

    @Test public void testSegnalazioneAzzeccagarbugli()
    {
        verificaNonSegnalato();
        ruolo.segnalazioneAzzeccagarbugli();
        verificaVero(isSegnalatoAzzeccagarbugli());
        ruolo.annullaSegnalazioneAzzeccagarbugli();
        verificaNonSegnalato();
    }

    @Test public void testSegnalazioneInquisitore()
    {
        verificaNonInquisito();
        ruolo.segnalazioneInquisitore();
        verificaVero(isInquisito());
        ruolo.annullaSegnalazioneInquisitore();
        verificaNonInquisito();
    }

    @Test public void testSegnalazioneBoia()
    {
        ruolo.segnalazioneBoia();
        verificaFalso(ruolo.isSegnalatoBoia());
    }

    private void verificaNonSegnalato() { verificaFalso(isSegnalatoAzzeccagarbugli()); }

    private void verificaNonInquisito() { verificaFalso(isInquisito()); }

    private boolean isSegnalatoAzzeccagarbugli() { return ruolo.isSegnalatoAzzeccagarbugli(); }

    private boolean isInquisito() { return ruolo.isInquisito(); }

    private EsitoAttacco attaccoNosferatu() { return ruolo.attaccoNosferatu(); }

    private EsitoAttacco assassinio() { return ruolo.attaccoAssassino(); }

    private void romeizzazione() { ruolo.romeizzazione(); }

    private void sceltaAngeloCustode() { ruolo.sceltaAngeloCustode(); }

    private boolean maledizione() { return ruolo.maledizione(); }

    private boolean isMaledetto() { return ruolo.isMaledetto(); }

    private void verificaAttaccoFallito(EsitoAttacco esito) { verificaAttacco(esito, FALLITO); }

    private void verificaAttaccoLupiRiuscito(String nome) { verificaAttaccoRiuscito(attaccoLupi(nome)); }

    private void verificaAttaccoRiuscito(EsitoAttacco esito) { verificaAttacco(esito, RIUSCITO); }

    private void verificaAttacco(EsitoAttacco valore, EsitoAttacco risultato) { assertThat(valore).isEqualTo(risultato); }

    private EsitoAttacco attaccoLupi(String nome) { return ruolo.attaccoLupi(FACTORY.getRuolo(nome)); }

    private void verificaAssenzaProtezioni()
    {
        verificaFalso(isProtezioneLupiPresente());
        verificaFalso(ruolo.isProtezioneNegromantePresente());
    }

    private void verificaProtetto() { verificaTrattoPresente(PROTETTO); }

    private boolean isAmato() { return ruolo.isAmato(); }

    private void verificaNessunVoto() { assertThat(getNumeroVoti()).isZero(); }

    private void verificaVoti(int voti) { assertThat(getNumeroVoti()).isEqualTo(voti); }

    private int getNumeroVoti() { return ruolo.getNumeroVoti(); }

    private void verificaTrattoPresente(Tratto tratto) { verificaVero(isTrattoPresente(tratto)); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

    private void verificaFazione(Fazione risultato) { assertThat(getFazione()).isEqualTo(risultato); }

    private Fazione getFazione() { return ruolo.getFazione(); }

    private boolean isProtezioneLupiPresente() { return ruolo.isProtezioneLupiPresente(); }

    private boolean isTrattoPresente(Tratto tratto) { return ruolo.isTrattoPresente(tratto); }

    private void verificaAuraNera() { assertThat(ruolo.getAura()).isEqualTo(NERA); }

}